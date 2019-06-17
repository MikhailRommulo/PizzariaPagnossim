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

import model.bean.Funcionario;
import model.bean.Usuario;
import model.connection.ConnectionFactory;


public class FuncionarioDAO {

    public void create(Funcionario f) throws SQLException {
        
        Connection con = ConnectionFactory.conectar();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO funcionario (cpf,nome,telefone,nivel,funcao)VALUES(?,?,?,?,?)");
            stmt.setLong(1, f.getCpf());
            stmt.setString(2, f.getNome());
            stmt.setInt(3, f.getTelefone());
            stmt.setInt(4, f.getNivel());
            stmt.setString(5, f.getFuncao());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Funcionario cadastrado com sucesso !");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
        	ConnectionFactory.fechar();
        }

    }

    
    // Lista de medicamentos
    public List<Funcionario> read() throws SQLException {

        Connection con = ConnectionFactory.conectar();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Funcionario> funcionarios = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM funcionario");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Funcionario funcionario = new Funcionario();

               
                funcionario.setNome(rs.getString("nome"));
                funcionario.setTelefone(rs.getInt("telefone"));
                funcionario.setNivel(rs.getInt("nivel"));
                funcionario.setFuncao(rs.getString("funcao"));
                funcionarios.add(funcionario);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        	ConnectionFactory.fechar();
        }

        return funcionarios;

    }
    public List<Funcionario> readForDesc(String desc) throws SQLException {

        Connection con = ConnectionFactory.conectar();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Funcionario> funcionarios = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM funcionario WHERE nome LIKE ?");
            stmt.setString(1, "%"+desc+"%");
            
            rs = stmt.executeQuery();

            while (rs.next()) {

                Funcionario funcionario = new Funcionario();

                funcionario.setCpf(rs.getLong("cpf"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setTelefone(rs.getInt("telefone"));
                funcionario.setNivel(rs.getInt("nivel"));
                funcionario.setFuncao(rs.getString("funcao"));
                funcionarios.add(funcionario);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        	ConnectionFactory.fechar();
        }

        return funcionarios;

    }

    public void update(Funcionario f) throws SQLException {

        Connection con = ConnectionFactory.conectar();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE funcionario SET nome = ? ,telefone = ?,nivel = ?, funcao = ? WHERE cpf = ?");
            stmt.setString(1, f.getNome());
            stmt.setInt(2, f.getTelefone());
            stmt.setInt(3, f.getNivel());
            stmt.setString(4, f.getFuncao());
            stmt.setLong(5, f.getCpf());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Funcionario atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
        	ConnectionFactory.fechar();
        }

    }
    public void delete(Funcionario f) throws SQLException {

        Connection con = ConnectionFactory.conectar();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM funcionario WHERE cpf = ?");
            stmt.setLong(1, f.getCpf());
            
            Usuario u = new Usuario();
            UsuarioDAO ud = new UsuarioDAO();
            u = ud.readUnique(f.getCpf());
            ud.delete(u);
            

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "funcionario excluido com sucesso !");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
        	ConnectionFactory.fechar();
        }

    }
    
    public Funcionario readUnique(Long cpf) throws SQLException {

        Connection con = ConnectionFactory.conectar();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Funcionario funcionario = new Funcionario();

        try {
            stmt = con.prepareStatement("SELECT * FROM funcionario WHERE cpf = ?");
            stmt.setLong(1, cpf);
            
            rs = stmt.executeQuery();

            if (rs.next()) {             

            	funcionario.setCpf(rs.getLong("cpf"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setTelefone(rs.getInt("telefone"));
                funcionario.setNivel(rs.getInt("nivel"));
                funcionario.setFuncao(rs.getString("funcao"));
               
            }

        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        	ConnectionFactory.fechar();
        }

        return funcionario;

    }
    
}
