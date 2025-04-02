package pds.hispania360.modelo.ejercicios;

import java.util.List;

public class RespuestaMultiple implements Ejercicio {
    private String enunciado;
    private List<String> opciones;
    private String respuestaCorrecta;

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
        if (!(respuesta instanceof String)) return false;
        return respuestaCorrecta.equals(respuesta);
    }

    public List<String> getOpciones() {
        return opciones;
    }
}