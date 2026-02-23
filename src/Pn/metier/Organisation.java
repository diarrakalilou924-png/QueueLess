package Pn.metier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Organisation {

	    private UUID id;
	    private String nom;
	    private String adresse;
	    private String telephone;
	    private String modeReservation;
	    private String abonnementType;
	    private Timestamp createdAt;

	    

	    public UUID getId() { return id; }
	    public void setId(UUID id) { this.id = id; }

	    public String getNom() { return nom; }
	    public void setNom(String nom) { this.nom = nom; }

	    public String getAdresse() { return adresse; }
	    public void setAdresse(String adresse) { this.adresse = adresse; }

	    public String getTelephone() { return telephone; }
	    public void setTelephone(String telephone) { this.telephone = telephone; }

	    public String getModeReservation() { return modeReservation; }
	    public void setModeReservation(String modeReservation) { this.modeReservation = modeReservation; }

	    public String getAbonnementType() { return abonnementType; }
	    public void setAbonnementType(String abonnementType) { this.abonnementType = abonnementType; }

	    public Timestamp getCreatedAt() { return createdAt; }
	    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
       
	    private BDQUEueLess db = new BDQUEueLess();
	    public Organisation createOrganisation(Organisation org) {

	        String sql = "INSERT INTO organisations " +
	                     "(nom, adresse, telephone, modereservation, abonnement_type) " +
	                     "VALUES (?, ?, ?, ?, ?) RETURNING id, created_at";

	        try (Connection cnx = db.seconnecter();
	             PreparedStatement pst = cnx.prepareStatement(sql)) {

	            pst.setString(1, org.getNom());
	            pst.setString(2, org.getAdresse());
	            pst.setString(3, org.getTelephone());
	            pst.setString(4, org.getModeReservation());
	            pst.setString(5, org.getAbonnementType());

	            ResultSet rs = pst.executeQuery();

	            if (rs.next()) {
	                org.setId((UUID) rs.getObject("id"));
	                org.setCreatedAt(rs.getTimestamp("created_at"));
	            }

	            return org;

	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
			
	    public List<Organisation> getAllOrganisations() {

	        List<Organisation> list = new ArrayList<>();
	        String sql = "SELECT * FROM organisations ORDER BY created_at DESC";

	        try (Connection cnx = db.seconnecter();
	             Statement st = cnx.createStatement();
	             ResultSet rs = st.executeQuery(sql)) {

	            while (rs.next()) {

	                Organisation org = new Organisation();

	                org.setId((UUID) rs.getObject("id"));
	                org.setNom(rs.getString("nom"));
	                org.setAdresse(rs.getString("adresse"));
	                org.setTelephone(rs.getString("telephone"));
	                org.setModeReservation(rs.getString("modereservation"));
	                org.setAbonnementType(rs.getString("abonnement_type"));
	                org.setCreatedAt(rs.getTimestamp("created_at"));

	                list.add(org);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return list;
	    }
	    public boolean deleteOrganisation(UUID id) {

	        String sql = "DELETE FROM organisations WHERE id = ?";

	        try (Connection cnx = db.seconnecter();
	             PreparedStatement pst = cnx.prepareStatement(sql)) {

	            pst.setObject(1, id);

	            return pst.executeUpdate() > 0;

	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
}
