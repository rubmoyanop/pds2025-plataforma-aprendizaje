package pds.hispania360.controlador;

public class Controlador {
    
    private Controlador instancia;

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


    public boolean RegistrarUsuario(String email, String nombre, String password, boolean esCreador){
        return GestorUsuario.getInstancia().crearUsuario(email,nombre,password,esCreador);
    }
    

}
