package pds.hispania360.factoria;

import pds.hispania360.modelo.*;
import pds.hispania360.modelo.ejercicios.Ejercicio;
import java.util.List;

public enum FactoriaEstrategia {
    INSTANCIA;
    public static EstrategiaAprendizaje crearEstrategia(String tipo) {

        switch (tipo.toLowerCase()) {
            case "secuencial":
                return new EstrategiaSecuencial();
            case "repeticion espaciada":
                return new EstrategiaRepeticionEspaciada();
            case "random":
                return new EstrategiaRandom();
            default:
                throw new IllegalArgumentException("Estrategia desconocida: " + tipo);
        }
    }
}

