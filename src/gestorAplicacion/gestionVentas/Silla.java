package gestorAplicacion.gestionVentas;

import gestorAplicacion.herramientas.Asiento;

public class Silla {

    private Asiento tipo;
    private Boolean disponibilidad;
    private Integer codigo;

    //TIPO
    public Asiento getTipo() {
        return tipo;
    }
    public void setTipo(Asiento tipo) {
        this.tipo = tipo;
    }

    //DISPONIVILIDAD
    public Boolean getDisponibilidad() {
        return disponibilidad;
    }
    public void setDisponivilidad(Boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    //CODIGO
    public Integer getCodigo() {
        return codigo;
    }
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    
    
    
}
