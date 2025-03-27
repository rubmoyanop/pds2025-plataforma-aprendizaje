package pds.hispania360.repositorio;

public interface RepositorioUsuario {

    
     boolean crearUsuario(String email, String nombre, String password, boolean esCreador){

    }

     Usuario obtenerUsuario(int id){

    }

     boolean eliminarUsuario(int id){

     }




}
