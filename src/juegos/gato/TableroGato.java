package juegos.gato;

public class TableroGato {
    private char[][] casillas;

    // Constructor: Prepara el tablero con números del '1' al '9'
    public TableroGato() {
        casillas = new char[3][3];
        char numeroDinamico = '1';
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                casillas[i][j] = numeroDinamico++;
            }
        }
    }

    // Dibuja el tablero en la consola con formato clásico
    public void imprimirTablero() {
        System.out.println();
        for (int i = 0; i < 3; i++) {
            System.out.println(" " + casillas[i][0] + " | " + casillas[i][1] + " | " + casillas[i][2]);
            if (i < 2) {
                System.out.println("---|---|---");
            }
        }
        System.out.println();
    }

    // Convierte el número (1-9) en coordenadas de la matriz y coloca la ficha
    public boolean colocarFicha(int posicion, char ficha) {
        // Validar que el número esté en el rango correcto
        if (posicion < 1 || posicion > 9) {
            return false;
        }

        // Matemáticas simples para sacar fila y columna a partir del número 1-9
        int fila = (posicion - 1) / 3;
        int columna = (posicion - 1) % 3;

        // Revisar que la casilla no tenga ya una 'X' o una 'O'
        if (casillas[fila][columna] != 'X' && casillas[fila][columna] != 'O') {
            casillas[fila][columna] = ficha;
            return true; // Movimiento válido
        }

        return false; // Movimiento inválido (casilla ocupada)
    }

    // Revisa todas las combinaciones posibles para ganar
    public boolean verificarGanador() {
        // Revisar filas y columnas
        for (int i = 0; i < 3; i++) {
            if (casillas[i][0] == casillas[i][1] && casillas[i][1] == casillas[i][2]) return true;
            if (casillas[0][i] == casillas[1][i] && casillas[1][i] == casillas[2][i]) return true;
        }
        
        // Revisar diagonales
        if (casillas[0][0] == casillas[1][1] && casillas[1][1] == casillas[2][2]) return true;
        if (casillas[0][2] == casillas[1][1] && casillas[1][1] == casillas[2][0]) return true;
        
        return false; // Nadie ha ganado aún
    }

    // Revisa si ya no hay casillas con números (empate)
    public boolean estaLleno() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (casillas[i][j] != 'X' && casillas[i][j] != 'O') {
                    return false; // Todavía hay al menos una casilla libre
                }
            }
        }
        return true; // El tablero está lleno
    }
}