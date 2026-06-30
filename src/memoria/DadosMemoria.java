package memoria;

import java.util.ArrayList;
import java.util.List;

import model.Agenda;
import model.Aluno;
import model.Arquivo;
import model.Avaliacao;
import model.Conversa;
import model.Coordenador;
import model.Feedback;
import model.Mensagem;
import model.Orientador;
import model.Reuniao;
import model.Tarefa;
import model.TCC;
import model.Termo;
import model.VersaoTCC;

public class DadosMemoria {

    private static List<Aluno> listaAlunos = new ArrayList<>();
    private static List<Orientador> listaOrientadores = new ArrayList<>();
    private static List<Coordenador> listaCoordenadores = new ArrayList<>();
    private static List<TCC> listaTccs = new ArrayList<>();
    private static List<Termo> listaTermos = new ArrayList<>();
    private static List<VersaoTCC> listaVersoesTCC = new ArrayList<>();
    private static List<Avaliacao> listaAvaliacoes = new ArrayList<>();
    private static List<Agenda> listaAgendas = new ArrayList<>();
    private static List<Reuniao> listaReunioes = new ArrayList<>();
    private static List<Feedback> listaFeedbacks = new ArrayList<>();
    private static List<Tarefa> listaTarefas = new ArrayList<>();
    private static List<Conversa> listaConversas = new ArrayList<>();
    private static List<Mensagem> listaMensagens = new ArrayList<>();
    private static List<Arquivo> listaArquivos = new ArrayList<>();

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
