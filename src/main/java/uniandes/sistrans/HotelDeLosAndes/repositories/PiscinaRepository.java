package uniandes.sistrans.HotelDeLosAndes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uniandes.sistrans.HotelDeLosAndes.models.PiscinaEntity;


@Repository
public interface PiscinaRepository extends JpaRepository<PiscinaEntity, Long> {

}