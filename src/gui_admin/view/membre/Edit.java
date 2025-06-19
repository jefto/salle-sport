/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui_admin.view.membre;

import entite.Client;
import entite.Membre;
import gui_util.GenericEdit;
import java.awt.BorderLayout;
import java.time.LocalDateTime;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import service.ClientService;

/**
 *
 * @author TCHAMIE
 */
public class Edit extends GenericEdit{
    private Membre entite = new Membre(); // ou Seance ou autre classe
    private ClientService clientService = new ClientService();
    
    private JTextField id = new JTextField();
    private JTextField dateInscription = new JTextField();

    private JComboBox<Client> id_client = new JComboBox<>();

    
    public Edit(){
        super();
        JLabel idLabel    = new JLabel("Id : ");
        JLabel dateInscriptionLabel = new JLabel("Date Inscription : ");
        JLabel idClientLabel = new JLabel("Id Client : ");
        
        id.setEditable(false); 
        id_client.setEditable(false); 
        
        for (Client client : clientService.listerTout()) {
            id_client.addItem(client);
        }

        
        id.setText(String.valueOf(entite.getId()));
        dateInscription.setText(String.valueOf(entite.getDateInscription()));
       
        this.add(form, BorderLayout.CENTER);
        this.add(buttonPanel,BorderLayout.SOUTH );
        this.form.add(idLabel);
        this.form.add(id);
        
        this.form.add(dateInscriptionLabel);
        this.form.add(dateInscription);
        
        this.form.add(idClientLabel);   
        this.form.add(id_client);
    }
    public Edit(Membre entite){
      this();
      this.entite = entite;
      this.initForm(entite);
    }
    
    
    public void init(){
        this.entite.setId(Integer.parseInt(id.getText()));
        this.entite.setDateInscription(LocalDateTime.parse(dateInscription.getText()));
        entite.setClient((Client) id_client.getSelectedItem());
    }

    public Membre getEntite() {
        return entite;
    }
     private void initForm(Membre entite){
        id.setText(String.valueOf(entite.getId()));
        dateInscription.setText(String.valueOf(entite.getDateInscription()));
        
        id_client.setSelectedItem(entite.getClient());
    }
}
