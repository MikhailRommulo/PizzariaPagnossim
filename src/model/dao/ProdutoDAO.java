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

import model.bean.Produto;
import model.connection.ConnectionFactory;

public class ProdutoDAO {
public void create(Produto p) throws SQLException {
        
        Connection con = ConnectionFactory.conectar();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO produto (tipo,descricao,preco,quantidade)VALUES(?,?,?,?)");            
            stmt.setString(1, p.getTipo());
            stmt.setString(2, p.getDescricao());
            stmt.setDouble(3, p.getPreco());
            stmt.setInt(4, p.getQuantidade());            

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso !");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
        	ConnectionFactory.fechar();
        }

    }

    
    // Lista de medicamentos
    public List<Produto> read() throws SQLException {

        Connection con = ConnectionFactory.conectar();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Produto> produtos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM produto");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Produto produto = new Produto();

                produto.setCodProd(rs.getInt("codProd"));
                produto.setTipo(rs.getString("tipo"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setQuantidade(rs.getInt("quantidade"));
                
                produtos.add(produto);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        	ConnectionFactory.fechar();
        }

        return produtos;

    }
    
    public List<Produto> readOrder() throws SQLException {

        Connection con = ConnectionFactory.conectar();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Produto> produtos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM produto ORDER BY codProd DESC");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Produto produto = new Produto();

                produto.setCodProd(rs.getInt("codProd"));
                produto.setTipo(rs.getString("tipo"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setQuantidade(rs.getInt("quantidade"));
                
                produtos.add(produto);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        	ConnectionFactory.fechar();
        }

        return produtos;

    }
    public List<Produto> readForDesc(String desc) throws SQLException {

        Connection con = ConnectionFactory.conectar();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Produto> produtos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM produto WHERE descricao LIKE ?");
            stmt.setString(1, "%"+desc+"%");
            
            rs = stmt.executeQuery();

            while (rs.next()) {

                Produto produto = new Produto();

                produto.setCodProd(rs.getInt("codProd"));
                produto.setTipo(rs.getString("tipo"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produtos.add(produto);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        	ConnectionFactory.fechar();
        }

        return produtos;

    }

    public void update(Produto p) throws SQLException {

        Connection con = ConnectionFactory.conectar();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE produto SET tipo = ?, descricao = ? ,preco = ? ,quantidade = ? WHERE codProd = ?");            
            stmt.setString(1, p.getTipo());
            stmt.setString(2, p.getDescricao());
            stmt.setDouble(3, p.getPreco());
            stmt.setInt(4, p.getQuantidade());
            stmt.setInt(5, p.getCodProd());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
        	ConnectionFactory.fechar();
        }

    }
    public void delete(Produto p) throws SQLException {

        Connection con = ConnectionFactory.conectar();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM produto WHERE codProd = ?");
            stmt.setInt(1, p.getCodProd());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Produto excluido com sucesso !");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
        	ConnectionFactory.fechar();
        }

    }
    
    public Produto readUnique(int codProd) throws SQLException {

        Connection con = ConnectionFactory.conectar();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Produto produto = new Produto();

        try {
            stmt = con.prepareStatement("SELECT * FROM produto WHERE codProd = ?");
            stmt.setInt(1, codProd);
            
            rs = stmt.executeQuery();

            if (rs.next()) {         

            	produto.setCodProd(rs.getInt("codProd"));
                produto.setTipo(rs.getString("tipo"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setQuantidade(rs.getInt("quantidade"));
               
            }

        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        	ConnectionFactory.fechar();
        }

        return produto;
    }
}
