package pds.hispania360.modelo;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Curso {
    private long id;
    private String titulo;
    private String descripcion;
    private Usuario creador;
    private ArrayList<Bloque> bloques;
    private boolean compartido;
    private LocalDateTime fechaCreacion;

}
