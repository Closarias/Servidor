/**
 * UD3 · Desarrollo web en entorno servidor 0613 · Curso 26/27
 *
 * Repetir: for, while, do-while, for-each, break y continue.
 * Y el error de uno, que es el que se falla siempre.
 *
 *   javac -encoding UTF-8 Bucles.java
 *   java Bucles
 */
public class Bucles {

    public static void main(String[] args) {

        System.out.println("=== 1 - for de 0 a 4: CINCO vueltas ===");
        int vueltas = 0;
        for (int i = 0; i < 5; i++) {
            System.out.println("    i = " + i);
            vueltas++;
        }
        System.out.println("    total de vueltas: " + vueltas);

        System.out.println();
        System.out.println("=== 2 - for de 1 a 5: tambien cinco ===");
        vueltas = 0;
        for (int i = 1; i <= 5; i++) vueltas++;
        System.out.println("    total de vueltas: " + vueltas);

        System.out.println();
        System.out.println("=== 3 - El error de uno: <= donde iba < ===");
        vueltas = 0;
        for (int i = 0; i <= 5; i++) vueltas++;
        System.out.println("    total de vueltas: " + vueltas + "   <- SEIS, no cinco");

        String[] tallas = {"S", "M", "L", "XL", "XXL"};
        System.out.println("    con un array de " + tallas.length + ", el indice 5 no existe:");
        try {
            for (int i = 0; i <= tallas.length; i++) {
                System.out.println("      " + tallas[i]);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("      ArrayIndexOutOfBoundsException: " + e.getMessage());
            System.out.println("      en una aplicacion web, eso es un 500");
        }

        System.out.println();
        System.out.println("=== 4 - while: se comprueba ANTES ===");
        int intentos = 0;
        boolean conectado = false;
        while (intentos < 3 && !conectado) {
            intentos++;
            conectado = (intentos == 3);      // se conecta al tercero
            System.out.println("    intento " + intentos + " -> conectado = " + conectado);
        }

        System.out.println();
        System.out.println("=== 5 - El while que no termina ===");
        System.out.println("    int i = 0; while (i < 3) { p(i); }");
        System.out.println("    i no cambia NUNCA: la condicion siempre es cierta.");
        System.out.println("    Aqui no se ejecuta a proposito: dejaria el programa colgado,");
        System.out.println("    y en un servidor deja el hilo de la peticion atrapado para siempre.");
        System.out.println("    Se arregla con una linea: i++;");

        System.out.println();
        System.out.println("=== 6 - do-while: se comprueba DESPUES ===");
        int n = 10;
        do {
            System.out.println("    entra una vez aunque la condicion sea falsa (n = " + n + ")");
        } while (n < 5);

        System.out.println();
        System.out.println("=== 7 - for-each: sin indice y sin poder pasarse ===");
        for (String t : tallas) {
            System.out.println("    " + t);
        }

        System.out.println();
        System.out.println("=== 8 - break y continue ===");
        for (String t : tallas) {
            if (t.equals("M")) {
                System.out.println("    " + t + " -> continue, salta a la siguiente");
                continue;
            }
            if (t.equals("XL")) {
                System.out.println("    " + t + " -> break, sale del bucle");
                break;
            }
            System.out.println("    " + t);
        }

        System.out.println();
        System.out.println("=== 9 - Lo que se hace de verdad en un servidor ===");
        double[] precios = {24.90, 5.50, 34.00, 45.00, 6.20};
        double suma = 0;
        for (double precio : precios) {
            suma = suma + precio;
        }
        System.out.println("    " + precios.length + " articulos, suma = " + suma);
        System.out.println("    (esto es lo que hace th:each en la plantilla, pero en Java)");
        System.out.println("    Y fijate en los decimales: es el double de la UD2 otra vez.");
    }
}
