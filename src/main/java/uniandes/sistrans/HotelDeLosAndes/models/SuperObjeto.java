package uniandes.sistrans.HotelDeLosAndes.models;

import java.util.Date;
import java.time.LocalDate;

public class SuperObjeto {
    private String nombre;
    private Long id_habitacion;
    private Double dinero_recolectado;
    private Integer num_consumos;
    private Double indice_ocupacion;
    private String nombre_usuario;
    private String nombre_producto;
    private Integer costo;
    private Date fecha;
    private Integer ocupacion;
    private Double ingresos;
    private Integer duracion_total;
    private Double consumo_total;
    private Long id_consumo;
    private Long id_usuario;
    private String apellido_usuario;
    private Long id_servicio;
    private Integer frecuencia_consumo;
    private Integer numero_usuarios;
    private String tipo_agrupamiento;

    
    

    
    
    public Integer getFrecuencia_consumo() {
        return frecuencia_consumo;
    }

    public void setFrecuencia_consumo(Integer frecuencia_consumo) {
        this.frecuencia_consumo = frecuencia_consumo;
    }

    public Integer getNumero_usuarios() {
        return numero_usuarios;
    }

    public void setNumero_usuarios(Integer numero_usuarios) {
        this.numero_usuarios = numero_usuarios;
    }

    public Long getId_consumo() {
        return id_consumo;
    }

    public void setId_consumo(Long id_consumo) {
        this.id_consumo = id_consumo;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getApellido_usuario() {
        return apellido_usuario;
    }

    public void setApellido_usuario(String apellido_usuario) {
        this.apellido_usuario = apellido_usuario;
    }

    public Integer getDuracion_total() {
        return duracion_total;
    }

    public void setDuracion_total(Integer duracion_total) {
        this.duracion_total = duracion_total;
    }

    public Double getConsumo_total() {
        return consumo_total;
    }

    public void setConsumo_total(Double consumo_total) {
        this.consumo_total = consumo_total;
    }

    public SuperObjeto() {
        
    }

    public Integer getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(Integer ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getNombre() {
        return nombre;
    }

    public Long getId_habitacion() {
        return id_habitacion;
    }

    public Double getDinero_recolectado() {
        return dinero_recolectado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setId_habitacion(Long id_habitacion) {
        this.id_habitacion = id_habitacion;
    }

    public void setDinero_recolectado(Double dinero_recolectado) {
        this.dinero_recolectado = dinero_recolectado;
    }

    public Integer getNum_consumos() {
        return num_consumos;
    }

    public void setNum_consumos(Integer num_consumos) {
        this.num_consumos = num_consumos;
    }

    public Double getIndice_ocupacion() {
        return indice_ocupacion;
    }

    public void setIndice_ocupacion(Double indice_ocupacion) {
        this.indice_ocupacion = indice_ocupacion;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public Integer getCosto() {
        return costo;
    }

    public void setCosto(Integer costo) {
        this.costo = costo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getIngresos() {
        return ingresos;
    }

    public void setIngresos(Double ingresos) {
        this.ingresos = ingresos;
    }

    public Long getId_servicio() {
        return id_servicio;
    }

    public void setId_servicio(Long id_servicio) {
        this.id_servicio = id_servicio;
    }

    public String getTipo_agrupamiento() {
        return tipo_agrupamiento;
    }

    public void setTipo_agrupamiento(String tipo_agrupamiento) {
        this.tipo_agrupamiento = tipo_agrupamiento;
    }

    
    

    

    
    
}
