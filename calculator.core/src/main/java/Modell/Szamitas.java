package Modell;

import java.time.LocalDate;


public class Szamitas {
    private String szamitas;
    private String eredmeny;
    private String idopont;

    public String getSzamitas() {
        return szamitas;
    }

    public String getEredmeny() {
        return eredmeny;
    }

    public String getIdopont() {
        return idopont;
    }

    public Szamitas(String szamitas, String eredmeny){
        this.szamitas = szamitas;
        this.eredmeny=eredmeny;
        this.idopont= LocalDate.now().toString();

    }
    public Szamitas(String szamitas, String eredmeny, String idopont){
        this.szamitas = szamitas;
        this.eredmeny=eredmeny;
        this.idopont= idopont;
    }

}
