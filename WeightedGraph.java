// WeightedGraph.java
// For Dijkstra's algorithm implementation
// C.McArdle, DCU, 2017
package EE324StandardProjectClasses;

import java.util.Set;
import java.util.TreeSet;

public class WeightedGraph {

	private final int V;			// number of vertices
	private int E;					// number of edges
	private double[][] adj;			// weighted adjacency matrix
	
	public WeightedGraph(int V) {
		this.V = V;
		this.E = 0;
		adj = new double[V][V];
		for (int i=0; i<V; i++) // initialise the links
			for (int j=0; j<V; j++)
				adj[i][j] = -1; // -1 means no link
	}

	public void addEdge(int v, int w, double weight)
	{
		adj[v][w] = weight;
		adj[w][v] = weight;		// undirected graph
		E++;
	}
	
	public double edgeWeight(int v, int w) { return adj[v][w]; }
	
	// Return set of all nodes that are adjacent to node v
	public Set<Integer> adj(int v) {
		Set<Integer> adjVertices = new TreeSet<Integer>();
		for (int i=0; i<V; i++) {
			if (adj[v][i] != -1) { // undirected graph
				adjVertices.add(i);
			}
		}
		return adjVertices;
	}
	
	public int V() { return V; }
	
	public int E() { return E; }
}

