package pds.hispania360.modelo.ejercicios;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import pds.hispania360.vista.core.TipoVentana; // Importar TipoVentana

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_ejercicio", discriminatorType = DiscriminatorType.STRING)
public abstract class Ejercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    public abstract String getTipo();
    public abstract String getEnunciado();
    public abstract boolean validarRespuesta(String respuesta);

    /**
     * Devuelve el tipo de ventana asociado a este ejercicio para la UI.
     * @return El TipoVentana correspondiente.
     */
    public abstract TipoVentana getTipoVentana();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}