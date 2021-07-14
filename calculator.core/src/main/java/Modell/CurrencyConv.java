package Modell;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;

public class CurrencyConv {

    float mennyit;
    String mirol;
    float  ans;
    String  mire;

    public float getMennyit() {
        return mennyit;
    }

    public String getMirol() {
        return mirol;
    }

    public float getAns() {
        return ans;
    }

    public String getMire() {
        return mire;
    }

    public CurrencyConv(float mennyit, String mirol, float ans, String mire) {
        this.mennyit = mennyit;
        this.mirol = mirol;
        this.ans = ans;
        this.mire = mire;
    }
}
