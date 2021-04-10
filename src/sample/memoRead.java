package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import sample.datamodel.Memo;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

// A class for reading a memo
public class memoRead {

    // fxml references
    @FXML
    private Label labelDate;

    @FXML
    private Label labelTitle;

    @FXML
    private Label labelContent;

    public void showMemo(Memo selectedMemo){
        // Spaghetti code for date modification
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(selectedMemo.getKuupaev(), formatter);

        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        String strDate = date.format(formatter2);

        // setting the values of the text into the correct form
        labelDate.setText(strDate);
        labelTitle.setText(selectedMemo.getTiitel());
        labelContent.setText(selectedMemo.getSisu());
    }
}
