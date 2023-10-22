package uniandes.sistrans.HotelDeLosAndes.repositories;

import java.util.Collection;
import java.sql.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.sistrans.HotelDeLosAndes.models.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
    @Query(value = "SELECT * FROM Reserva WHERE id = :id", nativeQuery = true)
    Reserva darReservaPorId(@Param("id") Integer id);

    @Query(value = "SELECT * FROM Reserva", nativeQuery = true)
    Collection<Reserva> darReservas();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Reserva (id, check_in, check_out fecha_inicio, fecha_fin) VALUES (:id, :check_in, :check_out, :fecha_inicio, :fecha_fin)", nativeQuery = true)
    void insertarReserva(@Param("id") Integer id, @Param("check_in") Date checkIn, @Param("check_out")  Date checkOut,  @Param("fecha_inicio") Date fecha_inicio, @Param("fecha_fin") Date fecha_fin);


    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Reserva (id, check_in, check_out, fecha_inicio, fecha_fin) VALUES (:check_in, :check_out, :fecha_inicio, :fecha_fin)", nativeQuery = true)
    void insertarReserva(@Param("check_in") Date checkIn, @Param("check_out") Date checkOut,  @Param("fecha_inicio") Date fecha_inicio, @Param("fecha_fin") Date fecha_fin);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Reserva SET check_in=:check_in, check_out=:check_out, fecha_inicio=:fecha_inicio, fecha_fin=:fecha_fin WHERE id = :id", nativeQuery = true)
    void actualizarReserva(@Param("id") Integer id, @Param("check_in") Date checkIn, @Param("check_out") Date checkOut,  @Param("fecha_inicio") Date fecha_inicio, @Param("fecha_fin") Date fecha_fin);


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Reserva WHERE id = :id", nativeQuery = true)
    void eliminarReservasPorId(@Param("id") Integer id);
}
