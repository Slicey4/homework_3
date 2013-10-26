package ee.ut.math.tvt.salessystem.ui.tabs;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CreateWindow {

	private JTextField price, quantity, name;
	private JButton addok, cancel;
	private JFrame frame;
	private String name1;
	private int quantity1;
	private double price1;

	public CreateWindow() {

		frame = new JFrame("New Item");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel(new GridLayout(5, 2, 30, 50));
		panel.add(new JLabel("Enter name"));
		name = new JTextField(20);
		panel.add(name);
		panel.add(new JLabel("Enter quantity"));
		quantity = new JTextField(20);
		panel.add(quantity);
		panel.add(new JLabel("Enter price"));
		price = new JTextField(20);
		panel.add(price);

		addok = new JButton("Add item");
		addok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddItemButtonClicked();
			}
		});

		panel.add(addok);
		cancel = new JButton("Cancel");

		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelAddButtonClicked();
			}
		});

		panel.add(cancel);
		JLabel tekst = new JLabel();
		panel.add(tekst);

		frame.add(panel);
		frame.setSize(400, 400);
		frame.setVisible(true);

	}

	
	protected void cancelAddButtonClicked() {
		frame.dispose();
	}

	protected void AddItemButtonClicked() {
	
			
		
		

	}

	public JTextField getPrice() {
		return price;
	}


	public void setPrice(JTextField price) {
		this.price = price;
	}


	public JTextField getQuantity() {
		return quantity;
	}


	public void setQuantity(JTextField quantity) {
		this.quantity = quantity;
	}


	public JTextField getName() {
		return name;
	}


	public void setName(JTextField name) {
		this.name = name;
	}


	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}


	public int getQuantity1() {
		return quantity1;
	}


	public void setQuantity1(int quantity1) {
		this.quantity1 = quantity1;
	}


	public double getPrice1() {
		return price1;
	}


	public void setPrice1(double price1) {
		this.price1 = price1;
	}

	
}
