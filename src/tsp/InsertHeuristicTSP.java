package tsp;
import java.util.LinkedList;
import java.util.Collections;
import java.util.List;
import graph.*;
/**
 * 
 * This heuristic iteratively appends a customer
 * to the current solution until a tour is obtained
 *
 */

public class InsertHeuristicTSP implements HeuristicTSP {
	private Graph graph;
	private LinkedList<Edge> initList;
	private LinkedList<Edge> finalList;
	private double mBestCost;
	private Edge mBestArc;
	private LinkedList<Vertex> out = new LinkedList<Vertex>();

	public double computeSolution(double[][] matrix, List<Integer> solution) {
		graph = new Graph(matrix);
		initList = graph.getEdges();
		finalList = new LinkedList<Edge>();

		//Not visited vertex
		for(Vertex v : graph.vertex)
			out.add(v);
		
		// Cycle with the longest edge
		Edge max1 = Collections.max(initList);
		Edge max2 = Edge.findEdge(initList, max1.target, max1.source);
		double totalCost = 2*max1.cost;
		finalList.add(max1);
		finalList.add(max2);
		solution.add(max1.target.id);
		solution.add(max1.source.id);
		out.remove(max1.source);
		out.remove(max1.target);

		double maxCost;
		Vertex finalVertex;
		Edge finalArc = null;
		while(!out.isEmpty()){
			maxCost = Double.MIN_VALUE;
			finalVertex = null;
			for(Vertex v : out){
				findBestFit(v);
				if(maxCost < mBestCost){
					finalVertex = v;
					maxCost = mBestCost;
					finalArc = mBestArc;
				}
			}
			solution.add(finalVertex.id);
			out.remove(finalVertex);
			Edge tmp1 = Edge.findEdge(initList, finalArc.getSource(), finalVertex);
			Edge tmp2 = Edge.findEdge(initList, finalVertex, finalArc.getTarget());
			totalCost -= finalArc.getCost();
			totalCost = tmp1.getCost() + tmp2.getCost();
			finalList.remove(finalArc);
			finalList.add(tmp1);
			finalList.add(tmp2);
		}
		return totalCost;
	}

	private void findBestFit(Vertex v){
		mBestCost = Double.MAX_VALUE;
		mBestArc = null;
		double pCost;
		
		for(Edge a : finalList){
			if(mBestArc == null){
				mBestArc = a;
			}
			Edge tmp1 = Edge.findEdge(initList, a.source, v);
			Edge tmp2 = Edge.findEdge(initList, v, a.target);
			pCost = tmp1.getCost() + tmp2.getCost();
			if( pCost < mBestCost){
				mBestArc = a;
				mBestCost = pCost;
			}			
		}
	}
}