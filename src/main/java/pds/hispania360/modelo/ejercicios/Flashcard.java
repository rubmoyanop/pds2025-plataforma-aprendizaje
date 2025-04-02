package pds.hispania360.modelo.ejercicios;

public class Flashcard implements Ejercicio {
    private String frente;
    private String detras;

    public Flashcard(String frente, String detras) {
        this.frente = frente;
        this.detras = detras;
    }

    @Override
    public String getTipo() {
        return "flashcard";
    }

    @Override
    public String getEnunciado() {
        return frente;
    }

    public String getDetras() {
        return detras;
    }

    @Override
    public boolean validarRespuesta(String respuesta) {
        if (detras == null || respuesta == null) return false;
        return detras.toString().equals(respuesta.toString());
    }
}