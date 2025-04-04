package pds.hispania360.controlador;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import pds.hispania360.modelo.Usuario;
import pds.hispania360.sesion.Sesion;

public class ControladorTest {

    @BeforeEach
    public void setUp() {
        Sesion.INSTANCIA.cerrarSesion();
    }
    
    @Test
    public void testRegistrarUsuario() {
        boolean registrado = Controlador.INSTANCIA.registrarUsuario("messi@gmail.com", "Lionel Messi", "secret", false);
        assertThat(registrado).isTrue();
    }
    
    @Test
    public void testIniciarSesion() {
        Controlador.INSTANCIA.registrarUsuario("ronaldo@gmail.com", "Cristiano Ronaldo", "secret", false);
        boolean inicio = Controlador.INSTANCIA.iniciarSesion("ronaldo@gmail.com", "secret");
        assertThat(inicio).isTrue();
        Usuario actual = Sesion.INSTANCIA.getUsuarioActual();
        assertThat(actual).isNotNull();
        assertThat(actual.getEmail()).isEqualTo("ronaldo@gmail.com");
    }
    
    @Test
    public void testCerrarSesion() {
        Controlador.INSTANCIA.registrarUsuario("neymar@gmail.com", "Neymar Jr", "secret", false);
        Controlador.INSTANCIA.iniciarSesion("neymar@gmail.com", "secret");
        Controlador.INSTANCIA.cerrarSesion();
        assertThat(Sesion.INSTANCIA.getUsuarioActual()).isNull();
    }
    
    @Test
    public void testImportarCursoNotCreador() {
        Controlador.INSTANCIA.registrarUsuario("suarez@gmail.com", "Luis Suárez", "secret", false);
        Controlador.INSTANCIA.iniciarSesion("suarez@gmail.com", "secret");
        boolean resultado = Controlador.INSTANCIA.importarCurso();
        assertThat(resultado).isFalse();
    }
}