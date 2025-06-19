/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entite.MoyenDePaiement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.Connexion;

/**
 *
 * @author DevJude
 */
public class MoyenDePaiementDao {
    public void ajouter(MoyenDePaiement moyenDePaiement) {
        //Connection session =  Connexion.getInstance().getSession();
        Connection session = Connexion.getSessionV2();
        String sql = "INSERT INTO moyen_paiements(id_moyenpaiement, code, libelle) "
                + "VALUES("
                + moyenDePaiement.getId() + "," 
                + "'" + moyenDePaiement.getCode() + "',"
                + "'" + moyenDePaiement.getLibelle() +"'"
                + ");";
        try {
            Statement statement = session.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }

    }

    public void modifier(MoyenDePaiement moyenDePaiement) {
        
        Connection session = Connexion.getSessionV2();
        String sql = "UPDATE moyen_paiements"
                +"SET"
                +"id_moyenpaiement = " + moyenDePaiement.getId() + "," 
                +"libelle = " + moyenDePaiement.getLibelle()+"," 
                +"WHERE"+"code = "  + moyenDePaiement.getCode()+ ";" ;
        
        try{
            Statement statement = session.createStatement();
            statement.executeUpdate(sql);
        }catch(SQLException e){
            System.out.println("Erreur " + e.getMessage());
        }
    }
        

    public void supprimer(String code) {
        Connection session = Connexion.getSessionV2();
        String sql ="DELETE FROM moyen_paiements WHERE code = '" + code + "' ;" ;
        
        try{
            Statement statement = session.createStatement();
            statement.executeUpdate(sql);
        }catch(SQLException e){
            System.out.println("Erreur " + e.getMessage());
        }
    }

    public MoyenDePaiement trouver(String code) {
        MoyenDePaiement MoyenDePaiement_trouver = new MoyenDePaiement();
        Connection session = Connexion.getSessionV2();
        String sql = "SELECT id_moyenpaiement, code, libelle FROM moyen_paiements WHERE code = '" + code + "';";
        
        try{
            Statement statement = session.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                MoyenDePaiement_trouver.setCode(resultSet.getString(1));  
                MoyenDePaiement_trouver.setLibelle(resultSet.getString(2)); 
            }
        }catch(SQLException e){
            System.out.println("Erreur " + e.getMessage());
        }
    return MoyenDePaiement_trouver;
    }

    public List<MoyenDePaiement> listerTous() {
        List<MoyenDePaiement> listes = new ArrayList<>();
        Connection session = Connexion.getSessionV2();
        String sql = "SELECT id_moyenpaiement, code, libelle FROM moyen_paiements;";      
        try{
            Statement statement = session.createStatement();
           ResultSet  resultSet =  statement.executeQuery(sql);
           while(resultSet.next()){
                MoyenDePaiement MoyenDePaiement_trouver = new MoyenDePaiement();
                MoyenDePaiement_trouver.setCode(resultSet.getString(1));  
               MoyenDePaiement_trouver.setLibelle(resultSet.getString(2)); 
                
                listes.add(MoyenDePaiement_trouver);
           }
            
        }catch(SQLException e){
            System.out.println("Erreur " + e.getMessage());
        }
        if(listes == null){
            System.out.println(" liste vide");
        }
        return listes;
    }
}
