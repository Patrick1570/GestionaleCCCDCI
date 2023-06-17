package modules;

import java.time.LocalDate;
import java.util.ArrayList;


public class Conto {
	private String titolare;
	LocalDate dataApertura;
	protected double saldo;
	private int tasso;
	
	//Array con le date delle operazioni
	protected ArrayList<LocalDate> dateHistory = new ArrayList<LocalDate>();
	
	//Array con cronologia operazioni
	protected ArrayList<Integer> withdrawalsHistory = new ArrayList<Integer>();
	
	DateManager dateManager = new DateManager();
	
	//Costruttore senza tasso fisso
	public Conto(String titolare, String dataApertura) {
		this.titolare = titolare;
		this.dataApertura = LocalDate.parse(dataApertura);
		this.saldo = 1000;
	}
	
	//Costruttore con tasso fisso
	public Conto(String titolare, String dataApertura, int tasso) {
		this.titolare = titolare;
		this.dataApertura = LocalDate.parse(dataApertura);
		this.saldo = 1000;
		this.tasso = tasso; 
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
		if(quantità > 0 && quantità >= this.saldo) {
			this.saldo -= quantità;
		} else {
			System.out.println("Impossibile prelevare la quantità richiesta");
		}
		
		this.generaInteressi();
		
		this.dateHistory.add(this.dateManager.today());
		this.withdrawalsHistory.add((int) (0 - quantità));
		
	}
	
	//Registra un prelievo effettuato nel passato
	public void preleva(double quantità, String date) {
		if(quantità > 0 && quantità >= this.saldo && this.dateManager.getDaysBetween(date) > 0) {
			this.saldo -= quantità;
		} else {
			System.out.println("Impossibile prelevare la quantità richiesta");
		}
		
		this.generaInteressi();
		
		this.dateHistory.add(this.dateManager.parseDate(date));
		this.withdrawalsHistory.add((int) (0 - quantità));
		
	}
	
	//Versa un importo
	public void versa(double quantità) {
		if(quantità > 0) {
			this.saldo += quantità;
		} else {
			System.out.println("Inserire un valore valido");
		}
		
		this.generaInteressi();
		
		this.dateHistory.add(this.dateManager.today());
		this.withdrawalsHistory.add((int) quantità);
		
	}
	
	//Registra un versamento nel passato
	public void versa(double quantità, String date) {
		if(quantità > 0 && this.dateManager.getDaysBetween(date) > 0) {
			this.saldo += quantità;
		} else {
			System.out.println("Inserire un valore valido");
		}
		
		this.generaInteressi();
		
		this.dateHistory.add(dateManager.today());
		this.withdrawalsHistory.add((int) quantità);
		
	}
	
	public void generaInteressi() {
		
		LocalDate lastOperation = this.dateHistory.get(dateHistory.size() - 1);
		int periodo = this.dateManager.getDaysBetween(lastOperation);
		
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
	
	//Restituisce un array con il resosconto di tutte le operazioni
	public ArrayList<String> getHistory() {
		
		ArrayList<String> history = new ArrayList<String>();
		
		if(this.dateHistory.isEmpty()) {
			history.add("Nessuna operazione eseguita");
		}
		
		for(int i = 0; i < this.dateHistory.size(); i++) {
			
			String operazione = this.dateHistory.get(i).toString() + "\t\t" + this.withdrawalsHistory.get(i).toString();
			history.add(operazione);
		}
		
		return history;
	}


}
