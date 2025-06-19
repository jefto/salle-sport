/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entite.Salle;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import util.Connexion;

/**
 *
 * @author DevJude
 */
public class SalleDao {
    public void ajouter(Salle salle){
        Connection session = Connexion.getSessionV2();
        String sql = "INSERT INTO Salle(libelle, description) " +
             "VALUES ('" + salle.getLibelle() + "', '" + salle.getDescription() + "');";

        
        try{
            Statement statement = session.createStatement();
            statement.execute(sql);
        }catch(SQLException e){
            System.out.println("Erreur: " + e.getMessage());
        }      
    }
    
    public void suprimer(int id_salle){
                Connection session = Connexion.getSessionV2();
        String sql = " DELETE  Salle WHERE id_salle = " + id_salle;
        
        try{
            Statement statement = session.createStatement();
            statement.executeUpdate(sql);
        }catch(SQLException e){
            System.out.println("Erreur: " + e.getMessage());
        } 
    }
    
    public void modifier(Salle salle ){
        Connection session = Connexion.getSessionV2();
        String sql = " UPDATE Salle SET libelle = ' " + salle.getLibelle() + "'," 
                +" description = ' " + salle.getDescription() + "'"
                +"WHERE id_salle = "+salle.getId() + ";";
        
        try{
            Statement statement = session.createStatement();
            statement.executeUpdate(sql);
        }catch(SQLException e){
            System.out.println("Erreur: " + e.getMessage());
        } 
    }
    
    public Salle trouver(int id_salle){
        Salle salle = new Salle();
        Connection session = Connexion.getSessionV2();
        String sql = "SELECT ALL FROM  Salle WHERE id_salle =  "+ id_salle;
        
        try{
            Statement statement = session.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                salle.setId(resultSet.getInt(1));
                salle.setLibelle(resultSet.getString(2));
                salle.setDescription(resultSet.getString(3));            
            }
            
        }catch(SQLException e){
            System.out.println("Erreur: " + e.getMessage());
        } 
        
        return salle;
    }
    public List<Salle> ListerTout(){
            List<Salle> salles = new ArrayList<>();
        Connection session = Connexion.getSessionV2();
        String sql = "SELECT ALL FROM  Salle ";
        
        try{
            Statement statement = session.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
            Salle salle = new Salle(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3));
               
            salles.add(salle);
            }
            
        }catch(SQLException e){
            System.out.println("Erreur: " + e.getMessage());
        } 
        
        return salles;
    }
}
