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
import modeloBD.*;
import persistencia.*;
public class ControladorPersistencia {
    
    CargoJpaController carJPA = new CargoJpaController();
    DepartamentoJpaController depJPA = new DepartamentoJpaController();
    EmpleadoJpaController empJPA = new EmpleadoJpaController();
    ContactosEmergenciaJpaController conJPA = new ContactosEmergenciaJpaController();
    
    //======== CARGO ==========
    
    
    
    
    //======== DEPARTAMENTO ==========
    
    
    
    
    
    //======== EMPLEADO ==========
    public List<Empleado> mostrarEmpleados(){
        List<Empleado> emple = empJPA.findEmpleadoEntities();
        return emple;
    }
    
    
    
    //======== CONTACTO EMERGENCIA ==========
    
    
    
    
    
}
