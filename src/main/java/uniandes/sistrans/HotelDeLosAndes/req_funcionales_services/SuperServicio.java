package uniandes.sistrans.HotelDeLosAndes.req_funcionales_services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import uniandes.sistrans.HotelDeLosAndes.models.SuperObjeto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SuperServicio {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    


    // Req funcional 1, está en servicio controller
    public List<SuperObjeto> dineroRecolectado() {
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

    // Req funcional 2, está en servicio controller igualmente
    public List<SuperObjeto> darServiciosMasPopulares(LocalDate fechaInicio, LocalDate fechaFin) {
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


    // Req funcional 3, está en habitaciones controller
    public List<SuperObjeto> darIndicesOcupacion() {
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
    //Req funcional 5, está en usuario controller
    public List<SuperObjeto> consumosUsuario(LocalDate fechaInicio, LocalDate fechaFin, Integer usuarioId) {
    
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
    // Req funcional 6, está en cuentas controller
    public List<SuperObjeto> darFechaMayorOcupacion() {
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


    public List<SuperObjeto> darFechaMenorOcupacion() {
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

    public List<SuperObjeto> darFechaMayoresIngreso() {
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
    // Req funcional 7, está en clientes
    public List<SuperObjeto> darBuenosClientes() {
        String sql = "SELECT nombre, duracion_total, consumo_total\n" +
        "FROM (SELECT  us.nombre, SUM(re.fecha_final_reserva - re.fecha_inicio_reserva) AS duracion_total, SUM(pro.costo) AS consumo_total\n" +
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
                Integer duracion_total= rs.getInt("DURACION_TOTAL");
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

    // Req funcional 8, está en servicios controller
     public List<SuperObjeto> serviciosMenosDemanda() {
        String sql = "SELECT nombre, duracion_total, consumo_total\n" +
        "FROM (SELECT  us.nombre, SUM(re.fecha_final_reserva - re.fecha_inicio_reserva) AS duracion_total, SUM(pro.costo) AS consumo_total\n" +
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
                Integer duracion_total= rs.getInt("duracion_total");
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

    //Req funcional 9, está en servicio controller, usa una query dinamica
    public List<SuperObjeto> darConsumosServiciosUsuario(LocalDate fechaInicio, LocalDate fechaFin, Integer servicioId, String tipoAgrupamiento) 
    {   

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
            sqlBuilder.append("c2.FECHA, COUNT(c2.ID) AS numero_consumo, p2.SERVICIO_ID as id_servicio, u.ID, u.NOMBRE, u.APELLIDO ");
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
        if (!"Nada".equals(tipoAgrupamiento)){
            sqlBuilder.append(query);
        }
        // Crear un objeto Object array con los parámetros
        Object[] params = new Object[]{fechaInicio, fechaFin, servicioId};
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
    
}
