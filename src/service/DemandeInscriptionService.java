/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author DevJude
 */
import dao.DemandeInscriptionDao;
import entite.DemandeInscription;
import java.util.List;

public class DemandeInscriptionService {

    private DemandeInscriptionDao dao = new DemandeInscriptionDao();

    public void ajouter(DemandeInscription demande) {
        dao.ajouter(demande);
    }

    public DemandeInscription rechercher(int id) {
        return dao.trouver(id);
    }

    public void modifier(DemandeInscription demande) {
        dao.modifier(demande);
    }

    public void supprimer(int id) {
        dao.supprimer(id);
    }

    public List<DemandeInscription> lister() {
        return dao.listerTout();
    }
}

