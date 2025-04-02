package pds.hispania360.modelo.ejercicios;

public interface Ejercicio {
    String getTipo();
    String getEnunciado();
    boolean validarRespuesta(String respuesta);
}