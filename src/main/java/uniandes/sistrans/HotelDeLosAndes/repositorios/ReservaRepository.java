package uniandes.sistrans.HotelDeLosAndes.repositorios;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.sistrans.HotelDeLosAndes.modelo.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {

    @Query(value = "SELECT * FROM Reserva WHERE id = :id AND Usuario_id = :Usuario_id", nativeQuery = true)
    Reserva darReserva(@Param("id") Integer id, @Param("Usuario_id") Integer Usuario_id);

    @Query(value = "SELECT * FROM Reserva WHERE id = :id", nativeQuery = true)
    Collection<Reserva> darReservasPorId(@Param("id") Integer id);

    @Query(value = "SELECT * FROM Reserva", nativeQuery = true)
    Collection<Reserva> darReservas();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Reserva (id, fecha_inicio, fecha_fin, costo, estado, Habitacion_id, Usuario_id) VALUES (:id, :fecha_inicio, :fecha_fin, :costo, :estado, :Habitacion_id, :Usuario_id)", nativeQuery = true)
    void insertarReserva(@Param("id") Integer id, @Param("fecha_inicio") String fecha_inicio, @Param("fecha_fin") String fecha_fin, @Param("costo") Integer costo, @Param("estado") String estado, @Param("Habitacion_id") Integer Habitacion_id, @Param("Usuario_id") Integer Usuario_id);


    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Reserva (id, fecha_inicio, fecha_fin, costo, estado, Habitacion_id, Usuario_id) VALUES (:fecha_inicio, :fecha_fin, :costo, :estado, :Habitacion_id, :Usuario_id)", nativeQuery = true)
    void insertarReserva(@Param("fecha_inicio") String fecha_inicio, @Param("fecha_fin") String fecha_fin, @Param("costo") Integer costo, @Param("estado") String estado, @Param("Habitacion_id") Integer Habitacion_id, @Param("Usuario_id") Integer Usuario_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Reserva SET fecha_inicio=:fecha_inicio, fecha_fin=:fecha_fin, costo=:costo, estado=:estado, Habitacion_id=:Habitacion_id, Usuario_id=:Usuario_id WHERE id = :id AND Usuario_id = :Usuario_id", nativeQuery = true)
    void actualizarReserva(@Param("id") Integer id, @Param("fecha_inicio") String fecha_inicio, @Param("fecha_fin") String fecha_fin, @Param("costo") Integer costo, @Param("estado") String estado, @Param("Habitacion_id") Integer Habitacion_id, @Param("Usuario_id") Integer Usuario_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Reserva WHERE id = :id", nativeQuery = true)
    void eliminarReservasPorId(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Reserva WHERE id = :id AND Usuario_id = :Usuario_id", nativeQuery = true)
    void eliminarReserva(@Param("id") Integer id, @Param("Usuario_id") Integer Usuario_id);
    
}
