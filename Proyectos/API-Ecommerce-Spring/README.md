# API E-commerce — Spring Boot

API REST para un sistema de comercio electrónico.

## Tecnologías
- **Spring Boot** + Spring Data JPA
- **H2 Database** (en memoria)
- **Maven**

## Funcionalidades
- Gestión de **productos** y **categorías**
- **Carrito de compra** (añadir, listar, eliminar ítems)
- **Pedidos** y **envíos**
- **Wishlist** (lista de deseos)
- **Clientes** y autenticación básica

## Estructura
```
controllers/     → CartController, OrderController, ProductController, WishlistController
dto/             → AddItemDto, CartItemDto, ProductDto, ShipmentDto, WishlistDto
entities/        → CartItems, Category, Customer, Order, Product, Shipment, Wishlist
repositories/    → Interfaces JPA Repository
services/        → Lógica de negocio (interfaz + implementación)
```

## Ejecución
```bash
mvn spring-boot:run
```
