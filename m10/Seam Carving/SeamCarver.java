import java.awt.*;
import java.lang.Math;

public class SeamCarver {
	// create a seam carver object based on the given picture
	Picture p;
	public SeamCarver(Picture picture) {
		p = new Picture(picture);
	}
	// current picture
	public Picture picture() {
		return p;
	}
	// width of current picture
	public int width() {
		return p.width();
	}

	// height of current picture
	public int height() {
		return p.height();
	}

	// energy of pixel at column x and row y
	public double energy(int x, int y) {
		if (x == 0 || x == p.width()-1 || y == 0 || y == p.height() - 1) {
			return 1000.0;
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
	private double getValue(Color v, Color w) {
		int one = Math.abs(v.getRed() - w.getRed());
		int two = Math.abs(v.getGreen() - w.getGreen());
		int three = Math.abs(v.getBlue() - w.getBlue());
		return Math.pow(one, 2) + Math.pow(two, 2) + Math.pow(three, 2);
	}

	// sequence of indices for horizontal seam
	public int[] findHorizontalSeam() {
		return new int[0];
	}

	// sequence of indices for vertical seam
	public int[] findVerticalSeam() {
		return new int[0];
	}

	// remove horizontal seam from current picture
	public void removeHorizontalSeam(int[] seam) {

	}

	// remove vertical seam from current picture
	public void removeVerticalSeam(int[] seam) {

	}
}