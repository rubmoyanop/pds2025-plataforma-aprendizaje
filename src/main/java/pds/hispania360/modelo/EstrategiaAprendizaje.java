package pds.hispania360.modelo;


// patrón Strategy
public interface EstrategiaAprendizaje {
    Ejercicio siguientePregunta(List<Ejercicio> ejercicios, int progreso);
}
