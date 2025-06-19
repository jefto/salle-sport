/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui_admin.view.equipement;

import entite.Equipement;
import entite.Salle;
import gui_util.GenericEdit;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author DevJude
 */
public class Edit extends GenericEdit{
    private Equipement entite = new Equipement() ;
    
    private JTextField id = new JTextField();
    private JTextField libelle = new JTextField();
    private JTextField description = new JTextField();
    
    private JComboBox<Salle> salle = new JComboBox<>();
    
    public Edit(){
        super();
        JLabel idLabel    = new JLabel("Id : ");
        JLabel libelleLabel = new JLabel("Libellé : ");
        JLabel descriptionLabel = new JLabel ("Description : ");
        
        id.setEditable(false); 
        salle.setEditable(false); 
        
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
    public Edit(Equipement entite){
      this();
      this.entite = entite;
      this.initForm(entite);
    }
    
    
    public void init(){
        this.entite.setId(Integer.parseInt(id.getText()));
        this.entite.setLibelle(libelle.getText());
        this.entite.setDescription(description.getText());
        entite.setSalle((Salle) salle.getSelectedItem());
    }

    public Equipement getEntite() {
        return entite;
    }
    private void initForm(Equipement entite){
        id.setText(String.valueOf(entite.getId()));
        libelle.setText(entite.getLibelle());
        description.setText(entite.getDescription());
        
        salle.setSelectedItem(entite.getSalle());
  
     }
}
