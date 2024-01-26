package Projet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Salle {
    private int id;
    private String batiment;
    private int etage;
    private int numero;

    // Constructeur
    public Salle(int id, String batiment, int etage, int numero) {
        this.id = id;
        this.batiment = batiment;
        this.etage = etage;
        this.numero = numero;
    }
    
    public int getId() {
        return id;
    }
    // Getters and Setters 

    // Méthode pour ajouter une salle dans la base de données
    public static void addSalle(String batiment, int etage, int numero) throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO salle (batiment, etage, numero) VALUES (?, ?, ?)")) {

            preparedStatement.setString(1, batiment);
            preparedStatement.setInt(2, etage);
            preparedStatement.setInt(3, numero);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e; //
        }
    }

    // Méthode pour modifier une salle dans la base de données
    public static void updateSalle(int salleId, String batiment, int etage, int numero) throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE salle SET batiment = ?, etage = ?, numero = ? WHERE id = ?")) {

            preparedStatement.setString(1, batiment);
            preparedStatement.setInt(2, etage);
            preparedStatement.setInt(3, numero);
            preparedStatement.setInt(4, salleId);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e; 
        }
    }

    // Méthode pour supprimer une salle de la base de données
    public static void deleteSalle(int salleId) throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM salle WHERE id = ?")) {

            preparedStatement.setInt(1, salleId);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e; 
        }
    }

    // Méthode pour récupérer toutes les salles depuis la base de données
    public static List<Salle> getAllSalles() {
        List<Salle> salles = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM salle");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String batiment = resultSet.getString("batiment");
                int etage = resultSet.getInt("etage");
                int numero = resultSet.getInt("numero");

                Salle salle = new Salle(id, batiment, etage, numero);
                salles.add(salle);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return salles;
    }
    // Méthode pour récupérer une salle par son ID depuis la base de données
    public static Salle getSalleById(int salleId) {
        Salle salle = null;

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM salle WHERE id = ?");
        ) {
            preparedStatement.setInt(1, salleId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String batiment = resultSet.getString("batiment");
                    int etage = resultSet.getInt("etage");
                    int numero = resultSet.getInt("numero");

                    salle = new Salle(id, batiment, etage, numero);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return salle;
    }

    @Override
    public String toString() {
        return "Salle [id=" + id + ", batiment=" + batiment + ", etage=" + etage + ", numero=" + numero + "]";
    }
}

