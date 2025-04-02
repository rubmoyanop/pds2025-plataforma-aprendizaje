package pds.hispania360.factoria;

import com.fasterxml.jackson.databind.JsonNode;

import pds.hispania360.modelo.ejercicios.*;

import java.util.ArrayList;
import java.util.List;

public enum FactoriaEjercicio {
    INSTANCIA;
    
    public Ejercicio crearEjercicio(JsonNode ejercicioNode) {
        if (!ejercicioNode.has("tipo")) {
            throw new IllegalArgumentException("El ejercicio debe tener un tipo");
        }
        
        String tipo = ejercicioNode.get("tipo").asText();
        
        switch (tipo) {
            case "respuesta_multiple":
                return crearRespuestaMultiple(ejercicioNode);
            case "rellenar_hueco":
                return crearRellenarHueco(ejercicioNode);
            case "flashcard":
                return crearFlashcard(ejercicioNode);
            default:
                throw new IllegalArgumentException("Tipo de ejercicio no soportado: " + tipo);
        }
    }
    
    private static RespuestaMultiple crearRespuestaMultiple(JsonNode node) {
        if (!node.has("enunciado") || !node.has("opciones") || !node.has("respuestaCorrecta")) {
            throw new IllegalArgumentException("Ejercicio de respuesta múltiple incompleto");
        }
        
        String enunciado = node.get("enunciado").asText();
        String respuestaCorrecta = node.get("respuestaCorrecta").asText();
        List<String> opciones = new ArrayList<>();
        
        JsonNode opcionesNode = node.get("opciones");
        if (opcionesNode.isArray()) {
            for (JsonNode opcion : opcionesNode) {
                opciones.add(opcion.asText());
            }
        }
        
        return new RespuestaMultiple(enunciado, opciones, respuestaCorrecta);
    }
    
    private static RellenarHueco crearRellenarHueco(JsonNode node) {
        if (!node.has("enunciado") || !node.has("respuestaCorrecta")) {
            throw new IllegalArgumentException("Ejercicio de rellenar hueco incompleto");
        }
        
        String enunciado = node.get("enunciado").asText();
        String respuestaCorrecta = node.get("respuestaCorrecta").asText();
        
        return new RellenarHueco(enunciado, respuestaCorrecta);
    }
    
    private static Flashcard crearFlashcard(JsonNode node) {
        if (!node.has("frente") || !node.has("detras")) {
            throw new IllegalArgumentException("Ejercicio de flashcard incompleto");
        }
        
        String frente = node.get("frente").asText();
        String detras;
        
        JsonNode detrasNode = node.get("detras");
        if (detrasNode.isNumber()) {
            detras = detrasNode.asText();
        } else {
            detras = detrasNode.asText();
        }
        
        return new Flashcard(frente, detras);
    }
}