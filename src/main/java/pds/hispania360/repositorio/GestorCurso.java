package pds.hispania360.repositorio;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import pds.hispania360.modelo.*;

public enum GestorCurso implements RepositorioCurso{
    INSTANCIA;

    private Map<Integer, Curso> cursos;

    GestorCurso(){
        cursos = new HashMap<Integer, Curso>();
    }

    @Override
    public void agregarCurso(Curso c){
        cursos.put(c.getId(), c);
    }

    @Override
    public void crearCurso(String titulo, String descripcion, Usuario creador, ArrayList<Bloque> bloques, LocalDate fechaCreacion){
        Curso c = new Curso(cursos.size(), titulo, descripcion, creador, bloques, fechaCreacion);
        agregarCurso(c);
    }   
    
    @Override
    public List<Curso> obtenerCursos(){
        return Collections.unmodifiableList(new ArrayList<>(cursos.values()));
    }

    @Override
    public Curso obtenerCurso(int id) {
        return cursos.get(id);
    }
}
