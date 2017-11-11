package br.inatel.t141.dm107.finalhomework.data.user;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserEntity {

	public UserEntity() {
	}

	private int id;
	private String login;
	private String password;

	public UserEntity(int id, String login, String password) {
		this.id = id;
		this.login = login;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
