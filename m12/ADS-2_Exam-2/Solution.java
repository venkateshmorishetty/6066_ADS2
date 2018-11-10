import java.util.Scanner;
class Edge implements Comparable<Edge> {

	private final int v;
	private final int w;
	private final double weight;
	public Edge(int v, int w, double weight) {
		if (v < 0) throw new IllegalArgumentException("vertex index must be a nonnegative integer");
		if (w < 0) throw new IllegalArgumentException("vertex index must be a nonnegative integer");
		if (Double.isNaN(weight)) throw new IllegalArgumentException("Weight is NaN");
		this.v = v;
		this.w = w;
		this.weight = weight;
	}
	public double weight() {
		return weight;
	}

	public int either() {
		return v;
	}

	public int other(int vertex) {
		if      (vertex == v) return w;
		else if (vertex == w) return v;
		else throw new IllegalArgumentException("Illegal endpoint");
	}
	@Override
	public int compareTo(Edge that) {
		return Double.compare(this.weight, that.weight);
	}
	public String toString() {
		return String.format("%d-%d %.5f", v, w, weight);
	}
}
/**
 * Class for shortest path.
 */
class ShortestPath {
	/**.
	 * { distance to }
	 */
	private double[] distTo;
	/**.
	 * { edge to }
	 */
	private Edge[] edgeTo;
	/**.
	 * { min pq }
	 */
	private IndexMinPQ<Double> pq;
	/**
	 * Constructs the object.
	 * {time complexity is O(E+V)}
	 * @param      g     { g }
	 * @param      s     { s }
	 */
	ShortestPath(final EdgeWeightedGraph g, final int s) {
		distTo = new double[g.V()];
		edgeTo = new Edge[g.V()];
		for (int v = 0; v < g.V(); v++) {
			distTo[v] = Double.POSITIVE_INFINITY;
		}
		distTo[s] = 0;
		pq = new IndexMinPQ<Double>(g.V());
		pq.insert(s, distTo[s]);
		while (!pq.isEmpty()) {
			int v = pq.delMin();
			for (Edge e : g.adj(v)) {
				relax(e, v);
			}
		}
	}
	/**.
	 * { relaxing edge }
	 * {time complexity is O(logE)}
	 * @param      e     { e }
	 * @param      v     { v }
	 */
	private void relax(final Edge e, final int v) {
		int w = e.other(v);
		if (distTo[w] > distTo[v] + e.weight()) {
			distTo[w] = distTo[v] + e.weight();
			edgeTo[w] = e;
			if (pq.contains(w)) {
				pq.decreaseKey(w, distTo[w]);
			} else {
				pq.insert(w, distTo[w]);
			}
		}
	}
	/**.
	 * { distance to }
	 *
	 * @param      v     { v }
	 *
	 * @return     { distance from source }
	 */
	public double distTo(final int v) {
		if (distTo[v] < Double.POSITIVE_INFINITY) {
			return distTo[v];
		}
		return -1;
	}
	/**
	 * Determines if it has path to.
	 *
	 * @param      v     { v }
	 *
	 * @return     True if has path to, False otherwise.
	 */
	public boolean hasPathTo(final int v) {
		return distTo[v] < Double.POSITIVE_INFINITY;
	}


	public Iterable<Integer> pathTo(int v) {
		if (!hasPathTo(v)) return null;
		Stack<Integer> path = new Stack<Integer>();
		int x = v;
		for (Edge e = edgeTo[v]; e != null; e = edgeTo[x]) {
			path.push(e.other(x));
			x = e.other(x);
		}
		return path;
	}
}
/**.
 * Class for edge weighted graph.
 */
class EdgeWeightedGraph {
	private static final String NEWLINE = System.getProperty("line.separator");

	private final int V;
	private int E;
	private Bag<Edge>[] adj;
	public EdgeWeightedGraph(int V) {
		this.V = V;
		this.E = 0;
		adj = (Bag<Edge>[]) new Bag[V];
		for (int v = 0; v < V; v++) {
			adj[v] = new Bag<Edge>();
		}
	}
	/**
	 * Returns the number of vertices in this edge-weighted graph.
	 *
	 * @return the number of vertices in this edge-weighted graph
	 */
	public int V() {
		return V;
	}

	/**
	 * Returns the number of edges in this edge-weighted graph.
	 *
	 * @return the number of edges in this edge-weighted graph
	 */
	public int E() {
		return E;
	}

	// throw an IllegalArgumentException unless {@code 0 <= v < V}
	private void validateVertex(int v) {
		if (v < 0 || v >= V)
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
	}

	/**
	 * Adds the undirected edge {@code e} to this edge-weighted graph.
	 *
	 * @param  e the edge
	 * @throws IllegalArgumentException unless both endpoints are between {@code 0} and {@code V-1}
	 */
	public void addEdge(Edge e) {
		int v = e.either();
		int w = e.other(v);
		validateVertex(v);
		validateVertex(w);
		adj[v].add(e);
		adj[w].add(e);
		E++;
	}

	/**
	 * Returns the edges incident on vertex {@code v}.
	 *
	 * @param  v the vertex
	 * @return the edges incident on vertex {@code v} as an Iterable
	 * @throws IllegalArgumentException unless {@code 0 <= v < V}
	 */
	public Iterable<Edge> adj(int v) {
		validateVertex(v);
		return adj[v];
	}

	/**
	 * Returns the degree of vertex {@code v}.
	 *
	 * @param  v the vertex
	 * @return the degree of vertex {@code v}
	 * @throws IllegalArgumentException unless {@code 0 <= v < V}
	 */
	public int degree(int v) {
		validateVertex(v);
		return adj[v].size();
	}

	/**
	 * Returns all edges in this edge-weighted graph.
	 * To iterate over the edges in this edge-weighted graph, use foreach notation:
	 * {@code for (Edge e : G.edges())}.
	 *
	 * @return all edges in this edge-weighted graph, as an iterable
	 */
	public Iterable<Edge> edges() {
		Bag<Edge> list = new Bag<Edge>();
		for (int v = 0; v < V; v++) {
			int selfLoops = 0;
			for (Edge e : adj(v)) {
				if (e.other(v) > v) {
					list.add(e);
				}
				// only add one copy of each self loop (self loops will be consecutive)
				else if (e.other(v) == v) {
					if (selfLoops % 2 == 0) list.add(e);
					selfLoops++;
				}
			}
		}
		return list;
	}

	/**
	 * Returns a string representation of the edge-weighted graph.
	 * This method takes time proportional to <em>E</em> + <em>V</em>.
	 *
	 * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,
	 *         followed by the <em>V</em> adjacency lists of edges
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(V + " " + E + NEWLINE);
		for (int v = 0; v < V; v++) {
			s.append(v + ": ");
			for (Edge e : adj[v]) {
				s.append(e + "  ");
			}
			s.append(NEWLINE);
		}
		return s.toString();
	}



	public void findMin(int source, int dest) {

	}
}
public class Solution {

	public static void main(String[] args) {
		// Self loops are not allowed...
		// Parallel Edges are allowed...
		// Take the Graph input here...
		Scanner sc = new Scanner(System.in);
		int vertices = Integer.parseInt(sc.nextLine());
		int edges = Integer.parseInt(sc.nextLine());
		EdgeWeightedGraph g = new EdgeWeightedGraph(vertices);
		for (int i = 0; i < edges; i++) {
			String[] arr = sc.nextLine().split(" ");
			int first = Integer.parseInt(arr[0]);
			int second = Integer.parseInt(arr[1]);
			double weight = Double.parseDouble(arr[2]);
			if (first != second) {
				g.addEdge(new Edge(first, second, weight));

			}
		}
		String caseToGo = sc.nextLine();
		switch (caseToGo) {
		case "Graph":
			//Print the Graph Object.
			System.out.println(g.toString());
			break;

		case "DirectedPaths":
			// Handle the case of DirectedPaths, where two integers are given.
			// First is the source and second is the destination.
			// If the path exists print the distance between them.
			// Other wise print "No Path Found."
			String[] loc = sc.nextLine().split(" ");
			ShortestPath st = new ShortestPath(g, Integer.parseInt(loc[0]));
			if (st.distTo(Integer.parseInt(loc[1])) == -1) {
				System.out.println("No Path Found.");
			} else {
				System.out.println(st.distTo(Integer.parseInt(loc[1])));
			}
			break;

		case "ViaPaths":
			// Handle the case of ViaPaths, where three integers are given.
			// First is the source and second is the via is the one where path should pass throuh.
			// third is the destination.
			// If the path exists print the distance between them.
			// Other wise print "No Path Found."
			String path = "";
			// ArrayList<Integer> vr = new ArrayList<Integer>();

			String[] arr = sc.nextLine().split(" ");
			ShortestPath st1 = new ShortestPath(g, Integer.parseInt(arr[0]));
			double distance1 = st1.distTo(Integer.parseInt(arr[1]));
			// System.out.println(st1.pathTo(Integer.parseInt(arr[1])));
			for (int i:st1.pathTo(Integer.parseInt(arr[1]))) {
				path += i+" ";
			}
			ShortestPath st2 = new ShortestPath(g, Integer.parseInt(arr[1]));
			for (int i:st2.pathTo(Integer.parseInt(arr[2]))) {
				path += i+" ";
			}
			path = path + arr[2];
			double distance2 = st2.distTo(Integer.parseInt(arr[2]));
			if (distance1 == -1 || distance2 == -1) {
				System.out.println("No Path Found.");
			} else {
				double total  = distance1 + distance2;
				System.out.println(total);
				System.out.println(path);
			}
			break;

		default:
			break;
		}

	}
}