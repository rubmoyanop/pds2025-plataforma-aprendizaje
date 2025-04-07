package pds.hispania360.controlador;

import pds.hispania360.factoria.FactoriaEjercicio;
import pds.hispania360.factoria.FactoriaEstrategia;
import pds.hispania360.modelo.*;
import pds.hispania360.modelo.ejercicios.*;
import pds.hispania360.repositorio.*;
import pds.hispania360.sesion.Sesion;
import pds.hispania360.vista.core.GestorVentanas;
import pds.hispania360.vista.core.TipoVentana;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
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
    
    private File seleccionarFicheroCurso(){
        //Creamos el buscador de archivos, le ponemos los filtros convenientes y lo activamos
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos JSON y YMAL", "json", "ymal", "yml");
        fileChooser.setFileFilter(filter);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        //Mostramos el diálogo de selección.
        int resultado = fileChooser.showOpenDialog(null);

        //Comprobamos el fichero y lo mandamos. En su defecto, devuelve null
        if(resultado == JFileChooser.APPROVE_OPTION) return fileChooser.getSelectedFile();
        return null;
    }

    public boolean importarCurso(){
        if(!Sesion.INSTANCIA.esCreador()){
            return false;
        }
        File f = seleccionarFicheroCurso();
        if(f != null){
            JsonNode j = leerArchivoCurso(f);
            if(j!= null){
               return validarCurso(j);
            }
        }
        return false;
    }

    //Parseamos el archivo que acabamos de seleccionar 
    private JsonNode leerArchivoCurso(File archivo){
        //Distinguimos entre archivos JSON y YMAL
        String nombre = archivo.getName().toLowerCase();
        ObjectMapper mapper = null;
        
        if (nombre.endsWith(".json")) {
            mapper = new ObjectMapper();
        } 
        else{ //Hemos filtrado así que la única opción es que sea YMAL
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

    private Bloque validarBloque(JsonNode j){
        String titulo;
        String descripcion;
        ArrayList<Ejercicio> ejercicios = new ArrayList<>();
        if(j.has("titulo")){
            titulo = j.get("titulo").asText();
        }
        else return null; //throw new IllegalArgumentException("El campo 'titulo' es obligatorio.");

        if(j.has("descripcion")){
            descripcion = j.get("descripcion").asText();
        }
        else return null; //throw new IllegalArgumentException("El campo 'desc
        
        if(j.has("ejercicios") && j.get("ejercicios").isArray()){
            for (JsonNode ejerNode : j.get("ejercicios")) {
                Ejercicio e = FactoriaEjercicio.INSTANCIA.crearEjercicio(ejerNode);
                ejercicios.add(e);
            }
        }
        else return null;

        Bloque b = new Bloque(titulo, descripcion, ejercicios);
        return b;
    }

    //Validamos el archivo
    private boolean validarCurso(JsonNode j){
        String titulo;
        String descripcion;
        ArrayList<Bloque> bloques = new ArrayList<>();
        LocalDate fechaCreacion;
        
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
            fechaCreacion = LocalDate.parse(fecha);
        }
        else return false; //throw new IllegalArgumentException("El campo 'fechaCreacion' es obligatorio.");

        GestorCurso.INSTANCIA.crearCurso(titulo, descripcion, Sesion.INSTANCIA.getUsuarioActual(), bloques, fechaCreacion);
        return true;
    };

    public boolean isRealizado(int numBloque){
        // Verificar que existe un usuario activo y un curso asignado
        if(!Sesion.INSTANCIA.haySesion()){
            return false;
        }
        return Sesion.INSTANCIA.getUsuarioActual().isRealizado(
            Sesion.INSTANCIA.getCursoActual().getId(), numBloque);
    }

    public boolean isSiguienteBloque(int numBloque){
        // Verificar que existe un usuario activo y un curso asignado
        if(!Sesion.INSTANCIA.haySesionConCurso()){
            return false;
        }
        return Sesion.INSTANCIA.getUsuarioActual().isSiguienteBloque(
            Sesion.INSTANCIA.getCursoActual().getId(), numBloque);
    }


   

    public void crearProgresoCurso(){
        if(Sesion.INSTANCIA.haySesionConCurso()){
            Sesion.INSTANCIA.getUsuarioActual().empezarCurso(Sesion.INSTANCIA.getCursoActual());
        }
    }

    public ProgresoCurso getProgresoCursoActual(){
        if(!Sesion.INSTANCIA.haySesionConCurso()){
            return null;
        }
       return Sesion.INSTANCIA.getUsuarioActual().getProgresoCurso(Sesion.INSTANCIA.getCursoActual().getId());
    }

    public boolean existeProgresoCurso(){
       return getProgresoCursoActual() != null;
    }

    public boolean configurarEstrategia(ProgresoCurso progresoCurso, String estrategia){
        if(progresoCurso != null && estrategia != null){
            progresoCurso.setEstrategia(FactoriaEstrategia.INSTANCIA.crearEstrategia(estrategia));
            return true;
        }
        return false;
    }

  
}
