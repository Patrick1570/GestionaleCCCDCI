package modules;

import java.util.Random;

public class ContoInvestimento extends Conto{

	Random gen = new Random();
	
	public ContoInvestimento(String titolare, String dataApertura) {
		super(titolare, dataApertura);
	}

	private void setTasso() {
		this.setTasso(gen.nextInt(200) - 100);
	}
	
	@Override
	public void preleva(double quantità) {
		this.setTasso();
		this.generaInteressi();	
		this.setUltimaOperazione(dateManager.today());
		
		if(quantità > 0 && quantità <= this.saldo) {
			this.saldo -= quantità;
		} else {
			System.out.println("Impossibile prelevare la quantità richiesta");
		}
		
	}
	
	//TO-DO Modificare preleva e versa aggiornando il tasso dopo ogni operazione
}
