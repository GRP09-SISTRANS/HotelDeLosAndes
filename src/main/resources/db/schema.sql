/**
  * CREATE TABLES SECTION
  */

CREATE TABLE bar (
  servicio_id NUMBER NOT NULL,
  capacidad   NUMBER NOT NULL,
  estilo      VARCHAR2(4000)
);

ALTER TABLE bar
ADD CONSTRAINT bar_pk PRIMARY KEY ( servicio_id );

CREATE TABLE cuenta (
  id          NUMBER NOT NULL,
  reserva_id  NUMBER NOT NULL,
  producto_id NUMBER NOT NULL,
  cantidad    NUMBER NOT NULL
);

ALTER TABLE cuenta
ADD CONSTRAINT cuenta_pk PRIMARY KEY ( id );

CREATE TABLE gimnasio (
  servicio_id NUMBER NOT NULL,
  maquinas    INTEGER NOT NULL
);

ALTER TABLE gimnasio
ADD CONSTRAINT gimnasio_pk PRIMARY KEY ( servicio_id );

CREATE TABLE habitacion (
  id                  NUMBER NOT NULL,
  capacidad           INTEGER NOT NULL,
  tipos_habitacion_id NUMBER NOT NULL
);

ALTER TABLE habitacion
ADD CONSTRAINT habitacion_pk PRIMARY KEY ( id );

CREATE TABLE internet (
  servicio_id NUMBER NOT NULL
);

ALTER TABLE internet
ADD CONSTRAINT internet_pk PRIMARY KEY ( servicio_id );

CREATE TABLE lavanderia (
  servicio_id NUMBER NOT NULL
);

ALTER TABLE lavanderia
ADD CONSTRAINT lavanderia_pk PRIMARY KEY ( servicio_id );

CREATE TABLE piscina (
  servicio_id NUMBER NOT NULL,
  capacidad   INTEGER NOT NULL,
  profundidad FLOAT NOT NULL
);

ALTER TABLE piscina
ADD CONSTRAINT piscina_pk PRIMARY KEY ( servicio_id );

CREATE TABLE plan_consumo (
  id          NUMBER NOT NULL,
  nombre      VARCHAR2(4000) NOT NULL,
  descripcion VARCHAR2(4000) NOT NULL
);

ALTER TABLE plan_consumo
ADD CONSTRAINT plan_consumo_pk PRIMARY KEY ( id );

CREATE TABLE prestamo_utensilios (
  servicio_id NUMBER NOT NULL
);

ALTER TABLE prestamo_utensilios
ADD CONSTRAINT prestamo_utensilios_pk PRIMARY KEY ( servicio_id );

CREATE TABLE producto (
  id          NUMBER NOT NULL,
  servicio_id NUMBER NOT NULL,
  nombre      VARCHAR2(4000) NOT NULL,
  costo       NUMBER NOT NULL
);

ALTER TABLE producto
ADD CONSTRAINT producto_pk PRIMARY KEY ( id );

CREATE TABLE reserva (
  id                   NUMBER NOT NULL,
  check_in             DATE,
  check_out            DATE,
  fecha_inicio_reserva DATE NOT NULL,
  fecha_final_reserva  DATE NOT NULL
);

ALTER TABLE reserva
ADD CONSTRAINT reserva_pk PRIMARY KEY ( id );

CREATE TABLE reserva_cliente (
  reserva_id      NUMBER NOT NULL,
  usuario_id      NUMBER NOT NULL,
  habitacion_id   NUMBER NOT NULL,
  plan_consumo_id NUMBER NOT NULL
);

ALTER TABLE reserva_cliente
ADD CONSTRAINT reserva_cliente_pk PRIMARY KEY ( reserva_id, usuario_id );

CREATE TABLE reserva_servicio (
  id          NUMBER NOT NULL,
  fecha       DATE NOT NULL,
  producto_id NUMBER NOT NULL,
  usuario_id  NUMBER NOT NULL
);

ALTER TABLE reserva_servicio
ADD CONSTRAINT reserva_servicio_pk PRIMARY KEY ( id );

CREATE TABLE salon_conferencia (
  servicio_id NUMBER NOT NULL,
  capacidad   INTEGER
);

ALTER TABLE salon_conferencia
ADD CONSTRAINT salon_conferencia_pk PRIMARY KEY ( servicio_id );

CREATE TABLE servicio (
  id     NUMBER NOT NULL,
  nombre VARCHAR2(4000) NOT NULL,
  tipo   VARCHAR2(4000) NOT NULL
);

ALTER TABLE servicio
ADD CONSTRAINT servicios_pk PRIMARY KEY ( id );

CREATE TABLE spa (
  servicio_id NUMBER NOT NULL
);

ALTER TABLE spa
ADD CONSTRAINT spa_pk PRIMARY KEY ( servicio_id );

CREATE TABLE supermercado (
  servicio_id NUMBER NOT NULL
);

ALTER TABLE supermercado
ADD CONSTRAINT supermercado_pk PRIMARY KEY ( servicio_id );

CREATE TABLE tienda (
  servicio_id NUMBER NOT NULL
);

ALTER TABLE tienda
ADD CONSTRAINT tienda_pk PRIMARY KEY ( servicio_id );

CREATE TABLE tipos_habitacion (
  id         NUMBER NOT NULL,
  nombre     VARCHAR2(4000) NOT NULL,
  costo      NUMBER NOT NULL,
  minibar    CHAR(1) NOT NULL,
  cafetera   CHAR(1) NOT NULL,
  television CHAR(1) NOT NULL
);

ALTER TABLE tipos_habitacion
ADD CONSTRAINT tipo_habitacion_pk PRIMARY KEY ( id );

CREATE TABLE usuario (
  id              NUMBER NOT NULL,
  tipo_doc        CHAR(2 BYTE) NOT NULL,
  correo          VARCHAR2(4000) NOT NULL,
  direccion       VARCHAR2(4000) NOT NULL,
  nombre          VARCHAR2(4000) NOT NULL,
  apellido        VARCHAR2(4000) NOT NULL,
  contrasenia     CHAR(20 BYTE) NOT NULL,
  tipo_usuario    CHAR(1 CHAR) NOT NULL,
  plan_consumo_id NUMBER NOT NULL
);

ALTER TABLE usuario
ADD CONSTRAINT usuario_pk PRIMARY KEY ( id );

/**
  * FOREIGN KEYS SECTION
  */

ALTER TABLE bar
ADD CONSTRAINT bar_servicio_fk FOREIGN KEY ( servicio_id )
REFERENCES servicio ( id );

ALTER TABLE cuenta
ADD CONSTRAINT cuenta_producto_fk FOREIGN KEY ( producto_id )
REFERENCES producto ( id );

ALTER TABLE cuenta
ADD CONSTRAINT cuenta_reserva_fk FOREIGN KEY ( reserva_id )
REFERENCES reserva ( id );

ALTER TABLE gimnasio
ADD CONSTRAINT gimnasio_servicio_fk FOREIGN KEY ( servicio_id )
REFERENCES servicio ( id );

ALTER TABLE habitacion
ADD CONSTRAINT habitacion_tipos_habitacion_fk FOREIGN KEY ( tipos_habitacion_id )
REFERENCES tipos_habitacion ( id );

ALTER TABLE internet
ADD CONSTRAINT internet_servicio_fk FOREIGN KEY ( servicio_id )
REFERENCES servicio ( id );

ALTER TABLE lavanderia
ADD CONSTRAINT lavanderia_servicio_fk FOREIGN KEY ( servicio_id )
REFERENCES servicio ( id );

ALTER TABLE piscina
ADD CONSTRAINT piscina_servicio_fk FOREIGN KEY ( servicio_id )
REFERENCES servicio ( id );

ALTER TABLE prestamo_utensilios
ADD CONSTRAINT prestamo_ut_servicio_fk FOREIGN KEY ( servicio_id )
REFERENCES servicio ( id );

ALTER TABLE producto
ADD CONSTRAINT producto_servicio_fk FOREIGN KEY ( servicio_id )
REFERENCES servicio ( id );

ALTER TABLE reserva_cliente
ADD CONSTRAINT reserva_cliente_habitacion_fk FOREIGN KEY ( habitacion_id )
REFERENCES habitacion ( id );

ALTER TABLE reserva_cliente
ADD CONSTRAINT reserva_cliente_plan_con_fk FOREIGN KEY ( plan_consumo_id )
REFERENCES plan_consumo ( id );

ALTER TABLE reserva_cliente
ADD CONSTRAINT reserva_cliente_reserva_fk FOREIGN KEY ( reserva_id )
REFERENCES reserva ( id );

ALTER TABLE reserva_cliente
ADD CONSTRAINT reserva_cliente_usuario_fk FOREIGN KEY ( usuario_id )
REFERENCES usuario ( id );

ALTER TABLE reserva_servicio
ADD CONSTRAINT reserva_servicio_producto_fk FOREIGN KEY ( producto_id )
REFERENCES producto ( id );

ALTER TABLE reserva_servicio
ADD CONSTRAINT reserva_servicio_usuario_fk FOREIGN KEY ( usuario_id )
REFERENCES usuario ( id );

ALTER TABLE salon_conferencia
ADD CONSTRAINT salon_conferencia_servicio_fk FOREIGN KEY ( servicio_id )
REFERENCES servicio ( id );

ALTER TABLE spa
ADD CONSTRAINT spa_servicio_fk FOREIGN KEY ( servicio_id )
REFERENCES servicio ( id );

ALTER TABLE supermercado
ADD CONSTRAINT supermercado_servicio_fk FOREIGN KEY ( servicio_id )
REFERENCES servicio ( id );

ALTER TABLE tienda
ADD CONSTRAINT tienda_servicio_fk FOREIGN KEY ( servicio_id )
REFERENCES servicio ( id );



