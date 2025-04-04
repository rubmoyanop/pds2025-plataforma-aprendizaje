package pds.hispania360.factoria;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import pds.hispania360.modelo.ejercicios.*;

import java.util.List;

public class FactoriaEjercicioTest {

    private final ObjectMapper mapper = new ObjectMapper();
    
    @Test
    public void testCrearRespuestaMultiple() throws Exception {
        String json = "{\"tipo\": \"respuesta_multiple\", \"enunciado\": \"¿Pregunta?\", \"opciones\": [\"a\", \"b\", \"c\"], \"respuestaCorrecta\": \"a\"}";
        JsonNode node = mapper.readTree(json);
        Ejercicio ejercicio = FactoriaEjercicio.INSTANCIA.crearEjercicio(node);
        assertTrue(ejercicio instanceof RespuestaMultiple);
        // Aserciones adicionales
        RespuestaMultiple rm = (RespuestaMultiple) ejercicio;
        assertEquals("¿Pregunta?", rm.getEnunciado());
        assertTrue(rm.validarRespuesta("a"));
        List<String> opciones = rm.getOpciones();
        assertNotNull(opciones);
        assertEquals(3, opciones.size());
        assertTrue(opciones.contains("a"));
        assertTrue(opciones.contains("b"));
        assertTrue(opciones.contains("c"));
    }
    
    @Test
    public void testCrearRellenarHueco() throws Exception {
        String json = "{\"tipo\": \"rellenar_hueco\", \"enunciado\": \"Frase con ___\", \"respuestaCorrecta\": \"espacio\"}";
        JsonNode node = mapper.readTree(json);
        Ejercicio ejercicio = FactoriaEjercicio.INSTANCIA.crearEjercicio(node);
        assertTrue(ejercicio instanceof RellenarHueco);
        // Aserciones adicionales
        RellenarHueco rh = (RellenarHueco) ejercicio;
        assertEquals("Frase con ___", rh.getEnunciado());
        assertTrue(rh.validarRespuesta("espacio"));
    }
    
    @Test
    public void testCrearFlashcard() throws Exception {
        String json = "{\"tipo\": \"flashcard\", \"frente\": \"Pregunta\", \"detras\": \"Respuesta\"}";
        JsonNode node = mapper.readTree(json);
        Ejercicio ejercicio = FactoriaEjercicio.INSTANCIA.crearEjercicio(node);
        assertTrue(ejercicio instanceof Flashcard);
        // Aserciones adicionales
        Flashcard fc = (Flashcard) ejercicio;
        assertEquals("Pregunta", fc.getEnunciado());
        assertTrue(fc.validarRespuesta("Respuesta"));
    }
    
    @Test
    public void testTipoNoSoportado() throws Exception {
        String json = "{\"tipo\": \"invalido\"}";
        JsonNode node = mapper.readTree(json);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            FactoriaEjercicio.INSTANCIA.crearEjercicio(node);
        });
        assertTrue(exception.getMessage().contains("Tipo de ejercicio no soportado"));
    }
    
    @Test
    public void testFaltaTipo() throws Exception {
        String json = "{\"enunciado\": \"Sin tipo\"}";
        JsonNode node = mapper.readTree(json);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            FactoriaEjercicio.INSTANCIA.crearEjercicio(node);
        });
        assertTrue(exception.getMessage().contains("El ejercicio debe tener un tipo"));
    }
    
    @Test
    public void testCrearRespuestaMultiple_Incompleto() throws Exception {
        String json = "{\"tipo\": \"respuesta_multiple\", \"enunciado\": \"¿Pregunta?\", \"respuestaCorrecta\": \"a\"}";
        JsonNode node = mapper.readTree(json);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            FactoriaEjercicio.INSTANCIA.crearEjercicio(node);
        });
        assertTrue(ex.getMessage().contains("incompleto"));
    }
    
    @Test
    public void testCrearRellenarHueco_Incompleto() throws Exception {
        String json = "{\"tipo\": \"rellenar_hueco\", \"enunciado\": \"Frase con ___\"}";
        JsonNode node = mapper.readTree(json);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            FactoriaEjercicio.INSTANCIA.crearEjercicio(node);
        });
        assertTrue(ex.getMessage().contains("incompleto"));
    }
    
    @Test
    public void testCrearFlashcard_Incompleto() throws Exception {
        String json = "{\"tipo\": \"flashcard\", \"frente\": \"Pregunta\"}";
        JsonNode node = mapper.readTree(json);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            FactoriaEjercicio.INSTANCIA.crearEjercicio(node);
        });
        assertTrue(ex.getMessage().contains("incompleto"));
    }
}