CREATE DATABASE gimnasio;

USE gimnasio;

CREATE TABLE tipoEquipamiento(
    id int auto_increment,
    nombre varchar (20),
    primary key (id),
    unique(nombre)
)

CREATE TABLE tipoUsuario(
    id int auto_increment,
    nombre varchar (20),
    PRIMARY key (id),
    unique(nombre)
)

CREATE TABLE equipamiento(
    id int auto_increment,
    nombre varchar (20),
    descripcion varchar (100),
    cantidad INT,
    tipoEquipamiento_id_fk int,

    primary key(id),
    foreign key(tipoEquipamiento_id_fk) references tipoEquipamiento(id)
)



CREATE TABLE usuario(
    id int auto_increment,
    rut varchar (12),
    nombre varchar(20),
    apellido varchar (20),
    contrasena varchar(100),
    correo varchar(40),
    tipoUsuario_id_fk int,
    UNIQUE(correo),
    primary key (id),
    foreign key (tipoUsuario_id_fk) REFERENCES tipoUsuario(id)
    
)


CREATE TABLE tipoActividad(
    id int auto_increment,
    nombre varchar (30),

    unique(nombre),
    primary key (id)
)

CREATE TABLE actividad(
    id int auto_increment,
    nombre varchar (30),
    descripcion varchar(100),
    cupos int (15),
    fecha datetime,

    equipamiento_id_fk int,
    tipoActividad_id_fk int,
    usuario_id_fk int,

    primary key (id),
    foreign key (equipamiento_id_fk) references equipamiento(id),
    foreign key (tipoActividad_id_fk) references tipoActividad(id),
    foreign key (usuario_id_fk) references usuario(id)
)


CREATE TABLE actividadesRealizadas(
    id int auto_increment,
    nombre varchar(30),
    actividad_id_fk int,
    usuario_id_fk int,

    primary key (id),
    foreign key (usuario_id_fk) references usuario (id),
    foreign key (actividad_id_fk) references actividad(id)
)


CREATE TABLE HistorialEquipamiento(

    ID INT AUTO_INCREMENT,
    EQUIPAMIENTO_ID_FK INT,
    fecha_actualizado DATETIME
    ANTIGUO_STOCK INT,
    NUEVO_STOCK INT,
    PRIMARY KEY(ID),
    FOREIGN KEY (EQUIPAMIENTO_ID_FK) REFERENCES EQUIPAMIENTO(ID)

)


-- TRIGGER 1  

DELIMITER //
CREATE TRIGGER gatito AFTER INSERT ON actividades
FOR EACH ROW
    BEGIN
        INSERT INTO actividadesrealizadas VALUES (null, NEW.nombre, new.id, new.usuario_id_fk);

    END //
DELIMITER ;

-------------

-- TRIGGER 2 

DELIMITER //
CREATE TRIGGER gatitoinventario AFTER UPDATE ON equipamiento
FOR EACH ROW
    BEGIN
        INSERT INTO historialequipamiento VALUES (null, new.id, now(), old.cantidad, new.cantidad  );

    END //
DELIMITER ;

------------



-- Procedimiento 1

DELIMITER //
CREATE PROCEDURE equipamiento_mas_usados( IN  _equipamiento INT )
BEGIN
    IF (_equipamiento = 1) THEN

        SELECT equipamiento.NOMBRE, COUNT(actividad.equipamiento_id_fk) AS 'VECES USADA' FROM ACTIVIDAD
        INNER JOIN equipamiento on equipamiento.ID = ACTIVIDAD.equipamiento_id_fk
        GROUP by actividad.equipamiento_id_fk
        ORDER BY COUNT(actividad.equipamiento_id_fk) ASC;

    ELSEIF (_equipamiento = 2) THEN

        SELECT equipamiento.NOMBRE, COUNT(actividad.equipamiento_id_fk) AS 'VECES USADA' FROM ACTIVIDAD
        INNER JOIN equipamiento on equipamiento.ID = ACTIVIDAD.equipamiento_id_fk
        GROUP by actividad.equipamiento_id_fk
        ORDER BY COUNT(actividad.equipamiento_id_fk) DESC;

    END IF;    
END //
DELIMITER ;
-- FIN PROCEDURE 2
--PROCEDIMIENTO ALMACENADO PARA INSERTAR EN TipoActividad
DELIMITER //
CREATE PROCEDURE addTipoActividad(IN _nombre varchar(20))
BEGIN 
		DECLARE existe_tipo INT; 
        SET existe_tipo = (SELECT COUNT(*) FROM tipoactividad WHERE nombre = _nombre);
        
        IF (existe_tipo = 0) THEN
        	INSERT INTO tipoactividad VALUES (null,_nombre);
        ELSEIF (existe_tipo =1)THEN
        	SELECT 'Warning';
        END IF;
END //
DELIMITER ; 
-- FIN PROCEDIMIENTO
						     
-- PROCEDIMIENTO 3
DELIMITER //
CREATE PROCEDURE calculos_fecha_entre(_FECHA1 DATETIME,_FECHA2 DATETIME)
BEGIN

    SELECT ACTIVIDAD.NOMBRE, ACTIVIDAD.DESCRIPCION, ACTIVIDAD.FECHA, USUARIO.NOMBRE FROM ACTIVIDAD
    INNER JOIN USUARIO on USUARIO.ID = ACTIVIDAD.usuario_id_fk
    WHERE ACTIVIDAD.FECHA BETWEEN _FECHA1 AND _FECHA2;

END //
DELIMITER ;
-- Fin procedimiento 3
