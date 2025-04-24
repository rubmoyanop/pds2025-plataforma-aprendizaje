package pds.hispania360.modelo;

import java.util.List;

import jakarta.persistence.Embeddable;
import pds.hispania360.modelo.ejercicios.Ejercicio;

@Embeddable
public class EstrategiaRepeticionEspaciada implements EstrategiaAprendizaje {

    @Override
    public Ejercicio siguienteEjercicio(List<Ejercicio> ejercicios, int progreso) {
        // Repite la misma pregunta cada 3 , podríamos añadir un atributo que defina cada cuanto se repite.
        if (progreso > 0 && progreso % 3 == 0) {
            return ejercicios.get(Math.max(progreso - 3, 0)); // Repite una pregunta previa
        } else if (progreso < ejercicios.size()) {
            return ejercicios.get(progreso);
        }
        return null;
    }

}
