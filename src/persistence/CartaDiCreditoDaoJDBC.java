package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.betting.SchemaDiScommessa;
import model.betting.Scommessa;
import model.footballdata.EsitoPartita;
import model.footballdata.Partita;
import model.footballdata.Squadra;
import model.users.CartaDiCredito;
import model.users.Conto;
import persistence.dao.CartaDiCreditoDao;


public class CartaDiCreditoDaoJDBC implements CartaDiCreditoDao {



	@Override
	public void save(CartaDiCredito carta,Connection connection)throws SQLException {
		String insert = "insert into cartaDiCredito(codice, data_scadenza, saldo) values (?,?,?)";
		PreparedStatement statement = connection.prepareStatement(insert);
		statement.setString(1, carta.getCodiceCarta());
		statement.setDate(2, null);
		statement.setFloat(3, carta.getSaldo());
		statement.executeUpdate();
	}

	@Override
	public CartaDiCredito findByPrimaryKey(String matricola) {
		Connection connection = PostgresDAOFactory.dataSource.getConnection();
		CartaDiCredito carta= null;
		try {
			PreparedStatement statement;
			String query = "select c.data_scadenza, c.saldo from cartaDiCredito as c where c.codice=? "; 
			statement = connection.prepareStatement(query);
			statement.setString(1, matricola);
			ResultSet result = statement.executeQuery();
			if (result.next()){
				carta=new CartaDiCredito(matricola);
				carta.setSaldo(result.getFloat(2));
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				if(connection!=null)
					connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}	
		return carta;
	}

	@Override
	public void update(CartaDiCredito carta, Connection connection)throws SQLException {

		String update = "update cartaDiCredito SET data_scadenza = ?, saldo= ? where codice=? ";
		PreparedStatement statement = connection.prepareStatement(update);
		if(carta.getScadenza()!=null)
			statement.setDate(1, new java.sql.Date(carta.getScadenza().getTime()));
		else statement.setDate(1, null);
		statement.setFloat(2, carta.getSaldo());
		statement.setString(3, carta.getCodiceCarta());
		statement.executeUpdate();
			
	}

	@Override
	public void delete(CartaDiCredito carta) {
		
		Connection connection = PostgresDAOFactory.dataSource.getConnection();
		try {
			String delete = "delete FROM cartaDiCredito WHERE codice = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, carta.getCodiceCarta());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				if(connection!=null)
					connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

}
