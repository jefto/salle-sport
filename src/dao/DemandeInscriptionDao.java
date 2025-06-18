/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author DevJude
 */
import entite.DemandeInscription;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import util.Connexion;

public class DemandeInscriptionDao {
    private ClientDao clientdao = new ClientDao();
    
    public void ajouter(DemandeInscription demande) {
        Connection session = Connexion.getSessionV2();
        String sql = "INSERT INTO DemandeInscription (dateDeDemande, dateDeTraitement) VALUES (" +
                "'" + demande.getDateDeDemande() + "', " +
                "'" + demande.getDateDeTraitement() +" ');";

        try {
            Statement statement = session.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public DemandeInscription trouver(int id) {
        DemandeInscription demande = new DemandeInscription();
        Connection session = Connexion.getSessionV2();
        String sql = "SELECT id, dateDeDemande, dateDeTraitement, id_client FROM DemandeInscription WHERE id = " + id + ";";

        try {
            Statement statement = session.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                demande.setId(rs.getInt(1));
                demande.setDateDeDemande(rs.getTimestamp(2).toLocalDateTime());
                demande.setDateDeTraitement(rs.getTimestamp(3).toLocalDateTime());
                demande.setClient(clientdao.trouver(rs.getInt(4)));
            }
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }

        return demande;
    }

    public void modifier(DemandeInscription demande) {
        Connection session = Connexion.getSessionV2();
        String sql = "UPDATE DemandeInscription SET " +
                "dateDeDemande = '" + demande.getDateDeDemande() + "', " +
                "dateDeTraitement = '" + demande.getDateDeTraitement() + "', " +
                "id_client = " + demande.getClient().getId() + " " +
                "WHERE id = " + demande.getId() + ";";

        try {
            Statement statement = session.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public void supprimer(int id) {
        Connection session = Connexion.getSessionV2();
        String sql = "DELETE FROM DemandeInscription WHERE id = " + id + ";";

        try {
            Statement statement = session.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public List<DemandeInscription> listerTout() {
        List<DemandeInscription> liste = new ArrayList<>();
        Connection session = Connexion.getSessionV2();
        String sql = "SELECT id, dateDeDemande, dateDeTraitement, id_client FROM DemandeInscription;";

        try {
            Statement statement = session.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                DemandeInscription demande = new DemandeInscription();
                demande.setId(rs.getInt(1));
                demande.setDateDeDemande(rs.getTimestamp(2).toLocalDateTime());
                demande.setDateDeTraitement(rs.getTimestamp(3).toLocalDateTime());
                demande.setClient(clientdao.trouver(rs.getInt(4)));
                liste.add(demande);
            }
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }

        return liste;
    }
}

