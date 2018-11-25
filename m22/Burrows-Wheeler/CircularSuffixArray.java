import java.util.Arrays;
public class CircularSuffixArray {
    private class Node implements Comparable<Node> {
        String str;
        int value;
        Node(String s,int val) {
            this.str = s;
            this.value = val;
        }
        public int compareTo(Node that) {
            int l = str.length();
            for (int i = 0; i < str.length(); i++) {
                char temp1 = str.charAt((i + this.value) % l);
                char temp2 = str.charAt((i + that.value) % l);
                if (temp1 > temp2) {
                    return 1;
                }
                if (temp1 < temp2) {
                    return -1;
                }
            }
            return 0;
        }
        public int getValue() {
            return this.value;
        }
    }

    private int[] indices;
    private int length;

    public CircularSuffixArray(String s) {
        this.length = s.length();
        this.indices = new int[length];
        Node[] substrings = new Node[length];
        for (int i = 0; i < length; i++) {
            substrings[i] = new Node(s, i);
        }
        Arrays.sort(substrings);
        for (int j = 0; j < substrings.length; j++) {
            indices[j] = substrings[j].getValue();
        }
    }

    public int length() {
        return this.length;
    }
    public int index(int i) {
        return indices[i];
    }
}