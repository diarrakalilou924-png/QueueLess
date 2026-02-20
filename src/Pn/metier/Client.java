package Pn.metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Client {
private int id_clt;
private String nom;
private String telephone;
private String email;
private java.sql.Date date_inscription;
	public Client() {
		// TODO Auto-generated constructor stub
	}
	public int getId_clt() {
		return id_clt;
	}
	public void setId_clt(int id_clt) {
		this.id_clt = id_clt;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
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
	public java.sql.Date getDate_inscription() {
		return date_inscription;
	}
	public void setDate_inscription(java.sql.Date date_inscription) {
		this.date_inscription = date_inscription;
	}
	// les méthodes CRUD
				//1.CREATE - enregistrement des données dans la table client
				public void enregistrer(Client clt) {
					String sql="insert into Client values(?,?,?,?)";
					Connection cnx=null;
					BDQUEueLess db=new BDQUEueLess();
					cnx=db.seconnecter();
					int i=0; // résultat de l'éxécution de la requête
					PreparedStatement pst=null;
					try {
						pst=cnx.prepareStatement(sql);
						pst.setInt(1, clt.getId_clt());
						pst.setString(2, clt.getNom());
						pst.setString(3, clt.getTelephone());
						pst.setString(4, clt.getEmail());
						pst.setDate(5, clt.getDate_inscription());
						i=pst.executeUpdate();
						if(i!=0) System.out.println("Enregistrement effectué !");
						else System.out.println("Enregistrement non effectué !");
						pst.close(); cnx.close();
						
					} catch (SQLException e) {
						System.out.println("Enregistrement non effectué !");
						e.printStackTrace();
					}
				}// fin enregistrer()
				
				public List<Client> getClient(){
					List<Client> lclt=new ArrayList<Client>();
					String sql="select * from client";
					Connection cnx=null;
					BDQUEueLess db=new BDQUEueLess();
					cnx=db.seconnecter();
					
			          Statement st=null;
					ResultSet rs=null;
					try {
						st=cnx.createStatement();
						rs=st.executeQuery(sql);
						while(rs.next()) {
						    Client clt=new Client();
							clt.setId_clt(rs.getInt(1));
							clt.setNom(rs.getString(2));
							clt.setTelephone(rs.getString(3));
							clt.setEmail(rs.getString(4));
							clt.setDate_inscription(rs.getDate(5));
			
							lclt.add(clt);
						}
						rs.close(); st.close(); cnx.close();
					} catch (SQLException e) {
						
						e.printStackTrace();
					}
					return lclt;
				}// fin getclient()
				
				public void updateClient(Client clt, int id_clt) {
					List<Client> lclt=new ArrayList<Client>();
					String sql="update client set  nom=?, telephone=?, email=?, date_inscription where id_serv=?";
					Connection cnx=null;
					BDQUEueLess db=new BDQUEueLess();
					cnx=db.seconnecter();
					PreparedStatement pst=null;
					int i=0;
					
					try {
						pst=cnx.prepareStatement(sql);
						pst.setString(1, clt.getNom());
						pst.setString(2, clt.getTelephone());
						pst.setString(3, clt.getEmail());
						pst.setDate(4, clt.getDate_inscription());
						pst.setInt(5,id_clt);
						i=pst.executeUpdate();
						if(i!=0) System.out.println("Enregistrement modifié !");
						else System.out.println("Enregistrement non modifié !");
						pst.close(); cnx.close();
						
					} catch (SQLException e) {
						System.out.println("Enregistrement non modifié !");
						e.printStackTrace();
					}
				}// fin updateClient
				public void deleteClient(int id_clt) {
				String sql="delete from client where(id_clt=?)";
				Connection cnx=null;
				BDQUEueLess db=new BDQUEueLess();
				cnx=db.seconnecter();
				PreparedStatement pst=null;
				int i=0;
				try {
					pst=cnx.prepareStatement(sql);
					pst.setInt(1, id_clt);
					i=pst.executeUpdate();
					if(i!=0) System.out.println("Enregistrement Supprimé !");
					else System.out.println("Enregistrement non Supprimé !");
					pst.close(); cnx.close();
					
				} catch (SQLException e) {
					System.out.println("Enregistrement non Supprimé !");
					e.printStackTrace();
				}
			}// fin deleteClient
				
				public  Client getClientById_clt(int id_clt) {
					Client clt=new Client();
					String sql="select * from client where (id_clt=?)";
					Connection cnx=null;
					BDQUEueLess db=new BDQUEueLess();
					cnx=db.seconnecter();
					
					PreparedStatement pst=null;
					ResultSet rs=null;
					try {
						pst=cnx.prepareStatement(sql);
						pst.setInt(1, id_clt);
						rs=pst.executeQuery();
						while(rs.next()) {
							
							clt.setId_clt(rs.getInt(1));
							clt.setNom(rs.getString(2));
							clt.setTelephone(rs.getString(3));
							clt.setEmail(rs.getString(4));
							clt.setDate_inscription(rs.getDate(5));
							
							
							
						}
						rs.close(); pst.close(); cnx.close();
					} catch (SQLException e) {
						
						e.printStackTrace();
					}
					return clt;
				}
}
