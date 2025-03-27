package pds.hispania360.modelo;

public class EstrategiaSecuencial extends EstrategiaAprendizaje {

    @Override
    public Ejercicio siguienteEjercicio(List<PregEjercicio> ejercicios, int progreso) {
        if (progreso < ejercicios.size()) {
            return ejercicios.get(progreso);
        }
        return null; // No hay más preguntas
    }

}
