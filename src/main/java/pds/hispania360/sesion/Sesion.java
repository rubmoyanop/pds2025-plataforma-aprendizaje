package pds.hispania360.sesion;
import pds.hispania360.modelo.Curso;
import pds.hispania360.modelo.ProgresoCurso;
import pds.hispania360.modelo.Usuario;
import pds.hispania360.modelo.ejercicios.Ejercicio;
import pds.hispania360.persistencia.RepositorioUsuarioPersistente;
import java.util.Optional;

public enum Sesion {
	INSTANCIA;
	private Usuario usuarioActual;
	private Curso cursoActual;
	private Ejercicio ejercicioActual;
	private long tiempoInicioSesion;

	public Usuario getUsuarioActual() {
		return this.usuarioActual;
	}

	public void setEjercicioActual(Ejercicio ejercicio) {
		this.ejercicioActual = ejercicio;
	}

	public Ejercicio getEjercicioActual() {
		return this.ejercicioActual;
	}

	public void setCursoActual(Curso curso) {
		System.out.println("Curso actual: " + curso.getTitulo());
		this.cursoActual = curso;
	}

	public Curso getCursoActual() {
		return this.cursoActual;
	}

	public void setUsuarioActual(Usuario user) {
		this.usuarioActual = user;
		this.tiempoInicioSesion = System.currentTimeMillis();
	}

	public void setTiempoInicioSesion(long tiempo) {
		this.tiempoInicioSesion = tiempo;
	}

	public long getTiempoInicioSesion() {
		return this.tiempoInicioSesion;
	}

	public void cerrarSesion() {
		if(this.usuarioActual != null) {
			actualizarTiempoUsoActual(this.tiempoInicioSesion);
			RepositorioUsuarioPersistente.INSTANCIA.actualizarUsuario(this.usuarioActual);
		}
		this.usuarioActual = null;
		this.cursoActual = null;
		this.ejercicioActual = null;
		this.tiempoInicioSesion = 0;
	}

	public boolean haySesion() {
		return this.usuarioActual != null;
	}

	public boolean haySesionConCurso() {
		return this.usuarioActual != null && this.cursoActual != null;
	}

	public boolean esCreador() {
		return haySesion() && this.usuarioActual.isCreador();
	}

	public boolean esEstudiante() {
		return haySesion() && !this.usuarioActual.isCreador();
	}

	public boolean isUsuarioCreador() {
		return haySesion() && this.usuarioActual.isCreador();
	}

	public boolean haRealizadoBloqueActual(int numBloque) {
		if (!haySesionConCurso()) {
			return false;
		}
		return this.usuarioActual.haRealizadoBloque(this.cursoActual.getId(), numBloque);
	}

	public boolean isSiguienteBloqueActual(int numBloque) {
		if (!haySesionConCurso()) {
			return false;
		}
		return this.usuarioActual.isSiguienteBloque(this.cursoActual.getId(), numBloque);
	}

	public void empezarCursoActual() {
		if (haySesionConCurso()) {
			this.usuarioActual.empezarCurso(this.cursoActual);
		}
	}

	public Optional<ProgresoCurso> getProgresoCursoActual() {
		if (!haySesionConCurso()) {
			return Optional.empty();
		}
		return Optional.ofNullable(this.usuarioActual.getProgresoCurso(this.cursoActual.getId()));
	}

	public boolean existeProgresoCursoActual() {
		return getProgresoCursoActual().isPresent();
	}

	public boolean isCursoActualEmpezado() {
		return getProgresoCursoActual().map(progreso -> progreso.getProgreso() != 0).orElse(false);
	}

	public void eliminarProgresoCursoActual() {
		if (haySesionConCurso()) {
			this.usuarioActual.eliminarProgresoCurso(this.cursoActual.getId());
		}
	}

	public Ejercicio obtenerSiguienteEjercicio() {
		Optional<ProgresoCurso> progresoOpt = getProgresoCursoActual();
		if (progresoOpt.isPresent()) {
			Ejercicio siguiente = progresoOpt.get().SiguienteEjercicio();
			this.ejercicioActual = siguiente;
			return siguiente;
		}
		this.ejercicioActual = null;
		return null;
	}

	public void actualizarProgresoCursoActual() {
		if (haySesionConCurso()) {
			this.usuarioActual.actualizarProgresoCurso(this.cursoActual.getId());
		}
	}

	public void actualizarRachaActual(boolean acierto) {
		if (haySesion()) {
			this.usuarioActual.actualizarRacha(acierto);
		}
	}

	public void actualizarTiempoUsoActual(long tiempoInicioSesionMs) {
		if (haySesion() && tiempoInicioSesionMs > 0) {
			long tiempoTranscurrido = System.currentTimeMillis() - tiempoInicioSesionMs;
			this.usuarioActual.aumentarTiempoTotal(tiempoTranscurrido);
		}
	}

	public void actualizarExperienciaActual(int experiencia) {
		if (haySesion()) {
			this.usuarioActual.aumentarExperiencia(experiencia);
		}
	}
}
