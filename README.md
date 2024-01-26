```markdown
# Projet de Réservation de Salles

## Description
Ce projet Java est une application de gestion de réservations de salles. Elle permet aux utilisateurs d'ajouter, de modifier, de supprimer des salles et des réservations, ainsi que de visualiser les salles disponibles et les réservations actuelles.

## Fonctionnalités
- **Gestion des Salles**: Ajout, modification et suppression de salles.
- **Gestion des Réservations**: Ajout, modification et suppression de réservations.
- **Affichage des Salles**: Afficher toutes les salles disponibles.
- **Affichage des Réservations**: Liste de toutes les réservations.

## Comment Utiliser
1. **Cloner le projet**:
   git clone [url-du-repo]

2. **Compiler et Exécuter le programme**:
   javac Main.java
   java Main

3. Ajouter le connecteur `mysql-connector-j-8.0.31.jar` au projet
## Structure du Projet
- `Main.java`: Point d'entrée de l'application. Gère l'interface utilisateur et les interactions.
- `Salle.java`: Classe de gestion des salles, contient des méthodes CRUD pour les salles.
- `Reservation.java`: Classe de gestion des réservations, contient des méthodes CRUD pour les réservations.
- `DatabaseConnector.java`: Gère les connexions à la base de données.

## Technologies Utilisées
- Java
- JDBC pour la connexion à la base de données

## Configuration Requise
- Java JDK 8 ou supérieur
- Serveur de base de données SQL (MySQL, PostgreSQL, etc.)

## Perspectives d'Améliorations
1. **Interface Graphique**: Développer une interface graphique pour améliorer l'expérience utilisateur.
2. **Authentification**: Ajouter un système d'authentification pour sécuriser l'accès aux fonctionnalités.
3. **Amélioration des Performances**: Optimiser les requêtes SQL et la gestion des connexions à la base de données.
4. **Rapports et Statistiques**: Implémenter la génération de rapports sur les réservations et l'utilisation des salles.
5. **Intégration Calendrier**: Intégrer un calendrier pour une visualisation et une gestion plus aisée des réservations.
6. **Notifications**: Mettre en place un système de notifications pour informer les utilisateurs des modifications de réservation.
7. **Tests Unitaires**: Écrire des tests unitaires pour assurer la fiabilité et la stabilité de l'application.

```
