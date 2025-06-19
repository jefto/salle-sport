/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui_admin.controller;

import entite.Equipement;
import gui_admin.view.equipement.Edit;
import service.EquipementService;

/**
 *
    * @author TCHAMIE
 */
public class EquipementController {
    private EquipementService service;

    public EquipementController() {
        service = new EquipementService();
    }

    public Edit ajouter() {
        Edit edit = new Edit(); // Création d'une nouvelle vue
        edit.afficher();

        edit.getsaveButton().addActionListener(e -> {
            edit.init(); // Récupération des données depuis la vue
            service.ajouter(edit.getEntite()); // Appel au service
            edit.dispose(); // Fermeture de la fenêtre
        });

        return edit;
    }

    public Edit modifier(Equipement entite) {
        Edit edit = new Edit(entite); // Remplissage avec les données existantes
        edit.afficher();

        edit.getsaveButton().addActionListener(e -> {
            edit.init();
            service.modifier(edit.getEntite());
            edit.dispose();
        });

        return edit;
    }
}
