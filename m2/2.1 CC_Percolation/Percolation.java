/**
 * Class for percolation.
 */
class Percolation {
	/**
	 * { checks whether it is percolates or not }.
	 * { time complexity for check is O(n^2) }.
	 * @param      matrix  The matrix
	 * @param      graph   The graph
	 * @param      size    The size
	 *
	 * @return     { true if it is percolates else false }
	 */
	public boolean check(final int[][] matrix , final Graph graph,
		final int size) {
		for (int i = 0 ; i < size; i++) {
			for (int j = 0 ; j < size; j++) {
				if (matrix[i][j] == 1) {
					if (i == 0) {
						graph.addEdge(i * size + j , size * size);
					}
					if (i == size - 1) {
						graph.addEdge(i * size + j , size * size + 1);
					}
					// top
					if (i - 1 >= 0 && matrix[i - 1][j] == 1) {
						graph.addEdge(i * size + j , size*(i - 1)+ j);
					}
					// bottom
					if (i + 1 < size  && matrix[i + 1][j] == 1) {
						graph.addEdge(i * size + j , size*(i + 1)+ j);
					}
					// right
					if (j - 1 >= 0 && matrix[i][j - 1] == 1) {
						graph.addEdge(i * size + j , size*i+(j - 1));
					}
					// left
					if (j + 1 < size && matrix[i][j + 1] == 1) {
						graph.addEdge(i * size + j , size*(i)+(j + 1));
					}
				}
			}
		}
		DepthFirstPaths d = new DepthFirstPaths(graph, size * size);
		return d.hasPathTo(size * size + 1);
	}
}