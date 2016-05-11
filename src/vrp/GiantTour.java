package vrp;

import java.util.ArrayList;

public class GiantTour {
	private int N;
	/* distance matrix */
	private double cost[][] = null;

	/* demand table */
	private int demands[] = null;

	/* Capacity of a vehicle */
	private int capacity = -1;

	private ArrayList<Trip> L;  

	public GiantTour(VRPinstance vrp){
		cost = vrp.getMatrix();
		demands = vrp.getDemands();
		capacity = vrp.getCapacity();
		N = vrp.getN();
		L = new ArrayList<Trip>();
		for (int i = 1; i < N; i++)
			L.add(new Trip(i, demands[i]));
	}

	public double computeSolution(){
		return 0;
		
	}
	
}
