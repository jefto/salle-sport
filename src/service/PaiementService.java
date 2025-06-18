/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.PaiementDao;
import entite.Paiement;
import entite.Salle;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author DevJude
 */
public class PaiementService {
    
    private PaiementDao dao = new PaiementDao();
    
    public void ajouter(Paiement paiement) {
       dao.ajouter(paiement);
    }

    public Paiement trouver(int id) {
       return dao.trouver(id);
    }

    public void modifier(Paiement paiement) {
       dao.modifier( paiement);
    }

    public void suprimer(int id_paiement) {
       dao.suprimer(id_paiement);
    }

    public List<Paiement> listerTout() {
        return dao.listerTout();
    }
}
