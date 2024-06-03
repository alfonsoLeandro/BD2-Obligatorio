CREATE TABLE IF NOT EXIST Admin(id_admin INT PRIMARY KEY, CI VARCHAR(8), nombre VARCHAR(20), apellido VARCHAR(20), email VARCHAR(30), telefono VARCHAR(11));
CREATE TABLE IF NOT EXIST Alumno(id_alumno INT PRIMARY KEY, CI VARCHAR(8), nombre VARCHAR(20), apellido VARCHAR(20), email VARCHAR(30), telefono VARCHAR(11), puntaje INT);
CREATE TABLE IF NOT EXIST Carrera(id_carrera INT PRIMARY KEY, nombre_carrera VARCHAR(50));
CREATE TABLE IF NOT EXIST Equipo(id_equipo INT PRIMARY KEY, nombre_equipo VARCHAR(50), ranking VARCHAR(50));
CREATE TABLE IF NOT EXIST Partido(id_partido INT PRIMARY KEY, fecha DATE);
CREATE TABLE IF NOT EXIST Etapa(id_etapa INT PRIMARY KEY, nombre_etapa VARCHAR(20));
CREATE TABLE IF NOT EXIST Juega(id_juega INT PRIMARY KEY, goles INT)
CREATE TABLE IF NOT EXIST Elige(id_elige INT PRIMARY KEY);
CREATE TABLE IF NOT EXIST Predice(id_predice INT PRIMARY KEY, goles INT);
ALTER TABLE Alumno ADD FOREIGN KEY id_carrera REFERENCES Carrera(id_carrera);
ALTER TABLE Partido ADD FOREIGN KEY id_etapa REFERENCES Etapa(id_etapa);
ALTER TABLE Juega ADD FOREIGN KEY id_equipo REFERENCES Equipo(id_equipo);
ALTER TABLE Juega ADD FOREIGN KEY id_partido REFERENCES Partido(id_partido);
ALTER TABLE Elige ADD FOREIGN KEY id_alumno REFERENCES Alumno(id_alumno);
ALTER TABLE Elige ADD FOREIGN KEY campeon REFERENCES Equipo(id_equipo);
ALTER TABLE Elige ADD FOREIGN KEY subcampeon REFERENCES Equipo(id_equipo);
ALTER TABLE Predice ADD FOREIGN KEY idPartido REFERENCES Patido(id_partido);
ALTER TABLE Predice ADD FOREIGN KEY idEquipo REFERENCES Equipo(id_equipo);
ALTER TABLE Predice ADD FOREIGN KEY idAlumno REFERENCES Alumno(id_alumno);