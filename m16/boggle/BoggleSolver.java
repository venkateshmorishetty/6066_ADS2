import java.util.*;
public class BoggleSolver {
	// Initializes the data structure using the given array of strings as the dictionary.
	// (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
	private TST<Integer> tree = new TST<Integer>();
	Set<String> words = new HashSet<String>();
	// boolean[][] visited;
	String word = "";
	BoggleBoard board;
	public BoggleSolver(String[] dictionary) {
        for (int i = 0; i < dictionary.length; i++) {
            tree.put(dictionary[i], i);
        }
	}
	/**
	 * Gets all valid words.
	 * time complexity is O(N^2*(V+E))
	 * N = number of rows and cols.
	 * @param      board  The board
	 *
	 * @return     All valid words.
	 */
	public Iterable<String> getAllValidWords(BoggleBoard board) {
		this.board = board;
		boolean[][] visited = new boolean[board.rows()][board.cols()];
		for (int i = 0; i < board.rows(); i++) {
			for (int j = 0; j < board.cols(); j++) {
				dfs(i, j, words, word, visited);
			}
		}
		return words;
	}
	/**
	 * { dfs }
	 * time complexity is O(V+E)
	 * @param      row      The row
	 * @param      col      The col
	 * @param      words    The words
	 * @param      word     The word
	 * @param      visited  The visited
	 */
	private void dfs(int row, int col, Set words, String word, boolean[][] visited) {
		if (visited[row][col]) {
			return;
		}
		
		if (board.getLetter(row, col) == 'Q'){
			word += "QU";
		} else {
			word += board.getLetter(row, col);
		}
		if (word.length() > 2 && tree.contains(word)) {
			words.add(word);
		}
		if(!tree.hasPrefix(word)) {
			return;
		}
		visited[row][col] = true;
		
		//top		
		if (row > 0) {
			//topleft
			dfs(row - 1, col, words, word, visited);
			//top
			if (col > 0) {
				dfs(row - 1, col-1, words, word, visited);
			}
			//topright
			if(col < board.cols() - 1) {
				dfs(row - 1, col + 1, words, word, visited);

			}
		}
		//left
		if (col > 0) {
			dfs(row, col - 1, words, word, visited);
		}
		//right
		if (col < board.cols() - 1) {
			dfs(row, col + 1, words, word, visited);

		}
		//bottom
		if (row < board.rows() - 1) {
			//bottom left
			if (col > 0) {
				dfs(row+1, col -1, words, word, visited);

			}
			//bottom right
			if(col < board.cols() - 1) {
				dfs(row + 1, col + 1, words, word, visited);
			}
			//bottom
			dfs(row + 1, col, words, word, visited);
		}
		visited[row][col] = false;
	}
	/**
	 * { score }
	 * time complexity is O(1)
	 * @param      word  The word
	 *
	 * @return     { description_of_the_return_value }
	 */
	public int scoreOf(String word) {
		// System.out.println(word+" word");
		if (word.length() == 3 || word.length() == 4) {
			return 1;
		} else if (word.length() == 5) {
			return 2;
		} else if (word.length() == 6) {
			return 3;
		} else if (word.length() == 7) {
			return 5;
		}
		return 11;
	}
}