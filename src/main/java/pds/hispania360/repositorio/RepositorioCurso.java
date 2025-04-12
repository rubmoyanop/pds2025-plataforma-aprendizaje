package pds.hispania360.repositorio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import pds.hispania360.modelo.Bloque;
import pds.hispania360.modelo.Curso;
import pds.hispania360.modelo.Usuario;

public interface RepositorioCurso {

    void crearCurso(String titulo, String descripcion, Usuario creador, ArrayList<Bloque> bloques, LocalDate fechaCreacion);
    
    void agregarCurso(Curso c);

    List<Curso> obtenerCursos();

    Curso obtenerCurso(int id);

    
}
