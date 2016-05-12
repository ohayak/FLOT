package vrp;
import java.util.ArrayList;
import java.util.List;
import graph.Graph;
import graph.Vertex;

public class ClarkeWright implements HeuristicVRP{
	
	/*graph*/
	private Graph graph;
	
	/*cost*/
	private double[][] cost;
	
	/* demand table */
	private int demands[] = null;

	/* Capacity of a vehicle */
	private int capacity = -1;

	private ArrayList<Trip> L;  

	public ClarkeWright(VRPinstance vrp){
		graph = new Graph(vrp.getMatrix());
		cost = vrp.getMatrix();
		demands = vrp.getDemands();
		capacity = vrp.getCapacity();
		L = new ArrayList<Trip>();
		for (int i = 1; i < graph.getSize(); i++)
			L.add(new Trip(graph.vertex[i], demands[i]));
	}

	public double computeSolution(List<Trip> result){
		double saving, bestSaving, totalSaving = 0;
		Trip bestChoise1 = null, bestChoise2 = null;
		boolean repeat;
		do{
			bestSaving = 0;
			repeat = false;
			for(Trip t1: L){
				for(Trip t2: L){
					if (areFusable(t1,t2)){
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
				System.out.println(bestSaving);
			}
		}while(repeat);
		
		System.out.println("Total Saving= "+totalSaving);
		System.out.println("Trucks= "+L.size());
		result = L;
		return totalSaving;
	}

	private boolean areFusable(Trip t1, Trip t2){

		for(Vertex i: t1){
			if (t2.contains(i))
				return false;
		}

		if(t1.getDemand() + t2.getDemand() <= capacity)
			return true;
		else
			return false;		
	}

	private double computeSaving(Trip t1, Trip t2){
		Vertex from = t1.getLast();
		Vertex to = t2.getFirst();
		return cost[from.id][0]+cost[0][to.id]-cost[from.id][to.id];
	}

	private void fuse(Trip t1, Trip t2){
		for(Vertex i : t2){
			t1.addToTrip(i, demands[i.id]);
		}
		L.remove(t2);
	}

}