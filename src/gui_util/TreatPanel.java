/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui_util;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author DevJude
 */
public class TreatPanel extends JPanel {

    private JButton deleteButton = new JButton("Supprimer");
    private JButton changeButton = new JButton("Modifier");

    public TreatPanel() {
        this.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.add(deleteButton);
        this.add(changeButton); // C'était ça qui manquait !
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(JButton deleteButton) {
        this.deleteButton = deleteButton;
    }

    public JButton getChangeButton() {
        return changeButton;
    }

    public void setChangeButton(JButton changeButton) {
        this.changeButton = changeButton;
    }
}
