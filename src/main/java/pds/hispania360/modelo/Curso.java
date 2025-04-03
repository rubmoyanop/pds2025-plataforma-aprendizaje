package pds.hispania360.modelo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Curso {
    private final int id;
    private String titulo;
    private String descripcion;
    private Usuario creador;
    private ArrayList<Bloque> bloques;
    private LocalDate fechaCreacion;

    public Curso(int id, String titulo, String descripcion, Usuario creador, ArrayList<Bloque> bloques, LocalDate fechaCreacion) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.creador = creador;
        this.bloques = bloques;
        this.fechaCreacion = fechaCreacion;
    }

    public Curso(int id){
        this(id,null,null,null,null,null);
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

    public Usuario getCreador() {
        return this.creador;
    }

    public void setCreador(Usuario creador) {
        this.creador = creador;
    }

    public ArrayList<Bloque> getBloques() {
        return this.bloques;
    }

    public void setBloques(ArrayList<Bloque> bloques) {
        this.bloques = bloques;
    }

    public LocalDate getFechaCreacion() {
        return this.fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}