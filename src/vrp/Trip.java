package vrp;

import java.util.LinkedList;
import java.util.List;

public class Trip extends LinkedList<Integer> {
	private static final long serialVersionUID = 1L;
	private int demand;
	
	Trip(List<Integer> l, int d){
		super(l);
		demand = d;
	}
	
	Trip(int i, int d){
		super();
		add((Integer)i);
		demand = d;
	}
	
	int getDemand(){
		return demand;
	}
	
	void addToTrip(int i, int d){
		add((Integer)i);
		demand+=d;
	}
}
