package pds.hispania360;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import pds.hispania360.modelo.Curso;
import pds.hispania360.modelo.Usuario;
import pds.hispania360.modelo.Bloque;
import java.time.LocalDate;
import java.util.ArrayList;

public class CursoTest {

    @Test
    public void testConstructorAndGetters() {
        Usuario creador = new Usuario(1, true, "Creator", "creator@example.com", "pass");
        ArrayList<Bloque> bloques = new ArrayList<>();
        LocalDate fecha = LocalDate.now();

        Curso curso = new Curso(101, "Curso Java", "Descripcion del curso", creador, bloques, fecha);
        
        assertThat(curso.getId()).isEqualTo(101);
        assertThat(curso.getTitulo()).isEqualTo("Curso Java");
        assertThat(curso.getDescripcion()).isEqualTo("Descripcion del curso");
        assertThat(curso.getCreador()).isEqualTo(creador);
        assertThat(curso.getBloques()).isEqualTo(bloques);
        assertThat(curso.getFechaCreacion()).isEqualTo(fecha);
    }

    @Test
    public void testSetters() {
        Curso curso = new Curso(102);
        curso.setTitulo("Nuevo Titulo");
        curso.setDescripcion("Nueva Descripcion");
        Usuario creador = new Usuario(2, false, "New Creator", "newcreator@example.com", "password");
        curso.setCreador(creador);
        ArrayList<Bloque> bloques = new ArrayList<>();
        curso.setBloques(bloques);
        LocalDate fecha = LocalDate.now();
        curso.setFechaCreacion(fecha);
        
        assertThat(curso.getTitulo()).isEqualTo("Nuevo Titulo");
        assertThat(curso.getDescripcion()).isEqualTo("Nueva Descripcion");
        assertThat(curso.getCreador()).isEqualTo(creador);
        assertThat(curso.getBloques()).isEqualTo(bloques);
        assertThat(curso.getFechaCreacion()).isEqualTo(fecha);
    }
}