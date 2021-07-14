package Modell;

import java.util.List;

public interface CurrencyDAO {
    List<CurrencyConv> findAll();
    boolean addToDB(CurrencyConv sz);
}
