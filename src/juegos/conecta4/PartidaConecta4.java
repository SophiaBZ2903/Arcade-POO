package juegos.conecta4;

import core.Jugador;
import core.Jugable;
import java.util.InputMismatchException;
import java.util.Scanner;
import core.RegistrosEstadisticas;

public class PartidaConecta4 implements Jugable {
    private TableroConecta4 tablero;
    private RegistrosEstadisticas estadisticas;

    public PartidaConecta4(RegistrosEstadisticas estadisticas) {
        this.estadisticas = estadisticas;
    }

    @Override
    public void start() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("\n[CONECTA 4] Ingresa el nombre del Jugador 1 (X): ");
        Jugador jugador1 = estadisticas.registrarOObtenerJugador(scanner.nextLine());
        System.out.print("[CONECTA 4] Ingresa el nombre del Jugador 2 (O): ");
        Jugador jugador2 = estadisticas.registrarOObtenerJugador(scanner.nextLine());
        
        this.tablero = new TableroConecta4();
        boolean turnoJugador1 = true;
        boolean juegoTerminado = false;

        while (!juegoTerminado) {
            tablero.imprimirTablero();
            
            Jugador jugadorActual = turnoJugador1 ? jugador1 : jugador2;
            char fichaActual = turnoJugador1 ? 'X' : 'O';
            
            boolean movimientoValido = false;
            
            while (!movimientoValido) {
                System.out.print("Turno de " + jugadorActual.getNombre() + " (" + fichaActual + "). Elige una columna (1-7): ");
                
                try {
                    int columna = scanner.nextInt();
                    movimientoValido = tablero.soltarFicha(columna, fichaActual);
                    
                    if (!movimientoValido) {
                        System.out.println("Movimiento inválido. La columna está llena o el número es incorrecto.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Error: Por favor, ingresa solo números del 1 al 7.");
                    scanner.next(); // Limpia la basura de la consola
                }
            }
            
            if (tablero.verificarGanador()) {
                tablero.imprimirTablero();
                System.out.println("¡FELICIDADES! " + jugadorActual.getNombre() + " ha ganado en Conecta 4.");
                jugadorActual.sumarVictoriaConecta4(); // Se actualiza la estadística específica
                juegoTerminado = true;
            } 
            else if (tablero.estaLleno()) {
                tablero.imprimirTablero();
                System.out.println("¡EMPATE! El tablero está completamente lleno.");
                juegoTerminado = true;
            } 
            else {
                turnoJugador1 = !turnoJugador1;
            }
        }
    }
    @Override
    public void mostrarTop3() {
        estadisticas.mostrarTop3Conecta4();
    }
}