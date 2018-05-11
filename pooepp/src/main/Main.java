package main;

import simulation.Simulation;
import pec.PEC;
/**
 * This class has the main method that constructs and runs a simulation using a PEC
 * 
 *
 */
public class Main {
	
/**
 * This is the main method that reads the .xml file containing the parameters of the simulation, builds a simulation and a PEC and runs the simulation
 * @param args array of arguments of the program
 */
	public static void main(String[] args) {
		
		//checks if only one argument is being used
		if (args.length != 1) {
			System.out.println("Only 1 argument expected");
			System.exit(0);
		}

		String fileName = args[0];
		
		//checks if the argument contains the extension ".xml"
		if (fileName.indexOf(".xml") == -1) {
			System.out.println("Invalid file type");
			System.exit(0);
		}
		
		//creates PEC
		PEC pec = new PEC();
		//creates simulation
		Simulation simulation = new Simulation(fileName, pec);
		
		//runs simulation
		simulation.runSimulation();
		
		return;

	}

}
