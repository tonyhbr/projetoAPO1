package view;

import java.time.LocalDate;

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

import memoria.DadosMemoria;
import model.TCC;
import model.VersaoTCC;

public class VersaoTCCView {

    protected Shell shell;

    private Text txtId;
    private Text txtDescricao;
    private Text txtDataEnvio;
    private Combo comboTcc;

    private Table tabelaVersoes;

    private DadosMemoria dados = new DadosMemoria();

    public static void main(String[] args) {
        try {
            VersaoTCCView window = new VersaoTCCView();
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
        shell.setSize(850, 570);
        shell.setText("Versões do TCC");

        MessageBox caixaWarning = new MessageBox(shell, SWT.OK);
        caixaWarning.setText("Aviso");

        Label lblId = new Label(shell, SWT.NONE);
        lblId.setBounds(30, 20, 120, 20);
        lblId.setText("ID:");

        txtId = new Text(shell, SWT.BORDER);
        txtId.setBounds(160, 17, 120, 25);

        Label lblDescricao = new Label(shell, SWT.NONE);
        lblDescricao.setBounds(30, 60, 120, 20);
        lblDescricao.setText("Descrição:");

        txtDescricao = new Text(shell, SWT.BORDER);
        txtDescricao.setBounds(160, 57, 280, 25);

        Label lblData = new Label(shell, SWT.NONE);
        lblData.setBounds(30, 100, 120, 20);
        lblData.setText("Data Envio:");

        txtDataEnvio = new Text(shell, SWT.BORDER);
        txtDataEnvio.setBounds(160, 97, 120, 25);

        Label lblTcc = new Label(shell, SWT.NONE);
        lblTcc.setBounds(30, 140, 120, 20);
        lblTcc.setText("TCC:");

        comboTcc = new Combo(shell, SWT.READ_ONLY);
        comboTcc.setBounds(160, 137, 320, 25);

        Button btnCadastrar = new Button(shell, SWT.NONE);
        btnCadastrar.setBounds(30, 185, 120, 30);
        btnCadastrar.setText("Cadastrar");

        btnCadastrar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {

                if (txtId.getText().isEmpty()
                        || txtDescricao.getText().isEmpty()
                        || txtDataEnvio.getText().isEmpty()) {

                    caixaWarning.setMessage("Preencha todos os campos.");
                    caixaWarning.open();
                    return;
                }

                if (comboTcc.getSelectionIndex() < 0) {
                    caixaWarning.setMessage("Selecione um TCC.");
                    caixaWarning.open();
                    return;
                }

                try {

                    Integer id = Integer.parseInt(txtId.getText());

                    String descricao = txtDescricao.getText();

                    LocalDate dataEnvio =
                            LocalDate.parse(txtDataEnvio.getText());

                    TCC tcc =
                            dados.consultarTccs().get(comboTcc.getSelectionIndex());

                    VersaoTCC versao =
                            new VersaoTCC(id, descricao, dataEnvio, tcc);

                    dados.incluirVersaoTCC(versao);

                    txtId.setText("");
                    txtDescricao.setText("");
                    txtDataEnvio.setText("");

                    atualizarTudo();

                } catch (Exception ex) {

                    caixaWarning.setMessage("Dados inválidos.");
                    caixaWarning.open();

                }

            }
        });

        Button btnConsultar = new Button(shell, SWT.NONE);
        btnConsultar.setBounds(170, 185, 120, 30);
        btnConsultar.setText("Consultar");

        btnConsultar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                atualizarTudo();
            }
        });

        Button btnLimpar = new Button(shell, SWT.NONE);
        btnLimpar.setBounds(310, 185, 120, 30);
        btnLimpar.setText("Limpar");

        btnLimpar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {

                txtId.setText("");
                txtDescricao.setText("");
                txtDataEnvio.setText("");
                comboTcc.deselectAll();

            }
        });

        tabelaVersoes = new Table(shell,
                SWT.BORDER | SWT.FULL_SELECTION);

        tabelaVersoes.setBounds(30, 245, 760, 230);

        tabelaVersoes.setHeaderVisible(true);
        tabelaVersoes.setLinesVisible(true);

        TableColumn colId = new TableColumn(tabelaVersoes, SWT.NONE);
        colId.setText("ID");
        colId.setWidth(60);

        TableColumn colDescricao =
                new TableColumn(tabelaVersoes, SWT.NONE);
        colDescricao.setText("Descrição");
        colDescricao.setWidth(300);

        TableColumn colData =
                new TableColumn(tabelaVersoes, SWT.NONE);
        colData.setText("Data Envio");
        colData.setWidth(120);

        TableColumn colTcc =
                new TableColumn(tabelaVersoes, SWT.NONE);
        colTcc.setText("TCC");
        colTcc.setWidth(260);

        atualizarTudo();
    }

    private void atualizarTudo() {

        atualizarComboTcc();
        atualizarTabela();

    }

    private void atualizarComboTcc() {

        comboTcc.removeAll();

        for (TCC tcc : dados.consultarTccs()) {

            comboTcc.add(
                    tcc.getId() + " - " + tcc.getTitulo());

        }

    }

    private void atualizarTabela() {

        tabelaVersoes.removeAll();

        for (VersaoTCC versao : dados.consultarVersoesTCC()) {

            TableItem item =
                    new TableItem(tabelaVersoes, SWT.NONE);

            item.setText(new String[] {

                    String.valueOf(versao.getId()),
                    versao.getDescricao(),
                    versao.getDataEnvio().toString(),
                    versao.getTcc().getTitulo()

            });

        }

    }

}