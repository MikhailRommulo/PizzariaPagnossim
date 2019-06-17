package control;

import model.bean.Funcionario;
import model.bean.Produto;
import view.EditarCardapio;
import view.EditarFuncionario;

public class TabelaProduto {
	
	public void editarProduto(Produto p) {
		EditarCardapio editar = new EditarCardapio();
		editar.receberProduto(p);
		editar.setVisible(true);
	}
	
	public void excluirProduto(Produto p) {
		ControleProduto cp = new ControleProduto();
		cp.excluirProduto(p.getCodProd());		
	}
}
