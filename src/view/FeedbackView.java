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
import model.Feedback;
import model.TCC;

public class FeedbackView {

    protected Shell shell;

    private DadosMemoria dados = new DadosMemoria();

    private Text txtId;
    private Text txtTexto;
    private Text txtAutor;
    private DateTime dateFeedback;
    private Combo comboTcc;

    private Table tabelaFeedbacks;

    public static void main(String[] args) {
        try {
            FeedbackView window = new FeedbackView();
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
        shell.setSize(880, 580);
        shell.setText("Cadastro de Feedback");

        MessageBox caixaWarning = new MessageBox(shell, SWT.OK);
        caixaWarning.setText("Aviso");

        Label lblId = new Label(shell, SWT.NONE);
        lblId.setBounds(30, 25, 100, 20);
        lblId.setText("ID");

        txtId = new Text(shell, SWT.BORDER);
        txtId.setBounds(140, 22, 100, 24);

        Label lblTexto = new Label(shell, SWT.NONE);
        lblTexto.setBounds(30, 65, 100, 20);
        lblTexto.setText("Texto");

        txtTexto = new Text(shell, SWT.BORDER);
        txtTexto.setBounds(140, 62, 300, 24);

        Label lblAutor = new Label(shell, SWT.NONE);
        lblAutor.setBounds(30, 105, 100, 20);
        lblAutor.setText("Autor");

        txtAutor = new Text(shell, SWT.BORDER);
        txtAutor.setBounds(140, 102, 220, 24);

        Label lblData = new Label(shell, SWT.NONE);
        lblData.setBounds(30, 145, 100, 20);
        lblData.setText("Data");

        dateFeedback = new DateTime(shell, SWT.BORDER);
        dateFeedback.setBounds(140, 142, 120, 24);

        Label lblTcc = new Label(shell, SWT.NONE);
        lblTcc.setBounds(470, 25, 100, 20);
        lblTcc.setText("TCC");

        comboTcc = new Combo(shell, SWT.READ_ONLY);
        comboTcc.setBounds(540, 22, 280, 24);

        Button btnIncluir = new Button(shell, SWT.NONE);
        btnIncluir.setBounds(470, 90, 110, 30);
        btnIncluir.setText("Incluir");

        btnIncluir.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {

                if (txtId.getText().isEmpty()
                        || txtTexto.getText().isEmpty()
                        || txtAutor.getText().isEmpty()) {

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

                    LocalDate data = LocalDate.of(
                            dateFeedback.getYear(),
                            dateFeedback.getMonth() + 1,
                            dateFeedback.getDay());

                    TCC tcc = dados.consultarTccs()
                            .get(comboTcc.getSelectionIndex());

                    Feedback feedback = new Feedback(
                            id,
                            txtTexto.getText(),
                            txtAutor.getText(),
                            data,
                            tcc);

                    dados.incluirFeedback(feedback);

                    txtId.setText("");
                    txtTexto.setText("");
                    txtAutor.setText("");

                    atualizarTudo();

                } catch (Exception ex) {

                    caixaWarning.setMessage("Dados inválidos.");
                    caixaWarning.open();

                }

            }

        });

        Button btnConsultar = new Button(shell, SWT.NONE);
        btnConsultar.setBounds(600, 90, 110, 30);
        btnConsultar.setText("Consultar");

        btnConsultar.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                atualizarTudo();
            }

        });

        Button btnLimpar = new Button(shell, SWT.NONE);
        btnLimpar.setBounds(730, 90, 110, 30);
        btnLimpar.setText("Limpar");

        btnLimpar.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {

                txtId.setText("");
                txtTexto.setText("");
                txtAutor.setText("");
                comboTcc.deselectAll();

            }

        });

        tabelaFeedbacks = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
        tabelaFeedbacks.setBounds(30, 230, 810, 270);
        tabelaFeedbacks.setHeaderVisible(true);
        tabelaFeedbacks.setLinesVisible(true);

        TableColumn colId = new TableColumn(tabelaFeedbacks, SWT.NONE);
        colId.setWidth(60);
        colId.setText("ID");

        TableColumn colTexto = new TableColumn(tabelaFeedbacks, SWT.NONE);
        colTexto.setWidth(300);
        colTexto.setText("Texto");

        TableColumn colAutor = new TableColumn(tabelaFeedbacks, SWT.NONE);
        colAutor.setWidth(150);
        colAutor.setText("Autor");

        TableColumn colData = new TableColumn(tabelaFeedbacks, SWT.NONE);
        colData.setWidth(110);
        colData.setText("Data");

        TableColumn colTcc = new TableColumn(tabelaFeedbacks, SWT.NONE);
        colTcc.setWidth(170);
        colTcc.setText("TCC");

        atualizarTudo();

    }

    private void atualizarTudo() {
        atualizarComboTcc();
        atualizarTabela();
    }

    private void atualizarComboTcc() {

        comboTcc.removeAll();

        for (TCC tcc : dados.consultarTccs()) {
            comboTcc.add(tcc.getId() + " - " + tcc.getTitulo());
        }

    }

    private void atualizarTabela() {

        tabelaFeedbacks.removeAll();

        for (Feedback feedback : dados.consultarFeedbacks()) {

            TableItem item = new TableItem(tabelaFeedbacks, SWT.NONE);

            item.setText(new String[] {
                    String.valueOf(feedback.getId()),
                    feedback.getTexto(),
                    feedback.getAutor(),
                    feedback.getData().toString(),
                    feedback.getTcc().getTitulo()
            });

        }

    }

}