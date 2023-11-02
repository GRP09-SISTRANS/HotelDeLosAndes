package uniandes.sistrans.HotelDeLosAndes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import uniandes.sistrans.HotelDeLosAndes.models.CuentaEntity;

@Repository
public interface CuentaRepository extends JpaRepository<CuentaEntity, Long> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO cuenta (id, producto_id, cantidad, reserva_id) VALUES (40, :producto_id, :cantidad, :reserva_id)", nativeQuery = true)
    void insertarCuenta(
        @Param("producto_id") Long producto_id,
        @Param("cantidad") Integer cantidad,
        @Param("reserva_id") Integer reserva_id
    );

    @Modifying
    @Transactional
    @Query(value = "UPDATE cuenta SET producto_id = :producto_id, cantidad = :cantidad, reserva_id = :reserva_id WHERE id = :id", nativeQuery = true)
    void actualizarCuenta(  
        @Param("id") Long id,
        @Param("producto_id") Long producto_id,
        @Param("cantidad") Integer cantidad,
        @Param("reserva_id") Integer reserva_id
    );
}
