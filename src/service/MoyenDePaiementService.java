/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.MoyenDePaiementDao;
import entite.MoyenDePaiement;
import java.util.List;


/**
 *
 * @author DevJude
 */
public class MoyenDePaiementService {
    
    private MoyenDePaiementDao dao = new MoyenDePaiementDao();
       
    public void ajouter(MoyenDePaiement moyenDePaiement) {
        dao.ajouter(moyenDePaiement);
    }

    public void modifier(MoyenDePaiement moyenDePaiement) {
       dao.modifier(moyenDePaiement);
    }
      
    public void supprimer(String code) {
        dao.supprimer(code);
    }

    public MoyenDePaiement trouver(String code) {
       return dao.trouver(code);
    }

    public List<MoyenDePaiement> listerTous() {
       return dao.listerTous();
    }
}
