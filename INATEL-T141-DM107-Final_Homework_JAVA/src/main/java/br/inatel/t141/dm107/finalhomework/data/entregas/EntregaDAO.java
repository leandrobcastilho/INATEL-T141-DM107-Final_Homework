package br.inatel.t141.dm107.finalhomework.data.entregas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class EntregaDAO {

	private Connection conn;

	public EntregaDAO(Connection conn) {
		this.conn = conn;
	}

	public EntregaEntity getEntregaByNumeroDoPedido(String numeroDoPedido) throws SQLException {
		EntregaEntity entregaEntity = null;
		String sql = "SELECT * FROM Entregas WHERE NumeroDoPedido=?";

		try (PreparedStatement statement = conn.prepareStatement(sql)) {
			statement.setString(1, numeroDoPedido);

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				int idDaEntrega = result.getInt(1);
				String numeroDoPedidoDaEntrega = result.getString(2);
				int idDoCliente = result.getInt(3);
				String nomeDoRecebedor = result.getString(4);
				String cpfDoRecebedor = result.getString(5);
				Timestamp dataHoraDaEntrega = result.getTimestamp(6);

				entregaEntity = new EntregaEntity();
				entregaEntity.setId(idDaEntrega);
				entregaEntity.setNumeroDoPedido(numeroDoPedidoDaEntrega);
				entregaEntity.setIdDoCliente(idDoCliente);
				entregaEntity.setNomeDoRecebedor(nomeDoRecebedor);
				entregaEntity.setCpfDoRecebedor(cpfDoRecebedor);
				entregaEntity.setDataHoraDaEntrega(dataHoraDaEntrega);
			}
			statement.close();

		} catch (SQLException e) {
			throw e;
		}

		return entregaEntity;
	}

	public EntregaEntity getEntregaById(int id) throws SQLException {
		EntregaEntity entregaEntity = null;
		String sql = "SELECT * FROM Entregas WHERE id=?";

		try (PreparedStatement statement = conn.prepareStatement(sql)) {
			statement.setInt(1, id);

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				int idDaEntrega = result.getInt(1);
				String numeroDoPedido = result.getString(2);
				int idDoCliente = result.getInt(3);
				String nomeDoRecebedor = result.getString(4);
				String cpfDoRecebedor = result.getString(5);
				Timestamp dataHoraDaEntrega = result.getTimestamp(6);

				entregaEntity = new EntregaEntity();
				entregaEntity.setId(idDaEntrega);
				entregaEntity.setNumeroDoPedido(numeroDoPedido);
				entregaEntity.setIdDoCliente(idDoCliente);
				entregaEntity.setNomeDoRecebedor(nomeDoRecebedor);
				entregaEntity.setCpfDoRecebedor(cpfDoRecebedor);
				entregaEntity.setDataHoraDaEntrega(dataHoraDaEntrega);
			}
			statement.close();

		} catch (SQLException e) {
			throw e;
		}

		return entregaEntity;
	}

	public EntregaEntity createEntrega(EntregaEntity entity) throws SQLException {

		String sql = "INSERT INTO Entregas (numeroDoPedido, idDoCliente, nomeDoRecebedor, cpfDoRecebedor, dataHoraDaEntrega) VALUES (?, ?, ?, ?, ?)";

		try (PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, entity.getNumeroDoPedido());
			statement.setInt(2, entity.getIdDoCliente());
			statement.setString(3, entity.getNomeDoRecebedor());
			statement.setString(4, entity.getCpfDoRecebedor());
			statement.setTimestamp(5, entity.getDataHoraDaEntrega());

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				ResultSet rs = statement.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					entity.setId(id);
				}
				System.out.println("A new user was inserted successfully!");
			}
			statement.close();

		} catch (SQLException e) {
			throw e;
		}
		return entity;
	}

	public EntregaEntity updateEntrega(EntregaEntity entityToUpdate) throws SQLException {
		String sql = "UPDATE Entregas SET numeroDoPedido=?, idDoCliente=?, nomeDoRecebedor=?, cpfDoRecebedor=?, dataHoraDaEntrega=? WHERE id=?";

		try (PreparedStatement statement = conn.prepareStatement(sql)) {
			statement.setString(1, entityToUpdate.getNumeroDoPedido());
			statement.setInt(2, entityToUpdate.getIdDoCliente());
			statement.setString(3, entityToUpdate.getNomeDoRecebedor());
			statement.setString(4, entityToUpdate.getCpfDoRecebedor());
			statement.setTimestamp(5, entityToUpdate.getDataHoraDaEntrega());
			statement.setInt(6, entityToUpdate.getId());

			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("An existing user was updated successfully!");
			}
			statement.close();

		} catch (SQLException e) {
			throw e;
		}
		return entityToUpdate;
	}

	public Set<EntregaEntity> getEntregas() throws SQLException {
		Set<EntregaEntity> entregas = new HashSet<>();
		String sql = "SELECT * FROM Entregas";

		try (Statement statement = conn.createStatement()) {

			ResultSet result = statement.executeQuery(sql);

			while (result.next()) {
				int idDoPedido = result.getInt(1);
				String numeroDoPedido = result.getString(2);
				int idDoCliente = result.getInt(3);
				String nomeDoRecebedor = result.getString(4);
				String cpfDoRecebedor = result.getString(5);
				Timestamp dataHoraDaEntrega = result.getTimestamp(6);

				EntregaEntity entregaEntity = new EntregaEntity();
				entregaEntity = new EntregaEntity();
				entregaEntity.setId(idDoPedido);
				entregaEntity.setNumeroDoPedido(numeroDoPedido);
				entregaEntity.setIdDoCliente(idDoCliente);
				entregaEntity.setNomeDoRecebedor(nomeDoRecebedor);
				entregaEntity.setCpfDoRecebedor(cpfDoRecebedor);
				entregaEntity.setDataHoraDaEntrega(dataHoraDaEntrega);
				entregas.add(entregaEntity);
			}
			statement.close();

		} catch (SQLException e) {
			throw e;
		}

		return entregas;
	}


	public boolean deleteEntregaByNumeroDoPedido(String numeroDoPedido) throws SQLException {
		boolean deleted = false;
		String sql = "DELETE FROM Entregas WHERE NumeroDoPedido=?";

		try (PreparedStatement statement = conn.prepareStatement(sql)) {
			statement.setString(1, numeroDoPedido);

			int rowsDeleted = statement.executeUpdate();
			if (rowsDeleted > 0) {
				System.out.println("A user was deleted successfully!");
				deleted = true;
			}
			statement.close();

		} catch (SQLException e) {
			throw e;
		}

		return deleted;
	}
	
	public boolean deleteEntrega(int id) throws SQLException {
		boolean deleted = false;
		String sql = "DELETE FROM Entregas WHERE id=?";

		try (PreparedStatement statement = conn.prepareStatement(sql)) {
			statement.setInt(1, id);

			int rowsDeleted = statement.executeUpdate();
			if (rowsDeleted > 0) {
				System.out.println("A user was deleted successfully!");
				deleted = true;
			}
			statement.close();

		} catch (SQLException e) {
			throw e;
		}

		return deleted;
	}

}
