import java.util.*;
public class BoggleSolver {
	// Initializes the data structure using the given array of strings as the dictionary.
	// (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
	TST<Integer> tree = new TST<Integer>();
	Set<String> words = new HashSet<String>();
	boolean[][] visited;
	String word = "";
	BoggleBoard board;
	public BoggleSolver(String[] dictionary) {
        for (int i = 0; i < dictionary.length; i++) {
            tree.put(dictionary[i], i);
        }
	}

	// Returns the set of all valid words in the given Boggle board, as an Iterable.
	public Iterable<String> getAllValidWords(BoggleBoard board) {
		this.board = board;
		for (int i = 0; i < board.rows(); i++) {
			for (int j = 0; j < board.cols(); j++) {
				visited = new boolean[board.rows()][board.cols()];
				dfs(i, j, words, word, visited);
			}
		}
		return words;
	}
	public void dfs(int row, int col, Set words, String word, boolean[][] visited) {
		if (visited[row][col]) {
			return;
		}
		
		if (board.getLetter(row, col) == 'Q'){
			word += "QU";
		} else {
			word += board.getLetter(row, col);
		}
		if(!tree.hasPrefix(word)) {
			return;
		}
		visited[row][col] = true;
		if (word.length() > 2 && tree.contains(word)) {
			words.add(word);
		}		
		if (row > 0) {
			dfs(row - 1, col, words, word, visited);

			if (col > 0) {
				dfs(row - 1, col-1, words, word, visited);

			}
			if(col < board.cols() - 1) {
				dfs(row - 1, col + 1, words, word, visited);

			}
		}
		if (col > 0) {
			dfs(row, col - 1, words, word, visited);

		}
		if (col < board.cols() - 1) {
			dfs(row, col + 1, words, word, visited);

		}
		if (row < board.rows() - 1) {
			if (col > 0) {
				dfs(row+1, col -1, words, word, visited);

			}
			if(col < board.cols() - 1) {
				dfs(row + 1, col + 1, words, word, visited);

			}
			dfs(row + 1, col, words, word, visited);
		}
		visited[row][col] = false;
	}
	// Returns the score of the given word if it is in the dictionary, zero otherwise.
	// (You can assume the word contains only the uppercase letters A through Z.)
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