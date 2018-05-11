package simulation;

import java.util.Comparator;
/**
 * This class implements the Comparator and is used to compare the comfort two individuals
 *
 *
 */
class ConfComparator implements Comparator<Individual>{

	/**
	 * 
	 */
	@Override
	public int compare(Individual x, Individual y) {
		if(x.comfort < y.comfort) return -1;
		else if(x.comfort > y.comfort)return 1;

		return 0;
	}
}
