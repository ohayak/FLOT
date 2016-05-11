package vrp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Graph {
	private int V;   // No. of vertices
	private LinkedList<Integer> adj[]; // Adjacency List Representation
	private ArrayList<Arc> arcList = new ArrayList<Arc>();
	
	public Graph(int v) {
		V = v;
		adj = new LinkedList[v];
		for(int i=0; i<v; ++i)
			adj[i] = new LinkedList<Integer>();
	}

	// Function to add an edge into the graph
	public void addEdge(int v,int w, double cost) {
		adj[v].add(w);
		arcList.add(new Arc(v, w, cost));
		//adj[w].add(v);
	}
	
	public void removeEdge(int v, int w){
		adj[v].remove((Integer)w);
		Arc a = Arc.findArc(arcList, v, w);
		arcList.remove(a);
		// adj[w].remove((Integer)v);
		
	}
	
	public LinkedList<Integer> getAdj(int e){
		return adj[e];
	}
	
	public int getSize(){
		return V;
	}
	
	public Arc getArc(int v, int w){
		return Arc.findArc(arcList, v, w);
	}

	// A recursive function that uses visited[] and parent to detect
	// cycle in subgraph reachable from vertex v.
	Boolean isCyclicUtil(int v, Boolean visited[], int parent)
	{
		// Mark the current node as visited
		visited[v] = true;
		Integer i;

		// Recur for all the vertices adjacent to this vertex
		Iterator<Integer> it = adj[v].iterator();
		while (it.hasNext())
		{
			i = it.next();

			// If an adjacent is not visited, then recur for that
			// adjacent
			if (!visited[i])
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
		Boolean visited[] = new Boolean[V];
		for (int i = 0; i < V; i++)
			visited[i] = false;

		// Call the recursive helper function to detect cycle in
		// different DFS trees
		for (int u = 0; u < V; u++)
			if (!visited[u]) // Don't recur for u if already visited
				if (isCyclicUtil(u, visited, -1))
					return true;
		return false;
	}

}
