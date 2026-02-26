package Pn.metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Notification {
    private UUID id;
    private String message;
    private UUID utilisateur_id;
    private LocalDateTime date_envoi;
    private String statut;

    // Getters et setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public UUID getUtilisateur_id() { return utilisateur_id; }
    public void setUtilisateur_id(UUID utilisateur_id) { this.utilisateur_id = utilisateur_id; }

    public LocalDateTime getDate_envoi() { return date_envoi; }
    public void setDate_envoi(LocalDateTime date_envoi) { this.date_envoi = date_envoi; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    BDQUEueLess db = new BDQUEueLess();

    // Ajouter une notification
    public void AddNotification(Notification notification) {
        String sql = "INSERT INTO notifications(message, utilisateur_id, statut) VALUES (?,?,?)";
        Connection cnx = null;
        cnx = db.seconnecter();
        PreparedStatement pst;
        int i = 0;

        try {
            pst = cnx.prepareStatement(sql);
            pst.setString(1, notification.getMessage());
            pst.setObject(2, notification.getUtilisateur_id()); // peut être null
            pst.setString(3, notification.getStatut());

            i = pst.executeUpdate();
            if (i != 0)
                System.out.println("Notification ajoutée avec succès !!");
            else
                System.out.println("Notification non ajoutée !!");
            cnx.close();
            pst.close();

        } catch (SQLException e) {
            System.out.println("Problème d'insertion de la notification");
            e.printStackTrace();
        }
    }

    // Récupérer toutes les notifications
    public List<Notification> getNotifications() {
        List<Notification> liste = new ArrayList<>();
        String sql = "SELECT * FROM notifications";
        Connection cnx = null;
        cnx = db.seconnecter();
        Statement st;
        ResultSet rs;

        try {
            st = cnx.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Notification n = new Notification();
                n.setId((UUID) rs.getObject("id"));
                n.setMessage(rs.getString("message"));
                n.setUtilisateur_id((UUID) rs.getObject("utilisateur_id"));
                n.setDate_envoi(rs.getObject("date_envoi", LocalDateTime.class));
                n.setStatut(rs.getString("statut"));
                liste.add(n);
            }
            System.out.println("Liste des notifications récupérée !!");
            cnx.close();
            st.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Problème lors de la récupération des notifications !!");
            e.printStackTrace();
        }
        return liste;
    }

    // Mettre à jour une notification
    public void UpdateNotification(Notification notification, UUID id_notification) {
        String sql = "UPDATE notifications SET message=?, utilisateur_id=?, statut=? WHERE id=?";
        Connection cnx = null;
        cnx = db.seconnecter();
        PreparedStatement pst;
        int i = 0;

        try {
            pst = cnx.prepareStatement(sql);
            pst.setString(1, notification.getMessage());
            pst.setObject(2, notification.getUtilisateur_id());
            pst.setString(3, notification.getStatut());
            pst.setObject(4, id_notification);

            i = pst.executeUpdate();
            if (i != 0)
                System.out.println("Notification modifiée avec succès !!");
            else
                System.out.println("Notification non modifiée !!");
            cnx.close();
            pst.close();

        } catch (SQLException e) {
            System.out.println("Problème lors de la modification de la notification");
            e.printStackTrace();
        }
    }

    // Supprimer une notification
    public void DeleteNotification(UUID id_notification) {
        String sql = "DELETE FROM notifications WHERE id=?";
        Connection cnx = null;
        cnx = db.seconnecter();
        PreparedStatement pst;
        int i = 0;

        try {
            pst = cnx.prepareStatement(sql);
            pst.setObject(1, id_notification);

            i = pst.executeUpdate();
            if (i != 0)
                System.out.println("Notification supprimée avec succès !!");
            else
                System.out.println("Notification non supprimée !!");
            cnx.close();
            pst.close();

        } catch (SQLException e) {
            System.out.println("Problème lors de la suppression de la notification");
            e.printStackTrace();
        }
    }

    // Récupérer une notification par son ID
    public Notification getNotificationById(UUID id_notification) {
        Notification n = new Notification();
        String sql = "SELECT * FROM notifications WHERE id=?";
        Connection cnx = null;
        cnx = db.seconnecter();
        PreparedStatement pst;
        ResultSet rs;

        try {
            pst = cnx.prepareStatement(sql);
            pst.setObject(1, id_notification);
            rs = pst.executeQuery();
            if (rs.next()) {
                n.setId((UUID) rs.getObject("id"));
                n.setMessage(rs.getString("message"));
                n.setUtilisateur_id((UUID) rs.getObject("utilisateur_id"));
                n.setDate_envoi(rs.getObject("date_envoi", LocalDateTime.class));
                n.setStatut(rs.getString("statut"));
            }
            rs.close();
            pst.close();
            cnx.close();
            System.out.println("Récupération par ID effectuée !!");
        } catch (SQLException e) {
            System.out.println("Problème lors de la récupération par ID !!");
            e.printStackTrace();
        }
        return n;
    }
}