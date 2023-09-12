package edu.ou.cs2334.project4.views;

import java.util.HashMap;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class NonogramMakerView {

	private BorderPane borderPane;
	private MenuBar menubar;
	private CellGridView cellGridView;
	private HashMap<String, MenuItem> menuItemsMap = new HashMap<String, MenuItem>();

	public static String MENU_ITEM_OPEN = "MENU_ITEM_OPEN";

	public static String MENU_ITEM_SAVE = "MENU_ITEM_SAVE";

	public static String MENU_ITEM_EXIT = "MENU_ITEM_EXIT";

	public NonogramMakerView(int numRows, int numCols, int cellLength) {
		initMenuBar();
		cellGridView = new CellGridView(numRows, numCols, cellLength);
		borderPane = new BorderPane();
		borderPane.setCenter(cellGridView.getPane());
		borderPane.setTop(menubar);
	}

	private void initMenuBar() {

		Menu menu = new Menu("_File");

		MenuItem open = new MenuItem("_Open");
		MenuItem save = new MenuItem("_Save");
		MenuItem exit = new MenuItem("_Exit");

		menu.getItems().add(open);
		menu.getItems().add(save);
		menu.getItems().add(exit);

		menubar = new MenuBar();

		menubar.getMenus().addAll(menu);

		menuItemsMap.put(MENU_ITEM_EXIT, exit);
		menuItemsMap.put(MENU_ITEM_OPEN, open);
		menuItemsMap.put(MENU_ITEM_SAVE, save);

		exit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				Platform.exit();
			}
		});
	}

	public MenuItem getMenuItem(String name) {
		return menuItemsMap.get(name);
	}

	public Pane getPane() {
		return borderPane;
	}

	public void initButtons(int numRows, int numCols, int cellLength) {
		cellGridView.initButtons(numRows, numCols, cellLength);
	}

	public ToggleButton getToggleButton(int row, int col) {
		return cellGridView.getToggleButtons(row, col);
	}

	public int getNumRow() {
		return cellGridView.getNumRows();
	}

	public int getNumCols() {
		return cellGridView.getNumCols();
	}

}
