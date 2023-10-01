package uniandes.sistrans.HotelDeLosAndes.repositorios;
import uniandes.sistrans.HotelDeLosAndes.modelo.TiendaEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TiendaRepository extends JpaRepository<TiendaEntity, Long> {
    
}
