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
public class Guiche {

    private UUID id_guichet;
    private int numero;
    private String nom;
    private boolean actif;
    private LocalDateTime created_at;
    private UUID service_id;
    private UUID utilisateurs_id;
	public Guiche() {
		// TODO Auto-generated constructor stub
	}
	
	    public UUID getId_guichet() {
	        return id_guichet;
	    }

	    public void setId_guichet(UUID id_guichet) {
	        this.id_guichet = id_guichet;
	    }

	    public int getNumero() {
	        return numero;
	    }

	    public void setNumero(int numero) {
	        this.numero = numero;
	    }

	    public String getNom() {
	        return nom;
	    }

	    public void setNom(String nom) {
	        this.nom = nom;
	    }

	    public boolean isActif() {
	        return actif;
	    }

	    public void setActif(boolean actif) {
	        this.actif = actif;
	    }

	    public LocalDateTime getCreated_at() {
	        return created_at;
	    }

	    public void setCreated_at(LocalDateTime created_at) {
	        this.created_at = created_at;
	    }

	    public UUID getService_id() {
	        return service_id;
	    }

	    public void setService_id(UUID service_id) {
	        this.service_id = service_id;
	    }

	    public UUID getUtilisateurs_id() {
	        return utilisateurs_id;
	    }

	    public void setUtilisateurs_id(UUID utilisateurs_id) {
	        this.utilisateurs_id = utilisateurs_id;
	    }

	    // Instance de connexion (comme dans Service)
	    BDQUEueLess db = new BDQUEueLess();

	    // Ajouter un guichet
	    public void AddGuichet(Guiche guichet) {
	        String sql = "INSERT INTO guichets(numero, nom, actif, service_id, utilisateurs_id) VALUES (?,?,?,?,?)";
	        Connection cnx = null;
	        cnx = db.seconnecter();
	        PreparedStatement pst;
	        int i = 0;

	        try {
	            pst = cnx.prepareStatement(sql);
	            pst.setInt(1, guichet.getNumero());
	            pst.setString(2, guichet.getNom());
	            pst.setBoolean(3, guichet.isActif());
	            pst.setObject(4, guichet.getService_id());
	            pst.setObject(5, guichet.getUtilisateurs_id());

	            i = pst.executeUpdate();
	            if (i != 0)
	                System.out.println("Guichet ajouté avec succès !!");
	            else
	                System.out.println("Guichet non ajouté !!");
	            cnx.close();
	            pst.close();

	        } catch (SQLException e) {
	            System.out.println("Problème d'insertion du guichet");
	            e.printStackTrace();
	        }
	    }

	    // Récupérer tous les guichets
	    public List<Guiche> getAllGuichets() {
	        List<Guiche> liste = new ArrayList<>();
	        String sql = "SELECT * FROM guichets";
	        Connection cnx = null;
	        cnx = db.seconnecter();
	        Statement st;
	        ResultSet rs;

	        try {
	            st = cnx.createStatement();
	            rs = st.executeQuery(sql);
	            while (rs.next()) {
	                Guiche g = new Guiche();
	                g.setId_guichet((UUID) rs.getObject(1));
	                g.setNumero(rs.getInt(2));
	                g.setNom(rs.getString(3));
	                g.setActif(rs.getBoolean(4));
	                g.setCreated_at(rs.getObject(5, LocalDateTime.class));
	                g.setService_id((UUID) rs.getObject(6));
	                g.setUtilisateurs_id((UUID) rs.getObject(7));
	                liste.add(g);
	            }
	            System.out.println("Liste des guichets récupérée !!");
	            cnx.close();
	            st.close();
	            rs.close();
	        } catch (SQLException e) {
	            System.out.println("Problème lors de la récupération des guichets !!");
	            e.printStackTrace();
	        }
	        return liste;
	    }

	    // Mettre à jour un guichet
	    public void UpdateGuichet(Guiche guichet, UUID id_guichet) {
	        String sql = "UPDATE guichets SET numero=?, nom=?, actif=?, service_id=?, utilisateurs_id=? WHERE id=?";
	        Connection cnx = null;
	        cnx = db.seconnecter();
	        PreparedStatement pst;
	        int i = 0;

	        try {
	            pst = cnx.prepareStatement(sql);
	            pst.setInt(1, guichet.getNumero());
	            pst.setString(2, guichet.getNom());
	            pst.setBoolean(3, guichet.isActif());
	            pst.setObject(4, guichet.getService_id());
	            pst.setObject(5, guichet.getUtilisateurs_id());
	            pst.setObject(6, id_guichet);

	            i = pst.executeUpdate();
	            if (i != 0)
	                System.out.println("Guichet modifié avec succès !!");
	            else
	                System.out.println("Guichet non modifié !!");
	            cnx.close();
	            pst.close();

	        } catch (SQLException e) {
	            System.out.println("Problème lors de la modification du guichet");
	            e.printStackTrace();
	        }
	    }

	    // Supprimer un guichet
	    public void DeleteGuichet(UUID id_guichet) {
	        String sql = "DELETE FROM guichets WHERE id=?";
	        Connection cnx = null;
	        cnx = db.seconnecter();
	        PreparedStatement pst;
	        int i = 0;

	        try {
	            pst = cnx.prepareStatement(sql);
	            pst.setObject(1, id_guichet);

	            i = pst.executeUpdate();
	            if (i != 0)
	                System.out.println("Guichet supprimé avec succès !!");
	            else
	                System.out.println("Guichet non supprimé !!");
	            cnx.close();
	            pst.close();

	        } catch (SQLException e) {
	            System.out.println("Problème lors de la suppression du guichet");
	            e.printStackTrace();
	        }
	    }

	    // Récupérer un guichet par son ID
	    public Guiche getGuichetById(UUID id_guichet) {
	        Guiche g = new Guiche();
	        String sql = "SELECT * FROM guichets WHERE id=?";
	        Connection cnx = null;
	        cnx = db.seconnecter();
	        PreparedStatement pst;
	        ResultSet rs;

	        try {
	            pst = cnx.prepareStatement(sql);
	            pst.setObject(1, id_guichet);
	            rs = pst.executeQuery();
	            if (rs.next()) {
	                g.setId_guichet((UUID) rs.getObject("id"));
	                g.setNumero(rs.getInt("numero"));
	                g.setNom(rs.getString("nom"));
	                g.setActif(rs.getBoolean("actif"));
	                g.setCreated_at(rs.getObject("created_at", LocalDateTime.class));
	                g.setService_id((UUID) rs.getObject("service_id"));
	                g.setUtilisateurs_id((UUID) rs.getObject("utilisateurs_id"));
	            }
	            rs.close();
	            pst.close();
	            cnx.close();
	            System.out.println("Récupération par ID effectuée !!");
	        } catch (SQLException e) {
	            System.out.println("Problème lors de la récupération par ID !!");
	            e.printStackTrace();
	        }
	        return g;
	    }
	}


