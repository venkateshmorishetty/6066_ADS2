import java.awt.Color;
/**
 * Class for seam carver.
 */
public class SeamCarver {
    /**.
     * { picture }
     */
    private Picture p;
    /**.
     * { t }
     */
    private final double t = 1000.0;
    /**
     * Constructs the object.
     *
     * @param      picture  The picture
     */
    public SeamCarver(final Picture picture) {
        p = new Picture(picture);
    }
    /**.
     * { picture }
     *
     * @return     { picture }
     */
    public Picture picture() {
        return p;
    }
    /**.
     * { width }
     *
     * @return     { width }
     */
    public int width() {
        return p.width();
    }
    /**.
     * { height }
     *
     * @return     { height of picture }
     */
    public int height() {
        return p.height();
    }
    /**.
     * { energy function }
     * {time complexity is O(1)}
     * @param      x     { row }
     * @param      y     { col }
     *
     * @return     { energy }
     */
    public double energy(final int x, final int y) {
        if (x == 0 || x == p.width() - 1
            || y == 0 || y == p.height() - 1) {
            return t;
        }
        Color top = p.get(x - 1, y);
        Color bottom = p.get(x + 1, y);
        Color left = p.get(x, y - 1);
        Color right = p.get(x, y + 1);
        double one = getValue(top, bottom);
        double two = getValue(left, right);
        double energy = Math.sqrt(one + two);
        return energy;
    }
    /**
     * Gets the value.
     *
     * @param      v     { row }
     * @param      w     { col }
     *
     * @return     The value.
     */
    private double getValue(final Color v, final Color w) {
        int one = Math.abs(v.getRed() - w.getRed());
        int two = Math.abs(v.getGreen() - w.getGreen());
        int three = Math.abs(v.getBlue() - w.getBlue());
        return Math.pow(one, 2) + Math.pow(two, 2) + Math.pow(three, 2);
    }
    /**.
     * { finds horizontal stream }
     * {time complexity is O(V*E)}
     * @return     { horizontal stream }
     */
    public int[] findHorizontalSeam() {
        Picture temp = p;
        Picture transpose = new Picture(temp.height(),
            temp.width());
        for (int j = 0; j < transpose.width(); j++) {
            for (int i = 0; i < transpose.height(); i++) {
                transpose.set(j, i, temp.get(i, j));
            }
        }
        p = transpose;
        int[] arr = findVerticalSeam();
        p = temp;
        return arr;
    }
    /**.
     * { finds vertical stream }
     * {time complexity is O(V*E)}
     * @return     { vertical stream }
     */
    public int[] findVerticalSeam() {
        double[][] energy = new double[width()][height()];
        int[] vertexTo = new int[height()];
        double[][] distTo = new double[width()][height()];
        int[][] edgeTo = new int[width()][height()];
        if (width() - 1 == 0 || height() - 1 == 0) {
            return vertexTo;
        }
        for (int i = 0; i < width(); i++) {
            for (int j = 0; j < height(); j++) {
                energy[i][j] = energy(i, j);
                distTo[i][j] = Double.MAX_VALUE;
                if (j == 0) {
                    distTo[i][0] = t;
                    edgeTo[i][0] = i;
                }
            }
        }
        for (int j = 0; j < height() - 1; j++) {
            for (int i = 0; i < width(); i++) {
                relax(i, j, energy, edgeTo, distTo);
            }
        }
        int min = 0;
        for (int i = 1; i < width() - 1; i++) {
            if (distTo[min][height() - 1]
                > distTo[i][height() - 1]) {
                min = i;
            }
        }
        vertexTo[height() - 1] = min;
        int count = height() - 2;
        while (count >= 0) {
            vertexTo[count] = edgeTo[vertexTo[count + 1]][count + 1];
            count--;
        }
        return vertexTo;
    }
    /**.
     * { relax edge }
     * {time complexity is O(1)}
     * @param      i       { row }
     * @param      j       { col }
     * @param      energy  The energy
     * @param      edgeTo  The edge to
     * @param      distTo  The distance to
     */
    public void relax(final int i, final int j, final double[][] energy,
        final int[][] edgeTo, final double[][] distTo) {
        if (distTo[i][j + 1] >= distTo[i][j] + energy[i][j + 1]) {
            distTo[i][j + 1] = distTo[i][j] + energy[i][j + 1];
            edgeTo[i][j + 1] = i;
        }
        if (i > 0 && distTo[i - 1][j + 1]
            > distTo[i][j] + energy[i - 1][j + 1]) {
            distTo[i - 1][j + 1] = distTo[i][j] + energy[i - 1][j + 1];
            edgeTo[i - 1][j + 1] = i;
        }
        if (i < width() - 1 && distTo[i + 1][j + 1]
            > distTo[i + 1][j] + energy[i + 1][j + 1]) {
            distTo[i + 1][j + 1] =
            distTo[i][j] + energy[i + 1][j + 1];
            edgeTo[i + 1][j + 1] = i;
        }
    }
    /**
     * Removes a horizontal seam.
     * {time complexity is O(V*E)}
     * @param      seam  The seam
     */
    public void removeHorizontalSeam(final int[] seam) {
        Picture temp = p;
        Picture transpose
            = new Picture(height(), width());
        for (int j = 0; j < transpose.width(); j++) {
            for (int i = 0; i < transpose.height(); i++) {
                transpose.set(j, i, temp.get(i, j));
            }
        }
        p = transpose;
        transpose = null;
        temp = null;
        removeVerticalSeam(seam);
        temp = p;
        transpose = new Picture(temp.height(),
                                temp.width());
        for (int j = 0; j < transpose.width(); j++) {
            for (int i = 0; i < transpose.height(); i++) {
                transpose.set(j, i, temp.get(i, j));
            }
        }
        p = transpose;
        transpose = null;
        temp = null;
    }
    /**
     * Removes a vertical seam.
     * {time complexity is O(V*E)}
     * @param      verticalseam  The verticalseam
     */
    public void removeVerticalSeam(final int[] verticalseam) {
        Picture original = p;
        Picture temp = new Picture(width() - 1,
            height());

        for (int i = 0; i < temp.height(); i++) {
            for (int j = 0; j < verticalseam[i]; j++) {
                temp.set(j, i, original.get(j, i));
            }
            for (int j = verticalseam[i]; j < temp.width(); j++) {
                temp.set(j, i, original.get(j + 1, i));
            }
        }
        p = temp;
    }
}

