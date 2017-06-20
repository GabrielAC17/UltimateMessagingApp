package view;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;

import model.Group;
import model.ServerInfo;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Canvas;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.ListSelectionModel;

import controller.Main;
import javax.swing.JFormattedTextField;

public class ViewServer {

	private JFrame frmUmaServer;
	private JTextField textField;
	private JList<Group> list;
	private JTextField textField_1;
	private JButton btnNewButton;
	private JButton btnDeleteButton;

	public JFrame getFrame() {
		return frmUmaServer;
	}

	/**
	 * Create the application.
	 */
	public ViewServer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmUmaServer = new JFrame();
		frmUmaServer.setTitle("UMA Server");
		frmUmaServer.setBackground(new Color(65, 105, 225));
		frmUmaServer.getContentPane().setForeground(new Color(65, 105, 225));
		frmUmaServer.setForeground(new Color(65, 105, 225));
		frmUmaServer.setResizable(false);
		frmUmaServer.setBounds(100, 100, 573, 480);
		frmUmaServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmUmaServer.getContentPane().setLayout(new BoxLayout(frmUmaServer.getContentPane(), BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 0, 0));
		frmUmaServer.getContentPane().add(panel);
		
		list = new JList<Group>();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(10, 46, 317, 394);
		setListData(ServerInfo.getArrayGroups());
		
		btnNewButton = new JButton("Criar sala");
		btnNewButton.setEnabled(false);
		btnNewButton.setBounds(347, 284, 210, 71);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!textField.getText().isEmpty()){
					if (ServerInfo.findGroup(textField.getText()) == null){
						Group g = new Group();
						g.setName(textField.getText());
						textField.setText("");
						g.setGroupID(ServerInfo.getGroupID());
						ServerInfo.setGroupID();
						ServerInfo.getGroups().add(g);
						setListData(ServerInfo.getArrayGroups());
					}
				}
				else{
					JOptionPane.showMessageDialog(null,"Nome não pode estar em branco! ","Aviso!",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		btnDeleteButton = new JButton("Excluir sala selecionada");
		btnDeleteButton.setEnabled(false);
		btnDeleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Group g = list.getSelectedValue();
				if (g != null){
					ServerInfo.getGroups().remove(g);
					setListData(ServerInfo.getArrayGroups());
				}
				else{
					JOptionPane.showMessageDialog(null,"Selecione uma sala para excluir! ","Aviso!",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnDeleteButton.setBounds(345, 366, 212, 74);
		panel.setLayout(null);
		panel.add(list);
		panel.add(btnDeleteButton);
		panel.add(btnNewButton);
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setBounds(346, 227, 211, 46);
		panel.add(textField);
		textField.setColumns(10);
		
		JTextArea txtrNomeDaSala = new JTextArea();
		txtrNomeDaSala.setForeground(new Color(255, 255, 255));
		txtrNomeDaSala.setText("Nome da sala:");
		txtrNomeDaSala.setBackground(new Color(255, 0, 0));
		txtrNomeDaSala.setEditable(false);
		txtrNomeDaSala.setBounds(345, 194, 212, 22);
		panel.add(txtrNomeDaSala);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(null);
		panel_1.setOpaque(false);
		panel_1.setBounds(337, 11, 220, 205);
		panel.add(panel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ViewServer.class.getResource("/view/29558-200.png")));
		panel_1.add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setText("2020");
		textField_1.setBounds(91, 11, 92, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblPorta = new JLabel("Porta:");
		lblPorta.setBounds(10, 11, 46, 14);
		panel.add(lblPorta);
		
		JButton btnIniciarServidor = new JButton("Iniciar servidor");
		btnIniciarServidor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textField_1.getText() != ""){
					try{
						int porta = Integer.parseInt(textField_1.getText());
						Main.iniciarServidor(porta);
						btnNewButton.setEnabled(true);
						btnDeleteButton.setEnabled(true);
						textField.setEnabled(true);
						btnIniciarServidor.setEnabled(false);
						textField_1.setEnabled(false);
					}
					catch(Exception e){
						JOptionPane.showMessageDialog(null,"Porta inválida:  "+ e.getMessage(),"Erro!",JOptionPane.WARNING_MESSAGE);
					}
				}
				else{
					JOptionPane.showMessageDialog(null,"Porta não pode estar em branco! ","Aviso!",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnIniciarServidor.setBounds(193, 11, 134, 20);
		panel.add(btnIniciarServidor);
	}
	
	public void setListData(Group[] data){
		if (!list.equals(null)){
			list.setListData(data);
		}
	}
}
