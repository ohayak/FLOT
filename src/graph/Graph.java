package graph;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Graph {
	private int N;
	public Vertex[] vertex;
	public LinkedList<Vertex> adj[];
	private LinkedList<Edge> edges;
	
	public Graph(int N){
		this.N = N;
		adj = new LinkedList[N];
		edges = new LinkedList<Edge>();
		vertex = new Vertex[N];
		for (int i=0; i < N; i++){
			adj[i] = new LinkedList<Vertex>();
			vertex[i] = new Vertex(i);
		}
	}


	public Graph(double[][] matrix){
		N = matrix.length;
		adj = new LinkedList[N];
		edges = new LinkedList<Edge>();
		vertex = new Vertex[N];
		for (int i=0; i < N; i++){
			adj[i] = new LinkedList<Vertex>();
			vertex[i] = new Vertex(i);
		}

		for (Vertex i : vertex){
			for (Vertex j : vertex){
				if(!i.equals(j)){
					adj[i.id].add(j);	
					edges.add(new Edge(i,j, matrix[i.id][j.id]));
				}
			}	
		}
	}

	public void addEdge(Vertex i, Vertex j, double cost){
		Edge e = new Edge(i, j, cost);
		if(!edges.contains(e)){
			edges.add(e);
			if (adj[i.id] == null)
				adj[i.id] = new LinkedList<Vertex>();
			adj[i.id].add(j);
		}
	}

	public void delEdge(Vertex i, Vertex j){
		adj[i.id] = null;
		edges.remove(new Edge(i,j,0));

	}

	public List<Vertex> getVertexs() {
		return Arrays.asList(vertex);
	}

	public LinkedList<Edge> getEdges() {
		return edges;
	}

	public int getSize(){
		return N;
	}
	
	public void resetEdges(){
		edges.clear();
	}


	// A recursive function that uses visited[] and parent to detect
	// cycle in subgraph reachable from vertex v.
	Boolean isCyclicUtil(Vertex v, Boolean visited[], Vertex parent)
	{
		// Mark the current node as visited
		visited[v.id] = true;
		Vertex i;

		// Recur for all the vertices adjacent to this vertex
		Iterator<Vertex> it = adj[v.id].iterator();
		while (it.hasNext())
		{
			i = it.next();

			// If an adjacent is not visited, then recur for that
			// adjacent
			if (!visited[i.id])
			{
				if (isCyclicUtil(i, visited, v))
					return true;
			}

			// If an adjacent is visited and not parent of current
			// vertex, then there is a cycle.
			else if (i != parent)
				return true;
		}
		return false;
	}

	// Returns true if the graph contains a cycle, else false.
	public Boolean isCyclic()
	{
		// Mark all the vertices as not visited and not part of
		// recursion stack
		Boolean visited[] = new Boolean[N];
		for (int i = 0; i < N; i++)
			visited[i] = false;

		// Call the recursive helper function to detect cycle in
		// different DFS trees
		for (Vertex u : vertex)
			if (!visited[u.id]) // Don't recur for u if already visited
				if (isCyclicUtil(u, visited, null))
					return true;
		return false;
	}
} 
