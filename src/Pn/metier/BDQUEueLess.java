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
	String url="jdbc:mysql://localhost:3306/queueless";
	String user="admin";
	String pwd="12345";
	
	//Charger le pilote de MySQL
	try {
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Driver Compatible !");
	} catch (ClassNotFoundException e) {
		System.out.println("Driver non Compatible !");
		e.printStackTrace();
	}
	//Ouvrir la connexion
	try {
		cnx=DriverManager.getConnection(url, user, pwd);
		if(cnx!=null) System.out.println("Connexion établie !");
		else System.out.println("Connexion non établie !");	
	} 
	catch (SQLException e) {
		System.out.println("Connexion non établie !");	
		e.printStackTrace();
	}
	return cnx;
}

public static void main(String [] arg) {
	new BDQUEueLess().seconnecter();
}
}
