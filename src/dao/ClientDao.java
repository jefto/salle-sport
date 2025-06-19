/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author DevJude
 */
import entite.Client;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.Connexion;

public class ClientDao {


    public void ajouter(Client client) {
        Connection session = Connexion.getSessionV2();
        String sql = "INSERT INTO Client (id_client, nom, prenom, dateNaissance, email) VALUES (" 
                    + client.getId()+
                ",' " + client.getNom() + "', " +
                "' " + client.getPrenom() + "', " +
                "' " + client.getDateNaissance() + "', " +
                "' " + client.getEmail() + " '" +
                ");";

        try {
            Statement statement = session.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public Client trouver(int id) {
        Client client = new Client();
        Connection session = Connexion.getSessionV2();
        String sql = "SELECT id_client, nom, prenom, dateNaissance, email " +
                     "FROM Client WHERE id_client = " + id + ";";

        try {
            Statement statement = session.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                client.setId(rs.getInt(1));
                client.setNom(rs.getString(2));
                client.setPrenom(rs.getString(3));
                client.setDateNaissance(rs.getTimestamp(4).toLocalDateTime());
                client.setEmail(rs.getString(5));
            }
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }

        return client;
    }

    public void modifier(Client client ) {
        Connection session = Connexion.getSessionV2();
        String sql = "UPDATE Client SET " +
                "nom = '" + client.getNom() + "', " +
                "prenom = '" + client.getPrenom() + "', " +
                "dateNaissance = '" + client.getDateNaissance() + "', " +
                "email = '" + client.getEmail()+ "' " +
                "WHERE id_client = " +client.getId() + ";";

        try {
            Statement statement = session.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public void supprimer(Client client) {
        Connection session = Connexion.getSessionV2();
        String sql = "DELETE FROM Client WHERE id_client = " + client.getId() + ";";

        try {
            Statement statement = session.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public List<Client> listerTout() {
        List<Client> liste = new ArrayList<>();
        Connection session = Connexion.getSessionV2();
        String sql = "SELECT id_client, nom, prenom, dateNaissance, email FROM Client;";

        try {
            Statement statement = session.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Client client = new Client();
                client.setId(rs.getInt(1));
                client.setNom(rs.getString(2));
                client.setPrenom(rs.getString(3));
                client.setDateNaissance(rs.getTimestamp(4).toLocalDateTime());
                client.setEmail(rs.getString(5));

                liste.add(client);
            }
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }

        return liste;
    }
}

