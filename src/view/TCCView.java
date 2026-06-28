package view;

import java.time.LocalDate;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import memoria.DadosMemoria;
import model.Aluno;
import model.Coordenador;
import model.Orientador;
import model.TCC;

public class TCCView {

	protected Shell shellTcc;
	private DadosMemoria dadosMemoria = new DadosMemoria();

	private Text textIdTcc;
	private Text textTituloTcc;
	private Text textStatusTcc;
	private DateTime dateInicioTcc;
	private DateTime dateEntregaTcc;
	private Combo comboAlunoTcc;
	private Combo comboOrientadorTcc;
	private Combo comboCoordenadorTcc;
	private Table tableTccs;

	public static void main(String[] args) {
		try {
			TCCView window = new TCCView();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void open() {
		Display display = Display.getDefault();
		createContents();
		shellTcc.open();
		shellTcc.layout();
		while (!shellTcc.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	protected void createContents() {
		shellTcc = new Shell();
		shellTcc.setSize(870, 610);
		shellTcc.setText("Cadastro de TCC");

		MessageBox caixaWarning = new MessageBox(shellTcc, SWT.OK);
		caixaWarning.setText("Aviso");

		Label lblIdTcc = new Label(shellTcc, SWT.NONE);
		lblIdTcc.setBounds(25, 25, 105, 20);
		lblIdTcc.setText("Id TCC");

		textIdTcc = new Text(shellTcc, SWT.BORDER);
		textIdTcc.setBounds(135, 22, 110, 24);

		Label lblTitulo = new Label(shellTcc, SWT.NONE);
		lblTitulo.setBounds(25, 65, 105, 20);
		lblTitulo.setText("Titulo");

		textTituloTcc = new Text(shellTcc, SWT.BORDER);
		textTituloTcc.setBounds(135, 62, 260, 24);

		Label lblStatus = new Label(shellTcc, SWT.NONE);
		lblStatus.setBounds(25, 105, 105, 20);
		lblStatus.setText("Status");

		textStatusTcc = new Text(shellTcc, SWT.BORDER);
		textStatusTcc.setBounds(135, 102, 160, 24);

		Label lblDataInicio = new Label(shellTcc, SWT.NONE);
		lblDataInicio.setBounds(25, 145, 105, 20);
		lblDataInicio.setText("Data Inicio");

		dateInicioTcc = new DateTime(shellTcc, SWT.BORDER);
		dateInicioTcc.setBounds(135, 142, 120, 24);

		Label lblDataEntrega = new Label(shellTcc, SWT.NONE);
		lblDataEntrega.setBounds(25, 185, 105, 20);
		lblDataEntrega.setText("Data Entrega");

		dateEntregaTcc = new DateTime(shellTcc, SWT.BORDER);
		dateEntregaTcc.setBounds(135, 182, 120, 24);

		Label lblAluno = new Label(shellTcc, SWT.NONE);
		lblAluno.setBounds(435, 25, 105, 20);
		lblAluno.setText("Aluno");

		comboAlunoTcc = new Combo(shellTcc, SWT.READ_ONLY);
		comboAlunoTcc.setBounds(535, 22, 250, 24);

		Label lblOrientador = new Label(shellTcc, SWT.NONE);
		lblOrientador.setBounds(435, 65, 105, 20);
		lblOrientador.setText("Orientador");

		comboOrientadorTcc = new Combo(shellTcc, SWT.READ_ONLY);
		comboOrientadorTcc.setBounds(535, 62, 250, 24);

		Label lblCoordenador = new Label(shellTcc, SWT.NONE);
		lblCoordenador.setBounds(435, 105, 105, 20);
		lblCoordenador.setText("Coordenador");

		comboCoordenadorTcc = new Combo(shellTcc, SWT.READ_ONLY);
		comboCoordenadorTcc.setBounds(535, 102, 250, 24);

		Button btnIncluirTcc = new Button(shellTcc, SWT.NONE);
		btnIncluirTcc.setBounds(435, 160, 110, 28);
		btnIncluirTcc.setText("Incluir");
		btnIncluirTcc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (textIdTcc.getCharCount() == 0 || textTituloTcc.getCharCount() == 0
						|| textStatusTcc.getCharCount() == 0) {
					caixaWarning.setMessage("Informe id, titulo e status do TCC.");
					caixaWarning.open();
					return;
				}
				if (comboAlunoTcc.getSelectionIndex() < 0 || comboOrientadorTcc.getSelectionIndex() < 0
						|| comboCoordenadorTcc.getSelectionIndex() < 0) {
					caixaWarning.setMessage("Selecione aluno, orientador e coordenador.");
					caixaWarning.open();
					return;
				}

				try {
					Aluno aluno = dadosMemoria.consultarAlunos().get(comboAlunoTcc.getSelectionIndex());
					Orientador orientador = dadosMemoria.consultarOrientadores()
							.get(comboOrientadorTcc.getSelectionIndex());
					Coordenador coordenador = dadosMemoria.consultarCoordenadores()
							.get(comboCoordenadorTcc.getSelectionIndex());
					LocalDate dataInicio = LocalDate.of(dateInicioTcc.getYear(), dateInicioTcc.getMonth() + 1,
							dateInicioTcc.getDay());
					LocalDate dataEntrega = LocalDate.of(dateEntregaTcc.getYear(), dateEntregaTcc.getMonth() + 1,
							dateEntregaTcc.getDay());

					TCC tcc = new TCC(Integer.valueOf(textIdTcc.getText()), textTituloTcc.getText(),
							textStatusTcc.getText(), dataInicio, dataEntrega, aluno, orientador, coordenador);
					dadosMemoria.incluirTcc(tcc);
					textIdTcc.setText("");
					textTituloTcc.setText("");
					textStatusTcc.setText("");
					atualizarTudo();
				} catch (Exception erro) {
					caixaWarning.setMessage("O id do TCC deve ser numerico.");
					caixaWarning.open();
				}
			}
		});

		Button btnConsultarTcc = new Button(shellTcc, SWT.NONE);
		btnConsultarTcc.setBounds(565, 160, 110, 28);
		btnConsultarTcc.setText("Consultar");
		btnConsultarTcc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				atualizarTudo();
			}
		});

		Button btnAluno = new Button(shellTcc, SWT.NONE);
		btnAluno.setBounds(25, 225, 120, 28);
		btnAluno.setText("Abrir Aluno");
		btnAluno.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new AlunoView().open();
				atualizarTudo();
			}
		});

		Button btnOrientador = new Button(shellTcc, SWT.NONE);
		btnOrientador.setBounds(155, 225, 130, 28);
		btnOrientador.setText("Abrir Orientador");
		btnOrientador.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new OrientadorView().open();
				atualizarTudo();
			}
		});

		Button btnCoordenador = new Button(shellTcc, SWT.NONE);
		btnCoordenador.setBounds(295, 225, 140, 28);
		btnCoordenador.setText("Abrir Coordenador");
		btnCoordenador.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new CoordenadorView().open();
				atualizarTudo();
			}
		});

		Button btnAtualizar = new Button(shellTcc, SWT.NONE);
		btnAtualizar.setBounds(445, 225, 120, 28);
		btnAtualizar.setText("Atualizar");
		btnAtualizar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				atualizarTudo();
			}
		});

		Button btnTermo = new Button(shellTcc, SWT.NONE);
		btnTermo.setBounds(575, 225, 120, 28);
		btnTermo.setText("Abrir Termo");
		btnTermo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new TermoView().open();
				atualizarTudo();
			}
		});

		tableTccs = new Table(shellTcc, SWT.BORDER | SWT.FULL_SELECTION);
		tableTccs.setBounds(25, 285, 800, 250);
		tableTccs.setHeaderVisible(true);
		tableTccs.setLinesVisible(true);

		TableColumn tblclmnId = new TableColumn(tableTccs, SWT.NONE);
		tblclmnId.setWidth(55);
		tblclmnId.setText("Id");

		TableColumn tblclmnTitulo = new TableColumn(tableTccs, SWT.NONE);
		tblclmnTitulo.setWidth(170);
		tblclmnTitulo.setText("Titulo");

		TableColumn tblclmnStatus = new TableColumn(tableTccs, SWT.NONE);
		tblclmnStatus.setWidth(90);
		tblclmnStatus.setText("Status");

		TableColumn tblclmnInicio = new TableColumn(tableTccs, SWT.NONE);
		tblclmnInicio.setWidth(85);
		tblclmnInicio.setText("Inicio");

		TableColumn tblclmnEntrega = new TableColumn(tableTccs, SWT.NONE);
		tblclmnEntrega.setWidth(85);
		tblclmnEntrega.setText("Entrega");

		TableColumn tblclmnAluno = new TableColumn(tableTccs, SWT.NONE);
		tblclmnAluno.setWidth(115);
		tblclmnAluno.setText("Aluno");

		TableColumn tblclmnOrientador = new TableColumn(tableTccs, SWT.NONE);
		tblclmnOrientador.setWidth(115);
		tblclmnOrientador.setText("Orientador");

		TableColumn tblclmnCoordenador = new TableColumn(tableTccs, SWT.NONE);
		tblclmnCoordenador.setWidth(115);
		tblclmnCoordenador.setText("Coordenador");

		atualizarTudo();
	}

	private void atualizarTudo() {
		atualizarCombos();
		atualizarTabelaTccs();
	}

	private void atualizarCombos() {
		comboAlunoTcc.removeAll();
		for (Aluno aluno : dadosMemoria.consultarAlunos()) {
			comboAlunoTcc.add(aluno.getId() + " - " + aluno.getNome());
		}

		comboOrientadorTcc.removeAll();
		for (Orientador orientador : dadosMemoria.consultarOrientadores()) {
			comboOrientadorTcc.add(orientador.getId() + " - " + orientador.getNome());
		}

		comboCoordenadorTcc.removeAll();
		for (Coordenador coordenador : dadosMemoria.consultarCoordenadores()) {
			comboCoordenadorTcc.add(coordenador.getId() + " - " + coordenador.getNome());
		}
	}

	private void atualizarTabelaTccs() {
		tableTccs.removeAll();
		for (TCC tcc : dadosMemoria.consultarTccs()) {
			TableItem item = new TableItem(tableTccs, SWT.NONE);
			item.setText(new String[] { String.valueOf(tcc.getId()), tcc.getTitulo(), tcc.getStatus(),
					String.valueOf(tcc.getDataInicio()), String.valueOf(tcc.getDataEntrega()),
					tcc.getAluno().getNome(), tcc.getOrientador().getNome(), tcc.getCoordenador().getNome() });
		}
	}
}
