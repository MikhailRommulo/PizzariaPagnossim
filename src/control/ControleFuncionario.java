package control;

import java.sql.SQLException;

import model.bean.Funcionario;
import model.dao.FuncionarioDAO;

public class ControleFuncionario {
	
	public void editarFuncionario(long cpf, String nome, int telefone, String funcao) {
		FuncionarioDAO fd = new FuncionarioDAO();
		Funcionario f = new Funcionario();
		try {
			f = fd.readUnique(cpf);
			f.setNome(nome);
			f.setTelefone(telefone);
			f.setFuncao(funcao);
			f.setNivel(selecionarNivel(f.getFuncao()));
			fd.update(f);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void cadastarFuncionario(long cpf, String nome, int telefone, String funcao) {
		int nivel = selecionarNivel(funcao);
		Funcionario funcionario = new Funcionario(cpf, nome, telefone,nivel,funcao);
		FuncionarioDAO cadastrar = new FuncionarioDAO();
		try {
			cadastrar.create(funcionario);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void excluirFuncionario(long cpf) {
		FuncionarioDAO fd = new FuncionarioDAO();
		Funcionario f = new Funcionario();
		try {
			f = fd.readUnique(cpf);
			fd.delete(f);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public int selecionarNivel(String funcao) {
		int nivel = 0;
		if(funcao == "Pizzaiolo" || funcao == "Entregador" || funcao == "Atendente") {
			nivel = 1;
		}else{
			nivel = 2;
		}
		return nivel;
	}
}
