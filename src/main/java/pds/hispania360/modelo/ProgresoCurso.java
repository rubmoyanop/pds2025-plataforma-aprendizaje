package pds.hispania360.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import pds.hispania360.factoria.FactoriaEstrategia;
import pds.hispania360.modelo.ejercicios.Ejercicio;

@Entity
public class ProgresoCurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "estrategia_tipo")
    private String estrategiaTipo;

    @Transient
    private EstrategiaAprendizaje estrategia;

    @ManyToOne
    private Curso curso;
    @ManyToOne
    private Usuario usuario;
    @Column(name = "progreso")
    private int progreso;  // Entero que indica el último bloque realizado (O si no se ha realizado).
    @Column(name = "progresoEjercicio")
    private int progresoEjercicio; // Entero que indica el último ejercicio realizado (O si no se ha realizado).

    public ProgresoCurso() {
        this.estrategiaTipo = null;
        this.estrategia = null;
        this.curso = null;
        this.usuario = null;
        this.progreso = 0;
        this.progresoEjercicio = 0;
    }

    // Constructor con estrategia, curso y progreso 
    public ProgresoCurso(EstrategiaAprendizaje estrategia, Curso curso, int progreso) {
        this.estrategia = estrategia;
        this.curso = curso;
        this.progreso = progreso;
        this.estrategiaTipo = (estrategia != null) ? estrategia.getClass().getSimpleName() : null;
    }

    // Getter y Setter para estrategia
    public EstrategiaAprendizaje getEstrategia() {
        if (estrategia == null && estrategiaTipo != null) {
            estrategia = FactoriaEstrategia.INSTANCIA.crearEstrategiaPorNombre(estrategiaTipo);
        }
        return estrategia;
    }

    public void setEstrategia(EstrategiaAprendizaje estrategia) {
        this.estrategia = estrategia;
        this.estrategiaTipo = (estrategia != null) ? estrategia.getClass().getSimpleName() : null;
    }

    public String getEstrategiaTipo() {
        return estrategiaTipo;
    }

    public void setEstrategiaTipo(String estrategiaTipo) {
        this.estrategiaTipo = estrategiaTipo;
        this.estrategia = null; // Se reinicializa al pedir getEstrategia()
    }

    // Getter y Setter para curso
    public Curso getCurso() {
        return this.curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    // Getter y Setter para usuario
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    // Getter y Setter para progreso
    public int getProgreso() {
        return this.progreso;
    }

    public void setProgreso(int progreso) {
        this.progreso = progreso;
    }

    public void setProgresoEjercicio(int progresoEjercicio) {
        this.progresoEjercicio = progresoEjercicio;
    }

    // Método para solicitar la lista de Ejercicios a la estrategia
    public Ejercicio SiguienteEjercicio() {
        Ejercicio e = this.estrategia.siguienteEjercicio(this.curso.getBloques().get(this.progreso).getEjercicios(), this.progresoEjercicio);
        this.progresoEjercicio++;
        return e;
    }

    public boolean isCompletado() {
        return this.progreso == this.curso.getBloques().size();
    }

}
