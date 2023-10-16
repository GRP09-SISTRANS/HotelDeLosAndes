package uniandes.sistrans.HotelDeLosAndes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;


@Entity
@Table(name = "internet")
@PrimaryKeyJoinColumn(name="servicio_id")
public class InternetEntity extends ServicioEntity{
    public InternetEntity (String nombre, String tipo){
        super(nombre,tipo);
    }

    public InternetEntity() {
        super();
    }
}
