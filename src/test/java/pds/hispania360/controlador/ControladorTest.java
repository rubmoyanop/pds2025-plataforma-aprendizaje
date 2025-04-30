package pds.hispania360.controlador;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import pds.hispania360.modelo.Usuario;
import pds.hispania360.sesion.Sesion;
import pds.hispania360.persistencia.RepositorioUsuarioPersistente;

import java.io.File; // Importar File
import java.net.URL; // Importar URL

public class ControladorTest {

    @BeforeEach
    public void setUp() {
        Sesion.INSTANCIA.cerrarSesion();
    }
    
    @Test
    public void testRegistrarUsuario() {
        // Elimina el usuario si ya existe para evitar fallo por duplicado
        Usuario existente = RepositorioUsuarioPersistente.INSTANCIA.autenticarUsuario("messi@gmail.com", "secret")
            .orElse(null);
        if (existente != null) {
            RepositorioUsuarioPersistente.INSTANCIA.eliminarUsuario(existente.getId());
        }
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

        // Obtener el archivo curso.json de los recursos
        URL resourceUrl = getClass().getClassLoader().getResource("curso.json");
        assertThat(resourceUrl).isNotNull(); // Asegurarse de que el recurso existe
        File archivoCurso = new File(resourceUrl.getFile());

        boolean resultado = Controlador.INSTANCIA.importarCurso(archivoCurso);
        assertThat(resultado).isFalse(); // El usuario no es creador, así que debería fallar
        assertThat(Controlador.INSTANCIA.getUltimoError()).isEqualTo("Solo los usuarios creadores pueden importar cursos.");
    }
}