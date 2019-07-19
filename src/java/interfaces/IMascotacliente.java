/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entidades.Mascotaporcliente;
import java.util.ArrayList;

/**
 *
 * @author Julio Cesar
 */
public interface IMascotacliente {
     public abstract boolean guardarMascotaporcliente(Mascotaporcliente mascotaporcliente);

    public abstract ArrayList<Mascotaporcliente> listarMascotaporclientes();

    public abstract boolean actualizarMascotaporcliente(Mascotaporcliente mascotaporcliente);

    public abstract boolean eliminarMascotaporcliente(Mascotaporcliente mascotaporcliente);
}
