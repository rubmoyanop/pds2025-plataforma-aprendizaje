package pds.hispania360.repositorio;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pds.hispania360.modelo.*;

public enum GestorCurso implements RepositorioCurso{
    INSTANCIA;

    private Map<Integer, Curso> cursos;

    GestorCurso(){
        cursos = new HashMap<Integer, Curso>();
    }

    public void agregarCurso(Curso c){
        cursos.put(c.getId(), c);
    }

    public void crearCurso(String titulo, String descripcion, Usuario creador, ArrayList<Bloque> bloques, LocalDateTime fechaCreacion){
        Curso c = new Curso(cursos.size(), titulo, descripcion, creador, bloques, fechaCreacion);
        agregarCurso(c);
    }
    
    

    public List<Curso> obtenerCursos(){
        return null;
    }
}
