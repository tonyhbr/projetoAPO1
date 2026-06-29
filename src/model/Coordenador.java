package model;

import java.util.ArrayList;
import java.util.List;

public class Coordenador {

	private Integer id;
	private String nome;
	private String login;
	private String senha;
	private List<TCC> tccs;

	public Coordenador() {
		this.tccs = new ArrayList<TCC>();
	}

	public Coordenador(Integer id) {
		this();
		this.id = id;
	}

	public Coordenador(Integer id, String nome) {
		this(id);
		this.nome = nome;
	}

	public Coordenador(Integer id, String nome, String login, String senha) {
		this();
		this.id = id;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
	}

	public void atualizarDados() {
	}

	public void atualizarDados(String nome, String login) {
		this.nome = nome;
		this.login = login;
	}

	public void alterarSenha() {
	}

	public void alterarSenha(String senha) {
		this.senha = senha;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<TCC> getTccs() {
		return tccs;
	}

	public void setTccs(List<TCC> tccs) {
		this.tccs = tccs;
	}

	public void adicionarTcc(TCC tcc) {
		this.tccs.add(tcc);
	}

	@Override
	public String toString() {
		return id + " - " + nome;
	}
}
