package control;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import model.bean.Usuario;
import model.dao.UsuarioDAO;

public class ControleUsuario {
	
	public void cadastrarUsuario(long usuario, String senha) {
		Usuario u = new Usuario(usuario, senha);
		UsuarioDAO ud = new UsuarioDAO();
		try {
			ud.create(u);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean login(Usuario usuario) {
		boolean verificado = false;
		UsuarioDAO ud = new UsuarioDAO();
		Usuario uv = new Usuario();
		try {
			uv = ud.readUnique(usuario.getUsuario());
			if(uv.getSenha().equals(usuario.getSenha())) {
				verificado = true;
				JOptionPane.showMessageDialog(null, "Login realizado !");
			}else {
				JOptionPane.showMessageDialog(null, "Senha incorreta !");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Usuário não encontrado !");
			e.printStackTrace();
		}
		return verificado;
	}
}
