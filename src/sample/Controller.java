package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import sample.datamodel.Memo;
import sample.datamodel.MemoData;

import java.io.IOException;
import java.util.Optional;

public class Controller {
    @FXML
    private BorderPane mainPanel;

    @FXML
    private TableView<Memo> memoTables;

    private MemoData data;

    public void initialize(){
        data = new MemoData();
        data.loadMemos();
        memoTables.setItems(data.getContacts());
    }

    @FXML
    public void showAddMemo(ActionEvent actionEvent) {
        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        dialog.initOwner(mainPanel.getScene().getWindow());
        dialog.setTitle("Add New Memo");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("menuDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        }catch (IOException e){
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            menuController menu = fxmlLoader.getController();
            Memo newMemo = menu.getNewMemo();
            data.addMemo(newMemo);
            data.saveMemos();
        }
    }

    @FXML
    public void showDeleteMemo(ActionEvent actionEvent) {
        Memo selectedMemo = memoTables.getSelectionModel().getSelectedItem();
        if (selectedMemo == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No memo selected!");
            alert.setHeaderText(null);
            alert.setContentText("I need you to select a memo before deletion!");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Memo");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete the selected memo "+ selectedMemo.getKuupaev()+ " " + selectedMemo.getTiitel());

        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            data.deleteMemo(selectedMemo);
            data.saveMemos();
        }
    }

    @FXML
    public void showEditMemo(ActionEvent actionEvent) {
        Memo selectedMemo = memoTables.getSelectionModel().getSelectedItem();
        if (selectedMemo == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No memo selected!");
            alert.setHeaderText(null);
            alert.setContentText("I need you to select a memo before editing!");
            alert.showAndWait();
            return;
        }

        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        dialog.initOwner(mainPanel.getScene().getWindow());
        dialog.setTitle("Edit Memo");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("menuDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        }catch (IOException e){
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        menuController contactController = fxmlLoader.getController();
        contactController.editMemo(selectedMemo);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            contactController.updateMemo(selectedMemo);
            data.saveMemos();
        }
    }
}
