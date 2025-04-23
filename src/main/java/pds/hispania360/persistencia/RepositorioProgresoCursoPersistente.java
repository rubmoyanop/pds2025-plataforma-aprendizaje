package pds.hispania360.persistencia;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import pds.hispania360.modelo.ProgresoCurso;
import pds.hispania360.modelo.Usuario;
import pds.hispania360.repositorio.RepositorioProgresoCurso;

public enum RepositorioProgresoCursoPersistente implements RepositorioProgresoCurso {
    INSTANCIA;

    private EntityManagerFactory emf;

    RepositorioProgresoCursoPersistente() {
        this.emf = Persistence.createEntityManagerFactory("usuarios");
    }

    @Override
    public void guardarProgreso(ProgresoCurso progreso) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            em.persist(progreso);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void actualizarProgreso(ProgresoCurso progreso) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            em.merge(progreso);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public List<ProgresoCurso> obtenerProgresosPorUsuario(Usuario usuario) {
        EntityManager em = emf.createEntityManager();
        List<ProgresoCurso> lista = em.createQuery(
            "SELECT p FROM ProgresoCurso p WHERE p.usuario = :usuario", ProgresoCurso.class)
            .setParameter("usuario", usuario)
            .getResultList();
        em.close();
        return lista;
    }

    @Override
    public ProgresoCurso obtenerProgresoPorId(int id) {
        EntityManager em = emf.createEntityManager();
        ProgresoCurso progreso = em.find(ProgresoCurso.class, id);
        em.close();
        return progreso;
    }
}
