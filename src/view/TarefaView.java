package view;

import model.Tarefa;
import memoria.DadosMemoria;

import java.time.LocalDateTime;

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

public class TarefaView {

    protected Shell shell;

    private Text txtId;
    private Text txtTitulo;
    private Text txtDescricao;
    private Text txtStatus;
    private Text txtPrazo;

    private Table tabelaTarefas;

    private DadosMemoria dados = new DadosMemoria();

    public static void main(String[] args) {
        try {
            TarefaView window = new TarefaView();
            window.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    protected void createContents() {

        shell = new Shell();
        shell.setSize(900, 600);
        shell.setText("Tarefa");

        Label lblId = new Label(shell, SWT.NONE);
        lblId.setBounds(30, 20, 120, 20);
        lblId.setText("ID:");

        txtId = new Text(shell, SWT.BORDER);
        txtId.setBounds(160, 17, 150, 25);

        Label lblTitulo = new Label(shell, SWT.NONE);
        lblTitulo.setBounds(30, 60, 120, 20);
        lblTitulo.setText("Título:");

        txtTitulo = new Text(shell, SWT.BORDER);
        txtTitulo.setBounds(160, 57, 300, 25);

        Label lblDescricao = new Label(shell, SWT.NONE);
        lblDescricao.setBounds(30, 100, 120, 20);
        lblDescricao.setText("Descrição:");

        txtDescricao = new Text(shell, SWT.BORDER);
        txtDescricao.setBounds(160, 97, 400, 25);

        Label lblStatus = new Label(shell, SWT.NONE);
        lblStatus.setBounds(30, 140, 120, 20);
        lblStatus.setText("Status:");

        txtStatus = new Text(shell, SWT.BORDER);
        txtStatus.setBounds(160, 137, 200, 25);

        Label lblPrazo = new Label(shell, SWT.NONE);
        lblPrazo.setBounds(30, 180, 120, 20);
        lblPrazo.setText("Prazo:");

        txtPrazo = new Text(shell, SWT.BORDER);
        txtPrazo.setBounds(160, 177, 220, 25);
        txtPrazo.setMessage("AAAA-MM-DDTHH:MM");

        Button btnCadastrar = new Button(shell, SWT.NONE);
        btnCadastrar.setBounds(30, 225, 120, 30);
        btnCadastrar.setText("Cadastrar");

        btnCadastrar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {

                Integer id = Integer.parseInt(txtId.getText());
                String titulo = txtTitulo.getText();
                String descricao = txtDescricao.getText();
                String status = txtStatus.getText();
                LocalDateTime prazo = LocalDateTime.parse(txtPrazo.getText().trim());

                Tarefa tarefa = new Tarefa(id, titulo, descricao, status, prazo);

                dados.incluirTarefa(tarefa);

                TableItem item = new TableItem(tabelaTarefas, SWT.NONE);

                item.setText(new String[] {
                        String.valueOf(tarefa.getId()),
                        tarefa.getTitulo(),
                        tarefa.getDescricao(),
                        tarefa.getStatus(),
                        tarefa.getPrazo().toString()
                });

                txtId.setText("");
                txtTitulo.setText("");
                txtDescricao.setText("");
                txtStatus.setText("");
                txtPrazo.setText("");
            }
        });

        Button btnLimpar = new Button(shell, SWT.NONE);
        btnLimpar.setBounds(170, 225, 120, 30);
        btnLimpar.setText("Limpar");

        btnLimpar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {

                txtId.setText("");
                txtTitulo.setText("");
                txtDescricao.setText("");
                txtStatus.setText("");
                txtPrazo.setText("");
            }
        });

        Label lblTabela = new Label(shell, SWT.NONE);
        lblTabela.setBounds(30, 270, 220, 20);
        lblTabela.setText("Tarefas cadastradas:");

        tabelaTarefas = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
        tabelaTarefas.setBounds(30, 295, 820, 220);
        tabelaTarefas.setHeaderVisible(true);
        tabelaTarefas.setLinesVisible(true);

        TableColumn colId = new TableColumn(tabelaTarefas, SWT.NONE);
        colId.setText("ID");
        colId.setWidth(60);

        TableColumn colTitulo = new TableColumn(tabelaTarefas, SWT.NONE);
        colTitulo.setText("Título");
        colTitulo.setWidth(160);

        TableColumn colDescricao = new TableColumn(tabelaTarefas, SWT.NONE);
        colDescricao.setText("Descrição");
        colDescricao.setWidth(250);

        TableColumn colStatus = new TableColumn(tabelaTarefas, SWT.NONE);
        colStatus.setText("Status");
        colStatus.setWidth(120);

        TableColumn colPrazo = new TableColumn(tabelaTarefas, SWT.NONE);
        colPrazo.setText("Prazo");
        colPrazo.setWidth(200);
    }
}