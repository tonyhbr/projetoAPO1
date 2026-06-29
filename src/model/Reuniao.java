package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reuniao {
	private Integer id;
    private LocalDate data;
    private LocalTime horario;
    private String status;
    private String pauta;
    private String linkOnline;
    private Agenda agenda;

    public Reuniao() {
    	
    }
    
	public Reuniao(Integer id, LocalDate data, LocalTime horario, String status, String pauta, String linkOnline) {
		this.id = id;
		this.data = data;
		this.horario = horario;
		this.status = status;
		this.pauta = pauta;
		this.linkOnline = linkOnline;
	}
	
	public void agendarReuniao() {
        this.status = "Agendada";
    }

    public void cancelarReuniao() {
        this.status = "Cancelada";
    }

    public void reagendarReuniao() {
        this.status = "Reagendada";
    }

    public void confirmarReuniao() {
        this.status = "Confirmada";
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPauta() {
		return pauta;
	}

	public void setPauta(String pauta) {
		this.pauta = pauta;
	}

	public String getLinkOnline() {
		return linkOnline;
	}

	public void setLinkOnline(String linkOnline) {
		this.linkOnline = linkOnline;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}
}