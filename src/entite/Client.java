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
public class Client {
    
    private int id;
    private String nom;
    private String prenom;
    private LocalDateTime dateNaissance;
    private String email;
    private List<Ticket> ticket;
    
    public Client(){  }

    public Client(int id, String nom, String prenom, LocalDateTime dateNaissance, String email) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.email = email;
    }
    
    @Override
    public String toString() {
        return id + " - " + nom + " " + prenom; // ou ce que tu veux afficher dans la liste
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDateTime getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDateTime dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public List<Ticket> getTicket() {
        return ticket;
    }

    public void setTicket(List<Ticket> ticket) {
        this.ticket = ticket;
    }

 
    
    
}
