package pds.hispania360.sesion;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pds.hispania360.modelo.Usuario;
import pds.hispania360.modelo.Curso;

public class SesionTest {

    @ParameterizedTest
    @CsvSource({
        "true, Curso de Creadores",
        "false, Curso de Estudiantes",
        "true, ",  
        "false, " 
    })
    public void testSesionConUsuarioYCurso(boolean isCreador, String cursoTitulo) {
        Sesion.INSTANCIA.cerrarSesion();
        
        // Crear un Usuario y asignar a la sesión
        Usuario user = new Usuario(1, isCreador, "Test User", "test@example.com", "secret");
        Sesion.INSTANCIA.setUsuarioActual(user);
        assertTrue(Sesion.INSTANCIA.haySesion());
        if(isCreador){
            assertTrue(Sesion.INSTANCIA.esCreador());
        } else {
            assertTrue(Sesion.INSTANCIA.esEstudiante());
        }
        
        // Asignar un curso a la sesión
        Curso curso = new Curso(1);
        curso.setTitulo(cursoTitulo);
        Sesion.INSTANCIA.setCursoActual(curso);
        assertEquals(cursoTitulo, Sesion.INSTANCIA.getCursoActual().getTitulo());
    }
    
    @ParameterizedTest
    @CsvSource({
       "true",
       "false"
    })
    public void testCerrarSesion(boolean isCreador) {
        
        Sesion.INSTANCIA.cerrarSesion();
        assertFalse(Sesion.INSTANCIA.haySesion());
        assertNull(Sesion.INSTANCIA.getUsuarioActual());
        
        // Creamos un Usuario e iniciamos sesión
        Usuario user = new Usuario(1, isCreador, "Test User", "test@example.com", "secret");
        Sesion.INSTANCIA.setUsuarioActual(user);
        assertTrue(Sesion.INSTANCIA.haySesion());
        
        // Cerramos sesión otra vez y verificamos
        Sesion.INSTANCIA.cerrarSesion();
        assertFalse(Sesion.INSTANCIA.haySesion());
        assertNull(Sesion.INSTANCIA.getUsuarioActual());
    }
    
    @ParameterizedTest
    @CsvSource({
        "true, true",
        "false, false"
    })
    public void testSesionSinCurso(boolean isCreador, boolean cursoAsignado) {
        // Reinicia sesión sin asignar curso
        Sesion.INSTANCIA.cerrarSesion();
        Usuario user = new Usuario(1, isCreador, "Test User", "test@example.com", "secret");
        Sesion.INSTANCIA.setUsuarioActual(user);
        // No se asigna curso si cursoAsignado es false
        if(cursoAsignado) {
            Curso curso = new Curso(1);
            curso.setTitulo("Curso Asignado");
            Sesion.INSTANCIA.setCursoActual(curso);
            assertNotNull(Sesion.INSTANCIA.getCursoActual());
        } else {
            assertNull(Sesion.INSTANCIA.getCursoActual());
        }
    }
}
