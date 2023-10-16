package uniandes.sistrans.HotelDeLosAndes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uniandes.sistrans.HotelDeLosAndes.model.InternetEntity;


@Repository
public interface InternetRepository extends JpaRepository<InternetEntity, Long> {

}
