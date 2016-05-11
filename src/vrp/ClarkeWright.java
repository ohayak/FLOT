package vrp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class ClarkeWright {
	Graph graph;
	private int N;
	/* distance matrix */
	private double cost[][] = null;

	/* demand table */
	private int demands[] = null;

	/* Capacity of a vehicle */
	private int capacity = -1;
	
	private double bestSaving = 0;
	
	int from = 1;
	
	public ClarkeWright(double[][] cost, int[] demands, int capacity, int N) {
		this.cost = cost;
		this.demands = demands;
		this.capacity = capacity;
		this.N = N;
		this.bestSaving = cost[0][1]+ cost[2][0] - cost[1][2];
	}
	
	public double computeSolution(VRPinstance vrp){
		graph = new Graph(N);
		System.out.println("Nb noeuds " + N);
				for (int i = 1; i < N; i++){
		
					graph.addEdge(i, 0, cost[i][0] );
				}
				
				while(isFusable()!= 0){
					//System.out.println("Fusionner les noeuds "+ from );
					System.out.println("Fusing in " + from);
					graph.addEdge(from, from+1, cost[from][from+1]);
					graph.removeEdge(0, from);
					graph.removeEdge(from + 1, 0);
					from = 1;
				}
				//System.out.println();
				return 0;
			}
			
	private int isFusable(){
		LinkedList<Integer> adj;
		int d = 0;
		int tmp = from;
		bestSaving = 0;
		for(int i = 1; i < N - 1; i++){	
			adj = graph.getAdj(i);
			if (!adj.contains(i+1)){
				//i++;
				
				while (graph.getAdj(i).contains((Integer)(i+1))){	
						d+=demands[i+1];
						i++;
						}
				if(i > N - 2){
					if ( from != tmp){
						System.out.println("Sortie 1 : from");
						return from;
					}else{
						System.out.println("Sortie 1 : 0");
						return 0;
					}
				}
					
				if (demands[i] + demands[i+1] + d < capacity){
						if (bestSaving < cost[0][i]+ cost[i + 1][0] - cost[i][i +1]){
							bestSaving = cost[0][i]+ cost[i + 1][0] - cost[i][i +1];
							from = i;
						}
					}else{
						d=0;
						continue;
					}
				}else{
					d+=demands[i];
					continue;
				}
		}
		System.out.println("BestSaving is now " + bestSaving);
		if(from != tmp){
			System.out.println("Sortie 2 : from");
			return from;
		}else{
			System.out.println("Sortie 2 : 0");
			return 0;
		}
	}

}