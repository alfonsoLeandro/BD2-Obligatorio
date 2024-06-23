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
    nombre VARCHAR(20) NOT NULL,
    anunciado BOOLEAN DEFAULT FALSE
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
INSERT INTO usuarios(id, ci, nombre, apellido, email, contrasena, telefono, role)
    VALUES (1, '99999999', 'ADMIN', '', 'admin@pencaucu', '$2a$12$2vbE.hh8zdRwtChMWX5lEe49osVEwh4Zbb8WVLhb1SIYvpd.lhQhu', '99999999', 1),
           (2, '12345436', 'Pedro', 'Picapiedra', 'pedro@crack.com', '$2a$12$2vbE.hh8zdRwtChMWX5lEe49osVEwh4Zbb8WVLhb1SIYvpd.lhQhu', '11111111', 0),
           (3, '12345437', 'Juan', 'Picapiedra', 'juan@crack.com', '$2a$12$2vbE.hh8zdRwtChMWX5lEe49osVEwh4Zbb8WVLhb1SIYvpd.lhQhu', '111122222', 0),
           (4, '12345438', 'Miguel', 'Jhonson', 'miguel@google.com', '$2a$12$2vbE.hh8zdRwtChMWX5lEe49osVEwh4Zbb8WVLhb1SIYvpd.lhQhu', '33333333', 0),
           (5, '12345439', 'Pablo', 'Pablo', 'pablo@google.com', '$2a$12$2vbE.hh8zdRwtChMWX5lEe49osVEwh4Zbb8WVLhb1SIYvpd.lhQhu', '44444444', 0),
           (6, '12345440', 'Pablin', 'Pablin', 'pablin@google.com', '$2a$12$2vbE.hh8zdRwtChMWX5lEe49osVEwh4Zbb8WVLhb1SIYvpd.lhQhu', '5555555', 0),
           (7, '12345441', 'Pablina', 'Pablina', 'pablina@google.com', '$2a$12$2vbE.hh8zdRwtChMWX5lEe49osVEwh4Zbb8WVLhb1SIYvpd.lhQhu', '5555555', 0),
           (8, '12345442', 'Pablardo', 'Pablardo', 'pablardo@google.com', '$2a$12$2vbE.hh8zdRwtChMWX5lEe49osVEwh4Zbb8WVLhb1SIYvpd.lhQhu', '66666', 0),
           (9, '12345443', 'Pablovich', 'Pablovich', 'pablovich@google.com', '$2a$12$2vbE.hh8zdRwtChMWX5lEe49osVEwh4Zbb8WVLhb1SIYvpd.lhQhu', '5555555', 0);

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

INSERT INTO etapas(id, nombre, anunciado)
VALUE (1, 'Grupos', TRUE);

INSERT INTO etapas(id, nombre)
VALUES (2, 'Cuartos'),
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

INSERT INTO juegos(id_equipo, id_partido, goles)
VALUES (1, 1, 2),
       (4, 1, 0),
       (3, 2, 0),
       (2, 2, 0),
       (5, 3, 1),
       (8, 3, 1),
       (6, 4, 1),
       (7, 4, 1),

INSERT INTO juegos(id_equipo, id_partido)
VALUES (9, 5),
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

INSERT INTO alumnos(id, id_usuario, id_carrera)
VALUES (1, 1, 1),
       (2, 2, 7),
       (3, 3, 8),
       (4, 4, 9),
       (5, 5, 10),
       (6, 6, 11),
       (7, 7, 12),
       (8, 8, 13),
       (9, 9, 14);

INSERT INTO elecciones(id_alumno, id_campeon, id_subcampeon)
VALUES (2, 1, 2),
       (3, 3, 4),
       (4, 5, 6),
       (5, 7, 8),
       (6, 9, 10),
       (7, 11, 12),
       (8, 13, 14),
       (9, 14, 15);

INSERT INTO predicciones(goles, id_partido, id_equipo, id_alumno)
VALUES 
    -- partido 1
    (10, 1, 1, 2),
    (10, 1, 4, 2),
    (4, 1, 1, 3),
    (2, 1, 4, 3),
    (3, 1, 1, 4),
    (6, 1, 4, 4),
    (3, 6, 1, 5),
    (6, 3, 4, 5),
    (3, 1, 1, 6),
    (6, 1, 4, 6),
    (10, 1, 1, 7),
    (0, 1, 4, 7),
    (1, 1, 1, 8),
    (3, 1, 4, 8),
    (1, 1, 1, 9),
    (3, 1, 4, 9),
    
    -- Partido 2
    (3, 2, 3, 2),
    (1, 2, 2, 2),
    (2, 2, 3, 3),
    (4, 2, 2, 3),
    (5, 2, 3, 4),
    (3, 2, 2, 4),
    (2, 0, 3, 5),
    (4, 0, 2, 5),
    (1, 2, 3, 6),
    (3, 2, 2, 6),
    (4, 2, 3, 7),
    (2, 2, 2, 7),
    (3, 2, 3, 8),
    (1, 2, 2, 8),
    (2, 2, 3, 9),
    (4, 2, 2, 9),

    -- Partido 3
    (2, 3, 5, 2),
    (3, 3, 8, 2),
    (1, 3, 5, 3),
    (2, 3, 8, 3),
    (3, 3, 5, 4),
    (4, 3, 8, 4),
    (2, 3, 5, 5),
    (1, 3, 8, 5),
    (3, 3, 5, 6),
    (2, 3, 8, 6),
    (1, 3, 5, 7),
    (3, 3, 8, 7),
    (2, 3, 5, 8),
    (1, 3, 8, 8),
    (3, 3, 5, 9),
    (2, 3, 8, 9),

    -- Partido 4
    (4, 4, 6, 2),
    (2, 4, 7, 2),
    (3, 4, 6, 3),
    (1, 4, 7, 3),
    (2, 4, 6, 4),
    (4, 4, 7, 4),
    (1, 4, 6, 5),
    (3, 4, 7, 5),
    (2, 4, 6, 6),
    (4, 4, 7, 6),
    (3, 4, 6, 7),
    (1, 4, 7, 7),
    (4, 4, 6, 8),
    (2, 4, 7, 8),
    (3, 4, 6, 9),
    (1, 4, 7, 9),

    -- Partido 5
    (3, 5, 9, 2),
    (2, 5, 11, 2),
    (1, 5, 9, 3),
    (4, 5, 11, 3),
    (2, 5, 9, 4),
    (3, 5, 11, 4),
    (4, 5, 9, 5),
    (1, 5, 11, 5),
    (2, 5, 9, 6),
    (3, 5, 11, 6),
    (1, 5, 9, 7),
    (2, 5, 11, 7),
    (3, 5, 9, 8),
    (4, 5, 11, 8),
    (2, 5, 9, 9),
    (1, 5, 11, 9),

    -- Partido 6
    (1, 6, 10, 2),
    (3, 6, 12, 2),
    (2, 6, 10, 3),
    (4, 6, 12, 3),
    (1, 6, 10, 4),
    (2, 6, 12, 4),
    (3, 6, 10, 5),
    (4, 6, 12, 5),
    (2, 6, 10, 6),
    (1, 6, 12, 6),
    (3, 6, 10, 7),
    (2, 6, 12, 7),
    (4, 6, 10, 8),
    (1, 6, 12, 8),
    (2, 6, 10, 9),
    (3, 6, 12, 9),

    -- Partido 7
    (4, 7, 13, 2),
    (2, 7, 16, 2),
    (1, 7, 13, 3),
    (3, 7, 16, 3),
    (2, 7, 13, 4),
    (4, 7, 16, 4),
    (3, 7, 13, 5),
    (1, 7, 16, 5),
    (2, 7, 13, 6),
    (3, 7, 16, 6),
    (4, 7, 13, 7),
    (2, 7, 16, 7),
    (1, 7, 13, 8),
    (3, 7, 16, 8),
    (2, 7, 13, 9),
    (4, 7, 16, 9),

    -- Partido 8
    (1, 8, 14, 2),
    (3, 8, 15, 2),
    (4, 8, 14, 3),
    (2, 8, 15, 3),
    (1, 8, 14, 4),
    (3, 8, 15, 4),
    (2, 8, 14, 5),
    (4, 8, 15, 5),
    (1, 8, 14, 6),
    (3, 8, 15, 6),
    (2, 8, 14, 7),
    (4, 8, 15, 7),
    (1, 8, 14, 8),
    (3, 8, 15, 8),
    (2, 8, 14, 9),
    (4, 8, 15, 9),

    -- Partido 9
    (4, 9, 2, 2),
    (2, 9, 1, 2),
    (1, 9, 2, 3),
    (3, 9, 1, 3),
    (2, 9, 2, 4),
    (1, 9, 1, 4),
    (4, 9, 2, 5),
    (2, 9, 1, 5),
    (1, 9, 2, 6),
    (3, 9, 1, 6),
    (2, 9, 2, 7),
    (4, 9, 1, 7),
    (3, 9, 2, 8),
    (1, 9, 1, 8),
    (2, 9, 2, 9),
    (4, 9, 1, 9),

    -- Partido 10
    (1, 10, 3, 2),
    (3, 10, 4, 2),
    (4, 10, 3, 3),
    (2, 10, 4, 3),
    (1, 10, 3, 4),
    (3, 10, 4, 4),
    (2, 10, 3, 5),
    (4, 10, 4, 5),
    (1, 10, 3, 6),
    (3, 10, 4, 6),
    (4, 10, 3, 7),
    (2, 10, 4, 7),
    (1, 10, 3, 8),
    (3, 10, 4, 8),
    (4, 10, 3, 9),
    (2, 10, 4, 9),

    -- Partido 11
    (3, 11, 7, 2),
    (1, 11, 5, 2),
    (2, 11, 7, 3),
    (4, 11, 5, 3),
    (3, 11, 7, 4),
    (2, 11, 5, 4),
    (1, 11, 7, 5),
    (3, 11, 5, 5),
    (2, 11, 7, 6),
    (4, 11, 5, 6),
    (3, 11, 7, 7),
    (1, 11, 5, 7),
    (4, 11, 7, 8),
    (2, 11, 5, 8),
    (1, 11, 7, 9),
    (3, 11, 5, 9),

    -- Partido 12
    (4, 12, 6, 2),
    (2, 12, 8, 2),
    (1, 12, 6, 3),
    (3, 12, 8, 3),
    (2, 12, 6, 4),
    (4, 12, 8, 4),
    (1, 12, 6, 5),
    (2, 12, 8, 5),
    (3, 12, 6, 6),
    (1, 12, 8, 6),
    (4, 12, 6, 7),
    (2, 12, 8, 7),
    (3, 12, 6, 8),
    (1, 12, 8, 8),
    (4, 12, 6, 9),
    (2, 12, 8, 9),

    -- Partido 13
    (3, 13, 12, 2),
    (1, 13, 9, 2),
    (2, 13, 12, 3),
    (4, 13, 9, 3),
    (3, 13, 12, 4),
    (2, 13, 9, 4),
    (1, 13, 12, 5),
    (3, 13, 9, 5),
    (2, 13, 12, 6),
    (4, 13, 9, 6),
    (3, 13, 12, 7),
    (1, 13, 9, 7),
    (4, 13, 12, 8),
    (2, 13, 9, 8),
    (1, 13, 12, 9),
    (3, 13, 9, 9),

    -- Partido 14
    (4, 14, 10, 2),
    (2, 14, 11, 2),
    (1, 14, 10, 3),
    (3, 14, 11, 3),
    (2, 14, 10, 4),
    (4, 14, 11, 4),
    (1, 14, 10, 5),
    (2, 14, 11, 5),
    (3, 14, 10, 6),
    (1, 14, 11, 6),
    (4, 14, 10, 7),
    (2, 14, 11, 7),
    (3, 14, 10, 8),
    (1, 14, 11, 8),
    (4, 14, 10, 9),
    (2, 14, 11, 9),

    -- Partido 15
    (1, 15, 15, 2),
    (3, 15, 13, 2),
    (4, 15, 15, 3),
    (2, 15, 13, 3),
    (1, 15, 15, 4),
    (3, 15, 13, 4),
    (2, 15, 15, 5),
    (4, 15, 13, 5),
    (1, 15, 15, 6),
    (3, 15, 13, 6),
    (2, 15, 15, 7),
    (4, 15, 13, 7),
    (1, 15, 15, 8),
    (3, 15, 13, 8),
    (2, 15, 15, 9),
    (4, 15, 13, 9),

    -- Partido 16
    (4, 16, 14, 2),
    (2, 16, 16, 2),
    (1, 16, 14, 3),
    (3, 16, 16, 3),
    (2, 16, 14, 4),
    (4, 16, 16, 4),
    (1, 16, 14, 5),
    (2, 16, 16, 5),
    (3, 16, 14, 6),
    (1, 16, 16, 6),
    (4, 16, 14, 7),
    (2, 16, 16, 7),
    (3, 16, 14, 8),
    (1, 16, 16, 8),
    (4, 16, 14, 9),
    (2, 16, 16, 9),

    -- Partido 17
    (3, 17, 1, 2),
    (1, 17, 3, 2),
    (2, 17, 1, 3),
    (4, 17, 3, 3),
    (3, 17, 1, 4),
    (2, 17, 3, 4),
    (1, 17, 1, 5),
    (3, 17, 3, 5),
    (2, 17, 1, 6),
    (4, 17, 3, 6),
    (3, 17, 1, 7),
    (1, 17, 3, 7),
    (4, 17, 1, 8),
    (2, 17, 3, 8),
    (1, 17, 1, 9),
    (3, 17, 3, 9),

    -- Partido 18
    (1, 18, 4, 2),
    (3, 18, 2, 2),
    (4, 18, 4, 3),
    (2, 18, 2, 3),
    (1, 18, 4, 4),
    (3, 18, 2, 4),
    (2, 18, 4, 5),
    (4, 18, 2, 5),
    (1, 18, 4, 6),
    (3, 18, 2, 6),
    (4, 18, 4, 7),
    (2, 18, 2, 7),
    (1, 18, 4, 8),
    (3, 18, 2, 8),
    (4, 18, 4, 9),
    (2, 18, 2, 9),

    -- Partido 19
    (2, 19, 5, 2),
    (4, 19, 6, 2),
    (1, 19, 5, 3),
    (3, 19, 6, 3),
    (4, 19, 5, 4),
    (2, 19, 6, 4),
    (1, 19, 5, 5),
    (3, 19, 6, 5),
    (2, 19, 5, 6),
    (4, 19, 6, 6),
    (1, 19, 5, 7),
    (2, 19, 6, 7),
    (3, 19, 5, 8),
    (4, 19, 6, 8),
    (2, 19, 5, 9),
    (1, 19, 6, 9),

    -- Partido 20
    (4, 20, 8, 2),
    (2, 20, 7, 2),
    (1, 20, 8, 3),
    (3, 20, 7, 3),
    (2, 20, 8, 4),
    (4, 20, 7, 4),
    (1, 20, 8, 5),
    (2, 20, 7, 5),
    (3, 20, 8, 6),
    (1, 20, 7, 6),
    (4, 20, 8, 7),
    (2, 20, 7, 7),
    (1, 20, 8, 8),
    (3, 20, 7, 8),
    (2, 20, 8, 9),
    (4, 20, 7, 9),

    -- Partido 21
    (1, 21, 9, 2),
    (3, 21, 10, 2),
    (4, 21, 9, 3),
    (2, 21, 10, 3),
    (1, 21, 9, 4),
    (3, 21, 10, 4),
    (2, 21, 9, 5),
    (4, 21, 10, 5),
    (1, 21, 9, 6),
    (3, 21, 10, 6),
    (4, 21, 9, 7),
    (2, 21, 10, 7),
    (1, 21, 9, 8),
    (3, 21, 10, 8),
    (4, 21, 9, 9),
    (2, 21, 10, 9),

    -- Partido 22
    (2, 22, 11, 2),
    (4, 22, 12, 2),
    (1, 22, 11, 3),
    (3, 22, 12, 3),
    (4, 22, 11, 4),
    (2, 22, 12, 4),
    (1, 22, 11, 5),
    (3, 22, 12, 5),
    (2, 22, 11, 6),
    (4, 22, 12, 6),
    (1, 22, 11, 7),
    (3, 22, 12, 7),
    (2, 22, 11, 8),
    (4, 22, 12, 8),
    (1, 22, 11, 9),
    (3, 22, 12, 9),

    -- Partido 23
    (4, 23, 13, 2),
    (2, 23, 14, 2),
    (1, 23, 13, 3),
    (3, 23, 14, 3),
    (4, 23, 13, 4),
    (2, 23, 14, 4),
    (1, 23, 13, 5),
    (3, 23, 14, 5),
    (4, 23, 13, 6),
    (2, 23, 14, 6),
    (1, 23, 13, 7),
    (3, 23, 14, 7),
    (4, 23, 13, 8),
    (2, 23, 14, 8),
    (1, 23, 13, 9),
    (3, 23, 14, 9),

    -- Partido 24
    (2, 24, 16, 2),
    (4, 24, 15, 2),
    (1, 24, 16, 3),
    (3, 24, 15, 3),
    (2, 24, 16, 4),
    (4, 24, 15, 4),
    (1, 24, 16, 5),
    (3, 24, 15, 5),
    (2, 24, 16, 6),
    (4, 24, 15, 6),
    (1, 24, 16, 7),
    (3, 24, 15, 7),
    (2, 24, 16, 8),
    (4, 24, 15, 8),
    (1, 24, 16, 9),
    (3, 24, 15, 9);