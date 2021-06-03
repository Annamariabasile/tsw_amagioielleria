package carrello;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import pack.DriverManagerConnectionPool;
import carrello.CarrelloBean;
import carrello.CarrelloModel;




	public class CarrelloDM implements CarrelloModel {
		private static final String TABLE_NAME= "Carrello";

		public synchronized void doSave(CarrelloBean carrello)throws SQLException{
		Connection connection = null;
		PreparedStatement rs = null;

		String insert2="INSERT INTO "+CarrelloDM.TABLE_NAME
		+ " (IDProdotto, IDOrdine, Quantità, IVA_Effettiva, prezzo_EffettivoUnitario) "
		+ " VALUES (?,?,?,?,?)";

		try {
		connection=DriverManagerConnectionPool.getConnection();
		rs=connection.prepareStatement(insert2);
		rs.setInt(1, carrello.getIDProdotto());
		rs.setInt(2, carrello.getIDOrdine());
		rs.setInt(3, carrello.getQuantita());
		rs.setInt(4, carrello.getIVA_Effettiva());
		rs.setDouble(5, carrello.getprezzo_EffettivoUnitario());



		rs.executeUpdate();
		connection.commit();


		} finally {
			try {
				if (rs != null)
					rs.close();

			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);

			}
		}

	}

		public synchronized Collection <CarrelloBean> doRetriveByOrder(int idOrdine) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<CarrelloBean> vociOrdini = new LinkedList<CarrelloBean>();

		String query="SELECT * FROM "+CarrelloDM.TABLE_NAME+" WHERE ordine=? ";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, idOrdine);

			ResultSet result=preparedStatement.executeQuery();

		while(result.next()) {
			CarrelloBean Carrello=new CarrelloBean();

			Carrello.setIDProdotto(result.getInt("IDProdotto"));
			Carrello.setIDOrdine(result.getInt("IDOrdine"));
			Carrello.setQuantita(result.getInt("Quantità"));
			Carrello.setIVA_Effettiva(result.getInt("IVA_Effettiva"));
			Carrello.setprezzo_EffettivoUnitario(result.getFloat("prezzo_EffettivoUnitario"));

			vociOrdini.add(Carrello);
		}


		}finally {
				try {
						if (preparedStatement != null)
								preparedStatement.close();
					}finally {
							DriverManagerConnectionPool.releaseConnection(connection);
							}
			}

			return vociOrdini;
		}
		}
		

