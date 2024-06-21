SET @@time_zone = '-03:00';
SET NAMES 'utf8';

CREATE TABLE IF NOT EXISTS usuarios
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    ci         VARCHAR(8)  NOT NULL,
    nombre     VARCHAR(20) NOT NULL,
    apellido   VARCHAR(20) NOT NULL,
    email      VARCHAR(50) NOT NULL,
    contrasena VARCHAR(60) NOT NULL,
    telefono   VARCHAR(11) NOT NULL,
    role       SMALLINT    NOT NULL
);

CREATE TABLE IF NOT EXISTS alumnos
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT NOT NULL,
    puntaje    INT NOT NULL DEFAULT 0,
    id_carrera INT NOT NULL
);

CREATE TABLE IF NOT EXISTS carreras
(
    id     INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS equipos
(
    id      INT PRIMARY KEY AUTO_INCREMENT,
    nombre  VARCHAR(50) NOT NULL,
    ranking INT
);

CREATE TABLE IF NOT EXISTS partidos
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    fecha    TIMESTAMP NOT NULL,
    id_etapa INT  NOT NULL
);

CREATE TABLE IF NOT EXISTS etapas
(
    id     INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS juegos
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    goles      INT,
    id_equipo  INT NOT NULL,
    id_partido INT NOT NULL
);

CREATE TABLE IF NOT EXISTS elecciones
(
    id            INT PRIMARY KEY AUTO_INCREMENT,
    id_alumno     INT NOT NULL,
    id_campeon    INT NOT NULL,
    id_subcampeon INT NOT NULL
);

CREATE TABLE IF NOT EXISTS predicciones
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    goles      INT NOT NULL,
    id_partido INT NOT NULL,
    id_equipo  INT NOT NULL,
    id_alumno  INT NOT NULL
);

ALTER TABLE alumnos
    ADD FOREIGN KEY (id_usuario) REFERENCES usuarios(id);
ALTER TABLE alumnos
    ADD FOREIGN KEY (id_carrera) REFERENCES carreras(id);

ALTER TABLE partidos
    ADD FOREIGN KEY (id_etapa) REFERENCES etapas(id);

ALTER TABLE juegos
    ADD FOREIGN KEY (id_equipo) REFERENCES equipos(id);
ALTER TABLE juegos
    ADD FOREIGN KEY (id_partido) REFERENCES partidos(id);

ALTER TABLE elecciones
    ADD FOREIGN KEY (id_alumno) REFERENCES alumnos(id);
ALTER TABLE elecciones
    ADD FOREIGN KEY (id_campeon) REFERENCES equipos(id);
ALTER TABLE elecciones
    ADD FOREIGN KEY (id_subcampeon) REFERENCES equipos(id);

ALTER TABLE predicciones
    ADD FOREIGN KEY (id_partido) REFERENCES partidos(id);
ALTER TABLE predicciones
    ADD FOREIGN KEY (id_equipo) REFERENCES equipos(id);
ALTER TABLE predicciones
    ADD FOREIGN KEY (id_alumno) REFERENCES alumnos(id);

CREATE UNIQUE INDEX juegos_id_equipo_id_partido_uindex
    on juegos (id_equipo, id_partido);

ALTER TABLE predicciones
    ADD CONSTRAINT predicciones_unique
        unique (id_partido, id_equipo, id_alumno);

-- Datos iniciales

-- Usuario ADMIN 'admin@pencaucu' - 'password1234'
INSERT INTO usuarios(ci, nombre, apellido, email, contrasena, telefono, role)
    VALUE ('99999999', 'ADMIN', '', 'admin@pencaucu', '$2a$12$2vbE.hh8zdRwtChMWX5lEe49osVEwh4Zbb8WVLhb1SIYvpd.lhQhu', '99999999', 1);

INSERT INTO carreras(nombre)
VALUES ('Abogacía'),
       ('Acompañamiento Terapéutico'),
       ('Agronomía'),
       ('Analista en Informática'),
       ('Arquitectura'),
       ('Artes Escénicas'),
       ('Artes Visuales'),
       ('Business Analytics'),
       ('Ciencia Política'),
       ('Cine'),
       ('Comunicación'),
       ('Comunicación y Marketing'),
       ('Contador Público'),
       ('Datos y Negocios'),
       ('Desarrollador de Software'),
       ('Dirección de Empresas'),
       ('Economía'),
       ('Educación Inicial'),
       ('Finanzas'),
       ('Fisioterapia'),
       ('Fonoaudiología'),
       ('Gestión Humana'),
       ('Ingeniería Ambiental'),
       ('Ingeniería en Alimentos'),
       ('Ingeniería en Electrónica'),
       ('Ingeniería en Informática'),
       ('Ingeniería Industrial'),
       ('Inteligencia Artificial y Ciencia de Datos'),
       ('Licenciatura en Enfermería'),
       ('Licenciatura en Enfermería (Profesionalización)'),
       ('Licenciatura en Informática'),
       ('Medicina'),
       ('Negocios Internacionales'),
       ('Negocios y Economía'),
       ('Notariado'),
       ('Nutrición'),
       ('Odontología'),
       ('Psicología'),
       ('Psicomotricidad'),
       ('Psicopedagogía'),
       ('Recreación Educativa'),
       ('Sociología'),
       ('Trabajo Social');

INSERT INTO equipos(id, nombre)
VALUES (1, 'Argentina'),
       (2, 'Chile'),
       (3, 'Perú'),
       (4, 'Canadá'),
       (5, 'México'),
       (6, 'Ecuador'),
       (7, 'Venezuela'),
       (8, 'Jamaica'),
       (9, 'Estados Unidos'),
       (10, 'Uruguay'),
       (11, 'Bolivia'),
       (12, 'Panamá'),
       (13, 'Brasil'),
       (14, 'Colombia'),
       (15, 'Paraguay'),
       (16, 'Costa Rica');

INSERT INTO etapas(id, nombre)
VALUES (1, 'Grupos'),
       (2, 'Cuartos'),
       (3, 'Semifinales'),
       (4, 'Tercer puesto'),
       (5, 'Final');

INSERT INTO partidos(id, fecha, id_etapa)
VALUES (1, '2024-06-20 20:00:00', 1),
       (2, '2024-06-21 19:00:00', 1),
       (3, '2024-06-25 21:00:00', 1),
       (4, '2024-06-25 17:00:00', 1),
       (5, '2024-06-29 20:00:00', 1),
       (6, '2024-06-29 20:00:00', 1),
       (7, '2024-06-22 20:00:00', 1),
       (8, '2024-06-22 15:00:00', 1),
       (9, '2024-06-26 18:00:00', 1),
       (10, '2024-06-26 15:00:00', 1),
       (11, '2024-06-30 17:00:00', 1),
       (12, '2024-06-30 19:00:00', 1),
       (13, '2024-06-23 17:00:00', 1),
       (14, '2024-06-23 21:00:00', 1),
       (15, '2024-06-27 18:00:00', 1),
       (16, '2024-06-27 21:00:00', 1),
       (17, '2024-07-01 20:00:00', 1),
       (18, '2024-07-01 21:00:00', 1),
       (19, '2024-06-24 18:00:00', 1),
       (20, '2024-06-24 17:00:00', 1),
       (21, '2024-06-28 18:00:00', 1),
       (22, '2024-06-28 15:00:00', 1),
       (23, '2024-07-02 18:00:00', 1),
       (24, '2024-07-02 20:00:00', 1),

       (25, '2024-07-04 20:00:00', 2),
       (26, '2024-07-05 20:00:00', 2),
       (27, '2024-07-06 18:00:00', 2),
       (28, '2024-07-06 15:00:00', 2),

       (29, '2024-07-09 20:00:00', 3),
       (30, '2024-07-10 20:00:00', 3),

       (31, '2024-07-13 20:00:00', 4),

       (32, '2024-07-14 20:00:00', 5);

INSERT INTO juegos(id_equipo, id_partido)
VALUES (1, 1),
       (4, 1),
       (3, 2),
       (2, 2),
       (5, 3),
       (8, 3),
       (6, 4),
       (7, 4),
       (9, 5),
       (11, 5),
       (10, 6),
       (12, 6),
       (13, 7),
       (16, 7),
       (14, 8),
       (15, 8),
       (2, 9),
       (1, 9),
       (3, 10),
       (4, 10),
       (7, 11),
       (5, 11),
       (6, 12),
       (8, 12),
       (12, 13),
       (9, 13),
       (10, 14),
       (11, 14),
       (15, 15),
       (13, 15),
       (14, 16),
       (16, 16),
       (1, 17),
       (3, 17),
       (4, 18),
       (2, 18),
       (5, 19),
       (6, 19),
       (8, 20),
       (7, 20),
       (9, 21),
       (10, 21),
       (11, 22),
       (12, 22),
       (13, 23),
       (14, 23),
       (16, 24),
       (15, 24);
