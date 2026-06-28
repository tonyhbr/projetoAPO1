package model;

import java.time.LocalDate;

public class Termo {

	public static final String TEXTO_PADRAO = "Declaro estar ciente das regras, prazos e responsabilidades relacionados ao desenvolvimento e entrega do Trabalho de Conclusao de Curso, comprometendo-me a acompanhar as orientacoes e cumprir as etapas definidas para o TCC.";

	private Integer id;
	private String texto;
	private LocalDate dataAceite;
	private Boolean aceito;
	private TCC tcc;

	public Termo() {
		this.texto = TEXTO_PADRAO;
		this.aceito = false;
	}

	public Termo(Integer id) {
		this();
		this.id = id;
	}

	public Termo(Integer id, String texto) {
		this(id);
		this.texto = texto;
	}

	public Termo(Integer id, String texto, LocalDate dataAceite, Boolean aceito, TCC tcc) {
		this(id, texto);
		this.dataAceite = dataAceite;
		this.aceito = aceito;
		this.tcc = tcc;
	}

	public void gerarTermo() {
		if (texto == null || texto.isEmpty()) {
			texto = TEXTO_PADRAO;
		}
	}

	public void aceitarTermo() {
		this.aceito = true;
		this.dataAceite = LocalDate.now();
	}

	public void registrarAceite() {
		aceitarTermo();
	}

	public Boolean validarAceite() {
		return Boolean.TRUE.equals(aceito);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public LocalDate getDataAceite() {
		return dataAceite;
	}

	public void setDataAceite(LocalDate dataAceite) {
		this.dataAceite = dataAceite;
	}

	public Boolean getAceito() {
		return aceito;
	}

	public void setAceito(Boolean aceito) {
		this.aceito = aceito;
	}

	public TCC getTcc() {
		return tcc;
	}

	public void setTcc(TCC tcc) {
		this.tcc = tcc;
	}

	@Override
	public String toString() {
		return id + " - " + (Boolean.TRUE.equals(aceito) ? "Aceito" : "Pendente") + " | " + texto;
	}
}
