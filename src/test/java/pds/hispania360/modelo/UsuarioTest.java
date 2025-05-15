package pds.hispania360.modelo;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class UsuarioTest {

    @Test
    public void testUsuarioBasicConstructor() {
        Usuario user = new Usuario(10, false, "Lionel Messi", "messi@gmail.com", "supersecret");
        assertThat(user.getId()).isEqualTo(10);
        assertThat(user.isCreador()).isFalse();
        assertThat(user.getNombre()).isEqualTo("Lionel Messi");
        assertThat(user.getEmail()).isEqualTo("messi@gmail.com");
        assertThat(user.getPassword()).isEqualTo("supersecret");
        assertThat(user.getStats()).isNotNull();
        assertThat(user.getCursos()).isNotNull();
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