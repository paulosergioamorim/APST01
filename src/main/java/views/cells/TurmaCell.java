package views.cells;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import controls.TurmaControl;
import models.Estado;
import models.entitys.Turma;

import javax.swing.*;
import java.awt.*;

import static models.Format.dateFormatter;
import static models.Format.timeFormatter;

public class TurmaCell implements ListCellRenderer<Turma> {
    private JPanel panel;
    private JLabel id;
    private JLabel dataInicio;
    private JLabel dataFim;
    private JLabel horario;
    private JLabel vagas;
    private JLabel estado;
    private JLabel curso;
    private JLabel responsavel;

    @Override
    public Component getListCellRendererComponent(JList<? extends Turma> list,
                                                  Turma value,
                                                  int index,
                                                  boolean isSelected,
                                                  boolean cellHasFocus) {
        String id = String.valueOf(value.getId());
        String dataInicio = value.getDataInicio().format(dateFormatter);
        String dataFim = value.getDataFim().format(dateFormatter);
        String horario = value.getHorario().format(timeFormatter);
        String vagas = value.getVagas() + " vagas";
        String estado = Estado.valueOf(value.getEstado());
        String curso = value.getCurso().getSigla();
        String responsavel = value.getResponsavel().getNome();
        this.id.setText(id);
        this.dataInicio.setText(dataInicio);
        this.dataFim.setText(dataFim);
        this.horario.setText(horario);
        this.vagas.setText(vagas);
        this.estado.setText(estado);
        this.curso.setText(curso);
        this.responsavel.setText(responsavel);
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
        this.$$$setupUI$$$();
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
        panel.setLayout(new FormLayout("center:50px:grow,left:4dlu:noGrow,center:75px:grow,left:4dlu:noGrow,center:75px:grow,left:4dlu:noGrow,center:75px:grow,left:4dlu:noGrow,center:50px:grow,left:4dlu:noGrow,center:50px:grow,left:4dlu:noGrow,center:100px:grow,left:4dlu:noGrow,center:100px:grow", "center:d:grow"));
        panel.setBackground(new Color(-13487566));
        panel.setPreferredSize(new Dimension(-1, 30));
        id = new JLabel();
        id.setForeground(new Color(-3618616));
        id.setText("ID");
        CellConstraints cc = new CellConstraints();
        panel.add(id, cc.xy(1, 1));
        dataInicio = new JLabel();
        dataInicio.setForeground(new Color(-3618616));
        dataInicio.setText("Data de Início");
        panel.add(dataInicio, cc.xy(3, 1));
        dataFim = new JLabel();
        dataFim.setForeground(new Color(-3618616));
        dataFim.setText("Data Final");
        panel.add(dataFim, cc.xy(5, 1));
        horario = new JLabel();
        horario.setForeground(new Color(-3618616));
        horario.setText("Horário");
        panel.add(horario, cc.xy(7, 1));
        vagas = new JLabel();
        vagas.setForeground(new Color(-3618616));
        vagas.setText("Vagas");
        panel.add(vagas, cc.xy(9, 1));
        curso = new JLabel();
        curso.setForeground(new Color(-3618616));
        curso.setText("Curso");
        panel.add(curso, cc.xy(11, 1));
        responsavel = new JLabel();
        responsavel.setForeground(new Color(-3618616));
        responsavel.setText("Responsável");
        panel.add(responsavel, cc.xy(13, 1));
        estado = new JLabel();
        estado.setForeground(new Color(-3618616));
        estado.setText("Estado");
        panel.add(estado, cc.xy(15, 1));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() { return panel; }

}
