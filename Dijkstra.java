package EE324StandardProjectClasses;
// Dijkstra.java
// Dijkstra implemented using a min-Priority Queue
// Similar to BFS implementation, except, prioritize nodes in queue by distance from source node
// C.McArdle, DCU, 2017

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import MyProject.PathFinderTester;;

public class Dijkstra {
	
	private boolean[] marked; 	// mark visited nodes
	private int[] edgeTo;		// store visited edges (stores the shortest-path spanning tree)
	private final int s;   		// source (first) visit vertex

	// PathFinderTester.points_hull.size()*2 represents the size of points you can go through
	// Therefore, some points might be visited multiple times, so the size should be big enough
	// to suit specific cases
	// Matrix because [original node_1] => [node_1 node_2] and so on...
	// It is a very practical way of accessing nodes.
	
	public static int[][] nodes=new int[2][PathFinderTester.points_hull.size()*2]; 
	// counter is used to go through all the hull points and perform the algorithm on them
	public static int counter= 0;
	
	
	// Dijkstra Algorithm is taken completely from the notes
	// With some small modifications at lines 70-75
	
	public Dijkstra(WeightedGraph G, int s) {
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		this.s = s; // source node
		dijkstra(G, s);
	}

	// Dijkstra's algorithm using a min-priority queue.
	private void dijkstra(WeightedGraph G, int s) {

		// initializaion of min-priority queue
		PriorityQueue<WeightedNode> pq = new PriorityQueue<WeightedNode>();
		pq.add(new WeightedNode(s, 0.0)); // add source node to queue
		marked[s] = true; // mark it as visited

		while (!pq.isEmpty()) { // similarly to BFS, remove a node and add its unvisited neighbours. repeat until queue empty.

			// remove head of queue and get node number (v) and it's weight (v_dist)
			WeightedNode head = pq.poll();
			int v = head.node();
			double v_dist = head.weight();
			marked[v] = true;

			for (int w : G.adj(v)) { // check all neighbours of v
				if (!marked[w]) { // if neighbour w not yet visited
					WeightedNode w_node = findNode(pq, w); // find this neighbour node in queue
					if (w_node != null) { // if w already in queue, check if it's weight should be updated
						// update w's weight, if path s->....->v->w is shorter path than currently known path length (weight) of w
						if ((v_dist + G.edgeWeight(v, w)) < w_node.weight()) {
							w_node.updateWeight(v_dist + G.edgeWeight(v, w));
							edgeTo[w] = v; // record child of w in spanning tree
						}
					}
					else { // w not in queue, so add it. It's weight will be path weight s->....->v->w.
						pq.add(new WeightedNode(w, v_dist + G.edgeWeight(v, w)));
						edgeTo[w] = v; // record child of w in spanning tree
						// Using the head/source point mark all the visited nodes
						nodes[0][counter]=v;
						// Visit the next unvisited node
						nodes[1][counter]=w;
						// Node that the counter is used to go through all the neighbour nodes of 
						// the specified point
						counter++;
					}
				}
			}

		}

	}

	// helper method - find a given node w in queue
	// returns null if node is not in queue
	private WeightedNode findNode(PriorityQueue<WeightedNode> pq, int w) {
		for (WeightedNode n : pq) {
			if (n.node() == w) {
				return n;
			}
		}
		return null;
	}

	// recover path to a given node v from source s, from the tree edges
	// stored in edgeTo[] array. (Same method as for BFS).
	public List<Integer> pathTo(int v) {
		List<Integer> path = new ArrayList<Integer>();
		for (int x = v; x != s; x = edgeTo[x]) {
			path.add(x);
		}
		path.add(s);
		Collections.reverse(path);
		return path;
	}
}
