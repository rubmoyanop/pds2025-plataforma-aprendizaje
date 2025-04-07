package pds.hispania360.sesion;

import pds.hispania360.modelo.Curso;
import pds.hispania360.modelo.ProgresoCurso;
import pds.hispania360.modelo.Usuario;

public enum Sesion {
	INSTANCIA;
	private Usuario usuarioActual;
	private Curso cursoActual;
	
	public Usuario getUsuarioActual() {
		return this.usuarioActual;
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
	}
	
	public void cerrarSesion() {
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
