/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

import model.bean.Cliente;
import model.bean.Item;
import model.bean.Pedido;
import model.bean.Produto;
import model.dao.ClienteDAO;
import model.dao.ItemDAO;
import model.dao.PedidoDAO;
import model.dao.ProdutoDAO;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import control.ControlePedido;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.Font;

public class TelaPedido extends javax.swing.JFrame {
	private TableModelCliente tableModelCliente;
	private TableModelProduto tableModelProduto;
	private TableModelItem tableModelItem;
	private Cliente cliente;
	private List<Item> itens;
	private Pedido pedido;
	private boolean paraEdicao;
	private JComboBox comboMudarSituacao;
	 
	 
    public TelaPedido() {
        initComponents();
        this.tableModelCliente = new TableModelCliente();
        this.tableClientes.setModel(tableModelCliente);
        
        this.tableModelProduto = new TableModelProduto();
        this.tableProdutos.setModel(tableModelProduto);
        
        this.tableModelItem = new TableModelItem();
        this.tableItens.setModel(tableModelItem);
        
        jPanelFazerPedido.setLayout(null);
        jPanelFazerPedido.add(scrollPane);
        jPanelFazerPedido.add(lblCliente);
        jPanelFazerPedido.add(textNomeCliente);
        jPanelFazerPedido.add(lblBusca);
        jPanelFazerPedido.add(textBuscaCliente);
        jPanelFazerPedido.add(btnSelecionar);
        jPanelFazerPedido.add(comboItens);
        jPanelFazerPedido.add(scrollPane_1);
        
        btnAdicionar = new JButton("Adicionar");
        btnAdicionar.setIcon(new ImageIcon("C:\\Users\\vagui\\Desktop\\PizzariaPagnossim\\icon\\addicon.png"));
        btnAdicionar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		Produto p = tableModelProduto.pegarProduto(tableProdutos.getSelectedRow());
        		String item = p.getCodProd()+" | unidades: "+textQuantidadeItem.getText()+" | "+p.getDescricao(); 
        		comboItens.addItem(item);
        	}
        });
        btnAdicionar.setBounds(228, 417, 110, 32);
        jPanelFazerPedido.add(btnAdicionar);
        
        lblBusca_1 = new JLabel("Busca:");
        lblBusca_1.setFont(new Font("Georgia", Font.PLAIN, 15));
        lblBusca_1.setBounds(11, 248, 67, 14);
        jPanelFazerPedido.add(lblBusca_1);
        
        textBuscaProduto = new JTextField();
        textBuscaProduto.addKeyListener(new KeyAdapter() {
        @Override
        	public void keyReleased(KeyEvent arg0) {
        		ProdutoDAO busca = new ProdutoDAO();
        		try {
					tableModelProduto.pegarListaProdutos(busca.readForDesc(textBuscaProduto.getText()));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        });
        textBuscaProduto.setBounds(67, 242, 660, 27);
        jPanelFazerPedido.add(textBuscaProduto);
        textBuscaProduto.setColumns(10);
        
        lblQuantidade = new JLabel("Quantidade:");
        lblQuantidade.setFont(new Font("Georgia", Font.PLAIN, 15));
        lblQuantidade.setBounds(11, 427, 102, 14);
        jPanelFazerPedido.add(lblQuantidade);
        
        textQuantidadeItem = new JTextField();
        textQuantidadeItem.setBounds(114, 420, 92, 27);
        jPanelFazerPedido.add(textQuantidadeItem);
        textQuantidadeItem.setColumns(10);
        
        btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setIcon(new ImageIcon("C:\\Users\\vagui\\Desktop\\PizzariaPagnossim\\icon\\checkicon.png"));
        btnConfirmar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		List<Item> itensloop = new ArrayList<Item>();
    			for(int i = 0;i<comboItens.getItemCount();i++) {
    				comboItens.setSelectedIndex(i);
    				String itemSelecionado = (String) comboItens.getSelectedItem();
    				String itemProcura = itemSelecionado.substring(0,1);
    				String itemQuantidade = itemSelecionado.substring(14, 15);
    				Item item = new Item();
    				ProdutoDAO pd = new ProdutoDAO();
    				try {
    					Produto p = pd.readUnique(Integer.parseInt(itemProcura));
    					item.setProduto(p);
    					item.setQuantidade(Integer.parseInt(itemQuantidade));
    					itensloop.add(item);
    				} catch (NumberFormatException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				} catch (SQLException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    			
    			}
    			itens = itensloop;
    			String status = "Na fila";
    			ControlePedido cp = new ControlePedido();    			
    			
        		if(paraEdicao) {
        			pedido = cp.editarPedido(cliente, itens, status, pedido.getCodPedido());
        		}else {
        			pedido = cp.cadastrarPedido(cliente, itens, status);
        		}
        		confirmarPedido(pedido);
        	}
        });
        btnConfirmar.setBounds(489, 418, 108, 29);
        jPanelFazerPedido.add(btnConfirmar);
        
        btnRemover = new JButton("remover");
        btnRemover.setIcon(new ImageIcon("C:\\Users\\vagui\\Desktop\\PizzariaPagnossim\\icon\\trashicon.png"));
        btnRemover.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int index = comboItens.getSelectedIndex();
        		comboItens.removeItemAt(index);
        	}
        });
        btnRemover.setBounds(622, 420, 106, 28);
        jPanelFazerPedido.add(btnRemover);
    }
    
    public void confirmarPedido(Pedido pedido) {
    	textFecharNomeCliente.setText(pedido.getCliente().getNome());
    	textFecharRua.setText(pedido.getCliente().getLogradouro());
    	textFecharBairro.setText(pedido.getCliente().getBairro());
    	textSituacao.setText(pedido.getStatus());
    	textFecharTelefone.setText(String.valueOf(pedido.getCliente().getTelefone()));
    	textFecharTotal.setText(String.valueOf(pedido.getPreco()));
    	tableModelItem.pegarListaDeItens(pedido.getItens());
    }
    
    public void pedidoEmProducao(Pedido pedido) {
    	textFecharNomeCliente.setText(pedido.getCliente().getNome());
    	textFecharRua.setText(pedido.getCliente().getLogradouro());
    	textFecharBairro.setText(pedido.getCliente().getBairro());
    	textSituacao.setText(pedido.getStatus());
    	textFecharTelefone.setText(String.valueOf(pedido.getCliente().getTelefone()));
    	textFecharTotal.setText(String.valueOf(pedido.getPreco()));
    	ItemDAO id = new ItemDAO();
    	try {
			tableModelItem.pegarListaDeItens(id.readPedido(pedido.getCodPedido()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	this.pedido = pedido;
    }
    
    public void receberPedido(Pedido pedido) {
    	cliente = pedido.getCliente();
    	textNomeCliente.setText(pedido.getCliente().getNome());
    	List<Cliente> pedidoDoCliente = new ArrayList<Cliente>();
    	pedidoDoCliente.add(pedido.getCliente());
    	tableModelCliente.pegarListaClientes(pedidoDoCliente);
    	ItemDAO id = new ItemDAO();
    	try {
			for(Item i: id.readPedido(pedido.getCodPedido())) {
				String itemDoPedido = i.getProduto().getCodProd()+" | unidades: "+i.getQuantidade()+" | "+i.getProduto().getDescricao();
				comboItens.addItem(itemDoPedido);
				paraEdicao = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	this.pedido = pedido;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelFazerPedido = new javax.swing.JPanel();
        jPanelFecharPedido = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableItens = new javax.swing.JTable();
        jButtonFechar = new javax.swing.JButton();
        jButtonFechar.setIcon(new ImageIcon("C:\\Users\\vagui\\Desktop\\PizzariaPagnossim\\icon\\deleteicon.png"));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pedidos");
        
        lblCliente = new JLabel("Cliente:");
        lblCliente.setFont(new Font("Georgia", Font.PLAIN, 15));
        lblCliente.setBounds(23, 23, 80, 14);
        
        textNomeCliente = new JTextField();
        textNomeCliente.setBounds(88, 17, 645, 29);
        textNomeCliente.setEditable(false);
        textNomeCliente.setColumns(10);
        
        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 105, 722, 71);
        
        lblBusca = new JLabel("Busca:");
        lblBusca.setFont(new Font("Georgia", Font.PLAIN, 15));
        lblBusca.setBounds(23, 73, 79, 14);
        
        textBuscaCliente = new JTextField();
        textBuscaCliente.setBounds(88, 64, 645, 31);
        textBuscaCliente.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyReleased(KeyEvent arg0) {
        		ClienteDAO busca = new ClienteDAO();
        		try {
					tableModelCliente.pegarListaClientes(busca.readForDesc(textBuscaCliente.getText()));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        });
        textBuscaCliente.setColumns(10);
        
        btnSelecionar = new JButton("Selecionar");
        btnSelecionar.setIcon(new ImageIcon("C:\\Users\\vagui\\Desktop\\PizzariaPagnossim\\icon\\targeticon.png"));
        btnSelecionar.setBounds(11, 194, 114, 30);
        btnSelecionar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		cliente = tableModelCliente.pegarCliente(tableClientes.getSelectedRow());
        		textNomeCliente.setText(cliente.getNome());

        	}
        });
        
        comboItens = new JComboBox<String>();
        comboItens.setBounds(9, 289, 718, 29);
        
        scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(11, 330, 717, 72);
        
        tableProdutos = new JTable();
        scrollPane_1.setViewportView(tableProdutos);
        
        tableClientes = new JTable();
        scrollPane.setViewportView(tableClientes);

        jTabbedPane1.addTab("Criar/Editar", jPanelFazerPedido);

        tableItens.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        	}
        ));
        jScrollPane1.setViewportView(tableItens);

        jButtonFechar.setText("Fechar");
        jButtonFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        
        lblCliente_1 = new JLabel("Cliente:");
        
        textFecharNomeCliente = new JTextField();
        textFecharNomeCliente.setEditable(false);
        textFecharNomeCliente.setColumns(10);
        
        lblRua = new JLabel("Rua:");
        
        textFecharRua = new JTextField();
        textFecharRua.setEditable(false);
        textFecharRua.setColumns(10);
        
        lblBairro = new JLabel("Bairro:");
        
        textFecharBairro = new JTextField();
        textFecharBairro.setEditable(false);
        textFecharBairro.setColumns(10);
        
        lblTelefone = new JLabel("Telefone:");
        
        textFecharTelefone = new JTextField();
        textFecharTelefone.setEditable(false);
        textFecharTelefone.setColumns(10);
        
        lblEstado = new JLabel("Situa\u00E7\u00E3o");
        
        textSituacao = new JTextField();
        textSituacao.setEditable(false);
        textSituacao.setColumns(10);
        
        JLabel lblTotal = new JLabel("Total:");
        
        textFecharTotal = new JTextField();
        textFecharTotal.setEditable(false);
        textFecharTotal.setColumns(10);
        
        JButton btnConfirmarEntrega = new JButton("mudar situa\u00E7\u00E3o");
        btnConfirmarEntrega.setIcon(new ImageIcon("C:\\Users\\vagui\\Desktop\\PizzariaPagnossim\\icon\\refreshicon.png"));
        btnConfirmarEntrega.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String status = (String) comboMudarSituacao.getSelectedItem();
        		pedido.setStatus(status);
        		PedidoDAO pd = new PedidoDAO();
        		try {
					pd.update(pedido);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		textSituacao.setText(status);
        		
        	}
        });
        
        comboMudarSituacao = new JComboBox();
        comboMudarSituacao.setModel(new DefaultComboBoxModel(new String[] {"Todos", "Na fila", "Em produ\u00E7\u00E3o", "Fila para entregar", "Saiu para entregar", "Entregue"}));

        javax.swing.GroupLayout gl_jPanelFecharPedido = new javax.swing.GroupLayout(jPanelFecharPedido);
        gl_jPanelFecharPedido.setHorizontalGroup(
        	gl_jPanelFecharPedido.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_jPanelFecharPedido.createSequentialGroup()
        			.addGroup(gl_jPanelFecharPedido.createParallelGroup(Alignment.TRAILING)
        				.addGroup(gl_jPanelFecharPedido.createSequentialGroup()
        					.addContainerGap()
        					.addGroup(gl_jPanelFecharPedido.createParallelGroup(Alignment.TRAILING)
        						.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 725, Short.MAX_VALUE)
        						.addGroup(gl_jPanelFecharPedido.createSequentialGroup()
        							.addGroup(gl_jPanelFecharPedido.createParallelGroup(Alignment.LEADING)
        								.addComponent(lblCliente_1)
        								.addComponent(lblRua)
        								.addComponent(lblTelefone)
        								.addComponent(lblTotal))
        							.addGap(13)
        							.addGroup(gl_jPanelFecharPedido.createParallelGroup(Alignment.LEADING)
        								.addComponent(textFecharNomeCliente, GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)
        								.addGroup(gl_jPanelFecharPedido.createSequentialGroup()
        									.addGroup(gl_jPanelFecharPedido.createParallelGroup(Alignment.LEADING)
        										.addComponent(textFecharRua, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        										.addGroup(gl_jPanelFecharPedido.createSequentialGroup()
        											.addPreferredGap(ComponentPlacement.UNRELATED)
        											.addGroup(gl_jPanelFecharPedido.createParallelGroup(Alignment.LEADING)
        												.addComponent(textFecharTotal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        												.addComponent(textFecharTelefone, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))))
        									.addGap(18)
        									.addGroup(gl_jPanelFecharPedido.createParallelGroup(Alignment.LEADING)
        										.addGroup(gl_jPanelFecharPedido.createSequentialGroup()
        											.addComponent(lblEstado)
        											.addPreferredGap(ComponentPlacement.UNRELATED)
        											.addComponent(textSituacao, GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE))
        										.addGroup(gl_jPanelFecharPedido.createSequentialGroup()
        											.addComponent(lblBairro)
        											.addGap(18)
        											.addComponent(textFecharBairro, GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE))))))))
        				.addGroup(gl_jPanelFecharPedido.createSequentialGroup()
        					.addGap(13)
        					.addComponent(comboMudarSituacao, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED, 323, Short.MAX_VALUE)
        					.addComponent(btnConfirmarEntrega, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
        					.addGap(43)
        					.addComponent(jButtonFechar, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap())
        );
        gl_jPanelFecharPedido.setVerticalGroup(
        	gl_jPanelFecharPedido.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_jPanelFecharPedido.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_jPanelFecharPedido.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblCliente_1)
        				.addComponent(textFecharNomeCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(gl_jPanelFecharPedido.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblRua)
        				.addComponent(textFecharRua, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblBairro)
        				.addComponent(textFecharBairro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(gl_jPanelFecharPedido.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblTelefone)
        				.addComponent(textFecharTelefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblEstado)
        				.addComponent(textSituacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(12)
        			.addGroup(gl_jPanelFecharPedido.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblTotal)
        				.addComponent(textFecharTotal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(10)
        			.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE)
        			.addGroup(gl_jPanelFecharPedido.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_jPanelFecharPedido.createSequentialGroup()
        					.addGap(20)
        					.addComponent(comboMudarSituacao, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
        				.addGroup(gl_jPanelFecharPedido.createSequentialGroup()
        					.addGap(18)
        					.addComponent(jButtonFechar, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
        				.addGroup(gl_jPanelFecharPedido.createSequentialGroup()
        					.addGap(18)
        					.addComponent(btnConfirmarEntrega, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)))
        			.addGap(109))
        );
        jPanelFecharPedido.setLayout(gl_jPanelFecharPedido);

        jTabbedPane1.addTab("Pedido", jPanelFecharPedido);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(jTabbedPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap())
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(jTabbedPane1, GroupLayout.PREFERRED_SIZE, 543, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        getContentPane().setLayout(layout);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPedido().setVisible(true);
            }
        });
    }
    private javax.swing.JButton jButtonFechar;
    private javax.swing.JPanel jPanelFazerPedido;
    private javax.swing.JPanel jPanelFecharPedido;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tableItens;
    private JTextField textNomeCliente;
    private JTable tableClientes;
    private JLabel lblBusca;
    private JTextField textBuscaCliente;
    private JTable tableProdutos;
    private JScrollPane scrollPane;
    private JLabel lblCliente;
    private JButton btnSelecionar;
    private JComboBox<String> comboItens;
    private JScrollPane scrollPane_1;
    private JButton btnAdicionar;
    private JLabel lblBusca_1;
    private JTextField textBuscaProduto;
    private JLabel lblQuantidade;
    private JTextField textQuantidadeItem;
    private JButton btnConfirmar;
    private JLabel lblCliente_1;
    private JTextField textFecharNomeCliente;
    private JLabel lblRua;
    private JTextField textFecharRua;
    private JLabel lblBairro;
    private JTextField textFecharBairro;
    private JLabel lblTelefone;
    private JTextField textFecharTelefone;
    private JLabel lblEstado;
    private JTextField textSituacao;
    private JTextField textFecharTotal;
    private JButton btnRemover;
}
