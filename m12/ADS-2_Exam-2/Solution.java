import java.util.Scanner;
/**
 * Class for edge.
 */
class Edge implements Comparable<Edge> {
    /**.
     * { v }
     */
    private final int v;
    /**.
     * { w }
     */
    private final int w;
    /**.
     * { weight }
     */
    private final double weight;
    /**
     * Constructs the object.
     *
     * @param      v       { v }
     * @param      w       { w }
     * @param      weight  The weight
     */
    Edge(final int first, final int second, final double we) {
        v = first;
        w = second;
        weight = we;
    }
    /**.
     * { weight }
     *
     * @return     { weight }
     */
    public double weight() {
        return weight;
    }
    /**.
     * { either }
     *
     * @return     { either }
     */
    public int either() {
        return v;
    }
    /**.
     * { other }
     *
     * @param      vertex  The vertex
     *
     * @return     { other }
     */
    public int other(final int vertex) {
        if (vertex == v) {
            return w;
        }
        else if (vertex == w) {
            return v;
        }
        else {
            throw new IllegalArgumentException("Illegal endpoint");
        }
    }
    @Override
    public int compareTo(final Edge that) {
        return Double.compare(this.weight, that.weight);
    }
    /**
     * Returns a string representation of the object.
     *
     * @return     String representation of the object.
     */
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
        distTo = new double[g.vertices()];
        edgeTo = new Edge[g.vertices()];
        for (int v = 0; v < g.vertices(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0;
        pq = new IndexMinPQ<Double>(g.vertices());
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
    /**.
     * { path }
     *
     * @param      v     { v }
     *
     * @return     { path }
     */
    public Iterable<Integer> pathTo(final int v) {
        if (!hasPathTo(v)) {
            return null;
        }
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
    /**.
     * { new line }
     */
    private static final String NEWLINE = System.getProperty("line.separator");
    /**.
     * { vertices }
     */
    private final int vertices;
    /**.
     * { edges }
     */
    private int edges;
    /**.
     * { adj }
     */
    private Bag<Edge>[] adj;
    /**
     * Constructs the object.
     *
     * @param      V     { v }
     */
    EdgeWeightedGraph(final int v) {
        this.vertices = v;
        this.edges = 0;
        adj = (Bag<Edge>[]) new Bag[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new Bag<Edge>();
        }
    }
    /**.
     * { vertices }
     *
     * @return     { vertices }
     */
    public int vertices() {
        return vertices;
    }
    /**.
     * { edges }
     *
     * @return     { edges }
     */
    public int countedges() {
        return edges;
    }
    /**
     * Adds an edge.
     *
     * @param      e     { e }
     */
    public void addEdge(final Edge e) {
        int v = e.either();
        int w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        edges++;
    }
    /**.
     * { adj }
     *
     * @param      v     { v }
     *
     * @return     { edge }
     */
    public Iterable<Edge> adj(final int v) {
        return adj[v];
    }
    /**.
     * { degree }
     *
     * @param      v     { v }
     *
     * @return     { degree }
     */
    public int degree(final int v) {
        return adj[v].size();
    }
    /**.
     * { edges }
     *
     * @return     { edges }
     */
    public Iterable<Edge> edges() {
        Bag<Edge> list = new Bag<Edge>();
        for (int v = 0; v < vertices; v++) {
            int selfLoops = 0;
            for (Edge e : adj(v)) {
                if (e.other(v) > v) {
                    list.add(e);
                } else if (e.other(v) == v) {
                    if (selfLoops % 2 == 0) {
                        list.add(e);
                    }
                    selfLoops++;
                }
            }
        }
        return list;
    }
    /**.
     * Returns a string representation of the object.
     *
     * @return     String representation of the object.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(vertices + " vertices " + edges + " edges" + NEWLINE);
        for (int v = 0; v < vertices; v++) {
            s.append(v + ": ");
            for (Edge e : adj[v]) {
                s.append(e + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}
/**.
 * { solution class }
 */
final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        //constructor.
    }
    /**.
     * { main function }
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
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
            String[] arr = sc.nextLine().split(" ");
            ShortestPath st1 = new ShortestPath(g, Integer.parseInt(arr[0]));
            double distance1 = st1.distTo(Integer.parseInt(arr[1]));
            ShortestPath st2 = new ShortestPath(g, Integer.parseInt(arr[1]));
            double distance2 = st2.distTo(Integer.parseInt(arr[2]));
            if (distance1 == -1 || distance2 == -1) {
                System.out.println("No Path Found.");
            } else {
                for (int i : st1.pathTo(Integer.parseInt(arr[1]))) {
                    path += i + " ";
                }
                for (int i : st2.pathTo(Integer.parseInt(arr[2]))) {
                    path += i + " ";
                }
                path = path + arr[2];
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