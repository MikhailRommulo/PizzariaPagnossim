package model.bean;

import java.util.List;

public class Pedido {
    
    private int codPedido;
    private Cliente cliente;
    private List<Item> itens;
    private String status;
    private double preco;
    
    public Pedido(Cliente cliente, String status) {
		super();
		this.cliente = cliente;
		this.status = status;
		
	}

    public Pedido() {
        
    }

    public int getCodPedido() {
        return codPedido;
    }

    public void setCodPedido(int codPedido) {
        this.codPedido = codPedido;
    }

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(List<Item> itens) {
		double precoAcumulador=0;
		for(Item i: itens) {
			precoAcumulador +=i.precoTotal();
		}
		this.preco = precoAcumulador;
	}
	
	public void setPreco(double preco) {		
		this.preco = preco;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}
    
}
