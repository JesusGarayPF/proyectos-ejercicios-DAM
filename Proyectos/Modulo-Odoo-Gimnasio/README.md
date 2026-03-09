# Módulo Odoo — Gimnasio (jgf_gym)

Módulo completo para **Odoo** que gestiona un gimnasio.

## Tecnologías
- **Python** + **Odoo ORM**
- **XML** (vistas)
- **Docker** (despliegue)

## Estructura
```
jgf_gym/
├── __init__.py
├── __manifest__.py       → Metadatos del módulo
├── controllers/          → Controladores web
├── data/                 → Datos iniciales
├── demo/                 → Datos de demostración
├── models/               → Modelos de datos (ORM)
├── security/             → Reglas de acceso y permisos
├── static/               → Assets estáticos
└── views/                → Vistas XML (formularios, listas)
```

## Despliegue
```bash
docker-compose up
```
