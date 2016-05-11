package tsp;

import graph.Edge;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import graph.Edge;

/**
 * This heuristic sorts the arcs by increasing value and 
 * considers each arc in turn for insertion
 * An arc is inserted if and only if it does not create a subtour.
 * The method stops when a tour is obtained.
 */
public class DecreasingArcHeuristicTSP implements HeuristicTSP {
	
	public double computeSolution(double[][] matrix, List<Integer> solution) {
		ArrayList<Edge> arcList = Edge.genArcList(matrix);
		Collections.sort(arcList);
		Graph tmp = new Graph(matrix.length);
		int journey = 0;
		double cost = 0;
		
		for (Edge edge: arcList){
			tmp.addEdge(edge.getSource(), edge.getTarget());
			tmp.addEdge(edge.getTarget(), edge.getSource());
			journey++;
			if (tmp.isCyclic() && journey < tmp.getSize()){
				tmp.removeEdge(edge.getSource(), edge.getTarget());
				tmp.removeEdge(edge.getTarget(), edge.getSource());
				journey--;
			} else {
				cost+=edge.getCost();
			}
		}
		int current = 1;
		int before = 1;
		int after = tmp.getAdj(current).getFirst();
		do{
			solution.add(current);
			if (tmp.getAdj(current).getFirst() != before)
				after = tmp.getAdj(current).getFirst();
			else
				after = tmp.getAdj(current).getLast();
			before = current;
			current = after;
		}while(current != 1);
		return cost;
	}
	
	/**
	 * generate an arc list from graph matrix
	 * @param matrix
	 * @return List<Edge>
	 */
	
}