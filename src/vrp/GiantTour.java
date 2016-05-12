package vrp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import graph.Dijkstra;
import graph.Graph;
import graph.Vertex;
import tsp.HeuristicTSP;

public class GiantTour implements HeuristicVRP{
	/*graph*/
	private int N;

	private Graph g;

	/*cost*/
	private double[][] cost;

	/* demand table */
	private int demands[] = null;

	/* Capacity of a vehicle */
	private int capacity = -1;

	private ArrayList<Trip> L;

	private ArrayList<Integer> tspTour;

	double tspCost;

	public GiantTour(HeuristicTSP h,VRPinstance vrp){
		N = vrp.getN();
		g = new Graph(N);
		cost = vrp.getMatrix();
		demands = vrp.getDemands();
		capacity = vrp.getCapacity();
		L = new ArrayList<Trip>();
		tspTour = new ArrayList<Integer>();
		tspCost = h.computeSolution(cost, tspTour);

	}

	public double computeSolution(List<Trip> result){
		// each edge is a possible trip with, the cost is the total cost of the trip
		for(Integer i : tspTour){
			for(Integer j : tspTour){
				if(tspTour.indexOf(j) >= tspTour.indexOf(i) && onTrip(g.vertex[i], g.vertex[j]))
					g.addEdge(g.vertex[i], g.vertex[j], tripCost(g.vertex[i], g.vertex[j]));
			}
		}
		Dijkstra algo = new Dijkstra(g);
		algo.execute(g.vertex[0]);
		LinkedList<Vertex> path = algo.getPath(g.vertex[N-1]);
		System.out.println(tspTour);
		System.out.println(path);
		Iterator itr = path.iterator();
		Vertex last = (Vertex) itr.next();
		Vertex next;
		do{
			next = (Vertex) itr.next();
			L.add(genTrip(last,next));
		}while(itr.hasNext());
		
		System.out.println("Trucks= "+L.size());
		return 0;
	}

	private Trip genTrip(Vertex i, Vertex j){
		int k = 0;		
		while(tspTour.get(k) != i.id)
			k++;

		Vertex v;
		Trip t = new Trip();
		for (int l = k+1; l < N ; l++){
			v = g.vertex[tspTour.get(l)];
			t.addToTrip(v, demands[v.id]);
			if(v.id == j.id)
				break;
		}
		return t;
	}

	private boolean onTrip(Vertex i, Vertex j){
		int d = 0;
		if(i.id == j.id)
			return demands[i.id] <= capacity;
		for(int v = i.id; v <= j.id; v++){
			d+=demands[v];
		}
		if(d <= capacity)
			return true;
		else
			return false;
	}

	private double tripCost(Vertex i, Vertex j){
		double d = 0;
		Integer start = tspTour.indexOf((Integer)i.id);
		Integer finish = tspTour.indexOf((Integer)j.id);
		Integer next;
		for(int v = start; v < finish; v++){
			next = tspTour.get(v+1);
			d+=cost[v][next];
		}
		d+= cost[0][i.id];
		d+= cost[j.id][0];
		return d;
	}

}
