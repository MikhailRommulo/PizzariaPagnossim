package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.bean.Item;
import model.bean.Pedido;
import model.bean.Produto;
import model.connection.ConnectionFactory;

public class ItemDAO {
	
	public void create(Item i) throws SQLException {
		
		Connection con = ConnectionFactory.conectar();
        
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO itempedido (codProd,codPed,quantidade)VALUES(?,?,?)");
            stmt.setInt(1, i.getProduto().getCodProd());
            stmt.setLong(2, i.getPedido().getCodPedido());
            stmt.setInt(3, i.getQuantidade());            

            stmt.executeUpdate();

        }catch (SQLException ex) {
            System.out.println(ex);
        } 
        
	}
	
	public List<Item> readPedido(int codigo) throws SQLException {
		
		 Connection con = ConnectionFactory.conectar();
	        
	     PreparedStatement stmt = null;
	     ResultSet rs = null;
	     
	     List<Item> itens = new ArrayList<Item>();
	     
	     PedidoDAO pd = new PedidoDAO();
	     Pedido pedido = pd.readUnique(codigo);
	     
	     stmt = con.prepareStatement("SELECT * FROM itempedido WHERE codPed = ?");
	     stmt.setInt(1, codigo);
	     
	     rs = stmt.executeQuery();
	     
	     while(rs.next()) {
	    	 Item item = new Item();
	    	 
	    	 item.setPedido(pedido);
	    	 ProdutoDAO pdao = new ProdutoDAO();
	    	 Produto p = pdao.readUnique(rs.getInt("codProd"));
	    	 item.setProduto(p);
	    	 item.setQuantidade(rs.getInt("quantidade"));
	    	 
	    	 itens.add(item);   	 
	    	 
	     }
	     
	     return itens;
	}
	
	public void delete(Item i) throws SQLException {

        Connection con = ConnectionFactory.conectar();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM itempedido WHERE codPed = ?");
            stmt.setInt(1, i.getPedido().getCodPedido());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } 

    }
}
