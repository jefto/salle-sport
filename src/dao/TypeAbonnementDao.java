/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entite.TypeAbonnement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import util.Connexion;

/**
 *
 * @author yaod
 */
public class TypeAbonnementDao {

    public void ajouter(TypeAbonnement typeAbonnement) {
        //Connection session =  Connexion.getInstance().getSession();
        Connection session = Connexion.getSessionV2();
        String sql = "INSERT INTO types_abonnement(code,  libelle, montant) "
                + "VALUES("
                + "'" + typeAbonnement.getCode() + "',"
                + "'" + typeAbonnement.getLibelle() +" ',"
                +  typeAbonnement.getMontant() 
                + ");";
        try {
            Statement statement = session.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }

    }

    public void modifier(TypeAbonnement typeAbonnement) {     
        Connection session = Connexion.getSessionV2();
        String sql = "UPDATE  types_abonnement SET "
                  +"libelle = '" + typeAbonnement.getLibelle()+"'," +
                   "montant = "+ typeAbonnement.getMontant() +" " +
                   "WHERE code  = '" + typeAbonnement.getCode() + "';" ;
        
        try{
            Statement statement = session.createStatement();
            statement.executeUpdate(sql);
        }catch(SQLException e){
            System.out.println("Erreur " + e.getMessage());
        }
    }      

    public void supprimer(String Code) {
        Connection session = Connexion.getSessionV2();
        String sql ="DELETE  FROM types_abonnement WHERE  code = '" + Code+ "';" ;
        
        try{
            Statement statement = session.createStatement();
            statement.executeUpdate(sql);
        }catch(SQLException e){
            System.out.println("Erreur " + e.getMessage());
        }
    }

    public TypeAbonnement trouver(String code) {
        TypeAbonnement typeAbonnement = new TypeAbonnement();
        Connection session = Connexion.getSessionV2();
        String sql ="SELECT code , libelle, montant  FROM  types_abonnement WHERE code = '" + code + "';" ;
        
        try{
            Statement statement = session.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                typeAbonnement.setCode(resultSet.getString(1));  
                typeAbonnement.setLibelle(resultSet.getString(2)); 
                typeAbonnement.setMontant(resultSet.getInt(3)); 
            }
        }catch(SQLException e){
            System.out.println("Erreur " + e.getMessage());
        }
    return typeAbonnement;
    }

    public List<TypeAbonnement> listerTous() {
        List<TypeAbonnement> listes = new ArrayList<>();
        Connection session = Connexion.getSessionV2();
        String sql ="SELECT code , libelle, montant  FROM types_abonnement";        
        try{
            Statement statement = session.createStatement();
           ResultSet  resultSet =  statement.executeQuery(sql);
           while(resultSet.next()){
                TypeAbonnement TypeAbonnement_trouver = new TypeAbonnement();
                TypeAbonnement_trouver.setCode(resultSet.getString(1));  
                TypeAbonnement_trouver.setLibelle(resultSet.getString(2)); 
                TypeAbonnement_trouver.setMontant(resultSet.getInt(3)); 
                
                listes.add(TypeAbonnement_trouver);
           }
            
        }catch(SQLException e){
            System.out.println("Erreur " + e.getMessage());
        }
        
        return listes;
    }

}
