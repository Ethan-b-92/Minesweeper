package mines;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MinesFX extends Application {

	private Pane root;
	private int height, width, numMines;
	private My_controller controller;
	private Mines mine;
	private Button b_arr[][];
	private GridPane grid;

	@Override
	// Load the FXML file and set the Stage
	public void start(Stage primaryStage) {

		try {
			FXMLLoader l = new FXMLLoader();
			l.setLocation(getClass().getResource("grid.fxml"));
			root = l.load();
			controller = l.getController();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene s = new Scene(root);
		primaryStage.setTitle("Minesweeper");
		primaryStage.sizeToScene();
		primaryStage.setScene(s);
		primaryStage.show();
		Button r = controller.getReset();
		r.setOnAction(new reset_pressed(primaryStage));
	}

	// When the reset button pressed
	class reset_pressed implements EventHandler<ActionEvent> {

		private Stage primaryStage;

		// Pressed
		public reset_pressed(Stage primaryStage) {
			this.primaryStage = primaryStage;
		}

		@Override
		// Handle the action event
		public void handle(ActionEvent arg0) {

			try {
				{width = Integer.parseInt(controller.getHeight_txt());}
			} catch (Exception e) {
				controller.getHeight_txt1().setText("");
			}

			try {
				{height = Integer.parseInt(controller.getWidth_txt());}
			} catch (Exception e) {
				controller.getWidth_txt1().setText("");
			}

			try {
				{numMines = Integer.parseInt(controller.getMines_txt());}
			} catch (Exception e) {
				controller.getMines_txt1().setText("");
			}

			mine = new Mines(height, width, numMines);
			b_arr = new Button[height][width];
			grid = new GridPane();

			for (int i = 0; i < height; i++)
				for (int j = 0; j < width; j++) {
					b_arr[i][j] = new Button(mine.get(i, j));
					b_arr[i][j].setMinSize(50, 50);
					b_arr[i][j].setOnMouseClicked(new button_pressed(i, j));
					grid.add(b_arr[i][j], i, j);
				}

			controller.getStackPane().getChildren().clear();
			controller.getStackPane().getChildren().add(grid);

			if ((width * 50) * (height * 50) < (306 * 386)) {
				primaryStage.setHeight(306);
				primaryStage.setWidth(386);
			}

			else {
				primaryStage.setWidth(height * 50 + 200);// set size of window
				primaryStage.setHeight((width * 50));
			}

		}
	}

	// When button pressed
	class button_pressed implements EventHandler<MouseEvent> {
		int i, j;

		// Constructor get the coordinates of the place
		public button_pressed(int i, int j) {

			this.i = i;
			this.j = j;
		}

		@Override
		// Mouse event
		public void handle(MouseEvent arg0) {
			{
			if (arg0.getButton() == MouseButton.SECONDARY)// if pressed by Right click
				mine.toggleFlag(i, j);
			else {
				if (!mine.open(i, j))
					mine.setShowAll(true);

				if (mine.isDone()) {

					mine.setShowAll(true);
					Alert a = new Alert(AlertType.INFORMATION);
					a.setContentText("OMG! you just won");
					a.show();
				}
			}
			}

			for (int i = 0; i < height; i++)
				for (int j = 0; j < width; j++)
					b_arr[i][j].setText(mine.get(i, j));
		}
	}

	// Launch the screen
	public static void main(String[] args) {
		launch(args);
	}
}
