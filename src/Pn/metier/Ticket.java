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

public class Ticket {
    private UUID id;
    private int numero;
    private String statut;
    private int position;
    private int temps_estime;
    private String code_suivi;
    private String qrcode;
    private String telephonetemporair;
    private UUID client_id;
    private UUID service_id;
    private UUID guiche_id;
    private LocalDateTime created_at;

    // Getters et setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    public int getPosition() { return position; }
    public void setPosition(int position) { this.position = position; }

    public int getTemps_estime() { return temps_estime; }
    public void setTemps_estime(int temps_estime) { this.temps_estime = temps_estime; }

    public String getCode_suivi() { return code_suivi; }
    public void setCode_suivi(String code_suivi) { this.code_suivi = code_suivi; }

    public String getQrcode() { return qrcode; }
    public void setQrcode(String qrcode) { this.qrcode = qrcode; }

    public String getTelephonetemporair() { return telephonetemporair; }
    public void setTelephonetemporair(String telephonetemporair) { this.telephonetemporair = telephonetemporair; }

    public UUID getClient_id() { return client_id; }
    public void setClient_id(UUID client_id) { this.client_id = client_id; }

    public UUID getService_id() { return service_id; }
    public void setService_id(UUID service_id) { this.service_id = service_id; }

    public UUID getGuiche_id() { return guiche_id; }
    public void setGuiche_id(UUID guiche_id) { this.guiche_id = guiche_id; }

    public LocalDateTime getCreated_at() { return created_at; }
    public void setCreated_at(LocalDateTime created_at) { this.created_at = created_at; }

    BDQUEueLess db = new BDQUEueLess();

    // Ajouter un ticket
    public void AddTicket(Ticket ticket) {
        String sql = "INSERT INTO tickets(numero, statut, position, temps_estime, code_suivi, qrcode, telephonetemporair, client_id, service_id, guiche_id) VALUES (?,?,?,?,?,?,?,?,?,?)";
        Connection cnx = null;
        cnx = db.seconnecter();
        PreparedStatement pst;
        int i = 0;

        try {
            pst = cnx.prepareStatement(sql);
            pst.setInt(1, ticket.getNumero());
            pst.setString(2, ticket.getStatut());
            pst.setInt(3, ticket.getPosition());
            pst.setInt(4, ticket.getTemps_estime());
            pst.setString(5, ticket.getCode_suivi());
            pst.setString(6, ticket.getQrcode());
            pst.setString(7, ticket.getTelephonetemporair());
            pst.setObject(8, ticket.getClient_id());
            pst.setObject(9, ticket.getService_id());
            pst.setObject(10, ticket.getGuiche_id());

            i = pst.executeUpdate();
            if (i != 0)
                System.out.println("Ticket ajouté avec succès !!");
            else
                System.out.println("Ticket non ajouté !!");
            cnx.close();
            pst.close();

        } catch (SQLException e) {
            System.out.println("Problème d'insertion du ticket");
            e.printStackTrace();
        }
    }

    // Récupérer tous les tickets
    public List<Ticket> getTickets() {
        List<Ticket> liste = new ArrayList<>();
        String sql = "SELECT * FROM tickets";
        Connection cnx = null;
        cnx = db.seconnecter();
        Statement st;
        ResultSet rs;

        try {
            st = cnx.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Ticket t = new Ticket();
                t.setId((UUID) rs.getObject(1));
                t.setNumero(rs.getInt(2));
                t.setStatut(rs.getString(3));
                t.setPosition(rs.getInt(4));
                t.setTemps_estime(rs.getInt(5));
                t.setCode_suivi(rs.getString(6));
                t.setQrcode(rs.getString(7));
                t.setTelephonetemporair(rs.getString(8));
                t.setClient_id((UUID) rs.getObject(9));
                t.setService_id((UUID) rs.getObject(10));
                t.setGuiche_id((UUID) rs.getObject(11));
                t.setCreated_at(rs.getObject(12, LocalDateTime.class));
                liste.add(t);
            }
            System.out.println("Liste des tickets récupérée !!");
            cnx.close();
            st.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Problème lors de la récupération des tickets !!");
            e.printStackTrace();
        }
        return liste;
    }

    // Mettre à jour un ticket
    public void UpdateTicket(Ticket ticket, UUID id_ticket) {
        String sql = "UPDATE tickets SET numero=?, statut=?, position=?, temps_estime=?, code_suivi=?, qrcode=?, telephonetemporair=?, client_id=?, service_id=?, guiche_id=? WHERE id=?";
        Connection cnx = null;
        cnx = db.seconnecter();
        PreparedStatement pst;
        int i = 0;

        try {
            pst = cnx.prepareStatement(sql);
            pst.setInt(1, ticket.getNumero());
            pst.setString(2, ticket.getStatut());
            pst.setInt(3, ticket.getPosition());
            pst.setInt(4, ticket.getTemps_estime());
            pst.setString(5, ticket.getCode_suivi());
            pst.setString(6, ticket.getQrcode());
            pst.setString(7, ticket.getTelephonetemporair());
            pst.setObject(8, ticket.getClient_id());
            pst.setObject(9, ticket.getService_id());
            pst.setObject(10, ticket.getGuiche_id());
            pst.setObject(11, id_ticket);

            i = pst.executeUpdate();
            if (i != 0)
                System.out.println("Ticket modifié avec succès !!");
            else
                System.out.println("Ticket non modifié !!");
            cnx.close();
            pst.close();

        } catch (SQLException e) {
            System.out.println("Problème lors de la modification du ticket");
            e.printStackTrace();
        }
    }

    // Supprimer un ticket
    public void DeleteTicket(UUID id_ticket) {
        String sql = "DELETE FROM tickets WHERE id=?";
        Connection cnx = null;
        cnx = db.seconnecter();
        PreparedStatement pst;
        int i = 0;

        try {
            pst = cnx.prepareStatement(sql);
            pst.setObject(1, id_ticket);

            i = pst.executeUpdate();
            if (i != 0)
                System.out.println("Ticket supprimé avec succès !!");
            else
                System.out.println("Ticket non supprimé !!");
            cnx.close();
            pst.close();

        } catch (SQLException e) {
            System.out.println("Problème lors de la suppression du ticket");
            e.printStackTrace();
        }
    }

    // Récupérer un ticket par son ID
    public Ticket getTicketById(UUID id_ticket) {
        Ticket t = new Ticket();
        String sql = "SELECT * FROM tickets WHERE id=?";
        Connection cnx = null;
        cnx = db.seconnecter();
        PreparedStatement pst;
        ResultSet rs;

        try {
            pst = cnx.prepareStatement(sql);
            pst.setObject(1, id_ticket);
            rs = pst.executeQuery();
            if (rs.next()) {
                t.setId((UUID) rs.getObject("id"));
                t.setNumero(rs.getInt("numero"));
                t.setStatut(rs.getString("statut"));
                t.setPosition(rs.getInt("position"));
                t.setTemps_estime(rs.getInt("temps_estime"));
                t.setCode_suivi(rs.getString("code_suivi"));
                t.setQrcode(rs.getString("qrcode"));
                t.setTelephonetemporair(rs.getString("telephonetemporair"));
                t.setClient_id((UUID) rs.getObject("client_id"));
                t.setService_id((UUID) rs.getObject("service_id"));
                t.setGuiche_id((UUID) rs.getObject("guiche_id"));
                t.setCreated_at(rs.getObject("created_at", LocalDateTime.class));
            }
            rs.close();
            pst.close();
            cnx.close();
            System.out.println("Récupération par ID effectuée !!");
        } catch (SQLException e) {
            System.out.println("Problème lors de la récupération par ID !!");
            e.printStackTrace();
        }
        return t;
    }
}