package br.inatel.t141.dm107.finalhomework;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

import br.inatel.t141.dm107.finalhomework.data.ConnectionFactory;
import br.inatel.t141.dm107.finalhomework.data.user.UserDAO;
import br.inatel.t141.dm107.finalhomework.data.user.UserEntity;

public class UserTest {

	public static void main(String[] args) {

		// initUser();

		// TestCreateUser();
		// TestUpdateUser();
		// TestGetUserById();
		// TestGetUser();
		TestGetUserByLogin();
		// TestDeleteUser();

		// TestGetUsers();
	}

	private static void initUser() {

		ConnectionFactory connectionFactory = new ConnectionFactory();
		try (Connection conn = connectionFactory.getConnection()) {

			UserDAO userDAO = new UserDAO(conn);
			for (int i = 0; i < 10; i++) {
				UserEntity userEntity = new UserEntity();
				String login = "user" + i;
				String password = "password" + i;

				userEntity = new UserEntity();
				userEntity.setLogin(login);
				userEntity.setPassword(password);

				UserEntity user = userDAO.createUser(userEntity);

				ShowUser(user);
			}

			// conn.commit();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void TestCreateUser() {

		ConnectionFactory connectionFactory = new ConnectionFactory();
		try (Connection conn = connectionFactory.getConnection()) {

			UserDAO userDAO = new UserDAO(conn);

			UserEntity userEntity = new UserEntity();
			String login = "user" + 1;
			String password = "password" + 1;

			userEntity = new UserEntity();
			userEntity.setLogin(login);
			userEntity.setPassword(password);

			UserEntity user = userDAO.createUser(userEntity);

			ShowUser(user);

			// conn.commit();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void TestUpdateUser() {

		ConnectionFactory connectionFactory = new ConnectionFactory();
		try (Connection conn = connectionFactory.getConnection()) {

			UserDAO userDAO = new UserDAO(conn);

			UserEntity userEntity = new UserEntity();
			String login = "user" + 2;
			String password = "password" + 2;

			userEntity = new UserEntity();
			userEntity.setId(1);
			userEntity.setLogin(login);
			userEntity.setPassword(password);

			UserEntity user = userDAO.updateUser(userEntity);

			ShowUser(user);

			// conn.commit();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void TestGetUserByLogin() {

		ConnectionFactory connectionFactory = new ConnectionFactory();
		try (Connection conn = connectionFactory.getConnection()) {

			UserDAO userDAO = new UserDAO(conn);

			UserEntity user = userDAO.getUserByLogin("user1");

			ShowUser(user);

			// conn.commit();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void TestGetUserById() {

		ConnectionFactory connectionFactory = new ConnectionFactory();
		try (Connection conn = connectionFactory.getConnection()) {

			UserDAO userDAO = new UserDAO(conn);

			UserEntity user = userDAO.getUserById(1);

			ShowUser(user);

			// conn.commit();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void TestGetUsers() {

		ConnectionFactory connectionFactory = new ConnectionFactory();
		try (Connection conn = connectionFactory.getConnection()) {

			UserDAO userDAO = new UserDAO(conn);

			Set<UserEntity> users = userDAO.getUsers();
			for (UserEntity user : users) {
				ShowUser(user);
			}

			// conn.commit();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void TestDeleteUser() {

		ConnectionFactory connectionFactory = new ConnectionFactory();
		try (Connection conn = connectionFactory.getConnection()) {

			UserDAO userDAO = new UserDAO(conn);

			boolean deleted = userDAO.deleteUser(1);

			System.out.println(deleted);

			// conn.commit();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void ShowUser(UserEntity userEntity) {
		System.out.println("Id: " + userEntity.getId());
		System.out.println("login: " + userEntity.getLogin());
		System.out.println("password: " + userEntity.getPassword());
	}

}
