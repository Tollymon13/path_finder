// WeightedNode.java
// For Dijkstra's algorithm implementation
// Node weighted by current distance from source node
// C.McArdle, DCU, 2017
package EE324StandardProjectClasses;

public class WeightedNode implements Comparable<WeightedNode> {
	
	private int node;
	private Double weight;
	
	public WeightedNode(int n, double w) {
		node = n;
		weight = w;
	}
	
	public int node() {
		return node;
	}
	
	public double weight() {
		return weight;
	}
	
	// In Dijkstra, we may need to update a node's weight
	// as algorithm progresses
	public void updateWeight(double w) {
		weight = w;
	}
	
	@Override
	public int compareTo(WeightedNode other) {
		return weight.compareTo(other.weight);
	}
	
}