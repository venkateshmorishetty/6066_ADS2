import java.util.Scanner;
interface Graph {
	public int V();
	public int E();
	public void addEdge(int v, int w);
	public Iterable<Integer> adj(int v);
	public boolean hasEdge(int v, int w);
}
class AdjacencyList implements Graph {
	int nodes;
	int edges;
	int count = 0;
	Bag<Integer>[] list;
	String[] keys;
	public AdjacencyList(int n, int e, String[] k) {
		nodes = n;
		edges = e;
		list = new Bag[n];
		for (int i = 0; i < n; i++) {
			list[i] = new Bag();
		}
		keys = k;
	}
	public int V() {
		return nodes;
	}
	public int E() {
		return edges;
	}
	public void addEdge(int v1, int v2) {
		if(hasEdge(v1, v2)) {
			return;
		}
		count++;
		list[v1].add(v2);
		list[v2].add(v1);
	}
	public boolean hasEdge(int v1, int v2) {
		for (int k : adj(v1)) {
			if(v2 == k) {
				return true;
			}
		}
		return false;
	}
	public Iterable<Integer> adj(int v) {
		return list[v];
	}
	public void print() {
		System.out.println(nodes + " vertices" + ", " + count + " edges");
		if(count == 0) {
			System.out.println("No edges");
			return;
		}
		for (int i  = 0; i < nodes; i++) {
			System.out.print(keys[i]+": ");
			for (int k : adj(i)) {
				System.out.print(keys[k] +" ");
			} System.out.println();
		}
	}
}
class AdjacencyMatrix implements Graph {
	int nodes;
	int edges;
	int count = 0;
	int[][] matrix;
	public AdjacencyMatrix(int n, int e) {
		nodes = n;
		edges = e;
		matrix = new int[nodes][nodes];
	}
	public int V() {
		return nodes;
	}
	public int E() {
		return edges;
	}
	public Iterable<Integer> adj(int v) {
		return null;
	}
	public void addEdge(int v1, int v2) {
		if (matrix[v1][v2] == 0 && matrix[v2][v1] == 0) {
			matrix[v1][v2] = 1;
			matrix[v2][v1] = 1;
			count++;
		}
	}
	public boolean hasEdge(int v1, int v2) {
		return matrix[v1][v2] == 1;
	}
	public void print() {
		System.out.println(nodes + " vertices" + ", " + count + " edges");
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
class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String type = sc.nextLine();
		int nodes = Integer.parseInt(sc.nextLine());
		int edges = Integer.parseInt(sc.nextLine());
		String[] keys = sc.nextLine().split(",");
		switch (type) {
		case "List":
			AdjacencyList al = new AdjacencyList(nodes, edges, keys);
			for (int i = 0; i < edges; i++) {
				String[] values = sc.nextLine().split(" ");
				if (!values[0].equals(values[1])) {
					al.addEdge(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
				}
			}
			al.print();
			break;
		case "Matrix":
			AdjacencyMatrix am = new AdjacencyMatrix(nodes, edges);
			for (int i = 0; i < edges; i++) {
				String[] values = sc.nextLine().split(" ");
				if (!values[0].equals(values[1])) {
					am.addEdge(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
				}
			}
			am.print();
			break;
		}
	}
}