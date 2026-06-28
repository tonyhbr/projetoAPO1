package model;

public class Professor extends Eleitor{

	public Professor(Integer idEleitor, String nome) {
		super(idEleitor, nome);
		this.idEleitor = idEleitor;
		this.nome = nome;
	}
	protected Integer idProfessor;
	protected Integer numProntuario;
	protected String curso;
	
	
}
