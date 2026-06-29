package view;

import java.time.LocalDateTime;
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
import model.Mensagem;

public class MensagemView {

	protected Shell shellMensagem;
	private DadosMemoria dadosMemoria = new DadosMemoria();

	private Text textIdMsg;
	private Text textTexto;
	private Text textRemetente;
	private DateTime dateEnvio;
	private Combo comboConversa;
	private Table tableMensagens;

	public static void main(String[] args) {
		try {
			MensagemView window = new MensagemView();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void open() {
		Display display = Display.getDefault();
		createContents();
		shellMensagem.open();
		shellMensagem.layout();
		while (!shellMensagem.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	protected void createContents() {
		shellMensagem = new Shell();
		shellMensagem.setSize(650, 520);
		shellMensagem.setText("Histórico de Mensagens");

		MessageBox caixaWarning = new MessageBox(shellMensagem, SWT.OK);
		caixaWarning.setText("Aviso");

		Label lblId = new Label(shellMensagem, SWT.NONE);
		lblId.setBounds(25, 25, 105, 20);
		lblId.setText("ID Mensagem");

		textIdMsg = new Text(shellMensagem, SWT.BORDER);
		textIdMsg.setBounds(135, 22, 110, 24);

		Label lblTexto = new Label(shellMensagem, SWT.NONE);
		lblTexto.setBounds(25, 65, 105, 20);
		lblTexto.setText("Texto");

		textTexto = new Text(shellMensagem, SWT.BORDER);
		textTexto.setBounds(135, 62, 250, 24);

		Label lblRemetente = new Label(shellMensagem, SWT.NONE);
		lblRemetente.setBounds(25, 105, 105, 20);
		lblRemetente.setText("Remetente");

		textRemetente = new Text(shellMensagem, SWT.BORDER);
		textRemetente.setBounds(135, 102, 160, 24);

		Label lblData = new Label(shellMensagem, SWT.NONE);
		lblData.setBounds(25, 145, 105, 20);
		lblData.setText("Data Envio");

		dateEnvio = new DateTime(shellMensagem, SWT.BORDER);
		dateEnvio.setBounds(135, 142, 120, 24);

		Label lblConversa = new Label(shellMensagem, SWT.NONE);
		lblConversa.setBounds(410, 25, 80, 20);
		lblConversa.setText("Conversa");

		comboConversa = new Combo(shellMensagem, SWT.READ_ONLY);
		comboConversa.setBounds(410, 55, 200, 24);

		Button btnIncluir = new Button(shellMensagem, SWT.NONE);
		btnIncluir.setBounds(410, 100, 100, 28);
		btnIncluir.setText("Enviar");
		btnIncluir.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (textIdMsg.getCharCount() == 0 || textTexto.getCharCount() == 0 || textRemetente.getCharCount() == 0) {
					caixaWarning.setMessage("Preencha todos os campos da mensagem.");
					caixaWarning.open();
					return;
				}
				if (comboConversa.getSelectionIndex() < 0) {
					caixaWarning.setMessage("Selecione uma Conversa ativa.");
					caixaWarning.open();
					return;
				}

				try {
					Conversa conv = dadosMemoria.consultarConversas().get(comboConversa.getSelectionIndex());
					LocalDateTime dataHora = LocalDateTime.of(dateEnvio.getYear(), dateEnvio.getMonth() + 1, dateEnvio.getDay(), 0, 0);

					Mensagem msg = new Mensagem(Integer.parseInt(textIdMsg.getText()), textTexto.getText(), dataHora, textRemetente.getText(), conv);
					dadosMemoria.incluirMensagem(msg);

					textIdMsg.setText("");
					textTexto.setText("");
					textRemetente.setText("");
					atualizarTudo();
				} catch (Exception erro) {
					caixaWarning.setMessage("O ID da mensagem deve ser numérico.");
					caixaWarning.open();
				}
			}
		});

		Button btnConsultar = new Button(shellMensagem, SWT.NONE);
		btnConsultar.setBounds(515, 100, 95, 28);
		btnConsultar.setText("Consultar");
		btnConsultar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				atualizarTudo();
			}
		});

		tableMensagens = new Table(shellMensagem, SWT.BORDER | SWT.FULL_SELECTION);
		tableMensagens.setBounds(25, 200, 585, 250);
		tableMensagens.setHeaderVisible(true);
		tableMensagens.setLinesVisible(true);

		TableColumn colId = new TableColumn(tableMensagens, SWT.NONE);
		colId.setWidth(50);
		colId.setText("ID");

		TableColumn colRemetente = new TableColumn(tableMensagens, SWT.NONE);
		colRemetente.setWidth(100);
		colRemetente.setText("Remetente");

		TableColumn colTexto = new TableColumn(tableMensagens, SWT.NONE);
		colTexto.setWidth(200);
		colTexto.setText("Texto");

		TableColumn colData = new TableColumn(tableMensagens, SWT.NONE);
		colData.setWidth(110);
		colData.setText("Data/Hora");

		TableColumn colConv = new TableColumn(tableMensagens, SWT.NONE);
		colConv.setWidth(120);
		colConv.setText("ID Conversa");

		atualizarTudo();
	}

	private void atualizarTudo() {
		comboConversa.removeAll();
		for (Conversa c : dadosMemoria.consultarConversas()) {
			comboConversa.add("Conversa #" + c.getId());
		}

		tableMensagens.removeAll();
		for (Mensagem m : dadosMemoria.consultarMensagens()) {
			TableItem item = new TableItem(tableMensagens, SWT.NONE);
			item.setText(new String[] { 
				String.valueOf(m.getId()), 
				m.getRemetente(), 
				m.getTexto(), 
				String.valueOf(m.getDataHora()),
				m.getConversa() != null ? String.valueOf(m.getConversa().getId()) : "-" 
			});
		}
	}
}