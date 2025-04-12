package pds.hispania360.modelo;

import java.util.List;
import java.util.Random;

import jakarta.persistence.Embeddable;
import pds.hispania360.modelo.ejercicios.Ejercicio;

@Embeddable
public class EstrategiaRandom implements EstrategiaAprendizaje {

    private Random random = new Random();

    @Override
    public Ejercicio siguienteEjercicio(List<Ejercicio> ejercicios, int progreso) {
        // Añadido: retorno de null si progreso excede el tamaño de ejercicios
        if (progreso >= ejercicios.size()) {
            return null;
        }
        if (!ejercicios.isEmpty()) {
            int index = random.nextInt(ejercicios.size());
            return ejercicios.get(index);
        }
        return null;
    }

}
