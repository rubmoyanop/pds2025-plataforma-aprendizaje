package pds.hispania360.modelo;

import java.util.ArrayList;

public class Usuario {
    private final int id;
    private boolean creador;
    private String nombre;
    private String email;
    private String password;
    private final EstadisticasUsuario stats; // Esto alomejor se puede hacer algo para que solo pueda haber una (repasar TDS)
    private ArrayList<ProgresoCurso> cursos;
    

    public Usuario(int id, boolean creador, String nombre, String email, String password, EstadisticasUsuario stats, ArrayList<ProgresoCurso> cursos) {
        this.id = id;
        this.creador = creador;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.stats = stats;
        this.cursos = cursos;
    }

    public Usuario(int id, boolean creador, String nombre, String email, String password) {
        this(id, creador, nombre, email, password, new EstadisticasUsuario(), new ArrayList<ProgresoCurso>());
    }

    public boolean isRealizado(int idCurso, int numBloque) {
        // Comprobar si el curso existe en la lista de cursos del usuario
        for (ProgresoCurso curso : this.cursos) {
            if (curso.getCurso().getId() == idCurso) {
                return curso.getProgreso() >= numBloque;  
            }
        }
        return false;
    }

    public boolean isSiguienteBloque(int idCurso, int numBloque) {
        // Comprobar si el curso existe en la lista de cursos del usuario
        for (ProgresoCurso curso : this.cursos) {
            if (curso.getCurso().getId() == idCurso) {
                return curso.getProgreso() == numBloque - 1;  
            }
        }
        return false;
    }

    public int getId() {
        return this.id;
    }


    public boolean isCreador() {
        return this.creador;
    }

    public void setCreador(boolean creador) {
        this.creador = creador;
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

    public void empezarCurso(Curso curso) {
        ProgresoCurso progresoCurso = new ProgresoCurso(null, curso, 0);
        this.cursos.add(progresoCurso);
    }

    public ProgresoCurso getProgresoCurso(int idCurso) {
        for (ProgresoCurso pc : this.cursos) {
            if (pc.getCurso().getId() == idCurso) {
                return pc;
            }
        }
        return null;
    }

    public void actualizarProgresoCurso(int idCurso) {
        ProgresoCurso progresoCurso = getProgresoCurso(idCurso);
        progresoCurso.setProgreso(progresoCurso.getProgreso() + 1);
        if(progresoCurso.getProgreso() == 1){
            this.stats.aumentarCursosEnProgreso();
        }
        progresoCurso.setProgresoEjercicio(0);
        if(progresoCurso.isCompletado()) {
            this.stats.aumentarCursosCompletados();
        }
    }

    public void eliminarProgresoCurso(int idCurso) {
        ProgresoCurso progresoCurso = getProgresoCurso(idCurso);
        if (progresoCurso != null) {
            this.cursos.remove(progresoCurso);
            this.stats.disminuirCursosEnProgreso();
        }
    }
    
    public void aumentarTiempoTotal(long tiempo) {
        this.stats.aumentarTiempoUso(tiempo);
    }

    public void actualizarRacha(boolean acierto) {
        this.stats.actualizarRacha(acierto);
    }
}
