# Tetris — Java Swing

Implementación completa del juego **Tetris** con interfaz gráfica en Java Swing.

## Tecnologías
- **Java Swing** (GUI)
- Programación orientada a objetos con **herencia**

## Estructura
```
tetris/
├── GameBoard.java      → Lógica del tablero
├── GamePanel.java      → Panel de renderizado
├── Settings.java       → Configuración del juego
├── Tetris.java         → Punto de entrada
└── tiles/
    ├── Tile.java           → Clase base abstracta
    ├── TilePosition.java   → Posición de las piezas
    └── tiles/
        ├── ITile, JTile, LTile, OTile, STile, TTile, ZTile
        └── (cada pieza hereda de Tile)
```

## Conceptos
- **Herencia** — cada tipo de pieza extiende la clase base `Tile`
- **Polimorfismo** — rendering y rotación polimórficos
- **Game loop** — bucle de juego con temporización
