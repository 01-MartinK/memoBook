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

// main class for editing and adding memo's
public class menuController {

    // fxml references
    @FXML
    private TextField titelField;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextArea contentField;

    // gets new memo data
    public Memo getNewMemo(){

        // formats date into the specific format dd/MM/yyyy
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateTime = datePicker.getValue();

        // sets the fields to they're corresponding specific values
        String date = formatter.format(dateTime).toString();
        String titel = titelField.getText();
        String content = contentField.getText();

        // creating a new memo and returning it
        Memo newContact = new Memo(titel,date,content);
        return newContact;
    }

    // editing the current memo
    public void editMemo(Memo selectedMemo) {

        // formats date into the specific format dd/MM/yyyy
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(selectedMemo.getKuupaev(), formatter);

        // sets the fields to they're corresponding specific values
        titelField.setText(selectedMemo.getTiitel());
        datePicker.setValue(date);
        contentField.setText(selectedMemo.getSisu());

    }

    // update a memo
    public void updateMemo(Memo selectedMemo) {

        // formats date into the specific format dd/MM/yyyy
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateTime = datePicker.getValue();

        //sets the fields to they're corresponding specific values
        selectedMemo.setTiitel(titelField.getText());
        selectedMemo.setKuupaev(formatter.format(dateTime).toString());
        selectedMemo.setSisu(contentField.getText());
    }
}
