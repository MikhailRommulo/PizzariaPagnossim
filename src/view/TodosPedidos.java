/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import javax.swing.GroupLayout.Alignment;

import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;

import javax.swing.GroupLayout;
import javax.swing.table.DefaultTableModel;

import control.ControlePedido;
import model.bean.Pedido;
import model.connection.ConnectionFactory;
import model.dao.PedidoDAO;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;


public class TodosPedidos extends javax.swing.JFrame {
	
	private TableModelPedido tableModelPedido;
	private JComboBox comboSituacaoPedido;

    /**
     * Creates new form Cadastro_Funcionario
     */
    public TodosPedidos() {
        initComponents();
        this.tableModelPedido = new TableModelPedido();
        this.tablePedidos.setModel(tableModelPedido);
        PedidoDAO pd = new PedidoDAO();
        try {
			tableModelPedido.pegarListaPedidos(pd.readOrder());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       //atualizarPedidos();
    }

    /*private void atualizarPedidos() {    	
    	
    	while(true) {    		
    		
    		PedidoDAO pd = new PedidoDAO();
    		try {
				tableModelPedido.pegarListaPedidos(pd.readOrder());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				ConnectionFactory.fechar();
			}
    		try {Thread.sleep(120000);}catch(Exception ex) {}
        	
    	}
    	
    }*/
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    
        jScrollPane1 = new javax.swing.JScrollPane();
        tablePedidos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Fila de pedidos");

        tablePedidos.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"CPF","Nome", "Telefone", "Fun\u00E7\u00E3o"
        	}
        ));
        jScrollPane1.setViewportView(tablePedidos);
        
        JButton btnAtualizar = new JButton("atualizar");
        btnAtualizar.setIcon(new ImageIcon("C:\\Users\\vagui\\Desktop\\PizzariaPagnossim\\icon\\refreshicon.png"));
        btnAtualizar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		PedidoDAO pd = new PedidoDAO();
        		String situacao = (String) comboSituacaoPedido.getSelectedItem();
        		if(situacao == "Todos") {
        			try {
        				tableModelPedido.pegarListaPedidos(pd.readOrder());
        			} catch (SQLException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}finally {
        				ConnectionFactory.fechar();
        			}
        		}else {
        			try {
    					tableModelPedido.pegarListaPedidos(pd.readStatus(situacao));
    				} catch (SQLException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}finally {
    					ConnectionFactory.fechar();
    				}
        		}
        	}
        });
        
        btnExcluir = new JButton("excluir");
        btnExcluir.setIcon(new ImageIcon("C:\\Users\\vagui\\Desktop\\PizzariaPagnossim\\icon\\deleteicon.png"));
        btnExcluir.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		ControlePedido cp = new ControlePedido();
        		Pedido p = tableModelPedido.pegarPedido(tablePedidos.getSelectedRow());
        		cp.excluirPedido(p);
        		
        	}
        });
        
        btnEditar = new JButton("editar");
        btnEditar.setIcon(new ImageIcon("C:\\Users\\vagui\\Desktop\\PizzariaPagnossim\\icon\\editicon.png"));
        btnEditar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		ControlePedido cp = new ControlePedido();
        		Pedido p = tableModelPedido.pegarPedido(tablePedidos.getSelectedRow());
        		cp.levarPedidoParaTela(p);
        		
        	}
        });
        
        comboSituacaoPedido = new JComboBox();
        comboSituacaoPedido.setModel(new DefaultComboBoxModel(new String[] {"Todos", "Na fila", "Em produ\u00E7\u00E3o", "Fila para entregar", "Saiu para entregar", "Entregue"}));
        
        btnSelecionar = new JButton("selecionar");
        btnSelecionar.setIcon(new ImageIcon("C:\\Users\\vagui\\Desktop\\PizzariaPagnossim\\icon\\targeticon.png"));
        btnSelecionar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		TelaPedido frame = new TelaPedido();
        		Pedido p = tableModelPedido.pegarPedido(tablePedidos.getSelectedRow());
        		frame.pedidoEmProducao(p);
        		frame.setVisible(true);
        	}
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(13)
        			.addComponent(comboSituacaoPedido, GroupLayout.PREFERRED_SIZE, 648, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap())
        		.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
        			.addGap(13)
        			.addComponent(btnSelecionar, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
        			.addGap(13)
        			.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
        			.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
        			.addGap(11)
        			.addComponent(btnAtualizar, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
        			.addGap(15))
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE)
        			.addContainerGap())
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(5)
        			.addComponent(comboSituacaoPedido, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addGap(17)
        					.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
        				.addGroup(layout.createSequentialGroup()
        					.addGap(17)
        					.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
        				.addGroup(layout.createSequentialGroup()
        					.addGap(16)
        					.addComponent(btnAtualizar, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
        				.addGroup(layout.createSequentialGroup()
        					.addGap(17)
        					.addComponent(btnSelecionar, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)))
        			.addGap(21)
        			.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 342, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap())
        );
        getContentPane().setLayout(layout);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TodosPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TodosPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TodosPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TodosPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               TodosPedidos frame = new TodosPedidos();
               frame.setVisible(true);
           
            }
        });
    }
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablePedidos;
    private JButton btnExcluir;
    private JButton btnEditar;
    private JButton btnSelecionar;
}
