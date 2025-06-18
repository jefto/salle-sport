/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import entite.Seance;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.Connexion;

public class SeanceDao {

    private MembreDao membreDao = new MembreDao();
    private SalleDao salleDao = new SalleDao();

    public void ajouter(Seance seance) {
        Connection session = Connexion.getSessionV2();
        String sql = "INSERT INTO Seance (dateDebut, dateFin, id_membre, id_salle) VALUES (" +
                "'" + seance.getDateDebut() + "', " +
                "'" + seance.getDateFin() + "', " +
                seance.getMembre().getId() + ", " +
                seance.getSalle().getId() + ");";

        try {
            Statement statement = session.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public Seance trouver(int id) {
        Seance seance = new Seance();
        Connection session = Connexion.getSessionV2();
        String sql = "SELECT id, dateDebut, dateFin, id_membre, id_salle FROM Seance WHERE id = " + id + ";";

        try {
            Statement statement = session.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                seance.setId(rs.getInt(1));
                seance.setDateDebut(rs.getTimestamp(2).toLocalDateTime());
                seance.setDateFin(rs.getTimestamp(3).toLocalDateTime());
                seance.setMembre(membreDao.trouver(rs.getInt(4)));
                seance.setSalle(salleDao.trouver(rs.getInt(5)));
            }
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }

        return seance;
    }

    public void modifier(Seance seance) {
        Connection session = Connexion.getSessionV2();
        String sql = "UPDATE Seance SET " +
                "dateDebut = '" + seance.getDateDebut() + "', " +
                "dateFin = '" + seance.getDateFin() + "', " +
                "id_membre = " + seance.getMembre().getId() + ", " +
                "id_salle = " + seance.getSalle().getId() + " " +
                "WHERE id = " + seance.getId() + ";";

        try {
            Statement statement = session.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public void supprimer(int id) {
        Connection session = Connexion.getSessionV2();
        String sql = "DELETE FROM Seance WHERE id = " + id + ";";

        try {
            Statement statement = session.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public List<Seance> listerTout() {
        List<Seance> liste = new ArrayList<>();
        Connection session = Connexion.getSessionV2();
        String sql = "SELECT id, dateDebut, dateFin, id_membre, id_salle FROM Seance;";

        try {
            Statement statement = session.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Seance seance = new Seance();
                seance.setId(rs.getInt(1));
                seance.setDateDebut(rs.getTimestamp(2).toLocalDateTime());
                seance.setDateFin(rs.getTimestamp(3).toLocalDateTime());
                seance.setMembre(membreDao.trouver(rs.getInt(4)));
                seance.setSalle(salleDao.trouver(rs.getInt(5)));

                liste.add(seance);
            }
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }

        return liste;
    }
}
