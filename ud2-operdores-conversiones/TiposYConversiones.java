/**
 * UD2 · Desarrollo web en entorno servidor 0613 · Curso 26/27
 *
 * Tipos, conversiones y lo que pasa cuando el dato llega como texto.
 * Se compila y se ejecuta sin nada instalado:
 *
 *   javac -encoding UTF-8 TiposYConversiones.java
 *   java TiposYConversiones
 */
public class TiposYConversiones {

    public static void main(String[] args) {

        System.out.println("=== 1 - Tipos primitivos: cada uno ocupa lo que ocupa ===");
        int entero = 2147483647;          // el mayor int que existe
        long grande = 2147483648L;        // la L no es decoracion: sin ella no compila
        double decimal = 19.99;
        boolean logico = true;
        char letra = 'A';
        System.out.println("int maximo      : " + entero);
        System.out.println("long            : " + grande);
        System.out.println("double          : " + decimal);
        System.out.println("boolean         : " + logico);
        System.out.println("char            : " + letra + "  (como numero: " + (int) letra + ")");

        System.out.println();
        System.out.println("=== 2 - Desbordamiento: sumar 1 al mayor int ===");
        System.out.println("entero + 1      : " + (entero + 1));
        System.out.println("Java no avisa. Da la vuelta y sigue.");

        System.out.println();
        System.out.println("=== 3 - Division entera: la trampa clasica ===");
        int a = 7, b = 2;
        System.out.println("7 / 2           : " + (a / b) + "   <- int / int siempre da int");
        System.out.println("7 % 2           : " + (a % b));
        System.out.println("7 / 2.0         : " + (a / 2.0));
        System.out.println("(double) 7 / 2  : " + ((double) a / b));

        System.out.println();
        System.out.println("=== 4 - double no es para dinero ===");
        System.out.println("0.1 + 0.2       : " + (0.1 + 0.2));
        System.out.println("19.99 * 3       : " + (19.99 * 3));
        System.out.println("Para precios se usa BigDecimal, que se vera en la UD6.");

        System.out.println();
        System.out.println("=== 5 - El + con Strings no suma: concatena ===");
        int x = 5, y = 3;
        System.out.println("x + y           : " + (x + y));
        System.out.println("\"\" + x + y      : " + "" + x + y + "   <- ya es texto, pega uno detras de otro");
        System.out.println("\"\" + (x + y)    : " + "" + (x + y) + "   <- los parentesis mandan");

        System.out.println();
        System.out.println("=== 6 - Lo que llega de un formulario es TEXTO ===");
        String cantidadTexto = "3";       // esto es lo que manda el navegador
        System.out.println("cantidadTexto   : \"" + cantidadTexto + "\"");
        System.out.println("cantidadTexto*2 : no compila. No se puede multiplicar texto.");
        int cantidad = Integer.parseInt(cantidadTexto);
        System.out.println("parseInt        : " + cantidad + "   -> ahora si: " + (cantidad * 2));

        System.out.println();
        System.out.println("=== 7 - Las cuatro conversiones que se usan a diario ===");
        System.out.println("Integer.parseInt(\"42\")        : " + Integer.parseInt("42"));
        System.out.println("Double.parseDouble(\"19.99\")   : " + Double.parseDouble("19.99"));
        System.out.println("Boolean.parseBoolean(\"true\")  : " + Boolean.parseBoolean("true"));
        System.out.println("String.valueOf(42)            : \"" + String.valueOf(42) + "\"");

        System.out.println();
        System.out.println("=== 8 - Y lo que pasa cuando el texto no es un numero ===");
        String loQueEscribioElUsuario = "tres";
        try {
            int n = Integer.parseInt(loQueEscribioElUsuario);
            System.out.println("no llega aqui: " + n);
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException: " + e.getMessage());
            System.out.println("Sin try/catch esto es un error 500 en la cara del usuario.");
        }

        System.out.println();
        System.out.println("=== 9 - int y Integer no son lo mismo ===");
        int primitivo = 0;
        Integer objeto = null;            // un objeto SI puede ser null; un int no
        System.out.println("int primitivo   : " + primitivo);
        System.out.println("Integer objeto  : " + objeto);
        try {
            int roto = objeto;            // autoboxing al reves, sobre null
            System.out.println("no llega aqui: " + roto);
        } catch (NullPointerException e) {
            System.out.println("NullPointerException al convertir un Integer null a int.");
            System.out.println("Es el error numero uno cuando un parametro no venia en la peticion.");
        }

        System.out.println();
        System.out.println("=== 10 - Comparar textos: == mide la caja, equals mide el contenido ===");
        String uno = new String("hola");
        String dos = new String("hola");
        System.out.println("uno == dos      : " + (uno == dos) + "   <- son dos objetos distintos");
        System.out.println("uno.equals(dos) : " + uno.equals(dos) + "    <- dicen lo mismo");
    }
}
