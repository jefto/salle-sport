/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entite.Ticket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.Connexion;

public class TicketDao {

    private ClientDao clientDao = new ClientDao();

    public void ajouter(Ticket ticket) {
        Connection session = Connexion.getSessionV2();
        String sql = "INSERT INTO Ticket (nombre_seance, montant, id_client) VALUES (" +
                ticket.getNombreDeSeance() + ", " +
                ticket.getMontant() + ", " +
                ticket.getClient().getId() + ");";

        try {
            Statement statement = session.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public Ticket trouver(int id) {
        Ticket ticket = new Ticket();
        Connection session = Connexion.getSessionV2();
        String sql = "SELECT id, nombre_seance, montant, id_client FROM Ticket WHERE id = " + id + ";";

        try {
            Statement statement = session.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                ticket.setId(rs.getInt(1));
                ticket.setNombreDeSeance(rs.getInt(2));
                ticket.setMontant(rs.getInt(3));
                ticket.setClient(clientDao.trouver(rs.getInt(4)));
            }
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }

        return ticket;
    }

    public void modifier(Ticket ticket) {
        Connection session = Connexion.getSessionV2();
        String sql = "UPDATE Ticket SET " +
                "nombre_seance = " + ticket.getNombreDeSeance() + ", " +
                "montant = " + ticket.getMontant() + ", " +
                "id_client = " + ticket.getClient().getId() + " " +
                "WHERE id = " + ticket.getId() + ";";

        try {
            Statement statement = session.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public void supprimer(int id) {
        Connection session = Connexion.getSessionV2();
        String sql = "DELETE FROM Ticket WHERE id = " + id + ";";

        try {
            Statement statement = session.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public List<Ticket> listerTout() {
        List<Ticket> liste = new ArrayList<>();
        Connection session = Connexion.getSessionV2();
        String sql = "SELECT id, nombre_seance, montant, id_client FROM Ticket;";

        try {
            Statement statement = session.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(rs.getInt(1));
                ticket.setNombreDeSeance(rs.getInt(2));
                ticket.setMontant(rs.getInt(3));
                ticket.setClient(clientDao.trouver(rs.getInt(4)));

                liste.add(ticket);
            }
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }

        return liste;
    }
}

