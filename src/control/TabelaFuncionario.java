package control;

import model.bean.Funcionario;
import view.EditarFuncionario;

public class TabelaFuncionario {
	
	public void editarFuncionario(Funcionario f) {
		EditarFuncionario editar = new EditarFuncionario();
		editar.receberFuncionario(f);
		editar.setVisible(true);
	}
	
	public void excluirFuncionario(Funcionario f) {
		ControleFuncionario cf = new ControleFuncionario();
		cf.excluirFuncionario(f.getCpf());		
	}
    
}
