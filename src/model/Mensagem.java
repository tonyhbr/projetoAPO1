package model;

import java.time.LocalDateTime;

public class Mensagem {

	private int id;
	private String texto;
	private LocalDateTime dataHora;
	private String remetente;
	private Conversa conversa;

	public Mensagem() {
	}

	public Mensagem(int id) {
		this.id = id;
	}

	public Mensagem(int id, String texto, LocalDateTime dataHora, String remetente, Conversa conversa) {
		this(id);
		this.texto = texto;
		this.dataHora = dataHora;
		this.remetente = remetente;
		this.conversa = conversa;
	}

	public void enviarMensagem() { }
	public void editarMensagem() { }
	public void removerMensagem() { }

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public String getTexto() { return texto; }
	public void setTexto(String texto) { this.texto = texto; }

	public LocalDateTime getDataHora() { return dataHora; }
	public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }

	public String getRemetente() { return remetente; }
	public void setRemetente(String remetente) { this.remetente = remetente; }

	public Conversa getConversa() { return conversa; }
	public void setConversa(Conversa conversa) { this.conversa = conversa; }

	@Override
	public String toString() {
		return id + " - " + remetente + ": " + texto;
	}
}