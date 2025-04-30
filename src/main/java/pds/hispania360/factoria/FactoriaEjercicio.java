package pds.hispania360.factoria;

import com.fasterxml.jackson.databind.JsonNode;
import pds.hispania360.modelo.ejercicios.*;

import java.util.ArrayList;
import java.util.List;

public enum FactoriaEjercicio {
    INSTANCIA;

    private String ultimoError = "";

    // Constantes para nombres de campos comunes
    private static final String CAMPO_TIPO = "tipo";
    private static final String CAMPO_ENUNCIADO = "enunciado";
    private static final String CAMPO_RESPUESTA_CORRECTA = "respuestaCorrecta";
    private static final String CAMPO_OPCIONES = "opciones";
    private static final String CAMPO_FRENTE = "frente";
    private static final String CAMPO_DETRAS = "detras";

    // Tipos de ejercicio
    private static final String TIPO_RESPUESTA_MULTIPLE = "respuesta_multiple";
    private static final String TIPO_RELLENAR_HUECO = "rellenar_hueco";
    private static final String TIPO_FLASHCARD = "flashcard";

    public String getUltimoError() {
        return ultimoError;
    }

    public Ejercicio crearEjercicio(JsonNode ejercicioNode) {
        this.ultimoError = ""; // Limpiar error previo

        if (!ejercicioNode.hasNonNull(CAMPO_TIPO) || !ejercicioNode.get(CAMPO_TIPO).isTextual()) {
            this.ultimoError = "El ejercicio debe tener un campo '" + CAMPO_TIPO + "' de tipo texto no nulo.";
            System.err.println(this.ultimoError);
            return null;
        }

        String tipo = ejercicioNode.get(CAMPO_TIPO).asText();

        try {
            switch (tipo) {
                case TIPO_RESPUESTA_MULTIPLE:
                    return crearRespuestaMultiple(ejercicioNode);
                case TIPO_RELLENAR_HUECO:
                    return crearRellenarHueco(ejercicioNode);
                case TIPO_FLASHCARD:
                    return crearFlashcard(ejercicioNode);
                default:
                    this.ultimoError = "Tipo de ejercicio no soportado: " + tipo;
                    System.err.println(this.ultimoError);
                    return null;
            }
        } catch (IllegalArgumentException e) {
            this.ultimoError = e.getMessage();
            System.err.println(this.ultimoError);
            return null;
        }
    }

    private String obtenerCampoTextoObligatorio(JsonNode node, String campo) {
        if (node.hasNonNull(campo) && node.get(campo).isTextual()) {
            String valor = node.get(campo).asText();
            if (!valor.trim().isEmpty()) {
                return valor;
            }
        }
        this.ultimoError = "El campo '" + campo + "' es obligatorio, no nulo, no vacío y debe ser de tipo texto.";
        return null;
    }

    private RespuestaMultiple crearRespuestaMultiple(JsonNode node) {
        String enunciado = obtenerCampoTextoObligatorio(node, CAMPO_ENUNCIADO);
        if (enunciado == null) return null;

        String respuestaCorrecta = obtenerCampoTextoObligatorio(node, CAMPO_RESPUESTA_CORRECTA);
        if (respuestaCorrecta == null) return null;

        List<String> opciones = new ArrayList<>();
        if (!node.hasNonNull(CAMPO_OPCIONES) || !node.get(CAMPO_OPCIONES).isArray() || node.get(CAMPO_OPCIONES).isEmpty()) {
            this.ultimoError = "El campo '" + CAMPO_OPCIONES + "' es obligatorio, debe ser un array no vacío.";
            System.err.println(this.ultimoError);
            return null;
        }

        JsonNode opcionesNode = node.get(CAMPO_OPCIONES);
        boolean opcionValidaEncontrada = false;
        for (JsonNode opcionNode : opcionesNode) {
            if (opcionNode.isTextual()) {
                String opcion = opcionNode.asText();
                if (!opcion.trim().isEmpty()) {
                    opciones.add(opcion);
                    opcionValidaEncontrada = true;
                }
            }
        }

        if (!opcionValidaEncontrada) {
            this.ultimoError = "El array '" + CAMPO_OPCIONES + "' debe contener al menos una opción de texto válida (no vacía).";
            System.err.println(this.ultimoError);
            return null;
        }

        if (!opciones.contains(respuestaCorrecta)) {
            this.ultimoError = "La respuesta correcta ('" + respuestaCorrecta + "') debe ser una de las opciones proporcionadas.";
            System.err.println(this.ultimoError);
            return null;
        }

        return new RespuestaMultiple(enunciado, opciones, respuestaCorrecta);
    }

    private RellenarHueco crearRellenarHueco(JsonNode node) {
        String enunciado = obtenerCampoTextoObligatorio(node, CAMPO_ENUNCIADO);
        if (enunciado == null) return null;

        String respuestaCorrecta = obtenerCampoTextoObligatorio(node, CAMPO_RESPUESTA_CORRECTA);
        if (respuestaCorrecta == null) return null;

        if (!enunciado.contains(RellenarHueco.PLACEHOLDER)) {
            this.ultimoError = "El enunciado del ejercicio de rellenar hueco debe contener el placeholder '" + RellenarHueco.PLACEHOLDER + "'.";
            System.err.println(this.ultimoError);
            return null;
        }

        return new RellenarHueco(enunciado, respuestaCorrecta);
    }

    private Flashcard crearFlashcard(JsonNode node) {
        String frente = obtenerCampoTextoObligatorio(node, CAMPO_FRENTE);
        if (frente == null) return null;

        String detras;
        if (node.hasNonNull(CAMPO_DETRAS) && (node.get(CAMPO_DETRAS).isTextual() || node.get(CAMPO_DETRAS).isNumber())) {
            detras = node.get(CAMPO_DETRAS).asText();
            if (detras.trim().isEmpty()) {
                this.ultimoError = "El campo '" + CAMPO_DETRAS + "' no puede estar vacío.";
                System.err.println(this.ultimoError);
                return null;
            }
        } else {
            this.ultimoError = "El campo '" + CAMPO_DETRAS + "' es obligatorio y debe ser de tipo texto o número no nulo.";
            System.err.println(this.ultimoError);
            return null;
        }

        return new Flashcard(frente, detras);
    }
}