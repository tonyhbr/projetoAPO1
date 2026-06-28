package model;

import java.time.LocalDate;

public class VersaoTCC extends TCC {
	protected Integer id;
	protected String descricao;
	protected LocalDate dataEnvio;
	
	public VersaoTCC(Integer id, String descricao, LocalDate dataEnvio) {
		this.id = id;
		this.descricao = descricao;
		this.dataEnvio = dataEnvio;
	}
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(LocalDate dataEnvio) {
		this.dataEnvio = dataEnvio;
	}
	
	
	
}
