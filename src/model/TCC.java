package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TCC {

	private Integer id;
	private String titulo;
	private String status;
	private LocalDate dataInicio;
	private LocalDate dataEntrega;
	private Aluno aluno;
	private Orientador orientador;
	private Coordenador coordenador;
	private List<Termo> termos;

	public TCC() {
		this.termos = new ArrayList<Termo>();
	}

	public TCC(Integer id) {
		this();
		this.id = id;
	}

	public TCC(Integer id, String titulo) {
		this(id);
		this.titulo = titulo;
	}

	public TCC(Integer id, String titulo, String status, LocalDate dataInicio, LocalDate dataEntrega,
			Aluno aluno, Orientador orientador, Coordenador coordenador) {
		this(id, titulo);
		this.status = status;
		this.dataInicio = dataInicio;
		this.dataEntrega = dataEntrega;
		this.aluno = aluno;
		this.orientador = orientador;
		this.coordenador = coordenador;
	}

	public void atualizarTitulo() {
	}

	public void atualizarTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void atualizarStatus() {
	}

	public void atualizarStatus(String status) {
		this.status = status;
	}

	public void definirDataEntrega() {
	}

	public void definirDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public void consultarAndamento() {
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Orientador getOrientador() {
		return orientador;
	}

	public void setOrientador(Orientador orientador) {
		this.orientador = orientador;
	}

	public Coordenador getCoordenador() {
		return coordenador;
	}

	public void setCoordenador(Coordenador coordenador) {
		this.coordenador = coordenador;
	}

	public List<Termo> getTermos() {
		return termos;
	}

	public void setTermos(List<Termo> termos) {
		this.termos = termos;
	}

	public void adicionarTermo(Termo termo) {
		this.termos.add(termo);
	}

	@Override
	public String toString() {
		return id + " - " + titulo + " | " + status;
	}
}
