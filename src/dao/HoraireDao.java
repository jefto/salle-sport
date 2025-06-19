/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entite.Horaire;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import util.Connexion;

/**
 *
 * @author DevJude
 */
public class HoraireDao {
    
    public void ajouter(Horaire horaire){
        Connection session = Connexion.getSessionV2();
        String sql = "INSERT INTO Horaire(debut,fin) VALUES ('"
                + horaire.getDebut()+"','"
                + horaire.getFin() +"' );";       
        try{
            Statement statement = session.createStatement();
        }catch(SQLException e){
            System.out.println("Erreur : "+ e.getMessage());
        }
    }
    
    public void modifier(Horaire horaire){
        Connection session = Connexion.getSessionV2();
        String sql = "UPDATE Horaire SET debut = '" +horaire.getDebut()+"' , fin = '" + horaire.getFin() + "'"
                +"WHERE id_horaire = " + horaire.getId() ; 
        
        try{
            Statement statement = session.createStatement();
            statement.execute(sql);
        }catch(SQLException e){
            System.out.println("Erreur : "+ e.getMessage());
        }
    }
    
    public void supprimer(int id_horaire){
        Connection session = Connexion.getSessionV2();
        String sql = "DELETE FROM Horaire WHERE id_horaire = " + id_horaire ; 
        
        try{
            Statement statement = session.createStatement();
            statement.executeUpdate(sql);
        }catch(SQLException e){
            System.out.println("Erreur : "+ e.getMessage());
        }
    }
    
    public Horaire trouver(int id_horaire){
        Horaire horaire = new Horaire();
        Connection session = Connexion.getSessionV2();
        String sql = " SELECT id_horaire , dateDebut ,dateFin FROM Horaire WHERE id_horaire = " + id_horaire+" ; " ; 
        
        try{
            Statement statement = session.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
               horaire.setId(resultSet.getInt(1));
               horaire.setDebut(resultSet.getTimestamp(2).toLocalDateTime()); 
               horaire.setDebut(resultSet.getTimestamp(3).toLocalDateTime());
            }
            
        }catch(SQLException e){
            System.out.println("Erreur : "+ e.getMessage());
        }
        return horaire;
    }
    
    public List<Horaire> listerTout(){
        List<Horaire> horaires = new ArrayList<>();
        Connection session = Connexion.getSessionV2();
        String sql = " SELECT ALL FROM Horaire WHERE "; 
        
        try{
            Statement statement = session.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
              Horaire horaire = new Horaire(
              resultSet.getInt(1),
              resultSet.getTimestamp(2).toLocalDateTime(),   
              resultSet.getTimestamp(3).toLocalDateTime()        
              );
              
              horaires.add(horaire);
            }
            
        }catch(SQLException e){
            System.out.println("Erreur : "+ e.getMessage());
        }
        return horaires;
    }
}
