package pds.hispania360.modelo.ejercicios;

public class RellenarHueco implements Ejercicio {
    private String enunciado;
    private String respuestaCorrecta;

    public RellenarHueco(String enunciado, String respuestaCorrecta) {
        this.enunciado = enunciado;
        this.respuestaCorrecta = respuestaCorrecta;
    }

    @Override
    public String getTipo() {
        return "rellenar_hueco";
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
}