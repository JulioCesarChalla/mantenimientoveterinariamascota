/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import dao.PersonaDao;
import dao.PersonalDao;
import entidades.Persona;
import entidades.Personal;
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
public class PersonalBean implements Serializable{
    private ArrayList listaPersona;
    private int idPersona;
    private Personal personal;
    private Persona persona;
    private boolean banderaSelect = false;
    
    public PersonalBean(){
        this.personal= new Personal();
        listaPersona= new ArrayList();
        persona= new Persona();
        listarPersona();
    }
    public void listarPersona(){
         PersonaDao personaDao = new PersonaDao();
        listaPersona= personaDao.listarPersonas();
    }
    public String guardarPersonal() {
        try {

            PersonalDao personalDao = new PersonalDao();
            persona.setIdPersona(idPersona);
            personal.setPersona(persona);
            boolean respuesta = personalDao.guardarPersonal(personal);
            if (respuesta) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se regidtro correctamente"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se puedo registrar"));
            }
        } catch (HibernateException e) {
            ///transation.rollback();  -- regresa a la anterior
            System.out.println("Error::: " + e);
        }
        return "/Personal";
    }

    public String actualizarPersonal() {
        try {
            PersonalDao personalDao = new PersonalDao();
            boolean respuesta = personalDao.actualizarPersonal(personal);
            if (respuesta) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se actualizo correctamente"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se puedo actualizar"));
            }
        } catch (HibernateException e) {
            ///transation.rollback();  -- regresa a la anterior
            System.out.println("Error::: " + e);
        }
        return "/Personal";

    }

    public ArrayList<Personal> listarPersonal() {
        ArrayList<Personal> lista = new ArrayList<>();
        PersonalDao personalDao = new PersonalDao();
        lista = personalDao.listarPersonales();
        return lista;
    }

    public String eliminar() {
        PersonalDao personalDao = new PersonalDao();
        boolean respuesta = personalDao.eliminarPersonal(personal);
        if (respuesta) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se elimino correctamente"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se puedo eliminar"));
        }
        return "/Personal";
    }

    
    
    public String limpiar() {
        return "/Personal";
    }

    public void selectBandera() {
        banderaSelect = true;
    }


    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public boolean isBanderaSelect() {
        return banderaSelect;
    }

    public void setBanderaSelect(boolean banderaSelect) {
        this.banderaSelect = banderaSelect;
    }

    public ArrayList getListaPersona() {
        return listaPersona;
    }

    public void setListaPersona(ArrayList listaPersona) {
        this.listaPersona = listaPersona;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    
    
    
}
