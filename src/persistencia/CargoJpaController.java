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
import modeloBD.Empleado;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modeloBD.Cargo;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author NICOLAS
 */
public class CargoJpaController implements Serializable {

    public CargoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public CargoJpaController(){
        emf = Persistence.createEntityManagerFactory("GestionDeEmpleadosPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cargo cargo) {
        if (cargo.getEmpleadoCargo() == null) {
            cargo.setEmpleadoCargo(new ArrayList<Empleado>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Empleado> attachedEmpleadoCargo = new ArrayList<Empleado>();
            for (Empleado empleadoCargoEmpleadoToAttach : cargo.getEmpleadoCargo()) {
                empleadoCargoEmpleadoToAttach = em.getReference(empleadoCargoEmpleadoToAttach.getClass(), empleadoCargoEmpleadoToAttach.getRut());
                attachedEmpleadoCargo.add(empleadoCargoEmpleadoToAttach);
            }
            cargo.setEmpleadoCargo(attachedEmpleadoCargo);
            em.persist(cargo);
            for (Empleado empleadoCargoEmpleado : cargo.getEmpleadoCargo()) {
                Cargo oldCargoFKOfEmpleadoCargoEmpleado = empleadoCargoEmpleado.getCargoFK();
                empleadoCargoEmpleado.setCargoFK(cargo);
                empleadoCargoEmpleado = em.merge(empleadoCargoEmpleado);
                if (oldCargoFKOfEmpleadoCargoEmpleado != null) {
                    oldCargoFKOfEmpleadoCargoEmpleado.getEmpleadoCargo().remove(empleadoCargoEmpleado);
                    oldCargoFKOfEmpleadoCargoEmpleado = em.merge(oldCargoFKOfEmpleadoCargoEmpleado);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cargo cargo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cargo persistentCargo = em.find(Cargo.class, cargo.getIdCargo());
            List<Empleado> empleadoCargoOld = persistentCargo.getEmpleadoCargo();
            List<Empleado> empleadoCargoNew = cargo.getEmpleadoCargo();
            List<Empleado> attachedEmpleadoCargoNew = new ArrayList<Empleado>();
            for (Empleado empleadoCargoNewEmpleadoToAttach : empleadoCargoNew) {
                empleadoCargoNewEmpleadoToAttach = em.getReference(empleadoCargoNewEmpleadoToAttach.getClass(), empleadoCargoNewEmpleadoToAttach.getRut());
                attachedEmpleadoCargoNew.add(empleadoCargoNewEmpleadoToAttach);
            }
            empleadoCargoNew = attachedEmpleadoCargoNew;
            cargo.setEmpleadoCargo(empleadoCargoNew);
            cargo = em.merge(cargo);
            for (Empleado empleadoCargoOldEmpleado : empleadoCargoOld) {
                if (!empleadoCargoNew.contains(empleadoCargoOldEmpleado)) {
                    empleadoCargoOldEmpleado.setCargoFK(null);
                    empleadoCargoOldEmpleado = em.merge(empleadoCargoOldEmpleado);
                }
            }
            for (Empleado empleadoCargoNewEmpleado : empleadoCargoNew) {
                if (!empleadoCargoOld.contains(empleadoCargoNewEmpleado)) {
                    Cargo oldCargoFKOfEmpleadoCargoNewEmpleado = empleadoCargoNewEmpleado.getCargoFK();
                    empleadoCargoNewEmpleado.setCargoFK(cargo);
                    empleadoCargoNewEmpleado = em.merge(empleadoCargoNewEmpleado);
                    if (oldCargoFKOfEmpleadoCargoNewEmpleado != null && !oldCargoFKOfEmpleadoCargoNewEmpleado.equals(cargo)) {
                        oldCargoFKOfEmpleadoCargoNewEmpleado.getEmpleadoCargo().remove(empleadoCargoNewEmpleado);
                        oldCargoFKOfEmpleadoCargoNewEmpleado = em.merge(oldCargoFKOfEmpleadoCargoNewEmpleado);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = cargo.getIdCargo();
                if (findCargo(id) == null) {
                    throw new NonexistentEntityException("The cargo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cargo cargo;
            try {
                cargo = em.getReference(Cargo.class, id);
                cargo.getIdCargo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cargo with id " + id + " no longer exists.", enfe);
            }
            List<Empleado> empleadoCargo = cargo.getEmpleadoCargo();
            for (Empleado empleadoCargoEmpleado : empleadoCargo) {
                empleadoCargoEmpleado.setCargoFK(null);
                empleadoCargoEmpleado = em.merge(empleadoCargoEmpleado);
            }
            em.remove(cargo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cargo> findCargoEntities() {
        return findCargoEntities(true, -1, -1);
    }

    public List<Cargo> findCargoEntities(int maxResults, int firstResult) {
        return findCargoEntities(false, maxResults, firstResult);
    }

    private List<Cargo> findCargoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cargo.class));
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

    public Cargo findCargo(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cargo.class, id);
        } finally {
            em.close();
        }
    }

    public int getCargoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cargo> rt = cq.from(Cargo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
