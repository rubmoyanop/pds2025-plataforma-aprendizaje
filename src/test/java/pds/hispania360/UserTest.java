package pds.hispania360;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import pds.hispania360.modelo.Usuario;


public class UserTest {

    @Test
    public void testUsuarioBasicConstructor() {
        // Se usa el constructor: Usuario(int id, boolean creador, String nombre, String email, String password)
        Usuario user = new Usuario(1, false, "John Doe", "john.doe@example.com", "secret");
        assertThat(user.getId()).isEqualTo(1);
        assertThat(user.isCreador()).isFalse();
        assertThat(user.getNombre()).isEqualTo("John Doe");
        assertThat(user.getEmail()).isEqualTo("john.doe@example.com");
        assertThat(user.getPassword()).isEqualTo("secret");
        // Los atributos stats y cursos permanecen nulos con este constructor
        assertThat(user.getStats()).isNull();
        assertThat(user.getCursos()).isNull();
    }
    
    @Test
    public void testSettersAndGetters() {
        Usuario user = new Usuario(2, true, "Jane Doe", "jane.doe@example.com", "password");
        user.setNombre("Jane Smith");
        user.setEmail("jane.smith@example.com");
        user.setPassword("newpassword");
        user.setCreador(false);
        assertThat(user.getNombre()).isEqualTo("Jane Smith");
        assertThat(user.getEmail()).isEqualTo("jane.smith@example.com");
        assertThat(user.getPassword()).isEqualTo("newpassword");
        assertThat(user.isCreador()).isFalse();
        
        // Establece y comprueba el atributo cursos
    }
}