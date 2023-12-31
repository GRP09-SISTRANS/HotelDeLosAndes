package uniandes.sistrans.HotelDeLosAndes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uniandes.sistrans.HotelDeLosAndes.models.LavanderiaEntity;


@Repository
public interface LavanderiaRepository extends JpaRepository<LavanderiaEntity, Long> {

}