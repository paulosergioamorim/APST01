package views;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import controls.Control;
import models.Sexo;
import models.entitys.Aluno;
import views.cells.AlunoCell;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultFormatterFactory;
import java.awt.*;
import java.util.List;

import static models.Format.cpfMask;
import static models.Format.dateMask;
import static models.View.MAIN_VIEW;

public class AlunoView extends JFrame {
    private final Control control;

    private JPanel panel;
    private JButton saveButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JFormattedTextField cpf;
    private JFormattedTextField dataNascimento;
    private JTextField nome;
    private JComboBox<Sexo> sexo;
    private JList<Aluno> listView;

    public AlunoView(Control control) {
        super("Alunos");
        this.control = control;
        $$$setupUI$$$();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(panel);
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);

        saveButton.addActionListener(e -> control.saveAluno());
        deleteButton.addActionListener(e -> control.deleteAluno());
        updateButton.addActionListener(e -> control.updateAluno());
    }

    @Override
    public void dispose() {
        control.changeView(MAIN_VIEW);
        super.dispose();
    }

    public void clearFields() {
        cpf.setValue(null);
        nome.setText(null);
        dataNascimento.setValue(null);
        sexo.setSelectedItem(null);
    }

    public void updateListViewer(List<Aluno> alunos) {
        DefaultListModel<Aluno> modelAluno = new DefaultListModel<>();
        modelAluno.addAll(alunos);
        listView.setModel(modelAluno);
    }

    public String getCpf() { return cpf.getText(); }

    public String getNome() { return nome.getText(); }

    public String getDataNascimento() { return dataNascimento.getText(); }

    public Sexo getSexo() { return (Sexo) sexo.getSelectedItem(); }

    public JList<Aluno> getListView() { return listView; }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        cpf = new JFormattedTextField(new DefaultFormatterFactory(cpfMask));
        dataNascimento = new JFormattedTextField(new DefaultFormatterFactory(dateMask));
        sexo = new JComboBox<>(Sexo.values());
        listView = new JList<>();
        AlunoCell alunoCell = new AlunoCell();
        listView.setCellRenderer(alunoCell);
        List<Aluno> alunos = control.getAlunoControl().getAll();
        this.updateListViewer(alunos);
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        panel = new JPanel();
        panel.setLayout(new GridLayoutManager(3, 3, new Insets(10, 10, 10, 10), - 1, - 1));
        panel.setBackground(new Color(- 13487566));
        saveButton = new JButton();
        saveButton.setText("Cadastrar");
        panel.add(saveButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        updateButton = new JButton();
        updateButton.setText("Atualizar");
        panel.add(updateButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        deleteButton = new JButton();
        deleteButton.setText("Excluir");
        panel.add(deleteButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(4, 2, new Insets(10, 0, 10, 0), - 1, - 1));
        panel1.setBackground(new Color(- 13487566));
        panel.add(panel1, new GridConstraints(1, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setForeground(new Color(- 3618616));
        label1.setText("CPF");
        panel1.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panel1.add(cpf, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 30), null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setForeground(new Color(- 3618616));
        label2.setText("Nome");
        panel1.add(label2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setForeground(new Color(- 3618616));
        label3.setText("Data de Nascimento");
        panel1.add(label3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panel1.add(dataNascimento, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 30), null, 0, false));
        nome = new JTextField();
        panel1.add(nome, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 30), null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setForeground(new Color(- 3618616));
        label4.setText("Sexo");
        panel1.add(label4, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panel1.add(sexo, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(- 1, 30), null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setBackground(new Color(- 13487566));
        panel.add(scrollPane1, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        scrollPane1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        listView.setBackground(new Color(- 13487566));
        listView.setForeground(new Color(- 3618616));
        listView.setSelectionBackground(new Color(- 8553091));
        scrollPane1.setViewportView(listView);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() { return panel; }

}
