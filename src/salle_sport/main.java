/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package salle_sport;

import entite.Abonnement;
import entite.Client;
import entite.DemandeInscription;
import entite.Equipement;
import entite.Membre;
import entite.MoyenDePaiement;
import entite.Paiement;
import entite.Salle;
import entite.Seance;
import entite.Ticket;
import entite.TypeAbonnement;
import gui_admin.controller.TypeAbonnementController;
import java.time.LocalDateTime;
import service.AbonnementService;
import service.ClientService;
import service.DemandeInscriptionService;
import service.EquipementService;
import service.HoraireService;
import service.MembreService;
import service.MoyenDePaiementService;
import service.PaiementService;
import service.SalleService;
import service.SeanceService;
import service.TicketService;
import service.TypeAbonnementService;

/**
 *
 * @author DevJude
 */
public class main {
    
    /**
     * @param args the command line arguments
     */
    
    private static TypeAbonnementService typeAbonnementService;
    private static MembreService membreService;
    private static PaiementService paiementService;
    private static MoyenDePaiementService moyenDePaiementService;
    private static EquipementService equipementService;
    private static HoraireService horaireService;
    private static DemandeInscriptionService demandeInscriptionService;
    private static TicketService ticketService;
    private static AbonnementService abonnementService; 
    private static ClientService clientService;
    private static SalleService salleService;
    private static SeanceService seanceService;
    
    static{
        
        typeAbonnementService = new TypeAbonnementService();
        membreService = new MembreService();
        paiementService = new PaiementService();
        moyenDePaiementService = new MoyenDePaiementService();
        equipementService =new EquipementService();
        horaireService = new HoraireService();
        demandeInscriptionService = new DemandeInscriptionService();
        ticketService = new TicketService();
        abonnementService = new AbonnementService();
        clientService = new ClientService();
        salleService = new SalleService() ;
        seanceService = new SeanceService();
        
    }
    
    public static void main(String[] args) { 
        
        TypeAbonnement typeAbonnement =  new TypeAbonnement("HEBDO", "Abonnement hebdomendaire", 2000);
        //typeAbonnementService.ajouter(typeAbonnement);
        
        Client client = new Client(1, "Jefto", "2.0", LocalDateTime.of(2024, 6, 5, 10, 30), "jefto@gmail.com");
        //clientService.ajouter(client);
        
        DemandeInscription demandeInscription = new DemandeInscription(1, LocalDateTime.of(2024, 6, 5, 10, 30), LocalDateTime.of(2024, 6, 10, 16, 0), client);
        //demandeInscriptionService.ajouter(demandeInscription);
        
       
        Membre membre = new Membre(1, LocalDateTime.now(), client);
        //membreService.ajouter(membre);
        
         
        MoyenDePaiement moyenDePaiement = new MoyenDePaiement("001L", "Premier Paiement");
        //moyenDePaiementService.ajouter(moyenDePaiement);
        
        Paiement paiement = new Paiement(1, LocalDateTime.now(), 100000, moyenDePaiement);
        //paiementService.ajouter(paiement);
        
        Abonnement abonnement = new Abonnement(1, LocalDateTime.of(2024, 6, 5, 10, 30), LocalDateTime.of(2024, 6, 10, 16, 0),typeAbonnement, paiement , membre);
        //abonnementService.ajouter(abonnement);
        
        Salle salle = new Salle(1, "Salle 1", "Salle de musculation");
        //salleService.ajouter(salle);
        
        Equipement equipement = new Equipement(1, "Alter", "Bout de poids pour chaques mains", salle);
        //equipementService.ajouter(equipement);
        
        Seance seance = new Seance(1, LocalDateTime.of(2024, 6, 5, 10, 30), LocalDateTime.of(2024, 6, 10, 16, 0),membre ,salle);
        //seanceService.ajouter(seance);
        
        Ticket ticket = new Ticket(1, 1, 5000, client);
        //ticketService.ajouter(ticket);
        
       /*MoyenDePaiement moyenPaiement = new MoyenDePaiement("ECO001","Abonnement hebdomadaire");
       TypeAbonnement typeAbonnement = new TypeAbonnement("ANNUL","Abonnement annuel",5000);
       Salle salle = new Salle("yoga","posture du codra");
       Paiement paiement = new Paiement(1, LocalDateTime.now(), 100, moyenPaiement);
       Client client = new Client (1,"Jude","Doe",LocalDateTime.now(),"doe@gmail");
       
       
        TypeAbonnementController controller = new TypeAbonnementController();
        JFrame fenetre = new JFrame();
        fenetre.setSize(400, 400);
        
        Container c = fenetre.getContentPane();
        c.setLayout(new BorderLayout());
        c.add(controller.edit());
        
        fenetre.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        fenetre.setVisible(true);*/
       
        TypeAbonnementController controller = new TypeAbonnementController();
        //controller.edit();
        TypeAbonnementService service =  new TypeAbonnementService();
        TypeAbonnement entite = service.trouver("HEBDO");
        controller.modifier(entite);

       
        
        
    }
}
