package gui_admin;

import entite.Client;
import entite.DemandeInscription;
import entite.Equipement;
import entite.Membre;
import entite.MoyenDePaiement;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import service.ClientService;
import service.DemandeInscriptionService;
import service.EquipementService;
import service.MembreService;
import service.MoyenDePaiementService;
import service.SalleService;

public class AdminDashboard extends JFrame {

    private JPanel mainContent;  // zone centrale dynamique
    private final Color activeColor = Color.BLACK;
    private final Color secondColor = Color.ORANGE;
    private final JButton instal_mater_btn = new JButton("Installation & Matériel");
    private final JButton abonnement_btn = new JButton("Abonnement");
    private final JButton membre_btn = new JButton("Utilisateur");
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
        topBar.setBackground(secondColor); // Jaune

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
                btn.setBackground(secondColor);
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
                c.setBackground(secondColor);
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
                c.setBackground(secondColor);
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
        MembreService membreService =new MembreService();
        ClientService clientService =new ClientService();
        
        // Panel principal avec BoxLayout vertical
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // === SECTION CLIENTS ===

        // Titre "Clients"
        JLabel titleClients = new JLabel("Clients");
        titleClients.setFont(new Font("Arial", Font.BOLD, 18));
        titleClients.setAlignmentX(Component.LEFT_ALIGNMENT);
        titleClients.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        mainPanel.add(titleClients);

        // Récupération des données des clients depuis la base de données
        String[] columnsClients = {"ID", "Nom", "Prénom", "Date Naissance", "Email", "Modifier", "Supprimer"};
        List<Client> clients = clientService.listerTout();
        Object[][] dataClients = new Object[clients.size()][7];

        // Remplissage du tableau avec les données de la base
        for (int i = 0; i < clients.size(); i++) {
            Client client = clients.get(i);
            dataClients[i][0] = client.getId();
            dataClients[i][1] = client.getNom();
            dataClients[i][2] = client.getPrenom();
            dataClients[i][3] = client.getDateNaissance().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            dataClients[i][4] = client.getEmail();
            dataClients[i][5] = "";
            dataClients[i][6] = "";
        }

        // Tableau des clients
        JTable tableClients = new JTable(dataClients, columnsClients) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Renderer personnalisé pour les colonnes Modifier/Supprimer des clients
        tableClients.getColumnModel().getColumn(5).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
                    boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(activeColor);
                setText("");
                return c;
            }
        });

        tableClients.getColumnModel().getColumn(6).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
                    boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(secondColor);
                setText("");
                return c;
            }
        });

        // Configuration du tableau des clients
        tableClients.setRowHeight(30);
        tableClients.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tableClients.setFont(new Font("Arial", Font.PLAIN, 11));

        // Gestionnaire de clic pour les boutons du tableau clients
        tableClients.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableClients.rowAtPoint(e.getPoint());
                int col = tableClients.columnAtPoint(e.getPoint());

                if (row >= 0) {
                    if (col == 5) { // Colonne "Modifier"
                        // Récupérer le client sélectionné
                        Client clientAModifier = clients.get(row);
                        ouvrirDialogueModificationClient(clientAModifier);
                    } else if (col == 6) { // Colonne "Supprimer"
                        int confirm = JOptionPane.showConfirmDialog(mainContent,
                            "Êtes-vous sûr de vouloir supprimer ce client ?",
                            "Confirmation", JOptionPane.YES_NO_OPTION);
                        if (confirm == JOptionPane.YES_OPTION) {
                            try {
                                // Récupérer le client à supprimer
                                Client clientASupprimer = clients.get(row);

                                // Appeler la méthode de suppression
                                clientService.supprimer(clientASupprimer);

                                // Message de succès
                                JOptionPane.showMessageDialog(mainContent,
                                    "Client supprimé avec succès (ID: " + clientASupprimer.getId() + ")",
                                    "Succès", JOptionPane.INFORMATION_MESSAGE);

                                // Rafraîchir l'affichage
                                showMembres();

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

        // ScrollPane pour le tableau des clients
        JScrollPane scrollPaneClients = new JScrollPane(tableClients);
        scrollPaneClients.setPreferredSize(new Dimension(800, 150));
        scrollPaneClients.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(scrollPaneClients);

        // Bouton "Ajouter" pour les clients
        JButton btnAjouterClient = new JButton("Ajouter");
        btnAjouterClient.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnAjouterClient.setMaximumSize(new Dimension(100, 30));
        btnAjouterClient.addActionListener(e -> {
            ouvrirDialogueAjoutClient();
        });

        JPanel panelBtnClient = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBtnClient.add(btnAjouterClient);
        panelBtnClient.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(panelBtnClient);

        // Espacement entre les sections
        mainPanel.add(Box.createVerticalStrut(30));

        // === SECTION MEMBRES ===

        // Titre "Membres"
        JLabel titleMembres = new JLabel("Membres");
        titleMembres.setFont(new Font("Arial", Font.BOLD, 18));
        titleMembres.setAlignmentX(Component.LEFT_ALIGNMENT);
        titleMembres.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        mainPanel.add(titleMembres);

        // Récupération des données des membres depuis la base de données
        String[] columnsMembres = {"ID Membre", "Date Inscription", "ID Client", "Modifier", "Supprimer"};
        List<Membre> membres = membreService.listerTous();
        Object[][] dataMembres = new Object[membres.size()][5];

        // Remplissage du tableau avec les données de la base
        for (int i = 0; i < membres.size(); i++) {
            Membre membre = membres.get(i);
            dataMembres[i][0] = membre.getId();
            dataMembres[i][1] = membre.getDateInscription().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            dataMembres[i][2] = membre.getClient() != null ? membre.getClient().getId() : "N/A";
            dataMembres[i][3] = "";
            dataMembres[i][4] = "";
        }

        // Tableau des membres
        JTable tableMembres = new JTable(dataMembres, columnsMembres) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Renderer personnalisé pour les colonnes Modifier/Supprimer des membres
        tableMembres.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
                    boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(activeColor);
                setText("");
                return c;
            }
        });

        tableMembres.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
                    boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(secondColor);
                setText("");
                return c;
            }
        });

        // Configuration du tableau des membres
        tableMembres.setRowHeight(30);
        tableMembres.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tableMembres.setFont(new Font("Arial", Font.PLAIN, 11));

        // Gestionnaire de clic pour les boutons du tableau membres
        tableMembres.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableMembres.rowAtPoint(e.getPoint());
                int col = tableMembres.columnAtPoint(e.getPoint());

                if (row >= 0) {
                    if (col == 3) { // Colonne "Modifier"
                        // Récupérer le membre sélectionné
                        Membre membreAModifier = membres.get(row);
                        ouvrirDialogueModificationMembre(membreAModifier, clients);
                    } else if (col == 4) { // Colonne "Supprimer"
                        int confirm = JOptionPane.showConfirmDialog(mainContent,
                            "Êtes-vous sûr de vouloir supprimer ce membre ?",
                            "Confirmation", JOptionPane.YES_NO_OPTION);
                        if (confirm == JOptionPane.YES_OPTION) {
                            try {
                                // Récupérer l'ID du membre à supprimer
                                int idMembre = (Integer) tableMembres.getValueAt(row, 0);

                                // Appeler la méthode de suppression
                                membreService.supprimer(idMembre);

                                // Message de succès
                                JOptionPane.showMessageDialog(mainContent,
                                    "Membre supprimé avec succès (ID: " + idMembre + ")",
                                    "Succès", JOptionPane.INFORMATION_MESSAGE);

                                // Rafraîchir l'affichage
                                showMembres();

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

        // ScrollPane pour le tableau des membres
        JScrollPane scrollPaneMembres = new JScrollPane(tableMembres);
        scrollPaneMembres.setPreferredSize(new Dimension(800, 150));
        scrollPaneMembres.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(scrollPaneMembres);

        // Bouton "Ajouter" pour les membres
        JButton btnAjouterMembre = new JButton("Ajouter");
        btnAjouterMembre.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnAjouterMembre.setMaximumSize(new Dimension(100, 30));
        btnAjouterMembre.addActionListener(e -> {
            ouvrirDialogueAjoutMembre(clients);
        });

        JPanel panelBtnMembre = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBtnMembre.add(btnAjouterMembre);
        panelBtnMembre.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(panelBtnMembre);

        // Ajouter le panel principal au conteneur
        mainContent.add(mainPanel, BorderLayout.CENTER);

        // Rafraîchir l'affichage
        mainContent.revalidate();
        mainContent.repaint();
    }

    // Méthode pour ouvrir le dialogue d'ajout de client
    private void ouvrirDialogueAjoutClient() {
        ClientService clientService =new ClientService();
        
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(mainContent), "Ajouter un client", true);
        dialog.setSize(450, 300);
        dialog.setLocationRelativeTo(mainContent);

        // Panel principal
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Champ ID client
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("ID Client :"), gbc);

        JTextField txtId = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 0; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(txtId, gbc);

        // Champ nom
        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("Nom :"), gbc);

        JTextField txtNom = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(txtNom, gbc);

        // Champ prénom
        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("Prénom :"), gbc);

        JTextField txtPrenom = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 2; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(txtPrenom, gbc);

        // Champ date de naissance
        gbc.gridx = 0; gbc.gridy = 3; gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("Date Naissance (dd/MM/yyyy) :"), gbc);

        JTextField txtDateNaissance = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 3; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(txtDateNaissance, gbc);

        // Champ email
        gbc.gridx = 0; gbc.gridy = 4; gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("Email :"), gbc);

        JTextField txtEmail = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 4; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(txtEmail, gbc);

        // Panel des boutons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton btnSauvegarder = new JButton("Sauvegarder");
        JButton btnAnnuler = new JButton("Annuler");

        buttonPanel.add(btnSauvegarder);
        buttonPanel.add(btnAnnuler);

        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(buttonPanel, gbc);

        // Actions des boutons
        btnSauvegarder.addActionListener(e -> {
            String idText = txtId.getText().trim();
            String nom = txtNom.getText().trim();
            String prenom = txtPrenom.getText().trim();
            String dateNaissanceText = txtDateNaissance.getText().trim();
            String email = txtEmail.getText().trim();

            if (idText.isEmpty() || nom.isEmpty() || prenom.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Tous les champs sont obligatoires!", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int id = Integer.parseInt(idText);
                LocalDateTime dateNaissance = LocalDate.parse(dateNaissanceText, DateTimeFormatter.ofPattern("dd/MM/yyyy")).atStartOfDay();

                // Créer et sauvegarder le client
                Client nouveauClient = new Client();
                nouveauClient.setId(id);
                nouveauClient.setNom(nom);
                nouveauClient.setPrenom(prenom);
                nouveauClient.setDateNaissance(dateNaissance);
                nouveauClient.setEmail(email);

                clientService.ajouter(nouveauClient);
                JOptionPane.showMessageDialog(dialog, "Client ajouté avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
                // Rafraîchir l'affichage
                showMembres();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Erreur lors de l'ajout: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnAnnuler.addActionListener(e -> dialog.dispose());

        dialog.add(panel);
        dialog.setVisible(true);
    }

    // Méthode pour ouvrir le dialogue d'ajout de membre
    private void ouvrirDialogueAjoutMembre(List<Client> clients) {
        MembreService membreService =new MembreService();
        
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(mainContent), "Ajouter un membre", true);
        dialog.setSize(450, 250);
        dialog.setLocationRelativeTo(mainContent);

        // Panel principal
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // ComboBox pour les clients
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("Client :"), gbc);

        JComboBox<Client> cmbClients = new JComboBox<>();
        for (Client client : clients) {
            cmbClients.addItem(client);
        }

        // Renderer pour afficher le nom complet du client
        cmbClients.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, 
                    boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Client) {
                    Client client = (Client) value;
                    setText(client.getNom() + " " + client.getPrenom() + " (ID: " + client.getId() + ")");
                }
                return this;
            }
        });

        gbc.gridx = 1; gbc.gridy = 0; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(cmbClients, gbc);

        // Champ date d'inscription
        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("Date d'inscription (dd/MM/yyyy) :"), gbc);

        JTextField txtDateInscription = new JTextField(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), 20);
        gbc.gridx = 1; gbc.gridy = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(txtDateInscription, gbc);

        // Panel des boutons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton btnSauvegarder = new JButton("Sauvegarder");
        JButton btnAnnuler = new JButton("Annuler");

        buttonPanel.add(btnSauvegarder);
        buttonPanel.add(btnAnnuler);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(buttonPanel, gbc);

        // Actions des boutons
        btnSauvegarder.addActionListener(e -> {
            Client clientSelectionne = (Client) cmbClients.getSelectedItem();
            String dateInscriptionText = txtDateInscription.getText().trim();

            if (clientSelectionne == null) {
                JOptionPane.showMessageDialog(dialog, "Veuillez sélectionner un client!", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                LocalDateTime dateInscription = LocalDate.parse(dateInscriptionText, DateTimeFormatter.ofPattern("dd/MM/yyyy")).atStartOfDay();

                // Créer et sauvegarder le membre
                Membre nouveauMembre = new Membre();
                nouveauMembre.setClient(clientSelectionne);
                nouveauMembre.setDateInscription(dateInscription);

                membreService.ajouter(nouveauMembre);
                JOptionPane.showMessageDialog(dialog, "Membre ajouté avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
                // Rafraîchir l'affichage
                showMembres();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Erreur lors de l'ajout: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnAnnuler.addActionListener(e -> dialog.dispose());

        dialog.add(panel);
        dialog.setVisible(true);
    }

    // Méthode pour ouvrir le dialogue de modification d'un client
    private void ouvrirDialogueModificationClient(Client client) {
        ClientService clientService =new ClientService();
        
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(mainContent), 
                                    "Modifier le client", true);
        dialog.setSize(450, 300);
        dialog.setLocationRelativeTo(mainContent);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Champ nom
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Nom:"), gbc);
        gbc.gridx = 1;
        JTextField txtNom = new JTextField(client.getNom(), 20);
        panel.add(txtNom, gbc);

        // Champ prénom
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Prénom:"), gbc);
        gbc.gridx = 1;
        JTextField txtPrenom = new JTextField(client.getPrenom(), 20);
        panel.add(txtPrenom, gbc);

        // Champ date de naissance
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Date Naissance:"), gbc);
        gbc.gridx = 1;
        JTextField txtDateNaissance = new JTextField(client.getDateNaissance().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), 20);
        panel.add(txtDateNaissance, gbc);

        // Champ email
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        JTextField txtEmail = new JTextField(client.getEmail(), 20);
        panel.add(txtEmail, gbc);

        // Boutons
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        JPanel btnPanel = new JPanel(new FlowLayout());

        JButton btnSauvegarder = new JButton("Sauvegarder");
        btnSauvegarder.addActionListener(e -> {
            try {
                // Mettre à jour l'objet client
                client.setNom(txtNom.getText());
                client.setPrenom(txtPrenom.getText());
                client.setDateNaissance(LocalDate.parse(txtDateNaissance.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")).atStartOfDay());
                client.setEmail(txtEmail.getText());

                // Appeler la méthode de modification
                clientService.modifier(client);

                // Fermer le dialogue
                dialog.dispose();

                // Rafraîchir l'affichage
                showMembres();

                JOptionPane.showMessageDialog(mainContent, "Client modifié avec succès!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Erreur lors de la modification: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.addActionListener(e -> dialog.dispose());

        btnPanel.add(btnSauvegarder);
        btnPanel.add(btnAnnuler);
        panel.add(btnPanel, gbc);

        dialog.add(panel);
        dialog.setVisible(true);
    }

    // Méthode pour ouvrir le dialogue de modification d'un membre
    private void ouvrirDialogueModificationMembre(Membre membre, List<Client> clients) {
        MembreService membreService =new MembreService();
        
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(mainContent), 
                                    "Modifier le membre", true);
        dialog.setSize(450, 250);
        dialog.setLocationRelativeTo(mainContent);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // ComboBox pour les clients
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("Client :"), gbc);

        JComboBox<Client> cmbClients = new JComboBox<>();
        for (Client client : clients) {
            cmbClients.addItem(client);
        }
        cmbClients.setSelectedItem(membre.getClient());

        // Renderer pour afficher le nom complet du client
        cmbClients.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, 
                    boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Client) {
                    Client client = (Client) value;
                    setText(client.getNom() + " " + client.getPrenom() + " (ID: " + client.getId() + ")");
                }
                return this;
            }
        });

        gbc.gridx = 1; gbc.gridy = 0; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(cmbClients, gbc);

        // Champ date d'inscription
        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("Date d'inscription :"), gbc);

        JTextField txtDateInscription = new JTextField(membre.getDateInscription().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), 20);
        gbc.gridx = 1; gbc.gridy = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(txtDateInscription, gbc);

        // Boutons
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        JPanel btnPanel = new JPanel(new FlowLayout());

        JButton btnSauvegarder = new JButton("Sauvegarder");
        btnSauvegarder.addActionListener(e -> {
            try {
                // Mettre à jour l'objet membre
                membre.setClient((Client) cmbClients.getSelectedItem());
                membre.setDateInscription(LocalDate.parse(txtDateInscription.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")).atStartOfDay());

                // Appeler la méthode de modification
                membreService.modifier(membre);

                // Fermer le dialogue
                dialog.dispose();

                // Rafraîchir l'affichage
                showMembres();

                JOptionPane.showMessageDialog(mainContent, "Membre modifié avec succès!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Erreur lors de la modification: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.addActionListener(e -> dialog.dispose());

        btnPanel.add(btnSauvegarder);
        btnPanel.add(btnAnnuler);
        panel.add(btnPanel, gbc);

        dialog.add(panel);
        dialog.setVisible(true);
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

        // Services
        MoyenDePaiementService moyenService = new MoyenDePaiementService();
        DemandeInscriptionService demandeService = new DemandeInscriptionService();

        // Panel principal avec BoxLayout vertical
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // === SECTION MOYENS DE PAIEMENT ===

        // Titre "Moyens de Paiement"
        JLabel titleMoyens = new JLabel("Moyens de Paiement");
        titleMoyens.setFont(new Font("Arial", Font.BOLD, 18));
        titleMoyens.setAlignmentX(Component.LEFT_ALIGNMENT);
        titleMoyens.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        mainPanel.add(titleMoyens);

        // Récupération des données des moyens de paiement
        String[] columnsMoyens = {"ID", "Code", "Libellé", "Modifier", "Supprimer"};
        List<MoyenDePaiement> moyensPaiement = moyenService.listerTous();
        Object[][] dataMoyens = new Object[moyensPaiement.size()][5];

        // Remplissage du tableau avec les données de la base
        for (int i = 0; i < moyensPaiement.size(); i++) {
            MoyenDePaiement moyen = moyensPaiement.get(i);
            dataMoyens[i][0] = moyen.getId();
            dataMoyens[i][1] = moyen.getCode();
            dataMoyens[i][2] = moyen.getLibelle();
            dataMoyens[i][3] = "";
            dataMoyens[i][4] = "";
        }

        // Tableau des moyens de paiement
        JTable tableMoyens = new JTable(dataMoyens, columnsMoyens) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Renderer personnalisé pour les colonnes Modifier/Supprimer
        tableMoyens.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
                    boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(activeColor);
                setText("");
                return c;
            }
        });

        tableMoyens.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
                    boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(secondColor);
                setText("");
                return c;
            }
        });

        // Configuration du tableau des moyens de paiement
        tableMoyens.setRowHeight(30);
        tableMoyens.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tableMoyens.setFont(new Font("Arial", Font.PLAIN, 11));

        // Gestionnaire de clic pour les boutons du tableau moyens
        tableMoyens.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableMoyens.rowAtPoint(e.getPoint());
                int col = tableMoyens.columnAtPoint(e.getPoint());

                if (row >= 0) {
                    if (col == 3) { // Colonne "Modifier"
                        MoyenDePaiement moyenAModifier = moyensPaiement.get(row);
                        ouvrirDialogueModificationMoyen(moyenAModifier);
                    } else if (col == 4) { // Colonne "Supprimer"
                        int confirm = JOptionPane.showConfirmDialog(mainContent,
                            "Êtes-vous sûr de vouloir supprimer ce moyen de paiement ?",
                            "Confirmation", JOptionPane.YES_NO_OPTION);
                        if (confirm == JOptionPane.YES_OPTION) {
                            try {
                                String code = (String) tableMoyens.getValueAt(row, 1);
                                moyenService.supprimer(code);
                                JOptionPane.showMessageDialog(mainContent,
                                    "Moyen de paiement supprimé avec succès",
                                    "Succès", JOptionPane.INFORMATION_MESSAGE);
                                showInscription(); // Rafraîchir
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

        // ScrollPane pour le tableau des moyens
        JScrollPane scrollPaneMoyens = new JScrollPane(tableMoyens);
        scrollPaneMoyens.setPreferredSize(new Dimension(800, 150));
        scrollPaneMoyens.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(scrollPaneMoyens);

        // Bouton "Ajouter" pour les moyens de paiement
        JButton btnAjouterMoyen = new JButton("Ajouter");
        btnAjouterMoyen.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnAjouterMoyen.setMaximumSize(new Dimension(100, 30));
        btnAjouterMoyen.addActionListener(e -> ouvrirDialogueAjoutMoyen());

        JPanel panelBtnMoyen = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBtnMoyen.add(btnAjouterMoyen);
        panelBtnMoyen.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(panelBtnMoyen);

        // Espacement entre les sections
        mainPanel.add(Box.createVerticalStrut(30));

        // === SECTION DEMANDES D'INSCRIPTION ===

        // Titre "Demandes d'Inscription"
        JLabel titleDemandes = new JLabel("Demandes d'Inscription");
        titleDemandes.setFont(new Font("Arial", Font.BOLD, 18));
        titleDemandes.setAlignmentX(Component.LEFT_ALIGNMENT);
        titleDemandes.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        mainPanel.add(titleDemandes);

        // Récupération des données des demandes d'inscription
        String[] columnsDemandes = {"ID", "Date Demande", "Date Traitement", "Modifier", "Supprimer"};
        List<DemandeInscription> demandes = demandeService.listerTous();
        Object[][] dataDemandes = new Object[demandes.size()][5];

        // Remplissage du tableau avec les données de la base
        for (int i = 0; i < demandes.size(); i++) {
            DemandeInscription demande = demandes.get(i);
            dataDemandes[i][0] = demande.getId();
            dataDemandes[i][1] = demande.getDateDeDemande() != null ? 
                demande.getDateDeDemande().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "N/A";
            dataDemandes[i][2] = demande.getDateDeTraitement() != null ? 
                demande.getDateDeTraitement().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "En attente";
            dataDemandes[i][3] = "";
            dataDemandes[i][4] = "";
        }

        // Tableau des demandes d'inscription
        JTable tableDemandes = new JTable(dataDemandes, columnsDemandes) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Renderer personnalisé pour les colonnes Modifier/Supprimer des demandes
        tableDemandes.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
                    boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(activeColor);
                setText("");
                return c;
            }
        });

        tableDemandes.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
                    boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(secondColor);
                setText("");
                return c;
            }
        });

        // Configuration du tableau des demandes
        tableDemandes.setRowHeight(30);
        tableDemandes.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tableDemandes.setFont(new Font("Arial", Font.PLAIN, 11));

        // Gestionnaire de clic pour les boutons du tableau demandes
        tableDemandes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableDemandes.rowAtPoint(e.getPoint());
                int col = tableDemandes.columnAtPoint(e.getPoint());

                if (row >= 0) {
                    if (col == 3) { // Colonne "Modifier"
                        DemandeInscription demandeAModifier = demandes.get(row);
                        ouvrirDialogueModificationDemande(demandeAModifier);
                    } else if (col == 4) { // Colonne "Supprimer"
                        int confirm = JOptionPane.showConfirmDialog(mainContent,
                            "Êtes-vous sûr de vouloir supprimer cette demande d'inscription ?",
                            "Confirmation", JOptionPane.YES_NO_OPTION);
                        if (confirm == JOptionPane.YES_OPTION) {
                            try {
                                int id = (Integer) tableDemandes.getValueAt(row, 0);
                                demandeService.supprimer(id);
                                JOptionPane.showMessageDialog(mainContent,
                                    "Demande d'inscription supprimée avec succès",
                                    "Succès", JOptionPane.INFORMATION_MESSAGE);
                                showInscription(); // Rafraîchir
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

        // ScrollPane pour le tableau des demandes
        JScrollPane scrollPaneDemandes = new JScrollPane(tableDemandes);
        scrollPaneDemandes.setPreferredSize(new Dimension(800, 150));
        scrollPaneDemandes.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(scrollPaneDemandes);

        // Bouton "Ajouter" pour les demandes d'inscription
        JButton btnAjouterDemande = new JButton("Ajouter");
        btnAjouterDemande.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnAjouterDemande.setMaximumSize(new Dimension(100, 30));
        btnAjouterDemande.addActionListener(e -> ouvrirDialogueAjoutDemande());

        JPanel panelBtnDemande = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBtnDemande.add(btnAjouterDemande);
        panelBtnDemande.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(panelBtnDemande);

        // Ajouter le panel principal au conteneur
        mainContent.add(mainPanel, BorderLayout.CENTER);

        // Rafraîchir l'affichage
        mainContent.revalidate();
        mainContent.repaint();
    }

    // === MÉTHODES DE DIALOGUE POUR MOYENS DE PAIEMENT ===

    private void ouvrirDialogueAjoutMoyen() {
        MoyenDePaiementService moyenService = new MoyenDePaiementService();

        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(mainContent), 
                                    "Ajouter un moyen de paiement", true);
        dialog.setSize(400, 250);
        dialog.setLocationRelativeTo(mainContent);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Champ ID
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("ID :"), gbc);

        JTextField txtId = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 0; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(txtId, gbc);

        // Champ Code
        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("Code :"), gbc);

        JTextField txtCode = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(txtCode, gbc);

        // Champ Libellé
        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("Libellé :"), gbc);

        JTextField txtLibelle = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 2; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(txtLibelle, gbc);

        // Boutons
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
            String code = txtCode.getText().trim();
            String libelle = txtLibelle.getText().trim();

            if (idText.isEmpty() || code.isEmpty() || libelle.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Tous les champs sont obligatoires!", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int id = Integer.parseInt(idText);
                MoyenDePaiement nouveauMoyen = new MoyenDePaiement();
                nouveauMoyen.setId(id);
                nouveauMoyen.setCode(code);
                nouveauMoyen.setLibelle(libelle);

                moyenService.ajouter(nouveauMoyen);
                JOptionPane.showMessageDialog(dialog, "Moyen de paiement ajouté avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
                showInscription();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "L'ID doit être un nombre!", "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Erreur lors de l'ajout: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnAnnuler.addActionListener(e -> dialog.dispose());

        dialog.add(panel);
        dialog.setVisible(true);
    }

    private void ouvrirDialogueModificationMoyen(MoyenDePaiement moyen) {
        MoyenDePaiementService moyenService = new MoyenDePaiementService();

        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(mainContent), 
                                    "Modifier le moyen de paiement", true);
        dialog.setSize(400, 250);
        dialog.setLocationRelativeTo(mainContent);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Champ ID (lecture seule)
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("ID :"), gbc);

        JTextField txtId = new JTextField(String.valueOf(moyen.getId()), 20);
        txtId.setEditable(false);
        gbc.gridx = 1; gbc.gridy = 0; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(txtId, gbc);

        // Champ Code (lecture seule car c'est la clé pour la modification)
        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("Code :"), gbc);

        JTextField txtCode = new JTextField(moyen.getCode(), 20);
        txtCode.setEditable(false);
        gbc.gridx = 1; gbc.gridy = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(txtCode, gbc);

        // Champ Libellé
        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("Libellé :"), gbc);

        JTextField txtLibelle = new JTextField(moyen.getLibelle(), 20);
        gbc.gridx = 1; gbc.gridy = 2; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(txtLibelle, gbc);

        // Boutons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton btnSauvegarder = new JButton("Sauvegarder");
        JButton btnAnnuler = new JButton("Annuler");

        buttonPanel.add(btnSauvegarder);
        buttonPanel.add(btnAnnuler);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(buttonPanel, gbc);

        // Actions des boutons
        btnSauvegarder.addActionListener(e -> {
            String libelle = txtLibelle.getText().trim();

            if (libelle.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Le libellé est obligatoire!", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                moyen.setLibelle(libelle);
                moyenService.modifier(moyen);
                JOptionPane.showMessageDialog(dialog, "Moyen de paiement modifié avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
                showInscription();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Erreur lors de la modification: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnAnnuler.addActionListener(e -> dialog.dispose());

        dialog.add(panel);
        dialog.setVisible(true);
    }

    // === MÉTHODES DE DIALOGUE POUR DEMANDES D'INSCRIPTION ===

    private void ouvrirDialogueAjoutDemande() {
        DemandeInscriptionService demandeService = new DemandeInscriptionService();

        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(mainContent), 
                                    "Ajouter une demande d'inscription", true);
        dialog.setSize(400, 250);
        dialog.setLocationRelativeTo(mainContent);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Champ Date de demande
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("Date de demande (dd/MM/yyyy) :"), gbc);

        JTextField txtDateDemande = new JTextField(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), 20);
        gbc.gridx = 1; gbc.gridy = 0; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(txtDateDemande, gbc);

        // Champ Date de traitement (optionnel)
        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("Date de traitement (optionnel) :"), gbc);

        JTextField txtDateTraitement = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(txtDateTraitement, gbc);

        // Boutons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton btnSauvegarder = new JButton("Sauvegarder");
        JButton btnAnnuler = new JButton("Annuler");

        buttonPanel.add(btnSauvegarder);
        buttonPanel.add(btnAnnuler);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(buttonPanel, gbc);

        // Actions des boutons
        btnSauvegarder.addActionListener(e -> {
            String dateDemande = txtDateDemande.getText().trim();
            String dateTraitement = txtDateTraitement.getText().trim();

            if (dateDemande.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "La date de demande est obligatoire!", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                DemandeInscription nouvelleDemande = new DemandeInscription();
                nouvelleDemande.setDateDeDemande(LocalDate.parse(dateDemande, DateTimeFormatter.ofPattern("dd/MM/yyyy")).atStartOfDay());

                if (!dateTraitement.isEmpty()) {
                    nouvelleDemande.setDateDeTraitement(LocalDate.parse(dateTraitement, DateTimeFormatter.ofPattern("dd/MM/yyyy")).atStartOfDay());
                }

                demandeService.ajouter(nouvelleDemande);
                JOptionPane.showMessageDialog(dialog, "Demande d'inscription ajoutée avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
                showInscription();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Erreur lors de l'ajout: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnAnnuler.addActionListener(e -> dialog.dispose());

        dialog.add(panel);
        dialog.setVisible(true);
    }

    private void ouvrirDialogueModificationDemande(DemandeInscription demande) {
        DemandeInscriptionService demandeService = new DemandeInscriptionService();

        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(mainContent), 
                                    "Modifier la demande d'inscription", true);
        dialog.setSize(400, 250);
        dialog.setLocationRelativeTo(mainContent);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Champ Date de demande
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("Date de demande :"), gbc);

        JTextField txtDateDemande = new JTextField(demande.getDateDeDemande().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), 20);
        gbc.gridx = 1; gbc.gridy = 0; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(txtDateDemande, gbc);

        // Champ Date de traitement
        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("Date de traitement :"), gbc);

        String dateTraitementStr = demande.getDateDeTraitement() != null ? 
            demande.getDateDeTraitement().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "";
        JTextField txtDateTraitement = new JTextField(dateTraitementStr, 20);
        gbc.gridx = 1; gbc.gridy = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(txtDateTraitement, gbc);

        // Boutons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton btnSauvegarder = new JButton("Sauvegarder");
        JButton btnAnnuler = new JButton("Annuler");

        buttonPanel.add(btnSauvegarder);
        buttonPanel.add(btnAnnuler);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(buttonPanel, gbc);

        // Actions des boutons
        btnSauvegarder.addActionListener(e -> {
            String dateDemande = txtDateDemande.getText().trim();
            String dateTraitement = txtDateTraitement.getText().trim();

            if (dateDemande.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "La date de demande est obligatoire!", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                demande.setDateDeDemande(LocalDate.parse(dateDemande, DateTimeFormatter.ofPattern("dd/MM/yyyy")).atStartOfDay());

                if (!dateTraitement.isEmpty()) {
                    demande.setDateDeTraitement(LocalDate.parse(dateTraitement, DateTimeFormatter.ofPattern("dd/MM/yyyy")).atStartOfDay());
                } else {
                    demande.setDateDeTraitement(null);
                }

                demandeService.modifier(demande);
                JOptionPane.showMessageDialog(dialog, "Demande d'inscription modifiée avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
                showInscription();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Erreur lors de la modification: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnAnnuler.addActionListener(e -> dialog.dispose());

        dialog.add(panel);
        dialog.setVisible(true);
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
