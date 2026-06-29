package model;

import java.time.LocalDateTime;

public class Tarefa {

	public Integer id;
	public String titulo;
	public String descricao;
	public String status;
	public LocalDateTime prazo;
	
	public Tarefa(Integer id, String titulo, String descricao, String status, LocalDateTime prazo) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.status = status;
		this.prazo = prazo;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDateTime getPrazo() {
		return prazo;
	}
	public void setPrazo(LocalDateTime prazo) {
		this.prazo = prazo;
	}
	
	
	
}
