package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.ControleProduto;
import model.bean.Cliente;
import model.bean.Produto;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Font;

public class EditarCardapio extends JFrame {

	private JPanel contentPane;
	private JTextField textEditarDescricao;
	private JTextField textEditarTipo;
	private JTextField textEditarValor;
	private JTextField textEditarQuantidade;
	private int codProd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarCardapio frame = new EditarCardapio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void receberProduto(Produto p) {
		textEditarDescricao.setText(p.getDescricao());
		textEditarTipo.setText(p.getTipo());
		textEditarValor.setText(String.valueOf(p.getPreco()));
		textEditarQuantidade.setText(String.valueOf(p.getQuantidade()));
		codProd = p.getCodProd();
		
	}

	/**
	 * Create the frame.
	 */
	public EditarCardapio() {
		setTitle("Editar Cardapio");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 586, 209);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEditarDescricao = new JLabel("Descri\u00E7\u00E3o:");
		lblEditarDescricao.setFont(new Font("Georgia", Font.PLAIN, 15));
		lblEditarDescricao.setBounds(159, 34, 81, 14);
		contentPane.add(lblEditarDescricao);
		
		textEditarDescricao = new JTextField();
		textEditarDescricao.setBounds(250, 32, 271, 20);
		contentPane.add(textEditarDescricao);
		textEditarDescricao.setColumns(10);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setFont(new Font("Georgia", Font.PLAIN, 15));
		lblTipo.setBounds(159, 59, 46, 14);
		contentPane.add(lblTipo);
		
		textEditarTipo = new JTextField();
		textEditarTipo.setBounds(250, 57, 271, 20);
		contentPane.add(textEditarTipo);
		textEditarTipo.setColumns(10);
		
		JLabel lblValor = new JLabel("Valor:");
		lblValor.setFont(new Font("Georgia", Font.PLAIN, 15));
		lblValor.setBounds(159, 103, 46, 14);
		contentPane.add(lblValor);
		
		textEditarValor = new JTextField();
		textEditarValor.setBounds(250, 101, 100, 20);
		contentPane.add(textEditarValor);
		textEditarValor.setColumns(10);
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setFont(new Font("Georgia", Font.PLAIN, 15));
		lblQuantidade.setBounds(159, 132, 94, 14);
		contentPane.add(lblQuantidade);
		
		textEditarQuantidade = new JTextField();
		textEditarQuantidade.setBounds(250, 126, 100, 20);
		contentPane.add(textEditarQuantidade);
		textEditarQuantidade.setColumns(10);
		
		JButton btnSalvar = new JButton("salvar");
		btnSalvar.setIcon(new ImageIcon("C:\\Users\\vagui\\Desktop\\PizzariaPagnossim\\icon\\checkicon.png"));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControleProduto cp = new ControleProduto();
				cp.editarProduto(codProd, textEditarDescricao.getText(), textEditarTipo.getText(),Double.parseDouble(textEditarValor.getText()), Integer.parseInt(textEditarQuantidade.getText()));
			}
		});
		btnSalvar.setBounds(406, 96, 115, 25);
		contentPane.add(btnSalvar);
		
		JButton btnCancelar = new JButton("cancelar");
		btnCancelar.setIcon(new ImageIcon("C:\\Users\\vagui\\Desktop\\PizzariaPagnossim\\icon\\deleteicon.png"));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(406, 121, 115, 25);
		contentPane.add(btnCancelar);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\vagui\\Desktop\\PizzariaPagnossim\\icon\\editcardapioicon.png"));
		lblNewLabel.setBounds(10, 21, 138, 135);
		contentPane.add(lblNewLabel);
	}

}
