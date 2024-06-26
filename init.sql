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
           (9, '12345443', 'Pablovich', 'Pablovich', 'pablovich@google.com', '$2a$12$2vbE.hh8zdRwtChMWX5lEe49osVEwh4Zbb8WVLhb1SIYvpd.lhQhu', '5555555', 0),
           (10, '12345444', 'Leandro', 'Alfonso', 'leandro.alfonso@correo.ucu.edu.uy', '$2a$12$2vbE.hh8zdRwtChMWX5lEe49osVEwh4Zbb8WVLhb1SIYvpd.lhQhu', '12323243', 0),
           (11, '12345445', 'Jorge', 'Limones', 'jorge@limones.com', '$2a$12$2vbE.hh8zdRwtChMWX5lEe49osVEwh4Zbb8WVLhb1SIYvpd.lhQhu', '12323244', 0),
           (12, '12345446', 'Marcelo', 'Perera', 'marcelo@marcelo.com', '$2a$12$2vbE.hh8zdRwtChMWX5lEe49osVEwh4Zbb8WVLhb1SIYvpd.lhQhu', '12323245', 0),
           (13, '12345447', 'Juan', 'Pecera', 'juan@google.com', '$2a$12$2vbE.hh8zdRwtChMWX5lEe49osVEwh4Zbb8WVLhb1SIYvpd.lhQhu', '12323246', 0),
           (14, '12345448', 'Pablo2', 'Cijura', 'pablo.c@google.com', '$2a$12$2vbE.hh8zdRwtChMWX5lEe49osVEwh4Zbb8WVLhb1SIYvpd.lhQhu', '12323247', 0),
           (15, '12345449', 'Sebastian', 'pergola', 'elseba24@google.com', '$2a$12$2vbE.hh8zdRwtChMWX5lEe49osVEwh4Zbb8WVLhb1SIYvpd.lhQhu', '12323248', 0),
           (16, '12345450', 'Michael', 'centauro', 'micha@google.com', '$2a$12$2vbE.hh8zdRwtChMWX5lEe49osVEwh4Zbb8WVLhb1SIYvpd.lhQhu', '12323249', 0);

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
VALUES (1, '2024-06-20 21:00:00-03:00', 1), -- argentina - canada
       (2, '2024-06-21 21:00:00-03:00', 1), -- peru - chile
       (3, '2024-06-22 19:00:00-03:00', 1), -- ecuador - venezuela
       (4, '2024-06-22 22:00:00-03:00', 1), -- mexico - jamaica
       (5, '2024-06-23 19:00:00-03:00', 1), -- USA - bolivia
       (6, '2024-06-23 22:00:00-03:00', 1), -- uruguay - panama
       (7, '2024-06-24 19:00:00-03:00', 1), -- colombia - paraguay
       (8, '2024-06-24 22:00:00-03:00', 1), -- brasil - costa rica
       (9, '2024-06-25 22:00:00-03:00', 1), -- argentina - chile
       (10, '2024-06-25 19:00:00-03:00', 1), -- peru - canada
       (11, '2024-06-26 19:00:00-03:00', 1), -- ecuador - jamaica
       (12, '2024-06-26 22:00:00-03:00', 1), -- mexico - venezuela
       (13, '2024-06-27 22:00:00-03:00', 1), -- uruguay - bolivia
       (14, '2024-06-27 19:00:00-03:00', 1), -- usa - panama
       (15, '2024-06-28 19:00:00-03:00', 1), -- colombia - costa rica
       (16, '2024-06-28 22:00:00-03:00', 1), -- brasil - paraguay
       (17, '2024-06-29 21:00:00-03:00', 1), -- argentina - peru
       (18, '2024-07-29 21:00:00-03:00', 1), -- chile - canada
       (19, '2024-06-30 21:00:00-03:00', 1), -- venezuela - jamaica
       (20, '2024-06-30 21:00:00-03:00', 1), -- mexico - ecuador
       (21, '2024-07-01 22:00:00-03:00', 1), -- bolivia - panama
       (22, '2024-07-01 22:00:00-03:00', 1), -- estados unidos - uruguay
       (23, '2024-07-02 22:00:00-03:00', 1), -- paraguay - costa rica
       (24, '2024-07-02 22:00:00-03:00', 1), -- brasil - colombia

       (25, '2024-07-04 22:00:00-03:00', 2),
       (26, '2024-07-05 22:00:00-03:00', 2),
       (27, '2024-07-06 19:00:00-03:00', 2),
       (28, '2024-07-06 22:00:00-03:00', 2),

       (29, '2024-07-09 21:00:00-03:00', 3),
       (30, '2024-07-10 21:00:00-03:00', 3),

       (31, '2024-07-13 21:00:00-03:00', 4),

       (32, '2024-07-14 21:00:00-03:00', 5);

INSERT INTO juegos(id_equipo, id_partido, goles)
VALUES (1, 1, 2), -- arg
       (4, 1, 0), -- canada
       (3, 2, 0), -- peru
       (2, 2, 0), -- chile
       (6, 3, 1), -- ecuador
       (7, 3, 2), -- venezuela
       (5, 4, 1), -- mexico
       (8, 4, 0), -- jamaica
       (9, 5, 2), -- usa
       (11, 5, 0), -- bolivia
       (10, 6, 3), -- uruguay
       (12, 6, 1), -- panama
       (14, 7, 2), -- colombia
       (15, 7, 1), -- paraguay
       (13, 8, 0), -- brasil
       (16, 8, 0), -- costa rica
       (1, 9, 1), -- argentina
       (2, 9, 0), -- chile
       (3, 10, 0), -- peru
       (4, 10, 1); -- canada

INSERT INTO juegos(id_equipo, id_partido)
VALUES (6, 11), -- ecuador
       (8, 11), -- jamaica
       (5, 12), -- mexico
       (7, 12), -- venezuela
       (10, 13), -- uruguay
       (11, 13), -- bolivia
       (9, 14), -- usa
       (12, 14), -- panama
       (14, 15), -- colombia
       (16, 15), -- costa rica
       (13, 16), -- brasil
       (15, 16), -- paraguay
       (1, 17), -- argentina
       (3, 17), -- peru
       (2, 18), -- chile
       (4, 18), -- canada
       (7, 19), -- venezuela
       (8, 19), -- jamaica
       (5, 20), -- mexico
       (6, 20), -- ecuador
       (11, 21), -- bolivia
       (12, 21), -- panama
       (9, 22), -- usa
       (10, 22), -- uruguay
       (15, 23), -- paraguay
       (16, 23), -- costa rica
       (13, 24), -- brasil
       (14, 24); -- colombia

INSERT INTO alumnos(id, id_usuario, id_carrera)
VALUES (1, 1, 1),
       (2, 2, 7),
       (3, 3, 8),
       (4, 4, 9),
       (5, 5, 10),
       (6, 6, 11),
       (7, 7, 12),
       (8, 8, 13),
       (9, 9, 14),
       (10, 10, 15),
       (11, 11, 16),
       (12, 12, 17),
       (13, 13, 18),
       (14, 14, 19),
       (15, 15, 20),
       (16, 16, 21);

INSERT INTO elecciones(id_alumno, id_campeon, id_subcampeon)
VALUES (2, 1, 2),
       (3, 3, 4),
       (4, 5, 6),
       (5, 7, 8),
       (6, 9, 10),
       (7, 11, 12),
       (8, 13, 14),
       (9, 14, 15),
       (10, 16, 1),
       (11, 2, 3),
       (12, 4, 5),
       (13, 6, 7),
       (14, 8, 9),
       (15, 10, 11),
       (16, 12, 13);

INSERT INTO predicciones(goles, id_partido, id_equipo, id_alumno)
VALUES
    -- partido 1
    (0, 1, 1, 2),
    (1, 1, 1, 3),
    (4, 1, 1, 4),
    (5, 1, 1, 5),
    (2, 1, 1, 6),
    (3, 1, 1, 7),
    (1, 1, 1, 8),
    (4, 1, 1, 9),
    (0, 1, 1, 10),
    (1, 1, 1, 11),
    (2, 1, 1, 12),
    (3, 1, 1, 13),
    (4, 1, 1, 14),
    (5, 1, 1, 15),
    (0, 1, 1, 16),
    (0, 1, 4, 2),
    (3, 1, 4, 3),
    (1, 1, 4, 4),
    (2, 1, 4, 5),
    (0, 1, 4, 6),
    (5, 1, 4, 7),
    (1, 1, 4, 8),
    (3, 1, 4, 9),
    (2, 1, 4, 10),
    (3, 1, 4, 11),
    (4, 1, 4, 12),
    (5, 1, 4, 13),
    (0, 1, 4, 14),
    (1, 1, 4, 15),
    (2, 1, 4, 16),
    -- partido 2
    (4, 2, 3, 2),
    (2, 2, 3, 3),
    (3, 2, 3, 4),
    (0, 2, 3, 5),
    (1, 2, 3, 6),
    (3, 2, 3, 7),
    (2, 2, 3, 8),
    (4, 2, 3, 9),
    (1, 2, 3, 10),
    (2, 2, 3, 11),
    (3, 2, 3, 12),
    (4, 2, 3, 13),
    (5, 2, 3, 14),
    (0, 2, 3, 15),
    (1, 2, 3, 16),
    (5, 2, 2, 2),
    (1, 2, 2, 3),
    (3, 2, 2, 4),
    (2, 2, 2, 5),
    (4, 2, 2, 6),
    (0, 2, 2, 7),
    (3, 2, 2, 8),
    (1, 2, 2, 9),
    (1, 2, 2, 10),
    (2, 2, 2, 11),
    (3, 2, 2, 12),
    (4, 2, 2, 13),
    (5, 2, 2, 14),
    (0, 2, 2, 15),
    (1, 2, 2, 16),
    -- partido 3
    (1, 3, 6, 2),
    (4, 3, 6, 3),
    (3, 3, 6, 4),
    (0, 3, 6, 5),
    (5, 3, 6, 6),
    (1, 3, 6, 7),
    (2, 3, 6, 8),
    (3, 3, 6, 9),
    (3, 3, 6, 10),
    (4, 3, 6, 11),
    (5, 3, 6, 12),
    (0, 3, 6, 13),
    (1, 3, 6, 14),
    (2, 3, 6, 15),
    (3, 3, 6, 16),
    (0, 3, 7, 2),
    (2, 3, 7, 3),
    (1, 3, 7, 4),
    (5, 3, 7, 5),
    (3, 3, 7, 6),
    (4, 3, 7, 7),
    (1, 3, 7, 8),
    (2, 3, 7, 9),
    (2, 3, 7, 10),
    (3, 3, 7, 11),
    (4, 3, 7, 12),
    (5, 3, 7, 13),
    (0, 3, 7, 14),
    (1, 3, 7, 15),
    (2, 3, 7, 16),
    -- partido 4
    (1, 4, 5, 2),
    (2, 4, 5, 3),
    (4, 4, 5, 4),
    (0, 4, 5, 5),
    (1, 4, 5, 6),
    (5, 4, 5, 7),
    (3, 4, 5, 8),
    (2, 4, 5, 9),
    (3, 4, 5, 10),
    (4, 4, 5, 11),
    (5, 4, 5, 12),
    (0, 4, 5, 13),
    (1, 4, 5, 14),
    (2, 4, 5, 15),
    (3, 4, 5, 16),
    (4, 4, 8, 2),
    (1, 4, 8, 3),
    (3, 4, 8, 4),
    (2, 4, 8, 5),
    (5, 4, 8, 6),
    (0, 4, 8, 7),
    (1, 4, 8, 8),
    (4, 4, 8, 9),
    (3, 4, 8, 10),
    (4, 4, 8, 11),
    (5, 4, 8, 12),
    (0, 4, 8, 13),
    (1, 4, 8, 14),
    (2, 4, 8, 15),
    (3, 4, 8, 16),
    -- partido 5
    (3, 5, 9, 2),
    (1, 5, 9, 3),
    (4, 5, 9, 4),
    (0, 5, 9, 5),
    (5, 5, 9, 6),
    (2, 5, 9, 7),
    (1, 5, 9, 8),
    (3, 5, 9, 9),
    (1, 5, 9, 10),
    (2, 5, 9, 11),
    (3, 5, 9, 12),
    (4, 5, 9, 13),
    (5, 5, 9, 14),
    (0, 5, 9, 15),
    (1, 5, 9, 16),
    (1, 5, 11, 2),
    (2, 5, 11, 3),
    (0, 5, 11, 4),
    (3, 5, 11, 5),
    (5, 5, 11, 6),
    (1, 5, 11, 7),
    (4, 5, 11, 8),
    (2, 5, 11, 9),
    (0, 5, 11, 10),
    (3, 5, 11, 11),
    (4, 5, 11, 12),
    (5, 5, 11, 13),
    (1, 5, 11, 14),
    (2, 5, 11, 15),
    (3, 5, 11, 16),
    -- partido 6
    (4, 6, 10, 2),
    (2, 6, 10, 3),
    (3, 6, 10, 4),
    (0, 6, 10, 5),
    (5, 6, 10, 6),
    (1, 6, 10, 7),
    (2, 6, 10, 8),
    (4, 6, 10, 9),
    (0, 6, 10, 10),
    (1, 6, 10, 11),
    (2, 6, 10, 12),
    (3, 6, 10, 13),
    (4, 6, 10, 14),
    (5, 6, 10, 15),
    (0, 6, 10, 16),
    (1, 6, 12, 2),
    (5, 6, 12, 3),
    (0, 6, 12, 4),
    (4, 6, 12, 5),
    (2, 6, 12, 6),
    (1, 6, 12, 7),
    (3, 6, 12, 8),
    (5, 6, 12, 9),
    (1, 6, 12, 10),
    (2, 6, 12, 11),
    (3, 6, 12, 12),
    (4, 6, 12, 13),
    (5, 6, 12, 14),
    (0, 6, 12, 15),
    (1, 6, 12, 16),
    -- partido 7
    (1, 7, 14, 2),
    (4, 7, 14, 3),
    (3, 7, 14, 4),
    (0, 7, 14, 5),
    (5, 7, 14, 6),
    (1, 7, 14, 7),
    (2, 7, 14, 8),
    (3, 7, 14, 9),
    (0, 7, 14, 10),
    (1, 7, 14, 11),
    (2, 7, 14, 12),
    (3, 7, 14, 13),
    (4, 7, 14, 14),
    (5, 7, 14, 15),
    (0, 7, 14, 16),
    (0, 7, 15, 2),
    (2, 7, 15, 3),
    (1, 7, 15, 4),
    (5, 7, 15, 5),
    (3, 7, 15, 6),
    (4, 7, 15, 7),
    (1, 7, 15, 8),
    (2, 7, 15, 9),
    (2, 7, 15, 10),
    (3, 7, 15, 11),
    (4, 7, 15, 12),
    (5, 7, 15, 13),
    (0, 7, 15, 14),
    (1, 7, 15, 15),
    (2, 7, 15, 16),
    -- partido 8
    (1, 8, 13, 2),
    (2, 8, 13, 3),
    (4, 8, 13, 4),
    (0, 8, 13, 5),
    (1, 8, 13, 6),
    (5, 8, 13, 7),
    (3, 8, 13, 8),
    (2, 8, 13, 9),
    (2, 8, 13, 10),
    (3, 8, 13, 11),
    (4, 8, 13, 12),
    (5, 8, 13, 13),
    (0, 8, 13, 14),
    (1, 8, 13, 15),
    (2, 8, 13, 16),
    (4, 8, 16, 2),
    (1, 8, 16, 3),
    (3, 8, 16, 4),
    (2, 8, 16, 5),
    (5, 8, 16, 6),
    (0, 8, 16, 7),
    (1, 8, 16, 8),
    (4, 8, 16, 9),
    (4, 8, 16, 10),
    (5, 8, 16, 11),
    (0, 8, 16, 12),
    (1, 8, 16, 13),
    (2, 8, 16, 14),
    (3, 8, 16, 15),
    (4, 8, 16, 16),
    -- partido 9
    (1, 9, 1, 2),
    (2, 9, 1, 3),
    (4, 9, 1, 4),
    (0, 9, 1, 5),
    (1, 9, 1, 6),
    (5, 9, 1, 7),
    (3, 9, 1, 8),
    (2, 9, 1, 9),
    (2, 9, 1, 10),
    (3, 9, 1, 11),
    (4, 9, 1, 12),
    (5, 9, 1, 13),
    (0, 9, 1, 14),
    (1, 9, 1, 15),
    (2, 9, 1, 16),
    (4, 9, 2, 2),
    (1, 9, 2, 3),
    (3, 9, 2, 4),
    (2, 9, 2, 5),
    (5, 9, 2, 6),
    (0, 9, 2, 7),
    (1, 9, 2, 8),
    (4, 9, 2, 9),
    (3, 9, 2, 10),
    (4, 9, 2, 11),
    (5, 9, 2, 12),
    (0, 9, 2, 13),
    (1, 9, 2, 14),
    (2, 9, 2, 15),
    (3, 9, 2, 16),
    -- partido 10
    (1, 10, 3, 2),
    (2, 10, 3, 3),
    (4, 10, 3, 4),
    (0, 10, 3, 5),
    (1, 10, 3, 6),
    (5, 10, 3, 7),
    (3, 10, 3, 8),
    (2, 10, 3, 9),
    (2, 10, 3, 10),
    (3, 10, 3, 11),
    (4, 10, 3, 12),
    (5, 10, 3, 13),
    (0, 10, 3, 14),
    (1, 10, 3, 15),
    (2, 10, 3, 16),
    (4, 10, 4, 2),
    (1, 10, 4, 3),
    (3, 10, 4, 4),
    (2, 10, 4, 5),
    (5, 10, 4, 6),
    (0, 10, 4, 7),
    (1, 10, 4, 8),
    (4, 10, 4, 9),
    (3, 10, 4, 10),
    (4, 10, 4, 11),
    (5, 10, 4, 12),
    (0, 10, 4, 13),
    (1, 10, 4, 14),
    (2, 10, 4, 15),
    (3, 10, 4, 16),
    -- partido 11
    (1, 11, 6, 2),
    (2, 11, 6, 3),
    (4, 11, 6, 4),
    (0, 11, 6, 5),
    (1, 11, 6, 6),
    (5, 11, 6, 7),
    (3, 11, 6, 8),
    (2, 11, 6, 9),
    (2, 11, 6, 10),
    (3, 11, 6, 11),
    (4, 11, 6, 12),
    (5, 11, 6, 13),
    (0, 11, 6, 14),
    (1, 11, 6, 15),
    (2, 11, 6, 16),
    (4, 11, 8, 2),
    (1, 11, 8, 3),
    (3, 11, 8, 4),
    (2, 11, 8, 5),
    (5, 11, 8, 6),
    (0, 11, 8, 7),
    (1, 11, 8, 8),
    (4, 11, 8, 9),
    (3, 11, 8, 10),
    (4, 11, 8, 11),
    (5, 11, 8, 12),
    (0, 11, 8, 13),
    (1, 11, 8, 14),
    (2, 11, 8, 15),
    (3, 11, 8, 16),
    -- partido 12
    (1, 12, 5, 2),
    (2, 12, 5, 3),
    (4, 12, 5, 4),
    (0, 12, 5, 5),
    (1, 12, 5, 6),
    (5, 12, 5, 7),
    (3, 12, 5, 8),
    (2, 12, 5, 9),
    (2, 12, 5, 10),
    (3, 12, 5, 11),
    (4, 12, 5, 12),
    (5, 12, 5, 13),
    (0, 12, 5, 14),
    (1, 12, 5, 15),
    (2, 12, 5, 16),
    (4, 12, 7, 2),
    (1, 12, 7, 3),
    (3, 12, 7, 4),
    (2, 12, 7, 5),
    (5, 12, 7, 6),
    (0, 12, 7, 7),
    (1, 12, 7, 8),
    (4, 12, 7, 9),
    (3, 12, 7, 10),
    (4, 12, 7, 11),
    (5, 12, 7, 12),
    (0, 12, 7, 13),
    (1, 12, 7, 14),
    (2, 12, 7, 15),
    (3, 12, 7, 16),
    -- partido 13
    (1, 13, 10, 2),
    (2, 13, 10, 3),
    (4, 13, 10, 4),
    (0, 13, 10, 5),
    (1, 13, 10, 6),
    (5, 13, 10, 7),
    (3, 13, 10, 8),
    (2, 13, 10, 9),
    (2, 13, 10, 10),
    (3, 13, 10, 11),
    (4, 13, 10, 12),
    (5, 13, 10, 13),
    (0, 13, 10, 14),
    (1, 13, 10, 15),
    (2, 13, 10, 16),
    (4, 13, 11, 2),
    (1, 13, 11, 3),
    (3, 13, 11, 4),
    (2, 13, 11, 5),
    (5, 13, 11, 6),
    (0, 13, 11, 7),
    (1, 13, 11, 8),
    (4, 13, 11, 9),
    (3, 13, 11, 10),
    (4, 13, 11, 11),
    (5, 13, 11, 12),
    (0, 13, 11, 13),
    (1, 13, 11, 14),
    (2, 13, 11, 15),
    (3, 13, 11, 16),
    -- partido 14
    (1, 14, 9, 2),
    (2, 14, 9, 3),
    (4, 14, 9, 4),
    (0, 14, 9, 5),
    (1, 14, 9, 6),
    (5, 14, 9, 7),
    (3, 14, 9, 8),
    (2, 14, 9, 9),
    (2, 14, 9, 10),
    (3, 14, 9, 11),
    (4, 14, 9, 12),
    (5, 14, 9, 13),
    (0, 14, 9, 14),
    (1, 14, 9, 15),
    (2, 14, 9, 16),
    (4, 14, 12, 2),
    (1, 14, 12, 3),
    (3, 14, 12, 4),
    (2, 14, 12, 5),
    (5, 14, 12, 6),
    (0, 14, 12, 7),
    (1, 14, 12, 8),
    (4, 14, 12, 9),
    (3, 14, 12, 10),
    (4, 14, 12, 11),
    (5, 14, 12, 12),
    (0, 14, 12, 13),
    (1, 14, 12, 14),
    (2, 14, 12, 15),
    (3, 14, 12, 16),
    -- partido 15
    (1, 15, 14, 2),
    (2, 15, 14, 3),
    (4, 15, 14, 4),
    (0, 15, 14, 5),
    (1, 15, 14, 6),
    (5, 15, 14, 7),
    (3, 15, 14, 8),
    (2, 15, 14, 9),
    (2, 15, 14, 10),
    (3, 15, 14, 11),
    (4, 15, 14, 12),
    (5, 15, 14, 13),
    (0, 15, 14, 14),
    (1, 15, 14, 15),
    (2, 15, 14, 16),
    (4, 15, 16, 2),
    (1, 15, 16, 3),
    (3, 15, 16, 4),
    (2, 15, 16, 5),
    (5, 15, 16, 6),
    (0, 15, 16, 7),
    (1, 15, 16, 8),
    (4, 15, 16, 9),
    (4, 15, 16, 10),
    (5, 15, 16, 11),
    (0, 15, 16, 12),
    (1, 15, 16, 13),
    (2, 15, 16, 14),
    (3, 15, 16, 15),
    (4, 15, 16, 16),
    -- partido 16
    (1, 16, 13, 2),
    (2, 16, 13, 3),
    (4, 16, 13, 4),
    (0, 16, 13, 5),
    (1, 16, 13, 6),
    (5, 16, 13, 7),
    (3, 16, 13, 8),
    (2, 16, 13, 9),
    (2, 16, 13, 10),
    (3, 16, 13, 11),
    (4, 16, 13, 12),
    (5, 16, 13, 13),
    (0, 16, 13, 14),
    (1, 16, 13, 15),
    (2, 16, 13, 16),
    (4, 16, 15, 2),
    (1, 16, 15, 3),
    (3, 16, 15, 4),
    (2, 16, 15, 5),
    (5, 16, 15, 6),
    (0, 16, 15, 7),
    (1, 16, 15, 8),
    (4, 16, 15, 9),
    (3, 16, 15, 10),
    (4, 16, 15, 11),
    (5, 16, 15, 12),
    (0, 16, 15, 13),
    (1, 16, 15, 14),
    (2, 16, 15, 15),
    (3, 16, 15, 16),
    -- partido 17
    (1, 17, 1, 2),
    (2, 17, 1, 3),
    (4, 17, 1, 4),
    (0, 17, 1, 5),
    (1, 17, 1, 6),
    (5, 17, 1, 7),
    (3, 17, 1, 8),
    (2, 17, 1, 9),
    (2, 17, 1, 10),
    (3, 17, 1, 11),
    (4, 17, 1, 12),
    (5, 17, 1, 13),
    (0, 17, 1, 14),
    (1, 17, 1, 15),
    (2, 17, 1, 16),
    (4, 17, 3, 2),
    (1, 17, 3, 3),
    (3, 17, 3, 4),
    (2, 17, 3, 5),
    (5, 17, 3, 6),
    (0, 17, 3, 7),
    (1, 17, 3, 8),
    (4, 17, 3, 9),
    (4, 17, 3, 10),
    (5, 17, 3, 11),
    (0, 17, 3, 12),
    (1, 17, 3, 13),
    (2, 17, 3, 14),
    (3, 17, 3, 15),
    (4, 17, 3, 16),
    -- partido 18
    (1, 18, 2, 2),
    (2, 18, 2, 3),
    (4, 18, 2, 4),
    (0, 18, 2, 5),
    (1, 18, 2, 6),
    (5, 18, 2, 7),
    (3, 18, 2, 8),
    (2, 18, 2, 9),
    (2, 18, 2, 10),
    (3, 18, 2, 11),
    (4, 18, 2, 12),
    (5, 18, 2, 13),
    (0, 18, 2, 14),
    (1, 18, 2, 15),
    (2, 18, 2, 16),
    (4, 18, 4, 2),
    (1, 18, 4, 3),
    (3, 18, 4, 4),
    (2, 18, 4, 5),
    (5, 18, 4, 6),
    (0, 18, 4, 7),
    (1, 18, 4, 8),
    (4, 18, 4, 9),
    (3, 18, 4, 10),
    (4, 18, 4, 11),
    (5, 18, 4, 12),
    (0, 18, 4, 13),
    (1, 18, 4, 14),
    (2, 18, 4, 15),
    (3, 18, 4, 16),
    -- partido 19
    (1, 19, 7, 2),
    (2, 19, 7, 3),
    (4, 19, 7, 4),
    (0, 19, 7, 5),
    (1, 19, 7, 6),
    (5, 19, 7, 7),
    (3, 19, 7, 8),
    (2, 19, 7, 9),
    (2, 19, 7, 10),
    (3, 19, 7, 11),
    (4, 19, 7, 12),
    (5, 19, 7, 13),
    (0, 19, 7, 14),
    (1, 19, 7, 15),
    (2, 19, 7, 16),
    (4, 19, 8, 2),
    (1, 19, 8, 3),
    (3, 19, 8, 4),
    (2, 19, 8, 5),
    (5, 19, 8, 6),
    (0, 19, 8, 7),
    (1, 19, 8, 8),
    (4, 19, 8, 9),
    (4, 19, 8, 10),
    (5, 19, 8, 11),
    (0, 19, 8, 12),
    (1, 19, 8, 13),
    (2, 19, 8, 14),
    (3, 19, 8, 15),
    (4, 19, 8, 16),
    -- partido 20
    (1, 20, 9, 2),
    (2, 20, 9, 3),
    (4, 20, 9, 4),
    (0, 20, 9, 5),
    (1, 20, 9, 6),
    (5, 20, 9, 7),
    (3, 20, 9, 8),
    (2, 20, 9, 9),
    (2, 20, 9, 10),
    (3, 20, 9, 11),
    (4, 20, 9, 12),
    (5, 20, 9, 13),
    (0, 20, 9, 14),
    (1, 20, 9, 15),
    (2, 20, 9, 16),
    (4, 20, 10, 2),
    (1, 20, 10, 3),
    (3, 20, 10, 4),
    (2, 20, 10, 5),
    (5, 20, 10, 6),
    (0, 20, 10, 7),
    (1, 20, 10, 8),
    (4, 20, 10, 9),
    (4, 20, 10, 10),
    (5, 20, 10, 11),
    (0, 20, 10, 12),
    (1, 20, 10, 13),
    (2, 20, 10, 14),
    (3, 20, 10, 15),
    (4, 20, 10, 16),
    -- partido 21
    (1, 21, 11, 2),
    (2, 21, 11, 3),
    (4, 21, 11, 4),
    (0, 21, 11, 5),
    (1, 21, 11, 6),
    (5, 21, 11, 7),
    (3, 21, 11, 8),
    (2, 21, 11, 9),
    (2, 21, 11, 10),
    (3, 21, 11, 11),
    (4, 21, 11, 12),
    (5, 21, 11, 13),
    (0, 21, 11, 14),
    (1, 21, 11, 15),
    (2, 21, 11, 16),
    (4, 21, 12, 2),
    (1, 21, 12, 3),
    (3, 21, 12, 4),
    (2, 21, 12, 5),
    (5, 21, 12, 6),
    (0, 21, 12, 7),
    (1, 21, 12, 8),
    (4, 21, 12, 9),
    (4, 21, 12, 10),
    (5, 21, 12, 11),
    (0, 21, 12, 12),
    (1, 21, 12, 13),
    (2, 21, 12, 14),
    (3, 21, 12, 15),
    (4, 21, 12, 16),

    -- partido 22
    (1, 22, 9, 2),
    (2, 22, 9, 3),
    (4, 22, 9, 4),
    (0, 22, 9, 5),
    (1, 22, 9, 6),
    (5, 22, 9, 7),
    (3, 22, 9, 8),
    (2, 22, 9, 9),
    (2, 22, 9, 10),
    (3, 22, 9, 11),
    (4, 22, 9, 12),
    (5, 22, 9, 13),
    (0, 22, 9, 14),
    (1, 22, 9, 15),
    (2, 22, 9, 16),
    (4, 22, 10, 2),
    (1, 22, 10, 3),
    (3, 22, 10, 4),
    (2, 22, 10, 5),
    (5, 22, 10, 6),
    (0, 22, 10, 7),
    (1, 22, 10, 8),
    (4, 22, 10, 9),
    (4, 22, 10, 10),
    (5, 22, 10, 11),
    (0, 22, 10, 12),
    (1, 22, 10, 13),
    (2, 22, 10, 14),
    (3, 22, 10, 15),
    (4, 22, 10, 16),

    -- partido 23
    (1, 23, 15, 2),
    (2, 23, 15, 3),
    (4, 23, 15, 4),
    (0, 23, 15, 5),
    (1, 23, 15, 6),
    (5, 23, 15, 7),
    (3, 23, 15, 8),
    (2, 23, 15, 9),
    (2, 23, 15, 10),
    (3, 23, 15, 11),
    (4, 23, 15, 12),
    (5, 23, 15, 13),
    (0, 23, 15, 14),
    (1, 23, 15, 15),
    (2, 23, 15, 16),
    (4, 23, 16, 2),
    (1, 23, 16, 3),
    (3, 23, 16, 4),
    (2, 23, 16, 5),
    (5, 23, 16, 6),
    (0, 23, 16, 7),
    (1, 23, 16, 8),
    (4, 23, 16, 9),
    (4, 23, 16, 10),
    (5, 23, 16, 11),
    (0, 23, 16, 12),
    (1, 23, 16, 13),
    (2, 23, 16, 14),
    (3, 23, 16, 15),
    (4, 23, 16, 16),

    -- partido 24
    (1, 24, 13, 2),
    (2, 24, 13, 3),
    (4, 24, 13, 4),
    (0, 24, 13, 5),
    (1, 24, 13, 6),
    (5, 24, 13, 7),
    (3, 24, 13, 8),
    (2, 24, 13, 9),
    (2, 24, 13, 10),
    (3, 24, 13, 11),
    (4, 24, 13, 12),
    (5, 24, 13, 13),
    (0, 24, 13, 14),
    (1, 24, 13, 15),
    (2, 24, 13, 16),
    (4, 24, 14, 2),
    (1, 24, 14, 3),
    (3, 24, 14, 4),
    (2, 24, 14, 5),
    (5, 24, 14, 6),
    (0, 24, 14, 7),
    (1, 24, 14, 8),
    (4, 24, 14, 9),
    (3, 24, 14, 10),
    (4, 24, 14, 11),
    (5, 24, 14, 12),
    (0, 24, 14, 13),
    (1, 24, 14, 14),
    (2, 24, 14, 15),
    (3, 24, 14, 16);