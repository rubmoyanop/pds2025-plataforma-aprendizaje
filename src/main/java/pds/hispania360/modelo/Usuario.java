package pds.hispania360.modelo;

public class Usuario {
    private int id;
    private boolean esCreador;
    private int IDCreador;
    private String nombre;
    private String email;
    private String contra;
    private EstadisticasUsuario stats; // Esto alomejor se puede hacer algo para que solo pueda haber una (repasar TDS)
    private List<ProgresoCurso> cursos;

    public int getID(){
        return this.id;
    }

    public String getNombre(){
        return this.nombre;
    }

    public String getEmail(){
        return this.email;
    }

    public String getContra(){
        return this.contra;
    }

    public EstadisticasUsuario getStats(){
        return this.stats;
    }

    public void InscribirseCurso (ProgresoCurso curso){
        
    }

    public void CrearCurso(){

    }

    


}
