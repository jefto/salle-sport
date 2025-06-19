/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui_admin.view.abonnement;

import entite.Abonnement;
import entite.Membre;
import entite.Paiement;
import entite.Salle;
import entite.Seance;
import entite.TypeAbonnement;
import gui_util.GenericEdit;
import java.awt.BorderLayout;
import java.time.LocalDateTime;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author DevJude
 */
public class Edit extends GenericEdit{
     private Abonnement entite = new Abonnement(); // ou Seance ou autre classe

    private JTextField id = new JTextField();
    private JTextField dateDebut = new JTextField();
    private JTextField dateFin = new JTextField();

    private JComboBox<TypeAbonnement> type_abonnement = new JComboBox<>();
    private JComboBox<Membre> membre = new JComboBox<>();
    private JComboBox<Paiement> paiement = new JComboBox<>();

    
    public Edit(){
        super();
        JLabel idLabel    = new JLabel("Id : ");
        JLabel dateDebutLabel = new JLabel("Date Debut : ");
        JLabel dateFinLabel = new JLabel ("Date Fin : ");
        JLabel typeAbonnementLabel = new JLabel("Id Type d'Abonnement : ");
        JLabel membreLabel = new JLabel("Id Membre : ");
        JLabel paiementLabel = new JLabel("Id Paiement : ");
        
        id.setEditable(false); 
        membre.setEditable(false); 
        type_abonnement.setEditable(false); 
        paiement.setEditable(false); 
        
        
        id.setText(String.valueOf(entite.getId()));
        dateDebut.setText(String.valueOf(entite.getDateDebut()));
        dateFin.setText(String.valueOf(entite.getDateFin()));
       
        this.add(form, BorderLayout.CENTER);
        this.add(buttonPanel,BorderLayout.SOUTH );
        this.form.add(idLabel);
        this.form.add(id);
        
        this.form.add(dateDebutLabel);
        this.form.add(dateDebut);
        
        this.form.add(dateFinLabel);
        this.form.add(dateFin);
        
        this.form.add(typeAbonnementLabel);    
        this.form.add(type_abonnement);
        
        this.form.add(membreLabel);   
        this.form.add(membre);

        this.form.add(paiementLabel);    
        this.form.add(paiement);
        
    }
    public Edit(Abonnement entite){
      this();
      this.entite = entite;
      this.initForm(entite);
    }
    
    
    public void init(){
        this.entite.setId(Integer.parseInt(id.getText()));
        this.entite.setDateDebut(LocalDateTime.parse(dateDebut.getText()));
        this.entite.setDateFin(LocalDateTime.parse(dateFin.getText()));
        entite.setTypeAbonnement((TypeAbonnement) type_abonnement.getSelectedItem());
        entite.setMembre((Membre) membre.getSelectedItem());
        entite.setPaiement((Paiement) paiement.getSelectedItem());
    }

    public Abonnement getEntite() {
        return entite;
    }
     private void initForm(Abonnement entite){
        id.setText(String.valueOf(entite.getId()));
        dateDebut.setText(String.valueOf(entite.getDateDebut()));
        dateFin.setText(String.valueOf(entite.getDateFin()));
        
        type_abonnement.setSelectedItem(entite.getTypeAbonnement());
        membre.setSelectedItem(entite.getMembre());
        paiement.setSelectedItem(entite.getPaiement());
    }
}
