# DAO Northwind — Patrón DAO/Service/DTO

Capa de acceso a datos completa siguiendo el patrón **DAO + Service + DTO** sobre la base de datos Northwind.

## Tecnologías
- **Java** + **JDBC**
- **HikariCP** (pool de conexiones)
- **Maven**

## Arquitectura
```
dataaccess/     → Interfaces DAO + Implementaciones (HikariConnectionPool)
dto/            → CreateOrderDto, OrderDetailDto
entities/       → Customer, Employee, Order, Product
services/       → Interfaces Service + Implementaciones
application/    → Tests de integración por entidad
```

## Patrones
- **DAO Pattern** — separación de lógica de acceso a datos
- **Service Layer** — lógica de negocio desacoplada
- **DTO Pattern** — objetos de transferencia para operaciones complejas
- **Connection Pooling** — HikariCP para gestión eficiente de conexiones
