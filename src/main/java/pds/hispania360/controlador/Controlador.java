package pds.hispania360.controlador;

import pds.hispania360.modelo.Bloque;
import pds.hispania360.modelo.Curso;
import pds.hispania360.repositorio.GestorCurso;
import pds.hispania360.repositorio.GestorUsuario;
import pds.hispania360.sesion.Sesion;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.databind.JsonNode;

public enum Controlador {
    INSTANCIA;
    
    /**
     * Registra un nuevo usuario en el sistema
     * @param email Email del usuario
     * @param nombre Nombre del usuario
     * @param password Contraseña del usuario
     * @param esCreador Indica si el usuario tiene rol creador
     * @return true si el registro fue exitoso, false en caso contrario
     */
    public boolean registrarUsuario(String email, String nombre, String password, boolean esCreador){
        return GestorUsuario.INSTANCIA.crearUsuario(email, nombre, password, esCreador);
    }
    
    public boolean iniciarSesion(String email, String password) {
    	if(GestorUsuario.INSTANCIA.autenticarUsuario(email, password).isPresent()) {
    		Sesion.INSTANCIA.setUsuarioActual(GestorUsuario.INSTANCIA.autenticarUsuario(email, password).get());
    		return true;
    	}
    	return false;
    }
    
    public void cerrarSesion() {
    	Sesion.INSTANCIA.cerrarSesion();
    }
    
    public File seleccionarCurso(){
        //Creamos el buscador de archivos, le ponemos los filtros convenientes y lo activamos
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos JSON y YMAL", "json", "ymal", "yml");
        fileChooser.setFileFilter(filter);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        //Mostramos el diálogo de selección.
        //TODO: PONER EL PARENT CORRESPONDIENTE
        int resultado = fileChooser.showOpenDialog(null);

        //Comprobamos el fichero y lo mandamos. En su defecto, devuelve null
        if(resultado == JFileChooser.APPROVE_OPTION) return fileChooser.getSelectedFile();
        return null;
    }

    //Parseamos el archivo que acabamos de seleccionar 
    public JsonNode leerArchivoCurso(File archivo){
        //Distinguimos entre archivos JSON y YMAL
        String nombre = archivo.getName().toLowerCase();
        ObjectMapper mapper = null;
        
        if (nombre.endsWith(".json")) {
            mapper = new ObjectMapper();
        } 
        else{
            mapper = new ObjectMapper(new YAMLFactory());
        }

        try {
            // Lee el árbol JSON a partir del archivo
            JsonNode rootNode = mapper.readTree(archivo);
            return rootNode;
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al parsear el archivo: " + e.getMessage());
        }
        return null;
    }

    //public boolean isJson(JsonNode j){}

    public Bloque validarBloque(JsonNode j){
        
    }

    //Validamos el archivo
    public boolean validarCurso(JsonNode j){
        String titulo;
        String descripcion;
        ArrayList<Bloque> bloques = new ArrayList<>();
        LocalDateTime fechaCreacion;
        
        if(j.has("titulo")){
            titulo = j.get("titulo").asText();
        }
        else return false; //throw new IllegalArgumentException("El campo 'titulo' es obligatorio.");

        if(j.has("descripcion")){
            descripcion = j.get("descripcion").asText();
        }
        else return false; //throw new IllegalArgumentException("El campo 'descripcion' es obligatorio.");

        if(j.has("bloques") && j.get("bloques").isArray()){
            for (JsonNode bloqueNode : j.get("bloques")) {
                Bloque bloque = validarBloque(bloqueNode);
                bloques.add(bloque);
            }
        }
        else return false; //throw new IllegalArgumentException("El campo 'bloque' es obligatorio y debe ser un array.");
    
        if(j.has("fechaCreacion")){
            String fecha = j.get("fechaCreacion").asText();
            fechaCreacion = LocalDateTime.parse(fecha);
        }
        else return false; //throw new IllegalArgumentException("El campo 'fechaCreacion' es obligatorio.");

        GestorCurso.INSTANCIA.crearCurso(titulo, descripcion, Sesion.INSTANCIA.getUsuarioActual(), bloques, fechaCreacion);
        return true;
    };

    
    //Cambiar a tipo Curso
    public boolean importarCurso(){
        File f = seleccionarCurso();
        if(f != null){
            JsonNode j = leerArchivoCurso(f);
            if(j!= null){
                validarCurso(j);
                return true;
            }
        }
        return false;
    }

}
