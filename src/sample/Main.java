/*
# A simple memo system for memo editing.
#
# What has it got:
#   * editing
#   * deletion
#   * reading
#   * saving and loading
#
# School assignment.
#
#   Author: MK
#
*/

package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage stage;

    // starts the main window and main fxml file
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Memo editor");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    // tells to start the main window
    public static void main(String[] args) {
        launch(args);
    }
}
