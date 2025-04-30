package pds.hispania360.modelo.ejercicios;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import pds.hispania360.vista.core.TipoVentana;

@Entity
@DiscriminatorValue("flashcard")
public class Flashcard extends Ejercicio {

    @Column(name = "frente")
    private String frente;
    @Column(name = "detras")
    private String detras;

    public Flashcard() {
        this.frente = null;
        this.detras = null;
    }

    public Flashcard(String frente, String detras) {
        this.frente = frente;
        this.detras = detras;
    }

    @Override
    public String getTipo() {
        return "flashcard";
    }

    @Override
    public String getEnunciado() {
        return frente;
    }

    public String getDetras() {
        return detras;
    }

    @Override
    public boolean validarRespuesta(String respuesta) {
        if (detras == null || respuesta == null) return false;
        return detras.equalsIgnoreCase(respuesta);
    }

    @Override
    public TipoVentana getTipoVentana() {
        return TipoVentana.FLASHCARD;
    }

    public String getFrente() {
        return frente;
    }

    public void setFrente(String frente) {
        this.frente = frente;
    }

    public void setDetras(String detras) {
        this.detras = detras;
    }
}