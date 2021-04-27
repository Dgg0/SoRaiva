/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.TableModel;

import java.awt.FontMetrics;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Diogo
 */
public class CategoriaColumnModel extends DefaultTableColumnModel {
    
    public CategoriaColumnModel(FontMetrics fm) {
        
    }
    
    public TableColumn criaColuna(int columnIndex, int largura, FontMetrics fm, boolean resizable, String titulo) {
        
        int larguraTitulo = fm.stringWidth(titulo + "  ");
        
        // Se a largura de titulo definida pelo usuário for menor que o titulo, a largura recebe um novo valor onde ele caiba
        if (largura < larguraTitulo) {
            largura = larguraTitulo;
        }
        
        TableColumn coluna = new TableColumn(columnIndex);
        
        //Seta o Renderer das cell's da table
        coluna.setCellRenderer(new CategoriaCellRenderer());
        
        //Seta com null o Renderer do titúlo das cell's
        coluna.setHeaderRenderer(null);
        
        // Seta a titulo da coluna
        coluna.setHeaderValue(titulo);
        
        // Seta a largura preferida para a coluna
        coluna.setPreferredWidth(largura);
        
        // Se a coluna não for redimensionavel a largura minima e maxima são predefinas
        if (!resizable) {
            coluna.setMaxWidth(largura);
            coluna.setMinWidth(largura);
        }
        
        // A coluna recebe o valor de risizable passado pelo usuário
        coluna.setResizable(resizable);
        
        return coluna;
    }
}
