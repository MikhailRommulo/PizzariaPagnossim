package control;

import java.sql.SQLException;

import model.bean.Cliente;
import model.bean.Funcionario;
import model.dao.ClienteDAO;
import model.dao.FuncionarioDAO;

public class ControleCliente {
	
	public void cadastrarCliente(String cpf, String nome, int telefone, String logradouro, String bairro, String cidade, String estado, String email){
		Cliente cliente = new Cliente(Long.parseLong(cpf),nome,telefone, logradouro, bairro, cidade, estado, email);
		ClienteDAO cadastrar = new ClienteDAO();
		try {
			cadastrar.create(cliente);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void editarCliente(long cpf, String nome, int telefone, String logradouro, String bairro, String cidade, String estado, String email) {
		ClienteDAO cd = new ClienteDAO();
		Cliente c = new Cliente();
		try {
			c = cd.readUnique(cpf);
			c.setNome(nome);
			c.setTelefone(telefone);
			c.setLogradouro(logradouro);
			c.setBairro(bairro);
			c.setCidade(cidade);
			c.setEstado(estado);
			c.setEmail(email);
			cd.update(c);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void excluirCliente(long cpf) {
		ClienteDAO cd = new ClienteDAO();
		Cliente c = new Cliente();
		try {
			c = cd.readUnique(cpf);
			cd.delete(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
