package pds.hispania360.modelo;

public class EstrategiaRandom extends EstrategiaAprendizaje {

    private Random random = new Random();

    @Override
    public Ejercicio siguienteEjercicio(List<Ejercicio> ejercicios, int progreso) {
        if (!ejercicios.isEmpty()) {
            int index = random.nextInt(ejercicios.size());
            return ejercicios.get(index);
        }
        return null;
    }

}
