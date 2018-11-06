import java.util.Scanner;
import java.util.ArrayList;
/**
 * Class for edge.
 */
class Edge {
    /**.
     * { start of edge }
     */
    private int start;
    /**.
     * { end of edge }
     */
    private int end;
    /**.
     * { weight of edge }
     */
    private double weight;
    /**
     * Constructs the object.
     *
     * @param      s     { start }
     * @param      e     { end }
     * @param      w     { weight }
     */
    Edge(final int s, final int e, final double w) {
        start = s;
        end = e;
        weight = w;
    }
    /**.
     * { either }
     *
     * @return     { start }
     */
    public int either() {
        return start;
    }
    /**.
     * { other node of edge }
     *
     * @param      v     { start }
     *
     * @return     { other end of edge }
     */
    public int other(final int v) {
        if (start == v) {
            return end;
        }
        return start;
    }
    /**.
     * { compare to }
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
    /**.
     * { weight }
     *
     * @return     { weight of edge }
     */
    public double weight() {
        return weight;
    }
}
/**.
 * Class for edge weighted graph.
 */
class EdgeWeightedGraph {
    /**.
     * { graph }
     */
    private Bag<Edge>[] list;
    /**.
     * { vertices of graph }
     */
    private int size;
    /**
     * Constructs the object.
     *
     * @param      s     { size of graph }
     */
    EdgeWeightedGraph(final int s) {
        list = new Bag[s];
        size = s;
        for (int i = 0; i < s; i++) {
            list[i] = new Bag();
        }
    }
    /**
     * Adds an edge.
     * {time complexity is O(1)}
     * @param      e     { edge }
     */
    public void addEdge(final Edge e) {
        int pos = e.either();
        int pos1 = e.other(pos);
        list[pos].add(e);
        list[pos1].add(e);
    }
    /**.
     * { vertices }
     *
     * @return     { count of vertices in graph }
     */
    public int vertices() {
        return size;
    }
    /**.
     * { adj verices }
     *
     * @param      v     { vertices }
     *
     * @return     { adjecent vertices }
     */
    public Iterable<Edge> adj(final int v) {
        return list[v];
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
        return distTo[v];
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
     * { time complexity O(V+E+k) k=number of qureries}
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] arr = sc.nextLine().split(" ");
        EdgeWeightedGraph d = new EdgeWeightedGraph(Integer.parseInt(arr[0]));
        String[] temp = sc.nextLine().split(" ");
        ArrayList<String> ver = new ArrayList<String>();
        for (int j = 0; j < temp.length; j++) {
            ver.add(temp[j]);
        }
        for (int i = 0; i < Integer.parseInt(arr[1]); i++) {
            String[] input = sc.nextLine().split(" ");
            d.addEdge(new Edge(ver.indexOf(input[0]),
            ver.indexOf(input[1]), Integer.parseInt(input[2])));
        }
        int queries = Integer.parseInt(sc.nextLine());
        for (int k = 0; k < queries; k++) {
            String[] loc = sc.nextLine().split(" ");
            ShortestPath st = new ShortestPath(d, ver.indexOf(loc[0]));
            System.out.println((int) st.distTo(ver.indexOf(loc[1])));
        }
    }
}
