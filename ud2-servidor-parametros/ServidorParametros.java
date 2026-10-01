/**
 * UD2 · Desarrollo web en entorno servidor 0613 · CDM Alcorcon · 26/27
 *
 * El servidor que hace a mano lo que Spring te da hecho.
 *
 * Tres rutas:
 *   /crudo     devuelve la peticion tal y como llega, sin interpretar nada
 *   /pedido    lee parametros, los convierte y calcula: es lo que hara @RequestParam
 *   /plantilla rellena un HTML con marcas {{...}}: es lo que hara Thymeleaf
 *
 * No usa ninguna libreria externa. Solo el JDK 21.
 *
 *   javac -encoding UTF-8 ServidorParametros.java
 *   java ServidorParametros 8081
 *
 * Y luego, en otra terminal o en el navegador:
 *   http://localhost:8081/crudo?nombre=Ana&cantidad=3
 *   http://localhost:8081/pedido?producto=Cubierta&precio=19.99&cantidad=3
 *   http://localhost:8081/plantilla?nombre=Ana
 */
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class ServidorParametros {

    public static void main(String[] args) throws IOException {
        int puerto = args.length > 0 ? Integer.parseInt(args[0]) : 8081;
        HttpServer servidor = HttpServer.create(new InetSocketAddress(puerto), 0);

        servidor.createContext("/crudo", ServidorParametros::crudo);
        servidor.createContext("/pedido", ServidorParametros::pedido);
        servidor.createContext("/plantilla", ServidorParametros::plantilla);

        servidor.setExecutor(null);
        servidor.start();
        System.out.println("Escuchando en http://localhost:" + puerto);
        System.out.println("  /crudo?nombre=Ana&cantidad=3");
        System.out.println("  /pedido?producto=Cubierta&precio=19.99&cantidad=3");
        System.out.println("  /plantilla?nombre=Ana");
    }

    /* ------------------------------------------------------------------ 1 */
    /** Devuelve la peticion sin tocar. Sirve para ver que TODO llega como texto. */
    private static void crudo(HttpExchange intercambio) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("Metodo        : ").append(intercambio.getRequestMethod()).append('\n');
        sb.append("Ruta          : ").append(intercambio.getRequestURI().getPath()).append('\n');
        sb.append("Query (texto) : ").append(intercambio.getRequestURI().getRawQuery()).append('\n');
        sb.append('\n');
        sb.append("Troceada en pares clave=valor:\n");
        for (Map.Entry<String, String> e : parametros(intercambio).entrySet()) {
            sb.append("  ").append(e.getKey())
              .append(" = \"").append(e.getValue()).append("\"")
              .append("   (tipo Java: ").append(e.getValue().getClass().getSimpleName()).append(")\n");
        }
        responder(intercambio, sb.toString(), "text/plain; charset=utf-8");
    }

    /* ------------------------------------------------------------------ 2 */
    /** Lee, convierte y calcula. Esto es, paso a paso, lo que hace @RequestParam. */
    private static void pedido(HttpExchange intercambio) throws IOException {
        Map<String, String> p = parametros(intercambio);

        // Paso 1: el parametro puede no venir. Valor por defecto.
        String producto = p.getOrDefault("producto", "sin nombre");

        // Paso 2: convertir. Y si el usuario escribio cualquier cosa, no reventar.
        int cantidad;
        try {
            cantidad = Integer.parseInt(p.getOrDefault("cantidad", "1"));
        } catch (NumberFormatException e) {
            responder(intercambio, 400,
                    "400 - cantidad tiene que ser un numero entero. Llego: \""
                            + p.get("cantidad") + "\"\n",
                    "text/plain; charset=utf-8");
            return;
        }

        double precio;
        try {
            precio = Double.parseDouble(p.getOrDefault("precio", "0"));
        } catch (NumberFormatException e) {
            responder(intercambio, 400,
                    "400 - precio tiene que ser un numero. Llego: \""
                            + p.get("precio") + "\"\n",
                    "text/plain; charset=utf-8");
            return;
        }

        // Paso 3: validar. El navegador no es de fiar.
        if (cantidad < 1) {
            responder(intercambio, 400, "400 - la cantidad minima es 1.\n",
                    "text/plain; charset=utf-8");
            return;
        }

        // Paso 4: calcular EN EL SERVIDOR.
        double base = precio * cantidad;
        double iva = base * 0.21;
        double total = base + iva;

        String html = """
                <!doctype html>
                <html lang="es"><head><meta charset="utf-8"><title>Pedido</title></head>
                <body>
                  <h1>%s</h1>
                  <p>%d unidades a %.2f EUR</p>
                  <p>Base: %.2f EUR</p>
                  <p>IVA (21%%): %.2f EUR</p>
                  <p><strong>Total: %.2f EUR</strong></p>
                </body></html>
                """.formatted(escapar(producto), cantidad, precio, base, iva, total);

        responder(intercambio, html, "text/html; charset=utf-8");
    }

    /* ------------------------------------------------------------------ 3 */
    /** Rellena una plantilla con marcas. Esto es lo que hace Thymeleaf, en pequenito. */
    private static void plantilla(HttpExchange intercambio) throws IOException {
        String plantilla = """
                <!doctype html>
                <html lang="es"><head><meta charset="utf-8"><title>Saludo</title></head>
                <body>
                  <h1>Hola, {{nombre}}</h1>
                  <p>Son las {{hora}} en el servidor.</p>
                </body></html>
                """;

        Map<String, String> p = parametros(intercambio);
        String nombre = p.getOrDefault("nombre", "alguien");

        String html = plantilla
                .replace("{{nombre}}", escapar(nombre))
                .replace("{{hora}}", java.time.LocalTime.now().withNano(0).toString());

        responder(intercambio, html, "text/html; charset=utf-8");
    }

    /* ---------------------------------------------------------- auxiliares */

    /** Trocea la query string en pares. Todos los valores son String. Siempre. */
    private static Map<String, String> parametros(HttpExchange intercambio) {
        Map<String, String> mapa = new HashMap<>();
        String query = intercambio.getRequestURI().getRawQuery();
        if (query == null || query.isEmpty()) {
            return mapa;
        }
        for (String par : query.split("&")) {
            int igual = par.indexOf('=');
            if (igual < 0) {
                mapa.put(URLDecoder.decode(par, StandardCharsets.UTF_8), "");
            } else {
                mapa.put(URLDecoder.decode(par.substring(0, igual), StandardCharsets.UTF_8),
                         URLDecoder.decode(par.substring(igual + 1), StandardCharsets.UTF_8));
            }
        }
        return mapa;
    }

    /** Sin esto, quien escriba HTML en el formulario lo ve ejecutado. Es la UD4. */
    private static String escapar(String texto) {
        return texto.replace("&", "&amp;").replace("<", "&lt;")
                    .replace(">", "&gt;").replace("\"", "&quot;");
    }

    private static void responder(HttpExchange i, String cuerpo, String tipo) throws IOException {
        responder(i, 200, cuerpo, tipo);
    }

    private static void responder(HttpExchange i, int estado, String cuerpo, String tipo)
            throws IOException {
        byte[] bytes = cuerpo.getBytes(StandardCharsets.UTF_8);
        i.getResponseHeaders().set("Content-Type", tipo);
        i.sendResponseHeaders(estado, bytes.length);
        try (OutputStream os = i.getResponseBody()) {
            os.write(bytes);
        }
    }
}
