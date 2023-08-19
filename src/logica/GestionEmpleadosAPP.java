/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.util.List;
import persistencia.ControlPersistencia;

/**
 *
 * @author NICOLAS
 */
public class GestionEmpleadosAPP {
    public static void main(String [] args){
        ControlPersistencia con = new ControlPersistencia();
        List<Cargo> lista = con.mostrarCargos();
        
        for (Cargo car : lista) {
            System.out.println(car.getIdCargo()+" - "+car.getNomCargo());
        }
        
    }
}
