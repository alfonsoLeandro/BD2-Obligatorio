# BD2-Obligatorio
Proyecto obligatorio para Bases de Datos II UCU 2024

**Índice:**
1. [Información general](#información-general)
2. [Tecnologías](#tecnologías)
3. [Proceso de desarrollo](#proceso-de-desarrollo)
4. [Instalación y ejecución](#instalación-y-ejecución)
5. [Integrantes](#integrantes)

## Información general
Este proyecto es un trabajo obligatorio para el primer semestre de 2024,
desarrollado como proyecto final para la materia Bases de Datos II.

[//]: # ( - TODO descripcion general)
[Diagramas (MER, tablas, diagrama de deploy)](https://drive.google.com/file/d/1WjgIBcqFxLbPESPz67LNtpOGHJfB475C/view?usp=sharing)
Ver también: [Bitácora de asunciones](bitacora%20de%20asunciones.md)

## Tecnologías

Backend:
 - **Lenguaje de programación:** Java 21
 - **Framework de desarrollo:** Spring Boot 3.2.5
 - **Gestor de dependencias:** Maven
 - **Base de datos:** MySQL

Frontend:
 - **Lenguaje de programación:** TypeScript
 - **Framework de desarrollo:** Angular 17.3.7
 - **Entorno de ejecución:** Node 10.13.1
 - **Gestor de dependencias:** npm 10.8.0

Contenedorización con **Docker**.

## Proceso de desarrollo
Nos decidimos por utilizar una estructura en git de monorepositorio porque además de los proyectos de backend y
frontend, tenemos archivos que son comunes a ambos proyectos o que decidimos dejar compartido entre ambos proyectos,
como lo son el archivo `docker-compose.yml`, este README, la bitácora de asunciones, la letra del problema,
el volumen de la base de datos, entre otros.

Para organizar nuestro trabajo, usamos la herramienta de gestión de proyectos de GitHub, que nos permite llevar un
registro de las tareas a realizar, asignarlas a los integrantes del equipo, y llevar un control de las mismas.

Para organizar el aporte de código, utilizamos ramas de git, una por cada tarea a realizar, y una rama `main` donde
en todo momento debía haber código estable. Para lo nomenclatura de estas ramas, optamos por establecer una convención
interna desde un primer momento y decidimos usar "backend/{tarea}" para las tareas de backend, "frontend/{tarea}" para
las tareas de frontend, y "{tarea}" para las tareas no específicas de un proyecto u otro.

## Instalación y ejecución
### Requisitos:
 - Docker Desktop (Docker Engine, Docker CLI y Docker Compose vienen incluídos en Docker Engine, en caso de
   optar por instalar las herramientas por separado, refiérase a la documentación oficial correspondiente).

### Pasos:
 - Clonar el repositorio.

[//]: # ( - TODO pasos para instalación y ejecución)

## Integrantes:
 - [Leandro Alfonso](https://github.com/alfonsoLeandro)
 - [Amanda Seara](https://github.com/amandaseara)