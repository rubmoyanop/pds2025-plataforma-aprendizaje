package pds.hispania360.persistencia;

import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.assertThat;
import pds.hispania360.modelo.Curso;
import pds.hispania360.modelo.Usuario;
import pds.hispania360.modelo.Bloque;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RepositorioCursoPersistenteIT {

    private static int cursoId;
    private static Usuario creadorPersistido;

    @Test
    @Order(1)
    public void testCrearCurso() {
        // Persistir el usuario creador antes de crear el curso
        String email = "creador@persistencia.com";
        String nombre = "Creador Persistencia";
        String password = "clave";
        boolean esCreador = false;
        RepositorioUsuarioPersistente.INSTANCIA.autenticarUsuario(email, password)
            .ifPresent(u -> RepositorioUsuarioPersistente.INSTANCIA.eliminarUsuario(u.getId()));
        boolean usuarioCreado = RepositorioUsuarioPersistente.INSTANCIA.crearUsuario(email, nombre, password, esCreador);
        assertThat(usuarioCreado).isTrue();
        creadorPersistido = RepositorioUsuarioPersistente.INSTANCIA.autenticarUsuario(email, password).orElse(null);
        assertThat(creadorPersistido).isNotNull();

        ArrayList<Bloque> bloques = new ArrayList<>();
        LocalDate fecha = LocalDate.now();
        RepositorioCursoPersistente.INSTANCIA.crearCurso("Curso Persistente", "Curso para test integración", creadorPersistido, bloques, fecha);

        List<Curso> cursos = RepositorioCursoPersistente.INSTANCIA.obtenerCursos();
        assertThat(cursos).isNotEmpty();
        Curso curso = cursos.stream().filter(c -> "Curso Persistente".equals(c.getTitulo())).findFirst().orElse(null);
        assertThat(curso).isNotNull();
        cursoId = curso.getId();
    }

    @Test
    @Order(2)
    public void testObtenerCursoPorId() {
        Curso curso = RepositorioCursoPersistente.INSTANCIA.obtenerCurso(cursoId);
        assertThat(curso).isNotNull();
        assertThat(curso.getTitulo()).isEqualTo("Curso Persistente");
    }

    @Test
    @Order(3)
    public void testEliminarCurso() {
        boolean eliminado = RepositorioCursoPersistente.INSTANCIA.eliminarCurso(cursoId);
        assertThat(eliminado).isTrue();
        Curso curso = RepositorioCursoPersistente.INSTANCIA.obtenerCurso(cursoId);
        assertThat(curso).isNull();
    }
}
