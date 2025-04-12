package pds.hispania360.modelo;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import pds.hispania360.modelo.ejercicios.Ejercicio;

@Embeddable
public class ProgresoCurso {

    

    private EstrategiaAprendizaje estrategia;
    private Curso curso;
    private int progreso; // Entero que indica el último bloque realizado (O si no se ha realizado).
    private int progresoEjercicio; // Entero que indica el último ejercicio realizado (O si no se ha realizado).

  
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

    public void setProgresoEjercicio(int progresoEjercicio) {
        this.progresoEjercicio = progresoEjercicio;
    }

    // Método para solicitar la lista de Ejercicios a la estrategia
    public Ejercicio SiguienteEjercicio(){
       Ejercicio e =  this.estrategia.siguienteEjercicio(this.curso.getBloques().get(this.progreso).getEjercicios(), this.progresoEjercicio);
       this.progresoEjercicio++;
       return e;
    }

    public boolean isCompletado(){
        return this.progreso == this.curso.getBloques().size();
    }

}
