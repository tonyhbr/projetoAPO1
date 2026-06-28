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
import model.Coordenador;

public class CoordenadorView {

	protected Shell shellCoordenador;
	private DadosMemoria dadosMemoria = new DadosMemoria();

	private Text textIdCoordenador;
	private Text textNomeCoordenador;
	private Text textLoginCoordenador;
	private Text textSenhaCoordenador;
	private Table tableCoordenadores;

	public static void main(String[] args) {
		try {
			CoordenadorView window = new CoordenadorView();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void open() {
		Display display = Display.getDefault();
		createContents();
		shellCoordenador.open();
		shellCoordenador.layout();
		while (!shellCoordenador.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	protected void createContents() {
		shellCoordenador = new Shell();
		shellCoordenador.setSize(830, 570);
		shellCoordenador.setText("Cadastro de Coordenador");

		MessageBox caixaWarning = new MessageBox(shellCoordenador, SWT.OK);
		caixaWarning.setText("Aviso");

		Label lblIdCoordenador = new Label(shellCoordenador, SWT.NONE);
		lblIdCoordenador.setBounds(25, 25, 105, 20);
		lblIdCoordenador.setText("Id Coordenador");

		textIdCoordenador = new Text(shellCoordenador, SWT.BORDER);
		textIdCoordenador.setBounds(135, 22, 110, 24);

		Label lblNome = new Label(shellCoordenador, SWT.NONE);
		lblNome.setBounds(25, 65, 105, 20);
		lblNome.setText("Nome");

		textNomeCoordenador = new Text(shellCoordenador, SWT.BORDER);
		textNomeCoordenador.setBounds(135, 62, 210, 24);

		Label lblLogin = new Label(shellCoordenador, SWT.NONE);
		lblLogin.setBounds(25, 105, 105, 20);
		lblLogin.setText("Login");

		textLoginCoordenador = new Text(shellCoordenador, SWT.BORDER);
		textLoginCoordenador.setBounds(135, 102, 160, 24);

		Label lblSenha = new Label(shellCoordenador, SWT.NONE);
		lblSenha.setBounds(25, 145, 105, 20);
		lblSenha.setText("Senha");

		textSenhaCoordenador = new Text(shellCoordenador, SWT.BORDER);
		textSenhaCoordenador.setBounds(135, 142, 160, 24);

		Button btnIncluirCoordenador = new Button(shellCoordenador, SWT.NONE);
		btnIncluirCoordenador.setBounds(380, 62, 110, 28);
		btnIncluirCoordenador.setText("Incluir");
		btnIncluirCoordenador.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (textIdCoordenador.getCharCount() == 0 || textNomeCoordenador.getCharCount() == 0) {
					caixaWarning.setMessage("Informe id e nome do coordenador.");
					caixaWarning.open();
					return;
				}

				try {
					Coordenador coordenador = new Coordenador(Integer.valueOf(textIdCoordenador.getText()),
							textNomeCoordenador.getText(), textLoginCoordenador.getText(),
							textSenhaCoordenador.getText());
					dadosMemoria.incluirCoordenador(coordenador);
					textIdCoordenador.setText("");
					textNomeCoordenador.setText("");
					textLoginCoordenador.setText("");
					textSenhaCoordenador.setText("");
					atualizarTabelaCoordenadores();
				} catch (Exception erro) {
					caixaWarning.setMessage("O id do coordenador deve ser numerico.");
					caixaWarning.open();
				}
			}
		});

		Button btnConsultarCoordenador = new Button(shellCoordenador, SWT.NONE);
		btnConsultarCoordenador.setBounds(380, 105, 110, 28);
		btnConsultarCoordenador.setText("Consultar");
		btnConsultarCoordenador.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				atualizarTabelaCoordenadores();
			}
		});

		tableCoordenadores = new Table(shellCoordenador, SWT.BORDER | SWT.FULL_SELECTION);
		tableCoordenadores.setBounds(25, 265, 760, 250);
		tableCoordenadores.setHeaderVisible(true);
		tableCoordenadores.setLinesVisible(true);

		TableColumn tblclmnId = new TableColumn(tableCoordenadores, SWT.NONE);
		tblclmnId.setWidth(70);
		tblclmnId.setText("Id");

		TableColumn tblclmnNome = new TableColumn(tableCoordenadores, SWT.NONE);
		tblclmnNome.setWidth(220);
		tblclmnNome.setText("Nome");

		TableColumn tblclmnLogin = new TableColumn(tableCoordenadores, SWT.NONE);
		tblclmnLogin.setWidth(180);
		tblclmnLogin.setText("Login");

		TableColumn tblclmnTotalTccs = new TableColumn(tableCoordenadores, SWT.NONE);
		tblclmnTotalTccs.setWidth(100);
		tblclmnTotalTccs.setText("Total TCCs");

		atualizarTabelaCoordenadores();
	}

	private void atualizarTabelaCoordenadores() {
		tableCoordenadores.removeAll();
		for (Coordenador coordenador : dadosMemoria.consultarCoordenadores()) {
			TableItem item = new TableItem(tableCoordenadores, SWT.NONE);
			item.setText(new String[] { String.valueOf(coordenador.getId()), coordenador.getNome(),
					coordenador.getLogin(), String.valueOf(coordenador.getTccs().size()) });
		}
	}
}
