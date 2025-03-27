package pds.hispania360.modelo;

public abstract class Ejercicio {
    private final int id;
    private String enunciado;


    public Ejercicio(int id, String enunciado) {
        this.id = id;
        this.enunciado = enunciado;
    }

    public int getId() {
        return this.id;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getEnunciado() {
        return enunciado;
    }


}
