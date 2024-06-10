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
    fecha    DATE NOT NULL,
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
    goles      INT NOT NULL,
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