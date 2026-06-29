package model;

import java.time.LocalDate;

public class Arquivo {

	private int id;
	private String nome;
	private String tipo;
	private float tamanho;
	private LocalDate dataEnvio;
	private String status;
	private Conversa conversa;

	public Arquivo() {
	}

	public Arquivo(int id) {
		this.id = id;
	}

	public Arquivo(int id, String nome, String tipo, float tamanho, LocalDate dataEnvio, String status, Conversa conversa) {
		this(id);
		this.nome = nome;
		this.tipo = tipo;
		this.tamanho = tamanho;
		this.dataEnvio = dataEnvio;
		this.status = status;
		this.conversa = conversa;
	}

	public void anexarArquivo() { }
	public void alterarNome() { }
	public void removerArquivo() { }
	public void baixarArquivo() { }
	public boolean validarFormato() { return true; }

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }

	public String getTipo() { return tipo; }
	public void setTipo(String tipo) { this.tipo = tipo; }

	public float getTamanho() { return tamanho; }
	public void setTamanho(float tamanho) { this.tamanho = tamanho; }

	public LocalDate getDataEnvio() { return dataEnvio; }
	public void setDataEnvio(LocalDate dataEnvio) { this.dataEnvio = dataEnvio; }

	public String getStatus() { return status; }
	public void setStatus(String status) { this.status = status; }

	public Conversa getConversa() { return conversa; }
	public void setConversa(Conversa conversa) { this.conversa = conversa; }

	@Override
	public String toString() {
		return id + " - " + nome + "." + tipo + " (" + status + ")";
	}
}