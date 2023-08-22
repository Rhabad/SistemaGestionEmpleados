/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.util.List;
import modeloBD.Cargo;
import modeloBD.Departamento;

/**
 *
 * @author NICOLAS
 */
public class Logica {
    private ControladorPersistencia con = new ControladorPersistencia();
    
    //encontrar el id, pasando por todos los registros de cargo.
    public int encontrarIdCargo(String nombreCargo){
        int id = 0;
        
        List<Cargo> listCargo = con.mostrarCargos();
        for (Cargo car : listCargo) {
            if (nombreCargo.equals(car.getNomCargo())) {
                id = car.getIdCargo();
            }
        }
        return id;
    }
    
    //CON DEPARTAMENTO
    public int encontrarIdDepartamento(String nombreDepartamento){
        int id = 0;
        
        List<Departamento> listDepa = con.mostrarDepartamentos();
        for (Departamento depa : listDepa) {
            if (nombreDepartamento.equals(depa.getNomDepartamento())) {
                id = depa.getIdDepartamento();
            }
        }
        return id;
    }
    
}
