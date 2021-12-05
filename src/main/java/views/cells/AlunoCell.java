package views.cells;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import models.Format;
import models.entitys.Aluno;

import javax.swing.*;
import java.awt.*;

import static models.Format.dateFormatter;

public class AlunoCell implements ListCellRenderer<Aluno> {
    private JLabel cpf;
    private JLabel nome;
    private JLabel idade;
    private JLabel sexo;
    private JLabel dataNascimento;
    private JPanel panel;

    @Override
    public Component getListCellRendererComponent(JList<? extends Aluno> list, Aluno value, int index, boolean isSelected, boolean cellHasFocus) {
        cpf.setText(String.valueOf(value.getCpf()));
        nome.setText(value.getNome());
        idade.setText(value.getIdade() + " anos");
        sexo.setText(value.getSexo().toString());
        dataNascimento.setText(value.getDataNascimento().format(dateFormatter));
        if (isSelected) {
            panel.setBackground(list.getSelectionBackground());
            panel.setForeground(list.getSelectionForeground());
        } else {
            panel.setBackground(list.getBackground());
            panel.setForeground(list.getForeground());
        }
        return panel;
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
        panel.setLayout(new FormLayout("center:40pt:grow,left:4dlu:noGrow,center:120pt:grow,left:4dlu:noGrow,center:20pt:grow,left:4dlu:noGrow,center:30pt:grow,left:4dlu:noGrow,center:d:grow", "center:d:grow"));
        panel.setBackground(new Color(- 13487566));
        panel.setPreferredSize(new Dimension(- 1, 30));
        idade = new JLabel();
        idade.setForeground(new Color(- 3618616));
        idade.setText("Label");
        CellConstraints cc = new CellConstraints();
        panel.add(idade, cc.xy(5, 1));
        dataNascimento = new JLabel();
        dataNascimento.setForeground(new Color(- 3618616));
        dataNascimento.setText("Label");
        panel.add(dataNascimento, cc.xy(9, 1));
        sexo = new JLabel();
        sexo.setForeground(new Color(- 3618616));
        sexo.setText("Label");
        panel.add(sexo, cc.xy(7, 1));
        cpf = new JLabel();
        cpf.setForeground(new Color(- 3618616));
        cpf.setHorizontalAlignment(2);
        cpf.setText("Label");
        panel.add(cpf, cc.xy(1, 1));
        nome = new JLabel();
        nome.setForeground(new Color(- 3618616));
        nome.setText("Label");
        panel.add(nome, cc.xy(3, 1));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() { return panel; }

}
