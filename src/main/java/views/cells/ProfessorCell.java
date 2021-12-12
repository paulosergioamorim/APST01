package views.cells;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import models.entitys.Professor;

import javax.swing.*;
import java.awt.*;

import static models.Format.dateFormatter;

public class ProfessorCell implements ListCellRenderer<Professor> {
    private JLabel cpf;
    private JLabel nome;
    private JLabel idade;
    private JLabel sexo;
    private JLabel dataNascimento;
    private JPanel panel;
    private JLabel titulacao;

    @Override
    public Component getListCellRendererComponent(JList<? extends Professor> list,
                                                  Professor value,
                                                  int index,
                                                  boolean isSelected,
                                                  boolean cellHasFocus) {
        String cpf = String.valueOf(value.getCpf());
        String nome = value.getNome();
        String idade = value.getIdade() + " anos";
        String sexo = value.getSexo().toString();
        String dataNascimento = value.getDataNascimento().format(dateFormatter);
        String titulacao = value.getTitulacao();
        this.cpf.setText(cpf);
        this.nome.setText(nome);
        this.idade.setText(idade);
        this.sexo.setText(sexo);
        this.dataNascimento.setText(dataNascimento);
        this.titulacao.setText(titulacao);
        if (isSelected) {
            panel.setBackground(list.getSelectionBackground());
            panel.setForeground(list.getSelectionForeground());
        }
        else {
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
        panel.setLayout(new FormLayout("center:100px:grow,left:4dlu:noGrow,center:200px:grow,left:4dlu:noGrow,center:50px:grow,left:4dlu:noGrow,center:100px:grow,left:4dlu:noGrow,center:100px:grow,left:4dlu:noGrow,center:100px:grow", "center:d:grow"));
        panel.setBackground(new Color(-13487566));
        panel.setPreferredSize(new Dimension(-1, 30));
        idade = new JLabel();
        idade.setForeground(new Color(-3618616));
        idade.setText("Idade");
        CellConstraints cc = new CellConstraints();
        panel.add(idade, cc.xy(5, 1));
        dataNascimento = new JLabel();
        dataNascimento.setForeground(new Color(-3618616));
        dataNascimento.setText("Data de Nascimento");
        panel.add(dataNascimento, cc.xy(9, 1));
        sexo = new JLabel();
        sexo.setForeground(new Color(-3618616));
        sexo.setText("Sexo");
        panel.add(sexo, cc.xy(7, 1));
        cpf = new JLabel();
        cpf.setForeground(new Color(-3618616));
        cpf.setHorizontalAlignment(2);
        cpf.setText("CPF");
        panel.add(cpf, cc.xy(1, 1));
        nome = new JLabel();
        nome.setForeground(new Color(-3618616));
        nome.setText("Nome");
        panel.add(nome, cc.xy(3, 1));
        titulacao = new JLabel();
        titulacao.setForeground(new Color(-3618616));
        titulacao.setText("Titulação");
        panel.add(titulacao, cc.xy(11, 1));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() { return panel; }

}
