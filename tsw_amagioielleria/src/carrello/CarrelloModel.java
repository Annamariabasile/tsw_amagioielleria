package carrello;

import java.sql.SQLException;
import java.util.Collection;

public interface CarrelloModel {
public void doSave(CarrelloBean voce)throws SQLException;
public Collection <CarrelloBean> doRetriveByOrder(int idOrdine )throws SQLException;

}
