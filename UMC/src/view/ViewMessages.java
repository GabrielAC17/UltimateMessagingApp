package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Window.Type;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;

import controller.Main;
import model.ClientInfo;
import model.Message;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.DropMode;
import javax.swing.JScrollPane;

public class ViewMessages {

	private JFrame frmHaveFunWith;
	private JTextField textField;
	private JTextArea textArea;

	/**
	 * Create the application.
	 */
	public ViewMessages() {
		initialize();
	}

	public JFrame getFrame() {
		return frmHaveFunWith;
	}

	public void setFrame(JFrame frame) {
		this.frmHaveFunWith = frame;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHaveFunWith = new JFrame();
		frmHaveFunWith.setTitle("Divirta-se ( \u0361\u00B0 \u035C\u0296 \u0361\u00B0)");
		frmHaveFunWith.setType(Type.POPUP);
		frmHaveFunWith.setResizable(false);
		frmHaveFunWith.setBounds(100, 100, 695, 425);
		frmHaveFunWith.getContentPane().setLayout(null);
		frmHaveFunWith.addWindowListener( new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                Main.enableMainWindow();
                textArea.setText("");
                frmHaveFunWith.setVisible(false);
            }
        } );
		
		textField = new JTextField();
		textField.setBounds(10, 332, 544, 54);
		frmHaveFunWith.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Enviar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!textField.getText().isEmpty()){
					Message m = new Message();
					
					m.setMessage(textField.getText());
					
					m.setUserName(ClientInfo.getUserName());
					
					DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					Date date = new Date();
					System.out.println(dateFormat.format(date));
					m.setTime(date);
			
					m.setCurrentGroup(ClientInfo.getCurrentGroup());
					
					m.setStatus(Message.MessageType.TEXT_MESSAGE);
					
					Main.sendMessage(m);
					textField.setText("");
				}
			}
		});
		btnNewButton.setBounds(564, 332, 115, 54);
		frmHaveFunWith.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBounds(10, 11, 669, 310);
		frmHaveFunWith.getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setText("");
		textArea.setForeground(Color.WHITE);
		textArea.setOpaque(false);
		
		JLabel label = new JLabel("");
		label.setBounds(0, 0, 689, 404);
		frmHaveFunWith.getContentPane().add(label);
		label.setIcon(new ImageIcon(ViewMessages.class.getResource("/view/1ZwWx.gif")));
	}
	
	
	public void addMessage(Message m){
		String finalMessage = m.getUserName() + " - " + m.getTime() + " - " + m.getMessage() + "\n";
		textArea.setText(textArea.getText() + finalMessage);
		textField.grabFocus();
	}
	
	public void addErrorMessage(String message){
		textArea.setText(textArea.getText() + message);
		textField.grabFocus();
	}
	
	public void clearMessages(){
		textArea.setText("");
	}
}
