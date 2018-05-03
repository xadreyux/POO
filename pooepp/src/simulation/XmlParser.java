package simulation;

import org.xml.sax.*; // Generic API for SAX
import org.xml.sax.helpers.*; // Handlers 

public class XmlParser extends DefaultHandler {

	static String fileName;
	Simulation sim;
	String currTag;
	int cini, rini, cfin, rfin;

	public XmlParser(String _fileName, Simulation sim) {
		fileName = _fileName;
		this.sim = sim;
	}

	public void startDocument() {
		System.out.println("Beginning the parsing of" + fileName);
	}

	public void endDocument() {
		System.out.println("Parsing concluded");
	}

	public void startElement(String uri, String name, String tag, Attributes atts) {
		currTag = tag;

		System.out.println("Element <" + tag + "> ");

		if (tag.equals("simulation")) {
			for (int i = 0; i < atts.getLength(); i++) {
				System.out.println(atts.getLocalName(i) + " = " + atts.getValue(i));

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
				System.out.println(atts.getLocalName(i) + " = " + atts.getValue(i));

				if (atts.getLocalName(i).equals("colsnb")) {
					c = Integer.parseInt(atts.getValue(i));

				} else if (atts.getLocalName(i).equals("rowsnb")) {
					r = Integer.parseInt(atts.getValue(i));
				}

			}
			sim.grid = new Grid(c, r);
		} else if (tag.equals("initialpoint")) {
			for (int i = 0; i < atts.getLength(); i++) {
				System.out.println(atts.getLocalName(i) + " = " + atts.getValue(i));

				if (atts.getLocalName(i).equals("xinitial")) {
					sim.grid.cini = Integer.parseInt(atts.getValue(i));

				} else if (atts.getLocalName(i).equals("yinitial")) {
					sim.grid.rini = Integer.parseInt(atts.getValue(i));
				}
			}
		} else if (tag.equals("finalpoint")) {
			for (int i = 0; i < atts.getLength(); i++) {
				System.out.println(atts.getLocalName(i) + " = " + atts.getValue(i));

				if (atts.getLocalName(i).equals("xfinal")) {
					sim.grid.cfin = Integer.parseInt(atts.getValue(i));

				} else if (atts.getLocalName(i).equals("yfinal")) {
					sim.grid.rfin = Integer.parseInt(atts.getValue(i));
				}
			}
		} else if (tag.equals("specialcostzones")) {
			for (int i = 0; i < atts.getLength(); i++) {
				System.out.println(atts.getLocalName(i) + " = " + atts.getValue(i));

				if (atts.getLocalName(i).equals("num")) {
					sim.grid.nSCZones = Integer.parseInt(atts.getValue(i));
				}
			}
		} else if (tag.equals("zone")) {
			for (int i = 0; i < atts.getLength(); i++) {
				System.out.println(atts.getLocalName(i) + " = " + atts.getValue(i));

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
				System.out.println(atts.getLocalName(i) + " = " + atts.getValue(i));

				if (atts.getLocalName(i).equals("num")) {
					sim.grid.nObs = Integer.parseInt(atts.getValue(i));
				}
			}
		} else if (tag.equals("obstacle")) {
			for (int i = 0; i < atts.getLength(); i++) {
				System.out.println(atts.getLocalName(i) + " = " + atts.getValue(i));

				if (atts.getLocalName(i).equals("xpos")) {
					cini = Integer.parseInt(atts.getValue(i));

				} else if (atts.getLocalName(i).equals("ypos")) {
					rini = Integer.parseInt(atts.getValue(i));
				}
			}
			sim.grid.addObstacle(cini, rini);
		} else if (tag.equals("death")) {
			for (int i = 0; i < atts.getLength(); i++) {
				System.out.println(atts.getLocalName(i) + " = " + atts.getValue(i));
				
				if (atts.getLocalName(i).equals("param")) {
					sim.paramDeath = Integer.parseInt(atts.getValue(i));
				}
			}
		} else if (tag.equals("reproduction")) {
			for (int i = 0; i < atts.getLength(); i++) {
				System.out.println(atts.getLocalName(i) + " = " + atts.getValue(i));
				
				if (atts.getLocalName(i).equals("param")) {
					sim.paramRep = Integer.parseInt(atts.getValue(i));
				}
			}
		} else if (tag.equals("move")) {
			for (int i = 0; i < atts.getLength(); i++) {
				System.out.println(atts.getLocalName(i) + " = " + atts.getValue(i));
				
				if (atts.getLocalName(i).equals("param")) {
					sim.paramMove = Integer.parseInt(atts.getValue(i));
				}
			}
		}

	}

	public void characters(char[] ch, int start, int length) {
		System.out.println(currTag);
		System.out.println(new String(ch, start, length));

		if (currTag.equals("zone")) {
			int cost = Integer.parseInt(new String(ch, start, length));
			sim.grid.addSCZone(cini, rini, cfin, rfin, cost);
		}
	}

}
