package view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.bean.Cliente;

public class TableModelCliente extends AbstractTableModel {
	private List<Cliente> listaDeClientes;
	private String[] colunas = {"CPF","Nome","Rua","Bairro","Telefone"};
	
	public TableModelCliente() {
		this.listaDeClientes = new ArrayList<>();
	}
	
	public void pegarListaClientes(List<Cliente> list) {
		this.listaDeClientes = list;
		fireTableDataChanged();
	}
	
	public Cliente pegarCliente(int rowIndex) {
		return this.listaDeClientes.get(rowIndex);
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public int getRowCount() {
		return this.listaDeClientes.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex) {
			case 0:
				return this.listaDeClientes.get(rowIndex).getCpf();
			case 1:
				return this.listaDeClientes.get(rowIndex).getNome();
			case 2:
				return this.listaDeClientes.get(rowIndex).getLogradouro();
			case 3:
				return this.listaDeClientes.get(rowIndex).getBairro();
			case 4:
				return this.listaDeClientes.get(rowIndex).getTelefone();
			default:
				return this.listaDeClientes.get(rowIndex);
		}
		
		
	}
	
	public String getColumnName(int columnIndex) {
		return this.colunas[columnIndex];
	}

}
