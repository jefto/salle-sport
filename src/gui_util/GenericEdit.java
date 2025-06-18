/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui_util;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;



/**
 *
 * @author DevJude
 */
public class GenericEdit extends JFrame {
    
    protected JPanel form = new JPanel();
    protected ButtonPanel buttonPanel = new ButtonPanel();
    
    public GenericEdit(){
        this.setSize(400,200);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.form.setLayout(new GridLayout(0,2));
        Container c = this.getContentPane();   
        
    }

    public JPanel getForm() {
        return form;
    }

    public void setForm(JPanel form) {
        this.form = form;
    }
    public JButton getsaveButton(){
        return buttonPanel.getSaveButton();
    }
    public JButton getcancelButton(){
        return buttonPanel.getCancelButton();
    }
    
    public void afficher(){
        this.setVisible(true);
    }
    
}
