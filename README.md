# Arcade de Juegos 
Consiste en una consola interactiva que permite jugar múltiples mini-juegos.

## Juegos Incluidos
1. **Gato (Tres en raya)**
2. **Conecta 4**
3. **Dino Runner**

## Características Principales (Requisitos cumplidos)
**Polimorfismo e Interfaces:** Todos los juegos implementan la interfaz `Jugable`, lo que permite un manejo unificado del método `start()` desde el menú principal.
**Registro de Estadísticas:** El sistema maneja una clase `RegistroEstadisticas` que ordena y calcula el Top 3 histórico de jugadores por cada juego utilizando un método `Comparator`.
**Escenario Ficticio:** El sistema precarga jugadores y victorias al inicializarse para demostrar el funcionamiento del Top 3 de manera inmediata.