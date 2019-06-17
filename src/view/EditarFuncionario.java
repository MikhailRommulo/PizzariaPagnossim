package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.ControleFuncionario;
import model.bean.Funcionario;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;

public class EditarFuncionario extends JFrame {

	/**
	 * 
	 */
	private JPanel contentPane;
	private JTextField textEditarNome;
	private JTextField textEditarTelefone;
	private JComboBox<String> comboEditarFuncao;
	private long cpfEditor;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarFuncionario frame = new EditarFuncionario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void receberFuncionario(Funcionario funcionario) {		
		textEditarNome.setText(funcionario.getNome());
		textEditarTelefone.setText(String.valueOf((funcionario.getTelefone())));
		comboEditarFuncao.setSelectedItem(funcionario.getFuncao());	
		cpfEditor = funcionario.getCpf();
	}

	/**
	 * Create the frame.
	 */
	public EditarFuncionario() {
		setTitle("Editar Funcionario");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 467, 252);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Georgia", Font.PLAIN, 15));
		lblNome.setBounds(188, 32, 56, 14);
		contentPane.add(lblNome);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Georgia", Font.PLAIN, 15));
		lblTelefone.setBounds(188, 72, 66, 14);
		contentPane.add(lblTelefone);
		
		JLabel lblFuno = new JLabel("Fun\u00E7\u00E3o:");
		lblFuno.setFont(new Font("Georgia", Font.PLAIN, 15));
		lblFuno.setBounds(188, 112, 66, 14);
		contentPane.add(lblFuno);
		
		textEditarNome = new JTextField();
		textEditarNome.setBounds(264, 26, 165, 29);
		contentPane.add(textEditarNome);
		textEditarNome.setColumns(10);
		
		textEditarTelefone = new JTextField();
		textEditarTelefone.setBounds(264, 66, 165, 29);
		contentPane.add(textEditarTelefone);
		textEditarTelefone.setColumns(10);
		
		comboEditarFuncao = new JComboBox();
		comboEditarFuncao.setModel(new DefaultComboBoxModel(new String[] {"Pizzaiolo", "Entregador", "Atendente", "Gerente"}));
		comboEditarFuncao.setBounds(264, 106, 165, 29);
		contentPane.add(comboEditarFuncao);
		
		JButton btnSalvar = new JButton("salvar");
		btnSalvar.setIcon(new ImageIcon("C:\\Users\\vagui\\Desktop\\PizzariaPagnossim\\icon\\checkicon.png"));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String funcao = String.valueOf(comboEditarFuncao.getSelectedItem());
				ControleFuncionario cf = new ControleFuncionario();
				cf.editarFuncionario(cpfEditor, textEditarNome.getText(), Integer.parseInt(textEditarTelefone.getText()),funcao);
			}
		});
		btnSalvar.setBounds(29, 173, 109, 29);
		contentPane.add(btnSalvar);
		
		JButton btnCancelar = new JButton("cancelar");
		btnCancelar.setIcon(new ImageIcon("C:\\Users\\vagui\\Desktop\\PizzariaPagnossim\\icon\\deleteicon.png"));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(313, 173, 109, 29);
		contentPane.add(btnCancelar);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\vagui\\Desktop\\PizzariaPagnossim\\icon\\editcardapioicon.png"));
		lblNewLabel.setBounds(10, 11, 128, 139);
		contentPane.add(lblNewLabel);
	}
}
