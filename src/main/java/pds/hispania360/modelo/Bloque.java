package pds.hispania360.modelo;

import java.util.ArrayList;

public class Bloque {
    private final int id;
    private String titulo;
    private String descripcion;
    private ArrayList<Ejercicio> ejercicios;

    public Bloque(int id, String titulo, String descripcion, ArrayList<Ejercicio> ejercicios) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.ejercicios = ejercicios;
    }

    public int getId() {
        return this.id;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ArrayList<Ejercicio> getEjercicios() {
        return this.ejercicios;
    }

    public void setEjercicios(ArrayList<Ejercicio> ejercicios) {
        this.ejercicios = ejercicios;
    }

}
