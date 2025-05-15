package pds.hispania360.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EstadisticasUsuarioTest {

    private EstadisticasUsuario stats;

    @BeforeEach
    void setUp() {
        stats = new EstadisticasUsuario(); // Usa el constructor por defecto
    }

    @Test
    void testDefaultConstructor() {
        assertThat(stats.getId()).isNull();
        assertThat(stats.getNumCursosCompletados()).isEqualTo(0);
        assertThat(stats.getNumCursosEnProgreso()).isEqualTo(0);
        assertThat(stats.getTiempoUso()).isEqualTo(0L);
        assertThat(stats.getMejorRacha()).isEqualTo(0);
        assertThat(stats.getExperiencia()).isEqualTo(0);
        // rachaActual es privada, se prueba indirectamente con actualizarRacha
    }

    @Test
    void testParameterizedConstructor() {
        EstadisticasUsuario statsParam = new EstadisticasUsuario(2, 3, 1000L, 5, 150);
        assertThat(statsParam.getId()).isNull(); // ID no se establece en constructor
        assertThat(statsParam.getNumCursosCompletados()).isEqualTo(2);
        assertThat(statsParam.getNumCursosEnProgreso()).isEqualTo(3);
        assertThat(statsParam.getTiempoUso()).isEqualTo(1000L);
        assertThat(statsParam.getMejorRacha()).isEqualTo(5);
        assertThat(statsParam.getExperiencia()).isEqualTo(150);
    }

    @Test
    void testSettersAndGetters() {
        // Arrange
        stats.setId(1);
        stats.setNumCursosCompletados(5);
        stats.setNumCursosEnProgreso(2);
        stats.setTiempoUso(5000L);
        stats.setMejorRacha(10);
        // No hay setter público para experiencia, se usa aumentarExperiencia
        // No hay setter público para rachaActual

        // Assert
        assertThat(stats.getId()).isEqualTo(1);
        assertThat(stats.getNumCursosCompletados()).isEqualTo(5);
        assertThat(stats.getNumCursosEnProgreso()).isEqualTo(2);
        assertThat(stats.getTiempoUso()).isEqualTo(5000L);
        assertThat(stats.getMejorRacha()).isEqualTo(10);
    }

    @Test
    void testAumentarCursosCompletados() {
        // Arrange
        stats.setNumCursosCompletados(1);
        stats.setNumCursosEnProgreso(3);

        // Act
        stats.aumentarCursosCompletados();

        // Assert
        assertThat(stats.getNumCursosCompletados()).isEqualTo(2);
        assertThat(stats.getNumCursosEnProgreso()).isEqualTo(2);
    }

    @Test
    void testAumentarCursosEnProgreso() {
        // Arrange
        stats.setNumCursosEnProgreso(1);

        // Act
        stats.aumentarCursosEnProgreso();

        // Assert
        assertThat(stats.getNumCursosEnProgreso()).isEqualTo(2);
    }

    @Test
    void testDisminuirCursosEnProgreso() {
        // Arrange
        stats.setNumCursosEnProgreso(3);

        // Act
        stats.disminuirCursosEnProgreso();

        // Assert
        assertThat(stats.getNumCursosEnProgreso()).isEqualTo(2);
    }

    @Test
    void testAumentarTiempoUso() {
        // Arrange
        stats.setTiempoUso(100L);

        // Act
        stats.aumentarTiempoUso(50L);

        // Assert
        assertThat(stats.getTiempoUso()).isEqualTo(150L);
    }

    @Test
    void testActualizarRachaAciertoSinSuperarMejor() {
        // Arrange
        stats.setMejorRacha(5);
        // Simular racha actual de 2 (no hay setter directo)
        stats.actualizarRacha(true); // rachaActual = 1
        stats.actualizarRacha(true); // rachaActual = 2

        // Act
        stats.actualizarRacha(true); // rachaActual = 3

        // Assert
        // Verificar rachaActual indirectamente
        stats.actualizarRacha(false); // rachaActual = 0
        stats.actualizarRacha(true); // rachaActual = 1
        stats.actualizarRacha(true); // rachaActual = 2
        stats.actualizarRacha(true); // rachaActual = 3 -> Correcto
        assertThat(stats.getMejorRacha()).isEqualTo(5); // No debería cambiar
    }

    @Test
    void testActualizarRachaAciertoSuperandoMejor() {
        // Arrange
        stats.setMejorRacha(2);
        // Simular racha actual de 2
        stats.actualizarRacha(true); // rachaActual = 1
        stats.actualizarRacha(true); // rachaActual = 2

        // Act
        stats.actualizarRacha(true); // rachaActual = 3

        // Assert
        assertThat(stats.getMejorRacha()).isEqualTo(3); // Debería actualizarse
    }

    @Test
    void testActualizarRachaFallo() {
        // Arrange
        stats.setMejorRacha(5);
        // Simular racha actual de 3
        stats.actualizarRacha(true);
        stats.actualizarRacha(true);
        stats.actualizarRacha(true);

        // Act
        stats.actualizarRacha(false); // rachaActual = 0

        // Assert
        // Verificar rachaActual indirectamente
        stats.actualizarRacha(true); // rachaActual = 1 -> Se reseteó correctamente
        assertThat(stats.getMejorRacha()).isEqualTo(5); // No debería cambiar
    }

    @Test
    void testAumentarExperiencia() {
        // Arrange
        // Experiencia inicial es 0 por el constructor por defecto

        // Act
        stats.aumentarExperiencia(50);

        // Assert
        assertThat(stats.getExperiencia()).isEqualTo(50);

        // Act again
        stats.aumentarExperiencia(100);

        // Assert again
        assertThat(stats.getExperiencia()).isEqualTo(150);
    }
}
