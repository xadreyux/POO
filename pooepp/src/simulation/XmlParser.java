package simulation;

import org.xml.sax.*; // Generic API for SAX
import org.xml.sax.helpers.*; // Handlers 
/**
 * Class that extends the class DefaultHandler and is responsible for parsing the simulation's parameters from the .xml file
 *
 *
 */
class XmlParser extends DefaultHandler {
	/**
	 * simulation to be ran
	 */
	private Simulation sim;
	/**
	 * current element being parsed
	 */
	private String currTag;
	/**
	 * column of obstacle or first corner of special cost zone
	 */
	private int cini;
	/**
	 * row of obstacle or first corner of special cost zone
	 */
	private int rini;
	/**
	 * column of second corner of special cost zone
	 */
	private int cfin;
	/**
	 * row of second corner of special cost zone
	 */
	private int rfin;
	/**
	 * Constructs a xml parser with the specified simulation
	 * @param sim simulation to be parsed to
	 */
	public XmlParser(Simulation sim) {
		this.sim = sim;
	}

	public void startDocument() {
	}

	public void endDocument() {
		double timestamp;
		Individual ind = null;
		for(int i = 0; i < sim.pop; i++) {
			ind = new Individual(sim.grid.getIni());
			sim.indAlive.addFirst(ind);
			ind.calcComfort(sim.grid.getDest(), sim.comfortsens, sim.grid.getCmax(), sim.grid.getncols(), sim.grid.getnrows());
			timestamp = ind.calcTimeStamp(sim.paramDeath);
			if(timestamp <= sim.finalinst) 
				sim.pec.addEvPEC(new Death(ind, timestamp));
			
			ind.deathtime = timestamp;
			timestamp = ind.calcTimeStamp(sim.paramMove);
			if(timestamp <= sim.finalinst && timestamp < ind.deathtime)
				sim.pec.addEvPEC(new Move(ind, timestamp, sim.grid));
			timestamp = ind.calcTimeStamp(sim.paramRep);
			if(timestamp <= sim.finalinst && timestamp < ind.deathtime)
				sim.pec.addEvPEC(new Reproduction(ind, timestamp));
				
		}
		for(int i = 1; i <= 20; i++) {
			timestamp = sim.finalinst/20.0 * i;
			sim.pec.addEvPEC(new Observation(timestamp, i));
		}
	}

	public void startElement(String uri, String name, String tag, Attributes atts) {
		currTag = tag;

		if (tag.equals("simulation")) {
			for (int i = 0; i < atts.getLength(); i++) {

				if (atts.getLocalName(i).equals("finalinst")) {
					sim.finalinst = Integer.parseInt(atts.getValue(i));

				} else if (atts.getLocalName(i).equals("initpop")) {
					sim.pop = Integer.parseInt(atts.getValue(i));

				} else if (atts.getLocalName(i).equals("maxpop")) {
					sim.maxpop = Integer.parseInt(atts.getValue(i));

				} else if (atts.getLocalName(i).equals("comfortsens")) {
					sim.comfortsens = Integer.parseInt(atts.getValue(i));
				}
			}
		} else if (tag.equals("grid")) {
			int c = 0, r = 0;
			for (int i = 0; i < atts.getLength(); i++) {

				if (atts.getLocalName(i).equals("colsnb")) {
					c = Integer.parseInt(atts.getValue(i));

				} else if (atts.getLocalName(i).equals("rowsnb")) {
					r = Integer.parseInt(atts.getValue(i));
				}

			}
			sim.grid = new Grid(c, r);
		} else if (tag.equals("initialpoint")) {
			for (int i = 0; i < atts.getLength(); i++) {

				if (atts.getLocalName(i).equals("xinitial")) {
					sim.grid.cini = Integer.parseInt(atts.getValue(i)) - 1;

				} else if (atts.getLocalName(i).equals("yinitial")) {
					sim.grid.rini = Integer.parseInt(atts.getValue(i)) - 1;
				}
			}
		} else if (tag.equals("finalpoint")) {
			for (int i = 0; i < atts.getLength(); i++) {

				if (atts.getLocalName(i).equals("xfinal")) {
					sim.grid.cfin = Integer.parseInt(atts.getValue(i)) - 1;

				} else if (atts.getLocalName(i).equals("yfinal")) {
					sim.grid.rfin = Integer.parseInt(atts.getValue(i)) - 1;
				}
			}
		} else if (tag.equals("specialcostzones")) {
			for (int i = 0; i < atts.getLength(); i++) {

				if (atts.getLocalName(i).equals("num")) {
					sim.grid.nSCZones = Integer.parseInt(atts.getValue(i));
				}
			}
		} else if (tag.equals("zone")) {
			for (int i = 0; i < atts.getLength(); i++) {

				if (atts.getLocalName(i).equals("xinitial")) {
					cini = Integer.parseInt(atts.getValue(i));

				} else if (atts.getLocalName(i).equals("yinitial")) {
					rini = Integer.parseInt(atts.getValue(i));

				} else if (atts.getLocalName(i).equals("xfinal")) {
					cfin = Integer.parseInt(atts.getValue(i));

				} else if (atts.getLocalName(i).equals("yfinal")) {
					rfin = Integer.parseInt(atts.getValue(i));
				}
			}
		} else if (tag.equals("obstacles")) {
			for (int i = 0; i < atts.getLength(); i++) {

				if (atts.getLocalName(i).equals("num")) {
					sim.grid.nObs = Integer.parseInt(atts.getValue(i));
				}
			}
		} else if (tag.equals("obstacle")) {
			for (int i = 0; i < atts.getLength(); i++) {

				if (atts.getLocalName(i).equals("xpos")) {
					cini = Integer.parseInt(atts.getValue(i));

				} else if (atts.getLocalName(i).equals("ypos")) {
					rini = Integer.parseInt(atts.getValue(i));
				}
			}
			sim.grid.addObstacle(cini, rini);
		} else if (tag.equals("death")) {
			for (int i = 0; i < atts.getLength(); i++) {
				
				if (atts.getLocalName(i).equals("param")) {
					sim.paramDeath = Integer.parseInt(atts.getValue(i));
				}
			}
		} else if (tag.equals("reproduction")) {
			for (int i = 0; i < atts.getLength(); i++) {
				
				if (atts.getLocalName(i).equals("param")) {
					sim.paramRep = Integer.parseInt(atts.getValue(i));
				}
			}
		} else if (tag.equals("move")) {
			for (int i = 0; i < atts.getLength(); i++) {
				
				if (atts.getLocalName(i).equals("param")) {
					sim.paramMove = Integer.parseInt(atts.getValue(i));
				}
			}
		}

	}

	public void characters(char[] ch, int start, int length) {

		if (currTag.equals("zone")) {
			int cost = Integer.parseInt(new String(ch, start, length));
			sim.grid.addSCZone(cini, rini, cfin, rfin, cost);
		}
	}

}
