/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.TableModel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import modelo.entidade.Categoria;

/**
 *
 * @author Diogo
 */
public class CategoriaTableModel extends AbstractTableModel{
    
    private List<Categoria> lstCategoria = new ArrayList<>();
    private String[] colunas = {"ID", "Nome"};
    
    //Retorna a quantidade de linhas do modelo
    @Override
    public int getRowCount() {
        return lstCategoria.size();
    }
    
    //Retorna a quantidade de colunas no modelo
    @Override
    public int getColumnCount() {
        return colunas.length;
    }
    
    //Retorna o nome da coluna em determinado indice
    @Override
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }
    
    //Retorna a classe da coluna informada pelo index, nesse caso String
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }
    
    //Setará os valores em determinada célula
    //E o fireTableCellUpdated notifica os listeners que o valor de uma célula foi alterada
    public void setValueAt(Categoria aValue, int rowIndex) {
        Categoria categoria = lstCategoria.get(rowIndex);
        
        categoria.setId(aValue.getId());
        categoria.setNome(aValue.getNome());
        
        fireTableCellUpdated(rowIndex, 1);
        fireTableCellUpdated(rowIndex, 2);
    }
    
    //Setará os valores em determinada célula
    // E o fireTableCellUpdated notifica os listeners que o valor de uma célula foi alterada
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Categoria categoria = lstCategoria.get(rowIndex);
        
        switch (rowIndex) {
            case 0:
                categoria.setId(Integer.parseInt(aValue.toString()));
                break;
            case 1:
                categoria.setNome(aValue.toString());
                break;
            default:
                System.err.println("Índice de coluna inválido. ");
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }
    
    //Retornara o valor em determinada célula de acordo com a linh a coluna informada
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Categoria categoriaSelecionada = lstCategoria.get(rowIndex);
        String valueObject = null;
        
        switch (columnIndex) {
            case 0:
                if (categoriaSelecionada.getId() != null) {
                    valueObject = String.valueOf(categoriaSelecionada.getId());
                } else {
                    valueObject = "";
                }
                break;
            case 1:
                valueObject = categoriaSelecionada.getNome();
                break;
            default:
                System.err.println("Índice inválido para  propriedade Categoria.class.");
        }
        
        return valueObject;
    }
    
    //Retorna se a célula atual (de acordo com a linha e colunas passadas) é editável
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
   //Retorna um objeto da lista de acordo com o index passado
    public Categoria getCategoria(int rowIndex) {
        return lstCategoria.get(rowIndex);
    }
    
    //Adiciona um object na lista e avisa aos Listeners que linhas foram inseridas
    public void addCategoria(Categoria c) {
        lstCategoria.add(c);
        
        int lastIndex = getRowCount() - 1;
        
        fireTableRowsInserted(lastIndex, lastIndex);
    }
    
    //Remove um objeto da lista e avisa aos Listeners que linhas foram apagadas
    public void removeUsuario(int rowIndex) {
        lstCategoria.remove(rowIndex);
        
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
    
    //Move-se todos os elementos da lista para o final e adiciona-se uma nova lista, por fim se avisa ao Listeners que linhas foram inseridas
    public void addListaDeCategoria(List<Categoria> novasCategorias){
        int tamanhoAntigo = getRowCount();
        
        lstCategoria.addAll(novasCategorias);
        
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }
    
    //Apaga toda a lista e avisa ao Listeners que os dados da lista mudaram
    public void limpar() {
        lstCategoria.clear();
        
        fireTableDataChanged();
    }
    
    //Retorna se a lista está vazia ou não
    public boolean isEmpty() {
        return lstCategoria.isEmpty();
    }
}
