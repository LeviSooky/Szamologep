package Modell;

import java.util.List;

public interface SzamitasDAO {
    
    List<Szamitas> findAll();
    boolean addToDB(Szamitas szamitas);
}
