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

    
    public boolean emailRegistrado(String email) {
    	return usuarios.values().stream().anyMatch(u -> u.getEmail().equalsIgnoreCase(email));
    }
    
    @Override
    public boolean crearUsuario(String email, String nombre, String password, boolean esCreador){
        Usuario user = new Usuario(usuarios.size(), esCreador, nombre, email, password);
        if(emailRegistrado(email)) return false;
         usuarios.put(user.getId(), user);
         return true;
    }

    @Override
    public Usuario obtenerUsuario(int id) {
        return usuarios.get(id);
    }

    @Override
    public boolean eliminarUsuario(int id) {
        return usuarios.remove(id) != null;
    }
    
    @Override
    public Optional<Usuario> autenticarUsuario(String email, String password) {
    	System.out.println(email);
    	System.out.println(password);
    	return usuarios.values().stream().filter(u -> u.getEmail().equalsIgnoreCase(email) 
    			&& u.getPassword().equals(password)).findAny();
    }


}
