package Pn.metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Entreprise {
private int id_entreprise;
private String nom;
private String type;
private String adresse;
private String telephone;
private String email;
private String mot_de_passe;
private String statut;
private java.sql.Date date_creation;
	public Entreprise() {
		// TODO Auto-generated constructor stub
	}
	public int getId_entreprise() {
		return id_entreprise;
	}
	public void setId_entreprise(int id_entreprise) {
		this.id_entreprise = id_entreprise;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMot_de_passe() {
		return mot_de_passe;
	}
	public void setMot_de_passe(String mot_de_passe) {
		this.mot_de_passe = mot_de_passe;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	public java.sql.Date getDate_creation() {
		return date_creation;
	}
	public void setDate_creation(java.sql.Date date_creation) {
		this.date_creation = date_creation;
	}
	// les méthodes CRUD
	//1.CREATE - enregistrement des données dans la table entreprise
	public void enregistrer(Entreprise entr) {
		String sql="insert into entreprise values(?,?,?,?,?,?,?,?)";
		Connection cnx=null;
		BDQUEueLess db=new BDQUEueLess();
		cnx=db.seconnecter();
		int i=0; // résultat de l'éxécution de la requête
		PreparedStatement pst=null;
		try {
			pst=cnx.prepareStatement(sql);
			pst.setInt(1, entr.getId_entreprise());
			pst.setString(2, entr.getNom());
			pst.setString(3, entr.getType());
			pst.setString(4, entr.getAdresse());
			pst.setString(5, entr.getTelephone());
			pst.setString(6, entr.getEmail());
			pst.setString(7, entr.getMot_de_passe());
			pst.setString(8, entr.getStatut());
			pst.setDate(9,new java.sql.Date(entr.getDate_creation().getTime()));
			i=pst.executeUpdate();
			if(i!=0) System.out.println("Enregistrement effectué !");
			else System.out.println("Enregistrement non effectué !");
			pst.close(); cnx.close();
			
		} catch (SQLException e) {
			System.out.println("Enregistrement non effectué !");
			e.printStackTrace();
		}
	}// fin enregistrer()
	
	public List<Entreprise> getEntreprise(){
		List<Entreprise> lentr=new ArrayList<Entreprise>();
		String sql="select * from entreprise";
		Connection cnx=null;
		BDQUEueLess db=new BDQUEueLess();
		cnx=db.seconnecter();
		
          Statement st=null;
		ResultSet rs=null;
		try {
			st=cnx.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				Entreprise entr=new Entreprise();
				entr.setId_entreprise(rs.getInt(1));
				entr.setNom(rs.getString(2));
				entr.setType(rs.getString(3));
				entr.setAdresse(rs.getString(4));
				entr.setTelephone(rs.getString(5));
				entr.setEmail(rs.getString(6));
				entr.setMot_de_passe(rs.getString(7));
				entr.setStatut(rs.getString(8));
				entr.setDate_creation(rs.getDate(9));
				lentr.add(entr);
			}
			rs.close(); st.close(); cnx.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return lentr;
	}// fin getAdmin()
	
	public void updateEntreprise(Entreprise entr, int id_entreprise) {
		List<Entreprise> lentr=new ArrayList<Entreprise>();
		String sql="update entreprise set  nom=?, type=?, adresse=?, telephone=?, email=? "
				+ " mot_de_passe=?, statut=?, date_creation=? where id_entreprise=?";
		Connection cnx=null;
		BDQUEueLess db=new BDQUEueLess();
		cnx=db.seconnecter();
		PreparedStatement pst=null;
		int i=0;
		
		try {
			pst=cnx.prepareStatement(sql);
			pst.setString(1, entr.getNom());
			pst.setString(2, entr.getType());
			pst.setString(3, entr.getAdresse());
			pst.setString(4, entr.getTelephone());
			pst.setString(5, entr.getEmail());
			pst.setString(6, entr.getMot_de_passe());
			pst.setString(7, entr.getStatut());
			pst.setDate(8, entr.getDate_creation());
			pst.setInt(9,id_entreprise);
			i=pst.executeUpdate();
			if(i!=0) System.out.println("Enregistrement modifié !");
			else System.out.println("Enregistrement non modifié !");
			pst.close(); cnx.close();
			
		} catch (SQLException e) {
			System.out.println("Enregistrement non modifié !");
			e.printStackTrace();
		}
	}// fin updateEntreprise
	public void deleteEntreprise(int id_entreprise) {
	String sql="delete from entreprise where(id_entreprise=?)";
	Connection cnx=null;
	BDQUEueLess db=new BDQUEueLess();
	cnx=db.seconnecter();
	PreparedStatement pst=null;
	int i=0;
	try {
		pst=cnx.prepareStatement(sql);
		pst.setInt(1, id_entreprise);
		i=pst.executeUpdate();
		if(i!=0) System.out.println("Enregistrement Supprimé !");
		else System.out.println("Enregistrement non Supprimé !");
		pst.close(); cnx.close();
		
	} catch (SQLException e) {
		System.out.println("Enregistrement non Supprimé !");
		e.printStackTrace();
	}
}// fin deleteEntreprise
	
	public  Entreprise getEntrepriseById_entreprise(int id_entreprise) {
		Entreprise entr=new Entreprise();
		String sql="select * from entreprise where (id_entreprise=?)";
		Connection cnx=null;
		BDQUEueLess db=new BDQUEueLess();
		cnx=db.seconnecter();
		
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			pst=cnx.prepareStatement(sql);
			pst.setInt(1, id_entreprise);
			rs=pst.executeQuery();
			while(rs.next()) {
				
				entr.setId_entreprise(rs.getInt(1));
				entr.setNom(rs.getString(2));
				entr.setType(rs.getString(3));
				entr.setAdresse(rs.getString(4));
				entr.setTelephone(rs.getString(5));
				entr.setEmail(rs.getString(6));
				entr.setMot_de_passe(rs.getString(7));
				entr.setStatut(rs.getString(8));
				entr.setDate_creation(rs.getDate(9));
				
			}
			rs.close(); pst.close(); cnx.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return entr;
	}
}
