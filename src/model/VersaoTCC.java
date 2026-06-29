package model;

import java.time.LocalDate;

public class VersaoTCC extends TCC {
	
	private Integer id;
	private String titulo;
	private String status;
	private LocalDate dataInicio;
	private LocalDate dataEntrega;
	private Aluno aluno;
	private Orientador orientador;
	private Coordenador coordenador;
	private List<Termo> termos;
	
	public VersaoTCC(Integer id, String descricao, LocalDate dataEnvio) {
		super(id, descricao, dataEnvio);
		// TODO Auto-generated constructor stub
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
	
}
