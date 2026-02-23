package Pn.metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;





public class Utilisateur {
	private UUID id;
    private String telephone;
    private String nom;
    private String email;
    private String motDePasse;
    private String role;           
    private String statut;        
    private UUID organisationId;
    private Timestamp dateCreation;

    public Utilisateur() { }

    // Getters & setters

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMotDePasse() { return motDePasse; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    public UUID getOrganisationId() { return organisationId; }
    public void setOrganisationId(UUID organisationId) { this.organisationId = organisationId; }

    public Timestamp getDateCreation() { return dateCreation; }
    public void setDateCreation(Timestamp dateCreation) { this.dateCreation = dateCreation; }

   

        private BDQUEueLess db = new BDQUEueLess();

        // CREATE : insère et récupère id + date_creation via RETURNING
        public Utilisateur create(Utilisateur u) {
            String sql = "INSERT INTO utilisateurs " +
                         "(telephone, nom, email, mot_de_passe, role, statut, organisation_id) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id, date_creation";

            try (Connection cnx = db.seconnecter();
                 PreparedStatement pst = cnx.prepareStatement(sql)) {

                pst.setString(1, u.getTelephone());
                pst.setString(2, u.getNom());
                pst.setString(3, u.getEmail());
                pst.setString(4, u.getMotDePasse()); // déjà hashé si tu utilises BCrypt
                pst.setString(5, u.getRole());
                pst.setString(6, u.getStatut());

                if (u.getOrganisationId() != null) {
                    pst.setObject(7, u.getOrganisationId());
                } else {
                    pst.setNull(7, Types.OTHER);
                }

                // executeQuery car RETURNING renvoie un resultset
                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        u.setId((UUID) rs.getObject("id"));
                        u.setDateCreation(rs.getTimestamp("date_creation"));
                    }
                }

                return u;

            } catch (SQLException ex) {
                ex.printStackTrace();
                return null;
            }
        }

        // READ ALL
        public List<Utilisateur> getAll() {
            List<Utilisateur> list = new ArrayList<>();
            String sql = "SELECT id, telephone, nom, email, mot_de_passe, role, statut, organisation_id, date_creation " +
                         "FROM utilisateurs ORDER BY date_creation DESC";

            try (Connection cnx = db.seconnecter();
                 Statement st = cnx.createStatement();
                 ResultSet rs = st.executeQuery(sql)) {

                while (rs.next()) {
                    Utilisateur u = new Utilisateur();
                    u.setId((UUID) rs.getObject("id"));
                    u.setTelephone(rs.getString("telephone"));
                    u.setNom(rs.getString("nom"));
                    u.setEmail(rs.getString("email"));
                    u.setMotDePasse(rs.getString("mot_de_passe"));
                    u.setRole(rs.getString("role"));
                    u.setStatut(rs.getString("statut"));
                    u.setOrganisationId((UUID) rs.getObject("organisation_id"));
                    u.setDateCreation(rs.getTimestamp("date_creation"));
                    list.add(u);
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            return list;
        }

        // READ BY ID
        public Utilisateur getById(UUID id) {
            String sql = "SELECT id, telephone, nom, email, mot_de_passe, role, statut, organisation_id, date_creation " +
                         "FROM utilisateurs WHERE id = ?";

            try (Connection cnx = db.seconnecter();
                 PreparedStatement pst = cnx.prepareStatement(sql)) {

                pst.setObject(1, id);
                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        Utilisateur u = new Utilisateur();
                        u.setId((UUID) rs.getObject("id"));
                        u.setTelephone(rs.getString("telephone"));
                        u.setNom(rs.getString("nom"));
                        u.setEmail(rs.getString("email"));
                        u.setMotDePasse(rs.getString("mot_de_passe"));
                        u.setRole(rs.getString("role"));
                        u.setStatut(rs.getString("statut"));
                        u.setOrganisationId((UUID) rs.getObject("organisation_id"));
                        u.setDateCreation(rs.getTimestamp("date_creation"));
                        return u;
                    }
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return null;
        }

        // UPDATE
        public boolean update(Utilisateur u) {
            String sql = "UPDATE utilisateurs SET telephone = ?, nom = ?, email = ?, mot_de_passe = ?, role = ?, statut = ?, organisation_id = ? " +
                         "WHERE id = ?";

            try (Connection cnx = db.seconnecter();
                 PreparedStatement pst = cnx.prepareStatement(sql)) {

                pst.setString(1, u.getTelephone());
                pst.setString(2, u.getNom());
                pst.setString(3, u.getEmail());
                pst.setString(4, u.getMotDePasse()); // si modifié -> hashé
                pst.setString(5, u.getRole());
                pst.setString(6, u.getStatut());

                if (u.getOrganisationId() != null) pst.setObject(7, u.getOrganisationId());
                else pst.setNull(7, Types.OTHER);

                pst.setObject(8, u.getId());

                int updated = pst.executeUpdate();
                return updated > 0;

            } catch (SQLException ex) {
                ex.printStackTrace();
                return false;
            }
        }

        // DELETE
        public boolean delete(UUID id) {
            String sql = "DELETE FROM utilisateurs WHERE id = ?";

            try (Connection cnx = db.seconnecter();
                 PreparedStatement pst = cnx.prepareStatement(sql)) {

                pst.setObject(1, id);
                int deleted = pst.executeUpdate();
                return deleted > 0;

            } catch (SQLException ex) {
                ex.printStackTrace();
                return false;
            }
        }
    }
    
    






