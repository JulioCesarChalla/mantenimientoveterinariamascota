/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import dao.MascotaDao;
import entidades.Mascota;
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
public class MascotaBean implements Serializable {

    /**
     * SE COMUNICA CON EL DAO
     */
    private Mascota mascota;
    private boolean banderaSelect = false;

    public MascotaBean() {
        this.mascota = new Mascota();

    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public String guardarMascota() {
        try {

            MascotaDao mascotaDao = new MascotaDao();
            boolean respuesta = mascotaDao.guardarMascota(mascota);
            if (respuesta) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se regidtro correctamente"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se puedo registrar"));
            }
        } catch (HibernateException e) {
            ///transation.rollback();  -- regresa a la anterior
            System.out.println("Error::: " + e);
        }
        return "/Mascota";
    }

    public String actualizarMascota() {
        try {
            MascotaDao mascotaDao = new MascotaDao();
            boolean respuesta = mascotaDao.actualizarMascota(mascota);
            if (respuesta) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se actualizo correctamente"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se puedo actualizar"));
            }
        } catch (HibernateException e) {
            ///transation.rollback();  -- regresa a la anterior
            System.out.println("Error::: " + e);
        }
        return "/Mascota";

    }

    public ArrayList<Mascota> listarMascotas() {
        ArrayList<Mascota> lista = new ArrayList<>();
        MascotaDao mascotaDao = new MascotaDao();
        lista = mascotaDao.listarMascotas();
        return lista;
    }

    public String eliminar() {
        MascotaDao mascotaDao = new MascotaDao();
        boolean respuesta = mascotaDao.eliminarMascota(mascota);
        if (respuesta) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se elimino correctamente"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se puedo eliminar"));
        }
        return "/Mascota";
    }

    public String limpiar() {
        return "/Mascota";
    }

    public void selectBandera() {
        banderaSelect = true;
    }

    public boolean isBanderaSelect() {
        return banderaSelect;
    }

    public void setBanderaSelect(boolean banderaSelect) {
        this.banderaSelect = banderaSelect;
    }

}
