package pds.hispania360.modelo;

import java.util.List;

import jakarta.persistence.Embeddable;
import pds.hispania360.modelo.ejercicios.Ejercicio;

@Embeddable
public class EstrategiaRepeticionEspaciada implements EstrategiaAprendizaje {

    @Override
    public Ejercicio siguienteEjercicio(List<Ejercicio> ejercicios, int progreso) {
        // Usamos bloques de 4 (3 ejercicios nuevos y 1 repetición)
        // La secuencia esperada: nuevos: indices 0,1,2; repetición: ejercicio en índice 0;
        // nuevos: indices 3,4,5; repetición: ejercicio en índice 1; etc.
        int block = progreso / 4;
        int rem = progreso % 4;
        if (rem == 3) {
            // Repetir el ejercicio correspondiente al bloque
            if (block < ejercicios.size()) {
                return ejercicios.get(block);
            }
        } else {
            int newIndex = block * 3 + rem;
            if (newIndex < ejercicios.size()) {
                return ejercicios.get(newIndex);
            }
        }
        return null;
    }

}
