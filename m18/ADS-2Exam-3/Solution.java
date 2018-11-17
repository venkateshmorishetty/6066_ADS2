// import java.util.Scanner;
import java.util.*;

public class Solution {

	// Don't modify this method.
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String cases = scan.nextLine();

		switch (cases) {
		case "loadDictionary":
			// input000.txt and output000.txt
			BinarySearchST<String, Integer> hash = loadDictionary("/Files/t9.csv");
			while (scan.hasNextLine()) {
				String key = scan.nextLine();
				System.out.println(hash.get(key));
			}
			break;

		case "getAllPrefixes":
			// input001.txt and output001.txt
			T9 t9 = new T9(loadDictionary("/Files/t9.csv"));
			while (scan.hasNextLine()) {
				String prefix = scan.nextLine();
				for (String each : t9.getAllWords(prefix)) {
					System.out.println(each);
				}
			}
			break;

		case "potentialWords":
			// input002.txt and output002.txt
			t9 = new T9(loadDictionary("/Files/t9.csv"));
			int count = 0;
			while (scan.hasNextLine()) {
				String t9Signature = scan.nextLine();
				for (String each : t9.potentialWords(t9Signature)) {
					count++;
					System.out.println(each);
				}
			}
			if (count == 0) {
				System.out.println("No valid words found.");
			}
			break;

		case "topK":
			// input003.txt and output003.txt
			t9 = new T9(loadDictionary("/Files/t9.csv"));
			Bag<String> bag = new Bag<String>();
			int k = Integer.parseInt(scan.nextLine());
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				bag.add(line);
			}
			for (String each : t9.getSuggestions(bag, k)) {
				System.out.println(each);
			}

			break;

		case "t9Signature":
			// input004.txt and output004.txt
			t9 = new T9(loadDictionary("/Files/t9.csv"));
			bag = new Bag<String>();
			k = Integer.parseInt(scan.nextLine());
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				for (String each : t9.t9(line, k)) {
					System.out.println(each);
				}
			}
			break;

		default:
			break;

		}
	}

	// Don't modify this method.
	public static String[] toReadFile(String file) {
		In in = new In(file);
		return in.readAllStrings();
	}

	public static BinarySearchST<String, Integer> loadDictionary(String file) {
		BinarySearchST<String, Integer>  st = new BinarySearchST<String, Integer>();
		// your code goes here
		String[] words = toReadFile(file);
		for (String i: words) {
			String temp = i.toLowerCase();
			// System.out.println(temp);
			if(st.contains(temp)) {
				int value = st.get(temp);
				st.put(temp, value+1);
			}
			else {
				st.put(temp, 1);
			}
		}
		return st;
	}

}

class T9 {
	TST<Integer> tree;
	int size; 
	public T9(BinarySearchST<String, Integer> st) {
		tree = new TST<Integer>();
		int size = st.size();
		for (String i: st.keys()) {
			tree.put(i, st.get(i));
		}
	}
	
	// get all the prefixes that match with given prefix.
	public Iterable<String> getAllWords(String prefix) {
		// your code goes here

		return tree.keysWithPrefix(prefix);
	}

	public Iterable<String> potentialWords(String t9Signature) {
		// your code goes here
		Character[] arr = new Character[26];
		int temp = 65;
		for (int i = 0; i < 26; i++) {
			arr[i] = (char)temp++;
		}
		BinarySearchST<Integer, ArrayList<Character>> table = new BinarySearchST<Integer, ArrayList<Character>>();
		int  k = 0;
		for (int i =2; i <= 9; i++) {
			ArrayList<Character> list = new ArrayList<Character>();
			for (int j = 0; j < 3; j++) {
				// table.put(i, list.add(arr[k++]));
			}
		}
		System.out.println(table.get(3));
		return null;
	}

	// return all possibilities(words), find top k with highest frequency.
	public Iterable<String> getSuggestions(Iterable<String> words, int k) {
		// your code goes here
		MaxPQ<Integer> values = new MaxPQ<Integer>();
		BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>();
		ArrayList<String> bag = new ArrayList<String>();
		for (String i: words) {
			for (String j: getAllWords(i)) {
				values.insert(tree.get(j));
				st.put(j, tree.get(j));
			}
		}
		for (int i = 0; i < k; i++) {
			int high = values.delMax();
			for (String j: st.keys()) {
				if (high == st.get(j)) {
					bag.add(j);
				}
			}
		}
		Collections.sort(bag);
		return bag;
	}

	// final output
	// Don't modify this method.
	public Iterable<String> t9(String t9Signature, int k) {
		return getSuggestions(potentialWords(t9Signature), k);
	}
}
