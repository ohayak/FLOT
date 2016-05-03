package vrp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class ClarkeWright {
	Graph graph;
	int N;
	/* distance matrix */
	private double cost[][] = null;

	/* demand table */
	private int demands[] = null;

	/* Capacity of a vehicle */
	private int capacity = -1;
	
	
	public double computeSolution(VRPinstance vrp){
		N = vrp.getSize();
		cost = vrp.getMatrix();
		demands = vrp.getDemands();
		capacity = vrp.getCapacity();
		graph = new Graph(N);
		
		for (int i = 1; i < N; i++){
			graph.addEdge(0, i, cost[0][i] );
			graph.addEdge(i, 0, cost[i][0] );
		}
		
		while(isFusable()){
			
			
		}
		
		
		return 0;
	}
	
	private Boolean isFusable(){
		LinkedList<Integer> adj;
		int d = 0;
		for(int i = 1; i < N; i++){
			adj = graph.getAdj(i);
			if (!adj.contains((Integer)(i+1))){
				if (demands[i] + demands[i+1] + d < capacity){
					return true;
				}else{
					d=0;
					continue;
				}
			}else{
				d+=demands[i];
				continue;
			}
		}
		return false;
	}

}