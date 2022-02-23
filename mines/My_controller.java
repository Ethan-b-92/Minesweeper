package mines;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;

public class My_controller {

	@FXML
	private Button reset;

	@FXML
	private Label width;

	@FXML
	private TextArea width_txt;

	public Button getReset() {
		return reset;
	}

	public String getWidth_txt() {
		return width_txt.getText();
	}

	public String getHeight_txt() {
		return height_txt.getText();
	}

	public String getMines_txt() {
		return mines_txt.getText();
	}

	public TextArea getMines_txt1() {
		return mines_txt;
	}

	public TextArea getHeight_txt1() {
		return height_txt;
	}

	public TextArea getWidth_txt1() {
		return width_txt;
	}

	public StackPane getStackPane() {
		return StackPane;
	}

	@FXML
	private Label height;

	@FXML
	private TextArea height_txt;

	@FXML
	private Label mines;

	@FXML
	private TextArea mines_txt;

	@FXML
	private StackPane StackPane;

}
