package uniandes.sistrans.HotelDeLosAndes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uniandes.sistrans.HotelDeLosAndes.model.GimnasioEntity;


@Repository
public interface GimnasioRepository extends JpaRepository<GimnasioEntity, Long> {

}