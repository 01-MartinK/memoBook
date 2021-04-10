package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import sample.datamodel.Memo;
import sample.datamodel.MemoData;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class Controller {

    // fxml reference's
    @FXML
    private BorderPane mainPanel;

    @FXML
    private TableView<Memo> memoTables;

    // memoData for updating the board
    private MemoData data;

    // initial initialization
    public void initialize(){

        // setting up the data
        data = new MemoData();
        data.loadMemos();
        memoTables.setItems(data.getContacts());
    }

    // shows a menu for memo adding
    @FXML
    public void showAddMemo(ActionEvent actionEvent) {

        // new dialog for the menu
        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        dialog.initOwner(mainPanel.getScene().getWindow());
        dialog.setTitle("Add New Memo");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("menuDialog.fxml"));

        // See if the dialog can get the window
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        }catch (IOException e){
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        // add to buttons to the dialog for canceling and for finishing
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        // wait for a response in the dialog
        Optional<ButtonType> result = dialog.showAndWait();

        // respond to the response
        if(result.isPresent() && result.get() == ButtonType.OK){
            menuController menu = fxmlLoader.getController();
            Memo newMemo = menu.getNewMemo();
            data.addMemo(newMemo);
            data.saveMemos();
        }
    }

    // shows a menu for memo deletion
    @FXML
    public void showDeleteMemo(ActionEvent actionEvent) {

        // get the current selected memo from the memoTable
        Memo selectedMemo = memoTables.getSelectionModel().getSelectedItem();

        // check if a memo is selected
        if (selectedMemo == null){

            // show the alert for not selecting a memo
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No memo selected!");
            alert.setHeaderText(null);
            alert.setContentText("I need you to select a memo before deletion!");
            alert.showAndWait();
            return;
        }

        // show an alert for if they want to do it
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Memo");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete the selected memo "+ selectedMemo.getKuupaev()+ " with the title of " + selectedMemo.getTiitel());

        // wait for a response in the dialog
        Optional<ButtonType> result = alert.showAndWait();

        // respond to the response
        if(result.isPresent() && result.get() == ButtonType.OK){
            data.deleteMemo(selectedMemo);
            data.saveMemos();
        }
    }

    // shows a menu for memo editing
    @FXML
    public void showEditMemo(ActionEvent actionEvent) {

        // get the current selected memo from the memoTable
        Memo selectedMemo = memoTables.getSelectionModel().getSelectedItem();

        // check if a memo is selected
        if (selectedMemo == null){

            // show the alert for not selecting a memo
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No memo selected!");
            alert.setHeaderText(null);
            alert.setContentText("I need you to select a memo before editing!");
            alert.showAndWait();
            return;
        }

        // new dialog for the menu
        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        dialog.initOwner(mainPanel.getScene().getWindow());
        dialog.setTitle("Edit Memo");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("menuDialog.fxml"));
        // See if the dialog can get the window
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        }catch (IOException e){
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        // add to buttons to the dialog for canceling and for finishing
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        // get the menuController class
        menuController contactController = fxmlLoader.getController();
        contactController.editMemo(selectedMemo);

        // wait for a response in the dialog
        Optional<ButtonType> result = dialog.showAndWait();

        // respond to the response
        if(result.isPresent() && result.get() == ButtonType.OK){
            contactController.updateMemo(selectedMemo);
            data.saveMemos();
        }
    }

    // shows a menu for memo reading
    @FXML
    public void showReadMemo(ActionEvent actionEvent) {

        // get the current selected memo from the memoTable
        Memo selectedMemo = memoTables.getSelectionModel().getSelectedItem();

        // check if a memo is selected
        if (selectedMemo == null){

            // show the alert for not selecting a memo
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No memo selected!");
            alert.setHeaderText(null);
            alert.setContentText("I need you to select a memo before reading!");
            alert.showAndWait();
            return;
        }

        // new dialog for the menu
        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        dialog.initOwner(mainPanel.getScene().getWindow());
        dialog.setTitle("Read Memo");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("memoRead.fxml"));
        // See if the dialog can get the window
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        }catch (IOException e){
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        // add a close button to the dialog
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);

        // get the controller for the memoReader
        memoRead memoRead = fxmlLoader.getController();
        memoRead.showMemo(selectedMemo);

        // wait for a response in the dialog
        Optional<ButtonType> result = dialog.showAndWait();

        // respond to the response
        if(result.isPresent() && result.get() == ButtonType.OK){
            data.saveMemos();
        }
    }

    // shows a fileChooser for memo saving
    @FXML
    public void showSaveMemo(ActionEvent actionEvent) {

        // new fileChooser for memo saving
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Resource File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Memo Files .xml", "*.xml"));
        File selectedFile = fileChooser.showSaveDialog(new Stage());

        // set local memos file path to selectedFile path and then save the memos file
        data.setLocalMemosFile(selectedFile.toString());
        data.saveMemos();
    }

    // shows a fileChooser for memo loading
    @FXML
    public void showLoadMemo(ActionEvent actionEvent) {

        // new fileChooser for memo loading
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Memo Files .xml", "*.xml"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        // set local memos file path to selectedFile path and then load the memos file from the path
        data.setLocalMemosFile(selectedFile.toString());
        data.deleteMemos();
        data.loadMemos();
    }
}
