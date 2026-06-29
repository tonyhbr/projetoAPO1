package model;

public class Aluno {

	private Integer id;
	private String nome;
	private String login;
	private String senha;
	private String curso;
	private TCC tcc;

	public Aluno() {
	}

	public Aluno(Integer id) {
		this.id = id;
	}

	public Aluno(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Aluno(Integer id, String nome, String login, String senha, String curso) {
		this.id = id;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.curso = curso;
	}

	public void atualizarDados() {
	}

	public void atualizarDados(String nome, String login, String curso) {
		this.nome = nome;
		this.login = login;
		this.curso = curso;
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

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public TCC getTcc() {
		return tcc;
	}

	public void setTcc(TCC tcc) {
		this.tcc = tcc;
	}

	@Override
	public String toString() {
		return id + " - " + nome + " (" + curso + ")";
	}
}
