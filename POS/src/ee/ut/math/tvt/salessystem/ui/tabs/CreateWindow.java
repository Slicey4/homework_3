package ee.ut.math.tvt.salessystem.ui.tabs;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ConcurrentModificationException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

public class CreateWindow {

	private JTextField price, quantity, name, description, id;
	private JButton addok, cancel;
	private JFrame frame;
	private String name1, description1;
	private int quantity1;
	private JLabel teave;
	private double price1;
	private long id1;

	private SalesSystemModel model;

	public CreateWindow(SalesSystemModel model) {
		this.model = model;
	}

	public void AddWindow() {

		frame = new JFrame("New Item");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel(new GridLayout(7, 2, 30, 50));
		panel.add(new JLabel("Enter name"));
		name = new JTextField(20);
		panel.add(name);
		panel.add(new JLabel("Enter Id"));
		id = new JTextField(20);
		panel.add(id);

		panel.add(new JLabel("Enter description"));
		description = new JTextField(20);
		panel.add(description);
		panel.add(new JLabel("Enter quantity"));
		quantity = new JTextField(20);
		panel.add(quantity);
		panel.add(new JLabel("Enter price"));
		price = new JTextField(20);
		panel.add(price);
		String error = ("<html><p>The input was incorrect. Please enter numbers in price and quantity and item name in name field.</p></html>");
		teave = new JLabel(error);
		teave.setVisible(false);

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
		panel.add(teave);
		frame.add(panel);
		frame.setSize(600, 600);
		frame.setVisible(true);

	}

	protected void cancelAddButtonClicked() {
		frame.dispose();
	}

	protected void AddItemButtonClicked() {
		//try {
			name1 = name.getText();

			teave.setVisible(false);
			quantity1 = Integer.parseInt(quantity.getText());
			price1 = (double) Math
					.round(Double.parseDouble(price.getText()) * 100) / 100;
			id1=Integer.parseInt(id.getText());
			long indeks = model.getWarehouseTableModel().getRowCount() + 1;
			model.getWarehouseTableModel()
			.addItem(
					new StockItem(id1,name1,description1,price1,quantity1));
			frame.dispose();
/*
			for (StockItem x : model.getWarehouseTableModel().getTableRows()) {
				// System.out.println(x);
				if (x.getName().equals(name1)) {
					System.out.println(12312);

					model.getWarehouseTableModel()
							.addItem(
									new StockItem(x.getId(), x.getName(), x
											.getDescription(), x.getPrice(),
											quantity1));

				} else {
					model.getWarehouseTableModel().addItem(
							new StockItem(indeks, name1, description1, price1,
									quantity1));

					// System.out.println(12);
					frame.dispose();
				}

			}
			if (name1.length() == 0) {
				teave.setVisible(true);

			}

		} catch (NullPointerException e) {
			teave.setVisible(true);
			e.getMessage();

		} catch (NumberFormatException e) {
			teave.setVisible(true);
			System.out.println(e.getCause());
		} catch (ConcurrentModificationException e) {
			e.printStackTrace();
		}*/

	}

	public JTextField getId() {
		return id;
	}

	public void setId(JTextField id) {
		this.id = id;
	}

	public long getId1() {
		return id1;
	}

	public void setId1(int id1) {
		this.id1 = id1;
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
