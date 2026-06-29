package model;

import java.util.ArrayList;
import java.util.List;

public class Orientador {

	private Integer id;
	private String nome;
	private String login;
	private String senha;
	private String areaAtuacao;
	private List<TCC> tccs;

	public Orientador() {
		this.tccs = new ArrayList<TCC>();
	}

	public Orientador(Integer id) {
		this();
		this.id = id;
	}

	public Orientador(Integer id, String nome) {
		this(id);
		this.nome = nome;
	}

	public Orientador(Integer id, String nome, String login, String senha, String areaAtuacao) {
		this();
		this.id = id;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.areaAtuacao = areaAtuacao;
	}

	public void atualizarDados() {
	}

	public void atualizarDados(String nome, String login, String areaAtuacao) {
		this.nome = nome;
		this.login = login;
		this.areaAtuacao = areaAtuacao;
	}

	public void alterarSenha() {
	}

	public void alterarSenha(String senha) {
		this.senha = senha;
	}

	public void atualizarAreaAtuacao() {
	}

	public void atualizarAreaAtuacao(String areaAtuacao) {
		this.areaAtuacao = areaAtuacao;
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

	public String getAreaAtuacao() {
		return areaAtuacao;
	}

	public void setAreaAtuacao(String areaAtuacao) {
		this.areaAtuacao = areaAtuacao;
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
		return id + " - " + nome + " (" + areaAtuacao + ")";
	}
}
