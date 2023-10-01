package uniandes.sistrans.HotelDeLosAndes.repositorios;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.sistrans.HotelDeLosAndes.modelo.Habitacion;


public interface HabitacionRepository extends JpaRepository<Habitacion, Integer>{

    @Query(value = "SELECT * FROM Habitacion", nativeQuery = true)
    Collection<Habitacion> darHabitaciones();

    @Query(value = "SELECT * FROM Habitacion WHERE id = :id", nativeQuery = true)
    Habitacion darHabitacion(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Habitacion (id, capacidad) VALUES (:id, :capacidad)", nativeQuery = true)
    void insertarHabitacion(@Param("id") Integer id,@Param("capacidad") Integer capacidad);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Habitacion SET capacidad=:capacidad WHERE id = :id", nativeQuery = true)
    void actualizarHabitacion(@Param("id") Integer id,@Param("capacidad") Integer capacidad);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Habitacion WHERE id = :id", nativeQuery = true)
    void eliminarHabitacion(@Param("id") Integer id);

    
}
    

