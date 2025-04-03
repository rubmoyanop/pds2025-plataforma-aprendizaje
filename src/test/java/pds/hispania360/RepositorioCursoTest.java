package pds.hispania360;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pds.hispania360.modelo.Curso;
import pds.hispania360.modelo.Usuario;
import pds.hispania360.modelo.Bloque;
import pds.hispania360.repositorio.GestorCurso;

public class RepositorioCursoTest {

    @BeforeEach
    public void setUp() throws Exception {
        // Empleamos reflection para reinicializar el campo "cursos" asignándole un nuevo HashMap
        Field cursosField = GestorCurso.class.getDeclaredField("cursos");
        cursosField.setAccessible(true);
        cursosField.set(GestorCurso.INSTANCIA, new HashMap<Integer, Curso>());
    }
    
    @Test
    public void testAgregarCurso() {
        // Creamos un curso de historia de España
        Usuario creador = new Usuario(1, true, "Alfonso X", "alfonso@elsabio.com", "historia123");
        ArrayList<Bloque> bloques = new ArrayList<>();
        LocalDate fecha = LocalDate.now();
        Curso curso = new Curso(0, 
            "Curso de Historia de España", 
            "Estudia la evolución de España desde la antigüedad hasta la actualidad", 
            creador, bloques, fecha);
        // Agregar curso manualmente
        GestorCurso.INSTANCIA.agregarCurso(curso);
        
        List<Curso> cursosObtenidos = GestorCurso.INSTANCIA.obtenerCursos();
        assertThat(cursosObtenidos).contains(curso);
    }
    
    @Test
    public void testCrearCurso() {
        // Preparamos datos para crear un curso de historia de España vía método crearCurso
        Usuario creador = new Usuario(2, true, "Isabel la Católica", "isabel@lacatolica.com", "reinas123");
        ArrayList<Bloque> bloques = new ArrayList<>();
        LocalDate fecha = LocalDate.now();
        int sizeInicial = GestorCurso.INSTANCIA.obtenerCursos().size();
        
        GestorCurso.INSTANCIA.crearCurso(
            "Curso de Historia de España", 
            "Aprende los hitos fundamentales en la historia de España", 
            creador, bloques, fecha);
        List<Curso> cursosObtenidos = GestorCurso.INSTANCIA.obtenerCursos();
        assertThat(cursosObtenidos.size()).isEqualTo(sizeInicial + 1);
        Curso nuevoCurso = cursosObtenidos.get(cursosObtenidos.size() - 1);
        assertThat(nuevoCurso.getTitulo()).isEqualTo("Curso de Historia de España");
        assertThat(nuevoCurso.getDescripcion()).isEqualTo("Aprende los hitos fundamentales en la historia de España");
        assertThat(nuevoCurso.getCreador()).isEqualTo(creador);
        assertThat(nuevoCurso.getBloques()).isEqualTo(bloques);
        assertThat(nuevoCurso.getFechaCreacion()).isEqualTo(fecha);
    }
}