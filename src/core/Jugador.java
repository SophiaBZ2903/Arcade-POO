package core;

public class Jugador {
    // Atributos
    private String nombre;
    private int victoriasGato;
    private int victoriasConecta4;
    private int victoriasDinoRunner;

    // Constructor: Inicializa al jugador con 0 victorias
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.victoriasGato = 0;
        this.victoriasConecta4 = 0;
        this.victoriasDinoRunner = 0;
    }

    // --- Getters (Para leer los datos) ---
    public String getNombre() {
        return nombre;
    }

    public int getVictoriasGato() {
        return victoriasGato;
    }

    public int getVictoriasConecta4() {
        return victoriasConecta4;
    }

    public int getVictoriasDinoRunner() { 
        return victoriasDinoRunner; 
    }

    // --- Métodos de Acción (Para sumar victorias) ---
    public void sumarVictoriaGato() {
        this.victoriasGato++;
    }

    public void sumarVictoriaConecta4() {
        this.victoriasConecta4++;
    }

    public void sumarVictoriaDinoRunner() { 
        this.victoriasDinoRunner++; 
    }
}
