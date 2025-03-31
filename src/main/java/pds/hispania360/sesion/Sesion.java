package pds.hispania360.sesion;

import pds.hispania360.modelo.Usuario;

public enum Sesion {
	INSTANCE;
	private Usuario usuarioActual;
	
	public Usuario getUsuarioActual() {
		return this.usuarioActual;
	}
	
	public void setUsuarioActual(Usuario user) {
		this.usuarioActual = user;
	}
	
	public void cerrarSesion() {
		this.usuarioActual = null;
	}
	
	public boolean haySesion() {
		return this.usuarioActual != null;
	}
	
	public boolean esCreador() {
		return this.usuarioActual.isCreador();
	}
	
	public boolean esEstudiante() {
		return !this.usuarioActual.isCreador();
	}
	
	

}
