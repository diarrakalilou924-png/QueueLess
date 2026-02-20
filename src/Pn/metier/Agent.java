package Pn.metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Agent {
private int id_agent;
private String nom;
private String email;
private String mot_de_passe;
private int id_entreprise;
	public Agent() {
		// TODO Auto-generated constructor stub
	}
	public int getId_agent() {
		return id_agent;
	}
	public void setId_agent(int id_agent) {
		this.id_agent = id_agent;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
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
	public int getId_entreprise() {
		return id_entreprise;
	}
	public void setId_entreprise(int id_entreprise) {
		this.id_entreprise = id_entreprise;
	}
	// les méthodes CRUD
		//1.CREATE - enregistrement des données dans la table agent
		public void enregistrer(Agent agn) {
			String sql="insert into Agent values(?,?,?,?)";
			Connection cnx=null;
			BDQUEueLess db=new BDQUEueLess();
			cnx=db.seconnecter();
			int i=0; // résultat de l'éxécution de la requête
			PreparedStatement pst=null;
			try {
				pst=cnx.prepareStatement(sql);
				pst.setInt(1, agn.getId_agent());
				pst.setString(2, agn.getNom());
				pst.setString(3, agn.getEmail());
				pst.setString(4, agn.getMot_de_passe());
				pst.setInt(5, agn.getId_entreprise());
				i=pst.executeUpdate();
				if(i!=0) System.out.println("Enregistrement effectué !");
				else System.out.println("Enregistrement non effectué !");
				pst.close(); cnx.close();
				
			} catch (SQLException e) {
				System.out.println("Enregistrement non effectué !");
				e.printStackTrace();
			}
		}// fin enregistrer()
		
		public List<Agent> getAgent(){
			List<Agent> lagn=new ArrayList<Agent>();
			String sql="select * from agent";
			Connection cnx=null;
			BDQUEueLess db=new BDQUEueLess();
			cnx=db.seconnecter();
			
	          Statement st=null;
			ResultSet rs=null;
			try {
				st=cnx.createStatement();
				rs=st.executeQuery(sql);
				while(rs.next()) {
				    Agent agn=new Agent();
					agn.setId_agent(rs.getInt(1));
					agn.setNom(rs.getString(2));
					agn.setEmail(rs.getString(3));
					agn.setMot_de_passe(rs.getString(4));
					agn.setId_entreprise(rs.getInt(5));
	
					lagn.add(agn);
				}
				rs.close(); st.close(); cnx.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			return lagn;
		}// fin getAgent()
		
		public void updateAgent(Agent agn, int id_agent) {
			List<Agent> lagn=new ArrayList<Agent>();
			String sql="update agent set  nom=?, email=?, mot_de_passe=?,"
					+ " id_entreprise=? where id_agent=?";
			Connection cnx=null;
			BDQUEueLess db=new BDQUEueLess();
			cnx=db.seconnecter();
			PreparedStatement pst=null;
			int i=0;
			
			try {
				pst=cnx.prepareStatement(sql);
				pst.setString(1, agn.getNom());
				pst.setString(2, agn.getEmail());
				pst.setString(3, agn.getMot_de_passe());
				pst.setInt(4, agn.getId_entreprise());
				pst.setInt(5,id_agent);
				i=pst.executeUpdate();
				if(i!=0) System.out.println("Enregistrement modifié !");
				else System.out.println("Enregistrement non modifié !");
				pst.close(); cnx.close();
				
			} catch (SQLException e) {
				System.out.println("Enregistrement non modifié !");
				e.printStackTrace();
			}
		}// fin updateAgent
		public void deleteAgent(int id_agent) {
		String sql="delete from agent where(id_agent=?)";
		Connection cnx=null;
		BDQUEueLess db=new BDQUEueLess();
		cnx=db.seconnecter();
		PreparedStatement pst=null;
		int i=0;
		try {
			pst=cnx.prepareStatement(sql);
			pst.setInt(1, id_agent);
			i=pst.executeUpdate();
			if(i!=0) System.out.println("Enregistrement Supprimé !");
			else System.out.println("Enregistrement non Supprimé !");
			pst.close(); cnx.close();
			
		} catch (SQLException e) {
			System.out.println("Enregistrement non Supprimé !");
			e.printStackTrace();
		}
	}// fin deleteAgent
		
		public  Agent getAgentById_agent(int id_agent) {
			Agent agn=new Agent();
			String sql="select * from agent where (id_agent=?)";
			Connection cnx=null;
			BDQUEueLess db=new BDQUEueLess();
			cnx=db.seconnecter();
			
			PreparedStatement pst=null;
			ResultSet rs=null;
			try {
				pst=cnx.prepareStatement(sql);
				pst.setInt(1, id_agent);
				rs=pst.executeQuery();
				while(rs.next()) {
					
					agn.setId_agent(rs.getInt(1));
					agn.setNom(rs.getString(2));
					agn.setEmail(rs.getString(3));
					agn.setMot_de_passe(rs.getString(4));
					agn.setId_entreprise(rs.getInt(5));
					
					
				}
				rs.close(); pst.close(); cnx.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			return agn;
		}
}
