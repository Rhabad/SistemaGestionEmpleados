/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeloBD;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author NICOLAS
 */
@Entity (name = "empleado")
public class Empleado implements Serializable {
    @Id
    @Column(name = "rut")
    private String rut;
    @Column(name = "nombres")
    private String nombres;
    @Column(name = "apellidos")
    private String apellidos;
    @Column(name = "fechaNacimiento")
    private Date fechaNacimiento;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "telefono")
    private int telefono;
    @Column(name = "email")
    private String email;
    
    @ManyToOne
    @JoinColumn(name = "cargoFK")
    private Cargo cargoFK;
    @ManyToOne
    @JoinColumn(name = "departamentoFK")
    private Departamento departamentoFK;
    @OneToMany(mappedBy = "rutFK")
    private List<ContactosEmergencia> contacto;
    
    
    public Empleado(){
    }

    public Empleado(String rut, String nombres, String apellidos, Date fechaNacimiento, String direccion, int telefono, String email, Cargo cargoFK, Departamento departamentoFK, List<ContactosEmergencia> contacto) {
        this.rut = rut;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.cargoFK = cargoFK;
        this.departamentoFK = departamentoFK;
        this.contacto = contacto;
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
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

    public Cargo getCargoFK() {
        return cargoFK;
    }

    public void setCargoFK(Cargo cargoFK) {
        this.cargoFK = cargoFK;
    }

    public Departamento getDepartamentoFK() {
        return departamentoFK;
    }

    public void setDepartamentoFK(Departamento departamentoFK) {
        this.departamentoFK = departamentoFK;
    }

    public List<ContactosEmergencia> getContacto() {
        return contacto;
    }

    public void setContacto(List<ContactosEmergencia> contacto) {
        this.contacto = contacto;
    }
    
    
}
