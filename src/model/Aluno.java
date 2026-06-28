package model;

public class Aluno extends Eleitor{
	
	protected Integer idAluno;
	protected Integer numProntuario;
	protected String curso;

	public Aluno(Integer idEleitor, String nome, Integer idAluno, Integer numProntuario, String curso) {
		super(idEleitor, nome);
		// TODO Auto-generated constructor stub
		this.idAluno = idAluno;
		this.numProntuario = numProntuario;
		this.curso = curso;
	}

}
