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
import model.Avaliacao;
import model.TCC;

public class AvaliacaoView {

    protected Shell shell;

    private DadosMemoria dados = new DadosMemoria();

    private Text txtId;
    private Text txtComentario;
    private Text txtNota;
    private DateTime dateAvaliacao;
    private Combo comboTcc;

    private Table tabelaAvaliacoes;

    public static void main(String[] args) {
        try {
            AvaliacaoView window = new AvaliacaoView();
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
        shell.setSize(870, 570);
        shell.setText("Cadastro de Avaliações");

        MessageBox caixaWarning = new MessageBox(shell, SWT.OK);
        caixaWarning.setText("Aviso");

        Label lblId = new Label(shell, SWT.NONE);
        lblId.setBounds(30, 25, 100, 20);
        lblId.setText("ID");

        txtId = new Text(shell, SWT.BORDER);
        txtId.setBounds(140, 22, 100, 24);

        Label lblComentario = new Label(shell, SWT.NONE);
        lblComentario.setBounds(30, 65, 100, 20);
        lblComentario.setText("Comentário");

        txtComentario = new Text(shell, SWT.BORDER);
        txtComentario.setBounds(140, 62, 260, 24);

        Label lblNota = new Label(shell, SWT.NONE);
        lblNota.setBounds(30, 105, 100, 20);
        lblNota.setText("Nota");

        txtNota = new Text(shell, SWT.BORDER);
        txtNota.setBounds(140, 102, 100, 24);

        Label lblData = new Label(shell, SWT.NONE);
        lblData.setBounds(30, 145, 100, 20);
        lblData.setText("Data");

        dateAvaliacao = new DateTime(shell, SWT.BORDER);
        dateAvaliacao.setBounds(140, 142, 120, 24);

        Label lblTcc = new Label(shell, SWT.NONE);
        lblTcc.setBounds(440, 25, 100, 20);
        lblTcc.setText("TCC");

        comboTcc = new Combo(shell, SWT.READ_ONLY);
        comboTcc.setBounds(520, 22, 280, 24);

        Button btnIncluir = new Button(shell, SWT.NONE);
        btnIncluir.setBounds(440, 90, 110, 30);
        btnIncluir.setText("Incluir");

        btnIncluir.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {

                if (txtId.getText().isEmpty()
                        || txtComentario.getText().isEmpty()
                        || txtNota.getText().isEmpty()) {

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

                    Float nota = Float.parseFloat(txtNota.getText());

                    LocalDate data = LocalDate.of(
                            dateAvaliacao.getYear(),
                            dateAvaliacao.getMonth() + 1,
                            dateAvaliacao.getDay());

                    TCC tcc = dados.consultarTccs()
                            .get(comboTcc.getSelectionIndex());

                    Avaliacao avaliacao = new Avaliacao(
                            id,
                            txtComentario.getText(),
                            nota,
                            data,
                            tcc);

                    dados.incluirAvaliacao(avaliacao);

                    txtId.setText("");
                    txtComentario.setText("");
                    txtNota.setText("");

                    atualizarTudo();

                } catch (Exception ex) {

                    caixaWarning.setMessage("Dados inválidos.");
                    caixaWarning.open();

                }

            }
        });

        Button btnConsultar = new Button(shell, SWT.NONE);
        btnConsultar.setBounds(570, 90, 110, 30);
        btnConsultar.setText("Consultar");

        btnConsultar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                atualizarTudo();
            }
        });

        Button btnLimpar = new Button(shell, SWT.NONE);
        btnLimpar.setBounds(690, 90, 110, 30);
        btnLimpar.setText("Limpar");

        btnLimpar.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {

                txtId.setText("");
                txtComentario.setText("");
                txtNota.setText("");
                comboTcc.deselectAll();

            }
        });

        tabelaAvaliacoes = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
        tabelaAvaliacoes.setBounds(30, 220, 790, 270);
        tabelaAvaliacoes.setHeaderVisible(true);
        tabelaAvaliacoes.setLinesVisible(true);

        TableColumn colId = new TableColumn(tabelaAvaliacoes, SWT.NONE);
        colId.setWidth(60);
        colId.setText("ID");

        TableColumn colComentario = new TableColumn(tabelaAvaliacoes, SWT.NONE);
        colComentario.setWidth(280);
        colComentario.setText("Comentário");

        TableColumn colNota = new TableColumn(tabelaAvaliacoes, SWT.NONE);
        colNota.setWidth(80);
        colNota.setText("Nota");

        TableColumn colData = new TableColumn(tabelaAvaliacoes, SWT.NONE);
        colData.setWidth(110);
        colData.setText("Data");

        TableColumn colTcc = new TableColumn(tabelaAvaliacoes, SWT.NONE);
        colTcc.setWidth(240);
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

        tabelaAvaliacoes.removeAll();

        for (Avaliacao avaliacao : dados.consultarAvaliacoes()) {

            TableItem item = new TableItem(tabelaAvaliacoes, SWT.NONE);

            item.setText(new String[] {
                    String.valueOf(avaliacao.getId()),
                    avaliacao.getComentario(),
                    String.valueOf(avaliacao.getNota()),
                    avaliacao.getData().toString(),
                    avaliacao.getTcc().getTitulo()
            });

        }

    }

}