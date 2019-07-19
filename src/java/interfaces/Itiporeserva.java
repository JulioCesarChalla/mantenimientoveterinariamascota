/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entidades.Tiporeserva;
import java.util.ArrayList;

/**
 *
 * @author Julio Cesar
 */
public interface Itiporeserva {

    public abstract boolean guardarTiporeserva(Tiporeserva tiporeserva);

    public abstract ArrayList<Tiporeserva> listarTiporeservas();

    public abstract boolean actualizarTiporeserva(Tiporeserva tiporeserva);

    public abstract boolean eliminarTiporeserva(Tiporeserva tiporeserva);
}
