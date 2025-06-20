package gui_admin;

import entite.Equipement;
import entite.Salle;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import service.EquipementService;
import service.SalleService;

public class AdminDashboard extends JFrame {

    private JPanel mainContent;  // zone centrale dynamique
    private final Color activeColor = new Color(25, 25, 112);
    private final JButton instal_mater_btn = new JButton("Installation & Matériel");
    private final JButton abonnement_btn = new JButton("Abonnement");
    private final JButton membre_btn = new JButton("Membre");
    private final JButton inscription_btn = new JButton("Inscription");
    private final JButton session_btn = new JButton("Session");
    private final JButton[] boutons = {
        instal_mater_btn, abonnement_btn, membre_btn, inscription_btn, session_btn
    };
    
    EquipementService equipementService = new EquipementService(); 
    SalleService salleService = new SalleService(); 

    public AdminDashboard() {
        super("Tableau de bord Admin");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900, 600);
        this.setLocationRelativeTo(null);

        // --- Top Bar ---
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(new Color(255, 215, 0)); // Jaune

        // Panel gauche : logo
        JPanel topBar_left = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topBar_left.setOpaque(false);

        // Panel droit : boutons
        JPanel topBar_right = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topBar_right.setOpaque(false);

        JLabel logo = new JLabel("FITPlus+");
        logo.setFont(new Font("SansSerif", Font.BOLD, 28));

        // Boutons style
        for (JButton btn : boutons) {
            btn.setContentAreaFilled(false);
            btn.setBorderPainted(false);
            btn.setFocusPainted(false);
            btn.setForeground(Color.white);
            btn.setFont(new Font("SansSerif", Font.BOLD, 14));
        }

        // Ajout dans les panels
        topBar_left.add(logo);
        topBar_right.add(instal_mater_btn);
        topBar_right.add(abonnement_btn);
        topBar_right.add(membre_btn);
        topBar_right.add(inscription_btn);
        topBar_right.add(session_btn);

        topBar.add(topBar_left, BorderLayout.WEST);
        topBar.add(topBar_right, BorderLayout.EAST);

        this.setLayout(new BorderLayout());
        this.add(topBar, BorderLayout.NORTH);

        // --- Zone centrale ---
        mainContent = new JPanel(new BorderLayout());
        mainContent.setBackground(Color.WHITE);
        this.add(mainContent, BorderLayout.CENTER);

        // --- Actions ---
        instal_mater_btn.addActionListener(e -> {
            activerBouton(instal_mater_btn);
            showInstal_mater();
        });

        abonnement_btn.addActionListener(e -> {
            activerBouton(abonnement_btn);
            showAbonnements();
        });

        membre_btn.addActionListener(e -> {
            activerBouton(membre_btn);
            showMembres();
        });

        inscription_btn.addActionListener(e -> {
            activerBouton(inscription_btn);
            showInscription();
        });

        session_btn.addActionListener(e -> {
            activerBouton(session_btn);
            showSessions();
        });

        // Par défaut
        activerBouton(instal_mater_btn);
        showInstal_mater();
    }

    // ✅ Fonction propre pour changer l’apparence du bouton actif
    private void activerBouton(JButton boutonActif) {
        for (JButton btn : boutons) {
            if (btn == boutonActif) {
                btn.setOpaque(true);
                btn.setBackground(new Color(255, 215, 0));
                btn.setForeground(activeColor);
            } else {
                btn.setOpaque(false);
                btn.setBackground(null);
                btn.setForeground(Color.WHITE);
            }
        }
    }

    private void showInstal_mater() {
        mainContent.removeAll();

        // Panel principal avec BoxLayout vertical
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // === SECTION SALLES ===

        // Titre "Salles"
        JLabel titleSalles = new JLabel("Salles");
        titleSalles.setFont(new Font("Arial", Font.BOLD, 18));
        titleSalles.setAlignmentX(Component.LEFT_ALIGNMENT);
        titleSalles.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        mainPanel.add(titleSalles);

        // Récupération des données des salles depuis la base de données
        String[] columnsSalles = {"ID", "Libellé", "Description", "Modifier", "Supprimer"};
        List<Salle> salles = salleService.listerTous();
        Object[][] dataSalles = new Object[salles.size()][5];

        // Remplissage du tableau avec les données de la base
        for (int i = 0; i < salles.size(); i++) {
            Salle salle = salles.get(i);
            dataSalles[i][0] = salle.getId();
            dataSalles[i][1] = salle.getLibelle();
            dataSalles[i][2] = salle.getDescription();
            dataSalles[i][3] = "";
            dataSalles[i][4] = "";
        }

        // Tableau des salles
        JTable tableSalles = new JTable(dataSalles, columnsSalles) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Renderer personnalisé pour les colonnes Modifier/Supprimer
        tableSalles.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
                    boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(activeColor);
                setText("");
                return c;
            }
        });

        tableSalles.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
                    boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(Color.RED);
                setText("");
                return c;
            }
        });

        // Configuration du tableau
        tableSalles.setRowHeight(30);
        tableSalles.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tableSalles.setFont(new Font("Arial", Font.PLAIN, 11));

        // Gestionnaire de clic pour les boutons du tableau
        tableSalles.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableSalles.rowAtPoint(e.getPoint());
                int col = tableSalles.columnAtPoint(e.getPoint());

                if (row >= 0) {
                    if (col == 3) { // Colonne "Modifier"
                        // Récupérer la salle sélectionnée
                        Salle salleAModifier = salles.get(row);
                        ouvrirDialogueModificationSalle(salleAModifier);
                    } else if (col == 4) { // Colonne "Supprimer"
                        int confirm = JOptionPane.showConfirmDialog(mainContent,
                            "Êtes-vous sûr de vouloir supprimer cette salle ?",
                            "Confirmation", JOptionPane.YES_NO_OPTION);
                        if (confirm == JOptionPane.YES_OPTION) {
                            try {
                                // Récupérer l'ID de la salle à supprimer
                                int idSalle = (Integer) tableSalles.getValueAt(row, 0);
                                
                                // Appeler la méthode de suppression
                                salleService.suprimer(idSalle);
                                
                                // Message de succès
                                JOptionPane.showMessageDialog(mainContent,
                                    "Salle supprimée avec succès (ID: " + idSalle + ")",
                                    "Succès", JOptionPane.INFORMATION_MESSAGE);
                                
                                // Rafraîchir l'affichage
                                showInstal_mater();
                                
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(mainContent,
                                    "Erreur lors de la suppression: " + ex.getMessage(),
                                    "Erreur", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                }
            }
        });

        // ScrollPane pour le tableau des salles
        JScrollPane scrollPaneSalles = new JScrollPane(tableSalles);
        scrollPaneSalles.setPreferredSize(new Dimension(800, 120));
        scrollPaneSalles.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(scrollPaneSalles);

        // Bouton "Ajouter" pour les salles
        JButton btnAjouterSalle = new JButton("Ajouter");
        btnAjouterSalle.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnAjouterSalle.setMaximumSize(new Dimension(100, 30));
        btnAjouterSalle.addActionListener(e -> {
            ouvrirDialogueAjoutSalle();
        });

        JPanel panelBtnSalle = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBtnSalle.add(btnAjouterSalle);
        panelBtnSalle.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(panelBtnSalle);

        // Espacement entre les sections
        mainPanel.add(Box.createVerticalStrut(30));

        // === SECTION ÉQUIPEMENTS ===

        // Titre "Équipements"
        JLabel titleEquipements = new JLabel("Équipements");
        titleEquipements.setFont(new Font("Arial", Font.BOLD, 18));
        titleEquipements.setAlignmentX(Component.LEFT_ALIGNMENT);
        titleEquipements.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        mainPanel.add(titleEquipements);

        // Récupération des données des équipements depuis la base de données
        String[] columnsEquipements = {"ID", "Libellé", "Description", "Salle", "Modifier", "Supprimer"};
        List<Equipement> equipements = equipementService.ListerTout();
        Object[][] dataEquipements = new Object[equipements.size()][6];

        // Remplissage du tableau avec les données de la base
        for (int i = 0; i < equipements.size(); i++) {
            Equipement equipement = equipements.get(i);
            dataEquipements[i][0] = equipement.getId();
            dataEquipements[i][1] = equipement.getLibelle();
            dataEquipements[i][2] = equipement.getDescription();
            dataEquipements[i][3] = equipement.getSalle() != null ? equipement.getSalle().getId() : "Aucune salle";
            dataEquipements[i][4] = "";
            dataEquipements[i][5] = "";
        }

        // Tableau des équipements
        JTable tableEquipements = new JTable(dataEquipements, columnsEquipements) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Renderer personnalisé pour les colonnes Modifier/Supprimer
        tableEquipements.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
                    boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(activeColor);
                setText("");
                return c;
            }
        });

        tableEquipements.getColumnModel().getColumn(5).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
                    boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(Color.RED);
                setText("");
                return c;
            }
        });

        // Configuration du tableau
        tableEquipements.setRowHeight(30);
        tableEquipements.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tableEquipements.setFont(new Font("Arial", Font.PLAIN, 11));

        // Gestionnaire de clic pour les boutons du tableau équipements
        tableEquipements.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableEquipements.rowAtPoint(e.getPoint());
                int col = tableEquipements.columnAtPoint(e.getPoint());

                if (row >= 0) {
                    if (col == 4) { // Colonne "Modifier"
                        // Récupérer l'équipement sélectionné
                        Equipement equipementAModifier = equipements.get(row);
                        ouvrirDialogueModificationEquipement(equipementAModifier, salles);
                    } else if (col == 5) { // Colonne "Supprimer"
                        int confirm = JOptionPane.showConfirmDialog(mainContent,
                            "Êtes-vous sûr de vouloir supprimer cet équipement ?",
                            "Confirmation", JOptionPane.YES_NO_OPTION);
                        if (confirm == JOptionPane.YES_OPTION) {
                            try {
                                // Récupérer l'équipement à supprimer
                                Equipement equipementASupprimer = equipements.get(row);
                                
                                // Appeler la méthode de suppression
                                equipementService.supprimer(equipementASupprimer);
                                
                                // Message de succès
                                JOptionPane.showMessageDialog(mainContent,
                                    "Équipement supprimé avec succès (ID: " + equipementASupprimer.getId() + ")",
                                    "Succès", JOptionPane.INFORMATION_MESSAGE);
                                
                                // Rafraîchir l'affichage
                                showInstal_mater();
                                
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(mainContent,
                                    "Erreur lors de la suppression: " + ex.getMessage(),
                                    "Erreur", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                }
            }
        });

        // ScrollPane pour le tableau des équipements
        JScrollPane scrollPaneEquipements = new JScrollPane(tableEquipements);
        scrollPaneEquipements.setPreferredSize(new Dimension(800, 150));
        scrollPaneEquipements.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(scrollPaneEquipements);

        // Bouton "Ajouter" pour les équipements
        JButton btnAjouterEquipement = new JButton("Ajouter");
        btnAjouterEquipement.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnAjouterEquipement.setMaximumSize(new Dimension(100, 30));
        btnAjouterEquipement.addActionListener(e -> {
            ouvrirDialogueAjoutEquipement(salles);
        });

        JPanel panelBtnEquipement = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBtnEquipement.add(btnAjouterEquipement);
        panelBtnEquipement.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(panelBtnEquipement);

        // Ajouter le panel principal au conteneur
        mainContent.add(mainPanel, BorderLayout.CENTER);

        // Rafraîchir l'affichage
        mainContent.revalidate();
        mainContent.repaint();
    }

    // Méthode pour ouvrir le dialogue d'ajout de salle
    private void ouvrirDialogueAjoutSalle() {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(mainContent), "Ajouter une salle", true);
        dialog.setSize(400, 200);
        dialog.setLocationRelativeTo(mainContent);

        // Panel principal
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Champ ID salle
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("ID Salle :"), gbc);

        JTextField txtId = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 0; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(txtId, gbc);

        // Champ libellé
        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("Libellé :"), gbc);

        JTextField txtLibelle = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(txtLibelle, gbc);

        // Champ description
        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("Description :"), gbc);

        JTextArea txtDescription = new JTextArea(3, 20);
        txtDescription.setLineWrap(true);
        txtDescription.setWrapStyleWord(true);
        JScrollPane scrollDesc = new JScrollPane(txtDescription);
        gbc.gridx = 1; gbc.gridy = 2; gbc.fill = GridBagConstraints.BOTH;
        panel.add(scrollDesc, gbc);

        // Panel des boutons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton btnSauvegarder = new JButton("Sauvegarder");
        JButton btnAnnuler = new JButton("Annuler");

        buttonPanel.add(btnSauvegarder);
        buttonPanel.add(btnAnnuler);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(buttonPanel, gbc);

        // Actions des boutons
        btnSauvegarder.addActionListener(e -> {
            String idText = txtId.getText().trim();
            String libelle = txtLibelle.getText().trim();
            String description = txtDescription.getText().trim();

            if (libelle.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Le libellé est obligatoire!", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            int id = Integer.parseInt(idText);
            // Créer et sauvegarder la salle
            Salle nouvelleSalle = new Salle();
            nouvelleSalle.setId(id);
            nouvelleSalle.setLibelle(libelle);
            nouvelleSalle.setDescription(description);

            try {
                salleService.ajouter(nouvelleSalle);
                JOptionPane.showMessageDialog(dialog, "Salle ajoutée avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
                // Rafraîchir l'affichage
                showInstal_mater();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Erreur lors de l'ajout: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnAnnuler.addActionListener(e -> dialog.dispose());

        dialog.add(panel);
        dialog.setVisible(true);
    }

    // Méthode pour ouvrir le dialogue d'ajout d'équipement
    private void ouvrirDialogueAjoutEquipement(List<Salle> salles) {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(mainContent), "Ajouter un équipement", true);
        dialog.setSize(450, 250);
        dialog.setLocationRelativeTo(mainContent);

        // Panel principal
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Champ ID équipement
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("ID Équipement :"), gbc);

        JTextField txtId = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 0; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(txtId, gbc);

        // Champ libellé
        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("Libellé :"), gbc);

        JTextField txtLibelle = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(txtLibelle, gbc);

        // Champ description
        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("Description :"), gbc);

        JTextArea txtDescription = new JTextArea(3, 20);
        txtDescription.setLineWrap(true);
        txtDescription.setWrapStyleWord(true);
        JScrollPane scrollDesc = new JScrollPane(txtDescription);
        gbc.gridx = 1; gbc.gridy = 2; gbc.fill = GridBagConstraints.BOTH;
        panel.add(scrollDesc, gbc);

        // ComboBox pour les salles
        gbc.gridx = 0; gbc.gridy = 3; gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("Salle :"), gbc);

        JComboBox<Salle> cmbSalles = new JComboBox<>();
        cmbSalles.addItem(null); // Option "Aucune salle"
        for (Salle salle : salles) {
            cmbSalles.addItem(salle);
        }

        // Renderer pour afficher le libellé des salles
        cmbSalles.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, 
                    boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value == null) {
                    setText("Aucune salle");
                } else if (value instanceof Salle) {
                    setText(((Salle) value).getLibelle());
                }
                return this;
            }
        });

        gbc.gridx = 1; gbc.gridy = 3; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(cmbSalles, gbc);

        // Panel des boutons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton btnSauvegarder = new JButton("Sauvegarder");
        JButton btnAnnuler = new JButton("Annuler");

        buttonPanel.add(btnSauvegarder);
        buttonPanel.add(btnAnnuler);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(buttonPanel, gbc);

        // Actions des boutons
        btnSauvegarder.addActionListener(e -> {
            String idText = txtId.getText().trim();
            String libelle = txtLibelle.getText().trim();
            String description = txtDescription.getText().trim();
            Salle salleSelectionnee = (Salle) cmbSalles.getSelectedItem();

            if (idText.isEmpty() || libelle.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "L'ID et le libellé sont obligatoires!", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int id = Integer.parseInt(idText);

                // Créer et sauvegarder l'équipement
                Equipement nouvelEquipement = new Equipement();
                nouvelEquipement.setId(id);
                nouvelEquipement.setLibelle(libelle);
                nouvelEquipement.setDescription(description);
                nouvelEquipement.setSalle(salleSelectionnee);

                equipementService.ajouter(nouvelEquipement);
                JOptionPane.showMessageDialog(dialog, "Équipement ajouté avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
                // Rafraîchir l'affichage
                showInstal_mater();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "L'ID doit être un nombre entier!", "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Erreur lors de l'ajout: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnAnnuler.addActionListener(e -> dialog.dispose());

        dialog.add(panel);
        dialog.setVisible(true);
    }

    // Méthode pour ouvrir le dialogue de modification d'une salle
    private void ouvrirDialogueModificationSalle(Salle salle) {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(mainContent), 
                                    "Modifier la salle", true);
        dialog.setSize(400, 200);
        dialog.setLocationRelativeTo(mainContent);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Champ libellé
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Libellé:"), gbc);
        gbc.gridx = 1;
        JTextField txtLibelle = new JTextField(salle.getLibelle(), 20);
        panel.add(txtLibelle, gbc);

        // Champ description
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Description:"), gbc);
        gbc.gridx = 1;
        JTextField txtDescription = new JTextField(salle.getDescription(), 20);
        panel.add(txtDescription, gbc);

        // Boutons
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        JPanel btnPanel = new JPanel(new FlowLayout());

        JButton btnSauvegarder = new JButton("Sauvegarder");
        btnSauvegarder.addActionListener(e -> {
            // Mettre à jour l'objet salle
            salle.setLibelle(txtLibelle.getText());
            salle.setDescription(txtDescription.getText());

            // Appeler la méthode de modification
            salleService.modifier(salle); // Assumant que vous avez salleService

            // Fermer le dialogue
            dialog.dispose();

            // Rafraîchir l'affichage
            showInstal_mater();

            JOptionPane.showMessageDialog(mainContent, "Salle modifiée avec succès!");
        });

        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.addActionListener(e -> dialog.dispose());

        btnPanel.add(btnSauvegarder);
        btnPanel.add(btnAnnuler);
        panel.add(btnPanel, gbc);

        dialog.add(panel);
        dialog.setVisible(true);
    }

    // Méthode pour ouvrir le dialogue de modification d'un équipement
    private void ouvrirDialogueModificationEquipement(Equipement equipement, List<Salle> salles) {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(mainContent), 
                                    "Modifier l'équipement", true);
        dialog.setSize(400, 250);
        dialog.setLocationRelativeTo(mainContent);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Champ libellé
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Libellé:"), gbc);
        gbc.gridx = 1;
        JTextField txtLibelle = new JTextField(equipement.getLibelle(), 20);
        panel.add(txtLibelle, gbc);

        // Champ description
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Description:"), gbc);
        gbc.gridx = 1;
        JTextField txtDescription = new JTextField(equipement.getDescription(), 20);
        panel.add(txtDescription, gbc);

        // ComboBox pour la salle
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Salle:"), gbc);
        gbc.gridx = 1;
        JComboBox<Salle> cmbSalle = new JComboBox<>();
        cmbSalle.addItem(null); // Option "Aucune salle"
        for (Salle salle : salles) {
            cmbSalle.addItem(salle);
        }
        cmbSalle.setSelectedItem(equipement.getSalle());
        cmbSalle.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, 
                    int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value == null) {
                    setText("Aucune salle");
                } else {
                    Salle salle = (Salle) value;
                    setText(salle.getLibelle());
                }
                return this;
            }
        });
        panel.add(cmbSalle, gbc);

        // Boutons
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        JPanel btnPanel = new JPanel(new FlowLayout());

        JButton btnSauvegarder = new JButton("Sauvegarder");
        btnSauvegarder.addActionListener(e -> {
            // Mettre à jour l'objet équipement
            equipement.setLibelle(txtLibelle.getText());
            equipement.setDescription(txtDescription.getText());
            equipement.setSalle((Salle) cmbSalle.getSelectedItem());

            // Appeler la méthode de modification
            equipementService.modifier(equipement); // Assumant que vous avez equipementService

            // Fermer le dialogue
            dialog.dispose();

            // Rafraîchir l'affichage
            showInstal_mater();

            JOptionPane.showMessageDialog(mainContent, "Équipement modifié avec succès!");
        });

        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.addActionListener(e -> dialog.dispose());

        btnPanel.add(btnSauvegarder);
        btnPanel.add(btnAnnuler);
        panel.add(btnPanel, gbc);

        dialog.add(panel);
        dialog.setVisible(true);
    }

    private void showMembres() {
        mainContent.removeAll();
        JLabel label = new JLabel("Page de gestion des membres");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        mainContent.add(label, BorderLayout.CENTER);
        mainContent.revalidate();
        mainContent.repaint();
    }

    private void showAbonnements() {
        mainContent.removeAll();
        JLabel label = new JLabel("Page de gestion des abonnements");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        mainContent.add(label, BorderLayout.CENTER);
        mainContent.revalidate();
        mainContent.repaint();
    }

    private void showInscription() {
        mainContent.removeAll();
        JLabel label = new JLabel("Page de gestion des inscriptions");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        mainContent.add(label, BorderLayout.CENTER);
        mainContent.revalidate();
        mainContent.repaint();
    }

    private void showSessions() {
        mainContent.removeAll();
        JLabel label = new JLabel("Page de gestion des sessions");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        mainContent.add(label, BorderLayout.CENTER);
        mainContent.revalidate();
        mainContent.repaint();
    }
}
