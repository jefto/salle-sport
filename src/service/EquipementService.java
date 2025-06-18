/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.EquipementDao;
import entite.Equipement;
import java.util.List;


/**
 *
 * @author DevJude
 */
public class EquipementService {
    private EquipementDao dao = new EquipementDao();
    
     public void ajouter(Equipement equipement){
        dao.ajouter(equipement);
    }
    
    public Equipement trouver(int id_equipement){      
       return dao.trouver(id_equipement);
    }
    
    public void modifier(int id_equipement, String libelle,String description){
        dao.modifier(id_equipement, libelle, description);
    }
    
    public void supprimer(Equipement equipement) {
       dao.supprimer(equipement);   
  }
    
    public List<Equipement> ListerTout(){
        return dao.listerTous();
    }

}
