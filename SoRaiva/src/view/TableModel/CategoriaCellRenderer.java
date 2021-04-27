/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.TableModel;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Diogo
 */
public class CategoriaCellRenderer extends DefaultTableCellRenderer {
    
    // O construtor dessa classe chama o contrutor da classe que ele está herdando
    public CategoriaCellRenderer() {
        super();
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        
        JLabel label = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        Color corDestaque = new Color(176,224,230);//PowderBlue
        Color corNormal = new Color(255,250,250); //Snow
        Color corSelecionada = new Color(95,158,160); //CadetBlue
        
        //Mescla as cores de fundos da linhas
        switch (row%2) {
            case 0:
                label.setBackground(corNormal);
                break;
            case 1:
                label.setBackground(corDestaque);
        }
        
        //Muda a cor da linha se você clicar nela
        if (isSelected) {
            label.setBackground(corSelecionada);
        }
        
        // Alinhamento do texto do label
        label.setHorizontalAlignment(LEFT);
        
        return label;
    }
    
    
}
