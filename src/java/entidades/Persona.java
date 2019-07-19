package entidades;
// Generated 18/07/2019 10:48:46 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Persona generated by hbm2java
 */
public class Persona  implements java.io.Serializable {


     private Integer idPersona;
     private String nombre;
     private String apellido;
     private String telefono;
     private String email;
     private Set clientes = new HashSet(0);
     private Set personals = new HashSet(0);
     private Set usuarios = new HashSet(0);

    public Persona() {
    }

    public Persona(String nombre, String apellido, String telefono, String email, Set clientes, Set personals, Set usuarios) {
       this.nombre = nombre;
       this.apellido = apellido;
       this.telefono = telefono;
       this.email = email;
       this.clientes = clientes;
       this.personals = personals;
       this.usuarios = usuarios;
    }
   
    public Integer getIdPersona() {
        return this.idPersona;
    }
    
    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return this.apellido;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public Set getClientes() {
        return this.clientes;
    }
    
    public void setClientes(Set clientes) {
        this.clientes = clientes;
    }
    public Set getPersonals() {
        return this.personals;
    }
    
    public void setPersonals(Set personals) {
        this.personals = personals;
    }
    public Set getUsuarios() {
        return this.usuarios;
    }
    
    public void setUsuarios(Set usuarios) {
        this.usuarios = usuarios;
    }




}

