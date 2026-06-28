package view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import model.Aluno;

public class alunoView {

	protected Shell shell;
	private Text textIdEleitor;
	private Text textNomeEleitor;
	private Text textIdAluno;
	private Text textNomeAluno;
	private Text textNumProntuario;
	private Text textCurso;
	private Text textIdEleitorExibido;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			alunoView window = new alunoView();
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
		shell.setSize(589, 521);
		shell.setText("SWT Application");
		
		Label lblNomeEleitor = new Label(shell, SWT.NONE);
		lblNomeEleitor.setBounds(24, 52, 70, 17);
		lblNomeEleitor.setText("Nome Eleitor");
		
		Label lblIdEleitor = new Label(shell, SWT.NONE);
		lblIdEleitor.setBounds(24, 10, 70, 17);
		lblIdEleitor.setText("Id Eleitor");
		
		Label lblIdAluno = new Label(shell, SWT.NONE);
		lblIdAluno.setBounds(24, 100, 70, 17);
		lblIdAluno.setText("Id Aluno");
		
		Label lblNomeAluno = new Label(shell, SWT.NONE);
		lblNomeAluno.setBounds(24, 149, 70, 17);
		lblNomeAluno.setText("Nome Aluno");
		
		textIdEleitor = new Text(shell, SWT.BORDER);
		textIdEleitor.setBounds(191, 10, 83, 30);
		
		textNomeEleitor = new Text(shell, SWT.BORDER);
		textNomeEleitor.setBounds(191, 46, 83, 30);
		
		textIdAluno = new Text(shell, SWT.BORDER);
		textIdAluno.setBounds(191, 94, 83, 30);
		
		textNomeAluno = new Text(shell, SWT.BORDER);
		textNomeAluno.setBounds(191, 136, 83, 30);
		
		Button btnInserirAluno = new Button(shell, SWT.NONE);
		btnInserirAluno.setBounds(461, 399, 104, 34);
		btnInserirAluno.setText("Inserir Aluno");
		btnInserirAluno.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				
				Aluno aluno = new Aluno(Integer.parseInt(textIdEleitor.getText()), 
						textNomeAluno.getText(), Integer.parseInt(textIdAluno.getText()), 
						Integer.parseInt(textNumProntuario.getText()), 
						textCurso.getText());
				
				textIdEleitorExibido.setText(String.valueOf(aluno.getIdEleitor()));
			}
		});
		
		Label lblNumProntuario = new Label(shell, SWT.NONE);
		lblNumProntuario.setBounds(24, 200, 70, 17);
		lblNumProntuario.setText("Prontuário");
		
		textNumProntuario = new Text(shell, SWT.BORDER);
		textNumProntuario.setBounds(191, 200, 83, 30);
		
		textCurso = new Text(shell, SWT.BORDER);
		textCurso.setBounds(191, 258, 83, 30);
		
		Label lblCurso = new Label(shell, SWT.NONE);
		lblCurso.setBounds(24, 258, 70, 17);
		lblCurso.setText("Curso");
		
		textIdEleitorExibido = new Text(shell, SWT.BORDER);
		textIdEleitorExibido.setBounds(461, 10, 83, 30);
		
		Label lblIdEleitorExibido = new Label(shell, SWT.NONE);
		lblIdEleitorExibido.setBounds(353, 23, 70, 17);
		lblIdEleitorExibido.setText("New Label");

	}
}
