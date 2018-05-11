package pec;

import java.util.Comparator;
/**
 * This class implements the Comparator and is used to compare the time of occurrence of two events
 * 
 *
 */
class TimeComparator implements Comparator<Event>{
	

	@Override
	public int compare(Event x, Event y) {
		if(x.timestamp < y.timestamp) return -1;
		else if(x.timestamp > y.timestamp)return 1;

		return 0;
		
		
	}

}
