package pds.hispania360.factoria;

import pds.hispania360.modelo.*;

public enum FactoriaEstrategia {
    INSTANCIA;
    public  EstrategiaAprendizaje crearEstrategia(String tipo) {

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

    public EstrategiaAprendizaje crearEstrategiaPorNombre(String nombre) {
        if (nombre == null) return null;
        switch (nombre) {
            case "EstrategiaSecuencial":
                return new EstrategiaSecuencial();
            case "EstrategiaRepeticionEspaciada":
                return new EstrategiaRepeticionEspaciada();
            case "EstrategiaRandom":
                return new EstrategiaRandom();
            default:
                return null;
        }
    }
}

