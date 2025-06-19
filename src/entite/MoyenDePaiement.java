/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entite;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yaod
 */
public class MoyenDePaiement {
    
    private int id;
    private String code;
    private String libelle;
    private List<Paiement> paiements = new ArrayList<>();
    
    public MoyenDePaiement(){
        
    }

    public MoyenDePaiement(int id, String code, String libelle) {
        this.id = id;
        this.code = code;
        this.libelle = libelle;
    }
    @Override
    public String toString() {
        return id + " - " + id + " " + libelle; // ou ce que tu veux afficher dans la liste
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    
    public List<Paiement> getPaiements() {
        return paiements;
    }

    public void setPaiements(List<Paiement> paiements) {
        this.paiements = paiements;
    }
    
}
