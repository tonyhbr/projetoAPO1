package model;

import java.time.LocalDate;

public class Avaliacao {

    private Integer id;
    private String comentario;
    private Float nota;
    private LocalDate data;
    private TCC tcc;

    public Avaliacao() {
        super();
    }

    public Avaliacao(Integer id, String comentario, Float nota,
                     LocalDate data, TCC tcc) {
        super();
        this.id = id;
        this.comentario = comentario;
        this.nota = nota;
        this.data = data;
        this.tcc = tcc;
    }

    public void registrarAvaliacao() {
    }

    public void alterarNota() {
    }

    public void consultarAvaliacao() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Float getNota() {
        return nota;
    }

    public void setNota(Float nota) {
        this.nota = nota;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public TCC getTcc() {
        return tcc;
    }

    public void setTcc(TCC tcc) {
        this.tcc = tcc;
    }

}