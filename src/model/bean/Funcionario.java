package model.bean;

public class Funcionario {


    private long cpf;
    private String nome;
    private int telefone;
    private int nivel;
    private String funcao; 

    
    public Funcionario(long cpf, String nome, int telefone, int nivel, String funcao) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.setTelefone(telefone);
		this.nivel = nivel;
		this.funcao = funcao;
	}


	public Funcionario() {
        
    }
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

	public long getCpf() {
		return cpf;
	}

	public void setCpf(long cpf) {
		this.cpf = cpf;
	}


	public int getTelefone() {
		return telefone;
	}


	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}


	@Override
	public String toString() {
		return "Funcionario [cpf=" + cpf + ", nome=" + nome + ", telefone=" + telefone + ", nivel=" + nivel
				+ ", funcao=" + funcao + "]";
	}
	
	

}
