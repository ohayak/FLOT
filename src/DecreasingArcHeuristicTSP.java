package tsp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


/**
 * This heuristic sorts the arcs by increasing value and 
 * considers each arc in turn for insertion
 * An arc is inserted if and only if it does not create a subtour.
 * The method stops when a tour is obtained.
 */
public class DecreasingArcHeuristicTSP implements HeuristicTSP {

	/** TODO coder cette méthode */
	
	public double computeSolution(double[][] matrix, List<Integer> solution) {
		ArrayList<Arc> arcList = genArcList(matrix);
		ListIterator<Arc> arcIter = arcList.listIterator();
		LinkedList<Arc> lap = new LinkedList<Arc>();
		Arc tmp = arcIter.next();
		while(tmp != null){
			lap.add(tmp);
			if (isCyclic(lap)){
				lap.pop();
			}
			tmp = arcIter.next();
		}
		return 0.0;
	}
	
	/**
	 * generate an arc list from graph matrix
	 * @param matrix
	 * @return List<Arc>
	 */
	private ArrayList<Arc> genArcList(double[][] matrix){
		ArrayList<Arc> list = new ArrayList<Arc>();
		for (int i = 0; i < matrix.length; i++){
			for (int j = 0; j < matrix[i].length; j++){
				list.add(new Arc(i,j, matrix[i][j]));
			}
		}
		return list;
	}
			

	private void sortArcs(ArrayList<Arc> list){
		Collections.sort(list); 
	}
		

}