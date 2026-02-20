package Pn.metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Historique {
private int id_hist;
private int id_ticket;
private java.time.LocalTime duree_attente;
private java.sql.Date date_service;
	public Historique() {
		// TODO Auto-generated constructor stub
	}
	public int getId_hist() {
		return id_hist;
	}
	public void setId_hist(int id_hist) {
		this.id_hist = id_hist;
	}
	public int getId_ticket() {
		return id_ticket;
	}
	public void setId_ticket(int id_ticket) {
		this.id_ticket = id_ticket;
	}
	public java.time.LocalTime getDuree_attente() {
		return duree_attente;
	}
	public void setDuree_attente(java.time.LocalTime duree_attente) {
		this.duree_attente = duree_attente;
	}
	public java.sql.Date getDate_service() {
		return date_service;
	}
	public void setDate_service(java.sql.Date date_service) {
		this.date_service = date_service;
	}
	// les méthodes CRUD
				//1.CREATE - enregistrement des données dans la table historique
				public void enregistrer(Historique hist) {
					String sql="insert into Service values(?,?,?)";
					Connection cnx=null;
					BDQUEueLess db=new BDQUEueLess();
					cnx=db.seconnecter();
					int i=0; // résultat de l'éxécution de la requête
					PreparedStatement pst=null;
					try {
						pst=cnx.prepareStatement(sql);
						pst.setInt(1, hist.getId_hist());
						pst.setInt(2, hist.getId_ticket());
						pst.setTime(3, java.sql.Time.valueOf(hist.getDuree_attente()));
						pst.setDate(4, hist.getDate_service());
						i=pst.executeUpdate();
						if(i!=0) System.out.println("Enregistrement effectué !");
						else System.out.println("Enregistrement non effectué !");
						pst.close(); cnx.close();
						
					} catch (SQLException e) {
						System.out.println("Enregistrement non effectué !");
						e.printStackTrace();
					}
				}// fin enregistrer()
				
				public List<Historique> getHistorique(){
					List<Historique> lhist=new ArrayList<Historique>();
					String sql="select * from historique";
					Connection cnx=null;
					BDQUEueLess db=new BDQUEueLess();
					cnx=db.seconnecter();
					
			          Statement st=null;
					ResultSet rs=null;
					try {
						st=cnx.createStatement();
						rs=st.executeQuery(sql);
						while(rs.next()) {
						    Historique hist=new Historique();
							hist.setId_hist(rs.getInt(1));
							hist.setId_ticket(rs.getInt(2));
							hist.setDuree_attente((java.time.LocalTime) rs.getTime(3).toLocalTime());
							hist.setDate_service(rs.getDate(4));
			
							lhist.add(hist);
						}
						rs.close(); st.close(); cnx.close();
					} catch (SQLException e) {
						
						e.printStackTrace();
					}
					return lhist;
				}// fin getHistorique()
				
				public void updateHistorique(Historique hist, int id_hist) {
					List<Historique> lhist=new ArrayList<Historique>();
					String sql="update historique set  id_ticket=?, duree_attente=?, date_service=?"
							+ " where id_hist=?";
					Connection cnx=null;
					BDQUEueLess db=new BDQUEueLess();
					cnx=db.seconnecter();
					PreparedStatement pst=null;
					int i=0;
					
					try {
						pst=cnx.prepareStatement(sql);
						pst.setInt(1, hist.getId_ticket());
						pst.setTime(2, java.sql.Time.valueOf(hist.getDuree_attente()));
						pst.setDate(3, hist.getDate_service());
						pst.setInt(4,id_hist);
						i=pst.executeUpdate();
						if(i!=0) System.out.println("Enregistrement modifié !");
						else System.out.println("Enregistrement non modifié !");
						pst.close(); cnx.close();
						
					} catch (SQLException e) {
						System.out.println("Enregistrement non modifié !");
						e.printStackTrace();
					}
				}// fin updateHistorique
				public void deleteHistorique(int id_hist) {
				String sql="delete from historique where(id_hist=?)";
				Connection cnx=null;
				BDQUEueLess db=new BDQUEueLess();
				cnx=db.seconnecter();
				PreparedStatement pst=null;
				int i=0;
				try {
					pst=cnx.prepareStatement(sql);
					pst.setInt(1, id_hist);
					i=pst.executeUpdate();
					if(i!=0) System.out.println("Enregistrement Supprimé !");
					else System.out.println("Enregistrement non Supprimé !");
					pst.close(); cnx.close();
					
				} catch (SQLException e) {
					System.out.println("Enregistrement non Supprimé !");
					e.printStackTrace();
				}
			}// fin deleteHistorique
				
				public  Historique getHistoriqueById_hist(int id_hist) {
					Historique hist=new Historique();
					String sql="select * from historique where (id_hist=?)";
					Connection cnx=null;
					BDQUEueLess db=new BDQUEueLess();
					cnx=db.seconnecter();
					
					PreparedStatement pst=null;
					ResultSet rs=null;
					try {
						pst=cnx.prepareStatement(sql);
						pst.setInt(1, id_hist);
						rs=pst.executeQuery();
						while(rs.next()) {
							
							hist.setId_hist(rs.getInt(1));
							hist.setId_ticket(rs.getInt(2));
							hist.setDuree_attente((java.time.LocalTime) rs.getTime(3).toLocalTime());
							hist.setDate_service(rs.getDate(4));
							
							
							
						}
						rs.close(); pst.close(); cnx.close();
					} catch (SQLException e) {
						
						e.printStackTrace();
					}
					return hist;
				}
}
