/**
 * UD3 · Desarrollo web en entorno servidor 0613 · Curso 26/27
 *
 * Guardar conjuntos: arrays, listas y tipos compuestos (record).
 *
 *   javac -encoding UTF-8 Colecciones.java
 *   java Colecciones
 */
import java.util.ArrayList;
import java.util.List;

public class Colecciones {

    /** Tipo compuesto: junta los campos que van juntos. */
    public record Articulo(String nombre, double precio, int stock) { }

    public static void main(String[] args) {

        System.out.println("=== 1 - Array: tamano fijo, indice desde cero ===");
        String[] tallas = {"S", "M", "L", "XL"};
        System.out.println("    tallas.length : " + tallas.length + "   <- atributo, SIN parentesis");
        System.out.println("    tallas[0]     : " + tallas[0]);
        System.out.println("    tallas[3]     : " + tallas[3] + "   <- el ultimo es length - 1");

        System.out.println();
        System.out.println("=== 2 - Recorrerlo de las dos formas ===");
        for (int i = 0; i < tallas.length; i++) {
            System.out.println("    " + (i + 1) + " - " + tallas[i]);
        }
        for (String t : tallas) {
            System.out.print("    " + t);
        }
        System.out.println();

        System.out.println();
        System.out.println("=== 3 - Pasarse de indice ===");
        try {
            System.out.println(tallas[4]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("    ArrayIndexOutOfBoundsException: " + e.getMessage());
            System.out.println("    Si el indice viene de la URL, hay que comprobarlo ANTES:");
            System.out.println("    if (i >= 0 && i < tallas.length) { ... }");
        }

        System.out.println();
        System.out.println("=== 4 - Lista: crece y mengua ===");
        List<String> tareas = new ArrayList<>();
        tareas.add("Revisar el pedido");
        tareas.add("Llamar al proveedor");
        System.out.println("    tareas.size()    : " + tareas.size() + "   <- metodo, CON parentesis");
        System.out.println("    tareas.get(0)    : " + tareas.get(0));
        System.out.println("    tareas.isEmpty() : " + tareas.isEmpty());

        System.out.println();
        System.out.println("=== 5 - List.of no se puede modificar ===");
        List<String> fijas = List.of("uno", "dos");
        try {
            fijas.add("tres");
        } catch (UnsupportedOperationException e) {
            System.out.println("    UnsupportedOperationException al hacer add sobre List.of");
            System.out.println("    Para una lista que crece: new ArrayList<>()");
        }

        System.out.println();
        System.out.println("=== 6 - El catalogo, con un tipo compuesto ===");
        List<Articulo> catalogo = List.of(
                new Articulo("Cubierta 700x25", 24.90, 12),
                new Articulo("Camara 700", 5.50, 40),
                new Articulo("Cadena 11v", 34.00, 0),
                new Articulo("Maillot club", 45.00, 3),
                new Articulo("Bidon 750 ml", 6.20, 0));

        for (Articulo a : catalogo) {
            System.out.println("    " + a.nombre() + " - " + a.precio() + " EUR - stock " + a.stock());
        }

        System.out.println();
        System.out.println("=== 7 - Recorrer para contar y para sumar ===");
        int agotados = 0;
        double valor = 0;
        for (Articulo a : catalogo) {
            if (a.stock() == 0) {
                agotados++;
            }
            valor = valor + a.precio() * a.stock();
        }
        System.out.println("    agotados          : " + agotados);
        System.out.println("    valor del almacen : " + valor);

        System.out.println();
        System.out.println("=== 8 - Lo que da un record gratis ===");
        System.out.println("    toString : " + catalogo.get(0));
        System.out.println("    equals   : " + catalogo.get(0).equals(
                new Articulo("Cubierta 700x25", 24.90, 12)));
        System.out.println("    (dos articulos con los mismos datos son iguales, sin escribir nada)");
    }
}
