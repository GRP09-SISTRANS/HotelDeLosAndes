package uniandes.sistrans.HotelDeLosAndes.repositorios;

import uniandes.sistrans.HotelDeLosAndes.modelo.SalonConferenciaEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SalonConferenciaRepository extends JpaRepository<SalonConferenciaEntity, Long> {
    
}