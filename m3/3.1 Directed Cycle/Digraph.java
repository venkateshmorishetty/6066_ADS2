public class Digraph {
    /**
     * { vertices }.
     */
    private final int vertices;
    /**.
     * { edges }
     */
    private int edges;
    /**
     * { digraph }.
     */
    private Bag<Integer>[] digraph;
    /**
     * Constructs the object.
     *
     * @param      v     { vertices}
     */
    public Digraph(int v) {
        vertices = v;
        edges = 0;
        digraph = new Bag[vertices];
        for (int i = 0; i < vertices; i++) {
            digraph[i] = new Bag<Integer>();
        }
    }
    /**
     * { number of vertices }.
     *
     * @return     { number of vertirces }
     */
    public int V() {
        return vertices;
    }
    /**
     * { edges }.
     *
     * @return     { number of edges }
     */
    public int E() {
        return edges;
    }
    /**
     * Adds an edge.
     *
     * @param      v     { vertex 1 }
     * @param      w     { vertex 2 }
     */
    public void addEdge(int v, int w) {
        digraph[v].add(w);
        edges++;
    }
    /**
     * { adjecent nodes }.
     *
     * @param      v     { vertex }
     *
     * @return     { adjecent nodes }
     */
    public Iterable<Integer> adj(int v) {
        return digraph[v];
    }
}