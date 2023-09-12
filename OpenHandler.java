package edu.ou.cs2334.project4.handlers;

import java.io.File;
import java.io.IOException;
import edu.ou.cs2334.project4.interfaces.Openable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Window;

public class OpenHandler extends AbstractBaseHandler implements EventHandler<ActionEvent> {

	private Openable opener;

	public OpenHandler(Window window, FileChooser fileChooser, Openable opener) {
		super(window, fileChooser);
		this.opener = opener;

	}

	public void handle(ActionEvent event) {
		File temp = super.fileChooser.showOpenDialog(window);
		if (temp != null) {
			try {
				opener.open(temp);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}