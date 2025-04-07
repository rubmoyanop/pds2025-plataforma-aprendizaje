package pds.hispania360.modelo;



public class ProgresoCurso {
    private EstrategiaAprendizaje estrategia;
    private Curso curso;
   // private List<Bloque> bloquesRealizados;
    private int progreso; // Entero que indica el último bloque realizado (O si no se ha realizado).
    

  
    // Constructor con estrategia, curso y progreso 
    public ProgresoCurso(EstrategiaAprendizaje estrategia, Curso curso, int progreso) {
        this.estrategia = estrategia;
        this.curso = curso;
        this.progreso = progreso;
    }
    
    // Getter y Setter para estrategia
    public EstrategiaAprendizaje getEstrategia() {
        return this.estrategia;
    }
    
    public void setEstrategia(EstrategiaAprendizaje estrategia) {
        this.estrategia = estrategia;
    }
    
    // Getter y Setter para curso
    public Curso getCurso() {
        return this.curso;
    }
    
    public void setCurso(Curso curso) {
        this.curso = curso;
    }

   
    // Getter y Setter para progreso
    public int getProgreso() {
        return this.progreso;
    }
    
    public void setProgreso(int progreso) {
        this.progreso = progreso;
    }
}
