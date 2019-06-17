package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.ControleCliente;
import model.bean.Cliente;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Font;

public class EditarCliente extends JFrame {

	private JPanel contentPane;
	private JTextField textEditorNome;
	private JTextField textEditorTelefone;
	private JTextField textEditorRua;
	private JTextField textEditorBairro;
	private JTextField textEditorCidade;
	private JTextField textEditorEstado;
	private JTextField textEditarEmail;
	
	private long cpfEditor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarCliente frame = new EditarCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void receberCliente(Cliente c) {
		cpfEditor = c.getCpf();
		textEditorNome.setText(c.getNome());
		textEditorTelefone.setText(String.valueOf(c.getTelefone()));
		textEditorRua.setText(c.getLogradouro());
		textEditorBairro.setText(c.getBairro());
		textEditorCidade.setText(c.getCidade());
		textEditorEstado.setText(c.getEstado());
		textEditarEmail.setText(c.getEmail());
		
	}

	/**
	 * Create the frame.
	 */
	public EditarCliente() {
		setTitle("Editar Cliente");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 536, 373);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Georgia", Font.PLAIN, 15));
		lblNome.setBounds(148, 28, 55, 14);
		contentPane.add(lblNome);
		
		textEditorNome = new JTextField();
		textEditorNome.setBounds(217, 22, 283, 28);
		contentPane.add(textEditorNome);
		textEditorNome.setColumns(10);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Georgia", Font.PLAIN, 15));
		lblTelefone.setBounds(148, 67, 70, 14);
		contentPane.add(lblTelefone);
		
		textEditorTelefone = new JTextField();
		textEditorTelefone.setBounds(217, 61, 283, 28);
		contentPane.add(textEditorTelefone);
		textEditorTelefone.setColumns(10);
		
		JLabel lblRua = new JLabel("Rua:");
		lblRua.setFont(new Font("Georgia", Font.PLAIN, 15));
		lblRua.setBounds(148, 107, 46, 14);
		contentPane.add(lblRua);
		
		textEditorRua = new JTextField();
		textEditorRua.setBounds(217, 101, 283, 28);
		contentPane.add(textEditorRua);
		textEditorRua.setColumns(10);
		
		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setFont(new Font("Georgia", Font.PLAIN, 15));
		lblBairro.setBounds(148, 146, 55, 14);
		contentPane.add(lblBairro);
		
		textEditorBairro = new JTextField();
		textEditorBairro.setBounds(217, 140, 283, 28);
		contentPane.add(textEditorBairro);
		textEditorBairro.setColumns(10);
		
		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setFont(new Font("Georgia", Font.PLAIN, 15));
		lblCidade.setBounds(148, 185, 55, 14);
		contentPane.add(lblCidade);
		
		textEditorCidade = new JTextField();
		textEditorCidade.setBounds(217, 179, 283, 28);
		contentPane.add(textEditorCidade);
		textEditorCidade.setColumns(10);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setFont(new Font("Georgia", Font.PLAIN, 15));
		lblEstado.setBounds(148, 224, 55, 14);
		contentPane.add(lblEstado);
		
		textEditorEstado = new JTextField();
		textEditorEstado.setBounds(217, 218, 283, 28);
		contentPane.add(textEditorEstado);
		textEditorEstado.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setFont(new Font("Georgia", Font.PLAIN, 15));
		lblEmail.setBounds(148, 263, 55, 14);
		contentPane.add(lblEmail);
		
		textEditarEmail = new JTextField();
		textEditarEmail.setBounds(217, 257, 284, 28);
		contentPane.add(textEditarEmail);
		textEditarEmail.setColumns(10);
		
		JButton btnSalvar = new JButton("salvar");
		btnSalvar.setIcon(new ImageIcon("C:\\Users\\vagui\\Desktop\\PizzariaPagnossim\\icon\\checkicon.png"));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControleCliente cc = new ControleCliente();
				cc.editarCliente(cpfEditor, textEditorNome.getText(), Integer.parseInt(textEditorTelefone.getText()), textEditorRua.getText(), textEditorBairro.getText(), textEditorCidade.getText(), textEditorEstado.getText(), textEditarEmail.getText());

			}
		});
		btnSalvar.setBounds(20, 176, 116, 34);
		contentPane.add(btnSalvar);
		
		JButton btnCancelar = new JButton("cancelar");
		btnCancelar.setIcon(new ImageIcon("C:\\Users\\vagui\\Desktop\\PizzariaPagnossim\\icon\\deleteicon.png"));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(22, 243, 116, 34);
		contentPane.add(btnCancelar);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\vagui\\Desktop\\PizzariaPagnossim\\icon\\editcardapioicon.png"));
		lblNewLabel.setBounds(10, 1, 128, 169);
		contentPane.add(lblNewLabel);
	}
}
