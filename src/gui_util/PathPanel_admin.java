/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui_util;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author DevJude
 */
public class PathPanel_admin extends JPanel {
   private JButton abonnement           = new JButton("Abonnements"); 
   private JButton typeAbonnement       = new JButton("TypeAbonnements"); 
   private JButton demandeInscription   = new JButton("DemandeInscriptions"); 
   private JButton moyenPaiment         = new JButton("MoyenPaiements"); 
   private JButton salle                = new JButton("Salles"); 
   private JButton seance               = new JButton("Seances"); 
   private JButton equipement           = new JButton("Equipements"); 
   private JButton horaire              = new JButton("Horaires"); 
   private JButton membre              = new JButton("Membres"); 

  private List<JButton> buttons = new ArrayList<>();

    public PathPanel_admin() {
        this.setLayout(new GridLayout(0,1));
        this.setBackground(new Color(45, 90, 210));

        buttons.add(demandeInscription);
        buttons.add(salle);
        buttons.add(seance);
        buttons.add(equipement);
        buttons.add(horaire);
        buttons.add(membre);
        buttons.add(abonnement);
        buttons.add(typeAbonnement);
        buttons.add(moyenPaiment);


        for (JButton button : buttons) {
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setOpaque(false);
            button.setForeground(Color.WHITE);
            button.setOpaque(false);
            
            this.add(button);
        }
    }

   
   
    public JButton getAbonnement() {
        return abonnement;
    }

    public JButton getTypeAbonnement() {
        return typeAbonnement;
    }

    public JButton getDemandeInscription() {
        return demandeInscription;
    }

    public JButton getMoyenPaiment() {
        return moyenPaiment;
    }

    public JButton getSalle() {
        return salle;
    }

    public JButton getSeance() {
        return seance;
    }

    public JButton getEquipement() {
        return equipement;
    }

    public JButton getHoraire() {
        return horaire;
    }
   
   
}
