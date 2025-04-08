package pds.hispania360.sesion;
import pds.hispania360.modelo.Curso;
import pds.hispania360.modelo.Usuario;
import pds.hispania360.modelo.ejercicios.Ejercicio;

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
		//Guardamos el momento en el que se inicia sesión
		this.tiempoInicioSesion = System.currentTimeMillis();
	}
	
	public void cerrarSesion() {
		if(this.usuarioActual != null) {
            this.usuarioActual.aumentarTiempoTotal(System.currentTimeMillis() - tiempoInicioSesion);
        }
		this.usuarioActual = null;
		this.cursoActual = null;
	}
	
	public boolean haySesion() {
		return this.usuarioActual != null;
	}
	
	public boolean haySesionConCurso() {
		return this.usuarioActual != null && this.cursoActual != null;
	}

	public boolean esCreador() {
		return this.usuarioActual.isCreador();
	}
	
	public boolean esEstudiante() {
		return !this.usuarioActual.isCreador();
	}

}
