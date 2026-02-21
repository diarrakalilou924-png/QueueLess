package Pn.metier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDQUEueLess {

	public BDQUEueLess() {
		// TODO Auto-generated constructor stub
	}
public Connection seconnecter() {
	//Initialiser une connexion
	Connection cnx=null;
	
	//definir les paramètres de connexion
	String url="jdbc:postgresql://db.lnbfkdvmjpkxtzhizfhr.supabase.co:5432/postgres?sslmode=require";
	String user="postgres";
	String pwd="Ticke2026#Mali";
	
	//Charger le pilote de MySQL
	try {
		Class.forName("org.postgresql.Driver");
		System.out.println("Driver Compatible !");
	} catch (ClassNotFoundException e) {
		System.out.println("Driver non Compatible !");
		e.printStackTrace();
	}
	//Ouvrir la connexion
	try {
		cnx=DriverManager.getConnection(url, user, pwd);
		if(cnx!=null) System.out.println("Connexion etablie avec supabases !");
		else System.out.println("Connexion non etablie !");	
	} 
	catch (SQLException e) {
		System.out.println("Connexion non etablie !");	
		e.printStackTrace();
	}
	return cnx;
}

public static void main(String [] arg) {
	new BDQUEueLess().seconnecter();
}
}
