package br.inatel.t141.dm107.finalhomework;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.TimeZone;

import br.inatel.t141.dm107.finalhomework.data.ConnectionFactory;
import br.inatel.t141.dm107.finalhomework.data.entregas.EntregaDAO;
import br.inatel.t141.dm107.finalhomework.data.entregas.EntregaEntity;

public class EntregasTest {

	public static void main(String[] args) {

		// initEntregas();

		// TestCreateEntrega();
		// TestUpdateEntrega();
		// TestGetEntregaById();
		TestGetEntregaByNumeroDoPedido();
		// TestGetEntregas();
		// TestDeleteEntrega();

		// TestGetEntregas();
	}

	private static void initEntregas() {

		ConnectionFactory connectionFactory = new ConnectionFactory();
		try (Connection conn = connectionFactory.getConnection()) {

			EntregaDAO entregaDAO = new EntregaDAO(conn);
			for (int i = 0; i < 10; i++) {
				EntregaEntity entregaEntity = new EntregaEntity();
				String numeroDoPedido = "P" + i;
				int idDoCliente = 1;
				String nomeDoRecebedor = "Recebedor" + i;
				String cpfDoRecebedor = "123.456.789-" + i;
				Timestamp dataHoraDaEntrega = Timestamp.valueOf(LocalDateTime.now());

				entregaEntity = new EntregaEntity();
				entregaEntity.setNumeroDoPedido(numeroDoPedido);
				entregaEntity.setIdDoCliente(idDoCliente);
				entregaEntity.setNomeDoRecebedor(nomeDoRecebedor);
				entregaEntity.setCpfDoRecebedor(cpfDoRecebedor);
				entregaEntity.setDataHoraDaEntrega(dataHoraDaEntrega);

				EntregaEntity entrega = entregaDAO.createEntrega(entregaEntity);

				ShowEntrega(entrega);
			}

			// conn.commit();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void TestCreateEntrega() {

		ConnectionFactory connectionFactory = new ConnectionFactory();
		try (Connection conn = connectionFactory.getConnection()) {

			EntregaDAO entregaDAO = new EntregaDAO(conn);

			EntregaEntity entregaEntity = new EntregaEntity();
			String numeroDoPedido = "123456";
			int idDoCliente = 1;
			String nomeDoRecebedor = "Recebedor1";
			String cpfDoRecebedor = "123.456.789-0";
			Timestamp dataHoraDaEntrega = Timestamp.valueOf(LocalDateTime.now());

			entregaEntity = new EntregaEntity();
			entregaEntity.setNumeroDoPedido(numeroDoPedido);
			entregaEntity.setIdDoCliente(idDoCliente);
			entregaEntity.setNomeDoRecebedor(nomeDoRecebedor);
			entregaEntity.setCpfDoRecebedor(cpfDoRecebedor);
			entregaEntity.setDataHoraDaEntrega(dataHoraDaEntrega);

			EntregaEntity entrega = entregaDAO.createEntrega(entregaEntity);

			ShowEntrega(entrega);

			// conn.commit();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void TestUpdateEntrega() {

		ConnectionFactory connectionFactory = new ConnectionFactory();
		try (Connection conn = connectionFactory.getConnection()) {

			EntregaDAO entregaDAO = new EntregaDAO(conn);

			EntregaEntity entregaEntity = new EntregaEntity();
			String numeroDoPedido = "123456";
			int idDoCliente = 1;
			String nomeDoRecebedor = "Recebedor1";
			String cpfDoRecebedor = "123.456.789-0";
			Timestamp dataHoraDaEntrega = Timestamp.valueOf(LocalDateTime.now());

			entregaEntity = new EntregaEntity();
			entregaEntity.setId(1);
			entregaEntity.setNumeroDoPedido(numeroDoPedido);
			entregaEntity.setIdDoCliente(idDoCliente);
			entregaEntity.setNomeDoRecebedor(nomeDoRecebedor);
			entregaEntity.setCpfDoRecebedor(cpfDoRecebedor);
			entregaEntity.setDataHoraDaEntrega(dataHoraDaEntrega);

			EntregaEntity entrega = entregaDAO.updateEntrega(entregaEntity);

			ShowEntrega(entrega);

			// conn.commit();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void TestGetEntregaByNumeroDoPedido() {

		ConnectionFactory connectionFactory = new ConnectionFactory();
		try (Connection conn = connectionFactory.getConnection()) {

			EntregaDAO entregaDAO = new EntregaDAO(conn);

			EntregaEntity entrega = entregaDAO.getEntregaByNumeroDoPedido("P0");

			ShowEntrega(entrega);

			// conn.commit();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void TestGetEntregaById() {

		ConnectionFactory connectionFactory = new ConnectionFactory();
		try (Connection conn = connectionFactory.getConnection()) {

			EntregaDAO entregaDAO = new EntregaDAO(conn);

			EntregaEntity entrega = entregaDAO.getEntregaById(1);

			ShowEntrega(entrega);

			// conn.commit();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void TestGetEntregas() {

		ConnectionFactory connectionFactory = new ConnectionFactory();
		try (Connection conn = connectionFactory.getConnection()) {

			EntregaDAO entregaDAO = new EntregaDAO(conn);

			Set<EntregaEntity> entregas = entregaDAO.getEntregas();
			for (EntregaEntity entrega : entregas) {
				ShowEntrega(entrega);
			}

			// conn.commit();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void TestDeleteEntrega() {

		ConnectionFactory connectionFactory = new ConnectionFactory();
		try (Connection conn = connectionFactory.getConnection()) {

			EntregaDAO entregaDAO = new EntregaDAO(conn);

			boolean deleted = entregaDAO.deleteEntrega(1);

			System.out.println(deleted);

			// conn.commit();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void ShowEntrega(EntregaEntity entregaEntity) {
		System.out.println("Id: " + entregaEntity.getId());
		System.out.println("NumeroDoPedido: " + entregaEntity.getNumeroDoPedido());
		System.out.println("IdDoCliente: " + entregaEntity.getIdDoCliente());
		System.out.println("NomeDoRecebedor: " + entregaEntity.getNomeDoRecebedor());
		System.out.println("CpfDoRecebedor: " + entregaEntity.getCpfDoRecebedor());
		System.out.println("DataHoraDaEntrega: " + entregaEntity.getDataHoraDaEntrega() + " - "
				+ FormatDataHoraDaEntrega(entregaEntity.getDataHoraDaEntrega()));

	}

	private static String FormatDataHoraDaEntrega(Timestamp Timestamp) {
		String dataHoraDaEntrega = new String();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		dataHoraDaEntrega = sdf.format(Timestamp);
		return dataHoraDaEntrega;
	}

}
