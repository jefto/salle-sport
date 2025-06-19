/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui_admin.view.demandeInscription;

import entite.DemandeInscription;
import gui_util.GenericEdit;
import java.awt.BorderLayout;
import java.time.LocalDateTime;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author DevJude
 */
public class Edit extends GenericEdit{
    private DemandeInscription entite = new DemandeInscription(); // ou Seance ou autre classe

    private JTextField id = new JTextField();
    private JTextField dateDemande = new JTextField();
    private JTextField dateTraitement = new JTextField();

    
    public Edit(){
        super();
        JLabel idLabel    = new JLabel("Id : ");
        JLabel dateDemandeLabel = new JLabel("Date de Demande : ");
        JLabel dateTraitementLabel = new JLabel ("Date de Traitment : ");
        
        id.setEditable(false); 
        
        id.setText(String.valueOf(entite.getId()));
        dateDemande.setText(String.valueOf(entite.getDateDeDemande()));
        dateTraitement.setText(String.valueOf(entite.getDateDeTraitement()));
       
        this.add(form, BorderLayout.CENTER);
        this.add(buttonPanel,BorderLayout.SOUTH );
        this.form.add(idLabel);
        this.form.add(id);
        
        this.form.add(dateDemandeLabel);
        this.form.add(dateDemande);
        
        this.form.add(dateTraitementLabel);
        this.form.add(dateTraitement);
        
    }
    public Edit(DemandeInscription entite){
      this();
      this.entite = entite;
      this.initForm(entite);
    }
    
    
    public void init(){
        this.entite.setId(Integer.parseInt(id.getText()));
        this.entite.setDateDeDemande(LocalDateTime.parse(dateDemande.getText()));
        this.entite.setDateDeTraitement(LocalDateTime.parse(dateTraitement.getText()));
    }

    public DemandeInscription getEntite() {
        return entite;
    }
     private void initForm(DemandeInscription entite){
        id.setText(String.valueOf(entite.getId()));
        dateDemande.setText(String.valueOf(entite.getDateDeDemande()));
        dateTraitement.setText(String.valueOf(entite.getDateDeTraitement()));
        
    }
}
