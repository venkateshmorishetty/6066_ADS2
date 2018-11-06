import java.util.Scanner;
/**
 * Class for edge.
 */
class Edge implements Comparable<Edge> {
    /**.
     * { start }
     */
    private int start;
    /**.
     * { end }
     */
    private int end;
    /**.
     * { weight }
     */
    private double weight;
    /**
     * Constructs the object.
     *
     * @param      s     { s }
     * @param      e     { e }
     * @param      w     { w }
     */
    Edge(final int s, final int e, final double w) {
        start = s;
        end = e;
        weight = w;
    }
    /**.
     * { either }
     *
     * @return     { first node }
     */
    public int either() {
        return start;
    }
    /**.
     * { other }
     *
     * @param      v     { start }
     *
     * @return     { end node }
     */
    public int other(final int v) {
        if (start == v) {
            return end;
        }
        return start;
    }
    /**.
     * { compare }
     *
     * @param      that  The that
     *
     * @return     { -1 for less 0 for equal 1 for more }
     */
    public int compareTo(final Edge that) {
        if (this.weight < that.weight) {
            return -1;
        } else if (this.weight > that.weight) {
            return 1;
        } else {
            return 0;
        }
    }
    /**
     * Gets the weight.
     *
     * @return     The weight.
     */
    public double getWeight() {
        return weight;
    }
}
/**.
 * Class for edge weighted graph.
 */
class EdgeWeightedGraph {
    /**.
     * { number of vertices }
     */
    private int size;
    /**.
     * { graph }
     */
    private Bag<Edge>[] graph;
    /**
     * Constructs the object.
     *
     * @param      s     { number of nodes }
     */
    EdgeWeightedGraph(final int s) {
        size = s;
        graph = new Bag[s];
        for (int i = 0; i < s; i++) {
            graph[i] = new Bag();
        }
    }
    /**.
     * Adds an edge.
     * {time complexity is O(1)}
     * @param      e     { e }
     */
    public void addEdge(final Edge e) {
        int pos = e.either();
        int pos1 = e.other(pos);
        // System.out.println(pos +"  "+pos1+"  "+graph.length );
        graph[pos].add(e);
        graph[pos1].add(e);
    }
    /**.
     * { edges }
     * 
     * @return     { connected vertices }
     */
    public Iterable<Edge> edges() {
        Bag<Edge> temp = new Bag<Edge>();
        for (int v = 0; v < size; v++) {
            for (Edge e : adj(v)) {
                temp.add(e);
            }
        }
        return temp;
    }
    /**.
     * { vertices }
     *
     * @return     { count of vertices }
     */
    public int vertices() {
        return size;
    }
    /**.
     * { adj }
     *
     * @param      v     { v }
     *
     * @return     { adjecent to v }
     */
    public Iterable<Edge> adj(final int v) {
        return graph[v];
    }
}
/**
 * Class for spanning tree.
 */
class SpanningTree {
    /**.
     * { graph }
     */
    private EdgeWeightedGraph graph;
    /**.
     * { weight }
     */
    private double weight;
    /**.
     * { minmumst }
     */
    private Queue<Edge> minimumst;
    /**
     * Constructs the object.
     * {time complexity is O(ElogV)}
     * @param      g     { graph }
     */
    SpanningTree(final EdgeWeightedGraph g) {
        graph = g;
        minimumst = new Queue<Edge>();
        MinPQ<Edge> pq = new MinPQ<Edge>();
        for (Edge e : g.edges()) {
            pq.insert(e);
        }
        UF uf = new UF(graph.vertices());
        while (!pq.isEmpty() && minimumst.size() < graph.vertices() - 1) {
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);
            if (!uf.connected(v, w)) {
                uf.union(v, w);
                minimumst.enqueue(e);
                weight += e.getWeight();
            }
        }
    }
    /**.
     * { edges }
     *
     * @return     { edges }
     */
    public Iterable<Edge> edges() {
        return minimumst;
    }
    /**.
     * { min }
     * {time complexity is O(E)}
     * @return     { minimum spanning tree cost }
     */
    public double min() {
        double min = 0.0;
        for (Edge e : edges()) {
            min += e.getWeight();
        }
        return min;
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
     * {time complexity is O(E)}
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        int vertices = Integer.parseInt(sc.nextLine());
        int edges = Integer.parseInt(sc.nextLine());
        EdgeWeightedGraph w = new EdgeWeightedGraph(vertices);
        for (int i = 0; i < edges; i++) {
            String[] line = sc.nextLine().split(" ");
            w.addEdge(new Edge(Integer.parseInt(line[0]),
                Integer.parseInt(line[1]), Double.parseDouble(line[2])));
        }
        SpanningTree st = new SpanningTree(w);
        System.out.format("%.5f", st.min());
    }
}
