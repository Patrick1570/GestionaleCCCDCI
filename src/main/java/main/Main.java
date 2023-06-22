package main;

import modules.Correntista;
import modules.Inizializzatore;

public class Main {

	public static void main(String[] args) {
		
		Inizializzatore iniz = Inizializzatore.getInstance();
		
		for(Correntista x : iniz.generateArray()) {
			System.out.println(x.toString());
			
		}
		
		

	}

}
