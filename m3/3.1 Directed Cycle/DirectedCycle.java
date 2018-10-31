public class DirectedCycle {
    /**
     * { marked array }.
     */
    private boolean[] marked;
    /**
     * { edge to }.
     */
    private int[] edgeTo;
    /**
     * { on stack }.
     */
    private boolean[] onStack;
    /**
     * { cycle stack }.
     */
    private Stack<Integer> cycle;
    /**
     * Constructs the object.
     *
     * @param      G     { digraph }
     */
    public DirectedCycle(Digraph G) {
        marked  = new boolean[G.V()];
        onStack = new boolean[G.V()];
        edgeTo  = new int[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!marked[v] && cycle == null) dfs(G, v);
    }
    /**
     * { depth first search }.
     *
     * @param      G     { graph }
     * @param      v     { vertex }
     */
    private void dfs(Digraph G, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (cycle != null) return;
            else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
            else if (onStack[w]) {
                cycle = new Stack<Integer>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }
    /**
     * Determines if it has cycle.
     *
     * @return     True if has cycle, False otherwise.
     */
    public boolean hasCycle() {
        return cycle != null;
    }
}
