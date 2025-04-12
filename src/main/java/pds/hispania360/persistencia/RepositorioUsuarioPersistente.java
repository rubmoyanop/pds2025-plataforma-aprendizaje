package pds.hispania360.persistencia;

import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import pds.hispania360.modelo.Usuario;
import pds.hispania360.repositorio.RepositorioUsuario;


public enum RepositorioUsuarioPersistente implements RepositorioUsuario {
    INSTANCIA;

    private EntityManagerFactory emf;
    private EntityManager em;

    RepositorioUsuarioPersistente(){
        this.emf = Persistence.createEntityManagerFactory("usuarios");
        
    }
    
    
        

            
    public boolean crearUsuario(String email, String nombre, String password, boolean esCreador) {
        this.em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        boolean resultado = false;
        try {
            Usuario usuario = new Usuario(esCreador, nombre, email, password);
            em.persist(usuario);
            tx.commit();
            resultado = true;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen())
                em.close();
        }
        return resultado;
    }

    @Override
    public Usuario obtenerUsuario(int id) {
        this.em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Usuario usuario = null; 
        try {
            usuario = em.find(Usuario.class, id);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen())
                em.close();
        }
        return usuario;
    }

    @Override
    public boolean eliminarUsuario(int id) {
        this.em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        boolean resultado = false;
        try {
            Usuario usuario = em.find(Usuario.class, id);
            if (usuario != null) {
                em.remove(usuario);
                tx.commit();
                resultado = true;
            } else {
                tx.rollback();
            }
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen())
                em.close();
        }
        return resultado;
    }

    @Override
    public Optional<Usuario> autenticarUsuario(String email, String password) {
        this.em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Optional<Usuario> usuario = Optional.empty();
        try {
            usuario = em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email AND u.password = :password", Usuario.class)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .getResultStream()
                    .findFirst();
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen())
                em.close();
        }
        return usuario;
    }
}
