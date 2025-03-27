package pds.hispania360.modelo; 
import java.util.List;

public class EjercicioRespuestaMultiple extends Ejercicio {
    private List<String> opciones;
    private int indiceRespuestaCorrecta;

    public EjercicioRespuestaMultiple(int id, String enunciado, List<String> opciones, int indiceRespuestaCorrecta) {
        super(id, enunciado);
        this.opciones = opciones;
        this.indiceRespuestaCorrecta = indiceRespuestaCorrecta;
    }


    public List<String> getOpciones() {
        return this.opciones;
    }

    public void setOpciones(List<String> opciones) {
        this.opciones = opciones;
    }

    public int getIndiceRespuestaCorrecta() {
        return this.indiceRespuestaCorrecta;
    }

    public void setIndiceRespuestaCorrecta(int indiceRespuestaCorrecta) {
        this.indiceRespuestaCorrecta = indiceRespuestaCorrecta;
    }


    public boolean comprobarRespuesta(int opcionSeleccionada) {
        return opcionSeleccionada == (indiceRespuestaCorrecta + 1);  // +1 por el índice humano
    }
}
