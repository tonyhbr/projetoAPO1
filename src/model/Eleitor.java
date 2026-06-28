package model;

public class Eleitor {

	protected Integer idEleitor;
	protected String nome;
	
	public Eleitor(Integer idEleitor, String nome) {
		this.idEleitor = idEleitor;
		this.nome = nome;
	}

	public Eleitor(String nome) {
		this.nome = nome;
	}

	public Integer getIdEleitor() {
		return idEleitor;
	}

	public void setIdEleitor(Integer idEleitor) {
		this.idEleitor = idEleitor;
	}
	
}
