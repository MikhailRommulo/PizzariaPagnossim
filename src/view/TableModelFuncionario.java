package view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.bean.Funcionario;

public class TableModelFuncionario extends AbstractTableModel{
	
	private List<Funcionario> listeDeFuncionarios;
	private String[] colunas = {"CPF","Nome","Telefone","Função"};
	
	public TableModelFuncionario() {
		this.listeDeFuncionarios = new ArrayList<>();
	}
	
	public void pegarListaFuncionarios(List<Funcionario> list) {
		this.listeDeFuncionarios = list;
		fireTableDataChanged();
	}
	
	public Funcionario pegarFuncionario(int rowIndex) {
		return this.listeDeFuncionarios.get(rowIndex);
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public int getRowCount() {
		return this.listeDeFuncionarios.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex) {
			case 0:
				return this.listeDeFuncionarios.get(rowIndex).getCpf();
			case 1:
				return this.listeDeFuncionarios.get(rowIndex).getNome();
			case 2:
				return this.listeDeFuncionarios.get(rowIndex).getTelefone();
			case 3:
				return this.listeDeFuncionarios.get(rowIndex).getFuncao();
			default:
				return this.listeDeFuncionarios.get(rowIndex);
		}
		
		
	}
	
	public String getColumnName(int columnIndex) {
		return this.colunas[columnIndex];
	}

}
