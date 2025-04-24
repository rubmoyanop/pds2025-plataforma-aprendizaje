package pds.hispania360.repositorio;

import java.util.List;
import pds.hispania360.modelo.ProgresoCurso;
import pds.hispania360.modelo.Usuario;

public interface RepositorioProgresoCurso {
    void guardarProgreso(ProgresoCurso progreso);
    void actualizarProgreso(ProgresoCurso progreso);
    List<ProgresoCurso> obtenerProgresosPorUsuario(Usuario usuario);
    ProgresoCurso obtenerProgresoPorId(int id);
}
