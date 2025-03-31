package pds.hispania360.controlador;

import pds.hispania360.modelo.Curso;
import pds.hispania360.repositorio.GestorCurso;
import pds.hispania360.repositorio.GestorUsuario;
import pds.hispania360.sesion.Sesion;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos JSON y YMAL", "json", "ymal");
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
        ObjectMapper mapper = new ObjectMapper();
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

    public void importarCurso(Curso c){
        GestorCurso.INSTANCIA.agregarCurso(c);
    }
    

}
