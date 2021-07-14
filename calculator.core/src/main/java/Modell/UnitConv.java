package Modell;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UnitConv {
    private FloatProperty mennyit = new SimpleFloatProperty(this,"mennyit");
    private StringProperty mirol = new SimpleStringProperty(this,"mirol");
    private FloatProperty ans = new SimpleFloatProperty(this,"ans");
    private StringProperty mire = new SimpleStringProperty(this,"mire");

    public UnitConv() {
    }

    public float getMennyit() {
        return mennyit.get();
    }

    public FloatProperty mennyitProperty() {
        return mennyit;
    }

    public void setMennyit(float mennyit) {
        this.mennyit.set(mennyit);
    }

    public String getMirol() {
        return mirol.get();
    }

    public StringProperty mirolProperty() {
        return mirol;
    }

    public void setMirol(String mirol) {
        this.mirol.set(mirol);
    }

    public float getAns() {
        return ans.get();
    }

    public FloatProperty ansProperty() {
        return ans;
    }

    public void setAns(float ans) {
        this.ans.set(ans);
    }

    public String getMire() {
        return mire.get();
    }

    public StringProperty mireProperty() {
        return mire;
    }

    public void setMire(String mire) {
        this.mire.set(mire);
    }
}
