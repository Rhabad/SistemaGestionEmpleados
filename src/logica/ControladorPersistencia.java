/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author NICOLAS
 */
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modeloBD.*;
import persistencia.*;
import persistencia.exceptions.NonexistentEntityException;
public class ControladorPersistencia {
    
    CargoJpaController carJPA = new CargoJpaController();
    DepartamentoJpaController depJPA = new DepartamentoJpaController();
    EmpleadoJpaController empJPA = new EmpleadoJpaController();
    ContactosEmergenciaJpaController conJPA = new ContactosEmergenciaJpaController();
    
    
    //======== CARGO ==========
    public void crearCargo(Cargo cargo){
        carJPA.create(cargo);
    }
    public List<Cargo> mostrarCargos(){
        List<Cargo> cargo = carJPA.findCargoEntities();
        return cargo;
    }
    public Cargo traerCargo(int id){
        Cargo cargo = carJPA.findCargo(id);
        return cargo;
    }
    public void actualizarCargo(Cargo cargo){
        try {
            carJPA.edit(cargo);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void eliminarCargo(int id){
        try {
            carJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    //======== DEPARTAMENTO ==========
    public void crearDepartamento(Departamento departamento){
        depJPA.create(departamento);
    }
    public List<Departamento> mostrarDepartamentos(){
        List<Departamento> depar = depJPA.findDepartamentoEntities();
        return depar;
    }
    public Departamento traerDepartamento(int id){
        Departamento depar = depJPA.findDepartamento(id);
        return depar;
    }
    public void actualizarDepartamentos(Departamento departamento){
        try {
            depJPA.edit(departamento);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void eliminarDepartamento(int id){
        try {
            depJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    //======== EMPLEADO ==========
    public void crearEmpleado(Empleado empleado){
        try {
            empJPA.create(empleado);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<Empleado> mostrarEmpleados(){
        List<Empleado> emple = empJPA.findEmpleadoEntities();
        return emple;
    }
    public Empleado traerEmpleado(String id){
        Empleado emple = empJPA.findEmpleado(id);
        return emple;
    }
    public void actualizarEmpleado(Empleado empleado){
        try {
            empJPA.edit(empleado);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void eliminarEmpleado(String id){
        try {
            empJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    //======== CONTACTO EMERGENCIA ==========
    public void crearContactos(ContactosEmergencia contacto){
        try {
            conJPA.create(contacto);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<ContactosEmergencia> mostrarContactos(){
        List<ContactosEmergencia> contacto = conJPA.findContactosEmergenciaEntities();
        return contacto;
    }
    public ContactosEmergencia traerContacto(String id){
        ContactosEmergencia contacto = conJPA.findContactosEmergencia(id);
        return contacto;
    }
    public void actualizarContacto(ContactosEmergencia contacto){
        try {
            conJPA.edit(contacto);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void eliminarContacto(String id){
        try {
            conJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
