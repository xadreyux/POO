package simulation;

import java.util.Comparator;

class ConfComparator implements Comparator<Individual>{

	@Override
	public int compare(Individual x, Individual y) {
		if(x.comfort < y.comfort) return -1;
		else if(x.comfort > y.comfort)return 1;

		return 0;
	}
}
