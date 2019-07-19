/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import dao.AtencionDao;
import dao.ClienteDao;
import dao.MascotaDao;
import dao.PersonalDao;
import dao.TipoatencionDao;
import entidades.Atencion;
import entidades.Cliente;
import entidades.Mascota;
import entidades.Mascotaporcliente;
import entidades.MascotaporclienteId;
import entidades.Persona;
import entidades.Personal;
import entidades.Tipoatencion;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class AtencionBean implements Serializable {

    private ArrayList listaMascota;
    private ArrayList listaCliente;
    private ArrayList listaPersonal;
    private ArrayList listaTipoAtencion;
    private int idMascota;
    private int idCliente;
    private int idPersonal;
    private int idTipoAtencion;
    private Mascota mascota;
    private Cliente cliente;
    private Personal personal;
    private Tipoatencion tipoatencion;
    private Persona persona;

    private Mascotaporcliente mascotaporcliente;
    private MascotaporclienteId mascotaporclienteId;

    private Atencion atencion;
    private boolean banderaSelect = false;

    Date date = new Date();

    public AtencionBean() {
        this.atencion = new Atencion();
        listaMascota = new ArrayList();
        listaCliente = new ArrayList();
        listaPersonal = new ArrayList();
        listaTipoAtencion = new ArrayList();
        mascota = new Mascota();
        cliente = new Cliente();
        personal = new Personal();
        tipoatencion = new Tipoatencion();
        persona = new Persona();
        combosSeleccion();
    }

    public void combosSeleccion() {
        MascotaDao mascotaDao = new MascotaDao();
        listaMascota = mascotaDao.listarMascotas();
        ClienteDao clienteDao = new ClienteDao();
        listaCliente = clienteDao.listarClientes();
        PersonalDao personalDao = new PersonalDao();
        listaPersonal = personalDao.listarPersonales();
        TipoatencionDao tipoatencionDao = new TipoatencionDao();
        listaTipoAtencion = tipoatencionDao.listarTipoatencions();
    }

    public String guardarAtencion() {
        try {
            if (idMascota != 0 && idCliente != 0 && idPersonal != 0 && idTipoAtencion != 0) {
                AtencionDao atencionDao = new AtencionDao();
                mascotaporclienteId.setIdMascota(idMascota);
                mascotaporclienteId.setCodigoCliente(idCliente);
                mascotaporcliente.setId(mascotaporclienteId);
                System.out.println("id de dos:: mas y clien::" + idMascota + "++" + idMascota);
                System.out.println("setID" + mascotaporclienteId);
                personal.setIdpersonal(idPersonal);
                tipoatencion.setIdtipoatencion(idTipoAtencion);

                atencion.setMascotaporcliente(mascotaporcliente);
                atencion.setPersonal(personal);
                atencion.setTipoatencion(tipoatencion);
                try {
                    DateFormat hora = new SimpleDateFormat("HH:mm:ss");
                    String stringHora = hora.format(date);
                    Date convertido = hora.parse(stringHora);
                    System.out.println(convertido);
                    atencion.setHoraAtencion(convertido);
                } catch (ParseException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                boolean respuesta = atencionDao.guardarAtencion(atencion);
                if (respuesta) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se regidtro correctamente"));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se puedo registrar"));
                }
            } else {
                System.out.println("gravisisimo error");
            }

        } catch (HibernateException e) {
            ///transation.rollback();  -- regresa a la anterior
            System.out.println("Error::: " + e);
        }
        return "/Atencion";
    }

    public String actualizarAtencion() {
        try {
            AtencionDao atencionDao = new AtencionDao();
            boolean respuesta = atencionDao.actualizarAtencion(atencion);
            if (respuesta) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se actualizo correctamente"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se puedo actualizar"));
            }
        } catch (HibernateException e) {
            ///transation.rollback();  -- regresa a la anterior
            System.out.println("Error::: " + e);
        }
        return "/Atencion";

    }

    public ArrayList<Atencion> listarAtenciones() {
        ArrayList<Atencion> lista = new ArrayList<>();
        AtencionDao atencionDao = new AtencionDao();
        lista = atencionDao.listarAtenciones();
        return lista;
    }

    public String eliminar() {
        AtencionDao atencionDao = new AtencionDao();
        boolean respuesta = atencionDao.eliminarAtencion(atencion);
        if (respuesta) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se elimino correctamente"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se puedo eliminar"));
        }
        return "/Atencion";
    }

    public String limpiar() {
        return "/Atencion";
    }

    public void selectBandera() {
        banderaSelect = true;
    }

    public ArrayList getListaMascota() {
        return listaMascota;
    }

    public void setListaMascota(ArrayList listaMascota) {
        this.listaMascota = listaMascota;
    }

    public ArrayList getListaCliente() {
        return listaCliente;
    }

    public void setListaCliente(ArrayList listaCliente) {
        this.listaCliente = listaCliente;
    }

    public ArrayList getListaPersonal() {
        return listaPersonal;
    }

    public void setListaPersonal(ArrayList listaPersonal) {
        this.listaPersonal = listaPersonal;
    }

    public ArrayList getListaTipoAtencion() {
        return listaTipoAtencion;
    }

    public void setListaTipoAtencion(ArrayList listaTipoAtencion) {
        this.listaTipoAtencion = listaTipoAtencion;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(int idPersonal) {
        this.idPersonal = idPersonal;
    }

    public int getIdTipoAtencion() {
        return idTipoAtencion;
    }

    public void setIdTipoAtencion(int idTipoAtencion) {
        this.idTipoAtencion = idTipoAtencion;
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

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public Tipoatencion getTipoatencion() {
        return tipoatencion;
    }

    public void setTipoatencion(Tipoatencion tipoatencion) {
        this.tipoatencion = tipoatencion;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Atencion getAtencion() {
        return atencion;
    }

    public void setAtencion(Atencion atencion) {
        this.atencion = atencion;
    }

    public boolean isBanderaSelect() {
        return banderaSelect;
    }

    public void setBanderaSelect(boolean banderaSelect) {
        this.banderaSelect = banderaSelect;
    }

    public Mascotaporcliente getMascotaporcliente() {
        return mascotaporcliente;
    }

    public void setMascotaporcliente(Mascotaporcliente mascotaporcliente) {
        this.mascotaporcliente = mascotaporcliente;
    }

}
