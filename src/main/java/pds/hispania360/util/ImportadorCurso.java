package pds.hispania360.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import pds.hispania360.factoria.FactoriaEjercicio;
import pds.hispania360.modelo.Bloque;
import pds.hispania360.modelo.Usuario;
import pds.hispania360.modelo.ejercicios.Ejercicio;
import pds.hispania360.persistencia.RepositorioCursoPersistente;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ImportadorCurso {

    // Constantes para nombres de campos JSON/YAML
    private static final String CAMPO_TITULO = "titulo";
    private static final String CAMPO_DESCRIPCION = "descripcion";
    private static final String CAMPO_BLOQUES = "bloques";
    private static final String CAMPO_EJERCICIOS = "ejercicios";
    private static final String CAMPO_FECHA_CREACION = "fechaCreacion";

    private final RepositorioCursoPersistente repositorioCurso;
    private final FactoriaEjercicio factoriaEjercicio;
    private String ultimoError = "";

    public ImportadorCurso(RepositorioCursoPersistente repositorioCurso, FactoriaEjercicio factoriaEjercicio) {
        this.repositorioCurso = repositorioCurso;
        this.factoriaEjercicio = factoriaEjercicio;
    }

    public String getUltimoError() {
        return ultimoError;
    }

    public boolean importarDesdeArchivo(File archivo, Usuario creador) {
        this.ultimoError = ""; 
        JsonNode j = leerArchivoCurso(archivo);
        if (j != null) {
            if (creador == null) {
                this.ultimoError = "El usuario creador no puede ser nulo.";
                return false;
            }
            return validarYCrearCurso(j, creador);
        } else {
            // leerArchivoCurso ya establece ultimoError
            if (this.ultimoError.isEmpty()) {
                this.ultimoError = "No se pudo leer el archivo seleccionado.";
            }
            return false;
        }
    }


    private JsonNode leerArchivoCurso(File archivo) {
        String nombre = archivo.getName().toLowerCase();
        ObjectMapper mapper = nombre.endsWith(".json") ? new ObjectMapper() : new ObjectMapper(new YAMLFactory());

        try {
            return mapper.readTree(archivo);
        } catch (IOException e) {
            this.ultimoError = "Error al parsear el archivo: " + e.getMessage();
            System.err.println(this.ultimoError);
            return null;
        }
    }

    private String obtenerCampoObligatorio(JsonNode nodo, String campo) {
        if (nodo.has(campo) && nodo.get(campo).isTextual()) {
             String value = nodo.get(campo).asText();
             if (!value.trim().isEmpty()) {
                 return value;
             }
        }
        this.ultimoError = "El campo '" + campo + "' es obligatorio, no puede estar vacío o no es de tipo texto.";
        System.err.println(this.ultimoError);
        return null;
    }


    private ArrayList<Ejercicio> validarYConstruirEjercicios(JsonNode bloqueNode) {
        ArrayList<Ejercicio> ejercicios = new ArrayList<>();
        if (bloqueNode.has(CAMPO_EJERCICIOS) && bloqueNode.get(CAMPO_EJERCICIOS).isArray()) {
            for (JsonNode ejerNode : bloqueNode.get(CAMPO_EJERCICIOS)) {
                // Pasamos el mensaje de error de la factoría si falla
                Ejercicio e = factoriaEjercicio.crearEjercicio(ejerNode); // Llama al método de la factoría
                if (e != null) {
                    ejercicios.add(e);
                } else {
                    // La factoría ya establece el error
                    this.ultimoError = factoriaEjercicio.getUltimoError(); // Obtener error de la factoría 
                    if (this.ultimoError == null || this.ultimoError.isEmpty()) {
                         this.ultimoError = "Error desconocido al crear un ejercicio dentro del bloque.";
                    }
                    // Logueamos el error específico del ejercicio que falló
                    System.err.println("Error al crear ejercicio: " + this.ultimoError);
                    return null; // Error en un ejercicio invalida el bloque
                }
            }
             if (ejercicios.isEmpty()) {
                 this.ultimoError = "El bloque debe contener al menos un ejercicio válido.";
                 System.err.println(this.ultimoError);
                 return null;
             }
        } else {
            this.ultimoError = "El campo '" + CAMPO_EJERCICIOS + "' es obligatorio y debe ser un array en un bloque.";
            System.err.println(this.ultimoError);
            return null;
        }
        return ejercicios;
    }


    private Bloque validarBloque(JsonNode j){
        String titulo = obtenerCampoObligatorio(j, CAMPO_TITULO);
        if (titulo == null) return null; // Error ya establecido

        String descripcion = obtenerCampoObligatorio(j, CAMPO_DESCRIPCION);
         if (descripcion == null) return null; // Error ya establecido


        ArrayList<Ejercicio> ejercicios = validarYConstruirEjercicios(j);
        if (ejercicios == null) {
            // El error ya fue establecido y loggeado en validarYConstruirEjercicios
            return null;
        }

        return new Bloque(titulo, descripcion, ejercicios);
    }

    private boolean validarMetadatosCurso(JsonNode j) {
        String titulo = obtenerCampoObligatorio(j, CAMPO_TITULO);
         if (titulo == null) return false; // Error ya establecido

        String descripcion = obtenerCampoObligatorio(j, CAMPO_DESCRIPCION);
        if (descripcion == null) return false; // Error ya establecido

        return true;
    }

    private ArrayList<Bloque> validarYConstruirBloques(JsonNode j) {
        ArrayList<Bloque> bloques = new ArrayList<>();
        if (j.has(CAMPO_BLOQUES) && j.get(CAMPO_BLOQUES).isArray()) {
            for (JsonNode bloqueNode : j.get(CAMPO_BLOQUES)) {
                Bloque bloque = validarBloque(bloqueNode);
                if (bloque != null) {
                    bloques.add(bloque);
                } else {
                    // El error ya fue establecido y loggeado en validarBloque o sus llamadas
                    return null; // Error en un bloque invalida el curso
                }
            }
             if (bloques.isEmpty()) {
                 this.ultimoError = "El curso debe contener al menos un bloque válido.";
                 System.err.println(this.ultimoError);
                 return null;
             }
        } else {
            this.ultimoError = "El campo '" + CAMPO_BLOQUES + "' es obligatorio y debe ser un array en el curso.";
            System.err.println(this.ultimoError);
            return null;
        }
        return bloques;
    }

    private LocalDate parsearFechaCreacion(JsonNode j) {
        String fechaStr = obtenerCampoObligatorio(j, CAMPO_FECHA_CREACION);
        if (fechaStr == null) {
             // Error ya establecido
            return null;
        }
        try {
            return LocalDate.parse(fechaStr);
        } catch (Exception ex) {
            this.ultimoError = "El campo '" + CAMPO_FECHA_CREACION + "' tiene un formato inválido (esperado YYYY-MM-DD).";
            System.err.println(this.ultimoError);
            return null;
        }
    }


    private boolean validarYCrearCurso(JsonNode j, Usuario creador){
        if (!validarMetadatosCurso(j)) {
            return false; // Error ya establecido
        }
        // Obtenemos los valores de nuevo, ya sabemos que existen y son válidos por la validación previa
        String titulo = j.get(CAMPO_TITULO).asText();
        String descripcion = j.get(CAMPO_DESCRIPCION).asText();

        ArrayList<Bloque> bloques = validarYConstruirBloques(j);
        if (bloques == null) {
            return false; // Error ya establecido y loggeado
        }

        LocalDate fechaCreacion = parsearFechaCreacion(j);
        if (fechaCreacion == null) {
            return false; // Error ya establecido y loggeado
        }

        // Creamos el curso usando el repositorio 
        repositorioCurso.crearCurso(titulo, descripcion, creador, bloques, fechaCreacion);
        
        return true;
    }
}
