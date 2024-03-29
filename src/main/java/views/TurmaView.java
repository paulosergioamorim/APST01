package views;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import controls.Control;
import models.entitys.Curso;
import models.entitys.Professor;
import models.entitys.Turma;
import views.cells.TurmaCell;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static models.Format.*;
import static models.View.MAIN_VIEW;

public class TurmaView extends JFrame {
    private final Control control;

    private JPanel panel;
    private JButton saveButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JFormattedTextField idField;
    private JFormattedTextField dataInicioField;
    private JFormattedTextField dataFimField;
    private JList<Turma> listView;
    private JFormattedTextField horarioField;
    private JFormattedTextField limiteField;
    private JComboBox<Curso> cursoBox;
    private JComboBox<Professor> responsavelBox;

    public TurmaView(Control control) {
        super("Turmas");
        this.control = control;
        this.$$$setupUI$$$();
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setContentPane(panel);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);

        saveButton.addActionListener(e -> this.saveTurma());
        updateButton.addActionListener(e -> this.updateTurma());
        deleteButton.addActionListener(e -> this.deleteTurma());
    }

    private void saveTurma() {
        try {
            int id = Integer.parseInt(idField.getText());
            LocalDate dataInicio = LocalDate.parse(dataInicioField.getText(), dateFormatter);
            LocalDate dataFim = LocalDate.parse(dataFimField.getText(), dateFormatter);
            LocalTime horario = LocalTime.parse(horarioField.getText(), timeFormatter);
            int limite = Integer.parseInt(limiteField.getText());
            Curso curso = (Curso) cursoBox.getSelectedItem();
            Professor responsavel = (Professor) responsavelBox.getSelectedItem();
            control.saveTurma(id, dataInicio, dataFim, horario, limite, curso, responsavel);
        } catch (Exception e) {
            e.printStackTrace();
            control.showMessage("Erro ao salvar turma!");
        }
    }

    private void updateTurma() {
        try {
            int id = Integer.parseInt(idField.getText());
            LocalDate dataInicio = dataInicioField.getText().equals("__/__/____") ?
                    null : LocalDate.parse(dataInicioField.getText(), dateFormatter);
            LocalDate dataFim = dataFimField.getText().equals("__/__/____") ?
                    null : LocalDate.parse(dataFimField.getText(), dateFormatter);
            LocalTime horario = horarioField.getText().equals("__:__") ?
                    null : LocalTime.parse(horarioField.getText(), timeFormatter);
            int limite = Integer.parseInt(limiteField.getText());
            Curso curso = (Curso) cursoBox.getSelectedItem();
            Professor responsavel = (Professor) responsavelBox.getSelectedItem();
            control.updateTurma(id, dataInicio, dataFim, horario, limite, curso, responsavel);
        } catch (Exception e) {
            e.printStackTrace();
            control.showMessage("Erro ao atualizar turma!");
        }
    }

    private void deleteTurma() {
        try {
            Turma turma = listView.getSelectedValue();
            int id = turma != null ? turma.getId() : -1;
            control.deleteTurma(id);
        } catch (Exception e) {
            e.printStackTrace();
            control.showMessage("Erro ao deletar turma!");
        }
    }

    @Override
    public void dispose() {
        control.changeView(MAIN_VIEW);
        super.dispose();
    }

    public void clearFields() {
        idField.setValue(null);
        dataInicioField.setValue(null);
        dataFimField.setValue(null);
        horarioField.setValue(null);
        limiteField.setValue(null);
        cursoBox.setSelectedItem(null);
        responsavelBox.setSelectedItem(null);
    }

    public void updateListViewer(List<Turma> turmas) {
        DefaultListModel<Turma> model = new DefaultListModel<>();
        model.addAll(turmas);
        listView.setModel(model);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        idField = new JFormattedTextField(int4Mask);
        dataInicioField = new JFormattedTextField(dateMask);
        dataFimField = new JFormattedTextField(dateMask);
        limiteField = new JFormattedTextField(int2Mask);
        horarioField = new JFormattedTextField(timeMask);
        List<Curso> cursos = control.getCursoControl().getAll();
        DefaultComboBoxModel<Curso> cursoModel = new DefaultComboBoxModel<>();
        cursoModel.addAll(cursos);
        cursoBox = new JComboBox<>(cursoModel);
        List<Professor> professores = control.getProfessorControl().getAll();
        DefaultComboBoxModel<Professor> professorModel = new DefaultComboBoxModel<>();
        professorModel.addAll(professores);
        responsavelBox = new JComboBox<>(professorModel);
        listView = new JList<>();
        TurmaCell cell = new TurmaCell();
        listView.setCellRenderer(cell);
        List<Turma> turmas = control.getTurmaControl().getAll();
        this.updateListViewer(turmas);
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
        panel.setLayout(new GridLayoutManager(3, 3, new Insets(10, 10, 10, 10), -1, -1));
        panel.setBackground(new Color(-13487566));
        saveButton = new JButton();
        saveButton.setFocusPainted(false);
        saveButton.setText("Cadastrar");
        panel.add(saveButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        updateButton = new JButton();
        updateButton.setFocusPainted(false);
        updateButton.setText("Atualizar");
        panel.add(updateButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        deleteButton = new JButton();
        deleteButton.setFocusPainted(false);
        deleteButton.setText("Excluir");
        panel.add(deleteButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(4, 4, new Insets(10, 0, 10, 0), -1, -1));
        panel1.setBackground(new Color(-13487566));
        panel.add(panel1, new GridConstraints(1, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setForeground(new Color(-3618616));
        label1.setText("Curso");
        panel1.add(label1, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panel1.add(cursoBox, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 30), null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setForeground(new Color(-3618616));
        label2.setText("Responsável");
        panel1.add(label2, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panel1.add(responsavelBox, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 30), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setForeground(new Color(-3618616));
        label3.setText("Limite de Alunos");
        panel1.add(label3, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panel1.add(limiteField, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 30), null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setForeground(new Color(-3618616));
        label4.setText("Horário");
        panel1.add(label4, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panel1.add(horarioField, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 30), null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setForeground(new Color(-3618616));
        label5.setText("Data Final");
        panel1.add(label5, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panel1.add(dataFimField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 30), null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setForeground(new Color(-3618616));
        label6.setText("Data de Início");
        panel1.add(label6, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panel1.add(dataInicioField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 30), null, 0, false));
        panel1.add(idField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 30), null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setForeground(new Color(-3618616));
        label7.setText("ID");
        panel1.add(label7, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setBackground(new Color(-13487566));
        panel.add(scrollPane1, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        scrollPane1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        listView.setBackground(new Color(-13487566));
        listView.setForeground(new Color(-3618616));
        listView.setSelectionBackground(new Color(-8553091));
        scrollPane1.setViewportView(listView);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() { return panel; }

}
