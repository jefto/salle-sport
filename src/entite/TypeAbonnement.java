/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entite;

import java.util.List;

/**
 *
 * @author yaod
 */
public class TypeAbonnement {

    private String code;
    private String libelle;
    private int montant;
    private List<Abonnement> abonnements;
    
    public TypeAbonnement(){
        
    }
    
    public TypeAbonnement(String code, String libelle, int montant){
        this.code = code;
        this.libelle = libelle;
        this.montant = montant;
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public List<Abonnement> getAbonnements() {
        return abonnements;
    }

    public void setAbonnements(List<Abonnement> abonnements) {
        this.abonnements = abonnements;
    }
    
    
}
