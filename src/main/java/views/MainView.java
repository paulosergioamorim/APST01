package views;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import controls.Control;

import javax.swing.*;

import java.awt.*;

public class MainView extends JFrame {
    private JPanel panel;
    private JButton alunoViewButton;
    private JButton cursoViewButton;

    public MainView(Control control) {
        super("Program");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(panel);
        this.setSize(600, 600);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        alunoViewButton.addActionListener(e -> control.changeView(View.ALUNO_VIEW));
        cursoViewButton.addActionListener(e -> control.changeView(View.CURSO_VIEW));
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
        panel = new JPanel();
        panel.setLayout(new GridLayoutManager(2, 2, new Insets(10, 10, 10, 10), - 1, - 1));
        panel.setBackground(new Color(- 13487566));
        alunoViewButton = new JButton();
        alunoViewButton.setBorderPainted(true);
        alunoViewButton.setContentAreaFilled(true);
        alunoViewButton.setFocusPainted(false);
        alunoViewButton.setOpaque(true);
        alunoViewButton.setText("Aluno View");
        panel.add(alunoViewButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel.add(spacer1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        cursoViewButton = new JButton();
        cursoViewButton.setText("Curso View");
        panel.add(cursoViewButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() { return panel; }

}
