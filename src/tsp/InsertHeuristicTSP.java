package tsp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * This heuristic iteratively appends a customer
 * to the current solution until a tour is obtained
 *
 */

public class InsertHeuristicTSP implements HeuristicTSP {

	private ArrayList<Arc> initList;
	private ArrayList<Arc> finalList;

	public double computeSolution(double[][] matrix, List<Integer> solution) {
		initList = Arc.genArcList(matrix);
		finalList = new ArrayList<Arc>();
		ArrayList<Integer> out = new ArrayList<Integer>();

		//Not visited vertex
		for(int i = 0; i < matrix.length; i++){
			out.add((Integer)i);
		}

		// Cycle with the longest edge
		Arc max1 = Collections.max(initList);
		Arc max2 = Arc.findArc(initList, max1.getTarget(), max1.getSource());
		double totalCost = 2*max1.getCost();
		finalList.add(max1);
		finalList.add(max2);
		solution.add(max1.getTarget());
		solution.add(max1.getSource());
		out.remove(max1.getSource());
		out.remove(max1.getTarget());
		//		initList.remove(Arc.findArc(initList, max.getSource(), max.getTarget()));
		//		initList.remove(Arc.findArc(initList, max.getTarget(), max.getSource()));

		double bestCost, maxCost;
		int finalVertex;
		Arc bestArc = null;
		Arc finalArc = null;
		while(!out.isEmpty()){
			maxCost = Double.MAX_VALUE;
			finalVertex = -1;
			for(Integer v : out){
				bestCost = maxCost;
				findBestFit(v, bestCost, bestArc);
				if(maxCost < bestCost){
					finalVertex = v;
					maxCost = bestCost;
					finalArc = bestArc;
				}
			}
			solution.add(finalVertex);
			out.remove((Integer)finalVertex);
			Arc tmp1 = Arc.findArc(initList, finalArc.getSource(), finalVertex);
			Arc tmp2 = Arc.findArc(initList, finalVertex, finalArc.getTarget());
			totalCost -= finalArc.getCost();
			totalCost = tmp1.getCost() + tmp2.getCost();
			finalList.remove(finalArc);
			finalList.add(tmp1);
			finalList.add(tmp2);
			//			initList.remove(tmp1);
			//			initList.remove(tmp2);
		}

		return totalCost;
	}

	private void findBestFit(int v, double bestCost, Arc bestArc){
		bestArc = null;
		double pCost;
		for(Arc a : finalList){
			if(bestArc == null){
				bestArc = a;
			}
			System.out.println(a+"<<<");
			Arc tmp1 = Arc.findArc(initList, a.getSource(), v);
			Arc tmp2 = Arc.findArc(initList, v, a.getTarget());
			System.out.println(tmp1);
			System.out.println(tmp2);
			pCost = tmp1.getCost() + tmp2.getCost();
			if( pCost <= bestCost){
				bestArc = a;
				bestCost = pCost;
			}			
		}		
	}
}