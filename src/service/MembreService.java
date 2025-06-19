/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author DevJude
 */
import dao.MembreDao;
import entite.Client;
import entite.Membre;
import java.time.LocalDateTime;
import java.util.List;

public class MembreService {

    private MembreDao membreDao = new MembreDao();

    public void ajouter(Membre membre) {
        membreDao.ajouter(membre);
    }

    public Membre trouver(int id) {
        return membreDao.trouver(id);
    }

    public void modifier(Membre membre) {
        membreDao.modifier(membre);
    }

    public void supprimer(int id) {
        membreDao.supprimer(id);
    }

    public List<Membre> listerTous() {
        return membreDao.listerTout();
    }
}

