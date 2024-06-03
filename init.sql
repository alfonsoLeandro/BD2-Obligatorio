CREATE TABLE IF NOT EXIST admin(
    id_admin INT PRIMARY KEY,
    ci VARCHAR(8),
    nombre VARCHAR(20),
    apellido VARCHAR(20),
    email VARCHAR(30),
    telefono VARCHAR(11)
);

CREATE TABLE IF NOT EXIST alumno(
    id_alumno INT PRIMARY KEY,
    ci VARCHAR(8),
    nombre VARCHAR(20),
    apellido VARCHAR(20),
    email VARCHAR(30),
    telefono VARCHAR(11),
    puntaje INT
);

CREATE TABLE IF NOT EXIST carrera(
    id_carrera INT PRIMARY KEY,
    nombre_carrera VARCHAR(50)
);

CREATE TABLE IF NOT EXIST equipo(
    id_equipo INT PRIMARY KEY,
    nombre_equipo VARCHAR(50),
    ranking VARCHAR(50)
);

CREATE TABLE IF NOT EXIST partido(
    id_partido INT PRIMARY KEY,
    fecha DATE
);

CREATE TABLE IF NOT EXIST etapa(
    id_etapa INT PRIMARY KEY,
    nombre_etapa VARCHAR(20)
);

CREATE TABLE IF NOT EXIST juega(
    id_juega INT PRIMARY KEY,
    goles INT
);

CREATE TABLE IF NOT EXIST elige(
    id_elige INT PRIMARY KEY
);

CREATE TABLE IF NOT EXIST predice(
    id_predice INT PRIMARY KEY,
    goles INT
);

ALTER TABLE alumno ADD FOREIGN KEY id_carrera REFERENCES carrera(id_carrera);

ALTER TABLE partido ADD FOREIGN KEY id_etapa REFERENCES etapa(id_etapa);

ALTER TABLE juega ADD FOREIGN KEY id_equipo REFERENCES equipo(id_equipo);
ALTER TABLE juega ADD FOREIGN KEY id_partido REFERENCES partido(id_partido);

ALTER TABLE elige ADD FOREIGN KEY id_alumno REFERENCES alumno(id_alumno);
ALTER TABLE elige ADD FOREIGN KEY campeon REFERENCES equipo(id_equipo);
ALTER TABLE elige ADD FOREIGN KEY subcampeon REFERENCES equipo(id_equipo);

ALTER TABLE predice ADD FOREIGN KEY idPartido REFERENCES patido(id_partido);
ALTER TABLE predice ADD FOREIGN KEY idEquipo REFERENCES equipo(id_equipo);
ALTER TABLE predice ADD FOREIGN KEY idAlumno REFERENCES alumno(id_alumno);