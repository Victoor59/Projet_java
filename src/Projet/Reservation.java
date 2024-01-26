package Projet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Reservation {
    private int id;
    private Date date;
    private Time heureDep;
    private Time heureFin;
    private String promo;
    private String responsable;
    private Salle salle;

    // Constructeur (ajustez selon votre schéma de base de données)
    public Reservation(int id, Date date, Time heureDep, Time heureFin, String promo, String responsable, Salle salle) {
        this.id = id;
        this.date = date;
        this.heureDep = heureDep;
        this.heureFin = heureFin;
        this.promo = promo;
        this.responsable = responsable;
        this.salle = salle;
    }
    
    
    // Getters and Setters (ajustez selon votre schéma de base de données)
 // Méthode pour ajouter une réservation dans la base de données
    public static void addReservation(Date date, Time heureDep, Time heureFin, String promo, String responsable, Salle salle) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO reservation (date, heure_dep, heure_fin, promo, responsable, salle_id) VALUES (?, ?, ?, ?, ?, ?)")) {

            preparedStatement.setDate(1, date);
            preparedStatement.setTime(2, heureDep);
            preparedStatement.setTime(3, heureFin);
            preparedStatement.setString(4, promo);
            preparedStatement.setString(5, responsable);
            preparedStatement.setInt(6, salle.getId());

            preparedStatement.executeUpdate();
            System.out.println("Réservation ajoutée avec succès.");

        } catch (SQLException e) {
            System.out.println("Échec de la réservation. Erreur : " + e.getMessage());
        }
    }

    // Méthode pour modifier une réservation dans la base de données
    public static void updateReservation(int id, Date date, Time heureDep, Time heureFin, String promo, String responsable, Salle salle) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE reservation SET date = ?, heure_dep = ?, heure_fin = ?, promo = ?, responsable = ?, salle_id = ? WHERE id = ?")) {

            preparedStatement.setDate(1, date);
            preparedStatement.setTime(2, heureDep);
            preparedStatement.setTime(3, heureFin);
            preparedStatement.setString(4, promo);
            preparedStatement.setString(5, responsable);
            preparedStatement.setInt(6, salle.getId());
            preparedStatement.setInt(7, id);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Réservation modifiée avec succès.");
            } else {
                System.out.println("Aucune réservation modifiée. Vérifiez l'ID de la réservation.");
            }

        } catch (SQLException e) {
        	System.out.println("Échec de la modification. Erreur : " + e.getMessage());        }
    }

    // Méthode pour supprimer une réservation de la base de données
    public static void deleteReservation(int id) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM reservation WHERE id = ?")) {

            preparedStatement.setInt(1, id);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Réservation supprimée avec succès.");
            } else {
                System.out.println("Aucune réservation supprimée. Vérifiez l'ID de la réservation.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static Reservation getReservationById(int reservationId) throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM reservation WHERE id = ?")) {

            preparedStatement.setInt(1, reservationId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    Date date = resultSet.getDate("date");
                    Time heureDep = resultSet.getTime("heure_dep");
                    Time heureFin = resultSet.getTime("heure_fin");
                    String promo = resultSet.getString("promo");
                    String responsable = resultSet.getString("responsable");

                    // Récupérer l'ID de la salle associée à la réservation
                    int salleId = resultSet.getInt("salle_id");

                    // Récupérer les détails de la salle à partir de la base de données
                    Salle salle = Salle.getSalleById(salleId);

                    return new Reservation(id, date, heureDep, heureFin, promo, responsable, salle);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Retourne null si aucune réservation trouvée
    }
    
    public static List<Salle> getSallesDisponibles(Date date, Time heureDep, Time heureFin) {
        List<Salle> sallesDisponibles = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM salle WHERE id NOT IN ("
                             + "SELECT salle_id FROM reservation "
                             + "WHERE date = ? AND "
                             + "((heure_dep >= ? AND heure_dep < ?) OR (heure_fin > ? AND heure_fin <= ?) OR (heure_dep <= ? AND heure_fin >= ?))"
                             + ")"
        )) {
            preparedStatement.setDate(1, date);
            preparedStatement.setTime(2, heureDep);
            preparedStatement.setTime(3, heureFin);
            preparedStatement.setTime(4, heureDep);
            preparedStatement.setTime(5, heureFin);
            preparedStatement.setTime(6, heureDep);
            preparedStatement.setTime(7, heureFin);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String batiment = resultSet.getString("batiment");
                    int etage = resultSet.getInt("etage");
                    int numero = resultSet.getInt("numero");

                    Salle salle = new Salle(id, batiment, etage, numero);
                    sallesDisponibles.add(salle);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sallesDisponibles;
    }
    public static List<Salle> getSallesDisponibles2(int reservationId, Date date, Time heureDep, Time heureFin) {
        List<Salle> sallesDisponibles = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM salle WHERE id NOT IN ("
                             + "SELECT salle_id FROM reservation "
                             + "WHERE date = ? AND "
                             + "((heure_dep >= ? AND heure_dep < ?) OR (heure_fin > ? AND heure_fin <= ?) OR (heure_dep <= ? AND heure_fin >= ?))"
                             + " AND id <> ?"  // Exclure la salle originale lors de la modification
                             + ")"
        )) {
            preparedStatement.setDate(1, date);
            preparedStatement.setTime(2, heureDep);
            preparedStatement.setTime(3, heureFin);
            preparedStatement.setTime(4, heureDep);
            preparedStatement.setTime(5, heureFin);
            preparedStatement.setTime(6, heureDep);
            preparedStatement.setTime(7, heureFin);
            preparedStatement.setInt(8, reservationId); // ID de la réservation en cours de modification

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String batiment = resultSet.getString("batiment");
                    int etage = resultSet.getInt("etage");
                    int numero = resultSet.getInt("numero");

                    Salle salle = new Salle(id, batiment, etage, numero);
                    sallesDisponibles.add(salle);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sallesDisponibles;
    }
    
    
    // Méthode pour récupérer toutes les réservations depuis la base de données
    public static List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM reservation");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Date date = resultSet.getDate("date");
                Time heureDep = resultSet.getTime("heure_dep");
                Time heureFin = resultSet.getTime("heure_fin");
                String promo = resultSet.getString("promo");
                String responsable = resultSet.getString("responsable");

                // Récupérer l'ID de la salle associée à la réservation
                int salleId = resultSet.getInt("salle_id");

                // Récupérer les détails de la salle à partir de la base de données
                Salle salle = Salle.getSalleById(salleId); 

                Reservation reservation = new Reservation(id, date, heureDep, heureFin, promo, responsable, salle);
                reservations.add(reservation);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservations;
    }

    @Override
    public String toString() {
        return "Reservation [id=" + id + ", date=" + date + ", heureDep=" + heureDep + ", heureFin=" + heureFin +
               ", promo=" + promo + ", responsable=" + responsable + ", salle=" + salle + "]";
    }
}
