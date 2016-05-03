package tsp;

import java.util.ArrayList;

class Arc implements Comparable<Arc> {
	private int source;
	private int target;
	private double cost;

	public Arc(int source, int target, double cost) {
		this.source = source;
		this.target = target;
		this.cost = cost;
	}
	
	public Arc(){
		this.source = 0;
		this.target = 0;
		this.cost = Double.MAX_VALUE;
	}
	
	public double getCost(){
		return cost;
	}
	
	public int getSource() {
		return source;
	}

	public int getTarget() {
		return target;
	}

	public String toString() {
		return "(" + source + "," + target + ")"+cost;
	}
	
	
	public static Arc findArc(ArrayList<Arc> list, int source, int target){
		for(Arc a : list){
			if(a.getSource() == source && a.getTarget() == target){
				return a;
			}
		}
		return null;
	}
	
	public static ArrayList<Arc> genArcList(double[][] matrix){
		ArrayList<Arc> list = new ArrayList<Arc>();
		for (int i = 0; i < matrix.length; ++i){
			for (int j = 0; j < matrix.length ; ++j){
				if(i==j)
					continue;
				Arc a = new Arc(i,j, matrix[i][j]);
				list.add(a);
			}
		}
		return list;
	}

	@Override
	public int compareTo(Arc a) {
		if (cost > a.getCost())
			return 1;
		else if (cost == a.getCost())
			return 0;
		else
			return -1;
	}

}