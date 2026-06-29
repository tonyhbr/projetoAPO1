package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
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

public class alunoView {

	protected Shell shellAluno;
	private DadosMemoria dadosMemoria = new DadosMemoria();

	private Text textIdAluno;
	private Text textNomeAluno;
	private Text textLoginAluno;
	private Text textSenhaAluno;
	private Text textCursoAluno;
	private Table tableAlunos;

	public static void main(String[] args) {
		try {
			alunoView window = new alunoView();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void open() {
		Display display = Display.getDefault();
		createContents();
		shellAluno.open();
		shellAluno.layout();
		while (!shellAluno.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	protected void createContents() {
		shellAluno = new Shell();
		shellAluno.setSize(830, 570);
		shellAluno.setText("Cadastro de Aluno");

		MessageBox caixaWarning = new MessageBox(shellAluno, SWT.OK);
		caixaWarning.setText("Aviso");

		Label lblIdAluno = new Label(shellAluno, SWT.NONE);
		lblIdAluno.setBounds(25, 25, 105, 20);
		lblIdAluno.setText("Id Aluno");

		textIdAluno = new Text(shellAluno, SWT.BORDER);
		textIdAluno.setBounds(135, 22, 110, 24);

		Label lblNome = new Label(shellAluno, SWT.NONE);
		lblNome.setBounds(25, 65, 105, 20);
		lblNome.setText("Nome");

		textNomeAluno = new Text(shellAluno, SWT.BORDER);
		textNomeAluno.setBounds(135, 62, 210, 24);

		Label lblLogin = new Label(shellAluno, SWT.NONE);
		lblLogin.setBounds(25, 105, 105, 20);
		lblLogin.setText("Login");

		textLoginAluno = new Text(shellAluno, SWT.BORDER);
		textLoginAluno.setBounds(135, 102, 160, 24);

		Label lblSenha = new Label(shellAluno, SWT.NONE);
		lblSenha.setBounds(25, 145, 105, 20);
		lblSenha.setText("Senha");

		textSenhaAluno = new Text(shellAluno, SWT.BORDER);
		textSenhaAluno.setBounds(135, 142, 160, 24);

		Label lblCurso = new Label(shellAluno, SWT.NONE);
		lblCurso.setBounds(25, 185, 105, 20);
		lblCurso.setText("Curso");

		textCursoAluno = new Text(shellAluno, SWT.BORDER);
		textCursoAluno.setBounds(135, 182, 210, 24);

		Button btnIncluirAluno = new Button(shellAluno, SWT.NONE);
		btnIncluirAluno.setBounds(380, 62, 110, 28);
		btnIncluirAluno.setText("Incluir");
		btnIncluirAluno.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (textIdAluno.getCharCount() == 0 || textNomeAluno.getCharCount() == 0
						|| textCursoAluno.getCharCount() == 0) {
					caixaWarning.setMessage("Informe id, nome e curso do aluno.");
					caixaWarning.open();
					return;
				}

				try {
					Aluno aluno = new Aluno(Integer.valueOf(textIdAluno.getText()), textNomeAluno.getText(),
							textLoginAluno.getText(), textSenhaAluno.getText(), textCursoAluno.getText());
					dadosMemoria.incluirAluno(aluno);
					textIdAluno.setText("");
					textNomeAluno.setText("");
					textLoginAluno.setText("");
					textSenhaAluno.setText("");
					textCursoAluno.setText("");
					atualizarTabelaAlunos();
				} catch (Exception erro) {
					caixaWarning.setMessage("O id do aluno deve ser numerico.");
					caixaWarning.open();
				}
			}
		});

		Button btnConsultarAluno = new Button(shellAluno, SWT.NONE);
		btnConsultarAluno.setBounds(380, 105, 110, 28);
		btnConsultarAluno.setText("Consultar");
		btnConsultarAluno.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				atualizarTabelaAlunos();
			}
		});

		tableAlunos = new Table(shellAluno, SWT.BORDER | SWT.FULL_SELECTION);
		tableAlunos.setBounds(25, 265, 760, 250);
		tableAlunos.setHeaderVisible(true);
		tableAlunos.setLinesVisible(true);

		TableColumn tblclmnId = new TableColumn(tableAlunos, SWT.NONE);
		tblclmnId.setWidth(70);
		tblclmnId.setText("Id");

		TableColumn tblclmnNome = new TableColumn(tableAlunos, SWT.NONE);
		tblclmnNome.setWidth(180);
		tblclmnNome.setText("Nome");

		TableColumn tblclmnLogin = new TableColumn(tableAlunos, SWT.NONE);
		tblclmnLogin.setWidth(150);
		tblclmnLogin.setText("Login");

		TableColumn tblclmnCurso = new TableColumn(tableAlunos, SWT.NONE);
		tblclmnCurso.setWidth(150);
		tblclmnCurso.setText("Curso");

		TableColumn tblclmnTcc = new TableColumn(tableAlunos, SWT.NONE);
		tblclmnTcc.setWidth(200);
		tblclmnTcc.setText("TCC");

		atualizarTabelaAlunos();
	}

	private void atualizarTabelaAlunos() {
		tableAlunos.removeAll();
		for (Aluno aluno : dadosMemoria.consultarAlunos()) {
			TableItem item = new TableItem(tableAlunos, SWT.NONE);
			String tituloTcc = aluno.getTcc() == null ? "" : aluno.getTcc().getTitulo();
			item.setText(new String[] { String.valueOf(aluno.getId()), aluno.getNome(), aluno.getLogin(),
					aluno.getCurso(), tituloTcc });
		}
	}
}
