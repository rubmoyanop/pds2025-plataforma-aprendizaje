package pds.hispania360.modelo;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class UserTest {

    @Test
    public void testUsuarioBasicConstructor() {
        // Se usa el constructor: Usuario(int id, boolean creador, String nombre, String email, String password)
        Usuario user = new Usuario(10, false, "Lionel Messi", "messi@gmail.com", "supersecret");
        assertThat(user.getId()).isEqualTo(10);
        assertThat(user.isCreador()).isFalse();
        assertThat(user.getNombre()).isEqualTo("Lionel Messi");
        assertThat(user.getEmail()).isEqualTo("messi@gmail.com");
        assertThat(user.getPassword()).isEqualTo("supersecret");
        // Los atributos stats y cursos permanecen nulos con este constructor
        assertThat(user.getStats()).isNull();
        assertThat(user.getCursos()).isNull();
    }
    
    @Test
    public void testSettersAndGetters() {
        Usuario user = new Usuario(11, true, "Cristiano Ronaldo", "ronaldo@gmail.com", "initialPass");
        user.setNombre("Kylian Mbappé");
        user.setEmail("mbappe@gmail.com");
        user.setPassword("newpass");
        user.setCreador(false);
        assertThat(user.getNombre()).isEqualTo("Kylian Mbappé");
        assertThat(user.getEmail()).isEqualTo("mbappe@gmail.com");
        assertThat(user.getPassword()).isEqualTo("newpass");
        assertThat(user.isCreador()).isFalse();
    }
}