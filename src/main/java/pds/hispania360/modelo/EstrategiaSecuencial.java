package pds.hispania360.modelo;

import java.util.List;

import jakarta.persistence.Embeddable;
import pds.hispania360.modelo.ejercicios.Ejercicio;

@Embeddable
public class EstrategiaSecuencial implements EstrategiaAprendizaje {

    @Override
    public Ejercicio siguienteEjercicio(List<Ejercicio> ejercicios, int progreso) {
        if (progreso < ejercicios.size()) {
            return ejercicios.get(progreso);
        }
        return null; // No hay más preguntas
    }

}
