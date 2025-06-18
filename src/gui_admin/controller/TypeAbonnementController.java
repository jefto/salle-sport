/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui_admin.controller;

import gui_admin.view.type_abonnement.Edit;
import entite.TypeAbonnement;
import service.TypeAbonnementService;

/**
 *
 * @author DevJude
 */
public class TypeAbonnementController {
    private TypeAbonnementService service ;

    public TypeAbonnementController() {
        service = new TypeAbonnementService();
    }
    
    public Edit ajouter(){
        Edit edit = new Edit();
        edit.afficher();
        edit.getsaveButton().addActionListener(e -> {
            edit.init();
            service.ajouter(edit.getEntite());
            edit.dispose();
        });
        return edit;
    }
    
    public Edit modifier(TypeAbonnement entite){
        Edit edit = new Edit(entite);
        edit.afficher();
        edit.getsaveButton().addActionListener(e -> {
            edit.init();
            service.modifier(edit.getEntite());
            edit.dispose();
        });
        return edit;
    }
    
    
}
