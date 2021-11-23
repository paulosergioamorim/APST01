package ciu.cadastros;

import cci.ControladorPrincipal;
import cdp.Aluno;
import cdp.Turma;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;

import static cgt.AplGerenciarCurso.getTurmasVagas;
import static cgt.AplGerenciarPessoa.getAlunos;

public class JanCadMatricula extends JFrame {
	private final ControladorPrincipal controlador;

	private JPanel           Panel;
	private JButton          Matricular;
	private JComboBox<Aluno> Aluno;
	private JComboBox<Turma> Turma;
	private JLabel           Vagas;

	{
		// GUI initializer generated by IntelliJ IDEA GUI Designer
		// >>> IMPORTANT!! <<<
		// DO NOT EDIT OR ADD ANY CODE HERE!
		$$$setupUI$$$();
	}

	public JanCadMatricula(ControladorPrincipal controlador) {
		super("Cadastro de Matricula");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setContentPane(Panel);
		this.setResizable(false);
		this.setSize(400, 200);
		this.setLocationRelativeTo(null);

		this.controlador = controlador;

		ImageIcon icon = new ImageIcon("src/main/resources/images/icon.png");
		this.setIconImage(icon.getImage());

		DefaultComboBoxModel<Aluno> modelAluno = new DefaultComboBoxModel<>();
		modelAluno.addAll(getAlunos());
		Aluno.setModel(modelAluno);

		DefaultComboBoxModel<Turma> modelTurma = new DefaultComboBoxModel<>();
		modelTurma.addAll(getTurmasVagas());
		Turma.setModel(modelTurma);

		Turma.addActionListener(e -> updateTurmas());
		Matricular.addActionListener(e -> {
			if (Turma.getSelectedItem() != null && Aluno.getSelectedItem() != null) {
				Aluno aluno = (Aluno) Aluno.getSelectedItem();
				Turma turma = (Turma) Turma.getSelectedItem();
				controlador.efetuarMatricula(aluno, turma);
				this.updateTurmas();
			}
		});
	}

	public void updateTurmas() {
		if (Turma.getSelectedItem() != null) {
			Turma turma = (Turma) Turma.getSelectedItem();
			Vagas.setText("Vagas disponíveis: " + turma.getVagas());
		}
	}

	@Override
	public void dispose() {
		controlador.exibirJanPrincipal();
		super.dispose();
	}

	public JComboBox<Aluno> getAluno() {return Aluno;}

	public JComboBox<Turma> getTurma() {return Turma;}

	/**
	 * Method generated by IntelliJ IDEA GUI Designer
	 * >>> IMPORTANT!! <<<
	 * DO NOT edit this method OR call it in your code!
	 *
	 * @noinspection ALL
	 */
	private void $$$setupUI$$$() {
		Panel = new JPanel();
		Panel.setLayout(new GridLayoutManager(4, 2, new Insets(10, 10, 10, 10), -1, -1));
		Panel.setBackground(new Color(-13158601));
		Matricular = new JButton();
		Matricular.setBackground(new Color(-11513776));
		Matricular.setBorderPainted(false);
		Matricular.setFocusPainted(false);
		Matricular.setForeground(new Color(-3289651));
		Matricular.setText("Matricular");
		Panel.add(Matricular, new GridConstraints(3, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 30), null, 0, false));
		final JLabel label1 = new JLabel();
		label1.setForeground(new Color(-3289651));
		label1.setText("Aluno");
		Panel.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		Aluno = new JComboBox();
		Panel.add(Aluno, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 30), null, 0, false));
		final JLabel label2 = new JLabel();
		label2.setForeground(new Color(-3289651));
		label2.setText("Turma");
		Panel.add(label2, new GridConstraints(1, 0, 2, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		Turma = new JComboBox();
		Panel.add(Turma, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 30), null, 0, false));
		Vagas = new JLabel();
		Vagas.setForeground(new Color(-3289651));
		Vagas.setText("Vagas Disponíveis");
		Panel.add(Vagas, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
	}

	/**
	 * @noinspection ALL
	 */
	public JComponent $$$getRootComponent$$$() {return Panel;}

}