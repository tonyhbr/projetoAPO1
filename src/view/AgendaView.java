package view;

import model.Agenda;
import memoria.DadosMemoria;

import java.time.LocalDate;
import java.time.LocalTime;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;


public class AgendaView {

	protected Shell shell;
	private Text txtId;
    private Text txtData;
    private Text txtHorario;
    private Text txtDisponibilidade;
    private Table tabelaAgendas;
    private DadosMemoria dados = new DadosMemoria();

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AgendaView window = new AgendaView();
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
		shell.setText("Agenda");
		
		Label lblId = new Label(shell, SWT.NONE);
        lblId.setBounds(30, 20, 120, 20);
        lblId.setText("ID:");

        txtId = new Text(shell, SWT.BORDER);
        txtId.setBounds(160, 17, 120, 25);

        Label lblData = new Label(shell, SWT.NONE);
        lblData.setBounds(30, 60, 120, 20);
        lblData.setText("Data (AAAA-MM-DD):");

        txtData = new Text(shell, SWT.BORDER);
        txtData.setBounds(160, 57, 120, 25);

        Label lblHorario = new Label(shell, SWT.NONE);
        lblHorario.setBounds(30, 100, 120, 20);
        lblHorario.setText("Horario (HH:MM):");

        txtHorario = new Text(shell, SWT.BORDER);
        txtHorario.setBounds(160, 97, 120, 25);

        Label lblDisp = new Label(shell, SWT.NONE);
        lblDisp.setBounds(30, 140, 120, 20);
        lblDisp.setText("Disponivel (sim/nao):");

        txtDisponibilidade = new Text(shell, SWT.BORDER);
        txtDisponibilidade.setBounds(160, 137, 120, 25);

        Button btnCadastrar = new Button(shell, SWT.NONE);
        btnCadastrar.setBounds(30, 185, 120, 30);
        btnCadastrar.setText("Cadastrar");
        btnCadastrar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                Integer id = Integer.parseInt(txtId.getText());
                LocalDate data = LocalDate.parse(txtData.getText().trim());
                LocalTime horario = LocalTime.parse(txtHorario.getText().trim());
                boolean disponivel = txtDisponibilidade.getText().trim().equalsIgnoreCase("sim");

                Agenda agenda = new Agenda(id, data, horario, disponivel);
                dados.incluirAgenda(agenda);

                TableItem item = new TableItem(tabelaAgendas, SWT.NONE);
                item.setText(new String[]{
                    String.valueOf(agenda.getId()),
                    agenda.getData().toString(),
                    agenda.getHorario().toString(),
                    agenda.isDisponibilidade() ? "Sim" : "Nao"
                });

                txtId.setText("");
                txtData.setText("");
                txtHorario.setText("");
                txtDisponibilidade.setText("");
            }
        });

        Button btnLimpar = new Button(shell, SWT.NONE);
        btnLimpar.setBounds(170, 185, 120, 30);
        btnLimpar.setText("Limpar");
        btnLimpar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                txtId.setText("");
                txtData.setText("");
                txtHorario.setText("");
                txtDisponibilidade.setText("");
            }
        });

        Label lblTabela = new Label(shell, SWT.NONE);
        lblTabela.setBounds(30, 230, 200, 20);
        lblTabela.setText("Agendas cadastradas:");

        tabelaAgendas = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
        tabelaAgendas.setBounds(30, 255, 430, 170);
        tabelaAgendas.setHeaderVisible(true);
        tabelaAgendas.setLinesVisible(true);

        TableColumn colId = new TableColumn(tabelaAgendas, SWT.NONE);
        colId.setText("ID");
        colId.setWidth(50);

        TableColumn colData = new TableColumn(tabelaAgendas, SWT.NONE);
        colData.setText("Data");
        colData.setWidth(110);

        TableColumn colHorario = new TableColumn(tabelaAgendas, SWT.NONE);
        colHorario.setText("Horario");
        colHorario.setWidth(90);

        TableColumn colDisp = new TableColumn(tabelaAgendas, SWT.NONE);
        colDisp.setText("Disponivel");
        colDisp.setWidth(90);
    }
}
