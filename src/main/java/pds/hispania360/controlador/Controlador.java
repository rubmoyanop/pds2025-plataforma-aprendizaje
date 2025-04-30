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
    private final RepositorioUsuarioPersistente repositorioUsuario;
    private final RepositorioCursoPersistente repositorioCurso;
    private final Sesion sesion;
    private final FactoriaEstrategia factoriaEstrategia;

    Controlador() {
        this.repositorioUsuario = RepositorioUsuarioPersistente.INSTANCIA;
        this.repositorioCurso = RepositorioCursoPersistente.INSTANCIA;
        this.sesion = Sesion.INSTANCIA;
        this.factoriaEstrategia = FactoriaEstrategia.INSTANCIA;
    }

    public String getUltimoError() {
        return ultimoError;
    }

    public boolean registrarUsuario(String email, String nombre, String password, boolean esCreador){
        return repositorioUsuario.crearUsuario(email, nombre, password, esCreador);
    }

    public boolean iniciarSesion(String email, String password) {
        Optional<Usuario> usuarioOpt = repositorioUsuario.autenticarUsuario(email, password);
        usuarioOpt.ifPresent(sesion::setUsuarioActual);
        return usuarioOpt.isPresent();
    }

    public void cerrarSesion() {
        sesion.cerrarSesion();
    }

    private File seleccionarFicheroCurso(){
        String resourcesPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources";
        File resourcesDir = new File(resourcesPath);

        JFileChooser fileChooser = new JFileChooser(resourcesDir);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos JSON y YMAL", "json", "ymal", "yml");
        fileChooser.setFileFilter(filter);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int resultado = fileChooser.showOpenDialog(null);

        if(resultado == JFileChooser.APPROVE_OPTION) return fileChooser.getSelectedFile();
        return null;
    }

    public boolean importarCurso(){
        ultimoError = "";
        if(!sesion.esCreador()){
            ultimoError = "Solo los usuarios creadores pueden importar cursos.";
            return false;
        }
        File f = seleccionarFicheroCurso();
        if(f != null){
            JsonNode j = leerArchivoCurso(f);
            if(j!= null){
               Usuario creador = sesion.getUsuarioActual();
               if (creador == null) {
                   ultimoError = "No hay un usuario creador en la sesión.";
                   return false;
               }
               return validarYCrearCurso(j, creador);
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
        return null;
    }

    private Bloque validarBloque(JsonNode j){
        String titulo = obtenerCampoObligatorio(j, "titulo");
        String descripcion = obtenerCampoObligatorio(j, "descripcion");
        if (titulo == null) {
            ultimoError = "El campo 'titulo' es obligatorio en un bloque.";
            System.err.println(ultimoError);
            return null;
        }
        if (descripcion == null) {
            ultimoError = "El campo 'descripcion' es obligatorio en un bloque.";
            System.err.println(ultimoError);
            return null;
        }
        ArrayList<Ejercicio> ejercicios = new ArrayList<>();
        if(j.has("ejercicios") && j.get("ejercicios").isArray()){
            for (JsonNode ejerNode : j.get("ejercicios")) {
                Ejercicio e = FactoriaEjercicio.INSTANCIA.crearEjercicio(ejerNode);
                if (e != null) {
                    ejercicios.add(e);
                }
            }
        } else {
            ultimoError = "El campo 'ejercicios' es obligatorio y debe ser un array en un bloque.";
            System.err.println(ultimoError);
            return null;
        }
        return new Bloque(titulo, descripcion, ejercicios);
    }

    private boolean validarYCrearCurso(JsonNode j, Usuario creador){
        String titulo = obtenerCampoObligatorio(j, "titulo");
        String descripcion = obtenerCampoObligatorio(j, "descripcion");
        if (titulo == null) {
            ultimoError = "El campo 'titulo' es obligatorio en el curso.";
            System.err.println(ultimoError);
            return false;
        }
        if (descripcion == null) {
            ultimoError = "El campo 'descripcion' es obligatorio en el curso.";
            System.err.println(ultimoError);
            return false;
        }
        ArrayList<Bloque> bloques = new ArrayList<>();
        if(j.has("bloques") && j.get("bloques").isArray()){
            for (JsonNode bloqueNode : j.get("bloques")) {
                Bloque bloque = validarBloque(bloqueNode);
                if (bloque != null) {
                    bloques.add(bloque);
                } else {
                    return false;
                }
            }
        } else {
            ultimoError = "El campo 'bloques' es obligatorio y debe ser un array en el curso.";
            System.err.println(ultimoError);
            return false;
        }
        String fecha = obtenerCampoObligatorio(j, "fechaCreacion");
        if (fecha == null) {
            ultimoError = "El campo 'fechaCreacion' es obligatorio en el curso.";
            System.err.println(ultimoError);
            return false;
        }
        LocalDate fechaCreacion;
        try {
            fechaCreacion = LocalDate.parse(fecha);
        } catch (Exception ex) {
            ultimoError = "El campo 'fechaCreacion' tiene un formato inválido.";
            System.err.println(ultimoError);
            return false;
        }
        repositorioCurso.crearCurso(titulo, descripcion, creador, bloques, fechaCreacion);
        return true;
    }

    public boolean isRealizado(int numBloque){
        return sesion.haRealizadoBloqueActual(numBloque);
    }

    public boolean isSiguienteBloque(int numBloque){
        return sesion.isSiguienteBloqueActual(numBloque);
    }

    public void crearProgresoCurso(){
        sesion.empezarCursoActual();
    }

    public Optional<ProgresoCurso> getProgresoCursoActual(){
        return sesion.getProgresoCursoActual();
    }

    public boolean existeProgresoCurso(){
       return sesion.existeProgresoCursoActual();
    }

    public boolean cursoEmpezado(){
        return sesion.isCursoActualEmpezado();
    }

    public void eliminarProgresoCurso(){
        sesion.eliminarProgresoCursoActual();
    }

    public boolean configurarEstrategia(ProgresoCurso progresoCurso, String estrategiaNombre){
        if(progresoCurso != null && estrategiaNombre != null){
            EstrategiaAprendizaje estrategia = factoriaEstrategia.crearEstrategia(estrategiaNombre);
            if (estrategia != null) {
                 progresoCurso.setEstrategia(estrategia);
                 return true;
            } else {
                ultimoError = "Estrategia desconocida: " + estrategiaNombre;
                return false;
            }
        }
        return false;
    }

    public Ejercicio siguienteEjercicio(){
        Ejercicio ejercicio = sesion.obtenerSiguienteEjercicio();
        if (ejercicio == null) {
             Optional<ProgresoCurso> progresoOpt = sesion.getProgresoCursoActual();
             if (!progresoOpt.isPresent()) {
                 ultimoError = "No hay progreso de curso activo.";
             } else {
                 ultimoError = "No se pudo obtener el siguiente ejercicio.";
             }
        }
        return ejercicio;
    }

    public Ejercicio getEjercicioActual(){
        return sesion.getEjercicioActual();
    }

    public boolean mostrarEjercicio(){
        Ejercicio ejercicio = this.siguienteEjercicio();
        if(ejercicio != null){
            TipoVentana tipo = ejercicio.getTipoVentana();
            if (tipo != null) {
                GestorVentanas.INSTANCIA.mostrarVentana(tipo);
                return true;
            } else {
                ultimoError = "Tipo de ventana desconocido para el ejercicio.";
                return false;
            }
        }
        return false;
    }

    public void actualizarProgresoCurso(){
        sesion.actualizarProgresoCursoActual();
    }

    public void actualizarRacha(boolean acierto){
        sesion.actualizarRachaActual(acierto);
    }

    public void actualizarTiempoUso(){
        sesion.actualizarTiempoUsoActual(sesion.getTiempoInicioSesion());
        sesion.setTiempoInicioSesion(System.currentTimeMillis());
    }

    public void actualizarExperiencia(int experiencia){
        sesion.actualizarExperienciaActual(experiencia);
    }

}
