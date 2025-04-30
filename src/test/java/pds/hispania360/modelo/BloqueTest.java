package pds.hispania360.modelo;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import pds.hispania360.modelo.ejercicios.Ejercicio;
import pds.hispania360.modelo.ejercicios.Flashcard;
import pds.hispania360.modelo.ejercicios.RellenarHueco;

import java.util.ArrayList;
import java.util.List;

public class BloqueTest {

    @Test
    public void testConstructorAndGetters() {
        // Arrange
        String titulo = "Introducción a la Historia";
        String descripcion = "Conceptos básicos";
        List<Ejercicio> ejercicios = new ArrayList<>();
        ejercicios.add(new Flashcard("Pregunta 1", "Respuesta 1"));
        ejercicios.add(new RellenarHueco("Completa ___ frase.", "la"));

        // Act
        Bloque bloque = new Bloque(titulo, descripcion, ejercicios);

        // Assert
        // El ID se genera automáticamente por la persistencia, no se prueba aquí directamente.
        assertThat(bloque.getTitulo()).isEqualTo(titulo);
        assertThat(bloque.getDescripcion()).isEqualTo(descripcion);
        assertThat(bloque.getEjercicios()).isEqualTo(ejercicios);
        assertThat(bloque.getEjercicios()).hasSize(2);
    }

    @Test
    public void testDefaultConstructor() {
        // Arrange & Act
        Bloque bloque = new Bloque();

        // Assert
        assertThat(bloque.getTitulo()).isNull();
        assertThat(bloque.getDescripcion()).isNull();
        assertThat(bloque.getEjercicios()).isNotNull();
        assertThat(bloque.getEjercicios()).isEmpty();
    }

    @Test
    public void testSetters() {
        // Arrange
        Bloque bloque = new Bloque();
        String nuevoTitulo = "Historia Antigua";
        String nuevaDescripcion = "Grecia y Roma";
        List<Ejercicio> nuevosEjercicios = new ArrayList<>();
        nuevosEjercicios.add(new Flashcard("Capital de Grecia", "Atenas"));

        // Act
        bloque.setTitulo(nuevoTitulo);
        bloque.setDescripcion(nuevaDescripcion);
        bloque.setEjercicios(nuevosEjercicios);

        // Assert
        assertThat(bloque.getTitulo()).isEqualTo(nuevoTitulo);
        assertThat(bloque.getDescripcion()).isEqualTo(nuevaDescripcion);
        assertThat(bloque.getEjercicios()).isEqualTo(nuevosEjercicios);
        assertThat(bloque.getEjercicios()).hasSize(1);
    }
}
