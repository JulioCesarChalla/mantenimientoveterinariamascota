/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import dao.PersonaDao;
import dao.UsuarioDao;
import entidades.Persona;
import entidades.Usuario;
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
@ViewScoped
public class UsuarioBean implements Serializable {

    private ArrayList listarPersona;
    private Usuario usuario;
    private Persona persona;
    private int idPersona;
    private boolean banderaSelect = false;

    public UsuarioBean(){
         this.usuario = new Usuario();
        listarPersona = new ArrayList();
        persona = new Persona();
        listarPersona();
    }
     public void listarPersona() {
         PersonaDao personaDao = new PersonaDao();
         listarPersona= personaDao.listarPersonas();
     }
     public String guardarUsuario() {
        try {

            UsuarioDao usuarioDao = new UsuarioDao();
            persona.setIdPersona(idPersona);
            usuario.setPersona(persona);
            boolean respuesta = usuarioDao.guardarUsuario(usuario);
            if (respuesta) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se regidtro correctamente"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se puedo registrar"));
            }
        } catch (HibernateException e) {
            ///transation.rollback();  -- regresa a la anterior
            System.out.println("Error::: " + e);
        }
        return "/Usuario";
    }

    public String actualizarUsuario() {
        try {
            UsuarioDao usuarioDao = new UsuarioDao();
            boolean respuesta = usuarioDao.actualizarUsuario(usuario);
            if (respuesta) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se actualizo correctamente"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se puedo actualizar"));
            }
        } catch (HibernateException e) {
            ///transation.rollback();  -- regresa a la anterior
            System.out.println("Error::: " + e);
        }
        return "/Usuario";

    }

    public ArrayList<Usuario> listarUsuario() {
        ArrayList<Usuario> lista = new ArrayList<>();
        UsuarioDao usuarioDao = new UsuarioDao();
        lista = usuarioDao.listarUsuarios();
        return lista;
    }

    public String eliminar() {
        UsuarioDao usuarioDao = new UsuarioDao();
        boolean respuesta = usuarioDao.eliminarUsuario(usuario);
        if (respuesta) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se elimino correctamente"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se puedo eliminar"));
        }
        return "/Usuario";
    }

     public void selectBandera() {
        banderaSelect = true;
    }
     
       public String limpiar() {
        return "/Usuario";
    }
    
    
    public ArrayList getListarPersona() {
        return listarPersona;
    }

    public void setListarPersona(ArrayList listarPersona) {
        this.listarPersona = listarPersona;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public boolean isBanderaSelect() {
        return banderaSelect;
    }

    public void setBanderaSelect(boolean banderaSelect) {
        this.banderaSelect = banderaSelect;
    }

}
