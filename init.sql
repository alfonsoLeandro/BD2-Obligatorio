CREATE TABLE IF NOT EXIST admin(
    id INT PRIMARY KEY,
    ci VARCHAR(8) NOT NULL,
    nombre VARCHAR(20) NOT NULL,
    apellido VARCHAR(20) NOT NULL,
    email VARCHAR(30) NOT NULL,
    contrasena VARCHAR() NOT NULL,
    telefono VARCHAR(11) NOT NULL
);

CREATE TABLE IF NOT EXIST alumno(
    id INT PRIMARY KEY,
    ci VARCHAR(8) NOT NULL,
    nombre VARCHAR(20) NOT NULL,
    apellido VARCHAR(20) NOT NULL,
    email VARCHAR(30) NOT NULL,
    contrasena VARCHAR() NOT NULL,
    telefono VARCHAR(11) NOT NULL,
    puntaje INT NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXIST carrera(
    id INT PRIMARY KEY,
    nombre_carrera VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXIST equipo(
    id INT PRIMARY KEY,
    nombre_equipo VARCHAR(50) NOT NULL,
    ranking INT
);

CREATE TABLE IF NOT EXIST partido(
    id INT PRIMARY KEY,
    fecha DATE NOT NULL
);

CREATE TABLE IF NOT EXIST etapa(
    id INT PRIMARY KEY,
    nombre_etapa VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXIST juega(
    id INT PRIMARY KEY,
    goles INT NOT NULL
);

CREATE TABLE IF NOT EXIST elige(
    id INT PRIMARY KEY
);

CREATE TABLE IF NOT EXIST predice(
    id INT PRIMARY KEY,
    goles INT NOT NULL
);

ALTER TABLE alumno ADD FOREIGN KEY id_carrera REFERENCES carrera(id);

ALTER TABLE partido ADD FOREIGN KEY id_etapa REFERENCES etapa(id);

ALTER TABLE juega ADD FOREIGN KEY id_equipo REFERENCES equipo(id);
ALTER TABLE juega ADD FOREIGN KEY id_partido REFERENCES partido(id);

ALTER TABLE elige ADD FOREIGN KEY id_alumno REFERENCES alumno(id);
ALTER TABLE elige ADD FOREIGN KEY campeon REFERENCES equipo(id);
ALTER TABLE elige ADD FOREIGN KEY subcampeon REFERENCES equipo(id);

ALTER TABLE predice ADD FOREIGN KEY idPartido REFERENCES patido(id);
ALTER TABLE predice ADD FOREIGN KEY idEquipo REFERENCES equipo(id);
ALTER TABLE predice ADD FOREIGN KEY idAlumno REFERENCES alumno(id);