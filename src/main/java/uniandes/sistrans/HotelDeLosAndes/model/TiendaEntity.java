package uniandes.sistrans.HotelDeLosAndes.model;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;


@Entity
@Table(name = "tienda")
@PrimaryKeyJoinColumn(name="servicio_id")
public class TiendaEntity extends ServicioEntity{
    public TiendaEntity (String nombre, String tipo){
        super(nombre,tipo);
    }

    public TiendaEntity() {
        super();
    }
}
