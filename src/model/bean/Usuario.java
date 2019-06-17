/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

public class Usuario {
    
	private int codUsu;
	private long usuario;
    private String senha;
    
	public Usuario(long usuario, String senha) {
		super();
		this.usuario = usuario;
		this.senha = senha;
	}
	
	public Usuario() {
		
	}
	
	public long getUsuario() {
		return usuario;
	}
	public void setUsuario(long usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public int getCodUsu() {
		return codUsu;
	}
	public void setCodUsu(int codUsu) {
		this.codUsu = codUsu;
	}
    
}
