/**
 * UD2 · Desarrollo web en entorno servidor 0613 · Curso 26/27
 *
 * Operadores, y las tres cosas que se cuelan en un examen.
 *
 *   javac -encoding UTF-8 Operadores.java
 *   java Operadores
 */
public class Operadores {

    public static void main(String[] args) {

        System.out.println("=== 1 - Aritmeticos ===");
        int a = 17, b = 5;
        System.out.println("a + b  : " + (a + b));
        System.out.println("a - b  : " + (a - b));
        System.out.println("a * b  : " + (a * b));
        System.out.println("a / b  : " + (a / b) + "    <- entera, se pierde el resto");
        System.out.println("a % b  : " + (a % b) + "    <- el resto, que es lo que se perdia");

        System.out.println();
        System.out.println("=== 2 - El resto sirve para algo: par o impar, y repartir ===");
        System.out.println("17 % 2 : " + (17 % 2) + "    -> impar");
        System.out.println("18 % 2 : " + (18 % 2) + "    -> par");
        System.out.println("Fila de la tabla numero 7, con 3 columnas: columna " + (7 % 3));

        System.out.println();
        System.out.println("=== 3 - Relacionales: siempre devuelven boolean ===");
        System.out.println("a > b  : " + (a > b));
        System.out.println("a == b : " + (a == b));
        System.out.println("a != b : " + (a != b));

        System.out.println();
        System.out.println("=== 4 - Logicos, y el cortocircuito ===");
        int stock = 0;
        // && para de evaluar en cuanto sabe que es falso: no llega a dividir.
        boolean seguro = (stock > 0) && (100 / stock > 5);
        System.out.println("(stock > 0) && (100 / stock > 5) : " + seguro
                + "   <- no ha dividido entre cero");
        try {
            // & SI evalua los dos lados. Y aqui revienta.
            boolean roto = (stock > 0) & (100 / stock > 5);
            System.out.println("no llega aqui: " + roto);
        } catch (ArithmeticException e) {
            System.out.println("Con & en vez de &&: ArithmeticException / " + e.getMessage());
        }

        System.out.println();
        System.out.println("=== 5 - Asignacion compuesta ===");
        int total = 10;
        total += 5;  System.out.println("total += 5 : " + total);
        total -= 3;  System.out.println("total -= 3 : " + total);
        total *= 2;  System.out.println("total *= 2 : " + total);
        total /= 4;  System.out.println("total /= 4 : " + total);

        System.out.println();
        System.out.println("=== 6 - Incremento: antes o despues ===");
        int i = 5;
        System.out.println("i++ vale " + (i++) + " y despues i vale " + i);
        int j = 5;
        System.out.println("++j vale " + (++j) + " y despues j vale " + j);

        System.out.println();
        System.out.println("=== 7 - Precedencia: * antes que +, y los parentesis por encima ===");
        System.out.println("2 + 3 * 4   : " + (2 + 3 * 4));
        System.out.println("(2 + 3) * 4 : " + ((2 + 3) * 4));

        System.out.println();
        System.out.println("=== 8 - El ternario, que sale mucho en las plantillas ===");
        int unidades = 0;
        String mensaje = unidades > 0 ? "Disponible" : "Agotado";
        System.out.println("unidades > 0 ? ... : ... -> " + mensaje);

        System.out.println();
        System.out.println("=== 9 - Y el que hace perder mas tiempo en clase ===");
        String respuesta = "si";
        System.out.println("respuesta == \"si\"       : " + (respuesta == "si")
                + "   <- hoy da true, y manana puede dar false");
        System.out.println("respuesta.equals(\"si\")  : " + respuesta.equals("si")
                + "   <- este es el que se usa");
        String otra = new String("si");
        System.out.println("otra == \"si\"            : " + (otra == "si")
                + "  <- mismo texto, otra caja");
    }

}
