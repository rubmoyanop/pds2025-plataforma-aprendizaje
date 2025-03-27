package pds.hispania360.modelo;

public class EjercicioRellenarHuecos extends Ejercicio{

    private String textoConHuecos;  // Texto con huecos tipo "La capital de Francia es ____"
    private String respuestaCorrecta;

    public EjercicioRellenarHuecos(int id, String enunciado, String textoConHuecos, String respuestaCorrecta) {
        super(id, enunciado);
        this.textoConHuecos = textoConHuecos;
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public String getTextoConHuecos() {
        return this.textoConHuecos;
    }

    public void setTextoConHuecos(String textoConHuecos) {
        this.textoConHuecos = textoConHuecos;
    }

    public String getRespuestaCorrecta() {
        return this.respuestaCorrecta;
    }

    public void setRespuestaCorrecta(String respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public boolean comprobarRespuesta(String respuestaUsuario) {
        return respuestaCorrecta.equalsIgnoreCase(respuestaUsuario);
    }
    
}
