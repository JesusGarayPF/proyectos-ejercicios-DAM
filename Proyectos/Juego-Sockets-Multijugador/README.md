# Juego Multijugador — Sockets TCP

Juego cliente-servidor multijugador implementado con **sockets TCP** y gestión de hilos.

## Tecnologías
- **Java Sockets** (TCP)
- **Multithreading** — un hilo por cliente
- Serialización de estado de juego

## Estructura
```
Servidor.java       → Servidor que acepta conexiones y gestiona la partida
HiloCliente.java    → Hilo dedicado a cada jugador conectado
Cliente.java        → Aplicación cliente del jugador
Cliente2.java       → Variante de cliente
EstadoJuego.java    → Objeto serializable con el estado compartido
```

## Conceptos
- **Comunicación TCP** — protocolo fiable para turnos de juego
- **Concurrencia** — cada cliente se ejecuta en su propio hilo
- **Estado compartido** — sincronización del estado de juego entre clientes
