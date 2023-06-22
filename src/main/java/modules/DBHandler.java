package modules;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBHandler {
	
	public static DBHandler instance;
	private Connection con;
	private Properties prop;
	private String dbName;
	private String dbLink;
	private String utente;
	private String password;
	
	private DBHandler() {
		
		InputStream is = null;
		try {
			is = new FileInputStream("resources\\configDB.properties");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		this.prop = new Properties();
		
		if(is == null) {
			System.out.println("FIle non trovato");
			return;
		}
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			prop.load(is);
			
			this.utente = prop.getProperty("db.user");
			this.password = prop.getProperty("db.password");
			this.dbLink = prop.getProperty("db.url");
			this.dbName = prop.getProperty("db.name");
			
			//System.out.println(this.utente);
			//System.out.println(this.password);
			//System.out.println(this.dbLink);
			//System.out.println(dbName);		

		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		try {
			con = DriverManager.getConnection(dbLink + dbName, utente, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//con = DriverManager.getConnection(dbLink + dbName, null)
		System.out.println("Connessione avvenuta");
		return con;
	}
	
	public static DBHandler getInstance() {
		if(instance == null) {
			instance = new DBHandler();
		} 
		return instance;
	}
	
	public void closeConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

