package Pn.metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Service {
private int id_serv;
private String nom;
private int temps_moyen;
private int id_entreprise;
private java.sql.Date date_service;
	public Service() {
		// TODO Auto-generated constructor stub
	}
	public int getId_serv() {
		return id_serv;
	}
	public void setId_serv(int id_serv) {
		this.id_serv = id_serv;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getTemps_moyen() {
		return temps_moyen;
	}
	public void setTemps_moyen(int temps_moyen) {
		this.temps_moyen = temps_moyen;
	}
	public int getId_entreprise() {
		return id_entreprise;
	}
	public void setId_entreprise(int id_entreprise) {
		this.id_entreprise = id_entreprise;
	}
	public java.sql.Date getDate_service() {
		return date_service;
	}
	public void setDate_service(java.sql.Date date_service) {
		this.date_service = date_service;
	}
	// les méthodes CRUD
			//1.CREATE - enregistrement des données dans la table service
			public void enregistrer(Service serv) {
				String sql="insert into Service values(?,?,?,?)";
				Connection cnx=null;
				BDQUEueLess db=new BDQUEueLess();
				cnx=db.seconnecter();
				int i=0; // résultat de l'éxécution de la requête
				PreparedStatement pst=null;
				try {
					pst=cnx.prepareStatement(sql);
					pst.setInt(1, serv.getId_serv());
					pst.setString(2, serv.getNom());
					pst.setInt(3, serv.getTemps_moyen());
					pst.setInt(4, serv.getId_entreprise());
					pst.setDate(5, serv.getDate_service());
					i=pst.executeUpdate();
					if(i!=0) System.out.println("Enregistrement effectué !");
					else System.out.println("Enregistrement non effectué !");
					pst.close(); cnx.close();
					
				} catch (SQLException e) {
					System.out.println("Enregistrement non effectué !");
					e.printStackTrace();
				}
			}// fin enregistrer()
			
			public List<Service> getService(){
				List<Service> lserv=new ArrayList<Service>();
				String sql="select * from service";
				Connection cnx=null;
				BDQUEueLess db=new BDQUEueLess();
				cnx=db.seconnecter();
				
		          Statement st=null;
				ResultSet rs=null;
				try {
					st=cnx.createStatement();
					rs=st.executeQuery(sql);
					while(rs.next()) {
					    Service serv=new Service();
						serv.setId_serv(rs.getInt(1));
						serv.setNom(rs.getString(2));
						serv.setTemps_moyen(rs.getInt(3));
						serv.setId_entreprise(rs.getInt(4));
						serv.setDate_service(rs.getDate(5));
		
						lserv.add(serv);
					}
					rs.close(); st.close(); cnx.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				return lserv;
			}// fin getService()
			
			public void updateService(Service serv, int id_serv) {
				List<Service> lserv=new ArrayList<Service>();
				String sql="update service set  nom=?, temps_moyen=?, id_entreprise=?,"
						+ " date_service=? where id_serv=?";
				Connection cnx=null;
				BDQUEueLess db=new BDQUEueLess();
				cnx=db.seconnecter();
				PreparedStatement pst=null;
				int i=0;
				
				try {
					pst=cnx.prepareStatement(sql);
					pst.setString(1, serv.getNom());
					pst.setInt(2, serv.getTemps_moyen());
					pst.setInt(3, serv.getId_entreprise());
					pst.setDate(4, serv.getDate_service());
					pst.setInt(5,id_serv);
					i=pst.executeUpdate();
					if(i!=0) System.out.println("Enregistrement modifié !");
					else System.out.println("Enregistrement non modifié !");
					pst.close(); cnx.close();
					
				} catch (SQLException e) {
					System.out.println("Enregistrement non modifié !");
					e.printStackTrace();
				}
			}// fin updateService
			public void deleteService(int id_serv) {
			String sql="delete from agent where(id_serv=?)";
			Connection cnx=null;
			BDQUEueLess db=new BDQUEueLess();
			cnx=db.seconnecter();
			PreparedStatement pst=null;
			int i=0;
			try {
				pst=cnx.prepareStatement(sql);
				pst.setInt(1, id_serv);
				i=pst.executeUpdate();
				if(i!=0) System.out.println("Enregistrement Supprimé !");
				else System.out.println("Enregistrement non Supprimé !");
				pst.close(); cnx.close();
				
			} catch (SQLException e) {
				System.out.println("Enregistrement non Supprimé !");
				e.printStackTrace();
			}
		}// fin deleteService
			
			public  Service getServiceById_serv(int id_serv) {
				Service serv=new Service();
				String sql="select * from service where (id_serv=?)";
				Connection cnx=null;
				BDQUEueLess db=new BDQUEueLess();
				cnx=db.seconnecter();
				
				PreparedStatement pst=null;
				ResultSet rs=null;
				try {
					pst=cnx.prepareStatement(sql);
					pst.setInt(1, id_serv);
					rs=pst.executeQuery();
					while(rs.next()) {
						
						serv.setId_serv(rs.getInt(1));
						serv.setNom(rs.getString(2));
						serv.setTemps_moyen(rs.getInt(3));
						serv.setId_entreprise(rs.getInt(4));
						serv.setDate_service(rs.getDate(5));
						
						
						
					}
					rs.close(); pst.close(); cnx.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				return serv;
			}
}
