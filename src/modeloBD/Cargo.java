/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeloBD;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author NICOLAS
 */
@Entity(name = "cargo")
public class Cargo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idCargo")
    private int idCargo;
    @Column(name = "nomCargo")
    private String nomCargo;
    
    @OneToMany(mappedBy = "cargoFK")
    //@JoinColumn
    private List<Empleado> empleadoCargo;
    
    public Cargo(){
    }

    public Cargo(String nomCargo) {
        this.nomCargo = nomCargo;
    }

    public Cargo(int idCargo, String nomCargo) {
        this.idCargo = idCargo;
        this.nomCargo = nomCargo;
    }
    
    
    
    

    public Cargo(int idCargo, String nomCargo, List<Empleado> empleadoCargo) {
        this.idCargo = idCargo;
        this.nomCargo = nomCargo;
        this.empleadoCargo = empleadoCargo;
    }
    
    

    public int getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(int idCargo) {
        this.idCargo = idCargo;
    }

    public String getNomCargo() {
        return nomCargo;
    }

    public void setNomCargo(String nomCargo) {
        this.nomCargo = nomCargo;
    }

    public List<Empleado> getEmpleadoCargo() {
        return empleadoCargo;
    }

    public void setEmpleadoCargo(List<Empleado> empleadoCargo) {
        this.empleadoCargo = empleadoCargo;
    }

    @Override
    public String toString() {
        return "Cargo{" + "idCargo=" + idCargo + ", nomCargo=" + nomCargo + '}';
    }

    
    
}
