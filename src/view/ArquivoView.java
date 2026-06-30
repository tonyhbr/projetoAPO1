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
import model.Conversa;
import model.Arquivo;

public class ArquivoView {

	protected Shell shellArquivo;
	private DadosMemoria dadosMemoria = new DadosMemoria();

	private Text textIdArq;
	private Text textNome;
	private Text textTipo;
	private Text textTamanho;
	private Text textStatus;
	private DateTime dateEnvio;
	private Combo comboConversa;
	private Table tableArquivos;

	public static void main(String[] args) {
		try {
			ArquivoView window = new ArquivoView();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void open() {
		Display display = Display.getDefault();
		createContents();
		shellArquivo.open();
		shellArquivo.layout();
		while (!shellArquivo.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	protected void createContents() {
		shellArquivo = new Shell();
		shellArquivo.setSize(700, 520);
		shellArquivo.setText("Repositório de Arquivos");

		MessageBox caixaWarning = new MessageBox(shellArquivo, SWT.OK);
		caixaWarning.setText("Aviso");

		Label lblId = new Label(shellArquivo, SWT.NONE);
		lblId.setBounds(25, 25, 105, 20);
		lblId.setText("ID Arquivo");

		textIdArq = new Text(shellArquivo, SWT.BORDER);
		textIdArq.setBounds(135, 22, 110, 24);

		Label lblNome = new Label(shellArquivo, SWT.NONE);
		lblNome.setBounds(25, 65, 105, 20);
		lblNome.setText("Nome");

		textNome = new Text(shellArquivo, SWT.BORDER);
		textNome.setBounds(135, 62, 200, 24);

		Label lblTipo = new Label(shellArquivo, SWT.NONE);
		lblTipo.setBounds(25, 105, 105, 20);
		lblTipo.setText("Tipo (ext)");

		textTipo = new Text(shellArquivo, SWT.BORDER);
		textTipo.setBounds(135, 102, 110, 24);

		Label lblTamanho = new Label(shellArquivo, SWT.NONE);
		lblTamanho.setBounds(25, 145, 105, 20);
		lblTamanho.setText("Tamanho (Float)");

		textTamanho = new Text(shellArquivo, SWT.BORDER);
		textTamanho.setBounds(135, 142, 110, 24);

		Label lblStatus = new Label(shellArquivo, SWT.NONE);
		lblStatus.setBounds(25, 185, 105, 20);
		lblStatus.setText("Status");

		textStatus = new Text(shellArquivo, SWT.BORDER);
		textStatus.setBounds(135, 182, 140, 24);

		Label lblData = new Label(shellArquivo, SWT.NONE);
		lblData.setBounds(370, 25, 90, 20);
		lblData.setText("Data Envio");

		dateEnvio = new DateTime(shellArquivo, SWT.BORDER);
		dateEnvio.setBounds(470, 22, 120, 24);

		Label lblConversa = new Label(shellArquivo, SWT.NONE);
		lblConversa.setBounds(370, 65, 90, 20);
		lblConversa.setText("Conversa");

		comboConversa = new Combo(shellArquivo, SWT.READ_ONLY);
		comboConversa.setBounds(470, 62, 180, 24);

		Button btnIncluir = new Button(shellArquivo, SWT.NONE);
		btnIncluir.setBounds(370, 140, 110, 28);
		btnIncluir.setText("Anexar");
		btnIncluir.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (textIdArq.getCharCount() == 0 || textNome.getCharCount() == 0 || textTamanho.getCharCount() == 0) {
					caixaWarning.setMessage("ID, Nome e Tamanho são obrigatórios.");
					caixaWarning.open();
					return;
				}
				if (comboConversa.getSelectionIndex() < 0) {
					caixaWarning.setMessage("Vincule o arquivo a uma Conversa.");
					caixaWarning.open();
					return;
				}

				try {
					Conversa conv = dadosMemoria.consultarConversas().get(comboConversa.getSelectionIndex());
					LocalDate data = LocalDate.of(dateEnvio.getYear(), dateEnvio.getMonth() + 1, dateEnvio.getDay());

					Arquivo arq = new Arquivo(
						Integer.parseInt(textIdArq.getText()), 
						textNome.getText(), 
						textTipo.getText(), 
						Float.parseFloat(textTamanho.getText()), 
						data, 
						textStatus.getText(), 
						conv
					);
					
					dadosMemoria.incluirArquivo(arq);
					
					textIdArq.setText("");
					textNome.setText("");
					textTipo.setText("");
					textTamanho.setText("");
					textStatus.setText("");
					atualizarTudo();
				} catch (Exception erro) {
					caixaWarning.setMessage("Verifique se ID e Tamanho possuem valores numéricos válidos.");
					caixaWarning.open();
				}
			}
		});

		Button btnConsultar = new Button(shellArquivo, SWT.NONE);
		btnConsultar.setBounds(490, 140, 110, 28);
		btnConsultar.setText("Consultar");
		btnConsultar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				atualizarTudo();
			}
		});

		tableArquivos = new Table(shellArquivo, SWT.BORDER | SWT.FULL_SELECTION);
		tableArquivos.setBounds(25, 230, 625, 220);
		tableArquivos.setHeaderVisible(true);
		tableArquivos.setLinesVisible(true);

		TableColumn colId = new TableColumn(tableArquivos, SWT.NONE);
		colId.setWidth(45);
		colId.setText("ID");

		TableColumn colNome = new TableColumn(tableArquivos, SWT.NONE);
		colNome.setWidth(150);
		colNome.setText("Nome");

		TableColumn colTipo = new TableColumn(tableArquivos, SWT.NONE);
		colTipo.setWidth(60);
		colTipo.setText("Tipo");

		TableColumn colTam = new TableColumn(tableArquivos, SWT.NONE);
		colTam.setWidth(75);
		colTam.setText("Tam (KB)");

		TableColumn colData = new TableColumn(tableArquivos, SWT.NONE);
		colData.setWidth(95);
		colData.setText("Envio");

		TableColumn colStatus = new TableColumn(tableArquivos, SWT.NONE);
		colStatus.setWidth(90);
		colStatus.setText("Status");

		TableColumn colConv = new TableColumn(tableArquivos, SWT.NONE);
		colConv.setWidth(100);
		colConv.setText("ID Conversa");

		atualizarTudo();
	}

	private void atualizarTudo() {
		comboConversa.removeAll();
		for (Conversa c : dadosMemoria.consultarConversas()) {
			comboConversa.add("Conversa #" + c.getId());
		}

		tableArquivos.removeAll();
		for (Arquivo a : dadosMemoria.consultarArquivos()) {
			TableItem item = new TableItem(tableArquivos, SWT.NONE);
			item.setText(new String[] {
				String.valueOf(a.getId()),
				a.getNome(),
				a.getTipo(),
				String.valueOf(a.getTamanho()),
				String.valueOf(a.getDataEnvio()),
				a.getStatus(),
				a.getConversa() != null ? String.valueOf(a.getConversa().getId()) : "-"
			});
		}
	}
}