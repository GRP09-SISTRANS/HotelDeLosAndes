package uniandes.sistrans.HotelDeLosAndes.modelo;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "producto")
public class ProductoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    private String nombre;
    
    private Integer costo;
    
    @ManyToOne
    @JoinColumn(name="servicio_id")
    private ServicioEntity servicio;

    @OneToMany(mappedBy = "producto")
    private List<CuentaEntity> cuentas;
}


