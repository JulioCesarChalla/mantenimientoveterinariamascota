/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import dao.ClienteDao;
import dao.PersonaDao;
import entidades.Cliente;
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
public class ClienteBean implements Serializable{
     private ArrayList listaPersona;
    private int idPersona;
    private Persona persona;
    private Cliente cliente;
    private boolean banderaSelect = false;

    public ClienteBean(){
        this.cliente= new Cliente();
         listaPersona= new ArrayList();
        persona= new Persona();
        listarPersona();
    }
    
    public void listarPersona(){
         PersonaDao personaDao = new PersonaDao();
        listaPersona= personaDao.listarPersonas();
    }
    public String guardarCliente() {
        try {

            ClienteDao clienteDao = new ClienteDao();
            persona.setIdPersona(idPersona);
            cliente.setPersona(persona);
            boolean respuesta = clienteDao.guardarCliente(cliente);
            if (respuesta) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se regidtro correctamente"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se puedo registrar"));
            }
        } catch (HibernateException e) {
            ///transation.rollback();  -- regresa a la anterior
            System.out.println("Error::: " + e);
        }
        return "/Cliente";
    }

    public String actualizarCliente() {
        try {
            ClienteDao clienteDao = new ClienteDao();
            boolean respuesta = clienteDao.actualizarCliente(cliente);
            if (respuesta) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se actualizo correctamente"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se puedo actualizar"));
            }
        } catch (HibernateException e) {
            ///transation.rollback();  -- regresa a la anterior
            System.out.println("Error::: " + e);
        }
        return "/Cliente";

    }

    public ArrayList<Cliente> listarCliente() {
        ArrayList<Cliente> lista = new ArrayList<>();
        ClienteDao clienteDao = new ClienteDao();
        lista = clienteDao.listarClientes();
        return lista;
    }

    public String eliminar() {
        ClienteDao clienteDao = new ClienteDao();
        boolean respuesta = clienteDao.eliminarCliente(cliente);
        if (respuesta) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se elimino correctamente"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se puedo eliminar"));
        }
        return "/Cliente";
    }

    
    
    public String limpiar() {
        return "/Cliente";
    }

    public void selectBandera() {
        banderaSelect = true;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public boolean isBanderaSelect() {
        return banderaSelect;
    }

    public void setBanderaSelect(boolean banderaSelect) {
        this.banderaSelect = banderaSelect;
    }
    
    
    
}
