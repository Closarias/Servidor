/**
 * UD3 · Desarrollo web en entorno servidor 0613 · Curso 26/27
 *
 * Decidir: if, else if, comparaciones y ternario.
 * Se compila y se ejecuta sin nada instalado:
 *
 *   javac -encoding UTF-8 Decisiones.java
 *   java Decisiones
 */
public class Decisiones {

    static void p(String texto) {
        System.out.println("    " + texto);
    }

    public static void main(String[] args) {

        int unidades = 50;
        String forma = new String("envio");   // como si llegara de una peticion

        System.out.println("=== 1 - Dos if independientes ===");
        if (unidades > 10) { p("A"); }
        if (unidades > 40) { p("B"); }
        p("(los dos se comprueban: salen A y B)");

        System.out.println();
        System.out.println("=== 2 - El mismo caso con else if ===");
        if (unidades > 10) { p("A"); }
        else if (unidades > 40) { p("B"); }
        p("(en cuanto entra el primero, el resto ni se mira: solo A)");

        System.out.println();
        System.out.println("=== 3 - Comparar textos con == ===");
        if (forma == "envio") { p("SI"); } else { p("NO"); }
        p("(== pregunta si son el mismo objeto, no si dicen lo mismo)");

        System.out.println();
        System.out.println("=== 4 - Comparar textos con equals ===");
        if ("envio".equals(forma)) { p("SI"); } else { p("NO"); }
        p("(equals compara el contenido, que es lo que se queria)");

        System.out.println();
        System.out.println("=== 5 - El ternario ===");
        String r = (unidades > 40) ? "grande" : "pequeno";
        p(r);

        System.out.println();
        System.out.println("=== 6 - El literal delante protege del null ===");
        String vacio = null;
        p("\"club\".equals(null) -> " + "club".equals(vacio));
        try {
            p(String.valueOf(vacio.equals("club")));
        } catch (NullPointerException e) {
            p("null.equals(\"club\") -> NullPointerException, que en web es un 500");
        }

        System.out.println();
        System.out.println("=== 7 - El orden de la cadena decide ===");
        System.out.println("    unidades = 200");
        System.out.println("    bien:  " + descuentoBien(200) + " %");
        System.out.println("    mal:   " + descuentoMal(200) + " %   <- mismo codigo, orden cambiado");

        System.out.println();
        System.out.println("=== 8 - switch con flecha ===");
        for (String zona : new String[] {"peninsula", "canarias", "marte"}) {
            String plazo = switch (zona) {
                case "peninsula" -> "48 horas";
                case "baleares"  -> "72 horas";
                case "canarias"  -> "5 dias";
                default          -> "consultar";
            };
            p(zona + " -> " + plazo);
        }
    }

    /** De lo mas restrictivo a lo mas general. */
    static int descuentoBien(int unidades) {
        if (unidades >= 100) return 15;
        if (unidades >= 50)  return 10;
        if (unidades >= 10)  return 5;
        return 0;
    }

    /** Al reves: todos los pedidos grandes se llevan el descuento pequeno. */
    static int descuentoMal(int unidades) {
        if (unidades >= 10)  return 5;
        if (unidades >= 50)  return 10;
        if (unidades >= 100) return 15;
        return 0;
    }
}
