package ee.ut.math.tvt.salessystem.ui.tabs;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CreateWindow extends JFrame{
	
	public static void main(String[] args) {
		
	
		JFrame.setDefaultLookAndFeelDecorated(true);
	    JFrame frame = new JFrame();
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setTitle("JFrame Test");
	    frame.setLayout(new GridLayout(3, 2));
	    frame.add(new JLabel("First Name:"));
	    frame.add(new JTextField());
	    frame.add(new JLabel("Last Name:"));
	    frame.add(new JTextField());
	    frame.add(new JButton("Register"));

	   				    
	    frame.setSize(200, 100);
	    frame.setVisible(true);
		
}
}
