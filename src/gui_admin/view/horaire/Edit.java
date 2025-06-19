/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui_admin.view.horaire;

import entite.Horaire;
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
    private Horaire entite = new Horaire(); // ou Seance ou autre classe

    private JTextField id = new JTextField();
    private JTextField dateDebut = new JTextField();
    private JTextField dateFin = new JTextField();

    
    public Edit(){
        super();
        JLabel idLabel    = new JLabel("Id : ");
        JLabel dateDebutLabel = new JLabel("Date Debut : ");
        JLabel dateFinLabel = new JLabel ("Date Fin : ");

        
        id.setEditable(false); 
        
        id.setText(String.valueOf(entite.getId()));
        dateDebut.setText(String.valueOf(entite.getDebut()));
        dateFin.setText(String.valueOf(entite.getFin()));
       
        this.add(form, BorderLayout.CENTER);
        this.add(buttonPanel,BorderLayout.SOUTH );
        this.form.add(idLabel);
        this.form.add(id);
        
        this.form.add(dateDebutLabel);
        this.form.add(dateDebut);
        
        this.form.add(dateFinLabel);
        this.form.add(dateFin);
        
    }
    public Edit(Horaire entite){
      this();
      this.entite = entite;
      this.initForm(entite);
    }
    
    
    public void init(){
        this.entite.setId(Integer.parseInt(id.getText()));
        this.entite.setDebut(LocalDateTime.parse(dateDebut.getText()));
        this.entite.setFin(LocalDateTime.parse(dateFin.getText()));
    }

    public Horaire getEntite() {
        return entite;
    }
     private void initForm(Horaire entite){
        id.setText(String.valueOf(entite.getId()));
        dateDebut.setText(String.valueOf(entite.getDebut()));
        dateFin.setText(String.valueOf(entite.getFin()));
    }
}
