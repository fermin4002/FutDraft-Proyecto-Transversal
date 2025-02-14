drop database if exists liga_fantasy;
CREATE  DATABASE  liga_fantasy;
USE liga_fantasy;

create table equipo(
	id_equipo int not null auto_increment,
   
    nombre varchar(50) not null,
	victorias int(2) not null default 0,
    empates int(2) not null default 0,
    derrotas int(2) not null default 0,
    puntos int(3) not null default 0,
    goles_favor int not null default 0,
    goles_contra int not null default 0,
	equipo_jugador boolean not null default false,
    constraint pk_equipo primary key(id_equipo)
    
);

create table jugador (
	id_jugador int not null auto_increment,
    
    equipo varchar(100) not null,
	nombre varchar(100) not null,
	posicion varchar(3) not null,
	fuerza_ataque int(2) not null,
	fuerza_tecnica int(2) not null,
    fuerza_defensa int(2) not null,
    fuerza_portero int(2) not null,
	
	id_equipo int  null,
    
	constraint PK_jugadores PRIMARY KEY(id_jugador),
    constraint FK_equipo foreign key(id_equipo)references equipo(id_equipo)
    
);

create table partido(
id_partido int not null auto_increment,

jornada int not null,
id_equipo_local int null,
id_equipo_visitante int null,
goles_local int null,
goles_visitante int null,

constraint PK_partido PRIMARY KEY(id_partido),
constraint FK_equipo_local foreign key(id_equipo_local)references equipo(id_equipo),
constraint FK_equipo_visitante foreign key(id_equipo_visitante)references equipo(id_equipo)

);

select * from jugador where id_equipo=2 order by posicion;