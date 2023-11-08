package uniandes.sistrans.HotelDeLosAndes.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import uniandes.sistrans.HotelDeLosAndes.models.SuperObjeto;

@Service
public class SuperServicio {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // REQ FUNC-1: ServicioController.java
    public List<SuperObjeto> dineroRecolectado() {

        /*
         * Se realiza un JOIN de las tablas PRODUCTO, SERVICIO, CUENTA, RESERVA, RESERVA_CLIENTE y HABITACION.
         * Luego, se filtra los datos del último año.
         * Con ello, se calcula la cantidad de dinero recolectado por cada servicio en cada habitación.
         * Finalmente, se agrupan los resultados por el nombre del servicio y el ID de la habitación, y se ordenan en orden descendente por el dinero recolectado.
         */

        String sql = "SELECT se.nombre, hab.id as id_habitacion, sum(pro.costo) AS dinero_recolectado from producto pro inner join servicio se on pro.servicio_id = se.id inner join cuenta cu on pro.id = cu.producto_id inner join reserva re on re.id = cu.reserva_id inner join reserva_cliente rec on re.id = rec.reserva_id inner join habitacion hab on rec.habitacion_id = hab.id where re.fecha_final_reserva BETWEEN ADD_MONTHS(SYSDATE, -12) AND SYSDATE group by se.nombre, hab.id order by dinero_recolectado desc";

        return jdbcTemplate.query(sql, new ResultSetExtractor<List<SuperObjeto>>() {
            @Override
            // Lo que está dentro de list<Objeto> es el objeto al que se mapea el resultado
            public List<SuperObjeto> extractData(ResultSet rs) throws SQLException {
                List<SuperObjeto> list = new ArrayList<SuperObjeto>();

                while (rs.next()) {
                    String nombre = rs.getString("nombre");
                    Long id_habitacion = rs.getLong("id_habitacion");
                    Double dinero_recolectado = rs.getDouble("dinero_recolectado");

                    SuperObjeto superObjeto = new SuperObjeto();
                    superObjeto.setNombre(nombre);
                    superObjeto.setId_habitacion(id_habitacion);
                    superObjeto.setDinero_recolectado(dinero_recolectado);

                    list.add(superObjeto);
                }

                return list;
            }
        });
    }

    // REQ FUNC-2: ServicioController.java
    public List<SuperObjeto> darServiciosMasPopulares(LocalDate fechaInicio, LocalDate fechaFin) {

        /*
         * Se realiza un JOIN de la tabla CUENTA con PRODUCTO y SERVICIO. 
         * Luego, se filtra los datos entre dos fechas dadas.
         * Con ello, se calcula el número de consumos por cada servicio.
         * Finalmente, se ordenan los servicios por el número de consumos en orden descendente y se limita la salida a los primeros 20 registros.
         */
        String sql = "SELECT se.nombre, COUNT(*) AS num_consumos\n" +
                "FROM cuenta cu\n" +
                "INNER JOIN producto pro ON cu.producto_id = pro.id\n" +
                "INNER JOIN servicio se ON pro.servicio_id = se.id\n" +
                "WHERE cu.fecha BETWEEN ? AND ?" +
                "GROUP BY se.nombre\n" +
                "ORDER BY num_consumos DESC\n" +
                "FETCH FIRST 20 ROWS ONLY";
        return jdbcTemplate.query(sql, new ResultSetExtractor<List<SuperObjeto>>() {
            @Override
            // Lo que está dentro de list<Objeto> es el objeto al que se mapea el resultado
            public List<SuperObjeto> extractData(ResultSet rs) throws SQLException {
                List<SuperObjeto> list = new ArrayList<SuperObjeto>();

                while (rs.next()) {
                    String nombre = rs.getString("nombre");
                    Integer num_consumos = rs.getInt("num_consumos");

                    SuperObjeto superObjeto = new SuperObjeto();
                    superObjeto.setNombre(nombre);
                    superObjeto.setNum_consumos(num_consumos);

                    list.add(superObjeto);
                }

                return list;
            }
        }, fechaInicio, fechaFin);
    }

    // REQ FUNC-3: HabitacionesController.java
    public List<SuperObjeto> darIndicesOcupacion() {
        /*
         * Se realiza un JOIN de las tablas HABITACION, RESERVA_CLIENTE y RESERVA.
         * Luego, se filtra los datos del último año.
         * Con ello, se calcula el índice de ocupación de cada habitación, que es el porcentaje de días que la habitación ha estado ocupada en el último año.
         * Finalmente, se agrupan los resultados por el ID de la habitación y la fecha final de la reserva, y se ordenan en orden descendente por el índice de ocupación.
         */

        String sql = "SELECT h.id, " +
                "(COUNT(rc.habitacion_id) / 365.0) * 100 AS indice_ocupacion " +
                "FROM habitacion h " +
                "LEFT JOIN reserva_cliente rc ON h.id = rc.habitacion_id " +
                "LEFT JOIN reserva re ON rc.reserva_id = re.id " +
                "WHERE re.fecha_final_reserva BETWEEN ADD_MONTHS(SYSDATE, -12) AND SYSDATE " +
                "GROUP BY h.id, re.fecha_final_reserva " +
                "ORDER BY indice_ocupacion DESC";

        return jdbcTemplate.query(sql, new ResultSetExtractor<List<SuperObjeto>>() {
            @Override
            // Lo que está dentro de list<Objeto> es el objeto al que se mapea el resultado
            public List<SuperObjeto> extractData(ResultSet rs) throws SQLException {
                List<SuperObjeto> list = new ArrayList<SuperObjeto>();

                while (rs.next()) {
                    Long id_habitacion = rs.getLong("id");
                    Double indice_ocupacion = rs.getDouble("indice_ocupacion");

                    SuperObjeto superObjeto = new SuperObjeto();
                    superObjeto.setId_habitacion(id_habitacion);
                    superObjeto.setIndice_ocupacion(indice_ocupacion);

                    list.add(superObjeto);
                }

                return list;
            }
        });
    }

    // REQ FUNC-4: x.java


    // REQ FUNC-5: UsuarioController.java
    public List<SuperObjeto> consumosUsuario(LocalDate fechaInicio, LocalDate fechaFin, Integer usuarioId) {

        /*
         * Se realiza un JOIN de las tablas USUARIO, RESERVA_CLIENTE, RESERVA, CUENTA y PRODUCTO.
         * Luego, se filtra los datos entre dos fechas dadas.
         * Con ello, se obtiene el nombre del usuario, el nombre del producto y el costo del producto para un usuario específico.
         */

        String sql = "SELECT us.nombre, pro.nombre AS nombre_producto, pro.costo\n" +
                "FROM usuario us\n" +
                "INNER JOIN reserva_cliente rec ON rec.usuario_id = us.id\n" +
                "INNER JOIN reserva re ON rec.reserva_id = re.id\n" +
                "INNER JOIN cuenta cu ON cu.reserva_id = re.id\n" +
                "INNER JOIN producto pro ON cu.producto_id = pro.id\n" +
                "WHERE cu.fecha BETWEEN ? AND ?\n" +
                "AND us.id = ? ";

        return jdbcTemplate.query(sql, new ResultSetExtractor<List<SuperObjeto>>() {
            @Override
            // Lo que está dentro de list<Objeto> es el objeto al que se mapea el resultado
            public List<SuperObjeto> extractData(ResultSet rs) throws SQLException {
                List<SuperObjeto> list = new ArrayList<SuperObjeto>();

                while (rs.next()) {
                    String nombre_usuario = rs.getString("nombre");
                    String nombre_producto = rs.getString("nombre_producto");
                    Integer costo = rs.getInt("costo");

                    SuperObjeto superObjeto = new SuperObjeto();
                    superObjeto.setNombre_usuario(nombre_usuario);
                    superObjeto.setNombre_producto(nombre_producto);
                    superObjeto.setCosto(costo);

                    list.add(superObjeto);
                }

                return list;
            }
        }, fechaInicio, fechaFin, usuarioId);
    }

    // REQ FUNC-6: CuentasController.java
    public List<SuperObjeto> darFechaMayorOcupacion() {
        /*
         * Se realiza un JOIN de las tablas RESERVA y RESERVA_CLIENTE.
         * Luego, se agrupa los datos por la fecha final de la reserva.
         * Con ello, se calcula el número de habitaciones distintas ocupadas en cada fecha.
         * Finalmente, se ordenan los resultados en orden descendente por la ocupación y se limita la salida al primer registro.
         */
        String sql = "SELECT fecha_final_reserva AS fecha, COUNT(DISTINCT habitacion_id) AS ocupacion\n" +
                "FROM reserva\n" +
                "INNER JOIN reserva_cliente ON reserva.id = reserva_cliente.reserva_id\n" +
                "GROUP BY fecha_final_reserva\n" +
                "ORDER BY ocupacion DESC\n" +
                "FETCH FIRST 1 ROW ONLY";

        return jdbcTemplate.query(sql, new ResultSetExtractor<List<SuperObjeto>>() {
            @Override
            // Lo que está dentro de list<Objeto> es el objeto al que se mapea el resultado
            public List<SuperObjeto> extractData(ResultSet rs) throws SQLException {
                List<SuperObjeto> list = new ArrayList<SuperObjeto>();

                while (rs.next()) {
                    Date fecha = rs.getDate("fecha");
                    Integer ocupacion = rs.getInt("ocupacion");

                    SuperObjeto superObjeto = new SuperObjeto();
                    superObjeto.setFecha(fecha);
                    superObjeto.setOcupacion(ocupacion);

                    list.add(superObjeto);
                }

                return list;
            }
        });
    }

    // REQ FUNC-6 (pt. 2)
    public List<SuperObjeto> darFechaMenorOcupacion() {
        /*
         * Se realiza un JOIN de las tablas RESERVA y RESERVA_CLIENTE.
         * Luego, se agrupa los datos por la fecha final de la reserva.
         * Con ello, se calcula el número de habitaciones distintas ocupadas en cada fecha.
         * Finalmente, se ordenan los resultados en orden ascendente por la ocupación y se limita la salida al primer registro.
         */

        String sql = "SELECT fecha_final_reserva AS fecha, COUNT(DISTINCT habitacion_id) AS ocupacion\n" +
                "FROM reserva\n" +
                "INNER JOIN reserva_cliente ON reserva.id = reserva_cliente.reserva_id\n" +
                "GROUP BY fecha_final_reserva\n" +
                "ORDER BY ocupacion ASC\n" +
                "FETCH FIRST 1 ROW ONLY";

        return jdbcTemplate.query(sql, new ResultSetExtractor<List<SuperObjeto>>() {
            @Override
            // Lo que está dentro de list<Objeto> es el objeto al que se mapea el resultado
            public List<SuperObjeto> extractData(ResultSet rs) throws SQLException {
                List<SuperObjeto> list = new ArrayList<SuperObjeto>();

                while (rs.next()) {
                    Date fecha = rs.getDate("fecha");
                    Integer ocupacion = rs.getInt("ocupacion");

                    SuperObjeto superObjeto = new SuperObjeto();
                    superObjeto.setFecha(fecha);
                    superObjeto.setOcupacion(ocupacion);

                    list.add(superObjeto);
                }

                return list;
            }
        });
    }

    // REQ FUNC-6 (pt. 3)
    public List<SuperObjeto> darFechaMayoresIngreso() {
        /*
         * Se realiza un JOIN de las tablas CUENTA y PRODUCTO.
         * Luego, se agrupa los datos por la fecha de la cuenta.
         * Con ello, se calcula la suma del costo de los productos para cada fecha.
         * Finalmente, se ordenan los resultados en orden descendente por los ingresos y se limita la salida al primer registro.
         */

        String sql = "SELECT cu.fecha, SUM(pro.costo) AS ingresos\n" +
                "FROM cuenta cu\n" +
                "INNER JOIN producto pro ON cu.producto_id = pro.id\n" +
                "GROUP BY cu.fecha\n" +
                "ORDER BY ingresos DESC\n" +
                "FETCH FIRST 1 ROW ONLY";

        return jdbcTemplate.query(sql, new ResultSetExtractor<List<SuperObjeto>>() {
            @Override
            // Lo que está dentro de list<Objeto> es el objeto al que se mapea el resultado
            public List<SuperObjeto> extractData(ResultSet rs) throws SQLException {
                List<SuperObjeto> list = new ArrayList<SuperObjeto>();

                while (rs.next()) {
                    Date fecha = rs.getDate("fecha");
                    Double ingresos = rs.getDouble("ingresos");
                    SuperObjeto superObjeto = new SuperObjeto();
                    superObjeto.setFecha(fecha);
                    superObjeto.setIngresos(ingresos);

                    list.add(superObjeto);
                }

                return list;
            }
        });

    }

    // REQ FUNC-7: UsuarioController.java
    public List<SuperObjeto> darBuenosClientes() {
        /*
         * Se realiza un JOIN de las tablas USUARIO, RESERVA_CLIENTE, RESERVA, CUENTA y PRODUCTO.
         * Luego, se filtra los datos del último año.
         * Con ello, se calcula la duración total de las reservas y el consumo total de los productos para cada usuario.
         * Después, se filtran los usuarios cuya duración total de las reservas es mayor o igual a 14 días o cuyo consumo total es mayor a 15000000.
         * Finalmente, se ordenan los resultados en orden descendente por el consumo total y la duración total.
         */

        String sql = "SELECT nombre, duracion_total, consumo_total\n" +
                "FROM (SELECT  us.nombre, SUM(re.fecha_final_reserva - re.fecha_inicio_reserva) AS duracion_total, SUM(pro.costo) AS consumo_total\n"
                +
                "    FROM \n" +
                "        usuario us \n" +
                "        INNER JOIN reserva_cliente rec ON rec.usuario_id = us.id \n" +
                "        INNER JOIN reserva re ON rec.reserva_id = re.id \n" +
                "        INNER JOIN cuenta cu ON cu.reserva_id = re.id\n" +
                "        INNER JOIN producto pro ON cu.producto_id = pro.id\n" +
                "    WHERE  \n" +
                "        re.fecha_final_reserva BETWEEN ADD_MONTHS(SYSDATE, -12) AND SYSDATE\n" +
                "    GROUP BY \n" +
                "        us.nombre\n" +
                ") \n" +
                "WHERE duracion_total >= 14 OR consumo_total > 15000000\n" +
                "ORDER BY consumo_total DESC, duracion_total DESC";

        return jdbcTemplate.query(sql, new ResultSetExtractor<List<SuperObjeto>>() {
            @Override
            // Lo que está dentro de list<Objeto> es el objeto al que se mapea el resultado
            public List<SuperObjeto> extractData(ResultSet rs) throws SQLException {
                List<SuperObjeto> list = new ArrayList<SuperObjeto>();

                while (rs.next()) {
                    String nombre_usuario = rs.getString("nombre");
                    Integer duracion_total = rs.getInt("DURACION_TOTAL");
                    Double consumo_total = rs.getDouble("consumo_total");
                    SuperObjeto superObjeto = new SuperObjeto();
                    superObjeto.setNombre_usuario(nombre_usuario);
                    superObjeto.setDuracion_total(duracion_total);
                    superObjeto.setConsumo_total(consumo_total);

                    list.add(superObjeto);
                }

                return list;
            }
        });

    }

    // REQ FUNC-8: ServicioController.java
    public List<SuperObjeto> serviciosMenosDemanda() {
        /*
         * Se realiza un JOIN de las tablas USUARIO, RESERVA_CLIENTE, RESERVA, CUENTA y PRODUCTO.
         * Luego, se filtra los datos del último año.
         * Con ello, se calcula la duración total de las reservas y el consumo total de los productos para cada usuario.
         * Después, se filtran los usuarios cuya duración total de las reservas es mayor o igual a 14 días o cuyo consumo total es mayor a 15000000.
         * Finalmente, se ordenan los resultados en orden descendente por el consumo total y la duración total.
         */

        String sql = "SELECT nombre, duracion_total, consumo_total\n" +
                "FROM (SELECT  us.nombre, SUM(re.fecha_final_reserva - re.fecha_inicio_reserva) AS duracion_total, SUM(pro.costo) AS consumo_total\n"
                +
                "    FROM \n" +
                "        usuario us \n" +
                "        INNER JOIN reserva_cliente rec ON rec.usuario_id = us.id \n" +
                "        INNER JOIN reserva re ON rec.reserva_id = re.id \n" +
                "        INNER JOIN cuenta cu ON cu.reserva_id = re.id\n" +
                "        INNER JOIN producto pro ON cu.producto_id = pro.id\n" +
                "    WHERE  \n" +
                "        re.fecha_final_reserva BETWEEN ADD_MONTHS(SYSDATE, -12) AND SYSDATE\n" +
                "    GROUP BY \n" +
                "        us.nombre\n" +
                ") \n" +
                "WHERE duracion_total >= 14 OR consumo_total > 15000000\n" +
                "ORDER BY consumo_total DESC, duracion_total DESC";

        return jdbcTemplate.query(sql, new ResultSetExtractor<List<SuperObjeto>>() {
            @Override
            // Lo que está dentro de list<Objeto> es el objeto al que se mapea el resultado
            public List<SuperObjeto> extractData(ResultSet rs) throws SQLException {
                List<SuperObjeto> list = new ArrayList<SuperObjeto>();

                while (rs.next()) {
                    String nombre_usuario = rs.getString("nombre");
                    Integer duracion_total = rs.getInt("duracion_total");
                    Double consumo_total = rs.getDouble("consumo_total");
                    SuperObjeto superObjeto = new SuperObjeto();
                    superObjeto.setNombre_usuario(nombre_usuario);
                    superObjeto.setDuracion_total(duracion_total);
                    superObjeto.setConsumo_total(consumo_total);

                    list.add(superObjeto);
                }

                return list;
            }
        });

    }

    // REQ FUNC-9: ServicioController.java
    /*
     * - Inicialmente, se construye una consulta SQL dinámica basada en el valor de `tipoAgrupamiento`.
     * - Si `tipoAgrupamiento` es "Nada", la consulta selecciona el ID del consumo, el ID del usuario, y los nombres y apellidos del usuario.
     * - Si `tipoAgrupamiento` es "Fecha", la consulta selecciona la fecha, el nombre del usuario, y cuenta el número de consumos agrupados por fecha y nombre del usuario.
     * - Si `tipoAgrupamiento` es "Fecha y Cliente", la consulta selecciona la fecha, el número de consumos, el ID del servicio, el ID del usuario, y los nombres y apellidos del usuario, agrupados por fecha, ID del servicio, y ID del usuario.
     * - Si `tipoAgrupamiento` es "Cliente", la consulta selecciona el ID del usuario, los nombres y apellidos del usuario, y cuenta el número de consumos agrupados por ID del usuario.
     * - Si `tipoAgrupamiento` es "Frecuencia de Consumo", la consulta selecciona la frecuencia de consumo y cuenta el número de usuarios agrupados por frecuencia de consumo.
     * - Luego, la consulta se ejecuta en la base de datos y los resultados se almacenan en una lista de objetos `SuperObjeto`.
     * - Cada objeto `SuperObjeto` almacena los datos relevantes dependiendo del valor de `tipoAgrupamiento`. Por ejemplo, si `tipoAgrupamiento` es "Fecha y Cliente", el objeto `SuperObjeto` almacenará la fecha, el número de consumos, el ID del servicio, el ID del usuario, y los nombres y apellidos del usuario.
     * - Finalmente, la función devuelve la lista de objetos `SuperObjeto`.
     */

    public List<SuperObjeto> darConsumosServiciosUsuario(LocalDate fechaInicio, LocalDate fechaFin, Integer servicioId,
            String tipoAgrupamiento) {

        System.out.println(tipoAgrupamiento);
        StringBuilder sqlBuilder = new StringBuilder("SELECT ");
        String query = "Goku";
        if ("Nada".equals(tipoAgrupamiento)) {
            System.out.println("Erase una vez un pollo que se llamaba juan");
            sqlBuilder.append("c2.ID AS id_consumo, u.ID, u.NOMBRE, u.APELLIDO ");
        } else if ("Fecha".equals(tipoAgrupamiento)) {
            sqlBuilder.append("c2.FECHA, u.NOMBRE, COUNT(c2.ID) AS numero_consumo ");
            query = " GROUP BY c2.FECHA, u.NOMBRE";
        } else if ("Fecha y Cliente".equals(tipoAgrupamiento)) {
            query = " GROUP BY c2.FECHA, p2.SERVICIO_ID, u.ID, u.NOMBRE, u.APELLIDO";
            sqlBuilder.append(
                    "c2.FECHA, COUNT(c2.ID) AS numero_consumo, p2.SERVICIO_ID as id_servicio, u.ID, u.NOMBRE, u.APELLIDO ");
        } else if ("Cliente".equals(tipoAgrupamiento)) {
            sqlBuilder.append("u.ID, u.NOMBRE, u.APELLIDO, COUNT(c2.ID) AS numero_consumo ");
            query = " GROUP BY u.ID, u.NOMBRE, u.APELLIDO";

        } else if ("Frecuencia de Consumo".equals(tipoAgrupamiento)) {
            sqlBuilder.append("frecuencia_consumo, COUNT(*) AS numero_usuarios ");
            query = " GROUP BY FrecuenciaConsumo";
        }
        sqlBuilder.append("FROM (SELECT * FROM CUENTA WHERE FECHA > ? AND FECHA < ?) c2 ");
        sqlBuilder.append("INNER JOIN (SELECT * FROM PRODUCTO WHERE SERVICIO_ID = ?) p2 ON c2.PRODUCTO_ID = p2.ID ");
        sqlBuilder.append("INNER JOIN RESERVA_CLIENTE rc ON rc.RESERVA_ID = c2.RESERVA_ID ");
        sqlBuilder.append("INNER JOIN USUARIO u ON rc.USUARIO_ID = u.ID ");
        if (!"Nada".equals(tipoAgrupamiento)) {
            sqlBuilder.append(query);
        }
        // Crear un objeto Object array con los parámetros
        Object[] params = new Object[] { fechaInicio, fechaFin, servicioId };
        return jdbcTemplate.query(sqlBuilder.toString(), new ResultSetExtractor<List<SuperObjeto>>() {
            @Override
            public List<SuperObjeto> extractData(ResultSet rs) throws SQLException {
                List<SuperObjeto> list = new ArrayList<SuperObjeto>();

                while (rs.next()) {
                    SuperObjeto superObjeto = new SuperObjeto();
                    if ("Nada".equals(tipoAgrupamiento)) {
                        superObjeto.setId_consumo((rs.getLong("id_consumo")));
                        superObjeto.setId_usuario((rs.getLong("ID")));
                        superObjeto.setNombre_usuario(rs.getString("NOMBRE"));
                        superObjeto.setApellido_usuario(rs.getString("APELLIDO"));
                    } else if ("Fecha".equals(tipoAgrupamiento)) {
                        superObjeto.setFecha(rs.getDate("FECHA"));
                        superObjeto.setNombre_usuario(rs.getString("NOMBRE"));
                        superObjeto.setNum_consumos(rs.getInt("numero_consumo"));
                    } else if ("Fecha y Cliente".equals(tipoAgrupamiento)) {
                        superObjeto.setFecha(rs.getDate("FECHA"));
                        superObjeto.setNum_consumos(rs.getInt("numero_consumo"));
                        superObjeto.setId_servicio(rs.getLong("id_servicio"));
                        superObjeto.setId_usuario((rs.getLong("ID")));
                        superObjeto.setNombre_usuario(rs.getString("NOMBRE"));
                        superObjeto.setApellido_usuario(rs.getString("APELLIDO"));
                    } else if ("Cliente".equals(tipoAgrupamiento)) {
                        superObjeto.setId_usuario(rs.getLong("ID"));
                        superObjeto.setNombre_usuario(rs.getString("NOMBRE"));
                        superObjeto.setApellido_usuario(rs.getString("APELLIDO"));
                        superObjeto.setNum_consumos(rs.getInt("numero_consumo"));
                    } else if ("Frecuencia de Consumo".equals(tipoAgrupamiento)) {
                        superObjeto.setFrecuencia_consumo(rs.getInt("frecuencia_consumo"));
                        superObjeto.setNumero_usuarios(rs.getInt("numero_usuarios"));
                    }

                    list.add(superObjeto);
                }

                return list;
            }
        }, params);
    }

    // Req funcional 10, está en servicio controller, usa una query dinamica
    public List<SuperObjeto> darNoConsumosServiciosUsuario(LocalDate fechaInicio, LocalDate fechaFin,
            Integer servicioId, String tipoAgrupamiento) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("SELECT ");

        // Agregar la parte SELECT dependiendo del tipo de agrupamiento
        if ("Nada".equals(tipoAgrupamiento)) {
            sqlBuilder.append("c2.ID AS ID_CONSUMO, p2.SERVICIO_ID as ID_SERVICIO,u.ID, u.NOMBRE, u.APELLIDO");
        } else if ("Fecha".equals(tipoAgrupamiento)) {
            sqlBuilder.append("c2.FECHA, COUNT(c2.ID) AS NumeroConsumo");
        } else if ("Frecuencia de Consumo".equals(tipoAgrupamiento)) {
            sqlBuilder.append("FrecuenciaConsumo, COUNT(*) AS NumeroUsuarios");
        } else if ("Cliente".equals(tipoAgrupamiento)) {
            sqlBuilder.append("COUNT(c2.ID) AS TOTAL_CONSUMOs,u.ID, u.NOMBRE, u.APELLIDO");
        } else if ("Fecha y Cliente".equals(tipoAgrupamiento)) {
            sqlBuilder.append(
                    "c2.FECHA, COUNT(c2.ID) AS NumeroConsumo, p2.SERVICIO_ID as ID_SERVICIO, u.ID, u.NOMBRE, u.APELLIDO");
        }

        sqlBuilder.append(" FROM (SELECT * FROM CUENTA WHERE FECHA > ? AND FECHA < ?) c2");
        sqlBuilder.append(" INNER JOIN (SELECT * FROM PRODUCTO WHERE SERVICIO_ID != ?) p2 ON c2.PRODUCTO_ID = p2.ID");
        sqlBuilder.append(" INNER JOIN RESERVA_CLIENTE rc ON rc.RESERVA_ID = c2.RESERVA_ID");
        sqlBuilder.append(" INNER JOIN USUARIO u ON rc.USUARIO_ID = u.ID");

        if ("Fecha".equals(tipoAgrupamiento) || "Frecuencia de Consumo".equals(tipoAgrupamiento)
                || "Fecha y Cliente".equals(tipoAgrupamiento)
                || "Cliente".equals(tipoAgrupamiento)) {
            sqlBuilder.append(" GROUP BY ");
            if ("Fecha".equals(tipoAgrupamiento)) {
                sqlBuilder.append("c2.FECHA");
            } else if ("Frecuencia de Consumo".equals(tipoAgrupamiento)) {
                sqlBuilder.append("FrecuenciaConsumo");
            } else if ("Fecha y Cliente".equals(tipoAgrupamiento)) {
                sqlBuilder.append("c2.FECHA, p2.SERVICIO_ID, u.ID, u.NOMBRE, u.APELLIDO");
            } else if ("Cliente".equals(tipoAgrupamiento)) {
                sqlBuilder.append("u.ID, u.NOMBRE, u.APELLIDO");
            }
        }

        Object[] params = new Object[] { fechaInicio, fechaFin, servicioId };
        return jdbcTemplate.query(sqlBuilder.toString(), new ResultSetExtractor<List<SuperObjeto>>() {
            @Override
            public List<SuperObjeto> extractData(ResultSet rs) throws SQLException {
                List<SuperObjeto> list = new ArrayList<SuperObjeto>();

                while (rs.next()) {
                    SuperObjeto superObjeto = new SuperObjeto();
                    if ("Nada".equals(tipoAgrupamiento)) {
                        superObjeto.setId_consumo((rs.getLong("id_consumo")));
                        superObjeto.setId_servicio((rs.getLong("ID_SERVICIO")));
                        superObjeto.setId_usuario((rs.getLong("ID")));
                        superObjeto.setNombre_usuario(rs.getString("NOMBRE"));
                        superObjeto.setApellido_usuario(rs.getString("APELLIDO"));
                    } else if ("Fecha".equals(tipoAgrupamiento)) {
                        superObjeto.setFecha(rs.getDate("FECHA"));

                        superObjeto.setNum_consumos(rs.getInt("NumeroConsumo"));
                    } else if ("Fecha y Cliente".equals(tipoAgrupamiento)) {
                        superObjeto.setFecha(rs.getDate("FECHA"));
                        superObjeto.setNum_consumos(rs.getInt("NumeroConsumo"));
                        superObjeto.setId_servicio(rs.getLong("id_servicio"));
                        superObjeto.setId_usuario((rs.getLong("ID")));
                        superObjeto.setNombre_usuario(rs.getString("NOMBRE"));
                        superObjeto.setApellido_usuario(rs.getString("APELLIDO"));
                    } else if ("Cliente".equals(tipoAgrupamiento)) {
                        superObjeto.setNum_consumos(rs.getInt("TOTAL_CONSUMOS"));
                        superObjeto.setId_usuario((rs.getLong("ID")));
                        superObjeto.setNombre_usuario(rs.getString("NOMBRE"));
                        superObjeto.setApellido_usuario(rs.getString("APELLIDO"));
                    } else if ("Frecuencia de Consumo".equals(tipoAgrupamiento)) {
                        superObjeto.setFrecuencia_consumo(rs.getInt("frecuencia_consumo"));
                        superObjeto.setNumero_usuarios(rs.getInt("numero_usuarios"));
                    }

                    list.add(superObjeto);
                }

                return list;
            }
        }, params);
    }

    // REQ FUNC-11: x.java

    // REQ FUNC-12: y.java
}
