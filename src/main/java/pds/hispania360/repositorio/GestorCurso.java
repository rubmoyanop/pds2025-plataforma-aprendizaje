package pds.hispania360.repositorio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pds.hispania360.modelo.Curso;

public enum GestorCurso implements RepositorioCurso{
    INSTANCIA;

    private Map<Integer, Curso> cursos;

    GestorCurso(){
        cursos = new HashMap<Integer, Curso>();
    }

    public void agregarCurso(Curso c){
        cursos.put((int) c.getId(), c);
    }

    public List<Curso> obtenerCursos(){
        return null;
    }
}
