package pds.hispania360.repositorio;

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

public class RepositorioCursoTest {

    @BeforeEach
    public void setUp() throws Exception {
        Field cursosField = GestorCurso.class.getDeclaredField("cursos");
        cursosField.setAccessible(true);
        cursosField.set(GestorCurso.INSTANCIA, new HashMap<Integer, Curso>());
    }

    @Test
    public void testAgregarCurso() {
        Usuario creador = new Usuario(1, true, "Alfonso X", "alfonso@elsabio.com", "historia123");
        ArrayList<Bloque> bloques = new ArrayList<>();
        LocalDate fecha = LocalDate.now();
        Curso curso = new Curso(0, 
            "Curso de Historia de España", 
            "Estudia la evolución de España desde la antigüedad hasta la actualidad", 
            creador, bloques, fecha);
        GestorCurso.INSTANCIA.agregarCurso(curso);

        List<Curso> cursosObtenidos = GestorCurso.INSTANCIA.obtenerCursos();
        if (cursosObtenidos.isEmpty()) {
            Curso cursoObtenido = GestorCurso.INSTANCIA.obtenerCurso(0);
            assertThat(cursoObtenido).isNotNull();
            assertThat(cursoObtenido.getTitulo()).isEqualTo("Curso de Historia de España");
            assertThat(cursoObtenido.getCreador().getNombre()).isEqualTo("Alfonso X");
        } else {
            boolean encontrado = cursosObtenidos.stream().anyMatch(c ->
                c.getTitulo().equals("Curso de Historia de España") &&
                c.getDescripcion().equals("Estudia la evolución de España desde la antigüedad hasta la actualidad") &&
                c.getCreador() != null &&
                c.getCreador().getNombre().equals("Alfonso X")
            );
            assertThat(encontrado).isTrue();
        }
    }

    @Test
    public void testObtenerCursoPorId() {
        // Creamos un curso para probar
        Usuario creador = new Usuario(3, true, "Carlos V", "carlos@imperio.com", "austrias123");
        ArrayList<Bloque> bloques = new ArrayList<>();
        LocalDate fecha = LocalDate.now();
        Curso curso = new Curso(1, "Curso del Imperio Español", "Explora el imperio de Carlos V", creador, bloques, fecha);
        GestorCurso.INSTANCIA.agregarCurso(curso);

        // Obtenemos el curso por ID
        Curso cursoObtenido = GestorCurso.INSTANCIA.obtenerCurso(1);

        // Verificamos que el curso obtenido es el mismo que el curso creado
        assertThat(cursoObtenido).isEqualTo(curso);
    }
}