/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeloBD;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author NICOLAS
 */
@Entity
public class ContactosEmergencia implements Serializable {
    @Id
    @Column(name = "rutContacto")
    private String rutContacto;
    @Column(name = "nombreContacto")
    private String nombreContacto;
    @Column(name = "apellidoContacto")
    private String apellidoContacto;
    @Column(name = "telefonoContacto")
    private int telefonoContacto;
    @Column(name = "email")
    private String email;
    
    @ManyToOne
    @JoinColumn(name = "rutFK")
    private Empleado rutFK;
    
    public ContactosEmergencia(){
    }

    public ContactosEmergencia(String rutContacto, String nombreContacto, String apellidoContacto, int telefonoContacto, String email, Empleado rutFK) {
        this.rutContacto = rutContacto;
        this.nombreContacto = nombreContacto;
        this.apellidoContacto = apellidoContacto;
        this.telefonoContacto = telefonoContacto;
        this.email = email;
        this.rutFK = rutFK;
    }

    

    public String getRutContacto() {
        return rutContacto;
    }

    public void setRutContacto(String rutContacto) {
        this.rutContacto = rutContacto;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getApellidoContacto() {
        return apellidoContacto;
    }

    public void setApellidoContacto(String apellidoContacto) {
        this.apellidoContacto = apellidoContacto;
    }

    public int getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(int telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Empleado getRutFK() {
        return rutFK;
    }

    public void setRutFK(Empleado rutFK) {
        this.rutFK = rutFK;
    }

    @Override
    public String toString() {
        return "ContactosEmergencia{" + "rutContacto=" + rutContacto + ", nombreContacto=" + nombreContacto + ", apellidoContacto=" + apellidoContacto + ", telefonoContacto=" + telefonoContacto + ", email=" + email + ", rutFK=" + rutFK.getRut() + '}';
    }
    
    
    
}
