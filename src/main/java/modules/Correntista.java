package modules;

public class Correntista {

	private String nome;
	private String cognome;
	private String città;
	private String nazione;
	private String telefono;
	
	public Correntista(String nome, String cognome, String città, String nazione, String telefono) {
		this.nome = nome;
		this.cognome = cognome;
		this.città = città;
		this.nazione = nazione;
		this.telefono = telefono;
	}
	
	public String getNome() {
		return nome;
	}
	public String getCognome() {
		return cognome;
	}
	public String getCittà() {
		return città;
	}
	public String getNazione() {
		return nazione;
	}
	public String getTelefono() {
		return telefono;
	}
	
	@Override
	public String toString() {
		return ("Nome: " + nome
				+ "\tCognome: " + cognome
				+ "\tCittà: " + città
				+ "\tNazione: " + nazione
				+ "\tTelefono: " + telefono
				);
	}
	
	
}
