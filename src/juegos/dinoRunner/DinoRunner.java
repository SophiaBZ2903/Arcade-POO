package juegos.dinoRunner;

import core.Jugable;
import core.Jugador;
import java.util.Random;
import java.util.Scanner;
import core.RegistrosEstadisticas;

public class DinoRunner implements Jugable {

    private static final int ANCHO = 25;
    private final Random random = new Random();
    private Jugador jugador; // Lo conectamos con tu jugador
    private RegistrosEstadisticas estadisticas; // Agregado

    public DinoRunner(RegistrosEstadisticas estadisticas) {
        this.estadisticas = estadisticas;
    }

    // Colores ANSI extraídos de la clase original de tu amiga
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String PURPLE = "\u001B[35m";
    private static final String CYAN = "\u001B[36m";
    private static final String BOLD = "\u001B[1m";

    // Constructor que recibe al jugador
    public DinoRunner(Jugador jugador) {
        this.jugador = jugador;
    }

    // Cumplimos con tu interfaz Jugable
   @Override
    public void start() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\n[DINO RUNNER] Ingresa tu nombre para jugar: ");
        this.jugador = estadisticas.registrarOObtenerJugador(sc.nextLine());
        
        jugar(sc);
    }

    public void jugar(Scanner sc) {
        limpiarPantalla();
        System.out.println(GREEN + BOLD + "=== DINO RUNNER ARCADE ===" + RESET);
        System.out.println("Jugador actual: " + jugador.getNombre());
        System.out.println("Reglas de juego:");
        System.out.println(" - # = Cactus  -> Requiere SALTAR (opción 1)");
        System.out.println(" - V = Ave     -> Requiere AGACHARSE (opción 1)");
        System.out.println(" - * = Bonus   -> Requiere RECOGER (opción 1)");
        System.out.println("\nLlega a 300 puntos para ganar la carrera.");
        System.out.println("\nPresiona ENTER para iniciar...");
        sc.nextLine();

        int puntos = 0;
        boolean vivo = true;
        int baseDelay = 90;

        String[] carril = new String[ANCHO];
        for (int i = 0; i < ANCHO; i++) carril[i] = " ";

        while (vivo && puntos < 300) {
            System.arraycopy(carril, 1, carril, 0, ANCHO - 1);

            if (random.nextInt(100) < 30) {
                int tipo = random.nextInt(3);
                if (tipo == 0) carril[ANCHO - 1] = "#"; // Cactus
                else if (tipo == 1) carril[ANCHO - 1] = "V"; // Ave
                else carril[ANCHO - 1] = "*"; // Bonus
            } else {
                carril[ANCHO - 1] = " ";
            }

            limpiarPantalla();
            dibujarMapa(carril, puntos);

            String proximo = carril[3];

            if (proximo.equals("#")) {
                System.out.println(RED + BOLD + "¡CACTUS CERCA! # -> ¡SALTA AHORA!" + RESET);
                System.out.println("1) SALTAR   2) SEGUIR CORRIENDO");
                int resp = leerEntero(sc, "Acción: ", 1, 2);
                if (resp != 1) vivo = false;
                else puntos += 20;
            } else if (proximo.equals("V")) {
                System.out.println(PURPLE + BOLD + "¡AVE VOLANDO BAJO! V -> ¡AGÁCHATE!" + RESET);
                System.out.println("1) AGÁCHATE   2) SALTAR");
                int resp = leerEntero(sc, "Acción: ", 1, 2);
                if (resp != 1) vivo = false;
                else puntos += 20;
            } else if (proximo.equals("*")) {
                System.out.println(YELLOW + BOLD + "¡ESTRELLA DETECTADA! *" + RESET);
                System.out.println("1) RECOGER   2) IGNORAR");
                int resp = leerEntero(sc, "Acción: ", 1, 2);
                if (resp == 1) puntos += 50;
            } else {
                puntos += 5;
            }

            try {
                Thread.sleep(Math.max(25, baseDelay - (puntos / 8)));
            } catch (InterruptedException ignored) {}
        }

        limpiarPantalla();
        if (vivo) {
            System.out.println(GREEN + BOLD + "🏆 ¡FELICIDADES! " + jugador.getNombre() + " completó el circuito con " + puntos + " puntos." + RESET);
            jugador.sumarVictoriaDinoRunner(); // ¡AQUI SE GUARDA LA VICTORIA EN TUS ESTADÍSTICAS!
        } else {
            System.out.println(RED + BOLD + "💥 ¡HAS CHOCADO! Puntuación final: " + puntos + " pts" + RESET);
        }
        System.out.println("\nPresiona ENTER para volver al menú...");
        sc.nextLine();
    }

    private void dibujarMapa(String[] carril, int puntos) {
        System.out.println(CYAN + "PUNTOS: " + YELLOW + puntos + RESET + " / 300");
        System.out.println("┌" + "──".repeat(ANCHO + 1) + "┐");

        System.out.print("│ ");
        for (int i = 0; i < ANCHO; i++) {
            if (i == 3) {
                System.out.print("\uD83E\uDD96"); 
            } else {
                System.out.print(carril[i].equals(" ") ? "  " : carril[i]);
            }
        }
        System.out.println(" │");
        System.out.println("└" + "──".repeat(ANCHO + 1) + "┘");
    }

    // Métodos utilitarios que le faltaban al archivo de tu amiga
    private void limpiarPantalla() {
        for(int i = 0; i < 30; i++) {
            System.out.println();
        }
    }

    private int leerEntero(Scanner sc, String mensaje, int min, int max) {
        int opcion = -1;
        while (true) {
            System.out.print(mensaje);
            try {
                opcion = Integer.parseInt(sc.nextLine());
                if (opcion >= min && opcion <= max) break;
                else System.out.println(RED + "Opción inválida." + RESET);
            } catch (Exception e) {
                System.out.println(RED + "Ingresa un número." + RESET);
            }
        }
        return opcion;
    }
    @Override
    public void mostrarTop3() {
        estadisticas.mostrarTop3DinoRunner();
    }
}