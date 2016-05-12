package vrp;

import java.util.ArrayList;
import java.util.List;

import tsp.InsertHeuristicTSP;

public class MainVRP {
	
	public static void main(String args[]) {
		List<Trip> L = new ArrayList<Trip>();
		VRPinstance instance = null;
		try {
			instance = new VRPinstance(args[0]);
		} catch (java.io.FileNotFoundException e) {
			System.out.println("File not found");
		}
		System.out.println("Size:" + instance.getN());
		System.out.println("Matrice de distances");
		double[][] matrix = instance.getMatrix();	
		for (int i = 0; i < instance.getN(); i++) {
			System.out.print(matrix[i][0] + " ");
		}
		System.out.println();
		System.out.println("Demandes");
		for(int d : instance.getDemands()) System.out.print(d + " ");
		System.out.println();
		System.out.println("Capacite : " + instance.getCapacity());
		
		//ClarkeWright cw = new ClarkeWright(instance);
		InsertHeuristicTSP tsp = new InsertHeuristicTSP();
		GiantTour gt = new GiantTour(tsp, instance);
		
		gt.computeSolution(L);
	}
}
