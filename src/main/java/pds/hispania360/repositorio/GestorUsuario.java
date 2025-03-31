package pds.hispania360.repositorio;

import java.util.HashMap;
import java.util.Map;

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
        Usuario user = new Usuario(usuarios.size(), esCreador, nombre, email, password);
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
}
