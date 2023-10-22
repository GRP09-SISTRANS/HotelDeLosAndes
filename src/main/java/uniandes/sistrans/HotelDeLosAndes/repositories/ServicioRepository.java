package uniandes.sistrans.HotelDeLosAndes.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import uniandes.sistrans.HotelDeLosAndes.models.ServicioEntity;


@Repository
public interface ServicioRepository extends JpaRepository<ServicioEntity, Long> {
    @Query(value = "SELECT * FROM servicios", nativeQuery = true)
    Collection<ServicioEntity> darServicios();
}