package pds.hispania360.modelo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import pds.hispania360.persistencia.RepositorioUsuarioPersistente;
import pds.hispania360.persistencia.RepositorioProgresoCursoPersistente;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    
    @Column(name="creador", nullable = false)
    private boolean creador;
    
    @Column(name="nombre", nullable = false)
    private String nombre;
    
    @Column(name="email", nullable = false, unique = true)
    private String email;
    
    @Column(name="password", nullable = false)
    private String password;
    
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private EstadisticasUsuario stats;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ProgresoCurso> cursos = new ArrayList<>();
    
    public Usuario() {
        this.stats = new EstadisticasUsuario();
        this.cursos = new ArrayList<>();
    }
    
    public Usuario(boolean creador, String nombre, String email, String password) {
        this();
        this.creador = creador;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }

    public Usuario(int id, boolean creador, String nombre, String email, String password, EstadisticasUsuario stats, List<ProgresoCurso> cursos) {
        this.id = id;
        this.creador = creador;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.stats = stats != null ? stats : new EstadisticasUsuario();
        this.cursos = cursos != null ? cursos : new ArrayList<>();
    }

    public Usuario(int id, boolean creador, String nombre, String email, String password) {
        this(id, creador, nombre, email, password, new EstadisticasUsuario(), new ArrayList<ProgresoCurso>());
    }

    public boolean isBloqueRealizado(int idCurso, int numBloque) {
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

    public void setId(Integer id){
        this.id = id;
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

    public void setStats(EstadisticasUsuario stats) {
        this.stats = stats;
    }

    public List<ProgresoCurso> getCursos() {
        return this.cursos;
    }

    public void setCursos(List<ProgresoCurso> cursos) {
        this.cursos = cursos;
    }

    public void empezarCurso(Curso curso) {
        ProgresoCurso progresoCurso = new ProgresoCurso(null, curso, 0);
        progresoCurso.setUsuario(this); // Importante para la relación bidireccional
        this.cursos.add(progresoCurso);
        this.stats.aumentarCursosEnProgreso();
        RepositorioProgresoCursoPersistente.INSTANCIA.guardarProgreso(progresoCurso);
        RepositorioUsuarioPersistente.INSTANCIA.actualizarUsuario(this);
    }

    public void eliminarProgresoCurso(int idCurso) {
        ProgresoCurso progresoCurso = getProgresoCurso(idCurso);
        if (!progresoCurso.isCompletado()) {
            this.stats.disminuirCursosEnProgreso();
        }
        this.cursos.remove(progresoCurso);
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
        progresoCurso.setProgresoEjercicio(0);
        if(progresoCurso.isCompletado()) {
            this.stats.aumentarCursosCompletados();
        }
        RepositorioProgresoCursoPersistente.INSTANCIA.actualizarProgreso(progresoCurso);
        RepositorioUsuarioPersistente.INSTANCIA.actualizarUsuario(this);
    }
    
    public void aumentarTiempoTotal(long tiempo) {
        this.stats.aumentarTiempoUso(tiempo);
        RepositorioUsuarioPersistente.INSTANCIA.actualizarUsuario(this);
    }

    public void actualizarRacha(boolean acierto) {
        this.stats.actualizarRacha(acierto);
    }

    public void aumentarExperiencia(int experiencia) {
        this.stats.aumentarExperiencia(experiencia);
        RepositorioUsuarioPersistente.INSTANCIA.actualizarUsuario(this);
    }
}
