package ee.ut.math.tvt.salessystem.ui.panels;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.hibernate.Session;

/**
 * Purchase pane + shopping cart tabel UI.
 */
public class PurchaseItemPanel extends JPanel {
	private Session session;
	private static final long serialVersionUID = 1L;

	// Text field on the dialogPane
	public static JComboBox<String> items;
	private JTextField barCodeField;
	private JTextField quantityField;
	private JTextField priceField;
	private ArrayList<Integer> quantities;
	private JButton addItemButton;
	private int quantity;

	// Warehouse model
	private SalesSystemModel model;

	/**
	 * Constructs new purchase item panel.
	 * 
	 * @param model
	 *            composite model of the warehouse and the shopping cart.
	 */
	public PurchaseItemPanel(SalesSystemModel model) {
		this.model = model;

		setLayout(new GridBagLayout());

		add(drawDialogPane(), getDialogPaneConstraints());
		add(drawBasketPane(), getBasketPaneConstraints());

		setEnabled(false);
	}

	// shopping cart pane
	private JComponent drawBasketPane() {

		// Create the basketPane
		JPanel basketPane = new JPanel();
		basketPane.setLayout(new GridBagLayout());
		basketPane.setBorder(BorderFactory.createTitledBorder("Shopping cart"));

		// Create the table, put it inside a scollPane,
		// and add the scrollPane to the basketPanel.
		JTable table = new JTable(model.getCurrentPurchaseTableModel());
		JScrollPane scrollPane = new JScrollPane(table);

		basketPane.add(scrollPane, getBacketScrollPaneConstraints());

		return basketPane;
	}

	// purchase dialog

	private JComponent drawDialogPane() {

		// Create the panel
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 2));
		panel.setBorder(BorderFactory.createTitledBorder("Product"));

		// Combo items
		JComboBox<String> combo = new JComboBox<String>();
		combo.addItem("Select one");
		// System.out.println(model.getWarehouseTableModel().getRowCount());
		for (StockItem x : model.getWarehouseTableModel().getTableRows()) {
			combo.addItem(x.getName());
		}

		// Initialize the textfields

		items = combo;
		barCodeField = new JTextField();
		quantityField = new JTextField("1");
		priceField = new JTextField();

		// Fill the dialog fields if the bar code text field loses focus
		items.addItemListener(new ItemListener() {
			int q;

			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED)

				{
					q = Integer.parseInt(quantityField.getText()); // saan teada
																	// mitu seda
																	// toodet
																	// tellitakse
					StockItem stockItem = getStockItemByName();

					if (stockItem != null) {

						priceField.setText(String.valueOf(stockItem.getPrice()
								* q));
						barCodeField.setText(String.valueOf(stockItem.getId()));

					} else {
						reset();
					}
				}

			}
		});

		priceField.setEditable(false);
		barCodeField.setEditable(false);//

		// == Add components to the panel

		// -items
		panel.add(new JLabel("Items"));
		panel.add(items);

		// - bar code
		panel.add(new JLabel("Bar code:"));
		panel.add(barCodeField);

		// - amount
		panel.add(new JLabel("Amount:"));
		panel.add(quantityField);

		// - price
		panel.add(new JLabel("Price:"));
		panel.add(priceField);

		// Create and add the button
		addItemButton = new JButton("Add to cart");

		addItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getAllQuantities();
				// add chosen item to the shopping cart.
				StockItem stockItem = getStockItemByBarCode();

				if (stockItem != null) {

					try {
						// System.out.println(stockItem.getQuantity());
						quantity = Integer.parseInt(quantityField.getText());

						if (quantity > stockItem.getQuantity()) {

							JOptionPane.showMessageDialog(null,
									"There isn't enough " + stockItem.getName()
											+ " in the stock.");

						} else {
							stockItem.setQuantity(stockItem.getQuantity()
									- quantity);
							model.getCurrentPurchaseTableModel().addItem(
									new SoldItem(stockItem, quantity));
						}
					} catch (NumberFormatException ex) {
						quantity = 1;

					}

				} else
					try {
						model.getCurrentPurchaseTableModel().addItem(
								new SoldItem(stockItem, quantity));

					} catch (NullPointerException ee) {
						System.out.println(ee.getMessage());
					}
			}

		});

		panel.add(addItemButton);

		return panel;
	}

	public JComboBox<String> getItems() {
		return items;
	}

	// Fill dialog with data from the "database".
	private void fillDialogFields() {
		StockItem stockItem = getStockItemByBarCode();

		if (stockItem != null) {

			String priceString = String.valueOf(stockItem.getPrice());
			priceField.setText(priceString);
		} else {
			reset();
		}
	}

	// Search the warehouse for a StockItem with the name selected in combolist
	// to the items field.
	private StockItem getStockItemByName() {
		try {

			String name = String.valueOf(items.getSelectedItem());
			model.getWarehouseTableModel().getTableRows();
			return model.getWarehouseTableModel().getItemByName(name);
		} catch (NumberFormatException ex) {
			return null;
		} catch (NoSuchElementException ex) {
			return null;
		}
	}

	// Search the warehouse for a StockItem with the bar code entered
	// to the barCode textfield.
	private StockItem getStockItemByBarCode() {
		try {
			int code = Integer.parseInt(barCodeField.getText());
			return model.getWarehouseTableModel().getItemById(code);
		} catch (NumberFormatException ex) {
			return null;
		} catch (NoSuchElementException ex) {
			return null;
		}
	}

	/**
	 * Add new item to the cart.
	 */
/*	public void addItemEventHandler() {
		// add chosen item to the shopping cart.
		StockItem stockItem = getStockItemByBarCode();
		if (stockItem != null) {
			int quantity;
			try {
				quantity = Integer.parseInt(quantityField.getText());

			} catch (NumberFormatException ex) {
				quantity = 1;

			}

			if (quantity > stockItem.getQuantity()) {

				JOptionPane.showMessageDialog(null, "There isn't enough "
						+ stockItem.getName() + " in the stock.");

			} else
				try {
					model.getCurrentPurchaseTableModel().addItem(
							new SoldItem(stockItem, quantity));

				} catch (NullPointerException e) {
					System.out.println(e.getMessage());
				}
		}
	}*/

	// The total sum of the order

	/**
	 * Sets whether or not this component is enabled.
	 */
	@Override
	public void setEnabled(boolean enabled) {
		this.items.setEnabled(enabled);
		this.addItemButton.setEnabled(enabled);
		this.barCodeField.setEnabled(enabled);
		this.quantityField.setEnabled(enabled);

	}

	/**
	 * Reset dialog fields.
	 */
	public void reset() {
		items.setSelectedIndex(0);
		barCodeField.setText("");
		quantityField.setText("1");
		priceField.setText("");

	}

	/*
	 * === Ideally, UI's layout and behavior should be kept as separated as
	 * possible. If you work on the behavior of the application, you don't want
	 * the layout details to get on your way all the time, and vice versa. This
	 * separation leads to cleaner, more readable and better maintainable code.
	 * 
	 * In a Swing application, the layout is also defined as Java code and this
	 * separation is more difficult to make. One thing that can still be done is
	 * moving the layout-defining code out into separate methods, leaving the
	 * more important methods unburdened of the messy layout code. This is done
	 * in the following methods.
	 */

	// Formatting constraints for the dialogPane
	private GridBagConstraints getDialogPaneConstraints() {
		GridBagConstraints gc = new GridBagConstraints();

		gc.anchor = GridBagConstraints.WEST;
		gc.weightx = 0.2;
		gc.weighty = 0d;
		gc.gridwidth = GridBagConstraints.REMAINDER;
		gc.fill = GridBagConstraints.NONE;

		return gc;
	}

	// Formatting constraints for the basketPane
	private GridBagConstraints getBasketPaneConstraints() {
		GridBagConstraints gc = new GridBagConstraints();

		gc.anchor = GridBagConstraints.WEST;
		gc.weightx = 0.2;
		gc.weighty = 1.0;
		gc.gridwidth = GridBagConstraints.REMAINDER;
		gc.fill = GridBagConstraints.BOTH;

		return gc;
	}

	private GridBagConstraints getBacketScrollPaneConstraints() {
		GridBagConstraints gc = new GridBagConstraints();

		gc.fill = GridBagConstraints.BOTH;
		gc.weightx = 1.0;
		gc.weighty = 1.0;

		return gc;
	}

	public void setItems(JComboBox<String> combo) {
		this.items=combo;
		// TODO Auto-generated method stub

	}

	public void getAllQuantities() {
		quantities = new ArrayList<Integer>();
		for (StockItem x : model.getWarehouseTableModel().getTableRows()) {
			System.out.println(x.getQuantity());
			quantities.add(x.getQuantity());
		}

	}

	public void resetQuantities() {
		int i = -1;

		for (StockItem a : model.getWarehouseTableModel().getTableRows()) {
			i++;

			a.setQuantity(quantities.get(i));
			// System.out.println(a);
		}
	}

}
