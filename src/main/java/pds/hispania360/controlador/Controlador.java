package pds.hispania360.controlador;

import pds.hispania360.repositorio.GestorUsuario;

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
        return GestorUsuario.getInstancia().crearUsuario(email, nombre, password, esCreador);
    }
}
