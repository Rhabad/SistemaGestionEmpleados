/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author NICOLAS
 */
@Entity
public class Empleado {
    @Id
    private String rut;
    @Basic
    private String nombres;
    private String apellidos;
    private String fechaNacimiento;
    private String direccion;
    private int telefono;
    private String email;
    
    
    @ManyToOne
    private Cargo cargo;
    @ManyToOne
    private Departamento depart;
    
    @OneToMany (mappedBy = "emple")
    private List<ContactoEmergencia> contEme;

    public Empleado(String rut, String nombres, String apellidos, String fechaNacimiento, String direccion, int telefono, String email, Cargo cargo, Departamento depart, List<ContactoEmergencia> contEme) {
        this.rut = rut;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.cargo = cargo;
        this.depart = depart;
        this.contEme = contEme;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Departamento getDepart() {
        return depart;
    }

    public void setDepart(Departamento depart) {
        this.depart = depart;
    }

    public List<ContactoEmergencia> getContEme() {
        return contEme;
    }

    public void setContEme(List<ContactoEmergencia> contEme) {
        this.contEme = contEme;
    }
    
    
}
