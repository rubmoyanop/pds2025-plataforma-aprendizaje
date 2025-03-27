package pds.hispania360.modelo;

public class ProgresoCurso {
    private EstrategiaAprendizaje estrategia;
    private Curso curso;
    private int progreso;


    public ProgresoCurso(EstrategiaAprendizaje estrategia, Curso curso, int progreso) {
        this.estrategia = estrategia;
        this.curso = curso;
        this.progreso = progreso;
    }    

    public EstrategiaAprendizaje getEstrategia() {
        return this.estrategia;
    }

    public void setEstrategia(EstrategiaAprendizaje estrategia) {
        this.estrategia = estrategia;
    }

    public Curso getCurso() {
        return this.curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public int getProgreso() {
        return this.progreso;
    }

    public void setProgreso(int progreso) {
        this.progreso = progreso;
    }

}
