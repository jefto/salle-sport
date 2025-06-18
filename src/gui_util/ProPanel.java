/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui_util;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;


/**
 *
 * @author DevJude
 */
public class ProPanel extends JPanel {
    protected JPanel contentPanel = new JPanel();
    protected JPanel actionPanel  = new JPanel();
    protected JPanel addElementPanel = new JPanel();

    public ProPanel() {
        
        this.setLayout(new BorderLayout());
        
        contentPanel.setBackground(Color.red);
        addElementPanel.setBackground(Color.BLACK);
        actionPanel.setBackground(Color.GRAY);
        
        
    }
    
    
}
