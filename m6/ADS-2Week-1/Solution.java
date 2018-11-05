import java.util.*;
class PageRank {
	Digraph graph;
	Digraph temp;
	double[] pageranks;
	double[] temparr;
	double vertices;
	PageRank(Digraph d) {
		graph = d;
		temparr = new double[d.V()];
		pageranks = new double[d.V()];
	}
	public double getPR(int v) {
		//reversing graph to find the incoming nodes.
		//if there is no edge connect to all other nodes.
		for (int i = 0; i < graph.V(); i++) {
			if (graph.outdegree(i) == 0) {
				for (int j = 0; j < graph.V(); j++) {
					if (i != j) {
						graph.addEdge(i, j);
					}
				}
			}
		}
		temp = graph.reverse();

		vertices = (double)temp.V();
		//initially.
		for (int i = 0; i < temparr.length; i++) {
			pageranks[i] = 1.0 / vertices;
		}
		// System.out.println(Arrays.toString(temparr));
		//1000 times
		for (int i = 0; i < 1000; i++) {
			// for every node
			for (int j = 0; j < vertices; j++) {
				double t = 0.0;
				//adjacency vertices
				for (int k : temp.adj(j)) {
					t += (pageranks[k] / (double)graph.outdegree(k));
				}
				temparr[j] = t;
			}
		pageranks = Arrays.copyOf(temparr, temparr.length);
		}
		return pageranks[v];
	}

	public String toString() {
		double temp = getPR(0);
		String s = "";
		for (int v = 0; v < pageranks.length; v++) {
			s = s + v + " - " + pageranks[v] + "\n";
		}
		return s;
	}
}

class WebSearch {

}


public class Solution {
	public static void main(String[] args) {
		// read the first line of the input to get the number of vertices
		Scanner sc =  new Scanner(System.in);
		int vertices = Integer.parseInt(sc.nextLine());
		Digraph d = new Digraph(vertices);

		for (int i = 0; i < vertices; i++) {
			String[] arr = sc.nextLine().split(" ");
			for (int j = 1; j < arr.length; j++) {
				d.addEdge(Integer.parseInt(arr[0]), Integer.parseInt(arr[j]));
			}
		}
		System.out.println(d.toString());
		// iterate count of vertices times
		// to read the adjacency list from std input
		// and build the graph

		PageRank p = new PageRank(d);
		// Create page rank object and pass the graph object to the constructor

		// print the page rank object
		// System.out.println(p.getPR(0));
		System.out.println(p);
		// This part is only for the final test case

		// File path to the web content
		String file = "WebContent.txt";

		// instantiate web search object
		// and pass the page rank object and the file path to the constructor

		// read the search queries from std in
		// remove the q= prefix and extract the search word
		// pass the word to iAmFeelingLucky method of web search
		// print the return value of iAmFeelingLucky

	}
}
