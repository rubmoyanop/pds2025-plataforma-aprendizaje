package pds.hispania360.modelo.ejercicios;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;

@Entity
public class RespuestaMultiple extends Ejercicio {
    
    @Column(name = "enunciado")
    private String enunciado;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> opciones;
    @Column(name = "respuestaCorrecta")
    private String respuestaCorrecta;

    public RespuestaMultiple() {
        this.enunciado = null;
        this.opciones = new ArrayList<String>();
        this.respuestaCorrecta = null;
    }

    public RespuestaMultiple(String enunciado, List<String> opciones, String respuestaCorrecta) {
        this.enunciado = enunciado;
        this.opciones = opciones;
        this.respuestaCorrecta = respuestaCorrecta;
    }

    @Override
    public String getTipo() {
        return "respuesta_multiple";
    }

    @Override
    public String getEnunciado() {
        return enunciado;
    }

    @Override
    public boolean validarRespuesta(String respuesta) {
        return respuestaCorrecta.equals(respuesta);
    }

    public List<String> getOpciones() {
        return opciones;
    }
}