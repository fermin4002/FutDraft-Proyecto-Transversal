drop database if exists liga_fantasy;
CREATE  DATABASE  LIGA_FANTASY;
USE LIGA_FANTASY;

create table equipo(
	id_equipo int not null auto_increment,
    nombre varchar(50) not null,
    
    victorias int(2) not null default 0,
    empates int(2) not null default 0,
    derrotas int(2) not null default 0,
    puntos int(3) not null default 0,
    
	portero int(1) not null default 1,
    defensa int(1) not null default 4,
    centrocampista int(1) not null default 4,
    delantero int(1) not null default 2,
    
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


DELIMITER //
create trigger asignacion_jugador
after update on jugador 
for each row
begin 
	if old.id_equipo is null and new.id_equipo is not null then
		if upper(old.posicion) ="DEf" then 
			 update equipo 
			 set defensa=defensa-1 
			 where id_equipo = new.id_equipo ;
		elseif upper(old.posicion) ="POR" then 
			 update equipo 
			 set portero=portero-1 
			 where id_equipo = new.id_equipo ;
		elseif upper(old.posicion) ="MED" then 
			 update equipo 
			 set centrocampista=centrocampista-1 
			 where id_equipo = new.id_equipo ;
		elseif upper(old.posicion) ="DEL" then  
			 update equipo 
			 set delantero=delantero-1 
			 where id_equipo = new.id_equipo ;
		end if;
	end if;    
end;
//



create trigger limpieza_asignacion
after update on jugador 
for each row
begin 
	if old.id_equipo is not null and new.id_equipo is  null then
		if upper(old.posicion) ="DEf" then 
			 update equipo 
			 set defensa=defensa+1 
			 where id_equipo = old.id_equipo ;
		elseif upper(old.posicion) ="POR" then 
			 update equipo 
			 set portero=portero+1 
			 where id_equipo = old.id_equipo ;
		elseif upper(old.posicion) ="MED" then 
			 update equipo 
			 set centrocampista=centrocampista+1 
			 where id_equipo = old.id_equipo ;
		elseif upper(old.posicion) ="DEL" then  
			 update equipo 
			 set delantero=delantero+1 
			 where id_equipo = old.id_equipo ;
		end if;
	end if;    
end;
//



create trigger reasignacion
after update on jugador 
for each row
begin 
	if old.id_equipo is not null and new.id_equipo is  not null then
		if upper(old.posicion) ="DEf" then 
			 update equipo 
			 set defensa=defensa-1 
			 where id_equipo = new.id_equipo ;
             
             update equipo 
			 set defensa=defensa+1 
			 where id_equipo = old.id_equipo ;
		elseif upper(old.posicion) ="POR" then 
			 update equipo 
			 set portero=portero-1 
			 where id_equipo = new.id_equipo ;
             
			 update equipo 
			 set portero=portero+1 
			 where id_equipo = old.id_equipo ;
		elseif upper(old.posicion) ="MED" then 
			 update equipo 
			 set centrocampista=centrocampista-1 
			 where id_equipo = new.id_equipo;
             
			 update equipo 
			 set centrocampista=centrocampista+1 
			 where id_equipo = old.id_equipo ;
		elseif upper(old.posicion) ="DEL" then
			 update equipo 
			 set delantero=delantero-1 
			 where id_equipo = new.id_equipo ;
             
			 update equipo 
			 set delantero=delantero+1 
			 where id_equipo = old.id_equipo ;
		end if;
	end if;    
end;
//
DELIMITER ;

