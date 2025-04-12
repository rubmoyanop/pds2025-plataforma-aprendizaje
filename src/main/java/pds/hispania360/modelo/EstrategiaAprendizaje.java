package pds.hispania360.modelo;

import java.util.List;

import jakarta.persistence.Embeddable;
import pds.hispania360.modelo.ejercicios.Ejercicio;

// patrón Strategy
@Embeddable
public interface EstrategiaAprendizaje {
    Ejercicio siguienteEjercicio(List<Ejercicio> ejercicios, int progreso);
}
