package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RegistrosEstadisticas {
    // Lista donde se guardarán todos los jugadores que participen
    private ArrayList<Jugador> listaJugadores;

    public RegistrosEstadisticas() {
        listaJugadores = new ArrayList<>();
    }

    // Método crucial: Busca al jugador. Si no existe, lo crea y lo añade a la lista.
    public Jugador registrarOObtenerJugador(String nombre) {
        // 1. Buscar si el jugador ya existe en la lista
        for (Jugador j : listaJugadores) {
            // Ignoramos mayúsculas/minúsculas (Juan = juan)
            if (j.getNombre().equalsIgnoreCase(nombre)) {
                return j; // Lo encontró, devuelve al jugador existente
            }
        }
        
        // 2. Si el ciclo termina y no lo encontró, creamos uno nuevo
        Jugador nuevoJugador = new Jugador(nombre);
        listaJugadores.add(nuevoJugador);
        return nuevoJugador;
    }

    // --- Métodos para el Top 3 ---

    public void mostrarTop3Gato() {
        System.out.println("\n--- TOP 3: EL GATO ---");
        
        // Si no hay jugadores, avisamos
        if (listaJugadores.isEmpty()) {
            System.out.println("Aún no hay partidas jugadas.");
            return;
        }

        // Ordenamos la lista de mayor a menor según las victorias del Gato
        Collections.sort(listaJugadores, new Comparator<Jugador>() {
            @Override
            public int compare(Jugador j1, Jugador j2) {
                // j2 comparado con j1 para que sea descendente (de mayor a menor)
                return Integer.compare(j2.getVictoriasGato(), j1.getVictoriasGato());
            }
        });

        // Imprimimos solo los primeros 3 (o menos, si hay menos de 3 jugadores)
        int limite = Math.min(3, listaJugadores.size());
        for (int i = 0; i < limite; i++) {
            Jugador j = listaJugadores.get(i);
            // Solo mostramos si tienen al menos 1 victoria
            if (j.getVictoriasGato() > 0) {
                System.out.println((i + 1) + ". " + j.getNombre() + " - " + j.getVictoriasGato() + " victorias");
            }
        }
    }

    public void mostrarTop3Conecta4() {
        System.out.println("\n--- TOP 3: CONECTA 4 ---");
        
        if (listaJugadores.isEmpty()) {
            System.out.println("Aún no hay partidas jugadas.");
            return;
        }

        // Ordenamos la lista de mayor a menor según las victorias de Conecta 4
        Collections.sort(listaJugadores, new Comparator<Jugador>() {
            @Override
            public int compare(Jugador j1, Jugador j2) {
                return Integer.compare(j2.getVictoriasConecta4(), j1.getVictoriasConecta4());
            }
        });

        int limite = Math.min(3, listaJugadores.size());
        for (int i = 0; i < limite; i++) {
            Jugador j = listaJugadores.get(i);
            if (j.getVictoriasConecta4() > 0) {
                System.out.println((i + 1) + ". " + j.getNombre() + " - " + j.getVictoriasConecta4() + " victorias");
            }
        }
    }

    public void mostrarTop3DinoRunner() {
        System.out.println("\n--- TOP 3: DINO RUNNER ---");
        
        if (listaJugadores.isEmpty()) {
            System.out.println("Aún no hay partidas jugadas.");
            return;
        }

        Collections.sort(listaJugadores, new Comparator<Jugador>() {
            @Override
            public int compare(Jugador j1, Jugador j2) {
                return Integer.compare(j2.getVictoriasDinoRunner(), j1.getVictoriasDinoRunner());
            }
        });

        int limite = Math.min(3, listaJugadores.size());
        for (int i = 0; i < limite; i++) {
            Jugador j = listaJugadores.get(i);
            if (j.getVictoriasDinoRunner() > 0) {
                System.out.println((i + 1) + ". " + j.getNombre() + " - " + j.getVictoriasDinoRunner() + " victorias");
            }
        }
    }
}