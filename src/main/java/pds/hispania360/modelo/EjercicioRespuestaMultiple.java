package pds.hispania360.modelo; 
import java.util.List;

public class EjercicioRespuestaMultiple extends Ejercicio {
    private List<String> opciones;
    private int indiceRespuestaCorrecta;

    public RespuestaMultiple(String enunciado, List<String> opciones, int indiceRespuestaCorrecta) {
        super(enunciado);
        this.opciones = opciones;
        this.indiceRespuestaCorrecta = indiceRespuestaCorrecta;
    }


    public boolean comprobarRespuesta(int opcionSeleccionada) {
        return opcionSeleccionada == (indiceRespuestaCorrecta + 1);  // +1 por el índice humano
    }
}
