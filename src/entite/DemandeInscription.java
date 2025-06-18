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
public class DemandeInscription {
       
    private int id;
    private LocalDateTime dateDeDemande; 
    private LocalDateTime dateDeTraitement;
    private Client client;

    public DemandeInscription(int id, LocalDateTime dateDeDemande, LocalDateTime dateDeTraitement, Client client) {
        this.id = id;
        this.dateDeDemande = dateDeDemande;
        this.dateDeTraitement = dateDeTraitement;
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    

      public DemandeInscription() {
       
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDateDeDemande() {
        return dateDeDemande;
    }

    public void setDateDeDemande(LocalDateTime dateDeDemande) {
        this.dateDeDemande = dateDeDemande;
    }

    public LocalDateTime getDateDeTraitement() {
        return dateDeTraitement;
    }

    public void setDateDeTraitement(LocalDateTime dateDeTraitement) {
        this.dateDeTraitement = dateDeTraitement;
    }

       public String getDateDeTraitementSQL() {
        if (this.dateDeTraitement != null) {
            return "'" + this.dateDeTraitement + "'";
        } else {
            return null;
        }
    }
}
