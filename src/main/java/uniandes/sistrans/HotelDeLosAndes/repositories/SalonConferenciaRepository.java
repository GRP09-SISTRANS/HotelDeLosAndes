package uniandes.sistrans.HotelDeLosAndes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uniandes.sistrans.HotelDeLosAndes.model.SalonConferenciaEntity;


@Repository
public interface SalonConferenciaRepository extends JpaRepository<SalonConferenciaEntity, Long> {
    
}