package ciu;

import cci.ControladorPrincipal;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;

public class JanPrincipal extends JFrame {
    private JPanel Panel;
    private JButton CadastrarCurso;
    private JButton CadastrarProfessor;
    private JButton CadastrarTurma;
    private JButton CadastrarAluno;
    private JButton MatricularAluno;

    public JanPrincipal(ControladorPrincipal controlador) {
        super("App");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(Panel);
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        CadastrarCurso.addActionListener(e -> controlador.exibirJanCadCurso());
        CadastrarProfessor.addActionListener(e -> controlador.exibirJanCadProfessor());
        CadastrarTurma.addActionListener(e -> controlador.exibirJanCadTurma());
        CadastrarAluno.addActionListener(e -> controlador.exibirJanCadAluno());
        MatricularAluno.addActionListener(e -> controlador.exibirJanCadMatricula());
    }

    {
        // GUI initializer generated by IntelliJ IDEA GUI Designer
        // >>> IMPORTANT!! <<<
        // DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        Panel = new JPanel();
        Panel.setLayout(new GridLayoutManager(5, 1, new Insets(0, 0, 0, 0), -1, -1));
        Panel.setBackground(new Color(-13487566));
        CadastrarProfessor = new JButton();
        CadastrarProfessor.setBackground(new Color(-11513776));
        CadastrarProfessor.setBorderPainted(false);
        CadastrarProfessor.setContentAreaFilled(true);
        CadastrarProfessor.setDefaultCapable(true);
        CadastrarProfessor.setDoubleBuffered(false);
        CadastrarProfessor.setEnabled(true);
        CadastrarProfessor.setFocusCycleRoot(false);
        CadastrarProfessor.setFocusPainted(false);
        CadastrarProfessor.setFocusable(true);
        CadastrarProfessor.setForeground(new Color(-3289651));
        CadastrarProfessor.setText("Cadastrar Professor");
        Panel.add(CadastrarProfessor, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CadastrarCurso = new JButton();
        CadastrarCurso.setBackground(new Color(-11513776));
        CadastrarCurso.setBorderPainted(false);
        CadastrarCurso.setFocusPainted(false);
        CadastrarCurso.setForeground(new Color(-3289651));
        CadastrarCurso.setText("Cadastrar Curso");
        Panel.add(CadastrarCurso, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CadastrarTurma = new JButton();
        CadastrarTurma.setBackground(new Color(-11513776));
        CadastrarTurma.setBorderPainted(false);
        CadastrarTurma.setFocusPainted(false);
        CadastrarTurma.setForeground(new Color(-3289651));
        CadastrarTurma.setText("Cadastrar Turma");
        Panel.add(CadastrarTurma, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CadastrarAluno = new JButton();
        CadastrarAluno.setBackground(new Color(-11513776));
        CadastrarAluno.setBorderPainted(false);
        CadastrarAluno.setFocusPainted(false);
        CadastrarAluno.setForeground(new Color(-3289651));
        CadastrarAluno.setText("Cadastrar Aluno");
        Panel.add(CadastrarAluno, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        MatricularAluno = new JButton();
        MatricularAluno.setBackground(new Color(-11513776));
        MatricularAluno.setBorderPainted(false);
        MatricularAluno.setFocusPainted(false);
        MatricularAluno.setForeground(new Color(-3289651));
        MatricularAluno.setText("Matricular Aluno");
        Panel.add(MatricularAluno, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() { return Panel; }

}
