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

<<<<<<< HEAD
=======
    public Bloque devolverBloque(int progreso){
        return bloques.get(progreso);
    }

>>>>>>> a2cd7c9fa1adcd05e7b8b3c90a3383622e22b939
}
