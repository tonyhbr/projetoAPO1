package model;

public class Funcionario extends Eleitor{

	public Funcionario(Integer idEleitor, String nome) {
		super(idEleitor, nome);
		this.idEleitor = idEleitor;
		this.nome = nome;
	}

	private Integer idFuncionario;
	
}
