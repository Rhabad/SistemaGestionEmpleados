/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.Cargo;
import logica.Departamento;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author NICOLAS
 */
public class ControlPersistencia {
    
    CargoJpaController cargoJPA = new CargoJpaController();
    DepartamentoJpaController deparJPA = new DepartamentoJpaController();
    
    //================ TABLA CARGO =================
    
    //creamos un cargo nuevo
    public void crearCargo(Cargo car){
            cargoJPA.create(car);
    }
    
    //mostramos los cargos actuales.
    public List<Cargo> mostrarCargos(){
        List<Cargo> cargoLista = cargoJPA.findCargoEntities();
        return cargoLista;
    }
    //un registro en particular
    public Cargo registrCargos(int idCargo){
        Cargo car = cargoJPA.findCargo(idCargo);
        return car;
    }
    
    //actualizamos los cargos, si es que lo necesitamos.
    public void actualizarCargo(Cargo car){
        try {
            cargoJPA.edit(car);
        } catch (Exception ex) {
            Logger.getLogger(ControlPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Eliminamos un registro de la tabla cargo.
    public void eliminarCargo(int idCargo){
        try {
            cargoJPA.destroy(idCargo);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControlPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    //================ TABLA DEPARTAMENTO =================
    //crear un departamento nuevo
    public void crearDepartamento(Departamento depar){
        deparJPA.create(depar);
    }
    
    //mostramos todos los registros de departamento
    public List<Departamento> mostrarDepartamentos(){
        List<Departamento> depart = deparJPA.findDepartamentoEntities();
        return depart;
    }
    //mostrar un registro en particular
    public void traerDepartamento(int id){
        deparJPA.findDepartamento(id);
    }
    
    //actualizar los datos de un registro en departamento
    public void actualizarDepartamento(Departamento depart){
        try {
            deparJPA.edit(depart);
        } catch (Exception ex) {
            Logger.getLogger(ControlPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //borrar registros en tabla departamentos
    public void eliminarDepartament(int id){
        try {
            deparJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControlPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
