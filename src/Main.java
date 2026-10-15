import core.RegistrosEstadisticas;
import core.Jugable;
import core.Jugador; 
import juegos.gato.PartidaGato;
import juegos.conecta4.PartidaConecta4;
import juegos.dinoRunner.DinoRunner;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        RegistrosEstadisticas estadisticas = new RegistrosEstadisticas();
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        // --- ESCENARIO FICTICIO (Punto 2 de la pizarra) ---
        Jugador b1 = estadisticas.registrarOObtenerJugador("ProfeMario");
        b1.sumarVictoriaGato(); b1.sumarVictoriaGato(); b1.sumarVictoriaGato(); 
        Jugador b2 = estadisticas.registrarOObtenerJugador("EstudianteLuigi");
        b2.sumarVictoriaConecta4(); b2.sumarVictoriaConecta4();
        b2.sumarVictoriaDinoRunner(); b2.sumarVictoriaDinoRunner();
        Jugador b3 = estadisticas.registrarOObtenerJugador("PrincesaPeach");
        b3.sumarVictoriaConecta4(); b3.sumarVictoriaConecta4(); b3.sumarVictoriaConecta4();

        // --- POLIMORFISMO PURO (Punto 3 de la pizarra) ---
        // "Condicionar que solo haya Objetos que tengan start()"
        Jugable[] catalogoJuegos = new Jugable[3];
        catalogoJuegos[0] = new PartidaGato(estadisticas);
        catalogoJuegos[1] = new PartidaConecta4(estadisticas);
        catalogoJuegos[2] = new DinoRunner(estadisticas);

        System.out.println("¡Bienvenido al Arcade de Juegos!");

        while (!salir) {
            System.out.println("\n....MENU PRINCIPAL....");
            System.out.println("1. Jugar Gato (Tres en raya)");
            System.out.println("2. Jugar Conecta 4");
            System.out.println("3. Jugar Dino Runner");
            System.out.println("4. Ver Estadisticas ");
            System.out.println("5. Salir");
            System.out.print("Elige una opcion: ");

            try {
                int opcion = scanner.nextInt();
                scanner.nextLine(); 

                if (opcion >= 1 && opcion <= 3) {
                    // Magia del polimorfismo: Lanza el juego correspondiente sin saber cuál es
                    catalogoJuegos[opcion - 1].start(); 
                } 
                else if (opcion == 4) {
                    // Recorre TODOS los objetos Jugables y les pide su Top 3
                    System.out.println("\nCargando estadisticas globales...");
                    for (Jugable juego : catalogoJuegos) {
                        juego.mostrarTop3();
                    }
                } 
                else if (opcion == 5) {
                    salir = true;
                } 
                else {
                    System.out.println("\nOpción no valida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nError: Por favor, ingresa solo numeros.");
                scanner.nextLine(); 
            }
        }
        scanner.close();
    }
}