package edu.ou.cs2334.project4.views;

import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class CellGridView {

	private ArrayList<ToggleButton> gridButtons = new ArrayList<ToggleButton>();
	private GridPane gridPane;
	private int numRows;
	private int numCols;

	public CellGridView(int numRows, int numCols, int cellLength) {
		this.numCols = numCols;
		this.numRows = numRows;
		gridPane = new GridPane();
		gridPane.setMinSize(numRows, numCols);
		gridPane.setAlignment(Pos.CENTER);

	}

	public void initButtons(int numRows, int numCols, int cellLength) {
		this.numRows = numRows;
		this.numCols = numCols;
		gridButtons.clear();
		gridPane.getChildren().clear();

		for (int j = 0; j < numRows; j++) {
			for (int i = 0; i < numCols; i++) {
				ToggleButton temp = new ToggleButton();
				gridPane.add(temp, i, j);
				gridButtons.add(temp);
			}
		}

		for (ToggleButton b : gridButtons) {
			b.setMinSize(cellLength, cellLength);
			b.setMaxSize(cellLength, cellLength);
			b.setPrefSize(cellLength, cellLength);
		}
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumCols() {
		return numCols;
	}

	public ToggleButton getToggleButtons(int row, int col) {
		return this.gridButtons.get((row * numCols) + col);

	}

	public Pane getPane() {
		return gridPane;
	}

}
