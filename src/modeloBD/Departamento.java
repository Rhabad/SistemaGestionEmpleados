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
@Entity(name = "departamento")
public class Departamento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idDepartamento")
    private int idDepartamento;
    @Column(name = "nomDepartamento")
    private String nomDepartamento;
    
    @OneToMany(mappedBy = "departamentoFK")
    private List<Empleado> empleadoDepartamento;
    
    public Departamento(){
    }

    public Departamento(String nomDepartamento) {
        this.nomDepartamento = nomDepartamento;
    }
    
    public Departamento(int idDepartamento, String nomDepartamento) {
        this.idDepartamento = idDepartamento;
        this.nomDepartamento = nomDepartamento;
    }

    public Departamento(int idDepartamento, String nomDepartamento, List<Empleado> empleadoDepartamento) {
        this.idDepartamento = idDepartamento;
        this.nomDepartamento = nomDepartamento;
        this.empleadoDepartamento = empleadoDepartamento;
    }

    

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getNomDepartamento() {
        return nomDepartamento;
    }

    public void setNomDepartamento(String nomDepartamento) {
        this.nomDepartamento = nomDepartamento;
    }

    public List<Empleado> getEmpleadoDepartamento() {
        return empleadoDepartamento;
    }

    public void setEmpleadoDepartamento(List<Empleado> empleadoDepartamento) {
        this.empleadoDepartamento = empleadoDepartamento;
    }

    @Override
    public String toString() {
        return "Departamento{" + "idDepartamento=" + idDepartamento + ", nomDepartamento=" + nomDepartamento + '}';
    }
}
