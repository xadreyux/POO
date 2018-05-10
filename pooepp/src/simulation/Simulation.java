package simulation;

import pec.PEC;
import pec.Event;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Simulation {
	int finalinst, pop, maxpop, comfortsens, paramDeath, paramRep, paramMove;
	double currentime;
	Grid grid;
	PEC pec;
	public LinkedList<Individual> indAlive;
	public BestPath bestPath;

	public Simulation(String fileName, PEC pec) {
		this.pec = pec;
		indAlive = new LinkedList<Individual>();
		currentime = 0.0;
		parse(fileName);
		bestPath = new BestPath(grid.getDest(), indAlive.peek());
	}

	public void parse(String fileName) {
		// builds the SAX parser
		SAXParserFactory fact = SAXParserFactory.newInstance();
		fact.setValidating(false);

		try {
			SAXParser saxParser = fact.newSAXParser();
			DefaultHandler handler = new XmlParser(fileName, this);
			saxParser.parse(new File(fileName), handler);
		} catch (IOException e) {
			System.err.println("IO error");
		} catch (SAXException e) {
			System.err.println("Parser error");
		} catch (ParserConfigurationException e) {
			System.err.println("Parser configuration error");
		}
		System.out.println(grid);
		System.out.println("" + finalinst + pop + maxpop + comfortsens + paramDeath + paramRep + paramMove);
	}

	public void runSimulation() {
		while (true) {
			Event curEv;
			double timestamp;
			Individual ind;

			curEv = pec.popEv();

			if (curEv == null) {
				System.out.println("End of Simulation");
				return;
			}

			currentime = curEv.getTime();
			ind = curEv.procEvent();

			if (curEv instanceof Move) {
				ind.calcComfort(grid.getDest(), comfortsens, grid.getCmax(), grid.getncols(), grid.getnrows());
				timestamp = ind.calcTimeStamp(paramMove);
				if (currentime + timestamp <= finalinst && currentime + timestamp < ind.getDeathTime())
					pec.addEvPEC(new Move(ind, currentime + timestamp, grid));
				bestPath.update(ind);
				
				System.out.println(ind);
			} 
			else if (curEv instanceof Reproduction) {
				Individual curInd;
				curInd = curEv.getInd();

				timestamp = curInd.calcTimeStamp(paramRep);
				if (currentime + timestamp <= finalinst && currentime + timestamp < ind.getDeathTime())
					pec.addEvPEC(new Reproduction(curInd, currentime + timestamp));

				ind.calcComfort(grid.getDest(), comfortsens, grid.getCmax(), grid.getncols(), grid.getnrows());

				timestamp = ind.calcTimeStamp(paramDeath);
				if (currentime + timestamp <= finalinst) {
					pec.addEvPEC(new Death(ind, currentime + timestamp));
				}

				ind.deathtime = currentime + timestamp;

				timestamp = ind.calcTimeStamp(paramMove);
				if (currentime + timestamp <= finalinst && currentime + timestamp < ind.deathtime)
					pec.addEvPEC(new Move(ind, currentime + timestamp, grid));

				timestamp = ind.calcTimeStamp(paramRep);
				if (currentime + timestamp <= finalinst && currentime + timestamp < ind.deathtime)
					pec.addEvPEC(new Reproduction(ind, currentime + timestamp));

				indAlive.addFirst(ind);
			} 
			else if (curEv instanceof Death) {
				indAlive.remove(ind);
			}
			
			

		}
	}
	

}
