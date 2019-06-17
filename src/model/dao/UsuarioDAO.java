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

public class UsuarioDAO {
	 public void create(Usuario u) throws SQLException {
	        
	        Connection con = ConnectionFactory.conectar();
	        
	        PreparedStatement stmt = null;

	        try {
	            stmt = con.prepareStatement("INSERT INTO usuario (cpfFun,senha)VALUES(?,?)");
	            stmt.setLong(1, u.getUsuario());
	            stmt.setString(2, u.getSenha());	            

	            stmt.executeUpdate();

	            JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso !");
	        } catch (SQLException ex) {
	            System.out.println(ex);
	        } finally {
	        	ConnectionFactory.fechar();
	        }

	    }
	 
	 public void delete(Usuario u) throws SQLException {
		 
		 Connection con = ConnectionFactory.conectar();
	        
	     PreparedStatement stmt = null;
	     try {
	    	 stmt = con.prepareStatement("DELETE FROM usuario WHERE codUsu = ?");
	    	 stmt.setInt(1, u.getCodUsu());
	     
	    	 stmt.executeUpdate();
	     
	    	 JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso !");
	     }catch(SQLException eu) {
	    	 System.out.println(eu);
	     }finally {
	        	ConnectionFactory.fechar();
	     }
	 }
	 
	 public List<Usuario> read() throws SQLException {

	        Connection con = ConnectionFactory.conectar();
	        
	        PreparedStatement stmt = null;
	        ResultSet rs = null;

	        List<Usuario> usuarios = new ArrayList<>();

	        try {
	            stmt = con.prepareStatement("SELECT * FROM usuario");
	            rs = stmt.executeQuery();

	            while (rs.next()) {

	                Usuario usuario = new Usuario();
	                
	                usuario.setCodUsu(rs.getInt("codUsu"));
	                usuario.setUsuario(rs.getLong("cpfFun"));
	                usuario.setSenha(rs.getString("senha"));
	                usuarios.add(usuario);
	            }

	        } catch (SQLException ex) {
	            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
	        } finally {
	        	ConnectionFactory.fechar();
	        }

	        return usuarios;

	    }
	 
	 public Usuario readUnique(Long cpfFun) throws SQLException {
		 
		 Connection con = ConnectionFactory.conectar();
	        
	     PreparedStatement stmt = null;
	     ResultSet rs = null;
	     
	     Usuario usuario = new Usuario();
	     
	     try {
	            stmt = con.prepareStatement("SELECT * FROM usuario WHERE cpfFun = ?");
	            stmt.setLong(1, cpfFun);
	            
	            rs = stmt.executeQuery();

	            if (rs.next()) {             

	            	usuario.setCodUsu(rs.getInt("codUsu"));
	            	usuario.setUsuario(rs.getLong("cpfFun"));
	            	usuario.setSenha(rs.getString("senha"));
	               
	            }

	        } catch (SQLException ex) {
	            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
	        } finally {
	        	ConnectionFactory.fechar();
	        }
	        
		 return usuario;
	 }
	 
	 
}
