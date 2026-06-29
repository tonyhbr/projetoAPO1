package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Conversa {

	private int id;
	private LocalDate dataInicio;
	private Aluno aluno;
	private Orientador orientador;
	private List<Mensagem> mensagens;
	private List<Arquivo> arquivos;

	public Conversa() {
		this.mensagens = new ArrayList<Mensagem>();
		this.arquivos = new ArrayList<Arquivo>();
	}

	public Conversa(int id) {
		this();
		this.id = id;
	}

	public Conversa(int id, LocalDate dataInicio, Aluno aluno, Orientador orientador) {
		this(id);
		this.dataInicio = dataInicio;
		this.aluno = aluno;
		this.orientador = orientador;
	}

	public void iniciarConversa() { }
	public void encerrarConversa() { }
	public void consultarMensagens() { }

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public LocalDate getDataInicio() { return dataInicio; }
	public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }

	public Aluno getAluno() { return aluno; }
	public void setAluno(Aluno aluno) { this.aluno = aluno; }

	public Orientador getOrientador() { return orientador; }
	public void setOrientador(Orientador orientador) { this.orientador = orientador; }

	public List<Mensagem> getMensagens() { return mensagens; }
	public void setMensagens(List<Mensagem> mensagens) { this.mensagens = mensagens; }

	public void adicionarMensagem(Mensagem mensagem) { this.mensagens.add(mensagem); }

	public List<Arquivo> getArquivos() { return arquivos; }
	public void setArquivos(List<Arquivo> arquivos) { this.arquivos = arquivos; }
	
	public void adicionarArquivo(Arquivo arquivo) { this.arquivos.add(arquivo); }

	@Override
	public String toString() {
		return id + " - Conversa de: " + (aluno != null ? aluno.getNome() : "") + " e " + (orientador != null ? orientador.getNome() : "");
	}
}