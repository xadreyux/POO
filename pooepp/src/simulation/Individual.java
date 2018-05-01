package simulation;

import java.util.Iterator;
import java.util.LinkedList;

public class Individual {
	int comfort;
	int size;
	int i;
	LinkedList<Point> path = new LinkedList<Point>();
	public Individual(int curcomf, LinkedList<Point> curpath)
	{
		this.comfort=curcomf;
		this.path=curpath;
	}
	public int calcComfort()
	{
		for(Iterator<Point>iter=path.iterator(); iter.hasNext();)
		{
			
		}
		return 1;
	}
}
