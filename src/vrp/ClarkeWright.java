package vrp;
import java.util.ArrayList;
import java.util.List;

public class ClarkeWright {
	private int N;
	/* distance matrix */
	private double cost[][] = null;

	/* demand table */
	private int demands[] = null;

	/* Capacity of a vehicle */
	private int capacity = -1;

	private ArrayList<Trip> L;  

	public ClarkeWright(VRPinstance vrp){
		cost = vrp.getMatrix();
		demands = vrp.getDemands();
		capacity = vrp.getCapacity();
		N = vrp.getN();
		L = new ArrayList<Trip>();
		for (int i = 1; i < N; i++)
			L.add(new Trip(i, demands[i]));
	}

	public double computeSolution(List<Trip> result){
		double saving, bestSaving, totalSaving = 0;
		Trip bestChoise1 = null, bestChoise2 = null;
		boolean repeat;
		do{
			bestSaving = Double.NEGATIVE_INFINITY;
			repeat = false;
			for(Trip t1: L){
				for(Trip t2: L){
					if (t1!=t2 && areFusable(t1,t2)){
						repeat = true;
						saving = computeSaving(t1,t2);
						if(saving > bestSaving){
							bestSaving = saving;
							bestChoise1 = t1;
							bestChoise2 = t2;
						}

					}
				}
			}
			if(repeat){
				System.out.println("Fuse "+bestChoise1+" & "+bestChoise2);
				fuse(bestChoise1, bestChoise2);
				totalSaving+= bestSaving;
			}
		}while(repeat);
		
		System.out.println("Total Saving= "+totalSaving);
		result = L;
		return totalSaving;
	}

	private boolean areFusable(Trip t1, Trip t2){

		for(Integer i: t1){
			if (t2.contains(i))
				return false;
		}

		if(t1.getDemand() + t2.getDemand() <= capacity)
			return true;
		else
			return false;		
	}

	private double computeSaving(Trip t1, Trip t2){
		int from = t1.getLast();
		int to = t2.getFirst();
		return cost[from][0]+cost[0][to]-cost[from][to];
	}

	private void fuse(Trip t1, Trip t2){
		for(Integer i : t2){
			t1.addToTrip(i, demands[i]);
		}
		L.remove(t2);
	}

}