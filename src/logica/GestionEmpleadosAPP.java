/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.util.List;
import modeloBD.*;

/**
 *
 * @author NICOLAS
 */
public class GestionEmpleadosAPP {
    public static void main(String [] args){
        ControladorPersistencia con = new ControladorPersistencia();
        
        /*
        System.out.println("TODOS LOS CARGOS");
        
        List<Cargo> car = con.mostrarCargos();
        
        for (Cargo cargo : car) {
            System.out.println(cargo.getIdCargo()+" - "+cargo.getNomCargo());
        }
        
        System.out.println("TODOS LOS DEPARTAMENTOS");
        List<Departamento> depart = con.mostrarDepartamentos();
        for (Departamento dep : depart) {
            System.out.println(dep.getIdDepartamento()+" - "+dep.getNomDepartamento());
        }
        
        System.out.println("TODOS LOS CONTACTOS DE EMERGENCIA");
        List<ContactoEmergencia> contac = con.mostrarContactos();
        for (ContactoEmergencia conta : contac) {
            System.out.println(conta.getRutContactos()+" - "+conta.getNomContactos()+" - "+conta.getApellContactos()+" - "+conta.getTelefonoContactos()+" - "+conta.getEmail()+" - "+conta.getEmple());
        }*/
        System.out.println("TODOS LOS EMPLEADOS");
        List<Empleado> empLista = con.mostrarEmpleados();
        
        for (Empleado emp : empLista) {
            System.out.println(emp.getRut()+" - "+emp.getNombres()+" - "+emp.getApellidos()+" - "+emp.getFechaNacimiento()+" - "+emp.getDireccion()+" - "+emp.getTelefono()+" - "+emp.getEmail()+" - "+emp.getCargoFK()+" - "+emp.getDepartamentoFK());
        }

    }
}
