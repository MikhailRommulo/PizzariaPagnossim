package view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.bean.Item;

public class TableModelItem extends AbstractTableModel{
	private List<Item> listaDeItens;
	private String[] colunas = {"Código","Produto","Valor","Quantidade","Total"};

	public TableModelItem() {
		this.listaDeItens = new ArrayList<>();
	}
	
	public void pegarListaDeItens(List<Item> list) {
		this.listaDeItens = list;
		fireTableDataChanged();
	}
	
	public Item pegarItem(int rowIndex) {
		return this.listaDeItens.get(rowIndex);
	}
	
	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public int getRowCount() {
		return this.listaDeItens.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex) {
			case 0:
				return this.listaDeItens.get(rowIndex).getProduto().getCodProd();
			case 1:
				return this.listaDeItens.get(rowIndex).getProduto().getDescricao();
			case 2:
				return this.listaDeItens.get(rowIndex).getProduto().getPreco();
			case 3:
				return this.listaDeItens.get(rowIndex).getQuantidade();
			case 4:
				return this.listaDeItens.get(rowIndex).precoTotal();
			default:
				return this.listaDeItens.get(rowIndex);
		}
		
	}
	
	public String getColumnName(int columnIndex) {
		return this.colunas[columnIndex];
	}

}
