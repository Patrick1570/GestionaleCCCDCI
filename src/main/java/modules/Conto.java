package modules;

import java.time.LocalDate;

public class Conto {
	private String titolare;
	private LocalDate dataApertura;
	private LocalDate ultimaOperazione;
	protected double saldo;
	private int tasso;
	
	DateManager dateManager = new DateManager();
	
	//Costruttore senza tasso fisso
	public Conto(String titolare, String dataApertura) {
		this.titolare = titolare;
		this.dataApertura = dateManager.parseDate(dataApertura);
		this.saldo = 1000;
	}
	
	//Costruttore con tasso fisso
	public Conto(String titolare, String dataApertura, int tasso) {
		this.titolare = titolare;
		this.dataApertura = dateManager.parseDate(dataApertura);
		this.saldo = 1000;
		this.tasso = tasso; 
		
		//Calcolo tassi interesse
		int periodo = this.dateManager.getDaysBetween(dataApertura);
		double tassoAnnuo = this.saldo * tasso / 100;
		double tassoRelativo = tassoAnnuo / 365 * periodo;
		
		this.saldo += tassoRelativo;
	}
	
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}
	public String getTitolare() {
		return this.titolare;
	}
	public LocalDate getDataApertura() {
		return this.dataApertura;
	}
	
	public int getTasso() {
		return this.tasso;
	}

	public void setTasso(int tasso) {
		this.tasso = tasso;
	}
	
	//Preleva e registra la data del prelievo
	public void preleva(double quantità) {
		this.generaInteressi();	
		this.setUltimaOperazione(dateManager.today());
		
		if(quantità > 0 && quantità <= this.saldo) {
			this.saldo -= quantità;
		} else {
			System.out.println("Impossibile prelevare la quantità richiesta");
		}
		
	}
	
	//Versa un importo
	public void versa(double quantità) {
		this.generaInteressi();	
		this.setUltimaOperazione(dateManager.today());
		
		if(quantità > 0) {
			this.saldo += quantità;
		} else {
			System.out.println("Inserire un valore valido");
		}
		

	}
	
	//Registra un versamento nel passato
	public void versa(double quantità, String date) {
		
		this.generaInteressi();	
		this.setUltimaOperazione(dateManager.today());
		
		if(quantità > 0 && this.dateManager.getDaysBetween(date) > 0) {
			this.saldo += quantità;
		} else {
			System.out.println("Inserire un valore valido");
		}
		
	}
	
	public void generaInteressi() {
		
		int periodo = 0;
		
		if(this.getUltimaOperazione() != null) {
			periodo = this.dateManager.getDaysBetween(this.getUltimaOperazione());
		} else {
			periodo = this.dateManager.getDaysBetween(dataApertura);
		}
		
		double tassoAnnuo = this.saldo * tasso / 100;
		double tassoRelativo = tassoAnnuo / 365 * periodo;
		
		this.saldo += tassoRelativo;
	}
	
	@Override
	public String toString() {
		return ("Titolare: \t"
				+ this.getTitolare()
				+ "\t || Data Apertura: \t"
				+ this.getDataApertura()
				+  "\t ||Saldo: \t"
				+ this.getSaldo()
				+ "\t Conto aperto da: "
				+ this.dataApertura);
	}

	public LocalDate getUltimaOperazione() {
		return ultimaOperazione;
	}

	public void setUltimaOperazione(LocalDate ultimaOperazione) {
		this.ultimaOperazione = ultimaOperazione;
	}


}
