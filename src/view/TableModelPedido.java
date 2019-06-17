package view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.bean.Pedido;

public class TableModelPedido extends AbstractTableModel{
	private List<Pedido> listaDePedidos;
	private String[] colunas = {"Número","Cliente","Endereço","Valor","Situação"};
	
	public TableModelPedido() {
		this.listaDePedidos = new ArrayList<>();
	}
	
	public void pegarListaPedidos(List<Pedido> list) {
		this.listaDePedidos = list;
		fireTableDataChanged();
	}
	
	public Pedido pegarPedido(int rowIndex) {
		return this.listaDePedidos.get(rowIndex);
	}
	
	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public int getRowCount() {
		return this.listaDePedidos.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex) {
			case 0:
				return this.listaDePedidos.get(rowIndex).getCodPedido();
			case 1:
				return this.listaDePedidos.get(rowIndex).getCliente().getNome();
			case 2:
				return this.listaDePedidos.get(rowIndex).getCliente().getLogradouro();
			case 3:
				return this.listaDePedidos.get(rowIndex).getPreco();
			case 4:
				return this.listaDePedidos.get(rowIndex).getStatus();
			default:
				return this.listaDePedidos.get(rowIndex);
		}
	}
	
	public String getColumnName(int columnIndex) {
		return this.colunas[columnIndex];
	}
	
}
