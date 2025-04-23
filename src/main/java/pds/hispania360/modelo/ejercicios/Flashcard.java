package pds.hispania360.modelo.ejercicios;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
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
        return detras.equals(respuesta);
    }
}