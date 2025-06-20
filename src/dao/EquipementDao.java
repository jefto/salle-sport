/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entite.Equipement;
//import entite.TypeAbonnement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.Connexion;

/**
 *
 * @author DevJude
 */


public class EquipementDao {
    private SalleDao salleDao = new SalleDao();
    
    public void ajouter(Equipement equipement){
        Connection session = Connexion.getSessionV2();
        String sql = "INSERT INTO Equipement(id_equipement , libelle , description , id_salle)"
                +"VALUES ( "+equipement.getId() +","
                +"'"+equipement.getLibelle()+"',"
                +"'"+equipement.getDescription()+"',"
                +equipement.getSalle().getId()+" );" ;
        
        try{
            Statement statement = session.createStatement();
            statement.executeUpdate(sql);
        }catch(SQLException e ){
            System.out.println("Erreur : "+ e.getMessage());
        }
    }
    
    public Equipement trouver(int id_equipement){      
        Connection session = Connexion.getSessionV2();
        Equipement equipement = new Equipement();
        String sql = " SELECT ALL FROM Equipement"
                + "WHERE id_equipement = " + id_equipement;
        try{
            Statement statement = session.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){                
               equipement.setId(resultSet.getInt(1)); 
               equipement.setLibelle(resultSet.getString(2)); 
               equipement.setDescription(resultSet.getString(3)); 
               equipement.setSalle(salleDao.trouver(resultSet.getInt(4)));
                
            }
            
        }catch(SQLException e){
            System.out.println("Erreur : " + e.getMessage());
        }
        return equipement;
    }
    
    public void modifier(Equipement equipement){
        Connection session = Connexion.getSessionV2();
        String sql = "UPDATE  Equipement"
                +"SET libelle = '" + equipement.getLibelle() + "'"
                +"SET description = " + equipement.getDescription() + "'"
                +"WHERE id_equipement = " + equipement.getId();
        
        try{
            Statement statement = session.createStatement();
            statement.executeUpdate(sql);
        }catch(SQLException e){
            System.out.println("Erreur :" + e.getMessage());
        }
        
    }
    public List<Equipement> listerTous() {
        List<Equipement> equipements = new ArrayList<>();
        Connection session = Connexion.getSessionV2();
        String sql ="SELECT * FROM  Equipement";        
        try{
            Statement statement = session.createStatement();
           ResultSet  resultSet =  statement.executeQuery(sql);
           while(resultSet.next()){
                Equipement equipement = new Equipement();
                equipement.setId(resultSet.getInt(1)); 
                equipement.setLibelle(resultSet.getString(2)); 
                equipement.setDescription(resultSet.getString(3)); 
                equipement.setSalle(salleDao.trouver(resultSet.getInt(4)));
                
                equipements.add(equipement);
           }
            
        }catch(SQLException e){
            System.out.println("Erreur " + e.getMessage());
        }
        
        return equipements;
    }
    
       public void supprimer(Equipement equipement) {
        Connection session = Connexion.getSessionV2();
        String sql ="DELETE FROM Equipement WHERE id_equipement = " + equipement.getId()+ ";" ;
        
        try{
            Statement statement = session.createStatement();
            statement.executeUpdate(sql);
        }catch(SQLException e){
            System.out.println("Erreur " + e.getMessage());
        }
    }
    
}