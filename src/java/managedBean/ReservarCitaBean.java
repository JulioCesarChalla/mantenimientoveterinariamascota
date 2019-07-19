/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import dao.ClienteDao;
import dao.PersonalDao;
import dao.ReservarCitaDao;
import dao.TiporeservaDao;
import entidades.Cliente;
import entidades.Persona;
import entidades.Personal;
import entidades.Reservarcita;
import entidades.Tiporeserva;
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
public class ReservarCitaBean implements Serializable {

    private ArrayList listaPersonal;
    private ArrayList listaTipoReserva;
    private ArrayList listaCliente;
    private int idPersonal;
    private int idTiporeserva;
    private int codigoCliente;
    private Personal personal;
    private Tiporeserva tiporeserva;
    private Cliente cliente;
    private Persona persona;

    private Reservarcita reservarcita;

    private boolean banderaSelect = false;

    public ReservarCitaBean() {
        this.reservarcita = new Reservarcita();
        listaPersonal = new ArrayList();
        listaTipoReserva = new ArrayList();
        listaCliente = new ArrayList();
        personal = new Personal();
        tiporeserva = new Tiporeserva();
        cliente = new Cliente();
        persona = new Persona();
        combosSelecciones();
    }

    public void combosSelecciones() {
        PersonalDao personalDao = new PersonalDao();
        listaPersonal = personalDao.listarPersonales();
        TiporeservaDao tiporeservaDao = new TiporeservaDao();
        listaTipoReserva = tiporeservaDao.listarTiporeservas();
        ClienteDao clienteDao = new ClienteDao();
        listaCliente = clienteDao.listarClientes();
    }

    public String guardarReservarCita() {
        try {

            ReservarCitaDao reservarCitaDao = new ReservarCitaDao();
            personal.setIdpersonal(idPersonal);
            tiporeserva.setIdtiporeserva(idTiporeserva);
            cliente.setCodigoCliente(codigoCliente);
            reservarcita.setPersonal(personal);
            reservarcita.setTiporeserva(tiporeserva);
            reservarcita.setCliente(cliente);
            boolean respuesta = reservarCitaDao.guardarReservarcita(reservarcita);
            if (respuesta) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se regidtro correctamente"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se puedo registrar"));
            }
        } catch (HibernateException e) {
            ///transation.rollback();  -- regresa a la anterior
            System.out.println("Error::: " + e);
        }
        return "/ReservarCita";
    }

    public String actualizarReservarCita() {
        try {
            ReservarCitaDao reservarCitaDao = new ReservarCitaDao();
            boolean respuesta = reservarCitaDao.actualizarReservarcita(reservarcita);
            if (respuesta) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se actualizo correctamente"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se puedo actualizar"));
            }
        } catch (HibernateException e) {
            ///transation.rollback();  -- regresa a la anterior
            System.out.println("Error::: " + e);
        }
        return "/ReservarCita";

    }

    public ArrayList<Reservarcita> listarReservarCita() {
        ArrayList<Reservarcita> lista = new ArrayList<>();
        ReservarCitaDao reservarCitaDao = new ReservarCitaDao();
        lista = reservarCitaDao.listarReservarcitas();
        return lista;
    }

    public String eliminar() {
        ReservarCitaDao reservarCitaDao = new ReservarCitaDao();
        boolean respuesta = reservarCitaDao.eliminarReservarcita(reservarcita);
        if (respuesta) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se elimino correctamente"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se puedo eliminar"));
        }
        return "/ReservarCita";
    }

    public String limpiar() {
        return "/ReservarCita";
    }

    public void selectBandera() {
        banderaSelect = true;
    }

    public ArrayList getListaPersonal() {
        return listaPersonal;
    }

    public void setListaPersonal(ArrayList listaPersonal) {
        this.listaPersonal = listaPersonal;
    }

    public ArrayList getListaTipoReserva() {
        return listaTipoReserva;
    }

    public void setListaTipoReserva(ArrayList listaTipoReserva) {
        this.listaTipoReserva = listaTipoReserva;
    }

    public ArrayList getListaCliente() {
        return listaCliente;
    }

    public void setListaCliente(ArrayList listaCliente) {
        this.listaCliente = listaCliente;
    }

    public int getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(int idPersonal) {
        this.idPersonal = idPersonal;
    }

    public int getIdTiporeserva() {
        return idTiporeserva;
    }

    public void setIdTiporeserva(int idTiporeserva) {
        this.idTiporeserva = idTiporeserva;
    }

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public Tiporeserva getTiporeserva() {
        return tiporeserva;
    }

    public void setTiporeserva(Tiporeserva tiporeserva) {
        this.tiporeserva = tiporeserva;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Reservarcita getReservarcita() {
        return reservarcita;
    }

    public void setReservarcita(Reservarcita reservarcita) {
        this.reservarcita = reservarcita;
    }

    public boolean isBanderaSelect() {
        return banderaSelect;
    }

    public void setBanderaSelect(boolean banderaSelect) {
        this.banderaSelect = banderaSelect;
    }

}
