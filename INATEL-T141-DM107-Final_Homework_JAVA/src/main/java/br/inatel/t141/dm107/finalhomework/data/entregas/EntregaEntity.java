package br.inatel.t141.dm107.finalhomework.data.entregas;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EntregaEntity {

	private int id;
	private String numeroDoPedido;
	private int idDoCliente;
	private String nomeDoRecebedor;
	private String cpfDoRecebedor;
	private Timestamp dataHoraDaEntrega;

	public EntregaEntity() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumeroDoPedido() {
		return numeroDoPedido;
	}

	public void setNumeroDoPedido(String numeroDoPedido) {
		this.numeroDoPedido = numeroDoPedido;
	}

	public int getIdDoCliente() {
		return idDoCliente;
	}

	public void setIdDoCliente(int idDoCliente) {
		this.idDoCliente = idDoCliente;
	}

	public String getNomeDoRecebedor() {
		return nomeDoRecebedor;
	}

	public void setNomeDoRecebedor(String nomeDoRecebedor) {
		this.nomeDoRecebedor = nomeDoRecebedor;
	}

	public String getCpfDoRecebedor() {
		return cpfDoRecebedor;
	}

	public void setCpfDoRecebedor(String cpfDoRecebedor) {
		this.cpfDoRecebedor = cpfDoRecebedor;
	}

	public Timestamp getDataHoraDaEntrega() {
		return dataHoraDaEntrega;
	}

	public void setDataHoraDaEntrega(Timestamp dataHoraDaEntrega) {
		this.dataHoraDaEntrega = dataHoraDaEntrega;
	}

}
