# API REST Tareas Familiares — Spring Boot

API REST para gestionar tareas domésticas asignadas a miembros de la familia.

## Tecnologías
- **Spring Boot** + Spring Data JPA
- **Maven**

## Funcionalidades
- CRUD de **tareas** con categorías y estados
- **Asignación** de tareas a miembros de la familia
- **Controladores REST** para tareas y asignaciones

## Estructura
```
controllers/    → TaskController, TaskAssignmentController
dto/            → NewTaskAssignmentDto
entities/       → Task, FamilyMember, TaskAssignment, TaskCategory, TaskStatus
repositories/   → TaskRepository, TaskAssignmentRepository
services/       → TaskService + TaskServiceImpl
```

## Ejecución
```bash
mvn spring-boot:run
```
