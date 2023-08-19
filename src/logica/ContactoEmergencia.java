/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import javax.persistence.ManyToOne;

/**
 *
 * @author NICOLAS
 */
public class ContactoEmergencia {
    
    
    
    @ManyToOne
    private Empleado emple;
}
