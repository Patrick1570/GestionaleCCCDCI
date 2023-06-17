package main;

import modules.ContoCorrente;

public class Main {

	public static void main(String[] args) {
		ContoCorrente cliente1 = new ContoCorrente("Mario", "23/05/2021");
		
		cliente1.generaInteressi();
		System.out.println(cliente1.toString());
	}

}
