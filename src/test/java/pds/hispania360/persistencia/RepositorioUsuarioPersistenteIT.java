package pds.hispania360.persistencia;

import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.assertThat;
import pds.hispania360.modelo.Usuario;

import java.util.Optional;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RepositorioUsuarioPersistenteIT {

    private static final String EMAIL = "testuser@persistencia.com";
    private static final String NOMBRE = "Test Persistencia";
    private static final String PASSWORD = "clave123";

    @Test
    @Order(1)
    public void testCrearUsuario() {
        boolean creado = RepositorioUsuarioPersistente.INSTANCIA.crearUsuario(EMAIL, NOMBRE, PASSWORD, false);
        assertThat(creado).isTrue();
    }

    @Test
    @Order(2)
    public void testAutenticarUsuario() {
        Optional<Usuario> opt = RepositorioUsuarioPersistente.INSTANCIA.autenticarUsuario(EMAIL, PASSWORD);
        assertThat(opt).isPresent();
        Usuario user = opt.get();
        assertThat(user.getNombre()).isEqualTo(NOMBRE);
        assertThat(user.getEmail()).isEqualTo(EMAIL);
    }

    @Test
    @Order(3)
    public void testObtenerUsuarioPorId() {
        Optional<Usuario> opt = RepositorioUsuarioPersistente.INSTANCIA.autenticarUsuario(EMAIL, PASSWORD);
        assertThat(opt).isPresent();
        Usuario user = opt.get();
        Usuario userById = RepositorioUsuarioPersistente.INSTANCIA.obtenerUsuario(user.getId());
        assertThat(userById).isNotNull();
        assertThat(userById.getEmail()).isEqualTo(EMAIL);
    }

    @Test
    @Order(4)
    public void testEliminarUsuario() {
        Optional<Usuario> opt = RepositorioUsuarioPersistente.INSTANCIA.autenticarUsuario(EMAIL, PASSWORD);
        assertThat(opt).isPresent();
        Usuario user = opt.get();
        boolean eliminado = RepositorioUsuarioPersistente.INSTANCIA.eliminarUsuario(user.getId());
        assertThat(eliminado).isTrue();
        Usuario userById = RepositorioUsuarioPersistente.INSTANCIA.obtenerUsuario(user.getId());
        assertThat(userById).isNull();
    }
}
