package tsp;

import graph.*;
import java.util.Collections;
import java.util.List;

/**
 * This heuristic sorts the arcs by increasing value and 
 * considers each arc in turn for insertion
 * An arc is inserted if and only if it does not create a subtour.
 * The method stops when a tour is obtained.
 */
public class DecreasingArcHeuristicTSP implements HeuristicTSP {
	private Graph graph;
	
	public double computeSolution(double[][] matrix, List<Integer> solution) {
		graph = new Graph(matrix);
		Collections.sort(graph.getEdges());
		Graph tmp = new Graph(matrix.length);
		int journey = 0;
		double cost = 0;
		
		for (Edge edge : graph.getEdges()){
			tmp.addEdge(edge.source, edge.target, edge.cost);
			tmp.addEdge(edge.target, edge.source, edge.cost);
			journey++;
			if (tmp.isCyclic() && journey < tmp.getSize()){
				tmp.delEdge(edge.source, edge.target);
				tmp.delEdge(edge.source, edge.target);
				journey--;
			} else {
				cost+=edge.getCost();
			}
		}
		Vertex current = new Vertex(1);
		Vertex before = new Vertex(1);
		Vertex after = tmp.adj[current.id].getFirst();
		do{
			solution.add(current.id);
			if (tmp.adj[current.id].getFirst().equals(before))
				after = tmp.adj[current.id].getFirst();
			else
				after = tmp.adj[current.id].getLast();
			before = current;
			current = after;
		}while(current.id != 1);
		return cost;
	}
	
	/**
	 * generate an arc list from graph matrix
	 * @param matrix
	 * @return List<Edge>
	 */
	
}