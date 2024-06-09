
CREATE TABLE IF NOT EXISTS admin(
    id INT PRIMARY KEY AUTO_INCREMENT,
    ci VARCHAR(8) NOT NULL,
    nombre VARCHAR(20) NOT NULL,
    apellido VARCHAR(20) NOT NULL,
    email VARCHAR(50) NOT NULL,
    contrasena VARCHAR(32) NOT NULL,
    telefono VARCHAR(11) NOT NULL
);

CREATE TABLE IF NOT EXISTS alumno(
    id INT PRIMARY KEY AUTO_INCREMENT,
    ci VARCHAR(8) NOT NULL,
    nombre VARCHAR(20) NOT NULL,
    apellido VARCHAR(20) NOT NULL,
    email VARCHAR(50) NOT NULL,
    contrasena VARCHAR(32) NOT NULL, #MD5 (128 bits) hashea un dato en una cadena de 32 caracteres
    telefono VARCHAR(11) NOT NULL,
    puntaje INT NOT NULL DEFAULT 0,
    id_carrera INT NOT NULL
);

CREATE TABLE IF NOT EXISTS carrera(
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS equipo(
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    ranking INT
);

CREATE TABLE IF NOT EXISTS partido(
    id INT PRIMARY KEY AUTO_INCREMENT,
    fecha DATE NOT NULL,
    id_etapa INT NOT NULL
);

CREATE TABLE IF NOT EXISTS etapa(
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS juega(
    id INT PRIMARY KEY AUTO_INCREMENT,
    goles INT NOT NULL,
    id_equipo INT NOT NULL,
    id_partido INT NOT NULL
);

CREATE TABLE IF NOT EXISTS elige(
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_alumno INT NOT NULL,
    id_campeon INT NOT NULL,
    id_subcampeon INT NOT NULL
);

CREATE TABLE IF NOT EXISTS predice(
    id INT PRIMARY KEY AUTO_INCREMENT,
    goles INT NOT NULL,
    id_partido INT NOT NULL,
    id_equipo INT NOT NULL,
    id_alumno INT NOT NULL
);

ALTER TABLE alumno ADD FOREIGN KEY (id_carrera) REFERENCES carrera(id);

ALTER TABLE partido ADD FOREIGN KEY (id_etapa) REFERENCES etapa(id);

ALTER TABLE juega ADD FOREIGN KEY (id_equipo) REFERENCES equipo(id);
ALTER TABLE juega ADD FOREIGN KEY (id_partido) REFERENCES partido(id);

ALTER TABLE elige ADD FOREIGN KEY (id_alumno) REFERENCES alumno(id);
ALTER TABLE elige ADD FOREIGN KEY (id_campeon) REFERENCES equipo(id);
ALTER TABLE elige ADD FOREIGN KEY (id_subcampeon) REFERENCES equipo(id);

ALTER TABLE predice ADD FOREIGN KEY (id_partido) REFERENCES partido(id);
ALTER TABLE predice ADD FOREIGN KEY (id_equipo) REFERENCES equipo(id);
ALTER TABLE predice ADD FOREIGN KEY (id_alumno) REFERENCES alumno(id);