package uniandes.sistrans.HotelDeLosAndes.modelo;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "prestamo_utensilios")
@PrimaryKeyJoinColumn(name="servicio_id")
public class PrestamoUtensiliosEntity extends ServicioEntity{
    public PrestamoUtensiliosEntity (String nombre, String tipo){
        super(nombre,tipo);
    }
    public PrestamoUtensiliosEntity() {
        super();
        
    }
}
