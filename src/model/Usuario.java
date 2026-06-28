package model;

public class Usuario {
	
	public Usuario() {
		
	}
	
	public Usuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	private Integer idUsuario;
	private String nome;
	private Boolean ativo;
	private String senha;
	private Integer cpf;
	private Integer tipo;
	
	public Usuario(Integer idUsuario, String nome, Boolean ativo) {
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.ativo = ativo;
	}
	
	public Usuario(String nome) {
		this.nome = nome;
	}
	
	public Usuario(String nome, Boolean ativo) {
		this.nome = nome;
		this.ativo = ativo;
	}
	

	public Usuario(Integer idUsuario, String nome) {
		this.idUsuario = idUsuario;
		this.nome = nome;
	}
	
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public Integer getUsuario(Integer Usuario) {
		return idUsuario;
	}

	
}
