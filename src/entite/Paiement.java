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
public class Paiement {
    private int id;
    private LocalDateTime dateDePaiement;
    private int montant;
    private MoyenDePaiement moyenDePaiement;
    
    public Paiement() {
    }

    public Paiement(int id, LocalDateTime dateDePaiement, int montant, MoyenDePaiement moyenDePaiement) {
        this.id = id;
        this.dateDePaiement = dateDePaiement;
        this.montant = montant;
        this.moyenDePaiement = moyenDePaiement;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public LocalDateTime getDateDePaiement() {
        return dateDePaiement;
    }

    public void setDateDePaiement(LocalDateTime dateDePaiement) {
        this.dateDePaiement = dateDePaiement;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public MoyenDePaiement getMoyenDePaiement() {
        return moyenDePaiement;
    }

    public void setMoyenDePaiement(MoyenDePaiement moyenDePaiement) {
        this.moyenDePaiement = moyenDePaiement;
    }

    
    
}
