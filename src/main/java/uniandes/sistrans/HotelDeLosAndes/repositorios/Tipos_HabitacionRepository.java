package uniandes.sistrans.HotelDeLosAndes.repositorios;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.sistrans.HotelDeLosAndes.modelo.Tipos_Habitacion;


public interface Tipos_HabitacionRepository extends JpaRepository<Tipos_Habitacion, Integer>{
    @Query(value = "SELECT * FROM Tipos_Habitacion WHERE id = :id", nativeQuery = true)
    Tipos_Habitacion darTipos_Habitacion(@Param("id") Integer id);

    @Query(value = "SELECT * FROM Tipos_Habitacion", nativeQuery = true)
    Collection<Tipos_Habitacion> darTipos_Habitacion();

    @Query(value = "SELECT * FROM Tipos_Habitacion WHERE nombre = :nombre", nativeQuery = true)
    Collection<Tipos_Habitacion> darTipos_HabitacionPorNombre(String nombre);

    @Query(value = "SELECT id FROM Tipos_Habitacion WHERE nombre = :nombre", nativeQuery = true)
    Integer darIdTipos_HabitacionPorNombre(String nombre);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Tipos_Habitacion (id, nombre, costo, minibar, cafetera, television) VALUES (:id, :nombre, :costo, :minibar, :cafetera, :television)", nativeQuery = true)
    void insertarTipos_Habitacion(@Param("id") Integer id,@Param("nombre") String nombre,@Param("costo") Integer costo,@Param("minibar") boolean minibar,@Param("cafetera") boolean cafetera,@Param("television") boolean television);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Tipos_Habitacion SET nombre=:nombre, costo=:costo, minibar=:minibar, cafetera=:cafetera, television=:television WHERE id = :id", nativeQuery = true)
    void actualizarTipos_Habitacion(@Param("id") Integer id,@Param("nombre") String nombre,@Param("costo") Integer costo,@Param("minibar") boolean minibar,@Param("cafetera") boolean cafetera,@Param("television") boolean television);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Tipos_Habitacion WHERE id = :id", nativeQuery = true)
    void eliminarTipos_Habitacion(@Param("id") Integer id);
}
