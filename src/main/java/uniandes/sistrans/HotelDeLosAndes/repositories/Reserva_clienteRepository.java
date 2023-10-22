package uniandes.sistrans.HotelDeLosAndes.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.sistrans.HotelDeLosAndes.models.Reserva_cliente;

public interface Reserva_clienteRepository extends JpaRepository<Reserva_cliente, Integer>{
    @Query(value = "SELECT * FROM Reserva_cliente", nativeQuery = true)
    Collection<Reserva_cliente> darReservas_cliente();

    @Query(value = "SELECT * FROM Reserva_cliente WHERE reserva_id = :id AND usuario_id = :id_usuario", nativeQuery = true)
    Collection<Reserva_cliente> darReservas_clientePorId(@Param("id")Integer id, @Param("id_usuario")Integer id_usuario);

    @Query(value = "SELECT * FROM Reserva_cliente WHERE reserva_id = :id", nativeQuery = true)
    Collection<Reserva_cliente> darReservas_clientePorReserva(@Param("id")Integer id);

    @Query(value = "SELECT * FROM Reserva_cliente WHERE usuario_id = :id", nativeQuery = true)
    Collection<Reserva_cliente> darReservas_clientePorUsuario(@Param("id")Integer id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Reserva_cliente (reserva_id, usuario_id, habitacion_id, plan_consumo_id) VALUES (:reserva_id, :usuario_id, :habitacion_id, :plan_consumo_id)", nativeQuery = true)
    void insertarReserva_cliente(@Param("reserva_id")Integer reserva_id, @Param("usuario_id")Integer usuario_id, @Param("habitacion_id")Integer habitacion_id, @Param("plan_consumo_id")Integer plan_consumo_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Reserva_cliente SET reserva_id=:reservaId, habitacion_id=:habitacionId, plan_consumo_id=:planConsumoId WHERE reserva_id = :reservaId AND usuario_id = :usuarioId", nativeQuery = true)
    void actualizarReserva_cliente(@Param("usuarioId") Integer usuarioId, @Param("reservaId") Integer reservaId, @Param("habitacionId") Integer habitacionId, @Param("planConsumoId") Integer planConsumoId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Reserva_cliente WHERE reserva_id = :reserva_id AND usuario_id = :usuario_id", nativeQuery = true)
    void eliminarReserva_cliente(@Param("reserva_id")Integer reserva_id, @Param("usuario_id")Integer usuario_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Reserva_cliente WHERE reserva_id = :reserva_id", nativeQuery = true)
    void eliminarReserva_clientePorReserva(@Param("reserva_id")Integer reserva_id);
}
