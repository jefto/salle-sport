/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui_admin.view.moyenPaiement;

import entite.MoyenDePaiement;
import gui_util.GenericEdit;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author DevJude
 */
public class Edit extends GenericEdit{
    private MoyenDePaiement entite = new MoyenDePaiement(); // ou Seance ou autre classe

    private JTextField id = new JTextField();
    private JTextField code = new JTextField();
    private JTextField libelle = new JTextField();

    
    public Edit(){
        super();
        JLabel idLabel    = new JLabel("Id : ");
        JLabel codeLabel = new JLabel("Code : ");
        JLabel libelleLabel = new JLabel ("Libelle : ");

        code.setEditable(false); 
        
        id.setText(String.valueOf(entite.getId()));
        code.setText(entite.getCode());
        libelle.setText(entite.getLibelle());
       
        this.add(form, BorderLayout.CENTER);
        this.add(buttonPanel,BorderLayout.SOUTH );
        this.form.add(idLabel);
        this.form.add(id);
        
        this.form.add(codeLabel);
        this.form.add(code);
        
        this.form.add(libelleLabel);
        this.form.add(libelle);
        
        
    }
    public Edit(MoyenDePaiement entite){
      this();
      this.entite = entite;
      this.initForm(entite);
    }
    
    
    public void init(){
        this.entite.setId(Integer.parseInt(id.getText()));
        this.entite.setCode(code.getText());
        this.entite.setLibelle(libelle.getText());
    }

    public MoyenDePaiement getEntite() {
        return entite;
    }
     private void initForm(MoyenDePaiement entite){
        id.setText(String.valueOf(entite.getId()));
        code.setText(entite.getCode());
        libelle.setText(entite.getLibelle());
    }
}
