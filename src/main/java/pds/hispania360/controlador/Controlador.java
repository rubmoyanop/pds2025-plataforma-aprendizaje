package pds.hispania360.controlador;

import pds.hispania360.factoria.FactoriaEjercicio;
import pds.hispania360.factoria.FactoriaEstrategia;
import pds.hispania360.modelo.*;
import pds.hispania360.modelo.ejercicios.*;
import pds.hispania360.persistencia.RepositorioCursoPersistente;
import pds.hispania360.persistencia.RepositorioUsuarioPersistente;
import pds.hispania360.sesion.Sesion;
import pds.hispania360.vista.core.GestorVentanas;
import pds.hispania360.vista.core.TipoVentana;


import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.databind.JsonNode;

public enum Controlador {
    INSTANCIA;

    private String ultimoError = "";

    public String getUltimoError() {
        return ultimoError;
    }
    
    /**
     * Registra un nuevo usuario en el sistema
     * @param email Email del usuario
     * @param nombre Nombre del usuario
     * @param password Contraseña del usuario
     * @param esCreador Indica si el usuario tiene rol creador
     * @return true si el registro fue exitoso, false en caso contrario
     */
    public boolean registrarUsuario(String email, String nombre, String password, boolean esCreador){
        return RepositorioUsuarioPersistente.INSTANCIA.crearUsuario(email, nombre, password, esCreador);
    }
    
    public boolean iniciarSesion(String email, String password) {
        Optional<Usuario> usuarioOpt = RepositorioUsuarioPersistente.INSTANCIA.autenticarUsuario(email, password);
        if (usuarioOpt.isPresent()) {
            Sesion.INSTANCIA.setUsuarioActual(usuarioOpt.get());
            return true;
        }
        return false;
    }
    
    
    public void cerrarSesion() {
    	Sesion.INSTANCIA.cerrarSesion();
    }
    
    private File seleccionarFicheroCurso(){
        // Obtener la ruta absoluta de la carpeta resources
        String resourcesPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources";
        File resourcesDir = new File(resourcesPath);

        // Creamos el buscador de archivos, le ponemos los filtros convenientes y lo activamos
        JFileChooser fileChooser = new JFileChooser(resourcesDir);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos JSON y YMAL", "json", "ymal", "yml");
        fileChooser.setFileFilter(filter);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        // Mostramos el diálogo de selección.
        int resultado = fileChooser.showOpenDialog(null);

        // Comprobamos el fichero y lo mandamos. En su defecto, devuelve null
        if(resultado == JFileChooser.APPROVE_OPTION) return fileChooser.getSelectedFile();
        return null;
    }

    public boolean importarCurso(){
        ultimoError = "";
        if(!Sesion.INSTANCIA.esCreador()){
            ultimoError = "Solo los usuarios creadores pueden importar cursos.";
            return false;
        }
        File f = seleccionarFicheroCurso();
        if(f != null){
            JsonNode j = leerArchivoCurso(f);
            if(j!= null){
               return validarCurso(j);
            } else {
                if (ultimoError.isEmpty()) {
                    ultimoError = "No se pudo leer el archivo seleccionado.";
                }
            }
        } else {
            ultimoError = "No se seleccionó ningún archivo.";
        }
        return false;
    }

    private JsonNode leerArchivoCurso(File archivo) {
        String nombre = archivo.getName().toLowerCase();
        ObjectMapper mapper = nombre.endsWith(".json") ? new ObjectMapper() : new ObjectMapper(new YAMLFactory());

        try {
            return mapper.readTree(archivo);
        } catch (IOException e) {
            ultimoError = "Error al parsear el archivo: " + e.getMessage();
            System.err.println(ultimoError);
            return null;
        }
    }

    private String obtenerCampoObligatorio(JsonNode nodo, String campo) {
        if (nodo.has(campo)) {
            return nodo.get(campo).asText();
        }
        throw new IllegalArgumentException("El campo '" + campo + "' es obligatorio.");
    }

    private Bloque validarBloque(JsonNode j){
        try {
            String titulo = obtenerCampoObligatorio(j, "titulo");
            String descripcion = obtenerCampoObligatorio(j, "descripcion");
            ArrayList<Ejercicio> ejercicios = new ArrayList<>();
            if(j.has("ejercicios") && j.get("ejercicios").isArray()){
                for (JsonNode ejerNode : j.get("ejercicios")) {
                    Ejercicio e = FactoriaEjercicio.INSTANCIA.crearEjercicio(ejerNode);
                    if (e != null) {
                        ejercicios.add(e);
                    }
                }
            } else {
                throw new IllegalArgumentException("El campo 'ejercicios' es obligatorio y debe ser un array.");
            }
            return new Bloque(titulo, descripcion, ejercicios);
        } catch (IllegalArgumentException ex) {
            ultimoError = "Error al validar bloque: " + ex.getMessage();
            System.err.println(ultimoError);
            return null;
        }
    }

    //Validamos el archivo
    private boolean validarCurso(JsonNode j){
        try {
            String titulo = obtenerCampoObligatorio(j, "titulo");
            String descripcion = obtenerCampoObligatorio(j, "descripcion");
            ArrayList<Bloque> bloques = new ArrayList<>();
            if(j.has("bloques") && j.get("bloques").isArray()){
                for (JsonNode bloqueNode : j.get("bloques")) {
                    Bloque bloque = validarBloque(bloqueNode);
                    if (bloque != null) {
                        bloques.add(bloque);
                    }
                }
            } else {
                throw new IllegalArgumentException("El campo 'bloques' es obligatorio y debe ser un array.");
            }
            String fecha = obtenerCampoObligatorio(j, "fechaCreacion");
            LocalDate fechaCreacion = LocalDate.parse(fecha);
            RepositorioCursoPersistente.INSTANCIA.crearCurso(titulo, descripcion, Sesion.INSTANCIA.getUsuarioActual(), bloques, fechaCreacion);
            return true;
        } catch (IllegalArgumentException ex) {
            ultimoError = "Error al validar curso: " + ex.getMessage();
            System.err.println(ultimoError);
            return false;
        }
    }

    public boolean isRealizado(int numBloque){
        if(!Sesion.INSTANCIA.haySesion()){
            return false;
        }
        // Delegar la responsabilidad al usuario
        return Sesion.INSTANCIA.getUsuarioActual().haRealizadoBloque(Sesion.INSTANCIA.getCursoActual().getId(), numBloque);
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

    public boolean cursoEmpezado(){
        if(getProgresoCursoActual().getProgreso() == 0){
            return false;
        }
        return true;
    }

    public void eliminarProgresoCurso(){
        if(Sesion.INSTANCIA.haySesionConCurso()){
            Sesion.INSTANCIA.getUsuarioActual().eliminarProgresoCurso(Sesion.INSTANCIA.getCursoActual().getId());
        }
    }

    public boolean configurarEstrategia(ProgresoCurso progresoCurso, String estrategia){
        if(progresoCurso != null && estrategia != null){
            progresoCurso.setEstrategia(FactoriaEstrategia.INSTANCIA.crearEstrategia(estrategia));
            return true;
        }
        return false;
    }

    public Ejercicio siguienteEjercicio(){
        Ejercicio ejercicio = getProgresoCursoActual().SiguienteEjercicio();
        Sesion.INSTANCIA.setEjercicioActual(ejercicio);
        return ejercicio;
    }

    public Ejercicio getEjercicioActual(){
        return Sesion.INSTANCIA.getEjercicioActual();
    }

    public boolean mostrarEjercicio(){
         Ejercicio ejercicio = this.siguienteEjercicio();
         if(ejercicio != null){
            
            // Mostrar el ejercicio en la ventana correspondiente
            GestorVentanas.INSTANCIA.mostrarVentana(getTipoVentana(ejercicio));
            return true;   
            }
            return false; 

    }

    public void actualizarProgresoCurso(){
        if(Sesion.INSTANCIA.haySesionConCurso()){
            Sesion.INSTANCIA.getUsuarioActual().actualizarProgresoCurso(Sesion.INSTANCIA.getCursoActual().getId());
        }
    }
   
    public void actualizarRacha(boolean acierto){
        if(Sesion.INSTANCIA.haySesionConCurso()){
            Sesion.INSTANCIA.getUsuarioActual().actualizarRacha(acierto);
        }
    }

    public TipoVentana getTipoVentana(Ejercicio ejercicio){
        if(ejercicio instanceof Flashcard){
            return TipoVentana.FLASHCARD;
        }
        else if(ejercicio instanceof RellenarHueco){
            return TipoVentana.RELLENAR_HUECO;
        }
        else if(ejercicio instanceof RespuestaMultiple){
            return TipoVentana.RESPUESTA_MULTIPLE;
        }
       
        return null;
    }
  

    public void actualizarTiempoUso(long tiempo){
        if(Sesion.INSTANCIA.haySesion()){
            System.out.println("Tiempo de uso: " + tiempo);
            Sesion.INSTANCIA.getUsuarioActual().aumentarTiempoTotal(tiempo);
            Sesion.INSTANCIA.setTiempoInicioSesion(System.currentTimeMillis());
        }
    }

    public void actualizarExperiencia(int experiencia){
        if(Sesion.INSTANCIA.haySesion()){
            Sesion.INSTANCIA.getUsuarioActual().aumentarExperiencia(experiencia);
        }
    }
  
}
