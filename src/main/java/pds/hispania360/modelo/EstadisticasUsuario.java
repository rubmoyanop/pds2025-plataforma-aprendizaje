package pds.hispania360.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class EstadisticasUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "num_cursos_completados")
    private int numCursosCompletados;
    @Column(name = "num_cursos_en_progreso")
    private int numCursosEnProgreso;
    @Column(name = "tiempo_uso")
    private long tiempoUso;
    @Column(name = "racha_actual")
    private int rachaActual;
    @Column(name = "mejor_racha")
    private int mejorRacha;

    public EstadisticasUsuario(int numCursosCompletados, int numCursosEnProgreso, long tiempoUso, int mejorRacha) {
        this.numCursosCompletados = numCursosCompletados;
        this.numCursosEnProgreso = numCursosEnProgreso;
        this.tiempoUso = tiempoUso;
        this.mejorRacha = mejorRacha;
    }

    public EstadisticasUsuario() {
        this(0, 0, 0, 0);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public long getTiempoUso() {
        return this.tiempoUso;
    }

    public void setTiempoUso(long tiempoUso) {
        this.tiempoUso = tiempoUso;
    }

    public int getMejorRacha() {
        return this.mejorRacha;
    }

    public void setMejorRacha(int mejorRacha) {
        this.mejorRacha = mejorRacha;
    }

    public void aumentarCursosCompletados() {
        this.numCursosCompletados++;
        this.numCursosEnProgreso--;
    }

    public void aumentarCursosEnProgreso() {
        this.numCursosEnProgreso++;
    }

    public void aumentarTiempoUso(long tiempo) {
        this.tiempoUso += tiempo;
    }

    public void actualizarRacha(boolean acierto) {
        if(acierto) {
            this.rachaActual++;
        } else {
            this.rachaActual = 0;
        }
        if (this.rachaActual > this.mejorRacha) {
            this.mejorRacha = this.rachaActual;
        }
    }
}
