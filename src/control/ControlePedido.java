package control;

import java.sql.SQLException;
import java.util.List;

import model.bean.Cliente;
import model.bean.Item;
import model.bean.Pedido;
import model.connection.ConnectionFactory;
import model.dao.ItemDAO;
import model.dao.PedidoDAO;
import view.TelaPedido;

public class ControlePedido {
	
	public Pedido cadastrarPedido(Cliente c, List<Item> li, String status) {
		Pedido p = new Pedido(c,status);
		PedidoDAO pd = new PedidoDAO();
		try {
			pd.create(p);
			p = pd.readOrder().get(0);
			for(Item i:li) {
				i.setPedido(p);
				ItemDAO id = new ItemDAO();
				id.create(i);
			}
			p.setItens(li);
			p.setPreco(li);
			pd.updateCreate(p);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}
	
	public void levarPedidoParaTela(Pedido p) {
		TelaPedido tela = new TelaPedido();
		tela.receberPedido(p);
		tela.setVisible(true);
	}
	
	public Pedido editarPedido(Cliente c, List<Item> li, String status, int codigo) {
		Pedido p = new Pedido(c,status);
		p.setCodPedido(codigo);
		PedidoDAO pd = new PedidoDAO();
		ItemDAO idexcluir = new ItemDAO();
		try {	
			Item item = li.get(0);
			item.setPedido(p);
			idexcluir.delete(item);
			
			for(Item i:li) {
				i.setPedido(p);
				ItemDAO id = new ItemDAO();
				id.create(i);
			}
			p.setItens(li);
			p.setPreco(li);
			pd.update(p);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return p;
		
	}
	
	
	
	public void excluirPedido(Pedido pedido) {
		PedidoDAO pd = new PedidoDAO();
		try {
			pd.delete(pedido);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConnectionFactory.fechar();
		}
	}
	
	
}
