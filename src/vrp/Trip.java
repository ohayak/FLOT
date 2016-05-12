package vrp;

import java.util.LinkedList;
import java.util.List;

import graph.Vertex;

public class Trip extends LinkedList<Vertex> {
	private static final long serialVersionUID = 1L;
	private int demand;
	
	Trip(){
		super();
	}
	
	Trip(List<Vertex> l, int d){
		super(l);
		demand = d;
	}
	
	Trip(Vertex i, int d){
		super();
		add(i);
		demand = d;
	}
	
	int getDemand(){
		return demand;
	}
	
	void addToTrip(Vertex i, int d){
		add(i);
		demand+=d;
	}
}
