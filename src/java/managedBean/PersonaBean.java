/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import dao.PersonaDao;
import entidades.Persona;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.hibernate.HibernateException;

/**
 *
 * @author Julio Cesar
 */
@ManagedBean
//@RequestScoped
@ViewScoped
public class PersonaBean implements Serializable {

    private Persona persona;
    private boolean banderaSelect = false;

    public PersonaBean() {
        this.persona = new Persona();
    }

    public String guardarPersona() {
        try {

            PersonaDao personaDao = new PersonaDao();
            boolean respuesta = personaDao.guardarPersona(persona);
            if (respuesta) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se regidtro correctamente"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se puedo registrar"));
            }
        } catch (HibernateException e) {
            ///transation.rollback();  -- regresa a la anterior
            System.out.println("Error::: " + e);
        }
        return "/Persona";
    }

    public String actualizarPersona() {
        try {
            PersonaDao personaDao = new PersonaDao();
            boolean respuesta = personaDao.actualizarPersona(persona);
            if (respuesta) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se actualizo correctamente"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se puedo actualizar"));
            }
        } catch (HibernateException e) {
            ///transation.rollback();  -- regresa a la anterior
            System.out.println("Error::: " + e);
        }
        return "/Persona";

    }

    public ArrayList<Persona> listarPersonas() {
        ArrayList<Persona> lista = new ArrayList<>();
        PersonaDao personaDao = new PersonaDao();
        lista = personaDao.listarPersonas();
        return lista;
    }

    public String eliminar() {
        PersonaDao personaDao = new PersonaDao();
        boolean respuesta = personaDao.eliminarPersona(persona);
        if (respuesta) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se elimino correctamente"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se puedo eliminar"));
        }
        return "/Persona";
    }

    public String limpiar() {
        return "/Persona";
    }

    public void selectBandera() {
        banderaSelect = true;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public boolean isBanderaSelect() {
        return banderaSelect;
    }

    public void setBanderaSelect(boolean banderaSelect) {
        this.banderaSelect = banderaSelect;
    }

}
