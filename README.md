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

El proyecto consiste en una aplicación web que permite a los alumnos de la Universidad Católica del Uruguay
registrarse para competir en una penca que se realiza a nivel de toda la universidad. La penca consiste en
predecir los resultados de los partidos de fútbol de la Copa América 2024.

Ver también:
 - [Diagramas (MER, tablas, diagrama de deploy)](https://drive.google.com/file/d/1WjgIBcqFxLbPESPz67LNtpOGHJfB475C/view?usp=sharing)
 - [Bitácora de asunciones](bitacora%20de%20asunciones.md)
 - [Prototipo en Figma](https://www.figma.com/design/p7qNy1jVJzBysPr0lnGMmA)

## Tecnologías

Backend:
 - **Lenguaje de programación:** Java 21
 - **Framework de desarrollo:** Spring Boot 3.2.5
 - **Gestor de dependencias:** Maven
 - **Base de datos:** MySQL

Frontend:
 - **Lenguaje de programación:** TypeScript
 - **Framework de desarrollo:** Angular 17.3.7
 - **Entorno de ejecución:** Node 20.15.0
 - **Gestor de dependencias:** npm 10.8.0

Contenedorización con **Docker**.

## Proceso de desarrollo
Nos decidimos por utilizar una estructura en git de mono-repositorio porque además de los proyectos de backend y
frontend, tenemos archivos que son comunes a ambos proyectos o que decidimos dejar compartido entre ambos proyectos,
como lo son el archivo `docker-compose.yml`, este README, la bitácora de asunciones, la letra del problema,
el volumen de la base de datos, entre otros.

Para organizar nuestro trabajo, usamos la herramienta de gestión de proyectos de GitHub, que nos permite llevar un
registro de las tareas a realizar, asignarlas a los integrantes del equipo, y llevar un control de las mismas.

Para organizar el aporte de código, utilizamos ramas de git, una por cada tarea a realizar, y una rama `main` donde
en todo momento debía haber código estable. Para la nomenclatura de estas ramas, optamos por establecer una convención
interna desde un primer momento y decidimos usar `backend/{tarea}` para las tareas de backend, `frontend/{tarea}` para
las tareas de frontend, y `{tarea}` para las tareas no específicas de un proyecto u otro.

Leandro Alfonso tomó un rol más orientado a la gestión y organización del proyecto, mientras que Amanda Seara tomó un
rol más orientado al desarrollo de código.

## Instalación y ejecución
### Requisitos:
 - Docker Desktop (Docker Engine, Docker CLI y Docker Compose vienen incluídos en Docker Engine, en caso de
   optar por instalar las herramientas por separado, refiérase a la documentación oficial correspondiente).

### Pasos:
 - Clonar el repositorio.
 - Generar un archivo "jwt-secret", que debe contener un secreto HS256, necesario para que funcione el backend, espeíficamente la autenticación usando JWT.
 - Generar un arcihov "email-pwd", que debe contener la contraseña para la dirección de correo de outlook especificada en el docker-compose, dentro de las variables de entorno del servicio "backend" como "EMAIL". 
 - Abrir una terminal en la raíz del repositorio.
 - Ejecutar el comando `docker-compose up`.
 - Esperar a que se descarguen las imágenes y se levanten los contenedores.
 - Acceder a la aplicación web en `http://localhost`.

### Uso
Para utilizar la aplicación es necesario registrarse llenando el formulario de registro. Una vez registrado, se puede
iniciar sesión con el usuario y contraseña elegidos. Al iniciar sesión, se accede a la pantalla principal de la penca,
donde se listan todos los partidos de la Copa América 2024 y se puede ingresar el resultado de cada partido 
(siempre que el partido tenga fecha de inicio mayor a una hora a partir de la fecha actual).


## Integrantes:
 - [Leandro Alfonso](https://github.com/alfonsoLeandro)
 - [Amanda Seara](https://github.com/amandaseara)
