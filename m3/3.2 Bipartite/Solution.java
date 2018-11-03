import java.util.Scanner;
class Bipartite {
	/**.
	 * { bipartite variable }
	 */
	private boolean isBipartite;
	/**.
	 * { color }
	 */
    private boolean[] color;
    /**.
     * { marked }
     */
    private boolean[] marked;
    /**.
     * { edge to }
     */
    private int[] edgeTo;
    /**.
     * { cycle }
     */
    private Stack<Integer> cycle;
    /**
     * Constructs the object.
     *
     * @param      G     { graph }
     */
    public Bipartite(final Graph g) {
        isBipartite = true;
        color  = new boolean[g.vertices()];
        marked = new boolean[g.vertices()];
        edgeTo = new int[g.vertices()];
        for (int v = 0; v < g.vertices(); v++) {
            if (!marked[v]) {
                dfs(g, v);
            }
        }
    }
    private void dfs(Graph g, int v) { 
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (cycle != null) return;
            if (!marked[w]) {
                edgeTo[w] = v;
                color[w] = !color[v];
                dfs(g, w);
            }
            else if (color[w] == color[v]) {
                isBipartite = false;
                cycle = new Stack<Integer>();
                cycle.push(w);
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
            }
        }
    }
    public boolean isBipartite() {
        return isBipartite;
    }
}
class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int nodes = sc.nextInt();
		int edges = sc.nextInt();
		Graph g = new Graph(nodes);
		for (int i = 0; i < edges; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			g.addEdge(a,b);
		}
		Bipartite b = new Bipartite(g);
		if(b.isBipartite()) {
			System.out.println("Graph is bipartite");
		} else {
			System.out.println("Graph is not a bipartite");
		}
	}
}