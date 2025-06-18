/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author DevJude
 */
import dao.SeanceDao;
import entite.Seance;
import java.util.List;

public class SeanceService {

    private SeanceDao seanceDao = new SeanceDao();

    public void ajouter(Seance seance) {
        seanceDao.ajouter(seance);
    }

    public Seance rechercher(int id) {
        return seanceDao.trouver(id);
    }

    public void modifier(Seance seance) {
        seanceDao.modifier(seance);
    }

    public void supprimer(int id) {
        seanceDao.supprimer(id);
    }

    public List<Seance> lister() {
        return seanceDao.listerTout();
    }
}

