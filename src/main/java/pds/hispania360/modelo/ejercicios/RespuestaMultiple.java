package pds.hispania360.modelo.ejercicios;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.DiscriminatorValue;

import pds.hispania360.vista.core.TipoVentana;

@Entity
@DiscriminatorValue("respuesta_multiple")
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
        return respuestaCorrecta != null && respuestaCorrecta.equalsIgnoreCase(respuesta);
    }

    @Override
    public TipoVentana getTipoVentana() {
        return TipoVentana.RESPUESTA_MULTIPLE;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public void setOpciones(List<String> opciones) {
        this.opciones = opciones;
    }

    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public void setRespuestaCorrecta(String respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public List<String> getOpciones() {
        return opciones;
    }
}