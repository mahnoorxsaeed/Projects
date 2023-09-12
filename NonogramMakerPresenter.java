package edu.ou.cs2334.project4.presenters;

import java.io.File;
import java.io.FileNotFoundException;
import edu.ou.cs2334.project4.handlers.OpenHandler;
import edu.ou.cs2334.project4.handlers.SaveHandler;
import edu.ou.cs2334.project4.handlers.ToggleButtonEventHandler;
import edu.ou.cs2334.project4.interfaces.Openable;
import edu.ou.cs2334.project4.interfaces.Saveable;
import edu.ou.cs2334.project4.models.NonogramMakerModel;
import edu.ou.cs2334.project4.views.NonogramMakerView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;

public class NonogramMakerPresenter implements Saveable, Openable {

	private NonogramMakerView view;
	private NonogramMakerModel model;
	private int cellLength;

	public NonogramMakerPresenter(int numRows, int numColumns, int cellLength) {
		view = new NonogramMakerView(numRows, numColumns, cellLength);
		model = new NonogramMakerModel(numRows, numColumns);
		this.cellLength = cellLength;
		init();
	}

	private void init() {
		initToggleButtons();
		bindToggleButtons();
		configureMenuItems();
	}

	public Pane getPane() {
		return view.getPane();
	}

	private Window getWindow() {
		try {
			return view.getPane().getScene().getWindow();
		} catch (NullPointerException e) {
			return null;
		}
	}



	private void initToggleButtons() {
		view.initButtons(model.getNumRows(), model.getNumCols(), cellLength);
		if (getWindow() != null) {
			getWindow().sizeToScene();
		}
	}

	private void configureMenuItems() {
		FileChooser filechooser = new FileChooser();
		filechooser.setTitle("Open");
		filechooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
		filechooser.setInitialDirectory(new File("."));
		view.getMenuItem(NonogramMakerView.MENU_ITEM_OPEN).setOnAction(new OpenHandler(getWindow(), filechooser, this));
		filechooser.setTitle("Save");
		view.getMenuItem(NonogramMakerView.MENU_ITEM_SAVE).setOnAction(new SaveHandler(getWindow(), filechooser, this));

	}

	private void bindToggleButtons() {
		for (int i = 0; i < model.getNumRows(); i++) {
			for (int j = 0; j < model.getNumCols(); j++) {
				view.getToggleButton(i, j).setSelected(model.getCell(i, j));
				view.getToggleButton(i, j).setOnAction(new ToggleButtonEventHandler(model, i, j));
			}
		}
	}

	public void open(File file) throws FileNotFoundException {
		model = new NonogramMakerModel(file);
		init();
	}

	public void save(String filename) throws FileNotFoundException {
		this.model.saveToFile(filename);
	}
}
