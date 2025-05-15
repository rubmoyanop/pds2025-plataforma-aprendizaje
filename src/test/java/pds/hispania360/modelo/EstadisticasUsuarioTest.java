package pds.hispania360.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EstadisticasUsuarioTest {

    private EstadisticasUsuario stats;

    @BeforeEach
    void setUp() {
        stats = new EstadisticasUsuario(); 
    }

    @Test
    void testDefaultConstructor() {
        assertThat(stats.getId()).isNull();
        assertThat(stats.getNumCursosCompletados()).isEqualTo(0);
        assertThat(stats.getNumCursosEnProgreso()).isEqualTo(0);
        assertThat(stats.getTiempoUso()).isEqualTo(0L);
        assertThat(stats.getMejorRacha()).isEqualTo(0);
        assertThat(stats.getExperiencia()).isEqualTo(0);
    }

    @Test
    void testParameterizedConstructor() {
        EstadisticasUsuario statsParam = new EstadisticasUsuario(2, 3, 1000L, 5, 150);
        assertThat(statsParam.getId()).isNull(); 
        assertThat(statsParam.getNumCursosCompletados()).isEqualTo(2);
        assertThat(statsParam.getNumCursosEnProgreso()).isEqualTo(3);
        assertThat(statsParam.getTiempoUso()).isEqualTo(1000L);
        assertThat(statsParam.getMejorRacha()).isEqualTo(5);
        assertThat(statsParam.getExperiencia()).isEqualTo(150);
    }

    @Test
    void testSettersAndGetters() {
       
        stats.setId(1);
        stats.setNumCursosCompletados(5);
        stats.setNumCursosEnProgreso(2);
        stats.setTiempoUso(5000L);
        stats.setMejorRacha(10);

        assertThat(stats.getId()).isEqualTo(1);
        assertThat(stats.getNumCursosCompletados()).isEqualTo(5);
        assertThat(stats.getNumCursosEnProgreso()).isEqualTo(2);
        assertThat(stats.getTiempoUso()).isEqualTo(5000L);
        assertThat(stats.getMejorRacha()).isEqualTo(10);
    }

    @Test
    void testAumentarCursosCompletados() {
       
        stats.setNumCursosCompletados(1);
        stats.setNumCursosEnProgreso(3);

        stats.aumentarCursosCompletados();

        assertThat(stats.getNumCursosCompletados()).isEqualTo(2);
        assertThat(stats.getNumCursosEnProgreso()).isEqualTo(2);
    }

    @Test
    void testAumentarCursosEnProgreso() {
        
        stats.setNumCursosEnProgreso(1);

        stats.aumentarCursosEnProgreso();

        assertThat(stats.getNumCursosEnProgreso()).isEqualTo(2);
    }

    @Test
    void testDisminuirCursosEnProgreso() {
        
        stats.setNumCursosEnProgreso(3);

        stats.disminuirCursosEnProgreso();

        assertThat(stats.getNumCursosEnProgreso()).isEqualTo(2);
    }

    @Test
    void testAumentarTiempoUso() {
    
        stats.setTiempoUso(100L);

        stats.aumentarTiempoUso(50L);

        assertThat(stats.getTiempoUso()).isEqualTo(150L);
    }

    @Test
    void testActualizarRachaAciertoSinSuperarMejor() {
        
        stats.setMejorRacha(5);
        // Simular racha actual de 2 
        stats.actualizarRacha(true);
        stats.actualizarRacha(true); 

        stats.actualizarRacha(true); // rachaActual = 3

        stats.actualizarRacha(false); // rachaActual = 0
        stats.actualizarRacha(true); // rachaActual = 1
        stats.actualizarRacha(true); // rachaActual = 2
        stats.actualizarRacha(true); // rachaActual = 3 -> Correcto
        assertThat(stats.getMejorRacha()).isEqualTo(5); // No debería cambiar
    }

    @Test
    void testActualizarRachaAciertoSuperandoMejor() {
        
        stats.setMejorRacha(2);
        // Simular racha actual de 2
        stats.actualizarRacha(true); // rachaActual = 1
        stats.actualizarRacha(true); // rachaActual = 2

        stats.actualizarRacha(true); // rachaActual = 3

        assertThat(stats.getMejorRacha()).isEqualTo(3); // Debería actualizarse
    }

    @Test
    void testActualizarRachaFallo() {
        
        stats.setMejorRacha(5);
        // Simular racha actual de 3
        stats.actualizarRacha(true);
        stats.actualizarRacha(true);
        stats.actualizarRacha(true);

        stats.actualizarRacha(false); // rachaActual = 0

        // Verificar rachaActual indirectamente
        stats.actualizarRacha(true); // rachaActual = 1 -> Se reseteó correctamente
        assertThat(stats.getMejorRacha()).isEqualTo(5); // No debería cambiar
    }

    @Test
    void testAumentarExperiencia() {

        stats.aumentarExperiencia(50);

        assertThat(stats.getExperiencia()).isEqualTo(50);

        stats.aumentarExperiencia(100);

        assertThat(stats.getExperiencia()).isEqualTo(150);
    }
}
