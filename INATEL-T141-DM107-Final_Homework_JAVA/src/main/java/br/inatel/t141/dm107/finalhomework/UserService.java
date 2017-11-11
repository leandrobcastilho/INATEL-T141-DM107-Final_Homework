package br.inatel.t141.dm107.finalhomework;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import br.inatel.t141.dm107.finalhomework.data.ConnectionFactory;
import br.inatel.t141.dm107.finalhomework.data.user.UserDAO;
import br.inatel.t141.dm107.finalhomework.data.user.UserEntity;

@Path("/user")
public class UserService {

	@Context
	private UriInfo uriInfo;

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response users() {

		ConnectionFactory connectionFactory = new ConnectionFactory();
		try (Connection conn = connectionFactory.getConnection()) {

			Set<UserEntity> users = new UserDAO(conn).getUsers();

			if (users == null || users.isEmpty()) {
				conn.close();
				return Response.status(Status.NOT_FOUND).build();
			}

			GenericEntity<List<UserEntity>> entity = new GenericEntity<List<UserEntity>>(new ArrayList<>(users)) {
			};

			conn.close();
			return Response.ok(entity).build();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Response.status(Status.INTERNAL_SERVER_ERROR).build();
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("{id}")
	public Response getUser(@PathParam("id") int id) {

		ConnectionFactory connectionFactory = new ConnectionFactory();
		try (Connection conn = connectionFactory.getConnection()) {

			UserEntity User = new UserDAO(conn).getUserById(id);
			if (User == null) {
				conn.close();
				return Response.status(Status.NOT_FOUND).build();
			}

			GenericEntity<UserEntity> entity = new GenericEntity<UserEntity>(User) {
			};

			conn.close();
			return Response.ok(entity).build();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Response.status(Status.INTERNAL_SERVER_ERROR).build();
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response createUser(UserEntity user) {

		ConnectionFactory connectionFactory = new ConnectionFactory();
		try (Connection conn = connectionFactory.getConnection()) {

			if (user == null) {
				conn.close();
				return Response.status(Status.BAD_REQUEST).build();
			}

			UserEntity userCreate = new UserDAO(conn).createUser(user);

			GenericEntity<UserEntity> entity = new GenericEntity<UserEntity>(userCreate) {
			};

			try {
				URI location = new URI(String.format("%s/%s", uriInfo.getAbsolutePath(), userCreate.getId()));

				// return Response
				// .status(Status.CREATED)
				// .location(location)
				// .entity(entity)
				// .build();
				conn.close();
				return Response.created(location).entity(entity).build();

			} catch (URISyntaxException e) {
				conn.close();
				return Response.status(Status.INTERNAL_SERVER_ERROR).build();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Response.status(Status.INTERNAL_SERVER_ERROR).build();
	}

	@DELETE
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("{id}")
	public Response deleteUser(@PathParam("id") int id) {

		ConnectionFactory connectionFactory = new ConnectionFactory();
		try (Connection conn = connectionFactory.getConnection()) {

			if (new UserDAO(conn).deleteUser(id)) {
				conn.close();
				return Response.ok().build();
			} else {
				conn.close();
				return Response.status(Status.NOT_FOUND).build();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Response.status(Status.INTERNAL_SERVER_ERROR).build();

	}

	@PUT
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("{id}")
	public Response updateUser(@PathParam("id") int id, UserEntity User) {

		ConnectionFactory connectionFactory = new ConnectionFactory();
		try (Connection conn = connectionFactory.getConnection()) {

			UserDAO userDAO = new UserDAO(conn);
			UserEntity UserExiste = userDAO.getUserById(id);
			if (UserExiste == null) {
				conn.close();
				return Response.status(Status.NOT_FOUND).build();
			}

			if (UserExiste.getId() != User.getId()) {
				conn.close();
				return Response.status(Status.BAD_REQUEST).build();
			}

			UserExiste.setLogin(User.getLogin());
			UserExiste.setPassword(User.getPassword());

			UserEntity userUpdate = userDAO.updateUser(UserExiste);

			GenericEntity<UserEntity> entity = new GenericEntity<UserEntity>(userUpdate) {
			};

			try {
				URI location = new URI(String.format("%s", uriInfo.getAbsolutePath()));
				conn.close();
				return Response.ok(entity).location(location).build();
			} catch (URISyntaxException e) {
				conn.close();
				return Response.status(Status.INTERNAL_SERVER_ERROR).build();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Response.status(Status.INTERNAL_SERVER_ERROR).build();
	}
}
