package view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import model.Usuario;
import model.Urna;
import model.Voto;

public class usuarioView {

	protected Shell shell;
	private Text textId;
	private Text textNome;
	private Text textNomeInserido;
	private Text textIdInserido;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			usuarioView window = new usuarioView();
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
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		textId = new Text(shell, SWT.BORDER);
		textId.setBounds(173, 10, 83, 30);
		
		textNome = new Text(shell, SWT.BORDER);
		textNome.setBounds(173, 51, 142, 30);
		
		Label lblId = new Label(shell, SWT.NONE);
		lblId.setBounds(10, 16, 70, 17);
		lblId.setText("Id");
		
		Label lblNome = new Label(shell, SWT.NONE);
		lblNome.setBounds(10, 57, 142, 17);
		lblNome.setText("Nome");
		
		Button btnInserir = new Button(shell, SWT.NONE);
		btnInserir.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Usuario usu = new Usuario();
				// usu.setIdUsuario(Integer.parseInt(textId.getText()));
				// usu.setNome(textNome.getText());
				textIdInserido.setText(String.valueOf(usu.getIdUsuario()));
				textNomeInserido.setText(usu.getNome());
			}
		});
		btnInserir.setBounds(334, 222, 89, 34);
		btnInserir.setText("Inserir");
		
		textNomeInserido = new Text(shell, SWT.BORDER);
		textNomeInserido.setBounds(173, 108, 83, 30);
		
		textIdInserido = new Text(shell, SWT.BORDER);
		textIdInserido.setBounds(173, 164, 142, 30);
		
		Label lblNomeInserido = new Label(shell, SWT.NONE);
		lblNomeInserido.setBounds(10, 121, 70, 17);
		lblNomeInserido.setText("Nome Inserido");
		
		Label lblIdInserido = new Label(shell, SWT.NONE);
		lblIdInserido.setBounds(10, 170, 70, 17);
		lblIdInserido.setText("Id Inserido");
		
		Button btnAbrirUrna = new Button(shell, SWT.NONE);
		btnAbrirUrna.setBounds(226, 222, 89, 34);
		btnAbrirUrna.setText("Abrir Urna");
		btnAbrirUrna.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Urna urna = new Urna();
					}
		});			
		
		Button btnInserirAluno = new Button(shell, SWT.NONE);
		btnInserirAluno.setBounds(113, 222, 89, 34);
		btnInserirAluno.setText("BtnAluno");
		btnInserirAluno = new Button(shell, SWT.NONE);
		
				


	}
}
