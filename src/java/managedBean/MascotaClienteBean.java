/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import dao.ClienteDao;
import dao.MascotaDao;
import dao.MascotaporclienteDao;
import entidades.Cliente;
import entidades.Mascota;
import entidades.Mascotaporcliente;
import entidades.MascotaporclienteId;
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
public class MascotaClienteBean implements Serializable {

    private ArrayList listaClientes;
    private ArrayList listaMascotas;
    private int idCliente;
    private int idMacota;
    private Persona persona;
    private Mascota mascota;
    private Cliente cliente;

    private MascotaporclienteDao mascotaporCienteDao;
    private Mascotaporcliente mascotaporcliente;
    private MascotaporclienteId mascotaporclienteId;

    private boolean banderaSelect = false;

    public MascotaClienteBean() {
        this.mascotaporcliente = new Mascotaporcliente();
        listaClientes = new ArrayList();
        listaMascotas = new ArrayList();
        persona = new Persona();
        mascota = new Mascota();
        cliente= new Cliente();
        mascotaporclienteId = new MascotaporclienteId();
        combos();

    }

    public void combos() {
        ClienteDao clienteDao = new ClienteDao();
        listaClientes = clienteDao.listarClientes();
        MascotaDao mascotaDao = new MascotaDao();
        listaMascotas = mascotaDao.listarMascotas();
    }

    public String guardarMascotaCliente() {
        try {

            MascotaporclienteDao mascotaporClienteDao = new MascotaporclienteDao();
            mascotaporclienteId.setCodigoCliente(idCliente);
            mascotaporclienteId.setIdMascota(idMacota);
            mascotaporcliente.setId(mascotaporclienteId);
            boolean respuesta = mascotaporClienteDao.guardarMascotaporcliente(mascotaporcliente);
            System.out.println("ERRRO BOL::" + respuesta);
            if (respuesta) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se regidtro correctamente"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se puedo registrar"));
            }
        } catch (HibernateException e) {
            ///transation.rollback();  -- regresa a la anterior
            System.out.println("Error::: " + e);
        }
        return "/Mascotaporcliente";
    }

    public String actualizarMascotaCliente() {
        try {
            MascotaporclienteDao mascotaporClienteDao = new MascotaporclienteDao();
            boolean respuesta = mascotaporClienteDao.actualizarMascotaporcliente(mascotaporcliente);
            if (respuesta) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se actualizo correctamente"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se puedo actualizar"));
            }
        } catch (HibernateException e) {
            ///transation.rollback();  -- regresa a la anterior
            System.out.println("Error::: " + e);
        }
        return "/Mascotaporcliente";

    }

    public ArrayList<Mascotaporcliente> listarMascotaCliente() {
        ArrayList<Mascotaporcliente> lista = new ArrayList<>();
        MascotaporclienteDao mascotaporClienteDao = new MascotaporclienteDao();
        lista = mascotaporClienteDao.listarMascotaporclientes();
        return lista;
    }

    public String eliminar() {
        MascotaporclienteDao mascotaporClienteDao = new MascotaporclienteDao();
        boolean respuesta = mascotaporClienteDao.eliminarMascotaporcliente(mascotaporcliente);
        if (respuesta) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se elimino correctamente"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se puedo eliminar"));
        }
        return "/Mascotaporcliente";
    }

    public String limpiar() {
        return "/Mascotaporcliente";
    }

    public void selectBandera() {
        banderaSelect = true;
    }

    public MascotaporclienteDao getMascotaporCienteDao() {
        return mascotaporCienteDao;
    }

    public void setMascotaporCienteDao(MascotaporclienteDao mascotaporCienteDao) {
        this.mascotaporCienteDao = mascotaporCienteDao;
    }

    public ArrayList getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(ArrayList listaClientes) {
        this.listaClientes = listaClientes;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdMacota() {
        return idMacota;
    }

    public void setIdMacota(int idMacota) {
        this.idMacota = idMacota;
    }

    public ArrayList getListaMascotas() {
        return listaMascotas;
    }

    public void setListaMascotas(ArrayList listaMascotas) {
        this.listaMascotas = listaMascotas;
    }

    public Mascotaporcliente getMascotaporcliente() {
        return mascotaporcliente;
    }

    public void setMascotaporcliente(Mascotaporcliente mascotaporcliente) {
        this.mascotaporcliente = mascotaporcliente;
    }

    public boolean isBanderaSelect() {
        return banderaSelect;
    }

    public void setBanderaSelect(boolean banderaSelect) {
        this.banderaSelect = banderaSelect;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    

}
