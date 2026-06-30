package memoria;

import java.util.ArrayList;
import java.util.List;

import model.Agenda;
import model.Aluno;
<<<<<<< HEAD
import model.Avaliacao;
=======
import model.Arquivo;
import model.Conversa;
>>>>>>> branch 'master' of https://github.com/tonyhbr/projetoAPO1.git
import model.Coordenador;
<<<<<<< HEAD
import model.Feedback;
=======
import model.Mensagem;
>>>>>>> branch 'master' of https://github.com/tonyhbr/projetoAPO1.git
import model.Orientador;
import model.Reuniao;
import model.Tarefa;
import model.TCC;
import model.Termo;
import model.VersaoTCC;

public class DadosMemoria {

<<<<<<< HEAD
    private static List<Aluno> listaAlunos = new ArrayList<>();
    private static List<Orientador> listaOrientadores = new ArrayList<>();
    private static List<Coordenador> listaCoordenadores = new ArrayList<>();
=======
	private static List<Aluno> listaAlunos = new ArrayList<Aluno>();
	private static List<Orientador> listaOrientadores = new ArrayList<Orientador>();
	private static List<Coordenador> listaCoordenadores = new ArrayList<Coordenador>();
	private static List<TCC> listaTccs = new ArrayList<TCC>();
	private static List<Termo> listaTermos = new ArrayList<Termo>();
	private static List<Agenda> listaAgendas = new ArrayList<Agenda>();
	private static List<Reuniao> listaReunioes = new ArrayList<Reuniao>();
	private static List<Conversa> listaConversas = new ArrayList<Conversa>();
	private static List<Mensagem> listaMensagens = new ArrayList<Mensagem>();
	private static List<Arquivo> listaArquivos = new ArrayList<Arquivo>();
>>>>>>> branch 'master' of https://github.com/tonyhbr/projetoAPO1.git

<<<<<<< HEAD
    private static List<TCC> listaTccs = new ArrayList<>();
    private static List<Termo> listaTermos = new ArrayList<>();
    private static List<VersaoTCC> listaVersoesTCC = new ArrayList<>();
=======
	
	public void incluirConversa(Conversa conversa) {
		listaConversas.add(conversa);
	}

	public void incluirMensagem(Mensagem mensagem) {
		listaMensagens.add(mensagem);
		if (mensagem.getConversa() != null) {
			mensagem.getConversa().adicionarMensagem(mensagem);
		}
	}

	public void incluirArquivo(Arquivo arquivo) {
		listaArquivos.add(arquivo);
		if (arquivo.getConversa() != null) {
			arquivo.getConversa().adicionarArquivo(arquivo);
		}
	}
	
	public void incluirAluno(Aluno aluno) {
		listaAlunos.add(aluno);
	}
>>>>>>> branch 'master' of https://github.com/tonyhbr/projetoAPO1.git

    private static List<Avaliacao> listaAvaliacoes = new ArrayList<>();

    private static List<Agenda> listaAgendas = new ArrayList<>();
    private static List<Reuniao> listaReunioes = new ArrayList<>();

    private static List<Feedback> listaFeedbacks = new ArrayList<>();
    private static List<Tarefa> listaTarefas = new ArrayList<>();

    /* ==========================
     * Inclusões
     * ========================== */

    public void incluirAluno(Aluno aluno) {
        listaAlunos.add(aluno);
    }

    public void incluirOrientador(Orientador orientador) {
        listaOrientadores.add(orientador);
    }

    public void incluirCoordenador(Coordenador coordenador) {
        listaCoordenadores.add(coordenador);
    }

    public void incluirTcc(TCC tcc) {

        listaTccs.add(tcc);

        if (tcc.getAluno() != null) {
            tcc.getAluno().setTcc(tcc);
        }

        if (tcc.getOrientador() != null) {
            tcc.getOrientador().adicionarTcc(tcc);
        }

        if (tcc.getCoordenador() != null) {
            tcc.getCoordenador().adicionarTcc(tcc);
        }
    }

<<<<<<< HEAD
    public void incluirTermo(Termo termo) {

        listaTermos.add(termo);

        if (termo.getTcc() != null) {
            termo.getTcc().adicionarTermo(termo);
        }
    }

    public void incluirVersaoTCC(VersaoTCC versao) {

        listaVersoesTCC.add(versao);

        if (versao.getTcc() != null) {
            versao.getTcc().adicionarVersao(versao);
        }
    }

    public void incluirAvaliacao(Avaliacao avaliacao) {

        listaAvaliacoes.add(avaliacao);

        if (avaliacao.getTcc() != null) {
            avaliacao.getTcc().adicionarAvaliacao(avaliacao);
        }
    }

    public void incluirAgenda(Agenda agenda) {
        listaAgendas.add(agenda);
    }

    public void incluirReuniao(Reuniao reuniao) {

        listaReunioes.add(reuniao);

        if (reuniao.getAgenda() != null) {
            reuniao.getAgenda().adicionarReuniao(reuniao);
            reuniao.getAgenda().reservarHorario();
        }
    }

    public void incluirFeedback(Feedback feedback) {

        listaFeedbacks.add(feedback);

        if (feedback.getTcc() != null) {
            feedback.getTcc().adicionarFeedback(feedback);
        }

    }

    public void incluirTarefa(Tarefa tarefa) {

        listaTarefas.add(tarefa);

        if (tarefa.getTcc() != null) {
            tarefa.getTcc().adicionarTarefa(tarefa);
        }

    }

    /* ==========================
     * Consultas
     * ========================== */

    public List<Aluno> consultarAlunos() {
        return listaAlunos;
    }

    public List<Orientador> consultarOrientadores() {
        return listaOrientadores;
    }

    public List<Coordenador> consultarCoordenadores() {
        return listaCoordenadores;
    }

    public List<TCC> consultarTccs() {
        return listaTccs;
    }

    public List<Termo> consultarTermos() {
        return listaTermos;
    }

    public List<VersaoTCC> consultarVersoesTCC() {
        return listaVersoesTCC;
    }

    public List<Avaliacao> consultarAvaliacoes() {
        return listaAvaliacoes;
    }

    public List<Agenda> consultarAgendas() {
        return listaAgendas;
    }

    public List<Reuniao> consultarReunioes() {
        return listaReunioes;
    }

    public List<Feedback> consultarFeedbacks() {
        return listaFeedbacks;
    }

    public List<Tarefa> consultarTarefas() {
        return listaTarefas;
    }

}
=======
	public List<Reuniao> consultarReunioes() {
	    return listaReunioes;
	}
	
	public List<Conversa> consultarConversas() {
		return listaConversas;
	}

	public List<Mensagem> consultarMensagens() {
		return listaMensagens;
	}

	public List<Arquivo> consultarArquivos() {
		return listaArquivos;
	}
}
>>>>>>> branch 'master' of https://github.com/tonyhbr/projetoAPO1.git
