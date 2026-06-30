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
import model.Orientador;
import model.Conversa;

public class ConversaView {

	protected Shell shellConversa;
	private DadosMemoria dadosMemoria = new DadosMemoria();

	private Text textIdConversa;
	private DateTime dateInicio;
	private Combo comboAluno;
	private Combo comboOrientador;
	private Table tableConversas;

	public static void main(String[] args) {
		try {
			ConversaView window = new ConversaView();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void open() {
		Display display = Display.getDefault();
		createContents();
		shellConversa.open();
		shellConversa.layout();
		while (!shellConversa.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	protected void createContents() {
		shellConversa = new Shell();
		shellConversa.setSize(600, 500);
		shellConversa.setText("Gerenciamento de Conversas");

		MessageBox caixaWarning = new MessageBox(shellConversa, SWT.OK);
		caixaWarning.setText("Aviso");

		Label lblId = new Label(shellConversa, SWT.NONE);
		lblId.setBounds(25, 25, 105, 20);
		lblId.setText("ID Conversa");

		textIdConversa = new Text(shellConversa, SWT.BORDER);
		textIdConversa.setBounds(135, 22, 110, 24);

		Label lblDataInicio = new Label(shellConversa, SWT.NONE);
		lblDataInicio.setBounds(25, 65, 105, 20);
		lblDataInicio.setText("Data Início");

		dateInicio = new DateTime(shellConversa, SWT.BORDER);
		dateInicio.setBounds(135, 62, 120, 24);

		Label lblAluno = new Label(shellConversa, SWT.NONE);
		lblAluno.setBounds(25, 105, 105, 20);
		lblAluno.setText("Aluno");

		comboAluno = new Combo(shellConversa, SWT.READ_ONLY);
		comboAluno.setBounds(135, 102, 250, 24);

		Label lblOrientador = new Label(shellConversa, SWT.NONE);
		lblOrientador.setBounds(25, 145, 105, 20);
		lblOrientador.setText("Orientador");

		comboOrientador = new Combo(shellConversa, SWT.READ_ONLY);
		comboOrientador.setBounds(135, 142, 250, 24);

		Button btnIncluir = new Button(shellConversa, SWT.NONE);
		btnIncluir.setBounds(420, 60, 110, 28);
		btnIncluir.setText("Incluir");
		btnIncluir.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (textIdConversa.getCharCount() == 0) {
					caixaWarning.setMessage("Informe o ID da Conversa.");
					caixaWarning.open();
					return;
				}
				if (comboAluno.getSelectionIndex() < 0 || comboOrientador.getSelectionIndex() < 0) {
					caixaWarning.setMessage("Selecione um Aluno e um Orientador.");
					caixaWarning.open();
					return;
				}

				try {
					Aluno aluno = dadosMemoria.consultarAlunos().get(comboAluno.getSelectionIndex());
					Orientador orientador = dadosMemoria.consultarOrientadores().get(comboOrientador.getSelectionIndex());
					
					LocalDate data = LocalDate.of(dateInicio.getYear(), dateInicio.getMonth() + 1, dateInicio.getDay());

					Conversa conv = new Conversa(Integer.parseInt(textIdConversa.getText()), data, aluno, orientador);
					dadosMemoria.incluirConversa(conv);
					
					textIdConversa.setText("");
					atualizarTudo();
				} catch (Exception erro) {
					caixaWarning.setMessage("O ID da conversa deve ser numérico.");
					caixaWarning.open();
				}
			}
		});

		Button btnConsultar = new Button(shellConversa, SWT.NONE);
		btnConsultar.setBounds(420, 100, 110, 28);
		btnConsultar.setText("Consultar");
		btnConsultar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				atualizarTudo();
			}
		});

		tableConversas = new Table(shellConversa, SWT.BORDER | SWT.FULL_SELECTION);
		tableConversas.setBounds(25, 200, 530, 220);
		tableConversas.setHeaderVisible(true);
		tableConversas.setLinesVisible(true);

		TableColumn colId = new TableColumn(tableConversas, SWT.NONE);
		colId.setWidth(60);
		colId.setText("ID");

		TableColumn colData = new TableColumn(tableConversas, SWT.NONE);
		colData.setWidth(110);
		colData.setText("Data Início");

		TableColumn colAluno = new TableColumn(tableConversas, SWT.NONE);
		colAluno.setWidth(170);
		colAluno.setText("Aluno");

		TableColumn colOrientador = new TableColumn(tableConversas, SWT.NONE);
		colOrientador.setWidth(170);
		colOrientador.setText("Orientador");

		atualizarTudo();
	}

	private void atualizarTudo() {
		atualizarCombos();
		atualizarTabela();
	}

	private void atualizarCombos() {
		comboAluno.removeAll();
		for (Aluno a : dadosMemoria.consultarAlunos()) {
			comboAluno.add(a.getId() + " - " + a.getNome());
		}

		comboOrientador.removeAll();
		for (Orientador o : dadosMemoria.consultarOrientadores()) {
			comboOrientador.add(o.getId() + " - " + o.getNome());
		}
	}

	private void atualizarTabela() {
		tableConversas.removeAll();
		for (Conversa c : dadosMemoria.consultarConversas()) {
			TableItem item = new TableItem(tableConversas, SWT.NONE);
			item.setText(new String[] { 
				String.valueOf(c.getId()), 
				String.valueOf(c.getDataInicio()),
				c.getAluno() != null ? c.getAluno().getNome() : "", 
				c.getOrientador() != null ? c.getOrientador().getNome() : "" 
			});
		}
	}
}