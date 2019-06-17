/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import model.connection.ConnectionFactory;


public class ClienteDAO {

    public void create(Cliente c) throws SQLException {
        
        Connection con = ConnectionFactory.conectar();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO cliente (cpf,nome,telefone,logradouro,bairro,cidade,estado,email)VALUES(?,?,?,?,?,?,?,?)");
            stmt.setLong(1, c.getCpf());
            stmt.setString(2, c.getNome());
            stmt.setInt(3, c.getTelefone());
            stmt.setString(4, c.getLogradouro());
            stmt.setString(5, c.getBairro());
            stmt.setString(6, c.getCidade());
            stmt.setString(7, c.getEstado());
            stmt.setString(8, c.getEmail());
            

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso !");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
        	ConnectionFactory.fechar();
        }

    }

    
    // Lista de medicamentos
    public List<Cliente> read() throws SQLException {

        Connection con = ConnectionFactory.conectar();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Cliente> clientes = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM cliente");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Cliente cliente = new Cliente();

                cliente.setId(rs.getInt("id"));
                cliente.setCpf(rs.getLong("cpf"));
                cliente.setNome(rs.getString("nome"));
                cliente.setTelefone(rs.getInt("telefone"));
                cliente.setNome(rs.getString("nome"));
                cliente.setNome(rs.getString("nome"));
                cliente.setNome(rs.getString("nome"));
                cliente.setNome(rs.getString("nome"));
                cliente.setNome(rs.getString("nome"));
                clientes.add(cliente);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        	ConnectionFactory.fechar();
        }

        return clientes;

    }
    public List<Cliente> readForDesc(String desc) throws SQLException {

        Connection con = ConnectionFactory.conectar();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Cliente> clientes = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM cliente WHERE nome LIKE ?");
            stmt.setString(1, "%"+desc+"%");
            
            rs = stmt.executeQuery();

            while (rs.next()) {

                Cliente cliente = new Cliente();

                cliente.setCpf(rs.getLong("cpf"));
                cliente.setNome(rs.getString("nome"));
                cliente.setTelefone(rs.getInt("telefone"));
                cliente.setLogradouro(rs.getString("logradouro"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setEstado(rs.getString("estado"));
                cliente.setEmail(rs.getString("email"));
                clientes.add(cliente);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        	ConnectionFactory.fechar();
        }

        return clientes;

    }

    public void update(Cliente c) throws SQLException {

        Connection con = ConnectionFactory.conectar();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE cliente SET nome = ?,telefone = ?, logradouro = ? ,bairro = ? ,cidade = ? ,estado = ? ,email = ? WHERE cpf = ?");            
            stmt.setString(1, c.getNome());
            stmt.setInt(2, c.getTelefone());
            stmt.setString(3, c.getLogradouro());
            stmt.setString(4, c.getBairro());
            stmt.setString(5, c.getCidade());
            stmt.setString(6, c.getEstado());
            stmt.setString(7, c.getEmail());
            stmt.setLong(8, c.getCpf());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
        	ConnectionFactory.fechar();
        }

    }
    public void delete(Cliente c) throws SQLException {

        Connection con = ConnectionFactory.conectar();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM cliente WHERE cpf = ?");
            stmt.setLong(1, c.getCpf());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso !");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
        	ConnectionFactory.fechar();
        }

    }
    
    public Cliente readUnique(Long cpf) throws SQLException {

        Connection con = ConnectionFactory.conectar();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Cliente cliente = new Cliente();

        try {
            stmt = con.prepareStatement("SELECT * FROM cliente WHERE cpf = ?");
            stmt.setLong(1, cpf);
            
            rs = stmt.executeQuery();

            if (rs.next()) {         

            	cliente.setCpf(rs.getLong("cpf"));
                cliente.setNome(rs.getString("nome"));
                cliente.setTelefone(rs.getInt("telefone"));
                cliente.setLogradouro(rs.getString("Logradouro"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setEstado(rs.getString("estado"));
                cliente.setEmail(rs.getString("email"));
               
            }

        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        	ConnectionFactory.fechar();
        }

        return cliente;
    }

}
