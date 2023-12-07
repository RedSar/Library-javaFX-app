package demo;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.apache.commons.text.StringEscapeUtils;

public class ASCII extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Simulating a response from an external REST API containing HTML entities
        String htmlResponse = "<p>I will display \u2705</p>\n" +

                "<p>I will display \u274C</p>";

        // Decode HTML entities
        String decodedHtml = StringEscapeUtils.unescapeHtml4(htmlResponse);

        Label responseLabel = new Label();
        responseLabel.setText(decodedHtml);

        StackPane root = new StackPane();
        root.getChildren().add(responseLabel);

        Scene scene = new Scene(root, 300, 200);

        primaryStage.setTitle("HTML ASCII Handling");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
