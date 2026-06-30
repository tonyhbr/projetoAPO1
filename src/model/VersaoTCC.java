package model;

import java.time.LocalDate;

public class VersaoTCC {

    private Integer id;
    private String descricao;
    private LocalDate dataEnvio;
    private TCC tcc;

    public VersaoTCC(Integer id, String descricao, LocalDate dataEnvio, TCC tcc) {
        this.id = id;
        this.descricao = descricao;
        this.dataEnvio = dataEnvio;
        this.tcc = tcc;
    }

    public VersaoTCC() {
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

    public TCC getTcc() {
        return tcc;
    }

    public void setTcc(TCC tcc) {
        this.tcc = tcc;
    }

}