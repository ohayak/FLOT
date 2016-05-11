package vrp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import tsp.HeuristicTSP;

public class GiantTour {
	private int N;

	/* distance matrix */
	private double cost[][] = null;

	/* demand table */
	private int demands[] = null;

	/* Capacity of a vehicle */
	private int capacity = -1;

	HeuristicTSP tsp;

	private ArrayList<Arc> arcList;  

	public GiantTour(HeuristicTSP h, VRPinstance vrp){
		tsp = h;
		cost = vrp.getMatrix();
		demands = vrp.getDemands();
		capacity = vrp.getCapacity();
		N = vrp.getN();
		L = new ArrayList<Trip>();
		for (int i = 1; i < N; i++)
			L.add(new Trip(i, demands[i]));
	}

	public double computeSolution(List<Trip> result){
		ArrayList<Integer> tspResult = new ArrayList<Integer>();
		double tspCost = tsp.computeSolution(cost, tspResult);
		for(Integer i: tspResult){
			for(Integer j: tspResult){
				if(j > i && onTrip(i,j))
					arcList.add(new Arc(i, j, tripCost(i,j)));
			}
		}


		return 0;
	}
	
	private ArrayList<Arc> dijkstra(ArrayList<Arc> L, int start, int dest){
		ArrayList<Integer> P = new ArrayList<Integer>();
		HashMap<Integer,Integer> d = new HashMap<Integer,Integer>();
		for(Arc arc : L){
			d.put(arc.getSource(), Integer.MAX_VALUE);
		}
		d.put((Integer)start, 0);
		
		
		
		
		
		
		
		
		
		
		
		return null;
		
	}


	private boolean onTrip(int i, int j){
		int d = 0;
		for(int v = i+1; v <= j; v++){
			d+=demands[v];
		}
		if(d <= capacity)
			return true;
		else
			return false;
	}

	private double tripCost(int i, int j){
		double d = 0;
		for(int v = i+1; v < j; v++){
			d+=cost[v][v+1];
		}
		return d;
	}

}
