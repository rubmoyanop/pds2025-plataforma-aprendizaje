package pds.hispania360.repositorio;

import java.util.List;

import pds.hispania360.modelo.Curso;

public interface RepositorioCurso {

    void agregarCurso(Curso c);

    List<Curso> obtenerCursos();
}
