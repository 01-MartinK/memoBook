package sample;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.datamodel.Memo;

import java.awt.*;
public class menuController {
    @FXML
    private TextField titelField;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextArea contentField;

    public Memo getNewMemo(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateTime = datePicker.getValue();

        String date = formatter.format(dateTime).toString();
        String titel = titelField.getText();
        String content = contentField.getText();

        Memo newContact = new Memo(titel,date,content);
        return newContact;
    }

    public void editMemo(Memo selectedMemo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(selectedMemo.getKuupaev(), formatter);

        titelField.setText(selectedMemo.getTiitel());
        datePicker.setValue(date);
        contentField.setText(selectedMemo.getSisu());

    }

    public void updateMemo(Memo selectedMemo) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateTime = datePicker.getValue();

        selectedMemo.setTiitel(titelField.getText());
        selectedMemo.setKuupaev(formatter.format(dateTime).toString());
        selectedMemo.setSisu(contentField.getText());
    }
}
