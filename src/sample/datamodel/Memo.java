package sample.datamodel;

import javafx.beans.property.SimpleStringProperty;

public class Memo {

    private SimpleStringProperty tiitel = new SimpleStringProperty("");
    private SimpleStringProperty kuupaev = new SimpleStringProperty("");
    private SimpleStringProperty sisu = new SimpleStringProperty("");

    public Memo(){

    }

    public Memo(String tiitel, String kuupaev, String sisu){
        this.kuupaev.set(kuupaev);
        this.tiitel.set(tiitel);
        this.sisu.set(sisu);
    }

    public String getTiitel() {
        return tiitel.get();
    }

    public SimpleStringProperty tiitelProperty() {
        return tiitel;
    }

    public void setTiitel(String tiitel) {
        this.tiitel.set(tiitel);
    }

    public String getKuupaev() {
        return kuupaev.get();
    }

    public SimpleStringProperty kuupaevProperty() {
        return kuupaev;
    }

    public void setKuupaev(String kuupaev) {
        this.kuupaev.set(kuupaev);
    }

    public String getSisu() {
        return sisu.get();
    }

    public SimpleStringProperty sisuProperty() {
        return sisu;
    }

    public void setSisu(String sisu) {
        this.sisu.set(sisu);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "tiitel=" + tiitel +
                ", kuupäev=" + kuupaev +
                ", sisu=" + sisu +
                '}';
    }
}
