package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import model.bean.Cliente;
import model.bean.Item;
import model.bean.Pedido;
import model.bean.Produto;
import model.connection.ConnectionFactory;

public class PedidoDAO {
	
	public void create(Pedido p) throws SQLException {
		
		Connection con = ConnectionFactory.conectar();
        
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO pedido (codCli,status,precoPedido)VALUES(?,?,?)");
            stmt.setLong(1, p.getCliente().getCpf());
            stmt.setString(2, p.getStatus());
            stmt.setDouble(3, p.getPreco());            

            stmt.executeUpdate();          
            
        }catch (SQLException ex) {
            System.out.println(ex);
        } 
        
	}
	
	public List<Pedido> read() throws SQLException {

        Connection con = ConnectionFactory.conectar();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Pedido> pedidos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM pedido");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Pedido pedido = new Pedido();

                ClienteDAO cd = new ClienteDAO();
                Cliente cliente = cd.readUnique(rs.getLong("codCli"));
                pedido.setCodPedido(rs.getInt("codPed"));
                cliente.setCpf(rs.getLong("codCli"));
                pedido.setCliente(cliente);
                pedido.setStatus(rs.getString("status"));
                pedido.setPreco(rs.getDouble("precoPedido"));
                pedidos.add(pedido);
                
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pedidos;

    }
	
	public List<Pedido> readOrder() throws SQLException {

        Connection con = ConnectionFactory.conectar();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Pedido> pedidos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM pedido ORDER BY codPed DESC");
            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	Pedido pedido = new Pedido();
            	
            	pedido.setCodPedido(rs.getInt("codPed"));
		    	ClienteDAO cd = new ClienteDAO();
		    	Cliente c = cd.readUnique(rs.getLong("codCli"));
		    	pedido.setCliente(c);
		    	pedido.setStatus(rs.getString("status"));
		    	pedido.setPreco(rs.getDouble("precoPedido"));
		    	pedidos.add(pedido);
                
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 

        return pedidos;

    }
	
	public List<Pedido> readStatus(String situacao) throws SQLException {

        Connection con = ConnectionFactory.conectar();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Pedido> pedidos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM pedido WHERE status = ? ORDER BY codPed DESC");
            stmt.setString(1, situacao);
            rs = stmt.executeQuery();

            while (rs.next()) {
            	
            	Pedido pedido = new Pedido();
            	
            	pedido.setCodPedido(rs.getInt("codPed"));
		    	ClienteDAO cd = new ClienteDAO();
		    	Cliente c = cd.readUnique(rs.getLong("codCli"));
		    	pedido.setCliente(c);
		    	pedido.setStatus(rs.getString("status"));
		    	pedido.setPreco(rs.getDouble("precoPedido"));
		    	pedidos.add(pedido);
                
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 

        return pedidos;

    }
	
	public Pedido readUnique(int codPed) throws SQLException {
		 
		Connection con = ConnectionFactory.conectar();
	        
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    
	    Pedido pedido = new Pedido();
	    
	    try {
			stmt = con.prepareStatement("SELECT * FROM pedido WHERE codPed = ?");
			stmt.setInt(1, codPed);
		        
		    rs = stmt.executeQuery();
		    
		    if (rs.next()) {       
		    	pedido.setCodPedido(rs.getInt("codPed"));
		    	ClienteDAO cd = new ClienteDAO();
		    	Cliente c = cd.readUnique(rs.getLong("codCli"));
		    	pedido.setCliente(c);
		    	pedido.setStatus(rs.getString("status"));
		    	pedido.setPreco(rs.getDouble("precoPedido"));
		    	
		    }
		    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
	     
	    return pedido;
	}
	
	
	public void update(Pedido p) throws SQLException {

        Connection con = ConnectionFactory.conectar();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE pedido SET codCli = ?, status = ? ,precoPedido = ? WHERE codPed = ?");            
            stmt.setLong(1, p.getCliente().getCpf());
            stmt.setString(2, p.getStatus());
            stmt.setDouble(3, p.getPreco());
            stmt.setInt(4, p.getCodPedido());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Pedido atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } 

    }
	
	public void updateCreate(Pedido p) throws SQLException {

        Connection con = ConnectionFactory.conectar();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE pedido SET codCli = ?, status = ? ,precoPedido = ? WHERE codPed = ?");            
            stmt.setLong(1, p.getCliente().getCpf());
            stmt.setString(2, p.getStatus());
            stmt.setDouble(3, p.getPreco());
            stmt.setInt(4, p.getCodPedido());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Pedido criado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        }

    }
	
	public void delete(Pedido p) throws SQLException {

        Connection con = ConnectionFactory.conectar();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM pedido WHERE codPed = ?");
            stmt.setInt(1, p.getCodPedido());

            stmt.executeUpdate();
            
            for(Item i: p.getItens()) {
            	ItemDAO id = new ItemDAO();
            	id.delete(i);
            }

            JOptionPane.showMessageDialog(null, "Pedido excluido com sucesso !");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } 

    }
	
}
