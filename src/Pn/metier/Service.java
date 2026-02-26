package Pn.metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.tomcat.jni.Local;

public class Service {
	 private UUID id_service; 
	 private String  nom;
	 private String  description;
	 private int tempsmoyenbase;
	 private LocalTime horaireouverture ;
	 private LocalTime horairefermeture ;
	 private String  statut ;
	 private LocalDateTime datecreation ;
	 private UUID organisation_id;
	 
	
	public UUID getId_service() {
		return id_service;
	}
	public void setId_service(UUID id_service) {
		this.id_service = id_service;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getTempsmoyenbase() {
		return tempsmoyenbase;
	}
	public void setTempsmoyenbase(int tempsmoyenbase) {
		this.tempsmoyenbase = tempsmoyenbase;
	}
	public LocalTime getHoraireouverture() {
		return horaireouverture;
	}
	public void setHoraireouverture(LocalTime horaireouverture) {
		this.horaireouverture = horaireouverture;
	}
	public LocalTime getHorairefermeture() {
		return horairefermeture;
	}
	public void setHorairefermeture(LocalTime horairefermeture) {
		this.horairefermeture = horairefermeture;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	public LocalDateTime getDatecreation() {
		return datecreation;
	}
	public void setDatecreation(LocalDateTime datecreation) {
		this.datecreation = datecreation;
	}
	
	 public UUID getOrganisation_id() {
		return organisation_id;
	}
	public void setOrganisation_id(UUID organisation_id) {
		this.organisation_id = organisation_id;
	}

	BDQUEueLess db=new BDQUEueLess();
	 
	 public void AddService(Service service) {
		 String sql="insert into service(nom,description,tempsmoyenbase,horaireouverture,horairefermeture,statut,"
		 		+ "organisation_id) values(?,?,?,?,?,?,?)";
		 Connection cnx=null;
		 cnx=db.seconnecter();
		 PreparedStatement pst;
		 int i=0;
		 
		 try {
			pst=cnx.prepareStatement(sql);
			pst.setString(1, service.getNom());
			pst.setString(2, service.getDescription());
			pst.setInt(3, service.getTempsmoyenbase());
			pst.setObject(4,service.getHoraireouverture());
			pst.setObject(5,service.getHorairefermeture());
			pst.setString(6, service.getStatut());
			pst.setObject(7, service.getOrganisation_id());
			
			i=pst.executeUpdate();
			if(i !=0)System.out.println("Service ajouter avec succes!!");
			else System.out.println("Service non ajouter ");
			cnx.close();
			pst.close();
			
		} catch (SQLException e) {
			 System.out.println("problème d'inscription Service  ");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
	 }///fin d'ajout
	 
	 public List<Service> getUtilisateur(){
		 List<Service> lser=new ArrayList<Service>();
		 String sql="select * from service ";
		 Connection cnx=null;
		 cnx=db.seconnecter();
		 Statement st;
		 ResultSet rs;
		 try {
			
			st=cnx.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				 Service ser=new Service();
				ser.setId_service((UUID)rs.getObject(1));
				ser.setNom(rs.getString(2));
				ser.setDescription(rs.getString(3));
				ser.setTempsmoyenbase(rs.getInt(4));
				ser.setHoraireouverture(rs.getObject(5,LocalTime.class));
				ser.setHorairefermeture(rs.getObject(6,LocalTime.class));
				ser.setStatut(rs.getString(7));
				ser.setDatecreation(rs.getObject(8,LocalDateTime.class));
				ser.setOrganisation_id((UUID) rs.getObject(9));
				lser.add(ser);
				
			}
			System.out.println("la liste des service récupere !!");
			cnx.close();
			st.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("problème à la  récuperation de la  liste service !!");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return lser;
	 }// fin selection
	 
	 public void UpdateService(Service service,UUID id_service) {
		 String sql="update  service set nom=?,description=?,tempsmoyenbase=?,horaireouverture=?,horairefermeture=?,statut=?,"
		 		+ "organisation_id=? where id_service=?";
		 Connection cnx=null;
		 cnx=db.seconnecter();
		 PreparedStatement pst;
		 int i=0;
		 
		 try {
			pst=cnx.prepareStatement(sql);
			pst.setString(1, service.getNom());
			pst.setString(2, service.getDescription());
			pst.setInt(3, service.getTempsmoyenbase());
			pst.setObject(4,service.getHoraireouverture());
			pst.setObject(5,service.getHorairefermeture());
			pst.setString(6, service.getStatut());
			pst.setObject(7, service.getOrganisation_id());
			pst.setObject(8,id_service);
			
			
			i=pst.executeUpdate();
			
			if(i !=0)System.out.println("Service Modifier avec succes!!");
			else System.out.println("Service non Modifier !!! ");
			cnx.close();
			pst.close();
			
		} catch (SQLException e) {
			 System.out.println("problème Modification du  Service  ");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	 }
	 public void DeleteService(UUID id_service) {
		 String sql="delete   from service where id_service=? ";
		 Connection cnx=null;
		 cnx=db.seconnecter();
		
		 PreparedStatement pst;
		 int i=0;
		 
		 try {
			 
			pst=cnx.prepareStatement(sql);
			pst.setObject(1,id_service);
			
			
			i=pst.executeUpdate();
			if(i !=0)System.out.println("utilisateur Supprimer avec succes!!");
			else System.out.println("utilisateur non Supprimer ");
			cnx.close();
			pst.close();
			
		} catch (SQLException e) {
			 System.out.println("problème pour Supprimer service  ");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
	 }// fin delete
	 
	public Service getServicebyIdService(UUID id_service) {
		Service ser= new Service();
		String sql="select * from service where id_service=? ";
		 Connection cnx=null;
		 cnx=db.seconnecter();
		 PreparedStatement pst;
		 ResultSet rs;
		 int i=0;
		 try {
			pst=cnx.prepareStatement(sql);
			pst.setObject(1, id_service);
			rs=pst.executeQuery();
			while(rs.next()) {
				ser.setId_service((UUID)rs.getObject(1));
				ser.setNom(rs.getString(2));
				ser.setDescription(rs.getString(3));
				ser.setTempsmoyenbase(rs.getInt(4));
				ser.setHoraireouverture(rs.getObject(5,LocalTime.class));
				ser.setHorairefermeture(rs.getObject(6,LocalTime.class));
				ser.setStatut(rs.getString(7));
				ser.setDatecreation(rs.getObject(8,LocalDateTime.class));
				ser.setOrganisation_id((UUID) rs.getObject(9));
			}
			rs.close();
			pst.close();
			cnx.close();
			System.out.println(" récuperation avec id effectuer !!!");
		} catch (SQLException e) {
			System.out.println(" récuperation avec id nom effectuer !!!");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return ser;
	}
	 

}
