/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui_admin.view.salle;

import entite.Membre;
import entite.Salle;
import entite.Seance;
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
    private Salle entite = new Salle();
    
    private JTextField id = new JTextField();
    private JTextField libelle = new JTextField();
    private JTextField description = new JTextField();

    
    public Edit(){
        super();
        JLabel idLabel    = new JLabel("Id : ");
        JLabel libelleLabel = new JLabel("Libelle : ");
        JLabel descriptionLabel = new JLabel ("Description : ");
        
        id.setEditable(false); 
        
        id.setText(String.valueOf(entite.getId()));
        libelle.setText(entite.getLibelle());
        description.setText(entite.getDescription());
       
        this.add(form, BorderLayout.CENTER);
        this.add(buttonPanel,BorderLayout.SOUTH );
        this.form.add(idLabel);
        this.form.add(id);
        
        this.form.add(libelleLabel);
        this.form.add(libelle);
        
        this.form.add(descriptionLabel);
        this.form.add(description);
                
    }
    public Edit(Salle entite){
      this();
      this.entite = entite;
      this.initForm(entite);
    }
    
    
    public void init(){
        this.entite.setId(Integer.parseInt(id.getText()));
        this.entite.setLibelle(libelle.getText());
        this.entite.setDescription(description.getText());
    }

    public Salle getEntite() {
        return entite;
    }
     private void initForm(Salle entite){
        id.setText(String.valueOf(entite.getId()));
        libelle.setText(entite.getLibelle());
        libelle.setText(entite.getDescription());
    }
}
