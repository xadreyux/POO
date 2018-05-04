package simulation;

import pec.PEC;

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
	int currentime = 0;
	Grid grid;
	PEC pec;
	LinkedList<Individual> indAlive;

	public Simulation(String fileName, PEC pec) {
		parse(fileName);
		this.pec = pec;
		indAlive = new LinkedList<Individual>();
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
	
	

}
