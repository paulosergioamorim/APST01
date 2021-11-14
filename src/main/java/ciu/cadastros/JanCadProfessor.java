package ciu.cadastros;

import cci.ControladorPrincipal;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

import static cdp.Formatters.*;

public class JanCadProfessor extends JFrame {
    private final ControladorPrincipal controlador;

    private JPanel Panel;
    private JButton Salvar;
    private JTextField Nome;
    private JTextField Titulacao;
    private JFormattedTextField Cpf;
    private JFormattedTextField DataNascimento;

    public JanCadProfessor(ControladorPrincipal controlador) {
        super("Cadastro de Professor");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(Panel);
        this.setResizable(false);
        this.setSize(300, 300);
        this.setLocationRelativeTo(null);

        this.controlador = controlador;

        ImageIcon icon = new ImageIcon("src/main/resources/images/icon.png");
        this.setIconImage(icon.getImage());

        Cpf.setFormatterFactory(getFormatterFactory(cpfMask));
        DataNascimento.setFormatterFactory(getFormatterFactory(dateMask));

        Salvar.addActionListener(e -> {
            try {
                long cpf = Long.parseLong(Cpf.getText().replaceAll("[\\D]", ""));
                String nome = Nome.getText();
                LocalDate dataNascimento = LocalDate.parse(DataNascimento.getText(), dateFormatter);
                String titulacao = Titulacao.getText();
                controlador.cadastrarProfessor(nome, dataNascimento, cpf, titulacao);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar professor", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    @Override
    public void dispose() {
        controlador.exibirJanPrincipal();
        super.dispose();
    }

    public JFormattedTextField getCpf() { return Cpf; }

    public JTextField getNome() { return Nome; }

    public JFormattedTextField getDataNascimento() { return DataNascimento; }

    public JTextField getTitulacao() { return Titulacao; }

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
        Panel.setLayout(new GridLayoutManager(5, 2, new Insets(10, 10, 10, 10), -1, -1));
        Panel.setBackground(new Color(-13487566));
        final JLabel label1 = new JLabel();
        label1.setForeground(new Color(-3289651));
        label1.setText("Data de Nascimento");
        Panel.add(label1, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(115, 16), null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setForeground(new Color(-3289651));
        label2.setText("Titulação");
        Panel.add(label2, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(115, 16), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setForeground(new Color(-3289651));
        label3.setText("Nome");
        Panel.add(label3, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(115, 16), null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setForeground(new Color(-3289651));
        label4.setText("CPF");
        Panel.add(label4, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(115, 16), null, 0, false));
        DataNascimento = new JFormattedTextField();
        Panel.add(DataNascimento, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 30), null, 0, false));
        Nome = new JTextField();
        Panel.add(Nome, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 30), null, 0, false));
        Cpf = new JFormattedTextField();
        Panel.add(Cpf, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 30), null, 0, false));
        Titulacao = new JTextField();
        Panel.add(Titulacao, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 30), null, 0, false));
        Salvar = new JButton();
        Salvar.setBackground(new Color(-11513776));
        Salvar.setBorderPainted(false);
        Salvar.setFocusPainted(false);
        Salvar.setForeground(new Color(-3289651));
        Salvar.setText("Salvar");
        Panel.add(Salvar, new GridConstraints(4, 0, 1, 2, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 30), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() { return Panel; }

}
