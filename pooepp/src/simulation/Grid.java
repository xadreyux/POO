package simulation;

class Grid {
	Point[][] pGrid;

	int ncols, nrows;
	int cini, rini, cfin, rfin;
	int nSCZones, nObs, cmax;

	public Grid(int n_cols, int n_rows) {
		pGrid = new Point[n_cols][n_rows];
		ncols = n_cols;
		nrows = n_rows;
		for (int i = 0; i < n_cols; i++) {
			for (int j = 0; j < n_rows; j++) {
				pGrid[i][j] = new Point(i + 1, j + 1);
				if (i != 0)
					pGrid[i][j].setEdgeCost(3, 1);
				if (i != n_cols - 1)
					pGrid[i][j].setEdgeCost(1, 1);
				if (j != 0)
					pGrid[i][j].setEdgeCost(2, 1);
				if (j != n_rows - 1)
					pGrid[i][j].setEdgeCost(0, 1);
			}
		}
		cmax = 1;
	}

	void addObstacle(int c, int r) {
		int i = c - 1;
		int j = r - 1;
		if (i != 0) {
			pGrid[i][j].setEdgeCost(3, 0);
			pGrid[i - 1][j].setEdgeCost(1, 0);
		}
		if (i != ncols - 1) {
			pGrid[i][j].setEdgeCost(1, 0);
			pGrid[i + 1][j].setEdgeCost(3, 0);
		}
		if (j != 0) {
			pGrid[i][j].setEdgeCost(2, 0);
			pGrid[i][j - 1].setEdgeCost(0, 0);
		}
		if (j != nrows - 1) {
			pGrid[i][j].setEdgeCost(0, 0);
			pGrid[i][j + 1].setEdgeCost(2, 0);
		}
	}

	void addSCZone(int c_init, int r_init, int c_end, int r_end, int cost) {
		for (int i = c_init - 1; i <= c_end - 1; i++) {
			if (i != c_init - 1 && i != 0) {
				pGrid[i][r_init - 1].setEdgeCost(3, Math.max(pGrid[i][r_init - 1].getCost(3), cost));
				pGrid[i][r_end - 1].setEdgeCost(3, Math.max(pGrid[i][r_end - 1].getCost(3), cost));
			}
			if (i != c_end - 1 && i != ncols - 1) {
				pGrid[i][r_init - 1].setEdgeCost(1, Math.max(pGrid[i][r_init - 1].getCost(1), cost));
				pGrid[i][r_end - 1].setEdgeCost(1,  Math.max(pGrid[i][r_end - 1].getCost(1), cost));
			}
		}
		for (int j = r_init - 1; j <= r_end - 1; j++) {
			if (j != r_init - 1 && j != 0) {
				pGrid[c_init - 1][j].setEdgeCost(2, Math.max(pGrid[c_init - 1][j].getCost(2),cost));
				pGrid[c_end - 1][j].setEdgeCost(2, Math.max(pGrid[c_end - 1][j].getCost(2),cost));
			}
			if (j != r_end - 1 && j != nrows - 1) {
				pGrid[c_init - 1][j].setEdgeCost(0, Math.max(pGrid[c_init - 1][j].getCost(0),cost));
				pGrid[c_end - 1][j].setEdgeCost(0, Math.max(pGrid[c_end - 1][j].getCost(0),cost));
			}
		}
		if(cost > cmax)
			cmax = cost;
	}
	
	Point getNextPoint(Point curr, int dir) {
		switch (dir) {
		case 0:
			return pGrid[curr.c - 1][curr.r];
		case 1:
			return pGrid[curr.c][curr.r - 1];
		case 2:
			return pGrid[curr.c - 1][curr.r - 2];
		case 3:
			return pGrid[curr.c - 2][curr.r - 1];
		}
		return null;
	}
	
	Point getPoint(int c, int r) {
		return pGrid[c - 1][r - 1];
	}
	
	
	Point getDest() {
		return pGrid[cfin][rfin];
	}
	
	Point getIni() {
		return pGrid[cini][rini];
	}
	
	int getCmax() {
		return cmax;
	}
	
	int getncols() {
		return ncols;
	}
	
	int getnrows() {
		return nrows;
	}

	@Override
	public String toString() {
		String ret = new String();
		for (int j = nrows - 1; j >= 0; j--) {
			for (int i = 0; i <= ncols - 1; i++) {
				ret = ret + pGrid[i][j].toString() + "\t";
			}
			ret = ret + "\n";
		}
		return ret;
	}
}


