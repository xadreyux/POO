package main;

import simulation.Simulation;
import pec.PEC;

public class Main {

	public static void main(String[] args) {
	
		if (args.length != 1) {
			System.out.println("Only 1 argument expected");
			System.exit(0);
		}

		String fileName = args[0];
		
		if (fileName.indexOf(".xml") == -1) {
			System.out.println("Invalid file type");
			System.exit(0);
		}
		
		PEC pec = new PEC();
		Simulation simulation = new Simulation(fileName, pec);
		
		System.out.println(simulation.indAlive);
		
		

	}

}
