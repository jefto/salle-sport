/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui_admin.view.seance;

import entite.Membre;
import entite.Salle;
import entite.Seance;
import gui_util.GenericEdit;
import java.awt.BorderLayout;
import java.time.LocalDateTime;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import service.MembreService;
import service.SalleService;

/**
 *
 * @author TCHAMIE
 */
public class Edit extends GenericEdit{
    private Seance entite = new Seance(); // ou Seance ou autre classe
    private MembreService clientService = new MembreService();
    private SalleService salleService = new SalleService();

    private JTextField id = new JTextField();
    private JTextField dateDebut = new JTextField();
    private JTextField dateFin = new JTextField();

    private JComboBox<Membre> membre = new JComboBox<>();
    private JComboBox<Salle> salle = new JComboBox<>();

    
    public Edit(){
        super();
        JLabel idLabel    = new JLabel("Id : ");
        JLabel dateDebutLabel = new JLabel("Date Debut : ");
        JLabel dateFinLabel = new JLabel ("Date Fin : ");
        JLabel salleLabel = new JLabel("Salle : ");
        JLabel membreLabel = new JLabel("Membre : ");
        
        
        
        id.setEditable(false); 
        membre.setEditable(false); 
        salle.setEditable(false); 
        
        for (Membre client : clientService.listerTous()) {
            membre.addItem(client);
        }
        for (Salle salles : salleService.listerTous()) {
            salle.addItem(salles);
        }
        
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
        
        this.form.add(salleLabel);    
        this.form.add(salle);
        
        this.form.add(membreLabel);   
        this.form.add(membre);
        
        
    }
    public Edit(Seance entite){
      this();
      this.entite = entite;
      this.initForm(entite);
    }
    
    
    public void init(){
        this.entite.setId(Integer.parseInt(id.getText()));
        this.entite.setDateDebut(LocalDateTime.parse(dateDebut.getText()));
        this.entite.setDateFin(LocalDateTime.parse(dateFin.getText()));
        entite.setSalle((Salle) salle.getSelectedItem());
        entite.setMembre((Membre) membre.getSelectedItem());
    }

    public Seance getEntite() {
        return entite;
    }
     private void initForm(Seance entite){
        id.setText(String.valueOf(entite.getId()));
        dateDebut.setText(String.valueOf(entite.getDateDebut()));
        dateFin.setText(String.valueOf(entite.getDateFin()));
        
        salle.setSelectedItem(entite.getSalle());
        membre.setSelectedItem(entite.getMembre());
    }
}
