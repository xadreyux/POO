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

public class Simulation {
	int finalinst, pop, maxpop, comfortsens, paramDeath, paramRep, paramMove;
	int nEv = 0;
	double currentime;
	Grid grid;
	PEC pec;
	LinkedList<Individual> indAlive;
	BestPath bestPath;
	private Comparator<Individual> comparator;
	
	/**
	 * 
	 * @param fileName 
	 * @param pec
	 */

	public Simulation(String fileName, PEC pec) {
		this.pec = pec;
		indAlive = new LinkedList<Individual>();
		currentime = 0.0;
		parse(fileName);
		bestPath = new BestPath(grid.getDest(), indAlive.peek());
		comparator = new ConfComparator();
	}

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
