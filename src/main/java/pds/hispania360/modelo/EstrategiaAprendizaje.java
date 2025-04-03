package pds.hispania360.modelo;

import java.util.List;

import pds.hispania360.modelo.ejercicios.Ejercicio;

// patrón Strategy
public interface EstrategiaAprendizaje {
    Ejercicio siguienteEjercicio(List<Ejercicio> ejercicios, int progreso);
}
