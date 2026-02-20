package Pn.metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Admin {
private int id_admin;
private String nom;
private String email;
private String mot_de_passe;
	public Admin() {
		// TODO Auto-generated constructor stub
	}
	public int getId_admin() {
		return id_admin;
	}
	public void setId_admin(int id_admin) {
		this.id_admin = id_admin;
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
	// les méthodes CRUD
				//1.CREATE - enregistrement des données dans la table admin
				public void enregistrer(Admin adm) {
					String sql="insert into admin values(?,?,?)";
					Connection cnx=null;
					BDQUEueLess db=new BDQUEueLess();
					cnx=db.seconnecter();
					int i=0; // résultat de l'éxécution de la requête
					PreparedStatement pst=null;
					try {
						pst=cnx.prepareStatement(sql);
						pst.setInt(1, adm.getId_admin());
						pst.setString(2, adm.getNom());
						pst.setString(3, adm.getEmail());
						pst.setString(4, adm.getMot_de_passe());
						
						i=pst.executeUpdate();
						if(i!=0) System.out.println("Enregistrement effectué !");
						else System.out.println("Enregistrement non effectué !");
						pst.close(); cnx.close();
						
					} catch (SQLException e) {
						System.out.println("Enregistrement non effectué !");
						e.printStackTrace();
					}
				}// fin enregistrer()
				
				public List<Admin> getAdmin(){
					List<Admin> ladm=new ArrayList<Admin>();
					String sql="select * from admin";
					Connection cnx=null;
					BDQUEueLess db=new BDQUEueLess();
					cnx=db.seconnecter();
					
			          Statement st=null;
					ResultSet rs=null;
					try {
						st=cnx.createStatement();
						rs=st.executeQuery(sql);
						while(rs.next()) {
							Admin adm=new Admin();
							adm.setId_admin(rs.getInt(1));
							adm.setNom(rs.getString(2));
							adm.setEmail(rs.getString(3));
							adm.setMot_de_passe(rs.getString(4));
							
							ladm.add(adm);
						}
						rs.close(); st.close(); cnx.close();
					} catch (SQLException e) {
						
						e.printStackTrace();
					}
					return ladm;
				}// fin getAdmin()
				
				public void updateAdmin(Admin adm, int id_admin) {
					List<Admin> ladm=new ArrayList<Admin>();
					String sql="update admin set  nom=?, email=?, mot_de_passe=? where id_admin=?";
					Connection cnx=null;
					BDQUEueLess db=new BDQUEueLess();
					cnx=db.seconnecter();
					PreparedStatement pst=null;
					int i=0;
					
					try {
						pst=cnx.prepareStatement(sql);
						pst.setString(1, adm.getNom());
						pst.setString(2, adm.getEmail());
						pst.setString(3, adm.getMot_de_passe());
						pst.setInt(6,id_admin);
						i=pst.executeUpdate();
						if(i!=0) System.out.println("Enregistrement modifié !");
						else System.out.println("Enregistrement non modifié !");
						pst.close(); cnx.close();
						
					} catch (SQLException e) {
						System.out.println("Enregistrement non modifié !");
						e.printStackTrace();
					}
				}// fin updateadmin
				public void deleteAdmin(int id_admin) {
				String sql="delete from Admin where(id_admin=?)";
				Connection cnx=null;
				BDQUEueLess db=new BDQUEueLess();
				cnx=db.seconnecter();
				PreparedStatement pst=null;
				int i=0;
				try {
					pst=cnx.prepareStatement(sql);
					pst.setInt(1, id_admin);
					i=pst.executeUpdate();
					if(i!=0) System.out.println("Enregistrement Supprimé !");
					else System.out.println("Enregistrement non Supprimé !");
					pst.close(); cnx.close();
					
				} catch (SQLException e) {
					System.out.println("Enregistrement non Supprimé !");
					e.printStackTrace();
				}
			}// fin deleteadmin
				
				public  Admin getAdminById_admin(int id_admin) {
					Admin adm=new Admin();
					String sql="select * from Admin where (id_admin=?)";
					Connection cnx=null;
					BDQUEueLess db=new BDQUEueLess();
					cnx=db.seconnecter();
					
					PreparedStatement pst=null;
					ResultSet rs=null;
					try {
						pst=cnx.prepareStatement(sql);
						pst.setInt(1, id_admin);
						rs=pst.executeQuery();
						while(rs.next()) {
							
							adm.setId_admin(rs.getInt(1));
							adm.setNom(rs.getString(2));
							adm.setEmail(rs.getString(3));
							adm.setMot_de_passe(rs.getString(4));
							
						}
						rs.close(); pst.close(); cnx.close();
					} catch (SQLException e) {
						
						e.printStackTrace();
					}
					return adm;
				}
}
