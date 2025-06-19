/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.HoraireDao;
import entite.Horaire;
import java.util.List;

/**
 *
 * @author DevJude
 */
public class HoraireService {
    private HoraireDao horaireDao = new HoraireDao();

    public void ajouter(Horaire horaire) {
        horaireDao.ajouter(horaire);
    }

    public Horaire rechercher(int id) {
        return horaireDao.trouver(id);
    }

    public void modifier(Horaire horaire) {
        horaireDao.modifier(horaire);
    }

    public void supprimer(int id) {
        horaireDao.supprimer(id);
    }

    public List<Horaire> lister() {
        return horaireDao.listerTout();
    }
}
