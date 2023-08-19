/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author NICOLAS
 */
@Entity
public class Cargo implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int idCargo;
    @Basic
    private String nomCargo;
    
    @OneToMany (mappedBy = "cargo")
    private List<Empleado> listaEmpleado;
    
    public Cargo(){
    }
    
    public Cargo(int idCargo, String nomCargo, List<Empleado> listaEmpleado){
        this.idCargo = idCargo;
        this.nomCargo = nomCargo;
        this.listaEmpleado = listaEmpleado;
    }
    
    public int getIdCargo(){
        return idCargo;
    }
    public void setIdCargo(int idCargo){
        this.idCargo = idCargo;
    }
    public String getNomCargo(){
        return nomCargo;
    }
    public void setNomCargo(String nomCargo){
        this.nomCargo = nomCargo;
    }
    public List<Empleado> getListaEmpleado(){
        return listaEmpleado;
    }
    public void setListaEmpleado(List<Empleado> listaEmpleado){
        this.listaEmpleado = listaEmpleado;
    }
}
