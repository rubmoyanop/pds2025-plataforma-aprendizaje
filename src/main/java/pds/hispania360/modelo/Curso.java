package pds.hispania360.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Curso {
    private int id;
    private String titulo;
    private String descripcion;
    private Usuario creador;
    private ArrayList<Bloque> bloques;
    private LocalDateTime fechaCreacion;

    public Curso(int id, String titulo, String descripcion, Usuario creador, ArrayList<Bloque> bloques, LocalDateTime fechaCreacion) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.creador = creador;
        this.bloques = bloques;
        this.fechaCreacion = fechaCreacion;
    }

    public long getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
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

    public LocalDateTime getFechaCreacion() {
        return this.fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}