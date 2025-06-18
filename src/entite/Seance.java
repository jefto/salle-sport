/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entite;

import java.time.LocalDateTime;

/**
 *
 * @author yaod
 */
public class Seance {
    
    private int id;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private Membre membre;
    private Salle salle;
    
    public Seance(){
        
    }

    public Seance(int id, LocalDateTime dateDebut, LocalDateTime dateFin, Membre membre, Salle salle) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.membre = membre;
        this.salle = salle;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }
    
}
