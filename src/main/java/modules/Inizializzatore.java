package modules;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Inizializzatore {
	
	public static Inizializzatore instance;
	private DBHandler handler = DBHandler.getInstance();
	
	private Inizializzatore() {
		handler.getConnection();	
		System.out.println("Pronto");
	}
	
	//public void printData() {}
	
	public static Inizializzatore getInstance() {
		if(instance == null) {
			instance = new Inizializzatore();
		} 
		return instance;
	}
	
	public ArrayList<Correntista> generateArray() {
		
		ArrayList<Correntista> array = new ArrayList<Correntista>();
		
		try {
			Statement stm = handler.getConnection().createStatement();
			ResultSet rs = stm.executeQuery("select * from correntista");
			
			while(rs.next()) {
				
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String città = rs.getString("città");
				String nazione = rs.getString("nazione");
				String telefono = rs.getString("telefono");
				
				Correntista nuovoCorrentista = new Correntista(nome, cognome, città, nazione, telefono);
				array.add(nuovoCorrentista);				
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return array;
	}
	
}
