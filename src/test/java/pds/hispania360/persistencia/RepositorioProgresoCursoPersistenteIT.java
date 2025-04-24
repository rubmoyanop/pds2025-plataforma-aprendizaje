package pds.hispania360.persistencia;

import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.assertThat;
import pds.hispania360.modelo.*;
import java.util.ArrayList;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RepositorioProgresoCursoPersistenteIT {

    private static Usuario usuario;
    private static Curso curso;
    private static ProgresoCurso progreso;

    @Test
    @Order(1)
    public void testGuardarProgreso() {
        // Persistir el usuario antes de asociarlo al curso y progreso
        String email = "estu@persistencia.com";
        String nombre = "Estudiante Persistencia";
        String password = "clave";
        boolean esCreador = false;
        // Elimina si ya existe para evitar duplicados
        RepositorioUsuarioPersistente.INSTANCIA.autenticarUsuario(email, password)
            .ifPresent(u -> RepositorioUsuarioPersistente.INSTANCIA.eliminarUsuario(u.getId()));
        boolean usuarioCreado = RepositorioUsuarioPersistente.INSTANCIA.crearUsuario(email, nombre, password, esCreador);
        assertThat(usuarioCreado).isTrue();
        usuario = RepositorioUsuarioPersistente.INSTANCIA.autenticarUsuario(email, password).orElse(null);
        assertThat(usuario).isNotNull();

        ArrayList<Bloque> bloques = new ArrayList<>();
        curso = new Curso("Curso Progreso", "Test progreso", usuario, bloques, java.time.LocalDate.now());
        RepositorioCursoPersistente.INSTANCIA.agregarCurso(curso);

        progreso = new ProgresoCurso(null, curso, 0);
        progreso.setUsuario(usuario);

        RepositorioProgresoCursoPersistente.INSTANCIA.guardarProgreso(progreso);

        List<ProgresoCurso> lista = RepositorioProgresoCursoPersistente.INSTANCIA.obtenerProgresosPorUsuario(usuario);
        assertThat(lista).isNotEmpty();
        ProgresoCurso pc = lista.get(0);
        assertThat(pc.getCurso().getTitulo()).isEqualTo("Curso Progreso");
    }

    @Test
    @Order(2)
    public void testActualizarProgreso() {
        List<ProgresoCurso> lista = RepositorioProgresoCursoPersistente.INSTANCIA.obtenerProgresosPorUsuario(usuario);
        assertThat(lista).isNotEmpty();
        ProgresoCurso pc = lista.get(0);
        pc.setProgreso(2);
        RepositorioProgresoCursoPersistente.INSTANCIA.actualizarProgreso(pc);

        ProgresoCurso pc2 = RepositorioProgresoCursoPersistente.INSTANCIA.obtenerProgresoPorId(pc.getId());
        assertThat(pc2.getProgreso()).isEqualTo(2);
    }
}
