package model.bean;


public class Item {
    private Produto produto;
    private Pedido pedido;
    private int quantidade;
    
	public Item(Produto produto, Pedido pedido, int quantidade) {
		super();
		this.produto = produto;
		this.pedido = pedido;
		this.quantidade = quantidade;
	}
	
	public Item() {
		
	}
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public double precoTotal() {
		double total = produto.getPreco()*quantidade;
		return total;	
	}
    
}
