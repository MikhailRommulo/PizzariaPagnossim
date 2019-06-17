package control;

import model.bean.Cliente;
import model.bean.Funcionario;
import view.EditarCliente;
import view.EditarFuncionario;

public class TabelaCliente {
	
	public void editarCliente(Cliente c) {
		EditarCliente editar = new EditarCliente();
		editar.receberCliente(c);
		editar.setVisible(true);
	}
	
	public void excluirCliente(Cliente c) {
		ControleCliente cc = new ControleCliente();
		cc.excluirCliente(c.getCpf());
	}
	
}
