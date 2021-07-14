package Modell;

import java.util.List;

public interface UnitDAO {

    List<UnitConv> findAll();
    boolean addToDB(UnitConv uc);
}
