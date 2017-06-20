package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

import controller.Client;
import controller.Main;
import model.ClientInfo;
import model.Group;
import model.Message;

import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewConnection {

	private JFrame frmUmaClient= new JFrame();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JButton btnEntrarNaSala;
	private JButton btnAtualizarSalas;
	private JButton btnConnect;
	JList<Group> list;

	public JFrame getFrame() {
		return frmUmaClient;
	}

	public void setFrame(JFrame frame) {
		this.frmUmaClient = frame;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public ViewConnection() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmUmaClient.getContentPane().setBackground(new Color(46, 139, 87));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(46, 139, 87));
		frmUmaClient.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 11, 124, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		btnConnect = new JButton("Conectar");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((!textField.getText().isEmpty() || textField.getText() != null) && 
						(!textField_1.getText().isEmpty() || textField_1.getText() != null)){
					try{
						int port = Integer.parseInt(textField_1.getText());
						if (Main.connect(textField.getText(), port)){
							textField.setEnabled(false);
							textField_1.setEnabled(false);
							btnConnect.setEnabled(false);
							btnEntrarNaSala.setEnabled(true);
							btnAtualizarSalas.setEnabled(true);
							textField_2.setEnabled(true);
						}
					}
					catch(Exception e1){
						JOptionPane.showMessageDialog(null,"Porta inválida:  "+ e1.getMessage(),"Erro!",JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		btnConnect.setBounds(208, 10, 89, 23);
		panel.add(btnConnect);
		
		list = new JList<Group>();
		textField_1 = new JTextField();
		textField_1.setText("2020");
		textField_1.setBounds(144, 11, 54, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(9, 42, 288, 300);
		panel.add(list);
		
		btnEntrarNaSala = new JButton("Entrar na Sala");
		btnEntrarNaSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!textField_2.getText().isEmpty() && !list.isSelectionEmpty()){
					ClientInfo.setUserName(textField_2.getText());
					ClientInfo.setCurrentGroup(list.getSelectedValue());
					Message m = new Message();
					m.setUserName(textField_2.getText());
					m.setCurrentGroup(list.getSelectedValue());
					m.setStatus(Message.MessageType.GROUP_JOIN);
					Main.sendMessage(m);
					Main.openMessageWindow(list.getSelectedValue().getName());
					frmUmaClient.setEnabled(false);
				}
				else{
					JOptionPane.showMessageDialog(null,"Especifique um nome para o usuário e/ou selecione uma sala! ","Aviso!",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnEntrarNaSala.setEnabled(false);
		btnEntrarNaSala.setBounds(307, 286, 135, 45);
		panel.add(btnEntrarNaSala);
		
		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel_1.setBackground(new Color(46, 139, 87));
		panel_1.setBounds(307, 11, 111, 233);
		panel.add(panel_1);
		panel_1.setLayout(new CardLayout(0, 0));
		
		JLabel label = new JLabel("");
		label.setBounds(331, 11, 111, 233);
		panel.add(label);
		label.setIcon(new ImageIcon(ViewConnection.class.getResource("/view/Sem T\u00EDtulo-1.png")));
		label.setBackground(new Color(46, 139, 87));
		
		btnAtualizarSalas = new JButton("Atualizar salas");
		btnAtualizarSalas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.updateSalas();
			}
		});
		btnAtualizarSalas.setEnabled(false);
		btnAtualizarSalas.setBounds(307, 342, 135, 45);
		panel.add(btnAtualizarSalas);
		
		textField_2 = new JTextField();
		textField_2.setEnabled(false);
		textField_2.setBounds(95, 354, 202, 33);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JTextArea txtrNomeDo = new JTextArea();
		txtrNomeDo.setBackground(new Color(46, 139, 87));
		txtrNomeDo.setEditable(false);
		txtrNomeDo.setText("Nome do \nusu\u00E1rio:");
		txtrNomeDo.setBounds(10, 353, 75, 34);
		panel.add(txtrNomeDo);
		frmUmaClient.setResizable(false);
		frmUmaClient.setTitle("UMA Client");
		frmUmaClient.setBounds(100, 100, 458, 426);
		frmUmaClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void setListData(Group[] data){
		if (!list.equals(null)){
			list.setListData(data);
		}
	}
	
	public void resetUI(){
		Main.closeMessageWindow();
		frmUmaClient.setEnabled(true);
		textField.setEnabled(true);
		textField_1.setEnabled(true);
		btnConnect.setEnabled(true);
		btnEntrarNaSala.setEnabled(false);
		btnAtualizarSalas.setEnabled(false);
		textField_2.setEnabled(false);
	}
}
