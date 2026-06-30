package model;

import java.time.LocalDate;

public class Feedback {

    private Integer id;
    private String texto;
    private String autor;
    private LocalDate data;
    private TCC tcc;

    public Feedback() {
        super();
    }

    public Feedback(Integer id, String texto, String autor,
            LocalDate data, TCC tcc) {
        super();
        this.id = id;
        this.texto = texto;
        this.autor = autor;
        this.data = data;
        this.tcc = tcc;
    }

    public void registrarFeedback() {
    }

    public void alterarFeedback() {
    }

    public void consultarFeedback() {
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

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
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