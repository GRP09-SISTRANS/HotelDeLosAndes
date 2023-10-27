package uniandes.sistrans.HotelDeLosAndes.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.sistrans.HotelDeLosAndes.models.Habitacion;
import uniandes.sistrans.HotelDeLosAndes.models.Tipos_Habitacion;


public interface HabitacionRepository extends JpaRepository<Habitacion, Integer>{
    @Query(value = "SELECT * FROM habitacion", nativeQuery = true)
    Collection<Habitacion> darHabitaciones();

    @Query(value = "SELECT * FROM Habitacion WHERE id = :id", nativeQuery = true)
    Habitacion darHabitacion(@Param("id") Integer id);

    @Query(value = "SELECT * FROM Habitacion WHERE capacidad = :capacidad", nativeQuery = true)
    Collection<Habitacion> darHabitacionesPorCapacidad(@Param("capacidad") Integer capacidad);

    @Query(value = "SELECT * FROM Habitacion WHERE tipos_habitacion_id = :tipo", nativeQuery = true)
    Collection<Habitacion> darHabitacionesPorTipo(@Param("tipo") Tipos_Habitacion tipo);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Habitacion (id, capacidad, tipos_habitacion_id) VALUES (:id, :capacidad, :tipo)", nativeQuery = true)
    void insertarHabitacion(@Param("id") Integer id,@Param("capacidad") Integer capacidad, @Param("tipo") Tipos_Habitacion tipo);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Habitacion SET capacidad=:capacidad, tipos_habitacion_id=:tipo WHERE id = :id", nativeQuery = true)
    void actualizarHabitacion(@Param("id") Integer id,@Param("capacidad") Integer capacidad, @Param("tipo") Tipos_Habitacion tipo);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Habitacion WHERE id = :id", nativeQuery = true)
    void eliminarHabitacion(@Param("id") Integer id);    
}
