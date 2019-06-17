package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.ControleUsuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;

public class Cadastro_Usuario extends JFrame {

	private JPanel contentPane;
	private JTextField textUsuario;
	private JTextField textSenha;
	private JTextField textConfirmarSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cadastro_Usuario frame = new Cadastro_Usuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Cadastro_Usuario() {
		setTitle("Cadastrar Usuario");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 393);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usu\u00E1rio:");
		lblUsuario.setFont(new Font("Georgia", Font.PLAIN, 15));
		lblUsuario.setBounds(25, 165, 72, 14);
		contentPane.add(lblUsuario);
		
		textUsuario = new JTextField();
		textUsuario.setBounds(107, 159, 306, 29);
		contentPane.add(textUsuario);
		textUsuario.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Georgia", Font.PLAIN, 15));
		lblSenha.setBounds(25, 205, 58, 14);
		contentPane.add(lblSenha);
		
		textSenha = new JTextField();
		textSenha.setBounds(107, 199, 306, 29);
		contentPane.add(textSenha);
		textSenha.setColumns(10);
		
		JLabel lblConfirmarSenha = new JLabel("Confirmar senha:");
		lblConfirmarSenha.setFont(new Font("Georgia", Font.PLAIN, 15));
		lblConfirmarSenha.setBounds(25, 262, 135, 14);
		contentPane.add(lblConfirmarSenha);
		
		textConfirmarSenha = new JTextField();
		textConfirmarSenha.setBounds(170, 256, 243, 29);
		contentPane.add(textConfirmarSenha);
		textConfirmarSenha.setColumns(10);
		
		JButton btnCadastrar = new JButton("cadastrar");
		btnCadastrar.setIcon(new ImageIcon("C:\\Users\\vagui\\Desktop\\PizzariaPagnossim\\icon\\checkicon.png"));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textSenha.getText().equals(textConfirmarSenha.getText())) {					
					ControleUsuario cu = new ControleUsuario();
					cu.cadastrarUsuario(Long.parseLong(textUsuario.getText()), textSenha.getText());
				}else {
					JOptionPane.showMessageDialog(null, "Senha e confirmação de senha não conferem");
				}
			}
		});
		btnCadastrar.setBounds(22, 314, 121, 29);
		contentPane.add(btnCadastrar);
		
		JButton btnCancelar = new JButton("cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnCancelar.setIcon(new ImageIcon("C:\\Users\\vagui\\Desktop\\PizzariaPagnossim\\icon\\deleteicon.png"));
		btnCancelar.setBounds(292, 314, 121, 29);
		contentPane.add(btnCancelar);
		
		JLabel lblCadastroDeUsurio = new JLabel("Cadastro de Usu\u00E1rio");
		lblCadastroDeUsurio.setFont(new Font("Georgia", Font.BOLD, 25));
		lblCadastroDeUsurio.setBounds(81, 21, 274, 29);
		contentPane.add(lblCadastroDeUsurio);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\vagui\\Desktop\\PizzariaPagnossim\\icon\\usericon.png"));
		lblNewLabel.setBounds(182, 61, 72, 87);
		contentPane.add(lblNewLabel);
	}

}
