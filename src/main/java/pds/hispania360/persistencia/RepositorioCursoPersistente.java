package pds.hispania360.persistencia;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import pds.hispania360.modelo.Curso;
import pds.hispania360.modelo.Bloque;
import pds.hispania360.modelo.Usuario;
import pds.hispania360.repositorio.RepositorioCurso;

public enum RepositorioCursoPersistente implements RepositorioCurso {
    INSTANCIA;

    private EntityManagerFactory emf;

    RepositorioCursoPersistente(){
        this.emf = Persistence.createEntityManagerFactory("usuarios");
    }

    @Override
    public void agregarCurso(Curso c) {
        EntityManager em = emf.createEntityManager();
        System.out.println("\033[32mEntity Manager created\033[0m"); // agregado
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            em.persist(c);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()){
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen()){
                System.out.println("\033[31mEntity Manager closed\033[0m"); // agregado
                em.close();
            }
        }
    }

    @Override
    public void crearCurso(String titulo, String descripcion, Usuario creador, ArrayList<Bloque> bloques, LocalDate fechaCreacion) {
        EntityManager em = emf.createEntityManager();
        System.out.println("\033[32mEntity Manager created\033[0m"); // agregado
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Curso curso = new Curso(titulo, descripcion, creador, bloques, fechaCreacion);
            em.persist(curso);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()){
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen()){
                System.out.println("\033[31mEntity Manager closed\033[0m"); // agregado
                em.close();
            }
        }
    }

    @Override
    public List<Curso> obtenerCursos(){
        EntityManager em = emf.createEntityManager();
        System.out.println("\033[32mEntity Manager created\033[0m"); // agregado
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        List<Curso> cursos = null;
        try {
            cursos = em.createQuery("SELECT c FROM Curso c", Curso.class).getResultList();
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()){
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen()){
                System.out.println("\033[31mEntity Manager closed\033[0m"); // agregado
                em.close();
            }
        }
        return cursos;
    }

    @Override
    public Curso obtenerCurso(int id) {
        EntityManager em = emf.createEntityManager();
        System.out.println("\033[32mEntity Manager created\033[0m"); // agregado
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Curso curso = null;
        try {
            curso = em.find(Curso.class, id);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()){
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen()){
                System.out.println("\033[31mEntity Manager closed\033[0m"); // agregado
                em.close();
            }
        }
        return curso;
    }

    public boolean eliminarCurso(int id) {
        EntityManager em = emf.createEntityManager();
        System.out.println("\033[32mEntity Manager created\033[0m"); // agregado
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        boolean resultado = false;
        try {
            Curso curso = em.find(Curso.class, id);
            if (curso != null) {
                em.remove(curso);
                tx.commit();
                resultado = true;
            } else {
                tx.rollback();
            }
        } catch (Exception e) {
            if (tx.isActive()){
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen()){
                System.out.println("\033[31mEntity Manager closed\033[0m"); // agregado
                em.close();
            }
        }
        return resultado;
    }
}