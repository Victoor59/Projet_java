package Projet;
import java.util.List;
import java.util.Scanner;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Time;

public class Main {

    public static void main(String[] args) {
        // Afficher toutes les salles
        System.out.println("Liste des salles :");
        List<Salle> salles = Salle.getAllSalles();
        for (Salle salle : salles) {
            System.out.println(salle);
        }

        // Afficher toutes les réservations
        System.out.println("\nListe des réservations :");
        List<Reservation> reservations = Reservation.getAllReservations();
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
        
        Scanner scanner = new Scanner(System.in);

        try {
            while (true) {
                System.out.println("Choisissez une action :");
                System.out.println("1. Ajouter une salle");
                System.out.println("2. Modifier une salle");
                System.out.println("3. Supprimer une salle");
                System.out.println("4. Ne rien faire");
                System.out.println("5. Ajouter une réservation");
                System.out.println("6. Modifier une réservation");
                System.out.println("7. Supprimer une réservation");
                System.out.println("8. Afficher une réservation");

                System.out.print("Votre choix : ");

                int choix;
                while (true) {
                    try {
                        choix = scanner.nextInt();
                        break; // Sortir de la boucle si la saisie est un entier valide
                    } catch (java.util.InputMismatchException e) {
                        // Afficher un message d'erreur et vider le scanner pour éviter une boucle infinie
                        System.out.println("Veuillez saisir un nombre entier.");
                        scanner.nextLine(); // Consommer la nouvelle ligne restante dans le tampon
                    }
                }

                switch (choix) {
                    case 1:
                        // Ajouter une salle
                    	System.out.print("Entrez le bâtiment : ");
                    	scanner.nextLine(); // Consommer la nouvelle ligne restante dans le tampon
                    	String batiment = scanner.nextLine();

                        System.out.print("Entrez l'étage : ");
                        int etage;
                        while (true) {
                            try {
                                etage = scanner.nextInt();
                                break; // Sortir de la boucle si la saisie est un entier valide
                            } catch (java.util.InputMismatchException e) {
                                // Afficher un message d'erreur et vider le scanner pour éviter une boucle infinie
                                System.out.println("Veuillez saisir un nombre entier.");
                                scanner.nextLine(); // Consommer la nouvelle ligne restante dans le tampon
                            }
                        }

                        System.out.print("Entrez le numéro : ");
                        int numero;
                        while (true) {
                            try {
                                numero = scanner.nextInt();
                                break; // Sortir de la boucle si la saisie est un entier valide
                            } catch (java.util.InputMismatchException e) {
                                // Afficher un message d'erreur et vider le scanner pour éviter une boucle infinie
                                System.out.println("Veuillez saisir un nombre entier.");
                                scanner.nextLine(); // Consommer la nouvelle ligne restante dans le tampon
                            }
                        }

                        Salle.addSalle(batiment, etage, numero);
                        System.out.println("Salle ajoutée avec succès.");
                        
                        // Afficher la liste des salles après l'opération
                        List<Salle> salles2 = Salle.getAllSalles();
                        System.out.println("Liste des salles mise à jour :");
                        for (Salle salle2 : salles2) {
                            System.out.println(salle2);
                        }
                        break;

                    case 2:
                        // Modifier une salle
                    	System.out.print("Entrez l'ID de la salle à modifier : ");
                    	int salleIdAModifier;
                    	while (true) {
                    	    try {
                    	    	salleIdAModifier = scanner.nextInt();
                    	        scanner.nextLine(); // Consommer la nouvelle ligne restante dans le tampon
                    	        break; // Sortir de la boucle si la saisie est un entier valide
                    	    } catch (java.util.InputMismatchException e) {
                    	        // Afficher un message d'erreur et vider le scanner pour éviter une boucle infinie
                    	        System.out.println("Veuillez saisir un nombre entier.");
                    	        scanner.nextLine(); // Consommer la nouvelle ligne restante dans le tampon
                    	    }
                    	}
                    	
                    	Salle salleExistante = Salle.getSalleById(salleIdAModifier);
                    	System.out.println("Détails de la salle existante :");
                        System.out.println(salleExistante);
                    	
                    	System.out.print("Entrez le nouveau bâtiment : ");
                    	String nouveauBatiment = scanner.nextLine().trim();

                    	System.out.print("Entrez le nouvel étage : ");
                    	int nouvelEtage;
                    	while (true) {
                    	    try {
                    	        nouvelEtage = scanner.nextInt();
                    	        scanner.nextLine(); // Consommer la nouvelle ligne restante dans le tampon
                    	        break; // Sortir de la boucle si la saisie est un entier valide
                    	    } catch (java.util.InputMismatchException e) {
                    	        // Afficher un message d'erreur et vider le scanner pour éviter une boucle infinie
                    	        System.out.println("Veuillez saisir un nombre entier.");
                    	        scanner.nextLine(); // Consommer la nouvelle ligne restante dans le tampon
                    	    }
                    	}

                        System.out.print("Entrez le nouveau numéro : ");
                        int nouveauNumero = scanner.nextInt();

                        Salle.updateSalle(salleIdAModifier, nouveauBatiment, nouvelEtage, nouveauNumero);
                        System.out.println("Salle modifiée avec succès.");
                        // Afficher la liste des salles après l'opération
                        List<Salle> salles4 = Salle.getAllSalles();
                        System.out.println("Liste des salles mise à jour :");
                        for (Salle salle4 : salles4) {
                            System.out.println(salle4);
                        }
                        break;
                        
                    case 3:
                        // Supprimer une salle
                        System.out.print("Entrez l'ID de la salle à supprimer : ");
                        int salleASupprimer = scanner.nextInt();

                        Salle.deleteSalle(salleASupprimer);
                        System.out.println("Salle supprimée avec succès.");
                        // Afficher la liste des salles après l'opération
                        List<Salle> salles5 = Salle.getAllSalles();
                        System.out.println("Liste des salles mise à jour :");
                        for (Salle salle5 : salles5) {
                            System.out.println(salle5);
                        }
                        break;

                    case 4:
                        // Ne rien faire
                        System.out.println("Aucune action effectuée.");
                        break;
                    
                    case 5:
                        // Ajouter une réservation
                        System.out.print("Entrez la date (AAAA-MM-JJ) : ");
                        String dateString = scanner.next();
                        Date date = Date.valueOf(dateString);

                        System.out.print("Entrez l'heure de début (HH:MM:SS) : ");
                        String heureDepString = scanner.next();
                        Time heureDep = Time.valueOf(heureDepString);

                        System.out.print("Entrez l'heure de fin (HH:MM:SS) : ");
                        String heureFinString = scanner.next();
                        Time heureFin = Time.valueOf(heureFinString);

                        System.out.print("Entrez la promo : ");
                        scanner.nextLine(); // Consommer le retour à la ligne précédent

                        String promo = scanner.nextLine();

                        System.out.print("Entrez le responsable : ");
                        String responsable = scanner.nextLine();

                        // Affiche la liste des salles pour que l'utilisateur choisisse une salle
                        List<Salle> salles3 = Reservation.getSallesDisponibles(date, heureDep, heureFin);
                        System.out.println("Liste des salles disponibles :");
                        for (Salle salle3 : salles3) {
                            System.out.println(salle3);
                        }

                        System.out.print("Choisissez l'ID de la salle : ");
                        int salleId = scanner.nextInt();
                        Salle salleChoisie = Salle.getSalleById(salleId);

                        Reservation.addReservation(date, heureDep, heureFin, promo, responsable, salleChoisie);
                        
                        System.out.println("\nListe des réservations :");
                        List<Reservation> reservations2 = Reservation.getAllReservations();
                        for (Reservation reservation2 : reservations2) {
                            System.out.println(reservation2);
                        }
                        
                        break;
                        
                    case 6:
                    	// Modifier une réservation
                        System.out.print("Entrez l'ID de la réservation à modifier : ");
                        int reservationId = scanner.nextInt();

                        Reservation reservationExistante = Reservation.getReservationById(reservationId);
                        if (reservationExistante == null) {
                            System.out.println("Aucune réservation trouvée avec l'ID spécifié.");
                            break;
                        }

                        System.out.println("Détails de la réservation existante :");
                        System.out.println(reservationExistante);

                        // Saisir les nouvelles informations de la réservation
                        System.out.print("Entrez la nouvelle date (AAAA-MM-JJ) : ");
                        String dateString2 = scanner.next();
                        Date nouvelleDate = java.sql.Date.valueOf(dateString2);

                        System.out.print("Entrez la nouvelle heure de début (HH:MM:SS) : ");
                        String heureDepString2 = scanner.next();
                        Time nouvelleHeureDep = Time.valueOf(heureDepString2);

                        System.out.print("Entrez la nouvelle heure de fin (HH:MM:SS) : ");
                        String heureFinString2 = scanner.next();
                        Time nouvelleHeureFin = Time.valueOf(heureFinString2);

                        System.out.print("Entrez la nouvelle promo : ");
                        scanner.nextLine(); // Consommer le retour à la ligne précédent
                        String nouvellePromo = scanner.nextLine();

                        System.out.print("Entrez le nouveau responsable : ");
                        String nouveauResponsable = scanner.nextLine();

                        // Choisir une nouvelle salle
                        System.out.println("Choisissez une nouvelle salle :");
                        List<Salle> salles8 = Reservation.getSallesDisponibles2(reservationId,nouvelleDate, nouvelleHeureDep, nouvelleHeureFin);
                        for (Salle salle8 : salles8) {
                            System.out.println(salle8);
                        }

                        System.out.print("Entrez l'ID de la nouvelle salle : ");
                        int nouvelleSalleId = scanner.nextInt();

                        // Modifier la réservation avec la nouvelle salle {
                        Salle nouvelleSalle = Salle.getSalleById(nouvelleSalleId);

                        Reservation.updateReservation(reservationId, nouvelleDate, nouvelleHeureDep, nouvelleHeureFin, nouvellePromo, nouveauResponsable, nouvelleSalle);
                        List<Reservation> reservations3 = Reservation.getAllReservations();
                        for (Reservation reservation3 : reservations3) {
                            System.out.println(reservation3);
                        }
                        break;
                    
                    case 7:
                        // Supprimer une réservation
                        System.out.print("Entrez l'ID de la réservation à supprimer : ");
                        int reservationIdASupprimer = scanner.nextInt();

                        try {
                            Reservation reservationASupprimer = Reservation.getReservationById(reservationIdASupprimer);

                            if (reservationASupprimer == null) {
                                System.out.println("Aucune réservation trouvée avec l'ID spécifié.");
                                break;
                            }

                            System.out.println("Détails de la réservation à supprimer :");
                            System.out.println(reservationASupprimer);

                            System.out.print("Confirmez-vous la suppression de cette réservation ? (O/N) : ");
                            String confirmation = scanner.next();

                            if (confirmation.equalsIgnoreCase("O")) {
                                Reservation.deleteReservation(reservationIdASupprimer);
                            } else {
                                System.out.println("Suppression annulée.");
                            }
                        } catch (SQLException e) {
                            System.out.println("Échec de la suppression de la réservation. Erreur : " + e.getMessage());
                        }
                        List<Reservation> reservations4 = Reservation.getAllReservations();
                        for (Reservation reservation4 : reservations4) {
                            System.out.println(reservation4);
                        }
                        break;
                        
                    case 8:
                    	// Afficher une réservation en particulier
                    	System.out.println("\nListe des réservations :");
                        List<Reservation> reservations5 = Reservation.getAllReservations();
                        for (Reservation reservation5 : reservations5) {
                            System.out.println(reservation5);
                        }
                        
                        System.out.print("Entrez l'ID de la réservation que vous voulez afficher : ");
                        int reservationId3 = scanner.nextInt();
                        Reservation reservationExistante3 = Reservation.getReservationById(reservationId3);
                        System.out.println(reservationExistante3);

                    	break;
                       
                    default:
                        System.out.println("Choix non valide.");
                }              

                System.out.print("Voulez-vous continuer ? (O/N) : ");
                String continuer = scanner.next().trim().toLowerCase();
                if (!continuer.equals("o")) {
                    break; // Sortir de la boucle principale si l'utilisateur ne veut pas continuer
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de l'opération : " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}