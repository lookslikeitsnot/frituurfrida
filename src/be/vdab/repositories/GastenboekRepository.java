package be.vdab.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import be.vdab.entities.GastenboekEntry;

public class GastenboekRepository extends AbstractRepository {
	private static final String SELECT_ENTRIES = "SELECT datum, gastentekst, gastnaam FROM gastenboek";
	private static final String ADD_ENTRY = "INSERT INTO gastenboek(gastentekst, gastnaam) VALUES (?,?)";

	public List<GastenboekEntry> findAll() {
		try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement()) {
			List<GastenboekEntry> gastenboekEntries = new ArrayList<>();
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			connection.setAutoCommit(false);
			try (ResultSet resultSet = statement.executeQuery(SELECT_ENTRIES)) {
				while (resultSet.next()) {
					gastenboekEntries.add(resultSetRijNaarEntry(resultSet));
				}
			}
			connection.commit();
			return gastenboekEntries;

		} catch (SQLException ex) {
			throw new RepositoryException(ex);
		}
	}

	private GastenboekEntry resultSetRijNaarEntry(ResultSet resultSet) throws SQLException {
		LocalDateTime datum = resultSet.getTimestamp("datum").toLocalDateTime();
		String gastentekst = resultSet.getString("gastenTekst");
		String gastnaam = resultSet.getString("gastnaam");
		return new GastenboekEntry(gastentekst, gastnaam, datum);
	}

	public void create(GastenboekEntry gastenboekEntry) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(ADD_ENTRY)) {
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			connection.setAutoCommit(false);
			statement.setString(1, gastenboekEntry.getGastentekst());
			statement.setString(2, gastenboekEntry.getGastnaam());
			statement.executeUpdate();			
			connection.commit();
		} catch (SQLException ex) {
			throw new RepositoryException(ex);
		}
	}
}
