/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.SalleDao;
import entite.Salle;
import java.util.List;

/**
 *
 * @author DevJude
 */
public class SalleService {
    private SalleDao dao = new SalleDao();
    
     public void ajouter(Salle salle){
        dao.ajouter(salle);
    }
    
    public void suprimer(int id_salle){
      dao.suprimer(id_salle);
    }
    
    public void modifier(int id_salle, String libelle, String description ){
       dao.modifier(id_salle, libelle, description);
    }
    
    public Salle trouver(int id_salle){
       return  dao.trouver(id_salle);
    }
    
    public List<Salle> ListerTout(){
       return dao.ListerTout();
}

}
