package pds.hispania360.modelo;

public abstract class Ejercicio {
    private long id;
    private String enunciado;


    public Ejercicio(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getEnunciado() {
        return enunciado;
    }


}
