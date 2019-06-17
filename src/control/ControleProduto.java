package control;

import java.sql.SQLException;

import model.bean.Cliente;
import model.bean.Produto;
import model.dao.ClienteDAO;
import model.dao.ProdutoDAO;

public class ControleProduto {
	
	public void cadastrarProduto(String tipo, String descricao, double preco, int quantidade) {
		Produto produto = new Produto(tipo,descricao,preco,quantidade);
		ProdutoDAO cadastrar = new ProdutoDAO();
		try {
			cadastrar.create(produto);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void editarProduto(int codProd, String descricao, String tipo, double valor, int quantidade) {
		ProdutoDAO pd = new ProdutoDAO();
		Produto p = new Produto();
		try {
			p = pd.readUnique(codProd);
			p.setDescricao(descricao);
			p.setTipo(tipo);
			p.setPreco(valor);
			p.setQuantidade(quantidade);
			pd.update(p);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void excluirProduto(int codProd) {
		ProdutoDAO pd = new ProdutoDAO();
		Produto p = new Produto();
		try {
			p = pd.readUnique(codProd);
			pd.delete(p);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
