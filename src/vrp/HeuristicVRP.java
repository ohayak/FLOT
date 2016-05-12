package vrp;

import java.util.List;

/**
* Interface defining the behavior of a TSP heuristic
*/

public interface HeuristicVRP {
	
	/** apply the heuristic to the VSP problem given to constructor
	 * 
	 * @param L an empty list that will be filled with the solution by the method 
	 * @return the value of the solution found
	 */
	
	public double computeSolution(List<Trip> L);

}
