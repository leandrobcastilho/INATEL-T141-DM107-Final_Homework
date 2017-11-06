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
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import br.inatel.t141.dm107.finalhomework.business.EntregaBusiness;
import br.inatel.t141.dm107.finalhomework.data.ConnectionFactory;
import br.inatel.t141.dm107.finalhomework.data.entregas.EntregaDAO;
import br.inatel.t141.dm107.finalhomework.data.entregas.EntregaEntity;

@Path("/entrega")
public class EntregaService {

	@Context
	private UriInfo uriInfo;

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response entregas() {

		ConnectionFactory connectionFactory = new ConnectionFactory();
		try (Connection conn = connectionFactory.getConnection()) {

			Set<EntregaEntity> entregas = new EntregaDAO(conn).getEntregas();

			if (entregas == null || entregas.isEmpty()) {
				conn.close();
				return Response.status(Status.NO_CONTENT).build();
			}

			GenericEntity<List<EntregaEntity>> entity = new GenericEntity<List<EntregaEntity>>(
					new ArrayList<>(entregas)) {
			};

			conn.close();
			// return Response.ok().entity(entity).build();
			return Response.ok(entity).build();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Response.status(Status.INTERNAL_SERVER_ERROR).build();
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("{numeroDoPedido}")
	public Response getEntrega(@PathParam("numeroDoPedido") String numeroDoPedido) {

		ConnectionFactory connectionFactory = new ConnectionFactory();
		try (Connection conn = connectionFactory.getConnection()) {

			EntregaEntity entrega = new EntregaDAO(conn).getEntregaByNumeroDoPedido(numeroDoPedido);
			if (entrega == null) {
				conn.close();
				return Response.status(Status.NOT_FOUND).build();
			}

			GenericEntity<EntregaEntity> entity = new GenericEntity<EntregaEntity>(entrega) {
			};

			conn.close();
			return Response.ok(entity).build();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Response.status(Status.INTERNAL_SERVER_ERROR).build();
	}

//	@GET
//	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	@Path("{id}")
//	public Response getEntrega(@PathParam("id") int id) {
//
//		ConnectionFactory connectionFactory = new ConnectionFactory();
//		try (Connection conn = connectionFactory.getConnection()) {
//
//			EntregaEntity Entrega = new EntregaDAO(conn).getEntregaById(id);
//			if (Entrega == null) {
//				conn.close();
//				return Response.status(Status.NOT_FOUND).build();
//			}
//
//			GenericEntity<EntregaEntity> entity = new GenericEntity<EntregaEntity>(Entrega) {
//			};
//
//			conn.close();
//			return Response.ok(entity).build();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return Response.status(Status.INTERNAL_SERVER_ERROR).build();
//	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	//@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/create")
	public Response createEntrega(@FormParam("numeroDoPedido") String numeroDoPedido,
			@FormParam("idDoCliente") Integer idDoCliente) {

		ConnectionFactory connectionFactory = new ConnectionFactory();
		try (Connection conn = connectionFactory.getConnection()) {

			System.out.println("numeroDoPedido "+numeroDoPedido);
			System.out.println("idDoCliente "+idDoCliente);
			if (numeroDoPedido == null || idDoCliente == null) {
				conn.close();
				return Response.status(Status.BAD_REQUEST).build();
			}
			EntregaEntity entrega = EntregaBusiness.createEntrega(numeroDoPedido, idDoCliente);
			EntregaEntity entregaCreate = new EntregaDAO(conn).createEntrega(entrega);

			GenericEntity<EntregaEntity> entity = new GenericEntity<EntregaEntity>(entregaCreate) {
			};

			try {
				URI location = new URI(String.format("%s/%s", uriInfo.getAbsolutePath(), entregaCreate.getId()));

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

//	@POST
//	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	public Response createEntrega(EntregaEntity entrega) {
//
//		ConnectionFactory connectionFactory = new ConnectionFactory();
//		try (Connection conn = connectionFactory.getConnection()) {
//
//			if (entrega == null) {
//				conn.close();
//				return Response.status(Status.BAD_REQUEST).build();
//			}
//
//			EntregaEntity entregaCreate = new EntregaDAO(conn).createEntrega(entrega);
//
//			GenericEntity<EntregaEntity> entity = new GenericEntity<EntregaEntity>(entregaCreate) {
//			};
//
//			try {
//				URI location = new URI(String.format("%s/%s", uriInfo.getAbsolutePath(), entregaCreate.getId()));
//
//				conn.close();
//				return Response.created(location).entity(entity).build();
//
//			} catch (URISyntaxException e) {
//				conn.close();
//				return Response.status(Status.INTERNAL_SERVER_ERROR).build();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return Response.status(Status.INTERNAL_SERVER_ERROR).build();
//	}

	@DELETE
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("{numeroDoPedido}")
	public Response deleteEntrega(@PathParam("numeroDoPedido") String numeroDoPedido) {

		ConnectionFactory connectionFactory = new ConnectionFactory();
		try (Connection conn = connectionFactory.getConnection()) {

			if (new EntregaDAO(conn).deleteEntregaByNumeroDoPedido(numeroDoPedido)) {
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
	
//	@DELETE
//	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	@Path("{id}")
//	public Response deleteEntrega(@PathParam("id") int id) {
//
//		ConnectionFactory connectionFactory = new ConnectionFactory();
//		try (Connection conn = connectionFactory.getConnection()) {
//
//			if (new EntregaDAO(conn).deleteEntrega(id)) {
//				conn.close();
//				return Response.ok().build();
//			} else {
//				conn.close();
//				return Response.status(Status.NOT_FOUND).build();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return Response.status(Status.INTERNAL_SERVER_ERROR).build();
//	}

	@PUT
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	//@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/update/{numeroDoPedido}")
	public Response updateEntrega(@PathParam("numeroDoPedido") String numeroDoPedido,
			@FormParam("nomeDoRecebedor") String nomeDoRecebedor, @FormParam("cpfDoRecebedor") String cpfDoRecebedor,
			@FormParam("diaDaEntrega") String diaDaEntrega, @FormParam("mesDaEntrega") String mesDaEntrega,
			@FormParam("anoDaEntrega") String anoDaEntrega, @FormParam("horaDaEntrega") String horaDaEntrega,
			@FormParam("minutoDaEntrega") String minutoDaEntrega) {

		ConnectionFactory connectionFactory = new ConnectionFactory();
		try (Connection conn = connectionFactory.getConnection()) {

			System.out.println("nomeDoRecebedor "+nomeDoRecebedor);
			System.out.println("cpfDoRecebedor "+cpfDoRecebedor);
			System.out.println("diaDaEntrega "+diaDaEntrega);
			System.out.println("mesDaEntrega "+mesDaEntrega);
			System.out.println("anoDaEntrega "+anoDaEntrega);
			System.out.println("horaDaEntrega "+horaDaEntrega);
			System.out.println("minutoDaEntrega "+minutoDaEntrega);
			if (nomeDoRecebedor == null || cpfDoRecebedor == null || diaDaEntrega == null || mesDaEntrega == null
					|| anoDaEntrega == null || horaDaEntrega == null || minutoDaEntrega == null) {
				conn.close();
				return Response.status(Status.BAD_REQUEST).build();
			}

			EntregaDAO entregaDAO = new EntregaDAO(conn);
			EntregaEntity entregaExiste = entregaDAO.getEntregaByNumeroDoPedido(numeroDoPedido);
			if (entregaExiste == null) {
				conn.close();
				return Response.status(Status.NOT_FOUND).build();
			}

			entregaExiste.setNomeDoRecebedor(nomeDoRecebedor);
			entregaExiste.setCpfDoRecebedor(cpfDoRecebedor);
			entregaExiste.setDataHoraDaEntrega(EntregaBusiness.convertDataHoraDaEntrega(diaDaEntrega, mesDaEntrega,
					anoDaEntrega, horaDaEntrega, minutoDaEntrega));

			EntregaEntity entregaUpdate = entregaDAO.updateEntrega(entregaExiste);

			GenericEntity<EntregaEntity> entity = new GenericEntity<EntregaEntity>(entregaUpdate) {
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

//	@PUT
//	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	@Path("{id}")
//	public Response updateEntrega(@PathParam("id") int id, EntregaEntity entrega) {
//
//		ConnectionFactory connectionFactory = new ConnectionFactory();
//		try (Connection conn = connectionFactory.getConnection()) {
//
//			EntregaDAO entregaDAO = new EntregaDAO(conn);
//			EntregaEntity entregaExiste = entregaDAO.getEntregaById(id);
//			if (entregaExiste == null) {
//				conn.close();
//				return Response.status(Status.NOT_FOUND).build();
//			}
//
//			if (entregaExiste.getId() != entrega.getId()) {
//				conn.close();
//				return Response.status(Status.BAD_REQUEST).build();
//			}
//
//			entregaExiste.setId(entrega.getId());
//			entregaExiste.setNumeroDoPedido(entrega.getNumeroDoPedido());
//			entregaExiste.setIdDoCliente(entrega.getIdDoCliente());
//			entregaExiste.setNomeDoRecebedor(entrega.getNomeDoRecebedor());
//			entregaExiste.setCpfDoRecebedor(entrega.getCpfDoRecebedor());
//			entregaExiste.setDataHoraDaEntrega(entrega.getDataHoraDaEntrega());
//
//			EntregaEntity entregaUpdate = entregaDAO.updateEntrega(entregaExiste);
//
//			GenericEntity<EntregaEntity> entity = new GenericEntity<EntregaEntity>(entregaUpdate) {
//			};
//
//			try {
//				URI location = new URI(String.format("%s", uriInfo.getAbsolutePath()));
//				conn.close();
//				return Response.ok(entity).location(location).build();
//
//			} catch (URISyntaxException e) {
//				conn.close();
//				return Response.status(Status.INTERNAL_SERVER_ERROR).build();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return Response.status(Status.INTERNAL_SERVER_ERROR).build();
//
//	}
}
