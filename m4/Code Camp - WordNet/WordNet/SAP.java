/**
 * Class for sap.
 */
public class SAP {
    private Digraph digraph;
    private BreadthFirstDirectedPaths[] bfs;
    /**
     * Constructs the object.
     *
     * @param      digraph1  The digraph 1
     */
    public SAP(final Digraph digraph1) {
        this.digraph = new Digraph(digraph1);
        bfs = new BreadthFirstDirectedPaths[this.digraph.V()];
    }
    /**
     * { length }
     *
     * @param      v     { vertex v }
     * @param      w     { vertex w }
     *
     * @return     { length }
     */
    public int length(final int v, final int w) {
        if (bfs[v] == null) {
            bfs[v] = new BreadthFirstDirectedPaths(digraph, v);
        }

        if (bfs[w] == null) {
            bfs[w] = new BreadthFirstDirectedPaths(digraph, w);
        }

        int length = Integer.MAX_VALUE;

        for (int i = 0; i < digraph.V(); i++) {
            if (bfs[v].hasPathTo(i) && bfs[w].hasPathTo(i)) {
                int l = bfs[v].distTo(i) + bfs[w].distTo(i);
                if (l < length) {
                    length = l;
                }
            }
        }
        bfs[v] = null;
        bfs[w] = null;

        if (length != Integer.MAX_VALUE) {
            return length;
        } else {
            return -1;
        }
    }
    /**
     * { ancestor }
     *
     * @param      v     { vertx v }
     * @param      w     { vertex w }
     *
     * @return     { description_of_the_return_value }
     */
    public int ancestor(final int v, final int w) {
        if (bfs[v] == null) {
            bfs[v] = new BreadthFirstDirectedPaths(digraph, v);
        }

        if (bfs[w] == null) {
            bfs[w] = new BreadthFirstDirectedPaths(digraph, w);
        }

        int length = Integer.MAX_VALUE;
        int ancestor = -1;

        for (int i = 0; i < digraph.V(); i++) {
            if (bfs[v].hasPathTo(i) && bfs[w].hasPathTo(i)) {
                int l = bfs[v].distTo(i) + bfs[w].distTo(i);
                if (l < length) {
                    length = l;
                    ancestor = i;
                }
            }
        }
        bfs[v] = null;
        bfs[w] = null;

        return ancestor;
    }
    /**
     * { len }
     *
     * @param      v     { v }
     * @param      w     { w }
     *
     * @return     { description_of_the_return_value }
     */
    public int length(final Iterable<Integer> v, final Iterable<Integer> w) {
        if (v == null || w == null) {
            throw new NullPointerException();
        }

        int length = Integer.MAX_VALUE;
        for (int i : v) {
            for (int j : w) {
                int l = length(i, j);
                if (l != -1 && l < length) {
                    length = l;
                }
            }
        }
        if (length != Integer.MAX_VALUE) {
            return length;
        } else {
            return -1;
        }
    }
    /**
     * { ancestor }
     *
     * @param      v     { v }
     * @param      w     { parameter_description }
     *
     * @return     { w }
     */
    public int ancestor(final Iterable<Integer> v, final
                        Iterable<Integer> w) {
        if (v == null || w == null) {
            throw new NullPointerException();
        }

        int length = Integer.MAX_VALUE;
        int ancestor = -1;

        for (int i : v) {
            for (int j : w) {
                int l = length(i, j);
                if (l != -1 && l < length) {
                    length = l;
                    ancestor = ancestor(i, j);
                }
            }
        }
        return ancestor;
    }
}