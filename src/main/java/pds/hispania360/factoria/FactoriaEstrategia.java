package pds.hispania360.factoria;

import pds.hispania360.modelo.*;
import java.util.Map;
import java.util.function.Supplier;

public enum FactoriaEstrategia {
    INSTANCIA;

    private static final Map<String, Supplier<EstrategiaAprendizaje>> estrategias = Map.of(
        "secuencial", EstrategiaSecuencial::new,
        "repeticion espaciada", EstrategiaRepeticionEspaciada::new,
        "random", EstrategiaRandom::new
    );

    public EstrategiaAprendizaje crearEstrategia(String tipo) {
        Supplier<EstrategiaAprendizaje> supplier = estrategias.get(tipo.toLowerCase());
        if (supplier != null) {
            return supplier.get();
        }
        throw new IllegalArgumentException("Estrategia desconocida: " + tipo);
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

