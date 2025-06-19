/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author DevJude
 */
import dao.AbonnementDao;
import entite.Abonnement;
import entite.Membre;
import entite.Paiement;
import entite.TypeAbonnement;
import java.time.LocalDateTime;
import java.util.List;

public class AbonnementService {

    private AbonnementDao abonnementDao = new AbonnementDao();

    public void ajouter(Abonnement abonnement) {
        abonnementDao.ajouter(abonnement);
    }

    public Abonnement rechercher(int id) {
        return abonnementDao.trouver(id);
    }

    public void modifier(Abonnement abonnement) {
        abonnementDao.modifier(abonnement);
    }

    public void supprimer(int id) {
        abonnementDao.supprimer(id);
    }

    public List<Abonnement> lister() {
        return abonnementDao.listerTout();
    }
}

