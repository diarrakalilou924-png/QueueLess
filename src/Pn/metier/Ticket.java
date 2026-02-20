package Pn.metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Ticket {
private int id_ticket;
private String numero;
private int position;
private String statut;
private java.time.LocalTime heure_arrivee;
private java.time.LocalTime heure_appel;
private int id_serv;
private int id_clt;
private int id_agent;
	public Ticket() {
		// TODO Auto-generated constructor stub
	}
	public int getId_ticket() {
		return id_ticket;
	}
	public void setId_ticket(int id_ticket) {
		this.id_ticket = id_ticket;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	public java.time.LocalTime getHeure_arrivee() {
		return heure_arrivee;
	}
	public void setHeure_arrivee(java.time.LocalTime heure_arrivee) {
		this.heure_arrivee = heure_arrivee;
	}
	public java.time.LocalTime getHeure_appel() {
		return heure_appel;
	}
	public void setHeure_appel(java.time.LocalTime heure_appel) {
		this.heure_appel = heure_appel;
	}
	public int getId_serv() {
		return id_serv;
	}
	public void setId_serv(int id_serv) {
		this.id_serv = id_serv;
	}
	public int getId_clt() {
		return id_clt;
	}
	public void setId_clt(int id_clt) {
		this.id_clt = id_clt;
	}
	public int getId_agent() {
		return id_agent;
	}
	public void setId_agent(int id_agent) {
		this.id_agent = id_agent;
	}
	// les méthodes CRUD
		//1.CREATE - enregistrement des données dans la table Ticket
		public void enregistrer(Ticket tic) {
			String sql="insert into ticket values(?,?,?,?,?,?,?,?)";
			Connection cnx=null;
			BDQUEueLess db=new BDQUEueLess();
			cnx=db.seconnecter();
			int i=0; // résultat de l'éxécution de la requête
			PreparedStatement pst=null;
			try {
				pst=cnx.prepareStatement(sql);
				pst.setInt(1, tic.getId_ticket());
				pst.setString(2, tic.getNumero());
				pst.setInt(3, tic.getPosition());
				pst.setString(4, tic.getStatut());
				pst.setTime(5, java.sql.Time.valueOf(tic.getHeure_arrivee()));
				pst.setTime(6, java.sql.Time.valueOf(tic.getHeure_appel()));
				pst.setInt(7, tic.getId_serv());
				pst.setInt(8, tic.getId_clt());
				pst.setInt(9, tic.getId_agent());
				i=pst.executeUpdate();
				if(i!=0) System.out.println("Enregistrement effectué !");
				else System.out.println("Enregistrement non effectué !");
				pst.close(); cnx.close();
				
			} catch (SQLException e) {
				System.out.println("Enregistrement non effectué !");
				e.printStackTrace();
			}
		}// fin enregistrer()
		
		public List<Ticket> getTicket(){
			List<Ticket> ltic=new ArrayList<Ticket>();
			String sql="select * from ticket";
			Connection cnx=null;
			BDQUEueLess db=new BDQUEueLess();
			cnx=db.seconnecter();
			
	          Statement st=null;
			ResultSet rs=null;
			try {
				st=cnx.createStatement();
				rs=st.executeQuery(sql);
				while(rs.next()) {
					Ticket tic=new Ticket();
					tic.setId_ticket(rs.getInt(1));
					tic.setNumero(rs.getString(2));
					tic.setPosition(rs.getInt(3));
					tic.setStatut(rs.getString(4));
					tic.setHeure_arrivee((java.time.LocalTime) rs.getTime(5).toLocalTime());
					tic.setHeure_appel((java.time.LocalTime) rs.getTime(6).toLocalTime());
					tic.setId_serv(rs.getInt(7));
					tic.setId_clt(rs.getInt(8));
					tic.setId_agent(rs.getInt(9));
					ltic.add(tic);
				}
				rs.close(); st.close(); cnx.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			return ltic;
		}// fin getTicket()
		
		public void updateTicket(Ticket tic, int id_ticket) {
			List<Ticket> ltic=new ArrayList<Ticket>();
			String sql="update client set  numero=?, position=?, statut=?, heure_arrivee=?, heure_appel=? "
					+ " id_serv=?, id_clt=?, id_agent=? where id_ticket=?";
			Connection cnx=null;
			BDQUEueLess db=new BDQUEueLess();
			cnx=db.seconnecter();
			PreparedStatement pst=null;
			int i=0;
			
			try {
				pst=cnx.prepareStatement(sql);
				pst.setString(1, tic.getNumero());
				pst.setInt(2, tic.getPosition());
				pst.setString(3, tic.getStatut());
				pst.setTime(4, java.sql.Time.valueOf(tic.getHeure_arrivee()));
				pst.setTime(5, java.sql.Time.valueOf(tic.getHeure_appel()));
				pst.setInt(6, tic.getId_serv());
				pst.setInt(7, tic.getId_clt());
				pst.setInt(8, tic.getId_agent());
				pst.setInt(9,id_ticket);
				i=pst.executeUpdate();
				if(i!=0) System.out.println("Enregistrement modifié !");
				else System.out.println("Enregistrement non modifié !");
				pst.close(); cnx.close();
				
			} catch (SQLException e) {
				System.out.println("Enregistrement non modifié !");
				e.printStackTrace();
			}
		}// fin updateTicket
		public void deleteTicket(int id_ticket) {
		String sql="delete from ticket where(id_ticket=?)";
		Connection cnx=null;
		BDQUEueLess db=new BDQUEueLess();
		cnx=db.seconnecter();
		PreparedStatement pst=null;
		int i=0;
		try {
			pst=cnx.prepareStatement(sql);
			pst.setInt(1, id_ticket);
			i=pst.executeUpdate();
			if(i!=0) System.out.println("Enregistrement Supprimé !");
			else System.out.println("Enregistrement non Supprimé !");
			pst.close(); cnx.close();
			
		} catch (SQLException e) {
			System.out.println("Enregistrement non Supprimé !");
			e.printStackTrace();
		}
	}// fin deleteTicket
		
		public  Ticket getTicketById_ticket(int id_ticket) {
			Ticket tic=new Ticket();
			String sql="select * from ticket where (id_ticket=?)";
			Connection cnx=null;
			BDQUEueLess db=new BDQUEueLess();
			cnx=db.seconnecter();
			
			PreparedStatement pst=null;
			ResultSet rs=null;
			try {
				pst=cnx.prepareStatement(sql);
				pst.setInt(1, id_ticket);
				rs=pst.executeQuery();
				while(rs.next()) {
					
					tic.setId_ticket(rs.getInt(1));
					tic.setNumero(rs.getString(2));
					tic.setPosition(rs.getInt(3));
					tic.setStatut(rs.getString(4));
					tic.setHeure_arrivee((java.time.LocalTime) rs.getTime(5).toLocalTime());
					tic.setHeure_appel((java.time.LocalTime) rs.getTime(6).toLocalTime());
					tic.setId_serv(rs.getInt(7));
					tic.setId_clt(rs.getInt(8));
					tic.setId_agent(rs.getInt(9));
					
				}
				rs.close(); pst.close(); cnx.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			return tic;
		}
}
