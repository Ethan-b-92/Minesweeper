package simpleFX;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Vote extends Application {

	// Launch the screen
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	// Start the stage
	public void start(Stage stage) {
		Scene scene = new Scene(makeGrid());
		stage.setScene(scene);
		stage.setTitle("Voting Machine");
		stage.show();
	}

	private int i = 0;

	private GridPane makeGrid() {

		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(20));
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		Label counter = new Label("0");
		Button ofra = new Button("Ofra Haza");
		Button yardena = new Button("Yardena Arazi");

		// Increase the label
		class LabelIncreaser implements EventHandler<ActionEvent> {
			@Override
			public void handle(ActionEvent event) {
				i++;
				counter.setText("" + i + "");
			}
		}

		// Decrease the label
		class LabelDecreaser implements EventHandler<ActionEvent> {
			@Override
			public void handle(ActionEvent event) {
				i--;
				counter.setText("" + i + "");
			}
		}

		ofra.setOnAction(new LabelIncreaser());
		yardena.setOnAction(new LabelDecreaser());
		counter.setStyle("-fx-background-color: red");
		counter.prefWidthProperty().bind(gridPane.widthProperty());
		counter.setMinHeight(30);
		counter.setAlignment(Pos.CENTER);

		gridPane.add(ofra, 0, 0);
		gridPane.add(yardena, 1, 0);
		gridPane.add(counter, 0, 2, 2, 1);

		ColumnConstraints c = new ColumnConstraints();
		c.setHalignment(HPos.RIGHT);
		gridPane.getColumnConstraints().add(c);

		return gridPane;
	}
}
