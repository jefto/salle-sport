/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author DevJude
 */
import entite.Membre;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.Connexion;

public class MembreDao {

    private ClientDao clientDao = new ClientDao();

    public void ajouter(Membre membre) {
        Connection session = Connexion.getSessionV2();
        String sql = "INSERT INTO Membre (dateinscription, id_client) VALUES (" +
                "'" + membre.getDateInscription() + "', " +
                membre.getClient().getId() + ");";

        try {
            Statement statement = session.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public Membre trouver(int id) {
        Membre membre = new Membre();
        Connection session = Connexion.getSessionV2();
        String sql = "SELECT id_membre, dateinscription, id_client FROM Membre WHERE id_membre = " + id + ";";

        try {
            Statement statement = session.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                membre.setId(rs.getInt(1));
                membre.setDateInscription(rs.getTimestamp(2).toLocalDateTime());
                membre.setClient(clientDao.trouver(rs.getInt(3)));
                // Les listes de seances et abonnements peuvent être gérées ailleurs si besoin
            }
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }

        return membre;
    }

    public void modifier(Membre membre) {
        Connection session = Connexion.getSessionV2();
        String sql = "UPDATE Membre SET " +
                "dateinscription = '" + membre.getDateInscription() + "', " +
                "id_client = " + membre.getClient().getId() + " " +
                "WHERE id_membre = " + membre.getId() + ";";

        try {
            Statement statement = session.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public void supprimer(int id) {
        Connection session = Connexion.getSessionV2();
        String sql = "DELETE FROM Membre WHERE id_membre = " + id + ";";

        try {
            Statement statement = session.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public List<Membre> listerTout() {
        List<Membre> liste = new ArrayList<>();
        Connection session = Connexion.getSessionV2();
        String sql = "SELECT id_membre, dateinscription, id_client FROM Membre;";

        try {
            Statement statement = session.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Membre membre = new Membre();
                membre.setId(rs.getInt(1));
                membre.setDateInscription(rs.getTimestamp(2).toLocalDateTime());
                membre.setClient(clientDao.trouver(rs.getInt(3)));
                liste.add(membre);
            }
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }

        return liste;
    }
}

