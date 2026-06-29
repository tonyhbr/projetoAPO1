package view;

import java.util.ArrayList;
import java.util.List;

import memoria.DadosMemoria;
import model.Agenda;
import model.Reuniao;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Shell;

public class ReuniaoView {

	protected Shell shell;
	private Text txtId;
    private Text txtPauta;
    private Text txtLink;
    private Combo cmbAgenda;
    private Table tabelaReunioes;
    private DadosMemoria dados = new DadosMemoria();
    private List<Agenda> agendasDisponiveis = new ArrayList<>();

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ReuniaoView window = new ReuniaoView();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(830, 570);
		shell.setText("Reuniao");
		
		Label lblId = new Label(shell, SWT.NONE);
        lblId.setBounds(30, 20, 120, 20);
        lblId.setText("ID:");

        txtId = new Text(shell, SWT.BORDER);
        txtId.setBounds(160, 17, 120, 25);

        Label lblAgenda = new Label(shell, SWT.NONE);
        lblAgenda.setBounds(30, 60, 120, 20);
        lblAgenda.setText("Agenda:");

        cmbAgenda = new Combo(shell, SWT.DROP_DOWN | SWT.READ_ONLY);
        cmbAgenda.setBounds(160, 57, 220, 25);

        Button btnCarregar = new Button(shell, SWT.NONE);
        btnCarregar.setBounds(390, 57, 100, 25);
        btnCarregar.setText("Carregar");
        btnCarregar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                cmbAgenda.removeAll();
                agendasDisponiveis.clear();

                for (Agenda ag : dados.consultarAgendas()) {
                    if (ag.isDisponibilidade()) {
                        agendasDisponiveis.add(ag);
                        cmbAgenda.add("ID " + ag.getId() + " | " + ag.getData() + " " + ag.getHorario());
                    }
                }
            }
        });

        Label lblPauta = new Label(shell, SWT.NONE);
        lblPauta.setBounds(30, 100, 120, 20);
        lblPauta.setText("Pauta:");

        txtPauta = new Text(shell, SWT.BORDER);
        txtPauta.setBounds(160, 97, 220, 25);

        Label lblLink = new Label(shell, SWT.NONE);
        lblLink.setBounds(30, 140, 120, 20);
        lblLink.setText("Link Online:");

        txtLink = new Text(shell, SWT.BORDER);
        txtLink.setBounds(160, 137, 220, 25);

        Button btnAgendar = new Button(shell, SWT.NONE);
        btnAgendar.setBounds(30, 185, 120, 30);
        btnAgendar.setText("Agendar");
        btnAgendar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            	
                int idx = cmbAgenda.getSelectionIndex();

                if (idx < 0) {
                    MessageBox msg = new MessageBox(shell, SWT.ICON_WARNING);
                    msg.setMessage("Selecione uma agenda.");
                    msg.open();
                    return;
                }

                Agenda agendaSelecionada = agendasDisponiveis.get(idx);

                Reuniao reuniao = new Reuniao(
                    Integer.parseInt(txtId.getText()),
                    agendaSelecionada.getData(),
                    agendaSelecionada.getHorario(),
                    "Agendada",
                    txtPauta.getText(),
                    txtLink.getText()
                );

                reuniao.setAgenda(agendaSelecionada);
                dados.incluirReuniao(reuniao);

                TableItem item = new TableItem(tabelaReunioes, SWT.NONE);
                item.setText(new String[]{
                    String.valueOf(reuniao.getId()),
                    reuniao.getData().toString(),
                    reuniao.getHorario().toString(),
                    reuniao.getPauta(),
                    reuniao.getLinkOnline(),
                    reuniao.getStatus()
                });

                txtId.setText("");
                txtPauta.setText("");
                txtLink.setText("");
                cmbAgenda.removeAll();
                agendasDisponiveis.clear();
            }
        });

        Button btnLimpar = new Button(shell, SWT.NONE);
        btnLimpar.setBounds(170, 185, 120, 30);
        btnLimpar.setText("Limpar");
        btnLimpar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                txtId.setText("");
                txtPauta.setText("");
                txtLink.setText("");
                cmbAgenda.deselectAll();
            }
        });

        Label lblTabela = new Label(shell, SWT.NONE);
        lblTabela.setBounds(30, 235, 200, 20);
        lblTabela.setText("Reunioes agendadas:");

        tabelaReunioes = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
        tabelaReunioes.setBounds(30, 260, 480, 250);
        tabelaReunioes.setHeaderVisible(true);
        tabelaReunioes.setLinesVisible(true);

        TableColumn colId = new TableColumn(tabelaReunioes, SWT.NONE);
        colId.setText("ID");
        colId.setWidth(40);

        TableColumn colData = new TableColumn(tabelaReunioes, SWT.NONE);
        colData.setText("Data");
        colData.setWidth(100);

        TableColumn colHorario = new TableColumn(tabelaReunioes, SWT.NONE);
        colHorario.setText("Horario");
        colHorario.setWidth(75);

        TableColumn colPauta = new TableColumn(tabelaReunioes, SWT.NONE);
        colPauta.setText("Pauta");
        colPauta.setWidth(120);

        TableColumn colLink = new TableColumn(tabelaReunioes, SWT.NONE);
        colLink.setText("Link");
        colLink.setWidth(80);

        TableColumn colStatus = new TableColumn(tabelaReunioes, SWT.NONE);
        colStatus.setText("Status");
        colStatus.setWidth(75);
	}

}
