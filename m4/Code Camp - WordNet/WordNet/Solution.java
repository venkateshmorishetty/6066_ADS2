import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
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
     * { main method }
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            String fname = sc.nextLine();
            File temp = loc(fname);
            Scanner s = new Scanner(new FileReader(temp));
            int size = 0;
            String str;
            LinearProbingHashST<String, ArrayList<Integer>> first =
            new LinearProbingHashST<String, ArrayList<Integer>>();
            LinearProbingHashST<Integer, String> second =
            new LinearProbingHashST<Integer, String>();
            while (s.hasNext()) {
                size++;
                String[] line = s.nextLine().split(",");
                int id = Integer.parseInt(line[0]);
                String[] nouns = line[1].split(" ");
                ArrayList<String> nounList = new ArrayList<String>();
                for (String noun : nouns) {
                    nounList.add(noun);
                }
                second.put(id, line[1]);
                for (String noun : nouns) {
                    if (first.contains(noun)) {
                        first.get(noun).add(id);
                    } else {
                        ArrayList<Integer> te = new ArrayList<Integer>();
                        te.add(id);
                        first.put(noun, te);
                    }
                }
            }
            Digraph d = new Digraph(size);
            String fname1 = sc.nextLine();
            s = new Scanner(new FileReader(loc(fname1)));
            while (s.hasNext()) {
                str = s.nextLine();
                String[] values = str.split(",");
                for (int i = 1; i < values.length; i++) {
                    d.addEdge(Integer.parseInt(values[0]),
                        Integer.parseInt(values[i]));
                }
            }
            String type = sc.nextLine();
            if (type.equals("Graph")) {
                int count = 0;
                for (int i = 0; i < d.V(); i++) {
                    if (d.outdegree(i) == 0) {
                        count++;
                    }
                }
                if (count > 1) {
                    throw new Exception("Multiple roots");
                }
                DirectedCycle dc = new DirectedCycle(d);
                if (dc.hasCycle()) {
                    throw new Exception("Cycle detected");
                }
                System.out.println(d.toString());
            } else if (type.equals("Queries")) {
                SAP shortest = new SAP(d);
                while (sc.hasNext()) {
                    String[] arr = sc.nextLine().split(" ");
                    if (arr[0].equals("null") || arr[1].equals("null")) {
                        throw new Exception("IllegalArgumentException");
                    }
                    ArrayList<Integer> id1 = first.get(arr[0]);
                    ArrayList<Integer> id2 = first.get(arr[1]);
                    int dist = shortest.length(id1, id2);
                    int a = shortest.ancestor(id1, id2);
                    String ancestor =  second.get(a);
                    System.out.println("distance = " + dist + ", "
                        + "ancestor = " + ancestor);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    /**.
     * { finds the location of file }
     *
     * @param      fname  The filename
     *
     * @return     { file }
     */
    static File loc(final String fname) {
        File folder = new File("Files");
        File[] files = folder.listFiles();
        for (File f : files) {
            if (fname.equals(f.getName())) {
                return f;
            }
        }
        return null;
    }
}
