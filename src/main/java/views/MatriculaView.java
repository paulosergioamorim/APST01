package views;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import controls.Control;
import models.entitys.Aluno;
import models.entitys.Matricula;
import models.entitys.Turma;
import views.cells.MatriculaCell;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

import static models.Estado.MATRICULAS_ABERTAS;
import static models.Format.dateMask;
import static models.View.MAIN_VIEW;

public class MatriculaView extends JFrame {
    private final Control control;

    private JPanel panel;
    private JComboBox<Turma> turmaBox;
    private JComboBox<Aluno> alunoBox;
    private JFormattedTextField dataMatriculaField;
    private JButton saveButton;
    private JButton deleteButton;
    private JList<Matricula> listView;
    private JButton updateButton;

    public MatriculaView(Control control) {
        super("Matriculas");
        this.control = control;
        $$$setupUI$$$();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(panel);
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);

        saveButton.addActionListener(e -> control.saveMatricula());
        updateButton.addActionListener(e -> control.updateMatricula());
        deleteButton.addActionListener(e -> control.deleteMatricula());
    }

    @Override
    public void dispose() {
        control.changeView(MAIN_VIEW);
        super.dispose();
    }

    public void clearFields() {
        turmaBox.setSelectedItem(null);
        alunoBox.setSelectedItem(null);
        dataMatriculaField.setValue(null);
    }

    public void updateListViewer(List<Matricula> matriculas) {
        DefaultListModel<Matricula> model = new DefaultListModel<>();
        model.addAll(matriculas);
        listView.setModel(model);
    }

    public Turma getTurma() { return (Turma) turmaBox.getSelectedItem(); }

    public Aluno getAluno() { return (Aluno) alunoBox.getSelectedItem(); }

    public String getDataMatricula() { return dataMatriculaField.getText(); }

    public JList<Matricula> getListView() { return listView; }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        dataMatriculaField = new JFormattedTextField(dateMask);

        List<Turma> turmas = control.getTurmaControl().getAll();
        turmas = turmas.stream().filter(t -> t.getEstado() == MATRICULAS_ABERTAS).collect(Collectors.toList());
        DefaultComboBoxModel<Turma> turmaModel = new DefaultComboBoxModel<>();
        turmaModel.addAll(turmas);
        turmaBox = new JComboBox<>(turmaModel);

        List<Aluno> alunos = control.getAlunoControl().getAll();
        DefaultComboBoxModel<Aluno> alunoModel = new DefaultComboBoxModel<>();
        alunoModel.addAll(alunos);
        alunoBox = new JComboBox<>(alunoModel);

        listView = new JList<>();
        MatriculaCell cell = new MatriculaCell();
        listView.setCellRenderer(cell);
        List<Matricula> matriculas = control.getMatriculaControl().getAll();
        this.updateListViewer(matriculas);
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
        saveButton.setDefaultCapable(true);
        saveButton.setFocusPainted(false);
        saveButton.setText("Cadastrar");
        panel.add(saveButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(- 1, 30), null, 0, false));
        deleteButton = new JButton();
        deleteButton.setFocusPainted(false);
        deleteButton.setText("Excluir");
        panel.add(deleteButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(- 1, 30), null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setBackground(new Color(- 13487566));
        panel.add(scrollPane1, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        scrollPane1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        listView.setBackground(new Color(- 13487566));
        listView.setForeground(new Color(- 3618616));
        listView.setSelectionBackground(new Color(- 8553091));
        scrollPane1.setViewportView(listView);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(3, 2, new Insets(10, 0, 10, 0), - 1, - 1));
        panel1.setBackground(new Color(- 13487566));
        panel.add(panel1, new GridConstraints(1, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setForeground(new Color(- 3618616));
        label1.setText("Turma");
        panel1.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panel1.add(turmaBox, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(- 1, 30), null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setForeground(new Color(- 3618616));
        label2.setText("Aluno");
        panel1.add(label2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panel1.add(alunoBox, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(- 1, 30), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setForeground(new Color(- 3618616));
        label3.setText("Data de Matricula");
        panel1.add(label3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panel1.add(dataMatriculaField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(- 1, 30), null, 0, false));
        updateButton = new JButton();
        updateButton.setFocusPainted(false);
        updateButton.setText("Atualizar");
        panel.add(updateButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(- 1, 30), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() { return panel; }
}
