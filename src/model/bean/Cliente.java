package model.bean;

public class Cliente {

    private int id;
    private long cpf;
    private String nome;
    private int Telefone;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String estado;
    private String email;

    public Cliente(long cpf, String nome, int Telefone, String logradouro, String bairro, String cidade, String estado, String email) {
        this.cpf = cpf;
        this.nome = nome;
        this.Telefone = Telefone;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.email = email;
    }

    public Cliente() {
        
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTelefone() {
        return Telefone;
    }

    public void setTelefone(int Telefone) {
        this.Telefone = Telefone;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
	public String toString() {
		return "Cliente [cpf=" + cpf + ", nome=" + nome + ", Telefone=" + Telefone + ", logradouro=" + logradouro
				+ ", bairro=" + bairro + ", cidade=" + cidade + ", estado=" + estado + ", email=" + email + "]";
	}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }   
    

}
