/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modeloBD.ContactosEmergencia;
import modeloBD.Empleado;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.exceptions.PreexistingEntityException;

/**
 *
 * @author NICOLAS
 */
public class ContactosEmergenciaJpaController implements Serializable {

    public ContactosEmergenciaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public ContactosEmergenciaJpaController(){
        emf = Persistence.createEntityManagerFactory("GestionDeEmpleadosPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ContactosEmergencia contactosEmergencia) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado rutFK = contactosEmergencia.getRutFK();
            if (rutFK != null) {
                rutFK = em.getReference(rutFK.getClass(), rutFK.getRut());
                contactosEmergencia.setRutFK(rutFK);
            }
            em.persist(contactosEmergencia);
            if (rutFK != null) {
                rutFK.getContacto().add(contactosEmergencia);
                rutFK = em.merge(rutFK);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findContactosEmergencia(contactosEmergencia.getRutContacto()) != null) {
                throw new PreexistingEntityException("ContactosEmergencia " + contactosEmergencia + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ContactosEmergencia contactosEmergencia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ContactosEmergencia persistentContactosEmergencia = em.find(ContactosEmergencia.class, contactosEmergencia.getRutContacto());
            Empleado rutFKOld = persistentContactosEmergencia.getRutFK();
            Empleado rutFKNew = contactosEmergencia.getRutFK();
            if (rutFKNew != null) {
                rutFKNew = em.getReference(rutFKNew.getClass(), rutFKNew.getRut());
                contactosEmergencia.setRutFK(rutFKNew);
            }
            contactosEmergencia = em.merge(contactosEmergencia);
            if (rutFKOld != null && !rutFKOld.equals(rutFKNew)) {
                rutFKOld.getContacto().remove(contactosEmergencia);
                rutFKOld = em.merge(rutFKOld);
            }
            if (rutFKNew != null && !rutFKNew.equals(rutFKOld)) {
                rutFKNew.getContacto().add(contactosEmergencia);
                rutFKNew = em.merge(rutFKNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = contactosEmergencia.getRutContacto();
                if (findContactosEmergencia(id) == null) {
                    throw new NonexistentEntityException("The contactosEmergencia with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ContactosEmergencia contactosEmergencia;
            try {
                contactosEmergencia = em.getReference(ContactosEmergencia.class, id);
                contactosEmergencia.getRutContacto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The contactosEmergencia with id " + id + " no longer exists.", enfe);
            }
            Empleado rutFK = contactosEmergencia.getRutFK();
            if (rutFK != null) {
                rutFK.getContacto().remove(contactosEmergencia);
                rutFK = em.merge(rutFK);
            }
            em.remove(contactosEmergencia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ContactosEmergencia> findContactosEmergenciaEntities() {
        return findContactosEmergenciaEntities(true, -1, -1);
    }

    public List<ContactosEmergencia> findContactosEmergenciaEntities(int maxResults, int firstResult) {
        return findContactosEmergenciaEntities(false, maxResults, firstResult);
    }

    private List<ContactosEmergencia> findContactosEmergenciaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ContactosEmergencia.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ContactosEmergencia findContactosEmergencia(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ContactosEmergencia.class, id);
        } finally {
            em.close();
        }
    }

    public int getContactosEmergenciaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ContactosEmergencia> rt = cq.from(ContactosEmergencia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
