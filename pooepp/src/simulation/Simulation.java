package simulation;

import pec.PEC;
import pec.Event;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
/**
 * This Class contains the simulation and all its data structures, including the list of alive individuals, the grid, the best path and the PEC. It 
 * parses the ".xml" file and runs the simulation, getting and executing the events of the PEC and executing epidemics whenever the population
 * grows beyond the maximum specified.
 * 
 *
 */
public class Simulation {
	/**
	 * final instant of time of the simulation
	 */
	int finalinst;
	/**
	 * current population size
	 */
	int pop;
	/**
	 * maximum population size, beyond which an epidemic occurs
	 */
	int maxpop;
	/**
	 * comfort sensitivity to small variations of the parameters
	 */
	int comfortsens;
	/*
	 * Death event parameter
	 */
	int paramDeath;
	/**
	 * Reproduction event parameter
	 */
	int paramRep;
	/*
	 * Move event parameter
	 */
	int paramMove;
	/*
	 * Number of occurred events
	 */
	int nEv = 0;
	/**
	 * current instant of time
	 */
	double currentime;
	/**
	 * grid of the simulation
	 */
	Grid grid;
	/**
	 * Pending Event Container used in the simulation
	 */
	PEC pec;
	/**
	 * list of the alive individuals
	 */
	LinkedList<Individual> indAlive;
	/**
	 * current path of the best fit individual
	 */
	BestPath bestPath;
	/**
	 * Comparator used to compute the individuals with greatest comfort
	 */
	private Comparator<Individual> comparator;
	
	/**
	 * Constructs a simulation based on the .xml file specified with the PEC given
	 * @param fileName .xml file with the simulation parameters
	 * @param pec Pending Event Container to be used in the simulation
	 */
	public Simulation(String fileName, PEC pec) {
		this.pec = pec;
		indAlive = new LinkedList<Individual>();
		currentime = 0.0;
		parse(fileName);
		bestPath = new BestPath(grid.getDest(), indAlive.peek());
		comparator = new ConfComparator();
	}
	/**
	 * Parses the parameters of the simulation present in the .xml file specified
	 * @param fileName name of the .xml file
	 */
	private void parse(String fileName) {
		// builds the SAX parser
		SAXParserFactory fact = SAXParserFactory.newInstance();
		fact.setValidating(true);

		try {
			SAXParser saxParser = fact.newSAXParser();
			DefaultHandler handler = new XmlParser(this);
			saxParser.parse(new File(fileName), handler);
		} catch (IOException e) {
			System.err.println("IO error");
		} catch (SAXException e) {
			System.err.println("Parser error");
		} catch (ParserConfigurationException e) {
			System.err.println("Parser configuration error");
		}
	}
	/**
	 * runs the simulation, retrieving and executing events from the PEC until it is empty and executing epidemics whenever the population
	 * surpasses the maximum population. At the end of the simulation prints the path of the best fit individual
	 */
	public void runSimulation() {
		while (true) {
			Event curEv;
			
			curEv = pec.popEv();
			
			if (curEv == null) {
				System.out.println("Path of the best fit individual = "+bestPath);
				return;
			}
			
			currentime = curEv.getTime();
			curEv.procEvent(this);

			if(pop > maxpop)
				indAlive = Epidemy(indAlive);
			

		}
	}
	/**
	 * Executes an epidemic, in which the 5 individuals with greatest comfort survive. The remaining individuals have a chance of survival equal to
	 * their current comfort values.
	 * @param indAlive list of the alive individuals
	 * @return new list of the alive individuals, containing the individuals that survived the epidemic
	 */
	private LinkedList<Individual> Epidemy(LinkedList<Individual> indAlive) {
		Individual survivor;
		LinkedList<Individual> survList=new LinkedList<Individual>();
		Random rand = new Random();
		
		for(int n=0; n<5;n++)
		{
			survivor=Collections.max(indAlive, comparator);
			survList.add(survivor);
			indAlive.remove(survivor);
		}
		for(int i = 0; i < indAlive.size(); i++){
			double aux = rand.nextDouble();
			Individual ind = indAlive.get(i);
			if(aux < ind.getComfort())
				survList.add(ind);
			else
				indAlive.get(i).killInd();
		}
			
		indAlive.clear();
		pop = survList.size();
		return survList;
	}
	
	

}
