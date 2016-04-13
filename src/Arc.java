package tsp;

class Arc implements Comparable<Arc> {
	private int source;
	private int target;
	private double cost;

	public Arc(int source, int target, double cost) {
		this.source = source;
		this.target = target;
		this.cost = cost;
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
		return "(" + source + "," + target + ")";
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