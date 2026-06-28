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
import model.TCC;
import model.Termo;

public class TermoView {

	protected Shell shellTermo;
	private DadosMemoria dadosMemoria = new DadosMemoria();

	private Text textIdTermo;
	private Text textTextoTermo;
	private DateTime dateAceiteTermo;
	private Button checkAceitoTermo;
	private Combo comboTccTermo;
	private Table tableTermos;

	public static void main(String[] args) {
		try {
			TermoView window = new TermoView();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void open() {
		Display display = Display.getDefault();
		createContents();
		shellTermo.open();
		shellTermo.layout();
		while (!shellTermo.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	protected void createContents() {
		shellTermo = new Shell();
		shellTermo.setSize(850, 590);
		shellTermo.setText("Cadastro de Termo");

		MessageBox caixaWarning = new MessageBox(shellTermo, SWT.OK);
		caixaWarning.setText("Aviso");

		Label lblIdTermo = new Label(shellTermo, SWT.NONE);
		lblIdTermo.setBounds(25, 25, 105, 20);
		lblIdTermo.setText("Id Termo");

		textIdTermo = new Text(shellTermo, SWT.BORDER);
		textIdTermo.setBounds(135, 22, 110, 24);

		Label lblTexto = new Label(shellTermo, SWT.NONE);
		lblTexto.setBounds(25, 65, 105, 20);
		lblTexto.setText("Texto");

		textTextoTermo = new Text(shellTermo, SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.V_SCROLL | SWT.READ_ONLY);
		textTextoTermo.setBounds(135, 62, 260, 90);
		textTextoTermo.setText(Termo.TEXTO_PADRAO);

		Label lblDataAceite = new Label(shellTermo, SWT.NONE);
		lblDataAceite.setBounds(25, 175, 105, 20);
		lblDataAceite.setText("Data Aceite");

		dateAceiteTermo = new DateTime(shellTermo, SWT.BORDER);
		dateAceiteTermo.setBounds(135, 172, 120, 24);

		Label lblTcc = new Label(shellTermo, SWT.NONE);
		lblTcc.setBounds(435, 25, 105, 20);
		lblTcc.setText("TCC");

		comboTccTermo = new Combo(shellTermo, SWT.READ_ONLY);
		comboTccTermo.setBounds(535, 22, 250, 24);

		checkAceitoTermo = new Button(shellTermo, SWT.CHECK);
		checkAceitoTermo.setBounds(535, 65, 140, 24);
		checkAceitoTermo.setText("Aceito");

		Button btnIncluirTermo = new Button(shellTermo, SWT.NONE);
		btnIncluirTermo.setBounds(435, 115, 110, 28);
		btnIncluirTermo.setText("Incluir");
		btnIncluirTermo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (textIdTermo.getCharCount() == 0) {
					caixaWarning.setMessage("Informe o id do termo.");
					caixaWarning.open();
					return;
				}
				if (comboTccTermo.getSelectionIndex() < 0) {
					caixaWarning.setMessage("Selecione um TCC para o termo.");
					caixaWarning.open();
					return;
				}

				try {
					TCC tcc = dadosMemoria.consultarTccs().get(comboTccTermo.getSelectionIndex());
					LocalDate dataAceite = null;
					if (checkAceitoTermo.getSelection()) {
						dataAceite = LocalDate.of(dateAceiteTermo.getYear(), dateAceiteTermo.getMonth() + 1,
								dateAceiteTermo.getDay());
					}
					Termo termo = new Termo(Integer.valueOf(textIdTermo.getText()), Termo.TEXTO_PADRAO,
							dataAceite, checkAceitoTermo.getSelection(), tcc);
					dadosMemoria.incluirTermo(termo);
					textIdTermo.setText("");
					checkAceitoTermo.setSelection(false);
					atualizarTudo();
				} catch (Exception erro) {
					caixaWarning.setMessage("O id do termo deve ser numerico.");
					caixaWarning.open();
				}
			}
		});

		Button btnConsultarTermo = new Button(shellTermo, SWT.NONE);
		btnConsultarTermo.setBounds(565, 115, 110, 28);
		btnConsultarTermo.setText("Consultar");
		btnConsultarTermo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				atualizarTudo();
			}
		});

		Button btnTcc = new Button(shellTermo, SWT.NONE);
		btnTcc.setBounds(685, 115, 100, 28);
		btnTcc.setText("Abrir TCC");
		btnTcc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new TCCView().open();
				atualizarTudo();
			}
		});

		tableTermos = new Table(shellTermo, SWT.BORDER | SWT.FULL_SELECTION);
		tableTermos.setBounds(25, 245, 785, 285);
		tableTermos.setHeaderVisible(true);
		tableTermos.setLinesVisible(true);

		TableColumn tblclmnId = new TableColumn(tableTermos, SWT.NONE);
		tblclmnId.setWidth(55);
		tblclmnId.setText("Id");

		TableColumn tblclmnTexto = new TableColumn(tableTermos, SWT.NONE);
		tblclmnTexto.setWidth(280);
		tblclmnTexto.setText("Texto");

		TableColumn tblclmnDataAceite = new TableColumn(tableTermos, SWT.NONE);
		tblclmnDataAceite.setWidth(110);
		tblclmnDataAceite.setText("Data Aceite");

		TableColumn tblclmnAceito = new TableColumn(tableTermos, SWT.NONE);
		tblclmnAceito.setWidth(80);
		tblclmnAceito.setText("Aceito");

		TableColumn tblclmnTcc = new TableColumn(tableTermos, SWT.NONE);
		tblclmnTcc.setWidth(220);
		tblclmnTcc.setText("TCC");

		atualizarTudo();
	}

	private void atualizarTudo() {
		atualizarComboTcc();
		atualizarTabelaTermos();
	}

	private void atualizarComboTcc() {
		comboTccTermo.removeAll();
		for (TCC tcc : dadosMemoria.consultarTccs()) {
			comboTccTermo.add(tcc.getId() + " - " + tcc.getTitulo());
		}
	}

	private void atualizarTabelaTermos() {
		tableTermos.removeAll();
		for (Termo termo : dadosMemoria.consultarTermos()) {
			TableItem item = new TableItem(tableTermos, SWT.NONE);
			String dataAceite = termo.getDataAceite() == null ? "" : String.valueOf(termo.getDataAceite());
			item.setText(new String[] { String.valueOf(termo.getId()), termo.getTexto(), dataAceite,
					String.valueOf(termo.getAceito()), termo.getTcc().getTitulo() });
		}
	}
}
