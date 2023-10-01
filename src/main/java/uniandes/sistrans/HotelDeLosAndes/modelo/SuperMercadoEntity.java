package uniandes.sistrans.HotelDeLosAndes.modelo;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "supermercado")
@PrimaryKeyJoinColumn(name="servicio_id")
public class SuperMercadoEntity extends ServicioEntity {
    public SuperMercadoEntity (String nombre, String tipo){
        super(nombre,tipo);
    }
    
}
