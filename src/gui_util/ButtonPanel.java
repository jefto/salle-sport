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
public class ButtonPanel extends JPanel {
    private JButton saveButton = new JButton("Enregistrer");
    private JButton cancelButton = new JButton("Annuler");
    
    public ButtonPanel(){
        this.setLayout( new FlowLayout(FlowLayout.RIGHT));
        this.add(saveButton);
        this.add(cancelButton);
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public void setSaveButton(JButton saveButton) {
        this.saveButton = saveButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public void setCancelButton(JButton cancelButton) {
        this.cancelButton = cancelButton;
    }
    
}
