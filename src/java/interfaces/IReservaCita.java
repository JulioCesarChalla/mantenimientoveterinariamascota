/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entidades.Reservarcita;
import java.util.ArrayList;

/**
 *
 * @author Julio Cesar
 */
public interface IReservaCita {
      public abstract boolean guardarReservarcita(Reservarcita reservarcita);

    public abstract ArrayList<Reservarcita> listarReservarcitas();

    public abstract boolean actualizarReservarcita(Reservarcita reservarcita);

    public abstract boolean eliminarReservarcita(Reservarcita reservarcita);

}
