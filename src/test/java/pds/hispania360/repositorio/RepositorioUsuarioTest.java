package pds.hispania360.repositorio;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pds.hispania360.modelo.Usuario;

public class RepositorioUsuarioTest {

    // Implementación dummy para pruebas
    class RepositorioUsuarioDummy implements RepositorioUsuario {
        private Map<Integer, Usuario> usuarios = new HashMap<>();
        private int nextId = 1;
        
        @Override
        public boolean crearUsuario(String email, String nombre, String password, boolean esCreador) {
            Usuario user = new Usuario(nextId, esCreador, nombre, email, password);
            usuarios.put(nextId, user);
            nextId++;
            return true;
        }

        @Override
        public Usuario obtenerUsuario(int id) {
            return usuarios.get(id);
        }

        @Override
        public boolean eliminarUsuario(int id) {
            return usuarios.remove(id) != null;
        }

        @Override
        public Optional<Usuario> autenticarUsuario(String email, String password) {
            return usuarios.values().stream()
                    .filter(u -> u.getEmail().equals(email) && u.getPassword().equals(password))
                    .findFirst();
        }
    }
    
    private RepositorioUsuarioDummy repo;

    @BeforeEach
    public void setUp() {
        repo = new RepositorioUsuarioDummy();
    }
    
    @Test
    public void testCrearYObtenerUsuario() {
        boolean creado = repo.crearUsuario("messi@example.com", "Lionel Messi", "barcelona123", false);
        assertThat(creado).isTrue();
        // Se asume que el primer usuario creado tiene ID 1
        Usuario user = repo.obtenerUsuario(1);
        assertThat(user).isNotNull();
        assertThat(user.getNombre()).isEqualTo("Lionel Messi");
        assertThat(user.getEmail()).isEqualTo("messi@example.com");
    }
    
    @Test
    public void testAutenticarUsuario() {
        repo.crearUsuario("ronaldo@example.com", "Cristiano Ronaldo", "madrid987", false);
        Optional<Usuario> optUser = repo.autenticarUsuario("ronaldo@example.com", "madrid987");
        assertThat(optUser).isPresent();
        Usuario user = optUser.get();
        assertThat(user.getNombre()).isEqualTo("Cristiano Ronaldo");
    }
    
    @Test
    public void testEliminarUsuario() {
        repo.crearUsuario("mbappe@example.com", "Kylian Mbappé", "madrid456", true);
        // ID del primer usuario creado es 1
        boolean eliminado = repo.eliminarUsuario(1);
        assertThat(eliminado).isTrue();
        assertThat(repo.obtenerUsuario(1)).isNull();
    }
}