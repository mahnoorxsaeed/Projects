package edu.ou.cs2334.project4.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class NonogramMakerModel {

	private static char EMPTY_CELL_CHAR = '0';

	private static char FILLED_CELL_CHAR = '1';

	private int numRows;

	private int numCols;

	private boolean[] grid;

	public NonogramMakerModel(int numRows, int numCols) {
		if (numRows <= 0 || numCols <= 0) {
			throw new IllegalArgumentException();
		} else {
			this.numCols = numCols;
			this.numRows = numRows;
			this.grid = new boolean[this.numRows * this.numCols];
		}
	}

	public NonogramMakerModel(File file) throws FileNotFoundException {
		Scanner scan = new Scanner(file);
		numRows = scan.nextInt();
		numCols = scan.nextInt();
		for (int i = 0; i < (numRows + numCols + 1); i++) {
			scan.nextLine();
		}
		this.grid = new boolean[this.numCols * this.numRows];

		for (int i = 0; i < numRows; i++) {
			String line_input = scan.nextLine();

			for (int j = 0; j < line_input.length(); j++) {
				grid[(numCols * i) + j] = (line_input.charAt(j) == FILLED_CELL_CHAR);
			}

		}
		scan.close();
	}


	public NonogramMakerModel(String filename) throws FileNotFoundException {
		this(new File(filename));
	}


	public boolean[] getGrid() {
		return Arrays.copyOf(grid, grid.length);
	}


	public boolean getCell(int rowIdx, int colIdx) {
		return grid[(numCols * rowIdx) + colIdx];
	}


	public void setCell(int rowIdx, int colIdx, boolean state) {
		grid[(numCols * rowIdx) + colIdx] = state;
	}


	public int getNumRows() {
		return numRows;
	}

	public int getNumCols() {
		return numCols;
	}

	public static List<Integer> project(boolean[] cells) {
		List<Integer> temp = new ArrayList<Integer>();
		int count = 0;
		for (int i = 0; i < cells.length; i++) {
			if (cells[i] == true) {
				count++;
			} else if (cells[i] == false) {
				if (count != 0) {
					temp.add(count);
				}
				count = 0;
			}
		}
		if (count > 0) {
			temp.add(count);
		}

		if (temp.size() == 0) {
			temp.add(0);
		}
		return temp;
	}

	public List<Integer> projectRow(int rowIdx) {
		boolean[] temp = new boolean[getNumCols()];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = getCell(rowIdx, i);
		}
		return project(temp);
	}

	public List<Integer> projectCol(int colIdx) {
		boolean[] temp = new boolean[getNumRows()];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = getCell(i, colIdx);
		}
		return project(temp);
	}

	public void saveToFile(String filename) throws FileNotFoundException {
		PrintWriter temp = new PrintWriter(filename);
		temp.print(toString());
		temp.close();

	}

	public String toString() {
		StringJoiner sj = new StringJoiner("", "", "");
		List<Integer> temp = new ArrayList<Integer>();
		Integer count = 0;
		int counter = 0;
		int cols = numCols;
		String nums = numRows + " " + numCols + System.lineSeparator();
		for (int i = 0; i < numRows; i++) {
			temp = projectRow(i);
			count = temp.size();

			for (int j = 0; j < count - 1; j++) {
				nums = nums + temp.get(j) + " ";

			}
			nums = nums + temp.get(count - 1);
			nums = nums + System.lineSeparator();
		}
		for (int i = 0; i < numCols; i++) {
			temp = projectCol(i);
			count = temp.size();
			for (int j = 0; j < count - 1; j++) {
				nums = nums + temp.get(j) + " ";
			}
			nums = nums + temp.get(count - 1);
			nums = nums + System.lineSeparator();
		}

		for (int i = 0; i < grid.length; i++) {
			if (grid[i]) {
				sj.add("1");
			} else if (!grid[i]) {
				sj.add("0");
			}
			counter++;

			if (counter == cols && cols < (numRows * numCols)) {
				sj.add(System.lineSeparator());
				cols += numCols;
			}
		}
		return nums + sj;
	}
}
