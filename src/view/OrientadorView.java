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
import model.Orientador;

public class OrientadorView {

	protected Shell shellOrientador;
	private DadosMemoria dadosMemoria = new DadosMemoria();

	private Text textIdOrientador;
	private Text textNomeOrientador;
	private Text textLoginOrientador;
	private Text textSenhaOrientador;
	private Text textAreaOrientador;
	private Table tableOrientadores;

	public static void main(String[] args) {
		try {
			OrientadorView window = new OrientadorView();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void open() {
		Display display = Display.getDefault();
		createContents();
		shellOrientador.open();
		shellOrientador.layout();
		while (!shellOrientador.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	protected void createContents() {
		shellOrientador = new Shell();
		shellOrientador.setSize(830, 570);
		shellOrientador.setText("Cadastro de Orientador");

		MessageBox caixaWarning = new MessageBox(shellOrientador, SWT.OK);
		caixaWarning.setText("Aviso");

		Label lblIdOrientador = new Label(shellOrientador, SWT.NONE);
		lblIdOrientador.setBounds(25, 25, 105, 20);
		lblIdOrientador.setText("Id Orientador");

		textIdOrientador = new Text(shellOrientador, SWT.BORDER);
		textIdOrientador.setBounds(135, 22, 110, 24);

		Label lblNome = new Label(shellOrientador, SWT.NONE);
		lblNome.setBounds(25, 65, 105, 20);
		lblNome.setText("Nome");

		textNomeOrientador = new Text(shellOrientador, SWT.BORDER);
		textNomeOrientador.setBounds(135, 62, 210, 24);

		Label lblLogin = new Label(shellOrientador, SWT.NONE);
		lblLogin.setBounds(25, 105, 105, 20);
		lblLogin.setText("Login");

		textLoginOrientador = new Text(shellOrientador, SWT.BORDER);
		textLoginOrientador.setBounds(135, 102, 160, 24);

		Label lblSenha = new Label(shellOrientador, SWT.NONE);
		lblSenha.setBounds(25, 145, 105, 20);
		lblSenha.setText("Senha");

		textSenhaOrientador = new Text(shellOrientador, SWT.BORDER);
		textSenhaOrientador.setBounds(135, 142, 160, 24);

		Label lblArea = new Label(shellOrientador, SWT.NONE);
		lblArea.setBounds(25, 185, 105, 20);
		lblArea.setText("Area");

		textAreaOrientador = new Text(shellOrientador, SWT.BORDER);
		textAreaOrientador.setBounds(135, 182, 210, 24);

		Button btnIncluirOrientador = new Button(shellOrientador, SWT.NONE);
		btnIncluirOrientador.setBounds(380, 62, 110, 28);
		btnIncluirOrientador.setText("Incluir");
		btnIncluirOrientador.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (textIdOrientador.getCharCount() == 0 || textNomeOrientador.getCharCount() == 0
						|| textAreaOrientador.getCharCount() == 0) {
					caixaWarning.setMessage("Informe id, nome e area do orientador.");
					caixaWarning.open();
					return;
				}

				try {
					Orientador orientador = new Orientador(Integer.valueOf(textIdOrientador.getText()),
							textNomeOrientador.getText(), textLoginOrientador.getText(), textSenhaOrientador.getText(),
							textAreaOrientador.getText());
					dadosMemoria.incluirOrientador(orientador);
					textIdOrientador.setText("");
					textNomeOrientador.setText("");
					textLoginOrientador.setText("");
					textSenhaOrientador.setText("");
					textAreaOrientador.setText("");
					atualizarTabelaOrientadores();
				} catch (Exception erro) {
					caixaWarning.setMessage("O id do orientador deve ser numerico.");
					caixaWarning.open();
				}
			}
		});

		Button btnConsultarOrientador = new Button(shellOrientador, SWT.NONE);
		btnConsultarOrientador.setBounds(380, 105, 110, 28);
		btnConsultarOrientador.setText("Consultar");
		btnConsultarOrientador.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				atualizarTabelaOrientadores();
			}
		});

		tableOrientadores = new Table(shellOrientador, SWT.BORDER | SWT.FULL_SELECTION);
		tableOrientadores.setBounds(25, 265, 760, 250);
		tableOrientadores.setHeaderVisible(true);
		tableOrientadores.setLinesVisible(true);

		TableColumn tblclmnId = new TableColumn(tableOrientadores, SWT.NONE);
		tblclmnId.setWidth(70);
		tblclmnId.setText("Id");

		TableColumn tblclmnNome = new TableColumn(tableOrientadores, SWT.NONE);
		tblclmnNome.setWidth(180);
		tblclmnNome.setText("Nome");

		TableColumn tblclmnLogin = new TableColumn(tableOrientadores, SWT.NONE);
		tblclmnLogin.setWidth(150);
		tblclmnLogin.setText("Login");

		TableColumn tblclmnArea = new TableColumn(tableOrientadores, SWT.NONE);
		tblclmnArea.setWidth(180);
		tblclmnArea.setText("Area");

		TableColumn tblclmnTotalTccs = new TableColumn(tableOrientadores, SWT.NONE);
		tblclmnTotalTccs.setWidth(100);
		tblclmnTotalTccs.setText("Total TCCs");

		atualizarTabelaOrientadores();
	}

	private void atualizarTabelaOrientadores() {
		tableOrientadores.removeAll();
		for (Orientador orientador : dadosMemoria.consultarOrientadores()) {
			TableItem item = new TableItem(tableOrientadores, SWT.NONE);
			item.setText(new String[] { String.valueOf(orientador.getId()), orientador.getNome(),
					orientador.getLogin(), orientador.getAreaAtuacao(),
					String.valueOf(orientador.getTccs().size()) });
		}
	}
}
