/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entidades.Persona;
import java.util.ArrayList;

/**
 *
 * @author Julio Cesar
 */
public interface IPersona {
     public abstract boolean guardarPersona(Persona persona);

    public abstract ArrayList<Persona> listarPersonas();

    public abstract boolean actualizarPersona(Persona persona);

    public abstract boolean eliminarPersona(Persona persona);   
}
