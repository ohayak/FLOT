package graph;

public class Vertex {
	final public Integer id;


	public Vertex(int id) {
		this.id = id;
	}
	public Integer getId() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		return other.id == id;
	}

	@Override
	public String toString() {
		return id.toString();
	}

} 