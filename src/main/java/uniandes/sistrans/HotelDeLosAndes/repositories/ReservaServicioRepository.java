package uniandes.sistrans.HotelDeLosAndes.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import uniandes.sistrans.HotelDeLosAndes.models.ReservaServicio;


@Repository
public interface ReservaServicioRepository extends JpaRepository<ReservaServicio, Integer> {
    @Query(value = "SELECT * FROM reserva_servicio", nativeQuery = true)
    Collection<ReservaServicio> darReservasServicios();
}
