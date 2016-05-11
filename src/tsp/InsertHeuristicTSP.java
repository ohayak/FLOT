package tsp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import graph.Edge;

/**
 * 
 * This heuristic iteratively appends a customer
 * to the current solution until a tour is obtained
 *
 */

public class InsertHeuristicTSP implements HeuristicTSP {

	private ArrayList<Edge> initList;
	private ArrayList<Edge> finalList;
	private double mBestCost;
	private Edge mBestArc;

	public double computeSolution(double[][] matrix, List<Integer> solution) {
		initList = Edge.genArcList(matrix);
		finalList = new ArrayList<Edge>();
		ArrayList<Integer> out = new ArrayList<Integer>();

		//Not visited vertex
		for(int i = 0; i < matrix.length; i++){
			out.add((Integer)i);
		}
		
		// Cycle with the longest edge
		Edge max1 = Collections.max(initList);
		Edge max2 = Edge.findArc(initList, max1.getTarget(), max1.getSource());
		double totalCost = 2*max1.getCost();
		finalList.add(max1);
		finalList.add(max2);
		solution.add(max1.getTarget());
		solution.add(max1.getSource());
		out.remove((Integer)max1.getSource());
		out.remove((Integer)max1.getTarget());

		double maxCost;
		int finalVertex;
		Edge finalArc = null;
		while(!out.isEmpty()){
			maxCost = Double.MIN_VALUE;
			finalVertex = -1;
			for(Integer v : out){
				findBestFit(v);
				if(maxCost < mBestCost){
					finalVertex = v;
					maxCost = mBestCost;
					finalArc = mBestArc;
				}
			}
			solution.add(finalVertex);
			out.remove((Integer)finalVertex);
			Edge tmp1 = Edge.findArc(initList, finalArc.getSource(), finalVertex);
			Edge tmp2 = Edge.findArc(initList, finalVertex, finalArc.getTarget());
			totalCost -= finalArc.getCost();
			totalCost = tmp1.getCost() + tmp2.getCost();
			finalList.remove(finalArc);
			finalList.add(tmp1);
			finalList.add(tmp2);
		}
		return totalCost;
	}

	private void findBestFit(int v){
		mBestCost = Double.MAX_VALUE;
		mBestArc = null;
		double pCost;
		
		for(Edge a : finalList){
			if(mBestArc == null){
				mBestArc = a;
			}
			Edge tmp1 = Edge.findArc(initList, a.getSource(), v);
			Edge tmp2 = Edge.findArc(initList, v, a.getTarget());
			pCost = tmp1.getCost() + tmp2.getCost();
			if( pCost < mBestCost){
				mBestArc = a;
				mBestCost = pCost;
			}			
		}
	}
}