package modules;

public class ContoDeposito extends Conto{

	private final int tasso = 10;

	public ContoDeposito(String titolare, String dataApertura) {
		super(titolare, dataApertura);
	}

	public int getTasso() {
		return tasso;
	}

	//Preleva e registra la data del prelievo, limite 1000
	@Override
	public void preleva(double quantità) {
		if(quantità > 0 && quantità < 1000 && quantità >= this.saldo) {
			this.saldo -= quantità;
		} else {
			System.out.println("Impossibile prelevare la quantità richiesta");
		}

		this.generaInteressi();

		this.dateHistory.add(this.dateManager.today());
		this.withdrawalsHistory.add((int) (0 - quantità));

	}

	//Registra un prelievo effettuato nel passato, limite 1000
	@Override
	public void preleva(double quantità, String date) {
		if(quantità > 0 && quantità < 1000 && quantità >= this.saldo && this.dateManager.getDaysBetween(date) > 0) {
			this.saldo -= quantità;
		} else {
			System.out.println("Impossibile prelevare la quantità richiesta");
		}

		this.generaInteressi();

		this.dateHistory.add(this.dateManager.parseDate(date));
		this.withdrawalsHistory.add((int) (0 - quantità));

	}

}
