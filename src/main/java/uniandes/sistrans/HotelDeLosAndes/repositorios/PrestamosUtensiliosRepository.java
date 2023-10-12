package uniandes.sistrans.HotelDeLosAndes.repositorios;

import uniandes.sistrans.HotelDeLosAndes.modelo.PrestamoUtensiliosEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PrestamosUtensiliosRepository extends JpaRepository<PrestamoUtensiliosEntity, Long> {

}
