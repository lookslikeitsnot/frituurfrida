package be.vdab.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import be.vdab.entities.Saus;

public class SausRepository extends AbstractRepository{
	private static final String SELECT_SAUZEN = "SELECT id, naam FROM sauzen";
	private static final String SELECT_INGREDIENTEN = 
			"SELECT naam FROM ingredienten WHERE sausId = ?";
	private static final String SAUZEN_BY_INGREDIENT= 
			"SELECT s.naam FROM sauzen s RIGHT JOIN ingredienten i "
			+ "ON s.id = i.sausId WHERE i.naam = ?";
	private static final String DELETE_SAUS = 
			"DELETE FROM sauzen WHERE id = ?";
	
	public List<Saus> findAll(){
		try(Connection connection = dataSource.getConnection();
				Statement statement = connection.createStatement()){
			List<Saus> sauzen = new ArrayList<>();
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			connection.setAutoCommit(false);
			try(ResultSet resultSet = statement.executeQuery(SELECT_SAUZEN)){
				while(resultSet.next()) {
					sauzen.add(resultSetRijNaarSaus(resultSet));
				}
			}
			connection.commit();
			return sauzen;
		}catch (SQLException ex) {
			throw new RepositoryException(ex);
		}
	}
	
	private Saus resultSetRijNaarSaus(ResultSet resultSet) throws SQLException{
		int id = resultSet.getInt("id");
		String naam = resultSet.getString("naam");
		List<String> ingredienten = new ArrayList<>();
		
		try(Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_INGREDIENTEN)){
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			connection.setAutoCommit(false);
			statement.setInt(1, id);
			
			try(ResultSet ingredientResultSet = statement.executeQuery()){
				while(ingredientResultSet.next()) {
					ingredienten.add(ingredientResultSet.getString("naam"));
				}
			}
			connection.commit();
		}catch (SQLException ex) {
			throw new RepositoryException(ex);
		}
		return new Saus(id, naam, ingredienten);
	}
	
	public List<String> findByIngredient(String ingredient){
		List<String> sauzenMetIngredient = new ArrayList<>();
		try(Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(SAUZEN_BY_INGREDIENT)){
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			connection.setAutoCommit(false);
			statement.setString(1, ingredient);
			
			try(ResultSet sauzenResultSet = statement.executeQuery()){
				while(sauzenResultSet.next()) {
					sauzenMetIngredient.add(sauzenResultSet.getString("naam"));
				}
			}
			connection.commit();
		}catch (SQLException ex) {
			throw new RepositoryException(ex);
		}
		return sauzenMetIngredient;
	}
	
	public String getRandomSausName() {
		int randomNum = new  Random().nextInt(findAll().size());
		Object[] sauzen = findAll().toArray();
		Saus randomSaus = (Saus) sauzen[randomNum];
		return randomSaus.getNaam();
	}
	
	public void remove(int nummer) {
		if(findAll().stream().filter(saus->saus.getNummer()==nummer).count()>0) {
			try(Connection connection = dataSource.getConnection();
					PreparedStatement statement = connection.prepareStatement(DELETE_SAUS)){
				connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
				connection.setAutoCommit(false);
				statement.setInt(1, nummer);
				
				System.out.println(statement.executeUpdate());
				connection.commit();
			}catch (SQLException ex) {
				throw new RepositoryException(ex);
			}
		}
	}
}
