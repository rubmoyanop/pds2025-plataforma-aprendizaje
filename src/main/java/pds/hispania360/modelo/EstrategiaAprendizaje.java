package pds.hispania360.modelo;

import java.util.List;

// patrón Strategy
public interface EstrategiaAprendizaje {
    Ejercicio siguienteEjercicio(List<Ejercicio> ejercicios, int progreso);
}
