package model;

import java.time.LocalDate;

public class Feedback {
	
	public Integer id;
	public String texto;
	public String autor;
	public LocalDate data;
	
	public Feedback(Integer id, String texto, String autor, LocalDate data) {
		super();
		this.id = id;
		this.texto = texto;
		this.autor = autor;
		this.data = data;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}

	
	
}
