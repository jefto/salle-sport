/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author yaod
 */
public class Connexion {
    
    private Connection session;
    private static Connexion instance;
    
    static {
        instance = new Connexion();
    }
    
    private Connexion(){
        
        String url = "jdbc:postgresql://localhost:5432/gym";
        String login =  "postgres";
        String motDePasse = "Jefto";
        
        
        try{
            
            Class.forName("org.postgresql.Driver");
            session = DriverManager.getConnection(url, login, motDePasse);
            
        }catch(Exception e){
            System.out.println("Erreur :" + e.getMessage());
        }
        
    }
    
    public static Connexion getInstance(){
        return instance;
    }

    public Connection getSession() {
        return session;
    }

    public static Connection getSessionV2() {
        return instance.session;
    }

}
