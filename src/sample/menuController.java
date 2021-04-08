package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.datamodel.Memo;

import java.awt.*;
public class menuController {
    @FXML
    private TextField titelField;

    @FXML
    private TextField dateField;

    @FXML
    private TextArea contentField;

    public Memo getNewMemo(){
        String titel = titelField.getText();
        String date = dateField.getText();
        String content = contentField.getText();

        Memo newContact = new Memo(titel,date,content);
        return newContact;
    }

    public void editMemo(Memo selectedMemo) {
        titelField.setText(selectedMemo.getTiitel());
        dateField.setText(selectedMemo.getKuupaev());
        contentField.setText(selectedMemo.getSisu());

    }

    public void updateMemo(Memo selectedMemo) {
        selectedMemo.setTiitel(titelField.getText());
        selectedMemo.setKuupaev(dateField.getText());
        selectedMemo.setSisu(contentField.getText());
    }
}
