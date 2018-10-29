import java.util.Scanner;
interface Graph {
	int vertices();
	int edges();
	void addEdge(int v, int w);
	Iterable<Integer> adj(int v);
	boolean hasEdge(int v, int w);
}
/**
 * List of adjacencies.
 */
class AdjacencyList implements Graph {
	/**
	 * { number of vertices }.
	 */
	private int nodes;
	/**
	 * { number of edges }.
	 */
	private int edges;
	/**
	 * { count of valid edges }.
	 */
	private int count = 0;
	/**
	 * { bag array }.
	 */
	private Bag<Integer>[] list;
	/**
	 * { keys }.
	 */
	private String[] keys;
	/**
	 * Constructs the object.
	 *
	 * @param      n     { number of vertices }
	 * @param      e     { number of edges }
	 * @param      k     { keys }
	 */
	AdjacencyList(final int n, final int e, final String[] k) {
		nodes = n;
		edges = e;
		list = new Bag[n];
		for (int i = 0; i < n; i++) {
			list[i] = new Bag();
		}
		keys = k;
	}
	/**
	 * { total vertices }.
	 *
	 * @return     { count of vertices }
	 */
	public int vertices() {
		return nodes;
	}
	/**.
	 * { edges }
	 *
	 * @return     { count of edges }
	 */
	public int edges() {
		return edges;
	}
	/**
	 * Adds an edge.
	 *
	 * @param      v1    The v 1
	 * @param      v2    The v 2
	 */
	public void addEdge(final int v1, final int v2) {
		if (!hasEdge(v1, v2)) {
			count++;
		}
		list[v1].add(v2);
		list[v2].add(v1);
	}
	/**
	 * Determines if it has edge.
	 *
	 * @param      v1    The v 1
	 * @param      v2    The v 2
	 *
	 * @return     True if has edge, False otherwise.
	 */
	public boolean hasEdge(final int v1, final int v2) {
		for (int k : adj(v1)) {
			if (v2 == k) {
				return true;
			}
		}
		return false;
	}
	/**
	 * { adjecents connected to vertex }.
	 *
	 * @param      v     { vertex }
	 *
	 * @return     { return adjecent vertices for given vertex }
	 */
	public Iterable<Integer> adj(final int v) {
		return list[v];
	}
	/**.
	 * { print the graph }
	 */
	public void print() {
		System.out.println(nodes + " vertices"
			+ ", " + count + " edges");
		if (count == 0) {
			System.out.println("No edges");
			return;
		}
		for (int i  = 0; i < nodes; i++) {
			System.out.print(keys[i] + ": ");
			for (int k : adj(i)) {
				System.out.print(keys[k] + " ");
			} System.out.println();
		}
	}
}
/**
 * Class for adjacency matrix.
 */
class AdjacencyMatrix implements Graph {
	/**
	 * { nodes }.
	 */
	private int nodes;
	/**.
	 * { edges }
	 */
	private int edges;
	/**
	 * { count of valid edges }.
	 */
	private int count = 0;
	/**.
	 * { matrix }
	 */
	private int[][] matrix;
	/**
	 * Constructs the object.
	 *
	 * @param      n     { nodes }
	 * @param      e     { edges }
	 */
	AdjacencyMatrix(final int n, final int e) {
		nodes = n;
		edges = e;
		matrix = new int[nodes][nodes];
	}
	/**
	 * { tota number of vertices }.
	 *
	 * @return     { count of vertices }
	 */
	public int vertices() {
		return nodes;
	}
	/**
	 * { total number of edges }.
	 *
	 * @return     { count of edges }
	 */
	public int edges() {
		return edges;
	}
	/**
	 * { adjecent vertices for given vertex }.
	 *
	 * @param      v     { vertex }
	 *
	 * @return     { adjecent vertices for given vertex }
	 */
	public Iterable<Integer> adj(final int v) {
		return null;
	}
	/**
	 * Adds an edge.
	 *
	 * @param      v1    The v 1
	 * @param      v2    The v 2
	 */
	public void addEdge(final int v1, final int v2) {
		if (matrix[v1][v2] == 0 && matrix[v2][v1] == 0) {
			matrix[v1][v2] = 1;
			matrix[v2][v1] = 1;
			count++;
		}
	}
	/**
	 * Determines if it has edge.
	 *
	 * @param      v1    The v 1
	 * @param      v2    The v 2
	 *
	 * @return     True if has edge, False otherwise.
	 */
	public boolean hasEdge(final int v1, final int v2) {
		return matrix[v1][v2] == 1;
	}
	/**.
	 * { print the graph }
	 */
	public void print() {
		System.out.println(nodes + " vertices"
			+ ", " + count + " edges");
		if (count == 0) {
			System.out.println("No edges");
			return;
		}
		for (int i = 0; i < nodes; i++) {
			for (int j = 0; j < nodes; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

}
/**
 * Class for solution.
 */
final class Solution {
	/**
	 * Constructs the object.
	 */
	private Solution() {
		//constructor
	}
	/**.
	 * { main function }
	 *
	 * @param      args  The arguments
	 */
	public static void main(final String[] args) {
		Scanner sc = new Scanner(System.in);
		String type = sc.nextLine();
		int nodes = Integer.parseInt(sc.nextLine());
		int edges = Integer.parseInt(sc.nextLine());
		String[] keys = sc.nextLine().split(",");
		switch (type) {
		case "List":
			AdjacencyList al = new AdjacencyList(
				nodes, edges, keys);
			for (int i = 0; i < edges; i++) {
				String[] values = sc.nextLine().split(" ");
				if (!values[0].equals(values[1])) {
					al.addEdge(Integer.parseInt(values[0]),
						Integer.parseInt(values[1]));
				}
			}
			al.print();
			break;
		case "Matrix":
			AdjacencyMatrix am = new AdjacencyMatrix(nodes, edges);
			for (int i = 0; i < edges; i++) {
				String[] values = sc.nextLine().split(" ");
				if (!values[0].equals(values[1])) {
					am.addEdge(Integer.parseInt(values[0]),
						Integer.parseInt(values[1]));
				}
			}
			am.print();
			break;
		default:
			break;
		}
	}
}