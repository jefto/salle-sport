/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entite.Paiement;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.Connexion;

/**
 * 
 * @author DevJude
 */
public class PaiementDao {

    private MoyenDePaiementDao moyenDePaiementDao = new MoyenDePaiementDao();

    public void ajouter(Paiement paiement) {
        Connection session = Connexion.getSessionV2();
        String sql = "INSERT INTO Paiement (dateDePaiement, codeMoyenPaiement, montant) " +
                "VALUES ('" + paiement.getDateDePaiement() +
                "','" + paiement.getMoyenDePaiement().getCode() +
                "'," + paiement.getMontant() + ");"  ;

        try {
            Statement statement = session.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public Paiement trouver(int id) {
        Paiement paiement = new Paiement();
        Connection session = Connexion.getSessionV2();
        String sql = "SELECT id_paiement, dateDePaiement, codeMoyenPaiement, montant " +
                     "FROM Paiement WHERE id_paiement = " + id + ";";

        try {
            Statement statement = session.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                paiement.setId(resultSet.getInt(1));
                paiement.setDateDePaiement(resultSet.getTimestamp(2).toLocalDateTime());
                paiement.setMoyenDePaiement(moyenDePaiementDao.trouver(resultSet.getString(3)));
                paiement.setMontant(resultSet.getInt(4));
            }
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
        return paiement;
    }

    public void modifier( Paiement paiement) {
        Connection session = Connexion.getSessionV2();
        String sql = "UPDATE Paiement SET " +
                "dateDePaiement = '" + paiement.getDateDePaiement() + "', " +
                "montant = " + paiement.getMontant() + ", " +
                "codeMoyenPaiement = '" + paiement.getMoyenDePaiement().getCode() + "' " +
                "WHERE id_paiement = " + paiement.getId() + ";";

        try {
            Statement statement = session.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public void suprimer(int id_paiement) {
        Connection session = Connexion.getSessionV2();
        String sql = "DELETE FROM Paiement WHERE id_paiement = " + id_paiement + ";";
        try {
            Statement statement = session.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public List<Paiement> listerTout() {
        List<Paiement> listes = new ArrayList<>();
        Connection session = Connexion.getSessionV2();
        String sql = "SELECT id_paiement, dateDePaiement, codeMoyenPaiement, montant FROM Paiement;";

        try {
            Statement statement = session.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Paiement paiement = new Paiement();
                paiement.setId(resultSet.getInt(1));
                paiement.setDateDePaiement(resultSet.getTimestamp(2).toLocalDateTime());
                paiement.setMoyenDePaiement(moyenDePaiementDao.trouver(resultSet.getString(3)));
                paiement.setMontant(resultSet.getInt(4));

                listes.add(paiement);
            }

        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
        return listes;
    }
}
