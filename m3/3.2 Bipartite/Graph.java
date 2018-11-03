/**
 * Class for graph.
 */
public class Graph {
    /**.
     * { vertices }
     */
    private int vertices = 0;
    /**.
     * { edges }
     */
    private int edges;
    /**.
     * { adj }
     */
    private Bag<Integer>[] adj;
    /**
     * Constructs the object.
     *
     * @param      v     { v }
     */
    public Graph(int v) {
        vertices = v;
        edges = 0;
        adj = (Bag<Integer>[]) new Bag[vertices];
        for (int i = 0; i < vertices; i++) {
            adj[i] = new Bag<Integer>();
        }
    }
    /**
     * Constructs the object.
     *
     * @param      V     { vertices }
     * @param      E     { edges }
     */
    public Graph(final int vertices,final int ed) {
        for (int i = 0; i < edges; i++) {
            int v = (int) (Math.random() * vertices);
            int w = (int) (Math.random() * vertices);
            addEdge(v, w);
        }
    }
    /**.
     * { vertices }
     *
     * @return     { count }
     */
    public int vertices() { 
        return vertices;
    }
    /**.
     * { edges }
     *
     * @return     { edges }
     */
    public int E() {
        return edges;
    }
    /**
     * Adds an edge.
     *
     * @param      v     { v }
     * @param      w     { w }
     */
    public void addEdge(final int v, final int w) {
        edges++;
        adj[v].add(w);
        adj[w].add(v);
    }
    /**.
     * { iterator }
     *
     * @param      v     { v }
     *
     * @return     { iterable }
     */
    public Iterable<Integer> adj(final int v) {
        return adj[v];
    }
}
