package graph;

import java.util.ArrayList;

public class Edge implements Comparable<Edge> {
	public Integer source;
	public Integer target;
	public double cost;

	public Edge(Integer source, Integer target, double cost) {
		this.source = source;
		this.target = target;
		this.cost = cost;
	}
	
	public Edge(){
		this.source = 0;
		this.target = 0;
		this.cost = Double.MAX_VALUE;
	}
	
	public double getCost(){
		return cost;
	}
	
	public Integer getSource() {
		return source;
	}

	public Integer getTarget() {
		return target;
	}

	public String toString() {
		return "(" + source + "," + target + ")"+cost;
	}
	
	
	public static Edge findArc(ArrayList<Edge> list, Integer source, Integer target){
		for(Edge a : list){
			if(a.getSource() == source && a.getTarget() == target){
				return a;
			}
		}
		return null;
	}
	
	public static ArrayList<Edge> genArcList(double[][] matrix){
		ArrayList<Edge> list = new ArrayList<Edge>();
		for (Integer i = 0; i < matrix.length; ++i){
			for (Integer j = 0; j < matrix.length ; ++j){
				if(i==j)
					continue;
				Edge a = new Edge(i,j, matrix[i][j]);
				list.add(a);
			}
		}
		return list;
	}

	@Override
	public int compareTo(Edge a) {
		if (cost > a.getCost())
			return 1;
		else if (cost == a.getCost())
			return 0;
		else
			return -1;
	}

}