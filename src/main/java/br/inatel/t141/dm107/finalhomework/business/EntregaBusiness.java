package br.inatel.t141.dm107.finalhomework.business;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import br.inatel.t141.dm107.finalhomework.data.entregas.EntregaEntity;

public class EntregaBusiness {

	public static EntregaEntity createEntrega(String numeroDoPedido, Integer idDoCliente) {

		EntregaEntity entrega = new EntregaEntity();
		entrega.setNumeroDoPedido(numeroDoPedido);
		entrega.setIdDoCliente(idDoCliente);

		return entrega;
	}

	public static Timestamp convertDataHoraDaEntrega(String diaDaEntrega, String mesDaEntrega, String anoDaEntrega,
			String horaDaEntrega, String minutoDaEntrega) {

		String now = anoDaEntrega + "-" + mesDaEntrega + "-" + diaDaEntrega + " " + horaDaEntrega + ":"
				+ minutoDaEntrega;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime formatDateTime = LocalDateTime.parse(now, formatter);
		Timestamp dataHoraDaEntrega = Timestamp.valueOf(formatDateTime);

		return dataHoraDaEntrega;
	}

}
