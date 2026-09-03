package meow;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * Controls the main application window.
 */
public class MainWindow {
    private final Meow meow = new Meow();

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox dialogContainer;

    @FXML
    private TextField userInput;

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(new Label("Meow: Hello! What can I do for you? 😼"));
    }

    /**
     * Handles user input from the text field.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        if (input.isBlank()) {
            return;
        }

        String response = meow.getResponse(input);

        dialogContainer.getChildren().add(new Label("You: " + input));
        dialogContainer.getChildren().add(new Label("Meow: " + response));

        userInput.clear();
    }
}
