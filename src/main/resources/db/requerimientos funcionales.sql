--Req funcional 1
SELECT se.nombre, hab.id as id_habitacion, sum(pro.costo) AS dinero_recolectado from producto pro inner join servicio se on pro.servicio_id = se.id
inner join cuenta cu on pro.id = cu.producto_id inner join reserva re on re.id = cu.reserva_id inner join reserva_cliente rec on re.id = rec.reserva_id
inner join habitacion hab on rec.habitacion_id = hab.id where re.fecha_final_reserva 
BETWEEN ADD_MONTHS(SYSDATE, -12) AND SYSDATE group by se.nombre, hab.id order by dinero_recolectado desc;

--Req funcional 2, reemplazar en el between_to_date las fechas para el periodo de tiempo
SELECT se.nombre, COUNT(*) AS num_consumos
FROM cuenta cu inner join producto pro on cu.producto_id = pro.id inner join servicio se on pro.servicio_id = se.id 
WHERE cu.fecha BETWEEN TO_DATE('2023-01-01', 'YYYY-MM-DD') AND TO_DATE('2023-12-31', 'YYYY-MM-DD')
GROUP BY se.nombre
ORDER BY num_consumos DESC
FETCH FIRST 20 ROWS ONLY;

-- req funcional 3
SELECT h.id, 
       (COUNT(rc.habitacion_id) / 365.0) * 100 AS indice_ocupacion
FROM habitacion h
LEFT JOIN reserva_cliente rc ON h.id = rc.habitacion_id left join reserva re on rc.reserva_id = re.id
WHERE re.fecha_final_reserva BETWEEN ADD_MONTHS(SYSDATE, -12) AND SYSDATE
GROUP BY h.id, re.fecha_final_reserva
ORDER BY indice_ocupacion DESC;

-- req funcional 4
-- Servicio por tipo y precio
select se.* from servicio se inner join producto pro on pro.servicio_id = se.id
    inner join cuenta cu on cu.producto_id = pro.id 
    where se.tipo = 'Piscina' and costo Between 0 and 100000;
    
-- Servicio por tipo
select se.* from servicio se
    where se.tipo = 'Piscina';
    
--Servicio por tipo, precio y fecha
select se.* from servicio se inner join producto pro on pro.servicio_id = se.id
    inner join cuenta cu on cu.producto_id = pro.id 
    where se.tipo = 'Piscina' and cu.fecha BETWEEN TO_DATE('2023-01-01', 'YYYY-MM-DD') AND TO_DATE('2023-12-31', 'YYYY-MM-DD') and costo Between 0 and 100000;

-- req funcional 5
Select us.nombre, pro.nombre as nombre_producto, pro.costo from usuario us inner join reserva_cliente rec on rec.usuario_id = us.id inner join reserva re on rec.reserva_id = re.id
inner join cuenta cu on cu.reserva_id = re.id inner join producto pro on cu.producto_id = pro.id where us.nombre = 'nombre_cliente';

-- req funcional 6
-- fecha mayor ocupacion
SELECT fecha, COUNT(habitacion_id) AS ocupacion
FROM reserva_cliente
GROUP BY fecha
ORDER BY ocupacion DESC
FETCH FIRST 1 ROW ONLY;
--fechas mayores ingresos
SELECT cu.fecha, SUM(pro.costo) AS ingresos
FROM cuenta cu 
INNER JOIN producto pro ON cu.producto_id = pro.id 
GROUP BY cu.fecha
ORDER BY ingresos DESC
FETCH FIRST 1 ROW ONLY;
--fechas menor demanda
SELECT fecha, COUNT(habitacion_id) AS ocupacion
FROM reserva_cliente
GROUP BY fecha
ORDER BY ocupacion ASC
FETCH FIRST 1 ROW ONLY;


-- req funcional 7
SELECT nombre, duracion_total, consumo_total
FROM (SELECT  us.nombre, SUM(re.fecha_final_reserva - re.fecha_inicio_reserva) AS duracion_total, SUM(pro.costo) AS consumo_total
    FROM 
        usuario us 
        INNER JOIN reserva_cliente rec ON rec.usuario_id = us.id 
        INNER JOIN reserva re ON rec.reserva_id = re.id 
        INNER JOIN cuenta cu ON cu.reserva_id = re.id
        INNER JOIN producto pro ON cu.producto_id = pro.id
    WHERE  
        re.fecha_final_reserva BETWEEN ADD_MONTHS(SYSDATE, -12) AND SYSDATE
    GROUP BY 
        us.nombre
) 
WHERE duracion_total >= 14 OR consumo_total > 15000000
ORDER BY consumo_total DESC, duracion_total DESC;

--req funcional 8
SELECT COUNT(CUENTA.ID) FROM CUENTA,PRODUCTO
WHERE FECHA >= (SYSDATE - INTERVAL '1' YEAR) AND CUENTA.PRODUCTO_ID = PRODUCTO.ID
GROUP BY PRODUCTO.SERVICIO_ID HAVING COUNT(CUENTA.ID) < 156
-- 52 Semanas en un a�o 

--req funcional 9
--Especifico
--Sin agrupar
SELECT c2.ID AS ID_CONSUMO,u.ID, u.NOMBRE, u.APELLIDO
FROM (
    SELECT * FROM CUENTA 
    WHERE FECHA > TO_DATE('01-JAN-22', 'DD-MON-YY') AND FECHA < TO_DATE('31-DEC-23', 'DD-MON-YY')
) c2
INNER JOIN (
    SELECT * FROM PRODUCTO 
    WHERE SERVICIO_ID = 3
) p2 ON c2.PRODUCTO_ID = p2.ID
INNER JOIN RESERVA_CLIENTE rc ON rc.RESERVA_ID = c2.RESERVA_ID
INNER JOIN USUARIO u ON rc.USUARIO_ID = u.ID;

--Agrupar por fechas
SELECT CUENTA2.FECHA, COUNT(CUENTA2.ID) AS NumeroConsumo
FROM (
    SELECT * FROM CUENTA 
    WHERE FECHA > TO_DATE('01-JAN-22', 'DD-MON-YY') AND FECHA < TO_DATE('31-DEC-23', 'DD-MON-YY')
) CUENTA2
INNER JOIN (
    SELECT * FROM PRODUCTO 
    WHERE SERVICIO_ID = 3
) PRODUCTO2 ON CUENTA2.PRODUCTO_ID = PRODUCTO2.ID
INNER JOIN RESERVA_CLIENTE ON RESERVA_CLIENTE.RESERVA_ID = CUENTA2.RESERVA_ID
INNER JOIN USUARIO ON RESERVA_CLIENTE.USUARIO_ID = USUARIO.ID
GROUP BY CUENTA2.FECHA;

--Agrupar por cliente y fecha
SELECT c2.FECHA, COUNT(c2.ID) AS NumeroConsumo, p2.SERVICIO_ID as ID_SERVICIO, u.ID, u.NOMBRE, u.APELLIDO
FROM (
    SELECT * FROM CUENTA 
    WHERE FECHA > TO_DATE('01-JAN-22', 'DD-MON-YY') AND FECHA < TO_DATE('31-DEC-23', 'DD-MON-YY')
) c2
INNER JOIN (
    SELECT * FROM PRODUCTO 
    WHERE SERVICIO_ID = 3
) p2 ON c2.PRODUCTO_ID = p2.ID
INNER JOIN RESERVA_CLIENTE rc ON rc.RESERVA_ID = c2.RESERVA_ID
INNER JOIN USUARIO u ON rc.USUARIO_ID = u.ID
GROUP BY c2.FECHA, p2.SERVICIO_ID, u.ID, u.NOMBRE, u.APELLIDO;

--Agrupar por cliente
SELECT u.ID, u.NOMBRE, u.APELLIDO, COUNT(c2.ID) AS NumeroConsumo
FROM (
    SELECT * FROM CUENTA 
    WHERE FECHA > TO_DATE('01-JAN-22', 'DD-MON-YY') AND FECHA < TO_DATE('31-DEC-23', 'DD-MON-YY')
) c2
INNER JOIN (
    SELECT * FROM PRODUCTO 
    WHERE SERVICIO_ID = 3
) p2 ON c2.PRODUCTO_ID = p2.ID
INNER JOIN RESERVA_CLIENTE rc ON rc.RESERVA_ID = c2.RESERVA_ID
INNER JOIN USUARIO u ON rc.USUARIO_ID = u.ID
GROUP BY u.ID, u.NOMBRE, u.APELLIDO;

--Agrupar por frecuencia de consumos
SELECT FrecuenciaConsumo, COUNT(*) AS NumeroUsuarios
FROM (
    SELECT u.ID, COUNT(*) AS FrecuenciaConsumo
    FROM (
        SELECT * FROM CUENTA 
        WHERE FECHA > TO_DATE('01-JAN-22', 'DD-MON-YY') AND FECHA < TO_DATE('31-DEC-23', 'DD-MON-YY')
    ) c2
    INNER JOIN (
        SELECT * FROM PRODUCTO 
        WHERE SERVICIO_ID = 3
    ) p2 ON c2.PRODUCTO_ID = p2.ID
    INNER JOIN RESERVA_CLIENTE rc ON rc.RESERVA_ID = c2.RESERVA_ID
    INNER JOIN USUARIO u ON rc.USUARIO_ID = u.ID
    GROUP BY u.ID
)
GROUP BY FrecuenciaConsumo;

--Requerimiento Funcional 10

--Sin agrupar
SELECT c2.ID AS ID_CONSUMO, p2.SERVICIO_ID as ID_SERVICIO,u.ID, u.NOMBRE, u.APELLIDO
FROM (
    SELECT * FROM CUENTA 
    WHERE FECHA > TO_DATE('01-JAN-22', 'DD-MON-YY') AND FECHA < TO_DATE('31-DEC-23', 'DD-MON-YY')
) c2
INNER JOIN (
    SELECT * FROM PRODUCTO 
    WHERE SERVICIO_ID != 3
) p2 ON c2.PRODUCTO_ID = p2.ID
INNER JOIN RESERVA_CLIENTE rc ON rc.RESERVA_ID = c2.RESERVA_ID
INNER JOIN USUARIO u ON rc.USUARIO_ID = u.ID;

--Agrupar por fechas
SELECT c2.FECHA, COUNT(c2.ID) AS NumeroConsumo
FROM (
    SELECT * FROM CUENTA 
    WHERE FECHA > TO_DATE('01-JAN-22', 'DD-MON-YY') AND FECHA < TO_DATE('31-DEC-23', 'DD-MON-YY')
) c2
INNER JOIN (
    SELECT * FROM PRODUCTO 
    WHERE SERVICIO_ID != 3
) p2 ON c2.PRODUCTO_ID = p2.ID
INNER JOIN RESERVA_CLIENTE rc ON rc.RESERVA_ID = c2.RESERVA_ID
INNER JOIN USUARIO u ON rc.USUARIO_ID = u.ID
GROUP BY c2.FECHA;

--Agrupar por frecuencias de consumo
SELECT FrecuenciaConsumo, COUNT(*) AS NumeroUsuarios
FROM (
    SELECT u.ID, COUNT(*) AS FrecuenciaConsumo
    FROM (
        SELECT * FROM CUENTA 
        WHERE FECHA > TO_DATE('01-JAN-22', 'DD-MON-YY') AND FECHA < TO_DATE('31-DEC-23', 'DD-MON-YY')
    ) c2
    INNER JOIN (
        SELECT * FROM PRODUCTO 
        WHERE SERVICIO_ID != 3
    ) p2 ON c2.PRODUCTO_ID = p2.ID
    INNER JOIN RESERVA_CLIENTE rc ON rc.RESERVA_ID = c2.RESERVA_ID
    INNER JOIN USUARIO u ON rc.USUARIO_ID = u.ID
    GROUP BY u.ID
)
GROUP BY FrecuenciaConsumo;

--Agrupar por usuarios
SELECT COUNT(c2.ID) AS TOTAL_CONSUMOs,u.ID, u.NOMBRE, u.APELLIDO
FROM (
    SELECT * FROM CUENTA 
    WHERE FECHA > TO_DATE('01-JAN-22', 'DD-MON-YY') AND FECHA < TO_DATE('31-DEC-23', 'DD-MON-YY')
) c2
INNER JOIN (
    SELECT * FROM PRODUCTO 
    WHERE SERVICIO_ID != 3
) p2 ON c2.PRODUCTO_ID = p2.ID
INNER JOIN RESERVA_CLIENTE rc ON rc.RESERVA_ID = c2.RESERVA_ID
INNER JOIN USUARIO u ON rc.USUARIO_ID = u.ID
GROUP BY u.ID, u.NOMBRE, u.APELLIDO;

--Agrupar por usuario y fecha
SELECT c2.FECHA, COUNT(c2.ID) AS NumeroConsumo, p2.SERVICIO_ID as ID_SERVICIO, u.ID, u.NOMBRE, u.APELLIDO
FROM (
    SELECT * FROM CUENTA 
    WHERE FECHA > TO_DATE('01-JAN-22', 'DD-MON-YY') AND FECHA < TO_DATE('31-DEC-23', 'DD-MON-YY')
) c2
INNER JOIN (
    SELECT * FROM PRODUCTO 
    WHERE SERVICIO_ID != 3
) p2 ON c2.PRODUCTO_ID = p2.ID
INNER JOIN RESERVA_CLIENTE rc ON rc.RESERVA_ID = c2.RESERVA_ID
INNER JOIN USUARIO u ON rc.USUARIO_ID = u.ID
GROUP BY c2.FECHA, p2.SERVICIO_ID, u.ID, u.NOMBRE, u.APELLIDO;

--Req 12
--1 Estadias Trimestre
SELECT USUARIO.ID, USUARIO.TIPO_DOC, USUARIO.NOMBRE, USUARIO.APELLIDO, USUARIO.CORREO, USUARIO.DIRECCION, 'Caso 1' AS TIPO_SELECCION
FROM (
    SELECT * 
    FROM RESERVA 
    WHERE CHECK_IN > (SYSDATE - INTERVAL '90' DAY) AND CHECK_OUT <= SYSDATE
) RESERVA2
INNER JOIN RESERVA_CLIENTE ON RESERVA2.ID = RESERVA_CLIENTE.RESERVA_ID
INNER JOIN USUARIO ON USUARIO.ID = RESERVA_CLIENTE.USUARIO_ID
GROUP BY USUARIO.ID, USUARIO.TIPO_DOC, USUARIO.NOMBRE, USUARIO.APELLIDO, USUARIO.CORREO, USUARIO.DIRECCION;

--2 Productos >= 300000
SELECT USUARIO.ID, USUARIO.TIPO_DOC, USUARIO.NOMBRE, USUARIO.APELLIDO, USUARIO.CORREO, USUARIO.DIRECCION, 'Caso 2' AS TIPO_SELECCION
FROM RESERVA_CLIENTE
LEFT JOIN CUENTA ON RESERVA_CLIENTE.RESERVA_ID = CUENTA.RESERVA_ID AND CUENTA.PRODUCTO_ID IN
(SELECT PRODUCTO.ID FROM PRODUCTO WHERE PRODUCTO.COSTO >= 300000)
INNER JOIN USUARIO ON USUARIO.ID = RESERVA_CLIENTE.USUARIO_ID
WHERE USUARIO.ID IN (
    SELECT RESERVA_CLIENTE.USUARIO_ID
    FROM RESERVA_CLIENTE
    LEFT JOIN CUENTA ON RESERVA_CLIENTE.RESERVA_ID = CUENTA.RESERVA_ID
    WHERE CUENTA.PRODUCTO_ID = NULL
)
GROUP BY USUARIO.ID, USUARIO.TIPO_D OC, USUARIO.NOMBRE, USUARIO.APELLIDO, USUARIO.CORREO, USUARIO.DIRECCION;

--3 Servicios de 4 horas
SELECT USUARIO.ID, USUARIO.TIPO_DOC, USUARIO.NOMBRE, USUARIO.APELLIDO, USUARIO.CORREO, USUARIO.DIRECCION, 'Caso 3' AS TIPO_SELECCION
FROM RESERVA_CLIENTE
LEFT JOIN CUENTA ON RESERVA_CLIENTE.RESERVA_ID = CUENTA.RESERVA_ID AND CUENTA.PRODUCTO_ID IN
(SELECT PRODUCTO.ID FROM PRODUCTO WHERE PRODUCTO.SERVICIO_ID IN (SELECT SERVICIO.ID FROM SERVICIO WHERE SERVICIO.TIPO = 'Spa' OR SERVICIO.TIPO = 'Salon') AND PRODUCTO.DURACION >= 240)
INNER JOIN USUARIO ON USUARIO.ID = RESERVA_CLIENTE.USUARIO_ID
WHERE USUARIO.ID IN (
    SELECT RESERVA_CLIENTE.USUARIO_ID
    FROM RESERVA_CLIENTE
    LEFT JOIN CUENTA ON RESERVA_CLIENTE.RESERVA_ID = CUENTA.RESERVA_ID
    WHERE CUENTA.PRODUCTO_ID = NULL
)
GROUP BY USUARIO.ID, USUARIO.TIPO_DOC, USUARIO.NOMBRE, USUARIO.APELLIDO, USUARIO.CORREO, USUARIO.DIRECCION;

--Union Causales
SELECT USUARIO.ID, USUARIO.TIPO_DOC, USUARIO.NOMBRE, USUARIO.APELLIDO, USUARIO.CORREO, USUARIO.DIRECCION, 'Caso 1' AS TIPO_SELECCION
FROM (
    SELECT * 
    FROM RESERVA 
    WHERE CHECK_IN > (SYSDATE - INTERVAL '90' DAY) AND CHECK_OUT <= SYSDATE
) RESERVA2
INNER JOIN RESERVA_CLIENTE ON RESERVA2.ID = RESERVA_CLIENTE.RESERVA_ID
INNER JOIN USUARIO ON USUARIO.ID = RESERVA_CLIENTE.USUARIO_ID
GROUP BY USUARIO.ID, USUARIO.TIPO_DOC, USUARIO.NOMBRE, USUARIO.APELLIDO, USUARIO.CORREO, USUARIO.DIRECCION

UNION

SELECT USUARIO.ID, USUARIO.TIPO_DOC, USUARIO.NOMBRE, USUARIO.APELLIDO, USUARIO.CORREO, USUARIO.DIRECCION, 'Caso 2' AS TIPO_SELECCION
FROM RESERVA_CLIENTE
LEFT JOIN CUENTA ON RESERVA_CLIENTE.RESERVA_ID = CUENTA.RESERVA_ID AND CUENTA.PRODUCTO_ID IN
(SELECT PRODUCTO.ID FROM PRODUCTO WHERE PRODUCTO.COSTO >= 300000)
INNER JOIN USUARIO ON USUARIO.ID = RESERVA_CLIENTE.USUARIO_ID
WHERE USUARIO.ID IN (
    SELECT RESERVA_CLIENTE.USUARIO_ID
    FROM RESERVA_CLIENTE
    LEFT JOIN CUENTA ON RESERVA_CLIENTE.RESERVA_ID = CUENTA.RESERVA_ID
    WHERE CUENTA.PRODUCTO_ID = NULL
)
GROUP BY USUARIO.ID, USUARIO.TIPO_DOC, USUARIO.NOMBRE, USUARIO.APELLIDO, USUARIO.CORREO, USUARIO.DIRECCION

UNION

SELECT USUARIO.ID, USUARIO.TIPO_DOC, USUARIO.NOMBRE, USUARIO.APELLIDO, USUARIO.CORREO, USUARIO.DIRECCION, 'Caso 3' AS TIPO_SELECCION
FROM RESERVA_CLIENTE
LEFT JOIN CUENTA ON RESERVA_CLIENTE.RESERVA_ID = CUENTA.RESERVA_ID AND CUENTA.PRODUCTO_ID IN
(SELECT PRODUCTO.ID FROM PRODUCTO WHERE PRODUCTO.SERVICIO_ID IN (SELECT SERVICIO.ID FROM SERVICIO WHERE SERVICIO.TIPO = 'Spa' OR SERVICIO.TIPO = 'Salon') AND PRODUCTO.DURACION >= 240)
INNER JOIN USUARIO ON USUARIO.ID = RESERVA_CLIENTE.USUARIO_ID
WHERE USUARIO.ID IN (
    SELECT RESERVA_CLIENTE.USUARIO_ID
    FROM RESERVA_CLIENTE
    LEFT JOIN CUENTA ON RESERVA_CLIENTE.RESERVA_ID = CUENTA.RESERVA_ID
    WHERE CUENTA.PRODUCTO_ID = NULL
)
GROUP BY USUARIO.ID, USUARIO.TIPO_DOC, USUARIO.NOMBRE, USUARIO.APELLIDO, USUARIO.CORREO, USUARIO.DIRECCION;


--Indices requerimientos
--Requerimiento 1
CREATE INDEX idx_producto_servicio ON producto(servicio_id); -- Se usa en el join, Tambi�n se usa en el req 2
CREATE INDEX idx_cuenta_producto ON cuenta(producto_id); -- Tambi�n se usa en el req 2, en el req 5, req 6
CREATE INDEX idx_reserva_habitacion ON reserva_cliente(reserva_id, habitacion_id); -- Se usan estas dos para el join juntas
CREATE INDEX idx_cuenta_fecha ON cuenta(fecha); -- Se usa para encontrar el rango de fechas, tambi�n se usa en el req 2


--Requerimiento 2
CREATE INDEX idx_reserva_cliente_fecha ON reserva_cliente(fecha);
--Requiremento 3
CREATE INDEX idx_reserva_cliente_habitacion_id ON reserva_cliente(habitacion_id); --Se usa para el join
CREATE INDEX idx_reserva_fecha_final_reserva ON reserva(fecha_final_reserva); -- Se usa para el join


--Req 5 ya hay un indice de las primary key compuesto de (usuario_id,reserva_id) en la tabla reserva cliente.
CREATE INDEX idx_cuenta_reserva_id ON cuenta(reserva_id); -- Util para el join entre cuenta y reserva, se usa tambien en el req 7
CREATE INDEX idx_usuario_nombre ON usuario(nombre); -- Util para encontrar mas rapido el nombre del usuario


