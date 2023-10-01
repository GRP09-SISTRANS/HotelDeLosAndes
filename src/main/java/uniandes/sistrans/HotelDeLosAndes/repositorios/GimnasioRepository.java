package uniandes.sistrans.HotelDeLosAndes.repositorios;
import uniandes.sistrans.HotelDeLosAndes.modelo.GimnasioEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GimnasioRepository extends JpaRepository<GimnasioEntity, Long> {
    // métodos de consulta personalizados si los necesitas
}