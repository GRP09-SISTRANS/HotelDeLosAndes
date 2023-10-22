package uniandes.sistrans.HotelDeLosAndes.models;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;


@Entity
@Table(name = "lavanderia")
@PrimaryKeyJoinColumn(name="servicio_id")
public class LavanderiaEntity extends ServicioEntity {
    public LavanderiaEntity (String nombre, String tipo){
        super(nombre,tipo);
    }

    public LavanderiaEntity() {
        super();
    }
}
