package model;

import java.time.LocalDate;

public class Avaliacao {

	public Integer id;
	public String comentario;
	public Float nota;
	public LocalDate data;
	
	public Avaliacao(Integer id, String comentario, Float nota, LocalDate data) {
		super();
		this.id = id;
		this.comentario = comentario;
		this.nota = nota;
		this.data = data;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public Float getNota() {
		return nota;
	}
	public void setNota(Float nota) {
		this.nota = nota;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	
	
	
}
