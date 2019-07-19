/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import dao.TipoatencionDao;
import entidades.Tipoatencion;
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
public class TipoatencionBean implements Serializable{
    Tipoatencion tipoatencion;
        private boolean banderaSelect = false;
        
    public TipoatencionBean(){
        this.tipoatencion= new Tipoatencion();
    }
    
       public String guardarTipoatencion() {
        try {

            TipoatencionDao tipoatencionDao = new TipoatencionDao();
            boolean respuesta = tipoatencionDao.guardarTipoatencion(tipoatencion);
            if (respuesta) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se regidtro correctamente"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se puedo registrar"));
            }
        } catch (HibernateException e) {
            ///transation.rollback();  -- regresa a la anterior
            System.out.println("Error::: " + e);
        }
        return "/Tipoatencion";
    }
    public String actualizarTipoatencion() {
        try {
           TipoatencionDao tipoatencionDao = new TipoatencionDao();
            boolean respuesta = tipoatencionDao.actualizarTipoatencion(tipoatencion);
            if (respuesta) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se actualizo correctamente"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se puedo actualizar"));
            }
        } catch (HibernateException e) {
            ///transation.rollback();  -- regresa a la anterior
            System.out.println("Error::: " + e);
        }
        return "/Tipoatencion";

    }
    
    public ArrayList<Tipoatencion> listarTipoatencion() {
        ArrayList<Tipoatencion> lista = new ArrayList<>();
          TipoatencionDao tipoatencionDao = new TipoatencionDao();
        lista = tipoatencionDao.listarTipoatencions();
        return lista;
    }

    public String eliminar() {
     TipoatencionDao tipoatencionDao = new TipoatencionDao();
            boolean respuesta = tipoatencionDao.eliminarTipoatencion(tipoatencion);
        if (respuesta) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se elimino correctamente"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se puedo eliminar"));
        }
        return "/Tipoatencion";
    }

    public String limpiar() {
        return "/Tipoatencion";
    }

    public void selectBandera() {
        banderaSelect = true;
    }

    
    public Tipoatencion getTipoatencion() {
        return tipoatencion;
    }

    public void setTipoatencion(Tipoatencion tipoatencion) {
        this.tipoatencion = tipoatencion;
    }

    public boolean isBanderaSelect() {
        return banderaSelect;
    }

    public void setBanderaSelect(boolean banderaSelect) {
        this.banderaSelect = banderaSelect;
    }
        
}
