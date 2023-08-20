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
import modeloBD.Departamento;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author NICOLAS
 */
public class DepartamentoJpaController implements Serializable {

    public DepartamentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public DepartamentoJpaController(){
        emf = Persistence.createEntityManagerFactory("GestionDeEmpleadosPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Departamento departamento) {
        if (departamento.getEmpleadoDepartamento() == null) {
            departamento.setEmpleadoDepartamento(new ArrayList<Empleado>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Empleado> attachedEmpleadoDepartamento = new ArrayList<Empleado>();
            for (Empleado empleadoDepartamentoEmpleadoToAttach : departamento.getEmpleadoDepartamento()) {
                empleadoDepartamentoEmpleadoToAttach = em.getReference(empleadoDepartamentoEmpleadoToAttach.getClass(), empleadoDepartamentoEmpleadoToAttach.getRut());
                attachedEmpleadoDepartamento.add(empleadoDepartamentoEmpleadoToAttach);
            }
            departamento.setEmpleadoDepartamento(attachedEmpleadoDepartamento);
            em.persist(departamento);
            for (Empleado empleadoDepartamentoEmpleado : departamento.getEmpleadoDepartamento()) {
                Departamento oldDepartamentoFKOfEmpleadoDepartamentoEmpleado = empleadoDepartamentoEmpleado.getDepartamentoFK();
                empleadoDepartamentoEmpleado.setDepartamentoFK(departamento);
                empleadoDepartamentoEmpleado = em.merge(empleadoDepartamentoEmpleado);
                if (oldDepartamentoFKOfEmpleadoDepartamentoEmpleado != null) {
                    oldDepartamentoFKOfEmpleadoDepartamentoEmpleado.getEmpleadoDepartamento().remove(empleadoDepartamentoEmpleado);
                    oldDepartamentoFKOfEmpleadoDepartamentoEmpleado = em.merge(oldDepartamentoFKOfEmpleadoDepartamentoEmpleado);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Departamento departamento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Departamento persistentDepartamento = em.find(Departamento.class, departamento.getIdDepartamento());
            List<Empleado> empleadoDepartamentoOld = persistentDepartamento.getEmpleadoDepartamento();
            List<Empleado> empleadoDepartamentoNew = departamento.getEmpleadoDepartamento();
            List<Empleado> attachedEmpleadoDepartamentoNew = new ArrayList<Empleado>();
            for (Empleado empleadoDepartamentoNewEmpleadoToAttach : empleadoDepartamentoNew) {
                empleadoDepartamentoNewEmpleadoToAttach = em.getReference(empleadoDepartamentoNewEmpleadoToAttach.getClass(), empleadoDepartamentoNewEmpleadoToAttach.getRut());
                attachedEmpleadoDepartamentoNew.add(empleadoDepartamentoNewEmpleadoToAttach);
            }
            empleadoDepartamentoNew = attachedEmpleadoDepartamentoNew;
            departamento.setEmpleadoDepartamento(empleadoDepartamentoNew);
            departamento = em.merge(departamento);
            for (Empleado empleadoDepartamentoOldEmpleado : empleadoDepartamentoOld) {
                if (!empleadoDepartamentoNew.contains(empleadoDepartamentoOldEmpleado)) {
                    empleadoDepartamentoOldEmpleado.setDepartamentoFK(null);
                    empleadoDepartamentoOldEmpleado = em.merge(empleadoDepartamentoOldEmpleado);
                }
            }
            for (Empleado empleadoDepartamentoNewEmpleado : empleadoDepartamentoNew) {
                if (!empleadoDepartamentoOld.contains(empleadoDepartamentoNewEmpleado)) {
                    Departamento oldDepartamentoFKOfEmpleadoDepartamentoNewEmpleado = empleadoDepartamentoNewEmpleado.getDepartamentoFK();
                    empleadoDepartamentoNewEmpleado.setDepartamentoFK(departamento);
                    empleadoDepartamentoNewEmpleado = em.merge(empleadoDepartamentoNewEmpleado);
                    if (oldDepartamentoFKOfEmpleadoDepartamentoNewEmpleado != null && !oldDepartamentoFKOfEmpleadoDepartamentoNewEmpleado.equals(departamento)) {
                        oldDepartamentoFKOfEmpleadoDepartamentoNewEmpleado.getEmpleadoDepartamento().remove(empleadoDepartamentoNewEmpleado);
                        oldDepartamentoFKOfEmpleadoDepartamentoNewEmpleado = em.merge(oldDepartamentoFKOfEmpleadoDepartamentoNewEmpleado);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = departamento.getIdDepartamento();
                if (findDepartamento(id) == null) {
                    throw new NonexistentEntityException("The departamento with id " + id + " no longer exists.");
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
            Departamento departamento;
            try {
                departamento = em.getReference(Departamento.class, id);
                departamento.getIdDepartamento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The departamento with id " + id + " no longer exists.", enfe);
            }
            List<Empleado> empleadoDepartamento = departamento.getEmpleadoDepartamento();
            for (Empleado empleadoDepartamentoEmpleado : empleadoDepartamento) {
                empleadoDepartamentoEmpleado.setDepartamentoFK(null);
                empleadoDepartamentoEmpleado = em.merge(empleadoDepartamentoEmpleado);
            }
            em.remove(departamento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Departamento> findDepartamentoEntities() {
        return findDepartamentoEntities(true, -1, -1);
    }

    public List<Departamento> findDepartamentoEntities(int maxResults, int firstResult) {
        return findDepartamentoEntities(false, maxResults, firstResult);
    }

    private List<Departamento> findDepartamentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Departamento.class));
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

    public Departamento findDepartamento(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Departamento.class, id);
        } finally {
            em.close();
        }
    }

    public int getDepartamentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Departamento> rt = cq.from(Departamento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
