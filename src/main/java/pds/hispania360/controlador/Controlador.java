package pds.hispania360.controlador;

import pds.hispania360.factoria.FactoriaEjercicio;
import pds.hispania360.factoria.FactoriaEstrategia;
import pds.hispania360.modelo.*;
import pds.hispania360.modelo.ejercicios.Ejercicio;
import pds.hispania360.persistencia.RepositorioCursoPersistente;
import pds.hispania360.persistencia.RepositorioUsuarioPersistente;
import pds.hispania360.sesion.Sesion;
import pds.hispania360.util.ImportadorCurso;
import pds.hispania360.vista.core.GestorVentanas;
import pds.hispania360.vista.core.TipoVentana;

import java.io.File;
import java.util.Optional;

public enum Controlador {
    INSTANCIA;

    private String ultimoError = "";
    private final RepositorioUsuarioPersistente repositorioUsuario;
    private final RepositorioCursoPersistente repositorioCurso;
    private final Sesion sesion;
    private final FactoriaEstrategia factoriaEstrategia;
    private final ImportadorCurso importadorCurso;

    Controlador() {
        this.repositorioUsuario = RepositorioUsuarioPersistente.INSTANCIA;
        this.repositorioCurso = RepositorioCursoPersistente.INSTANCIA;
        this.sesion = Sesion.INSTANCIA;
        this.factoriaEstrategia = FactoriaEstrategia.INSTANCIA;
        this.importadorCurso = new ImportadorCurso(this.repositorioCurso, FactoriaEjercicio.INSTANCIA);
    }

    public String getUltimoError() {
        return ultimoError;
    }

    public boolean registrarUsuario(String email, String nombre, String password, boolean esCreador) {
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

    public boolean importarCurso(File archivoSeleccionado) {
        ultimoError = "";
        if (archivoSeleccionado == null) {
            ultimoError = "No se proporcionó ningún archivo para importar.";
            return false;
        }

        if (!sesion.esCreador()) {
            ultimoError = "Solo los usuarios creadores pueden importar cursos.";
            return false;
        }

        Usuario creador = sesion.getUsuarioActual();
        if (creador == null) {
            ultimoError = "No hay un usuario creador en la sesión.";
            return false;
        }

        boolean exito = importadorCurso.importarDesdeArchivo(archivoSeleccionado, creador);
        if (!exito) {
            this.ultimoError = importadorCurso.getUltimoError();
        }
        return exito;
    }

    public boolean isRealizado(int numBloque) {
        return sesion.haRealizadoBloqueActual(numBloque);
    }

    public boolean isSiguienteBloque(int numBloque) {
        return sesion.isSiguienteBloqueActual(numBloque);
    }

    public void crearProgresoCurso() {
        sesion.empezarCursoActual();
    }

    public Optional<ProgresoCurso> getProgresoCursoActual() {
        return sesion.getProgresoCursoActual();
    }

    public boolean existeProgresoCurso() {
        return sesion.existeProgresoCursoActual();
    }

    public boolean cursoEmpezado() {
        return sesion.isCursoActualEmpezado();
    }

    public void eliminarProgresoCurso() {
        sesion.eliminarProgresoCursoActual();
    }

    public boolean configurarEstrategia(ProgresoCurso progresoCurso, String estrategiaNombre) {
        if (progresoCurso != null && estrategiaNombre != null) {
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

    public Ejercicio siguienteEjercicio() {
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

    public Ejercicio getEjercicioActual() {
        return sesion.getEjercicioActual();
    }

    public boolean mostrarEjercicio() {
        Ejercicio ejercicio = this.siguienteEjercicio();
        if (ejercicio != null) {
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

    public void actualizarProgresoCurso() {
        sesion.actualizarProgresoCursoActual();
    }

    public void actualizarRacha(boolean acierto) {
        sesion.actualizarRachaActual(acierto);
    }

    public void actualizarTiempoUso() {
        sesion.actualizarTiempoUsoActual(sesion.getTiempoInicioSesion());
        sesion.setTiempoInicioSesion(System.currentTimeMillis());
    }

    public void actualizarExperiencia(int experiencia) {
        sesion.actualizarExperienciaActual(experiencia);
    }

}
