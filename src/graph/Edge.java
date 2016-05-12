package graph;

import java.util.LinkedList;

public class Edge implements Comparable<Edge> {
	public Vertex source;
	public Vertex target;
	public double cost;

	public Edge(Vertex source, Vertex target, double cost) {
		this.source = source;
		this.target = target;
		this.cost = cost;
	}
	
	public Edge(Integer source, Integer target, double cost) {
		this.source = new Vertex(source);
		this.target = new Vertex(target);
		this.cost = cost;
	}
	
	public Edge(){
		this.source = null;
		this.target = null;
		this.cost = Double.MAX_VALUE;
	}
	
	public double getCost(){
		return cost;
	}
	
	public Vertex getSource() {
		return source;
	}

	public Vertex getTarget() {
		return target;
	}

	public String toString() {
		return "(" + source + "," + target + "|" + cost + ")";
	}
	
	
	public static Edge findEdge(LinkedList<Edge> list, Vertex source, Vertex target){
		for(Edge a : list){
			if(a.getSource() == source && a.getTarget() == target){
				return a;
			}
		}
		return null;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		return other.source == source && other.target == target;
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