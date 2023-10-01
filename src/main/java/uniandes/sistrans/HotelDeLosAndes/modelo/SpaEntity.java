package uniandes.sistrans.HotelDeLosAndes.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "spa")
@PrimaryKeyJoinColumn(name="servicio_id")
public class SpaEntity extends ServicioEntity {
    public SpaEntity (String nombre, String tipo){
        super(nombre,tipo);
    }
}
