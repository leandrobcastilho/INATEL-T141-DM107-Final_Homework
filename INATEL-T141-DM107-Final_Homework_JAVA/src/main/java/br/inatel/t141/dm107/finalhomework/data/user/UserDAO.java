package br.inatel.t141.dm107.finalhomework.data.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class UserDAO {

	private Connection conn;

	public UserDAO(Connection conn) {
		this.conn = conn;
	}

	public UserEntity getUserByLogin(String login) throws SQLException {
		UserEntity userEntity = null;
		String sql = "SELECT * FROM User WHERE login=?";

		try (PreparedStatement statement = conn.prepareStatement(sql)) {
			statement.setString(1, login);

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				int idDoUser = result.getInt(1);
				String loginDoUser = result.getString(2);
				String password = result.getString(3);

				userEntity = new UserEntity();
				userEntity.setId(idDoUser);
				userEntity.setLogin(loginDoUser);
				userEntity.setPassword(password);
			}
			statement.close();

		} catch (SQLException e) {
			throw e;
		}

		return userEntity;
	}

	public UserEntity getUserById(int id) throws SQLException {
		UserEntity userEntity = null;
		String sql = "SELECT * FROM User WHERE id=?";

		try (PreparedStatement statement = conn.prepareStatement(sql)) {
			statement.setInt(1, id);

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				int idDoUser = result.getInt(1);
				String login = result.getString(2);
				String password = result.getString(3);

				userEntity = new UserEntity();
				userEntity.setId(idDoUser);
				userEntity.setLogin(login);
				userEntity.setPassword(password);
			}
			statement.close();

		} catch (SQLException e) {
			throw e;
		}

		return userEntity;
	}

	public UserEntity createUser(UserEntity entity) throws SQLException {

		String sql = "INSERT INTO User (login, password) VALUES (?, ?)";

		try (PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, entity.getLogin());
			statement.setString(2, entity.getPassword());

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

	public UserEntity updateUser(UserEntity entityToUpdate) throws SQLException {
		String sql = "UPDATE User SET login=?, password=? WHERE id=?";

		try (PreparedStatement statement = conn.prepareStatement(sql)) {
			statement.setString(1, entityToUpdate.getLogin());
			statement.setString(2, entityToUpdate.getPassword());
			statement.setInt(3, entityToUpdate.getId());

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

	public Set<UserEntity> getUsers() throws SQLException {
		Set<UserEntity> users = new HashSet<>();
		String sql = "SELECT * FROM User";

		try (Statement statement = conn.createStatement()) {

			ResultSet result = statement.executeQuery(sql);

			while (result.next()) {
				int idDoPedido = result.getInt(1);
				String login = result.getString(2);
				String password = result.getString(3);

				UserEntity userEntity = new UserEntity();
				userEntity = new UserEntity();
				userEntity.setId(idDoPedido);
				userEntity.setLogin(login);
				userEntity.setPassword(password);
				users.add(userEntity);
			}
			statement.close();

		} catch (SQLException e) {
			throw e;
		}

		return users;
	}

	public boolean deleteUser(int id) throws SQLException {
		boolean deleted = false;
		String sql = "DELETE FROM User WHERE id=?";

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
