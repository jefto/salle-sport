/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entite;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author yaod
 */
public class Horaire {
    
    private int id;
    private LocalDateTime debut;
    private LocalDateTime fin;
    private List<Salle> salles;
    
    public Horaire(){ }

    public Horaire(int id, LocalDateTime debut, LocalDateTime fin) {
        this.id = id;
        this.debut = debut;
        this.fin = fin;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDebut() {
        return debut;
    }

    public void setDebut(LocalDateTime debut) {
        this.debut = debut;
    }

    public LocalDateTime getFin() {
        return fin;
    }

    public void setFin(LocalDateTime fin) {
        this.fin = fin;
    }

    public List<Salle> getSalle() {
        return salles;
    }

    public void setSalle(List<Salle> salles) {
        this.salles = salles;
    }
    
}
