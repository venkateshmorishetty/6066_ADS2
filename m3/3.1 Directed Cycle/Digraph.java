/**
 * Class for digraph.
 */
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
    public Digraph(final int v) {
        vertices = v;
        edges = 0;
        digraph = new Bag[vertices];
        for (int i = 0; i < vertices; i++) {
            digraph[i] = new Bag<Integer>();
        }
    }
    /**
     * { number of vertices }.
     * {time complexity is O(1)}
     * @return     { number of vertirces }
     */
    public int vertices() {
        return vertices;
    }
    /**
     * { edges }.
     * {time complexity is O(1)}
     * @return     { number of edges }
     */
    public int edges() {
        return edges;
    }
    /**
     * Adds an edge.
     * {time comlexity is O(1)}
     * @param      v     { vertex 1 }
     * @param      w     { vertex 2 }
     */
    public void addEdge(final int v, final int w) {
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
    public Iterable<Integer> adj(final int v) {
        return digraph[v];
    }
}
