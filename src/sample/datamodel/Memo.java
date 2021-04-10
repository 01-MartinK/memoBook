package sample.datamodel;

import javafx.beans.property.SimpleStringProperty;

public class Memo {

    // string properties for memo data
    private SimpleStringProperty tiitel = new SimpleStringProperty("");
    private SimpleStringProperty kuupaev = new SimpleStringProperty("");
    private SimpleStringProperty sisu = new SimpleStringProperty("");

    // empty memo starter method
    public Memo(){

    }

    // memo starter method with primary values
    public Memo(String tiitel, String kuupaev, String sisu){
        this.kuupaev.set(kuupaev);
        this.tiitel.set(tiitel);
        this.sisu.set(sisu);
    }

    // value getter and setters

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

    // toString method overriding for convenience
    @Override
    public String toString() {
        return "Contact{" +
                "tiitel=" + tiitel +
                ", kuupäev=" + kuupaev +
                ", sisu=" + sisu +
                '}';
    }
}
