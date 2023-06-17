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
	
	//TO-DO Modificare preleva e versa aggiornando il tasso dopo ogni operazione
}
