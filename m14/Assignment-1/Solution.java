import java.util.Scanner;
/**
 * Class for solution.
 */
final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        //constructor.
    }
    /**.
     * { main }
     * {time comlexity is O(K*N*(L+logN)) n = number of words
     * k = each word suffix array size }
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        String[] words = loadWords();
        TST<Integer> tree = new TST<Integer>();
        for (int i = 0; i < words.length; i++) {
            String[] suffix = new String[words[i].length()];
            for (int j = 0; j < words[i].length(); j++) {
                suffix[j] = words[i].substring(j);
            }
            for (int j = 0; j < suffix.length; j++) {
                tree.put(suffix[j], i);
            }
        }
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        for (String i: tree.keysWithPrefix(text)) {
            System.out.println(i);
        }

    }
    /**
     * Loads words.
     *
     * @return     { array of words }
     */
    public static String[] loadWords() {
        In in = new In("/Files/dictionary-algs4.txt");
        String[] words = in.readAllStrings();
        return words;
    }
}
