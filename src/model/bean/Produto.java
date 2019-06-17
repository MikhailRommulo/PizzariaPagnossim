
package model.bean;


public class Produto {
    private int codProd;
    private String tipo;
    private String descricao;
    private double preco;
    private int quantidade;
    
	public Produto(String tipo, String descricao, double preco, int quantidade) {
		super();
		this.tipo = tipo;
		this.descricao = descricao;
		this.preco = preco;
		this.quantidade = quantidade;
	}
	public Produto() {
		
	}
	public int getCodProd() {
		return codProd;
	}
	public void setCodProd(int codProd) {
		this.codProd = codProd;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
    
    
}
