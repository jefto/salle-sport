/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entite.Abonnement;
import entite.Membre;
import entite.Paiement;
import entite.TypeAbonnement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import util.Connexion;

/**
 *
 * @author DevJude
 */

public class AbonnementDao {

    private TypeAbonnementDao typeAbonnementDao = new TypeAbonnementDao();
    private PaiementDao paiementDao = new PaiementDao();
    private MembreDao membreDao = new MembreDao();

    public void ajouter(Abonnement abonnement) {
        Connection session = Connexion.getSessionV2();
        String sql = "INSERT INTO Abonnement (date_debut, dateFin, id_typeAbonnement, id_paiement, id_membre) VALUES (" +
                "'" + abonnement.getDateDebut() + "', " +
                "'" + abonnement.getDateFin() + "', " +
                abonnement.getTypeAbonnement().getCode()+ ", " +
                abonnement.getPaiement().getId() + ", " +
                abonnement.getMembre().getId() + ");";

        try {
            Statement statement = session.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public Abonnement trouver(int id) {
        Abonnement abonnement = new Abonnement();
        Connection session = Connexion.getSessionV2();
        String sql = "SELECT id, date_debut, dateFin, id_type, id_paiement, id_membre FROM Abonnement WHERE id = " + id + ";";

        try {
            Statement statement = session.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                abonnement.setId(rs.getInt(1));
                abonnement.setDateDebut(rs.getTimestamp(2).toLocalDateTime());
                abonnement.setDateFin(rs.getTimestamp(3).toLocalDateTime());
                abonnement.setTypeAbonnement(typeAbonnementDao.trouver(rs.getString(4)));
                abonnement.setPaiement(paiementDao.trouver(rs.getInt(5)));
                abonnement.setMembre(membreDao.trouver(rs.getInt(6)));
            }
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }

        return abonnement;
    }

    public void modifier(LocalDateTime DateFin ,LocalDateTime DateDebut ,TypeAbonnement typeAbonnement,Membre membre,int id_abonnement,Paiement paiement) {
        Connection session = Connexion.getSessionV2();
        String sql = "UPDATE Abonnement SET " +
                "date_debut = '" + DateDebut + "', " +
                "dateFin = '" + DateFin + "', " +
                "id_type = " + typeAbonnement.getCode() + ", " +
                "id_paiement = " + paiement.getId() + ", " +
                "id_membre = " + membre.getId() + " " +
                "WHERE id = " + id_abonnement + ";";

        try {
            Statement statement = session.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public void supprimer(int id) {
        Connection session = Connexion.getSessionV2();
        String sql = "DELETE FROM Abonnement WHERE id = " + id + ";";

        try {
            Statement statement = session.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public List<Abonnement> listerTout() {
        List<Abonnement> liste = new ArrayList<>();
        Connection session = Connexion.getSessionV2();
        String sql = "SELECT id, date_debut, dateFin, id_type, id_paiement, id_membre FROM Abonnement;";

        try {
            Statement statement = session.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Abonnement abonnement = new Abonnement();
                abonnement.setId(rs.getInt(1));
                abonnement.setDateDebut(rs.getTimestamp(2).toLocalDateTime());
                abonnement.setDateFin(rs.getTimestamp(3).toLocalDateTime());
                abonnement.setTypeAbonnement(typeAbonnementDao.trouver(rs.getString(4)));
                abonnement.setPaiement(paiementDao.trouver(rs.getInt(5)));
                abonnement.setMembre(membreDao.trouver(rs.getInt(6)));
                liste.add(abonnement);
            }
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }

        return liste;
    }
}

