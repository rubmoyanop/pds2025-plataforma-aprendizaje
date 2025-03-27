package pds.hispania360.modelo;

public class EjercicioRellenarHuecos extends Ejercicio{

    private String textoConHuecos;  // Texto con huecos tipo "La capital de Francia es ____"
    private String respuestaCorrecta;

    public RellenarHuecos(String enunciado, String textoConHuecos, String respuestaCorrecta) {
        super(enunciado);
        this.textoConHuecos = textoConHuecos;
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public boolean comprobarRespuesta(String respuestaUsuario) {
        return respuestaCorrecta.equalsIgnoreCase(respuestaUsuario);
    }
    
}
