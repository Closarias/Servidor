/**
 * UD2 · Desarrollo web en entorno servidor 0613 · Curso 26/27
 *
 * Ambitos de las variables, y por que esto no es teoria.
 *
 * En Spring, un @Controller se crea UNA VEZ y lo comparten todas las peticiones
 * de todos los usuarios. Un campo de instancia dentro de un controlador es, por
 * tanto, una variable compartida. Aqui se ve con un objeto y cuatro hilos, que
 * es exactamente la misma situacion.
 *
 *   javac -encoding UTF-8 Ambitos.java
 *   java Ambitos
 */
public class Ambitos {

    /** Ambito de CLASE (static): una sola copia para todo el programa. */
    static int peticionesTotales = 0;

    static final int USUARIOS = 8;
    static final int PETICIONES = 200000;

    /** Ambito de INSTANCIA: una copia por objeto. Como el objeto del controlador
     *  es uno solo, en la practica tambien esta compartida. */
    int contadorDelControlador = 0;

    public static void main(String[] args) throws InterruptedException {

        System.out.println("=== 1 - Ambito LOCAL: nace y muere dentro del metodo ===");
        demoLocal();
        demoLocal();
        System.out.println("Cada llamada empieza de cero. Es lo que se quiere en un controlador.");

        System.out.println();
        System.out.println("=== 2 - Ambito de BLOQUE: ni siquiera sale del if ===");
        int fuera = 1;
        if (fuera == 1) {
            int dentro = 99;
            System.out.println("dentro del if, dentro = " + dentro);
        }
        System.out.println("fuera del if, 'dentro' ya no existe: no compilaria.");

        System.out.println();
        System.out.println("=== 3 - Ambito de INSTANCIA compartido: el bug de verdad ===");
        Ambitos controlador = new Ambitos();   // UN solo objeto, como en Spring

        Runnable peticion = () -> {
            for (int k = 0; k < PETICIONES; k++) {
                // leer, sumar y escribir son TRES pasos. Otro hilo puede colarse en medio.
                int leido = controlador.contadorDelControlador;
                controlador.contadorDelControlador = leido + 1;
                peticionesTotales = peticionesTotales + 1;
            }
        };

        Thread[] usuarios = new Thread[USUARIOS];
        for (int u = 0; u < usuarios.length; u++) {
            usuarios[u] = new Thread(peticion);
        }
        for (Thread t : usuarios) t.start();
        for (Thread t : usuarios) t.join();

        System.out.println(USUARIOS + " usuarios x " + PETICIONES + " peticiones = "
                + (USUARIOS * PETICIONES) + " esperadas");
        System.out.println("contadorDelControlador (instancia) : " + controlador.contadorDelControlador);
        System.out.println("peticionesTotales      (clase)     : " + peticionesTotales);
        int esperado = USUARIOS * PETICIONES;
        int perdidas = esperado - controlador.contadorDelControlador;
        System.out.println("Sumas perdidas                     : " + perdidas);
        if (perdidas == 0) {
            System.out.println("Esta vez ha salido bien. Y ese es el peor de los casos:");
            System.out.println("el fallo existe igual, pero hoy no se ha visto.");
        } else {
            System.out.println("Dos peticiones leyeron el mismo valor y escribieron encima.");
        }
        System.out.println("Vuelve a ejecutarlo: el numero cambia en cada ejecucion.");

        System.out.println();
        System.out.println("=== 4 - Lo mismo con variables locales: siempre correcto ===");
        Contador[] resultados = new Contador[USUARIOS];
        Thread[] limpios = new Thread[USUARIOS];
        for (int u = 0; u < USUARIOS; u++) {
            final int mio = u;
            resultados[mio] = new Contador();
            limpios[mio] = new Thread(() -> {
                int local = 0;                 // una copia por hilo: nadie la toca
                for (int k = 0; k < PETICIONES; k++) local++;
                resultados[mio].valor = local;
            });
        }
        for (Thread t : limpios) t.start();
        for (Thread t : limpios) t.join();
        for (int u = 0; u < USUARIOS; u++) {
            System.out.println("usuario " + u + " -> " + resultados[u].valor);
        }
        System.out.println("Los ocho dan " + PETICIONES + ". Siempre.");

        System.out.println();
        System.out.println("=== 5 - La regla que hay que llevarse ===");
        System.out.println("El estado de UNA peticion va en variables LOCALES del metodo,");
        System.out.println("o en el Model. Nunca en un campo del controlador.");
    }

    static void demoLocal() {
        int visitas = 0;     // local: se crea al entrar y se destruye al salir
        visitas++;
        System.out.println("demoLocal() -> visitas = " + visitas);
    }

    static class Contador { int valor; }
}
