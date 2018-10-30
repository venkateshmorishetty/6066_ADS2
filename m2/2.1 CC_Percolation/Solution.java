import java.util.Scanner;
/**
 * { Solution class }.
 */
final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        //constructor
    }
    /**
     * { main method }.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        int nodes = Integer.parseInt(sc.nextLine());
        int[][] matrix  = new int[nodes][nodes];
        Graph graph = new Graph(nodes * nodes + 2);
        while (sc.hasNext()) {
            String[] loc = sc.nextLine().split(" ");
            int row = Integer.parseInt(loc[0]);
            int col = Integer.parseInt(loc[1]);
            matrix[row - 1][col - 1] = 1;
        }
        Percolation p = new Percolation();
        System.out.println(p.check(matrix, graph, nodes));
    }
}
