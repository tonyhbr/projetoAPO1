package memoria;

import java.util.ArrayList;
import java.util.List;

import model.Aluno;
import model.Coordenador;
import model.Orientador;
import model.TCC;
import model.Termo;

public class DadosMemoria {

	private static List<Aluno> listaAlunos = new ArrayList<Aluno>();
	private static List<Orientador> listaOrientadores = new ArrayList<Orientador>();
	private static List<Coordenador> listaCoordenadores = new ArrayList<Coordenador>();
	private static List<TCC> listaTccs = new ArrayList<TCC>();
	private static List<Termo> listaTermos = new ArrayList<Termo>();

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
}
