package simpleFx2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

public class MyController {
	private int count = 0;

	@FXML
	private ToggleButton button1;

	@FXML
	private ToggleButton button2;

	@FXML
	private TextField text;

	// Increase the label
	@FXML
	void pressOne(ActionEvent event) {
		count++;
		text.setText("" + count);
	}

	// Decrease the label
	@FXML
	void pressTwo(ActionEvent event) {
		count--;
		text.setText("" + count);
	}

}
