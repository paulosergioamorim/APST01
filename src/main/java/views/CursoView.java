package views;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import controls.Control;
import models.entitys.Curso;
import views.cells.CursoCell;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.List;

import static models.Format.int4Mask;
import static models.View.MAIN_VIEW;

public class CursoView extends JFrame {
    private final Control control;

    private JPanel panel;
    private JButton saveButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JList<Curso> listView;
    private JFormattedTextField idField;
    private JTextField nomeField;
    private JTextField siglaField;
    private JFormattedTextField cargaHorariaField;

    public CursoView(Control control) {
        super("Cursos");
        this.control = control;
        this.$$$setupUI$$$();
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setContentPane(panel);
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);

        saveButton.addActionListener(e -> control.saveCurso());
        updateButton.addActionListener(e -> control.updateCurso());
        deleteButton.addActionListener(e -> control.deleteCurso());
    }

    @Override
    public void dispose() {
        control.changeView(MAIN_VIEW);
        super.dispose();
    }

    public void clearFields() {
        idField.setValue(null);
        nomeField.setText(null);
        siglaField.setText(null);
        cargaHorariaField.setValue(null);
    }

    public void updateListViewer(List<Curso> cursos) {
        DefaultListModel<Curso> modelCurso = new DefaultListModel<>();
        modelCurso.addAll(cursos);
        listView.setModel(modelCurso);
    }

    public String getId() { return idField.getText(); }

    public String getNome() { return nomeField.getText(); }

    public String getSigla() { return siglaField.getText(); }

    public String getCargaHoraria() { return cargaHorariaField.getText(); }

    public JList<Curso> getListView() { return listView; }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        idField = new JFormattedTextField(int4Mask);
        cargaHorariaField = new JFormattedTextField(int4Mask);
        listView = new JList<>();
        CursoCell cell = new CursoCell();
        listView.setCellRenderer(cell);
        List<Curso> cursos = control.getCursoControl().getAll();
        this.updateListViewer(cursos);
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
        updateButton = new JButton();
        updateButton.setText("Atualizar");
        panel.add(updateButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(4, 2, new Insets(10, 0, 10, 0), - 1, - 1));
        panel1.setBackground(new Color(- 13487566));
        panel.add(panel1, new GridConstraints(1, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setForeground(new Color(- 3618616));
        label1.setText("ID");
        panel1.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panel1.add(idField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 30), null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setForeground(new Color(- 3618616));
        label2.setText("Nome");
        panel1.add(label2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nomeField = new JTextField();
        panel1.add(nomeField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 30), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setForeground(new Color(- 3618616));
        label3.setText("Sigla");
        panel1.add(label3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        siglaField = new JTextField();
        panel1.add(siglaField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 30), null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setForeground(new Color(- 3618616));
        label4.setText("Carga Horária");
        panel1.add(label4, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panel1.add(cargaHorariaField, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 30), null, 0, false));
        saveButton = new JButton();
        saveButton.setText("Cadastrar");
        panel.add(saveButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        deleteButton = new JButton();
        deleteButton.setText("Excluir");
        panel.add(deleteButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
