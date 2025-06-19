/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui_admin.view.paiement;

import entite.Client;
import entite.MoyenDePaiement;
import entite.Paiement;
import gui_util.GenericEdit;
import java.awt.BorderLayout;
import java.time.LocalDateTime;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import service.ClientService;
import service.MoyenDePaiementService;

/**
 *
 * @author TCHAMIE
 */
public class Edit extends GenericEdit{
    private Paiement entite = new Paiement(); // ou Seance ou autre classe
    private MoyenDePaiementService moyenDePaiementService = new MoyenDePaiementService();
    
    private JTextField id = new JTextField();
    private JTextField datePaiement = new JTextField();
     private JTextField montant = new JTextField();

    private JComboBox<MoyenDePaiement> moyenPaiement = new JComboBox<>();

    
    public Edit(){
        super();
        JLabel idLabel    = new JLabel("Id : ");
        JLabel datePaiementLabel = new JLabel("Date Traitement : ");
        JLabel montantLabel = new JLabel("Montant : ");
        JLabel moyenPaiementLabel = new JLabel("Moyen de Paiement : ");
        
        id.setEditable(false); 
        moyenPaiement.setEditable(false); 
        
        for (MoyenDePaiement moyenPaiements : moyenDePaiementService.listerTous()) {
            moyenPaiement.addItem(moyenPaiements);
        }
        
        id.setText(String.valueOf(entite.getId()));
        datePaiement.setText(String.valueOf(entite.getDateDePaiement()));
        montant.setText(String.valueOf(entite.getMontant()));
       
        this.add(form, BorderLayout.CENTER);
        this.add(buttonPanel,BorderLayout.SOUTH );
        this.form.add(idLabel);
        this.form.add(id);
        
        this.form.add(datePaiementLabel);
        this.form.add(datePaiement);
        
        this.form.add(moyenPaiementLabel);
        this.form.add(moyenPaiement);
        
        this.form.add(montantLabel);   
        this.form.add(montant);
        
    }
    public Edit(Paiement entite){
      this();
      this.entite = entite;
      this.initForm(entite);
    }
    
    
    public void init(){
        this.entite.setId(Integer.parseInt(id.getText()));
        this.entite.setDateDePaiement(LocalDateTime.parse(datePaiement.getText()));
        entite.setMoyenDePaiement((MoyenDePaiement) moyenPaiement.getSelectedItem());
        this.entite.setMontant(Integer.parseInt(montant.getText()));
    }

    public Paiement getEntite() {
        return entite;
    }
     private void initForm(Paiement entite){
        id.setText(String.valueOf(entite.getId()));
        datePaiement.setText(String.valueOf(entite.getDateDePaiement()));
        moyenPaiement.setSelectedItem(entite.getMoyenDePaiement());
        montant.setText(String.valueOf(entite.getMontant()));
        
    }
}
