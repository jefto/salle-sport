/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entite;

/**
 *
 * @author TCHAMIE
 */
public class Equipement {
    
    private int id;
    private String libelle;
    private String description;
    private Salle salle;
    
    public Equipement(){ }

    public Equipement(int id, String libelle, String description, Salle salle) {
        this.id = id;
        this.libelle = libelle;
        this.description = description;
        this.salle = salle;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }
    
}
