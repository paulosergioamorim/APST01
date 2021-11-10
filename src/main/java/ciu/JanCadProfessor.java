package ciu;

import cci.ControladorPrincipal;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class JanCadProfessor extends JFrame {
    private JPanel Panel;
    private JButton Voltar;
    private JButton Enviar;
    private JTextField Nome;
    private JTextField DataNascimento;
    private JTextField Titulacao;
    private JTextField Cpf;

    public JanCadProfessor(ControladorPrincipal controlador) {
        super("Cadastro de Professor");
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setContentPane(Panel);
        this.setResizable(false);
        this.setSize(300, 300);
        this.setLocationRelativeTo(null);

        Voltar.addActionListener(e -> controlador.exibirJanPrincipal());
        Enviar.addActionListener(e -> {
            try {
                long cpf = Long.parseLong(Cpf.getText());
                String nome = Nome.getText();
                LocalDate dataNascimento = LocalDate.parse(DataNascimento.getText());
                String titulacao = Titulacao.getText();
                controlador.cadastrarProfessor(nome, dataNascimento, cpf, titulacao);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar professor", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public JTextField getCpf() { return Cpf; }

    public JTextField getNome() { return Nome; }

    public JTextField getDataNascimento() { return DataNascimento; }

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
        Panel.setLayout(new GridLayoutManager(10, 2, new Insets(0, 0, 0, 0), -1, -1));
        Panel.setBackground(new Color(-13487566));
        Voltar = new JButton();
        Voltar.setBackground(new Color(-10855846));
        Voltar.setBorderPainted(false);
        Voltar.setFocusPainted(false);
        Voltar.setForeground(new Color(-3289651));
        Voltar.setText("Voltar");
        Panel.add(Voltar, new GridConstraints(9, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Enviar = new JButton();
        Enviar.setBackground(new Color(-10855846));
        Enviar.setBorderPainted(false);
        Enviar.setFocusPainted(false);
        Enviar.setForeground(new Color(-3289651));
        Enviar.setText("Enviar");
        Panel.add(Enviar, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Nome = new JTextField();
        Panel.add(Nome, new GridConstraints(3, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        DataNascimento = new JTextField();
        Panel.add(DataNascimento, new GridConstraints(5, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setForeground(new Color(-3289651));
        label1.setText("Data de Nascimento (yyyy-mm-dd)");
        Panel.add(label1, new GridConstraints(4, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Titulacao = new JTextField();
        Panel.add(Titulacao, new GridConstraints(7, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setForeground(new Color(-3289651));
        label2.setText("Titulação");
        Panel.add(label2, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        Panel.add(spacer1, new GridConstraints(8, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setForeground(new Color(-3289651));
        label3.setText("Nome");
        Panel.add(label3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setForeground(new Color(-3289651));
        label4.setText("CPF");
        Panel.add(label4, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Cpf = new JTextField();
        Panel.add(Cpf, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() { return Panel; }

}
