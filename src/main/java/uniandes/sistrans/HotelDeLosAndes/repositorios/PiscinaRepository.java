package uniandes.sistrans.HotelDeLosAndes.repositorios;
import uniandes.sistrans.HotelDeLosAndes.modelo.PiscinaEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PiscinaRepository extends JpaRepository<PiscinaEntity, Long> {
    // métodos de consulta personalizados si los necesitas
}