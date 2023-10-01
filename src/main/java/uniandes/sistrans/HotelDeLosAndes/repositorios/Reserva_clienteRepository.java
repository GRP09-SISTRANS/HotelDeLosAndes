package uniandes.sistrans.HotelDeLosAndes.repositorios;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.sistrans.HotelDeLosAndes.modelo.Reserva_cliente;

public interface Reserva_clienteRepository extends JpaRepository<Reserva_cliente, Integer>{

    @Query(value = "SELECT * FROM Reserva_cliente WHERE reserva_id = :id AND usuario_id = :id_usuario", nativeQuery = true)
    Collection<Reserva_cliente> darReservas_cliente(@Param("id")Integer id, @Param("id_usuario")Integer id_usuario);

    @Query(value = "SELECT * FROM Reserva_cliente WHERE reserva_id = :id", nativeQuery = true)
    Collection<Reserva_cliente> darReservas_clientePorReserva(@Param("id")Integer id);

    @Query(value = "SELECT * FROM Reserva_cliente WHERE usuario_id = :id", nativeQuery = true)
    Collection<Reserva_cliente> darReservas_clientePorUsuario(@Param("id")Integer id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Reserva_cliente (reserva_id, usuario_id) VALUES (:reserva_id, :usuario_id)", nativeQuery = true)
    void insertarReserva_cliente(@Param("reserva_id")Integer reserva_id, @Param("usuario_id")Integer usuario_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Reserva_cliente SET reserva_id=:reserva_id2, usuario_id=:usuario_id2 WHERE reserva_id = :reserva_id AND usuario_id = :usuario_id", nativeQuery = true)
    void actualizarReserva_cliente(@Param("reserva_id")Integer reserva_id, @Param("usuario_id")Integer usuario_id, @Param("reserva_id2")Integer reserva_id2, @Param("usuario_id2")Integer usuario_id2);
    
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Reserva_cliente WHERE reserva_id = :reserva_id AND usuario_id = :usuario_id", nativeQuery = true)
    void eliminarReserva_cliente(@Param("reserva_id")Integer reserva_id, @Param("usuario_id")Integer usuario_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Reserva_cliente WHERE reserva_id = :reserva_id", nativeQuery = true)
    void eliminarReserva_clientePorReserva(@Param("reserva_id")Integer reserva_id);


    
}

