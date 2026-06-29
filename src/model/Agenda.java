package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Agenda {
	private Integer id;
	private LocalDate data;
	private LocalTime horario;
	private boolean disponibilidade;
	private List<Reuniao> reunioes;
	
	public Agenda() {
        this.reunioes = new ArrayList<>();
    }
	
	public Agenda(Integer id, LocalDate data, LocalTime horario, boolean disponibilidade, List<Reuniao> reunioes) {		super();
		this.id = id;
		this.data = data;
		this.horario = horario;
		this.disponibilidade = disponibilidade;
		this.reunioes = new ArrayList<>();
	}
	
	public boolean verificarDisponibilidade() {
        return this.disponibilidade;
    }

    public void reservarHorario() {
        this.disponibilidade = false;
    }

    public void liberarHorario() {
        this.disponibilidade = true;
    }

    public List<Reuniao> consultarAgenda() {
        return this.reunioes;
    }
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalTime getHorario() {
		return horario;
	}

	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}

	public boolean isDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(boolean disponibilidade) {
		this.disponibilidade = disponibilidade;
	}

	public List<Reuniao> getReunioes() {
		return reunioes;
	}

	public void setReunioes(List<Reuniao> reunioes) {
		this.reunioes = reunioes;
	}
	
	public void adicionarReuniao(Reuniao reuniao) {
        this.reunioes.add(reuniao);
    }
}
