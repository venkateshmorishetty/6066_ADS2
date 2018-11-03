import java.util.Scanner;
/**
 * Class for bipartite.
 */
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
     * @param      g     { graph }
     */
    Bipartite(final Graph g) {
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
    /**.
     * { dfs }
     * {time complexity is O(V+E)}
     * @param      g     { g }
     * @param      v     { v }
     */
    private void dfs(final Graph g, final int v) {
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (cycle != null) {
                return;
            }
            if (!marked[w]) {
                edgeTo[w] = v;
                color[w] = !color[v];
                dfs(g, w);
            } else if (color[w] == color[v]) {
                isBipartite = false;
                cycle = new Stack<Integer>();
                cycle.push(w);
                for (int i = v; i != w; i = edgeTo[i]) {
                    cycle.push(i);
                }
                cycle.push(w);
            }
        }
    }
    /**
     * Determines if bipartite.
     *
     * @return     True if bipartite, False otherwise.
     */
    public boolean isBipartite() {
        return isBipartite;
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
     * { main }
     * { time complexity is O(n)}
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        int nodes = sc.nextInt();
        int edges = sc.nextInt();
        Graph g = new Graph(nodes);
        for (int i = 0; i < edges; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            g.addEdge(a, b);
        }
        Bipartite b = new Bipartite(g);
        if (b.isBipartite()) {
            System.out.println("Graph is bipartite");
        } else {
            System.out.println("Graph is not a bipartite");
        }
    }
}
