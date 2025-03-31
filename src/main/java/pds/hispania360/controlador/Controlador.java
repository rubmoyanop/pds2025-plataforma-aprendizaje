package pds.hispania360.controlador;

import pds.hispania360.repositorio.GestorUsuario;
import pds.hispania360.sesion.Sesion;

public class Controlador {
    
    private static Controlador instancia;

    private Controlador() {
          
    }
    
    /**
     * Obtiene la instancia única del gestor de ventanas.
     * @return La instancia del gestor
     */
    public static Controlador getInstancia() {
        if (instancia == null) {
            instancia = new Controlador();
        }
        return instancia;
    }


    public boolean registrarUsuario(String email, String nombre, String password, boolean esCreador){
        return GestorUsuario.getInstancia().crearUsuario(email,nombre, password,esCreador);
    }
    
    public boolean iniciarSesion(String email, String password) {
    	if(GestorUsuario.getInstancia().autenticarUsuario(email, password).isPresent()) {
    		Sesion.INSTANCE.setUsuarioActual(GestorUsuario.getInstancia().autenticarUsuario(email, password).get());
    		return true;
    	}
    	return false;
    }
    
    public void cerrarSesion() {
    	Sesion.INSTANCE.cerrarSesion();
    }
    
    
    

}
