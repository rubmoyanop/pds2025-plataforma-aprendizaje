package pds.hispania360.modelo.ejercicios;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class RellenarHueco extends Ejercicio {
    

    @Column(name = "enunciado")
    private String enunciado;
    @Column (name = "respuestaCorrecta")
    private String respuestaCorrecta;

    public RellenarHueco() {
        this.enunciado = null;
        this.respuestaCorrecta = null;
    }

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
        return respuestaCorrecta.equals(respuesta);
    }
}