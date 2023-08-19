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
public class Departamento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idDepartamento;
    @Basic
    private String nomDepartamento;
    
    @OneToMany (mappedBy = "depart")
    public List<Empleado> listaEmpleado;
    
    
    public Departamento(){
    }

    public Departamento(int idDepartamento, String nomDepartamento, List<Empleado> listaEmpleado) {
        this.idDepartamento = idDepartamento;
        this.nomDepartamento = nomDepartamento;
        this.listaEmpleado = listaEmpleado;
    }

    public int getIdDepartamento(){
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
    
    public List<Empleado> getListaEmpleado(){
        return listaEmpleado;
    }
    public void setListaEmpleado(List<Empleado> listaEmpleado){
        this.listaEmpleado = listaEmpleado;
    }
    
    
}
