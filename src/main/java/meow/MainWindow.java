package meow;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
        dialogContainer.setFillWidth(true);
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        addMeowMessage("Hello! What can I do for you? 😼");
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
        addUserMessage(input);
        addMeowMessage(response);

        userInput.clear();

        if (input.equals("bye")) {
            Platform.exit();
        }
    }

    /**
     * Adds a user message to the chat window.
     *
     * @param message the user's message
     */
    private void addUserMessage(String message) {
        Label label = new Label(message);
        label.setWrapText(true);
        label.setMaxWidth(280);
        label.getStyleClass().add("user-bubble");

        ImageView avatar = createAvatar("/images/user.png");

        HBox container = new HBox(8, label, avatar);
        container.setAlignment(Pos.CENTER_RIGHT);
        container.setPadding(new Insets(5, 10, 5, 50));

        dialogContainer.getChildren().add(container);
    }

    /**
     * Adds a Meow message to the chat window.
     *
     * @param message Meow's response
     */
    private void addMeowMessage(String message) {
        ImageView avatar = createAvatar("/images/meow.png");

        Label label = new Label(message);
        label.setWrapText(true);
        label.setMaxWidth(280);
        label.getStyleClass().add("meow-bubble");

        HBox container = new HBox(8, avatar, label);
        container.setAlignment(Pos.CENTER_LEFT);
        container.setPadding(new Insets(5, 50, 5, 10));

        dialogContainer.getChildren().add(container);
    }

    /**
     * Creates a circular avatar.
     *
     * @param emoji the emoji to display
     * @return the avatar label
     */
    /**
     * Creates an avatar from the given image resource.
     *
     * @param imagePath the path to the avatar image
     * @return the avatar image view
     */
    private ImageView createAvatar(String imagePath) {
        Image image = new Image(getClass().getResourceAsStream(imagePath));
        ImageView avatar = new ImageView(image);

        avatar.setFitWidth(40);
        avatar.setFitHeight(40);
        avatar.setPreserveRatio(true);

        return avatar;
    }
}
