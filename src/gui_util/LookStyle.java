/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui_util;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author DevJude
 */


public class LookStyle extends JFrame {

    protected JPanel titlePanel  = new JPanel();
    protected JPanel bodyPanel   = new JPanel();
    protected JPanel pathPanel   = new JPanel();
    
    public LookStyle() {
        this.setSize(900, 600);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Container c = this.getContentPane();
        c.setLayout(new BorderLayout());
        
        c.add(titlePanel, BorderLayout.NORTH);
        c.add(bodyPanel, BorderLayout.CENTER);
        c.add(pathPanel ,BorderLayout.WEST);

        this.setVisible(true);
    }

}


