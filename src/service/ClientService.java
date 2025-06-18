/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.ClientDao;
import entite.Client;
import java.util.List;

/**
 *
 * @author DevJude
 */
public class ClientService {
    
    private ClientDao dao = new ClientDao();
    public void ajouter(Client client) {
        dao.ajouter(client);
    }

    public void modifier(Client client) {
        dao.modifier(client);
    }
    
    public Client trouver(int id) {
        return dao.trouver(id);
    }

    public void supprimer(Client client) {
      dao.supprimer(client);
    }

    public List<Client> listerTout() {
        return dao.listerTout();
    }
}
