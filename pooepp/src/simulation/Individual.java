package simulation;

import java.util.Iterator;
import java.util.LinkedList;

public class Individual {
	int comfort;
	float deathtime;
	LinkedList<Point> path;

	public Individual(Point point) {
		path = new LinkedList<Point>();
		path.add(point);
	}

	public void calcComfort() {
		for (Iterator<Point> iter = path.iterator(); iter.hasNext();) {

		}
	}

	public void addPoint(Point point) {
		path.add(point);
	}

	public float calcTimeStamp(int param) {
		return 1;
	}
}
