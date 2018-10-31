import java.util.Scanner;
/**
 * Class for solution.
 */
final class Solution {
	/**
	 * Constructs the object.
	 */
	public Solution() {
		//constructor
	}
	/**
	 * { main function }
	 *
	 * @param      args  The arguments
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int nodes = Integer.parseInt(sc.nextLine());
		int edges = Integer.parseInt(sc.nextLine());
		Digraph d = new Digraph(nodes);
		for (int i = 0; i < edges; i++) {
			int a = sc.nextInt(), b = sc.nextInt();
			d.addEdge(a, b);
		}
		DirectedCycle dc = new DirectedCycle(d);
		if(dc.hasCycle()) {
			System.out.println("Cycle exists.");
		} else {
			System.out.println("Cycle doesn't exists");
		}
	}
}