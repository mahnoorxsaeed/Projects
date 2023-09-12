package edu.ou.cs2334.project4.handlers;

import edu.ou.cs2334.project4.models.NonogramMakerModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;

public class ToggleButtonEventHandler implements EventHandler<ActionEvent> {

	private NonogramMakerModel model;
	private int rowIdx;
	private int colIdx;

	public ToggleButtonEventHandler(NonogramMakerModel model, int rowIdx, int colIdx) {
		this.rowIdx = rowIdx;
		this.colIdx = colIdx;
		this.model = model;
	}

	public void handle(ActionEvent event) {
		ToggleButton temp = (ToggleButton) event.getSource();
		model.setCell(rowIdx, colIdx, temp.isSelected());
	}

}