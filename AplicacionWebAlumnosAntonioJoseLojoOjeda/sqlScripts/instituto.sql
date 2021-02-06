drop database if exists instituto;
create database instituto;
use instituto;
drop table if exists alumnos;
create table alumnos(
    cod_alumno int auto_increment primary key,
    dni varchar(9),
    nombre varchar(60),
    edad int
);
#indice necesario para la matricula
CREATE UNIQUE INDEX dni_index ON alumnos(dni);

create table asignaturas(
    cod_asignatura int primary key, #El código de asignatura no será autoincrement
    nombre_asignatura varchar(60),
    creditos int
);
create table matricula(
    cod_matricula int auto_increment primary key,
    dni_fk varchar(9),
    cod_asignatura_fk int,
    #Foreign keys
    constraint dni_fk foreign key (dni_fk) references alumnos (dni) on delete cascade,
    constraint cod_asignatura_fk foreign key (cod_asignatura_fk) references asignaturas (cod_asignatura) on delete cascade
);

