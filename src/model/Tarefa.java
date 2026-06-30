package model;

import java.time.LocalDateTime;

public class Tarefa {

    private Integer id;
    private String titulo;
    private String descricao;
    private String status;
    private LocalDateTime prazo;
    private TCC tcc;

    public Tarefa() {
        super();
    }

    public Tarefa(Integer id, String titulo, String descricao,
            String status, LocalDateTime prazo, TCC tcc) {
        super();
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
        this.prazo = prazo;
        this.tcc = tcc;
    }

    public Tarefa(Integer id, String titulo, String descricao, String status, LocalDateTime prazo) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.status = status;
		this.prazo = prazo;
	}

	public void registrarTarefa() {
    }

    public void alterarStatus() {
    }

    public void consultarTarefa() {
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getPrazo() {
        return prazo;
    }

    public void setPrazo(LocalDateTime prazo) {
        this.prazo = prazo;
    }

    public TCC getTcc() {
        return tcc;
    }

    public void setTcc(TCC tcc) {
        this.tcc = tcc;
    }

}