package model;

import java.time.LocalDate;
import java.util.Date;

public class Urna {

	private Integer codUrna;
	private String modeloUrna;
	private LocalDate dataAbertura;
	private LocalDate dataFechamento;
	private String statusUrna;
	private Usuario usuario;
	
	public void abrirUrna(Integer codUrnca, String modeloUrna, LocalDate dataAbertura) {
		this.codUrna = codUrna;
		this.modeloUrna = modeloUrna;
		this.dataAbertura = LocalDate.now();
	}
	
	public void fecharUrna(Integer dataFechamento) {
		this.dataFechamento = LocalDate.now();
	}
	public Integer getCodUrna() {
		return codUrna;
	}
	public String getModeloUrna() {
		return modeloUrna;
	}
	/*public Date getDataFechamento() {
		return dataAbertura;
	}*/
}
