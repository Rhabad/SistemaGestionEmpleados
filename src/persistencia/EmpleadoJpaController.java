/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modeloBD.Cargo;
import modeloBD.Departamento;
import modeloBD.ContactosEmergencia;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modeloBD.Empleado;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.exceptions.PreexistingEntityException;

/**
 *
 * @author NICOLAS
 */
public class EmpleadoJpaController implements Serializable {

    public EmpleadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public EmpleadoJpaController(){
        emf = Persistence.createEntityManagerFactory("GestionDeEmpleadosPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empleado empleado) throws PreexistingEntityException, Exception {
        if (empleado.getContacto() == null) {
            empleado.setContacto(new ArrayList<ContactosEmergencia>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cargo cargoFK = empleado.getCargoFK();
            if (cargoFK != null) {
                cargoFK = em.getReference(cargoFK.getClass(), cargoFK.getIdCargo());
                empleado.setCargoFK(cargoFK);
            }
            Departamento departamentoFK = empleado.getDepartamentoFK();
            if (departamentoFK != null) {
                departamentoFK = em.getReference(departamentoFK.getClass(), departamentoFK.getIdDepartamento());
                empleado.setDepartamentoFK(departamentoFK);
            }
            List<ContactosEmergencia> attachedContacto = new ArrayList<ContactosEmergencia>();
            for (ContactosEmergencia contactoContactosEmergenciaToAttach : empleado.getContacto()) {
                contactoContactosEmergenciaToAttach = em.getReference(contactoContactosEmergenciaToAttach.getClass(), contactoContactosEmergenciaToAttach.getRutContacto());
                attachedContacto.add(contactoContactosEmergenciaToAttach);
            }
            empleado.setContacto(attachedContacto);
            em.persist(empleado);
            if (cargoFK != null) {
                cargoFK.getEmpleadoCargo().add(empleado);
                cargoFK = em.merge(cargoFK);
            }
            if (departamentoFK != null) {
                departamentoFK.getEmpleadoDepartamento().add(empleado);
                departamentoFK = em.merge(departamentoFK);
            }
            for (ContactosEmergencia contactoContactosEmergencia : empleado.getContacto()) {
                Empleado oldRutFKOfContactoContactosEmergencia = contactoContactosEmergencia.getRutFK();
                contactoContactosEmergencia.setRutFK(empleado);
                contactoContactosEmergencia = em.merge(contactoContactosEmergencia);
                if (oldRutFKOfContactoContactosEmergencia != null) {
                    oldRutFKOfContactoContactosEmergencia.getContacto().remove(contactoContactosEmergencia);
                    oldRutFKOfContactoContactosEmergencia = em.merge(oldRutFKOfContactoContactosEmergencia);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEmpleado(empleado.getRut()) != null) {
                throw new PreexistingEntityException("Empleado " + empleado + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Empleado empleado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado persistentEmpleado = em.find(Empleado.class, empleado.getRut());
            Cargo cargoFKOld = persistentEmpleado.getCargoFK();
            Cargo cargoFKNew = empleado.getCargoFK();
            Departamento departamentoFKOld = persistentEmpleado.getDepartamentoFK();
            Departamento departamentoFKNew = empleado.getDepartamentoFK();
            List<ContactosEmergencia> contactoOld = persistentEmpleado.getContacto();
            List<ContactosEmergencia> contactoNew = empleado.getContacto();
            if (cargoFKNew != null) {
                cargoFKNew = em.getReference(cargoFKNew.getClass(), cargoFKNew.getIdCargo());
                empleado.setCargoFK(cargoFKNew);
            }
            if (departamentoFKNew != null) {
                departamentoFKNew = em.getReference(departamentoFKNew.getClass(), departamentoFKNew.getIdDepartamento());
                empleado.setDepartamentoFK(departamentoFKNew);
            }
            List<ContactosEmergencia> attachedContactoNew = new ArrayList<ContactosEmergencia>();
            for (ContactosEmergencia contactoNewContactosEmergenciaToAttach : contactoNew) {
                contactoNewContactosEmergenciaToAttach = em.getReference(contactoNewContactosEmergenciaToAttach.getClass(), contactoNewContactosEmergenciaToAttach.getRutContacto());
                attachedContactoNew.add(contactoNewContactosEmergenciaToAttach);
            }
            contactoNew = attachedContactoNew;
            empleado.setContacto(contactoNew);
            empleado = em.merge(empleado);
            if (cargoFKOld != null && !cargoFKOld.equals(cargoFKNew)) {
                cargoFKOld.getEmpleadoCargo().remove(empleado);
                cargoFKOld = em.merge(cargoFKOld);
            }
            if (cargoFKNew != null && !cargoFKNew.equals(cargoFKOld)) {
                cargoFKNew.getEmpleadoCargo().add(empleado);
                cargoFKNew = em.merge(cargoFKNew);
            }
            if (departamentoFKOld != null && !departamentoFKOld.equals(departamentoFKNew)) {
                departamentoFKOld.getEmpleadoDepartamento().remove(empleado);
                departamentoFKOld = em.merge(departamentoFKOld);
            }
            if (departamentoFKNew != null && !departamentoFKNew.equals(departamentoFKOld)) {
                departamentoFKNew.getEmpleadoDepartamento().add(empleado);
                departamentoFKNew = em.merge(departamentoFKNew);
            }
            for (ContactosEmergencia contactoOldContactosEmergencia : contactoOld) {
                if (!contactoNew.contains(contactoOldContactosEmergencia)) {
                    contactoOldContactosEmergencia.setRutFK(null);
                    contactoOldContactosEmergencia = em.merge(contactoOldContactosEmergencia);
                }
            }
            for (ContactosEmergencia contactoNewContactosEmergencia : contactoNew) {
                if (!contactoOld.contains(contactoNewContactosEmergencia)) {
                    Empleado oldRutFKOfContactoNewContactosEmergencia = contactoNewContactosEmergencia.getRutFK();
                    contactoNewContactosEmergencia.setRutFK(empleado);
                    contactoNewContactosEmergencia = em.merge(contactoNewContactosEmergencia);
                    if (oldRutFKOfContactoNewContactosEmergencia != null && !oldRutFKOfContactoNewContactosEmergencia.equals(empleado)) {
                        oldRutFKOfContactoNewContactosEmergencia.getContacto().remove(contactoNewContactosEmergencia);
                        oldRutFKOfContactoNewContactosEmergencia = em.merge(oldRutFKOfContactoNewContactosEmergencia);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = empleado.getRut();
                if (findEmpleado(id) == null) {
                    throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.");
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
            Empleado empleado;
            try {
                empleado = em.getReference(Empleado.class, id);
                empleado.getRut();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.", enfe);
            }
            Cargo cargoFK = empleado.getCargoFK();
            if (cargoFK != null) {
                cargoFK.getEmpleadoCargo().remove(empleado);
                cargoFK = em.merge(cargoFK);
            }
            Departamento departamentoFK = empleado.getDepartamentoFK();
            if (departamentoFK != null) {
                departamentoFK.getEmpleadoDepartamento().remove(empleado);
                departamentoFK = em.merge(departamentoFK);
            }
            List<ContactosEmergencia> contacto = empleado.getContacto();
            for (ContactosEmergencia contactoContactosEmergencia : contacto) {
                contactoContactosEmergencia.setRutFK(null);
                contactoContactosEmergencia = em.merge(contactoContactosEmergencia);
            }
            em.remove(empleado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Empleado> findEmpleadoEntities() {
        return findEmpleadoEntities(true, -1, -1);
    }

    public List<Empleado> findEmpleadoEntities(int maxResults, int firstResult) {
        return findEmpleadoEntities(false, maxResults, firstResult);
    }

    private List<Empleado> findEmpleadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empleado.class));
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

    public Empleado findEmpleado(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empleado.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpleadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empleado> rt = cq.from(Empleado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
