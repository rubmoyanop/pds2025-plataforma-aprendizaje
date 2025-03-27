package pds.hispania360.modelo;

public class EstadisticasUsuario {
    private final int id;
    private int numCursosCompletados;
    private int numCursosEnProgreso;
    private int tiempoUso;
    private int mejorRacha;


    public EstadisticasUsuario(int id, int numCursosCompletados, int numCursosEnProgreso, int tiempoUso, int mejorRacha) {
        this.id = id;
        this.numCursosCompletados = numCursosCompletados;
        this.numCursosEnProgreso = numCursosEnProgreso;
        this.tiempoUso = tiempoUso;
        this.mejorRacha = mejorRacha;
    }

    public int getId() {
        return this.id;
    }

    public int getNumCursosCompletados() {
        return this.numCursosCompletados;
    }

    public void setNumCursosCompletados(int numCursosCompletados) {
        this.numCursosCompletados = numCursosCompletados;
    }

    public int getNumCursosEnProgreso() {
        return this.numCursosEnProgreso;
    }

    public void setNumCursosEnProgreso(int numCursosEnProgreso) {
        this.numCursosEnProgreso = numCursosEnProgreso;
    }

    public int getTiempoUso() {
        return this.tiempoUso;
    }

    public void setTiempoUso(int tiempoUso) {
        this.tiempoUso = tiempoUso;
    }

    public int getMejorRacha() {
        return this.mejorRacha;
    }

    public void setMejorRacha(int mejorRacha) {
        this.mejorRacha = mejorRacha;
    }


}
