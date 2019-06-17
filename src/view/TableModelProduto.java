package view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.bean.Produto;

public class TableModelProduto extends AbstractTableModel {
	private List<Produto> listaDeProdutos;
	private String[] colunas = {"Descrição","Tipo","Valor","Quantidade"};
	
	public TableModelProduto() {
		this.listaDeProdutos = new ArrayList<>();
	}
	
	public void pegarListaProdutos(List<Produto> list) {
		this.listaDeProdutos = list;
		fireTableDataChanged();
	}
	
	public Produto pegarProduto(int rowIndex) {
		return this.listaDeProdutos.get(rowIndex);
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public int getRowCount() {
		return this.listaDeProdutos.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex) {
			case 0:
				return this.listaDeProdutos.get(rowIndex).getDescricao();
			case 1:
				return this.listaDeProdutos.get(rowIndex).getTipo();
			case 2:
				return this.listaDeProdutos.get(rowIndex).getPreco();
			case 3:
				return this.listaDeProdutos.get(rowIndex).getQuantidade();
			default:
				return this.listaDeProdutos.get(rowIndex);
		}
		
		
	}
	
	public String getColumnName(int columnIndex) {
		return this.colunas[columnIndex];
	}
}
