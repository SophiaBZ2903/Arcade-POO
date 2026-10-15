package juegos.gato;

import core.Jugador;
import core.Jugable;
import java.util.InputMismatchException;
import java.util.Scanner;
import core.RegistrosEstadisticas;

public class PartidaGato implements Jugable {
    private TableroGato tablero;
    private RegistrosEstadisticas estadisticas;

    public PartidaGato(RegistrosEstadisticas estadisticas) {
        this.estadisticas = estadisticas;
    }

    @Override
    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n[GATO] Ingresa el nombre del Jugador 1 (X): ");
        Jugador jugador1 = estadisticas.registrarOObtenerJugador(scanner.nextLine());
        System.out.print("[GATO] Ingresa el nombre del Jugador 2 (O): ");
        Jugador jugador2 = estadisticas.registrarOObtenerJugador(scanner.nextLine());
        
        this.tablero = new TableroGato();
        boolean turnoJugador1 = true; 
        boolean juegoTerminado = false;

        while (!juegoTerminado) {
            tablero.imprimirTablero();
            
            // Determinar de quién es el turno usando un operador ternario (forma corta de un if-else)
            Jugador jugadorActual = turnoJugador1 ? jugador1 : jugador2;
            char fichaActual = turnoJugador1 ? 'X' : 'O';
            
            boolean movimientoValido = false;
            
            // Pedir posición hasta que el usuario introduzca un movimiento válido
            while (!movimientoValido) {
                System.out.print("Turno de " + jugadorActual.getNombre() + " (" + fichaActual + "). Elige una posición (1-9): ");
                
                try {
                    int posicion = scanner.nextInt();
                    movimientoValido = tablero.colocarFicha(posicion, fichaActual);
                    
                    if (!movimientoValido) {
                        System.out.println("Movimiento inválido. La casilla está ocupada o el número está fuera de rango. ¡Intenta de nuevo!");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Error: Por favor, ingresa solo números.");
                    scanner.next(); // Limpia la "basura" que el usuario escribió por error en la consola
                }
            }
            
            // Revisar si el jugador que acaba de tirar, ganó
            if (tablero.verificarGanador()) {
                tablero.imprimirTablero();
                System.out.println("¡FELICIDADES! " + jugadorActual.getNombre() + " ha ganado la partida.");
                jugadorActual.sumarVictoriaGato(); // ¡Aquí actualizamos el puntaje para el Top 3!
                juegoTerminado = true;
            } 
            // Si no ganó, revisamos si ya se llenó el tablero
            else if (tablero.estaLleno()) {
                tablero.imprimirTablero();
                System.out.println("¡El juego termina en EMPATE! Nadie gana puntos.");
                juegoTerminado = true;
            } 
            // Si no hay ganador ni empate, simplemente le toca al otro jugador
            else {
                turnoJugador1 = !turnoJugador1;
            }
        }
        
    }
    @Override
    public void mostrarTop3() {
        estadisticas.mostrarTop3Gato();
    }
}