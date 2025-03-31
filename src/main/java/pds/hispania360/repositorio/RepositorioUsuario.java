package pds.hispania360.repositorio;

import java.util.Optional;

import pds.hispania360.modelo.Usuario;

public interface RepositorioUsuario {

    
     boolean crearUsuario(String email, String nombre, String password, boolean esCreador);

     Usuario obtenerUsuario(int id);

     boolean eliminarUsuario(int id);
     
     Optional<Usuario> autenticarUsuario(String email, String password);
}
