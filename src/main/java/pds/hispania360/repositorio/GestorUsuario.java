package pds.hispania360.repositorio;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import pds.hispania360.modelo.Usuario;

public enum GestorUsuario implements RepositorioUsuario {
    INSTANCIA;
    
    private Map<Integer, Usuario> usuarios;

    /**
     * Constructor del enum que inicializa el mapa de usuarios
     */
    GestorUsuario() {
        usuarios = new HashMap<Integer, Usuario>();
    }

    @Override
    public boolean crearUsuario(String email, String nombre, String password, boolean esCreador) {
    
    /**
     * Obtiene la instancia única del gestor de ventanas.
     * @return La instancia del gestor
     */
    public static GestorUsuario getInstancia() {
        if (instancia == null) {
            instancia = new GestorUsuario();
        }
        return instancia;
    }
    
    public boolean emailRegistrado(String email) {
    	return usuarios.values().stream().anyMatch(u -> u.getEmail().equalsIgnoreCase(email));
    }
    

    public boolean crearUsuario(String email, String nombre, String password, boolean esCreador){
        Usuario user = new Usuario(usuarios.size(), esCreador, nombre, email, password);
        if(emailRegistrado(email)) return false;
        return usuarios.put(user.getId(), user) != null;
    }

    @Override
    public Usuario obtenerUsuario(int id) {
        return usuarios.get(id);
    }

    @Override
    public boolean eliminarUsuario(int id) {
        return usuarios.remove(id) != null;
    }
    
    public Optional<Usuario> autenticarUsuario(String email, String password) {
    	return usuarios.values().stream().filter(u -> u.getEmail().equalsIgnoreCase(email) 
    			&& u.getPassword().equals(password)).findFirst();
    }


}
