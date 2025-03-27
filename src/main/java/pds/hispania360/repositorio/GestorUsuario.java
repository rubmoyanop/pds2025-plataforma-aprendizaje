package pds.hispania360.repositorio;

import java.util.HashMap;
import java.util.Map;

import pds.hispania360.modelo.Usuario;

public class GestorUsuario implements RepositorioUsuario {
    
    private static GestorUsuario instancia;
    private Map<Integer, Usuario> usuarios;

     /**
     * Constructor privado siguiendo el patrón Singleton.
     */
    private GestorUsuario() {
        usuarios = new HashMap<Integer,Usuario>();
        
    }
    
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

    public boolean crearUsuario(String email, String nombre, String password, boolean esCreador){
        Usuario user = new Usuario(usuarios.size(), esCreador, nombre, email, password);
        usuarios.put(user.getId(), user);
        return true;
    }

    public Usuario obtenerUsuario(int id){
        return usuarios.get(id);
    }

    public boolean eliminarUsuario(int id){
        return usuarios.remove(id) != null;
    }


}
