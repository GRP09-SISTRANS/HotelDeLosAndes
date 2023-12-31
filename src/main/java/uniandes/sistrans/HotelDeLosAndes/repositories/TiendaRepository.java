package uniandes.sistrans.HotelDeLosAndes.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uniandes.sistrans.HotelDeLosAndes.models.TiendaEntity;


@Repository
public interface TiendaRepository extends JpaRepository<TiendaEntity, Long> {
    
}
