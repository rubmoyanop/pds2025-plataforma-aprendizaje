package pds.hispania360.factoria;

import pds.hispania360.modelo.ejercicios.Ejercicio;
import pds.hispania360.modelo.ejercicios.Flashcard;
import pds.hispania360.modelo.ejercicios.RespuestaMultiple;
import pds.hispania360.modelo.ejercicios.RellenarHueco;
import pds.hispania360.vista.pantallas.VentanaEjercicio;
import pds.hispania360.vista.pantallas.VentanaFlashcard;
import pds.hispania360.vista.pantallas.VentanaRellenarHueco;
import pds.hispania360.vista.pantallas.VentanaRespuestaMultiple;

public class FactoriaVentanaEjercicio {

    public static VentanaEjercicio crearVentana(Ejercicio ejercicio) {
        if(ejercicio instanceof Flashcard) {
            return new VentanaFlashcard((Flashcard) ejercicio);
        } else if(ejercicio instanceof RespuestaMultiple) {
            return new VentanaRespuestaMultiple((RespuestaMultiple) ejercicio);
        } else if(ejercicio instanceof RellenarHueco) {
            return new VentanaRellenarHueco((RellenarHueco) ejercicio);
        } else {
            throw new IllegalArgumentException("Ejercicio no soportado: " + ejercicio.getClass().getSimpleName());
        }
    }
}
