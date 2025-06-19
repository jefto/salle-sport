/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui_admin.controller;

import entite.DemandeInscription;
import gui_admin.view.demandeInscription.Edit;
import service.DemandeInscriptionService;

/**
 *
 * @author DevJude
 */
public class DemandeInscriptionController {
    private DemandeInscriptionService service;

    public DemandeInscriptionController() {
        service = new DemandeInscriptionService();
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

    public Edit modifier(DemandeInscription entite) {
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
