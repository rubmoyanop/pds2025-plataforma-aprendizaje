package pds.hispania360.modelo;

import java.util.ArrayList;

public class Usuario {
    private final int id;
    private boolean esCreador;
    private String nombre;
    private String email;
    private String password;
    private final EstadisticasUsuario stats; // Esto alomejor se puede hacer algo para que solo pueda haber una (repasar TDS)
    private ArrayList<ProgresoCurso> cursos;
    

    public Usuario(int id, boolean esCreador, String nombre, String email, String password, EstadisticasUsuario stats, ArrayList<ProgresoCurso> cursos) {
        this.id = id;
        this.esCreador = esCreador;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.stats = stats;
        this.cursos = cursos;
    }


    public int getId() {
        return this.id;
    }


    public boolean isEsCreador() {
        return this.esCreador;
    }

    public boolean getEsCreador() {
        return this.esCreador;
    }

    public void setEsCreador(boolean esCreador) {
        this.esCreador = esCreador;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public EstadisticasUsuario getStats() {
        return this.stats;
    }


    public ArrayList<ProgresoCurso> getCursos() {
        return this.cursos;
    }

    public void setCursos(ArrayList<ProgresoCurso> cursos) {
        this.cursos = cursos;
    }
    

}
