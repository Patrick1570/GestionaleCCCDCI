package modules;

public class ContoDeposito extends Conto{

	private static int tasso = 10;

	public ContoDeposito(String titolare, String dataApertura) {
		super(titolare, dataApertura, tasso);
	}

	public int getTasso() {
		return tasso;
	}

	//Preleva e registra la data del prelievo, limite 1000
	@Override
	public void preleva(double quantità) {
		this.generaInteressi();	
		this.setUltimaOperazione(dateManager.today());
		
		if(quantità > 0 && quantità <= this.saldo) {
			this.saldo -= quantità;
		} else {
			System.out.println("Impossibile prelevare la quantità richiesta");
		}
		
	}

}
