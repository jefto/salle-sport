/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.TicketDao;
import entite.Ticket;
import java.util.List;

/**
 *
 * @author DevJude
 */
public class TicketService {
    private TicketDao dao= new TicketDao();

    public void ajouter(Ticket ticket) {
        dao.ajouter(ticket);
    }

    public Ticket trouver(int id) {
        return dao.trouver(id);
    }

    public void modifier(Ticket ticket) {
        dao.modifier(ticket);
    }

    public void supprimer(int id) {
        dao.supprimer(id);
    }

    public List<Ticket> listerTout() {
      return dao.listerTout();
    }
}
