package ee.ut.math.tvt.salessystem.ui.tabs;

import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;
import ee.ut.math.tvt.salessystem.ui.panels.PurchaseItemPanel;
import ee.ut.math.tvt.salessystem.util.HibernateUtil;

import java.text.NumberFormat;
import java.util.ConcurrentModificationException;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.JTableHeader;

import org.apache.log4j.Logger;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Session;
import org.hibernate.SessionException;

public class StockTab {

	private static final Logger log = Logger.getLogger(StockTab.class);

	private JButton addItem;
	// private JLabel name, quantity,price,id;

	private SalesSystemModel model;
	Session session;
	StockItem item;

	private int quantity;
	private double price;
	private long id1;

	private JTextField nameField;
	private JFormattedTextField idField;
	private JTextField descField;
	private JFormattedTextField quantityField;
	private JFormattedTextField priceField;

	private NumberFormat intFormat;
	private NumberFormat numFormat;

	public StockTab(SalesSystemModel model) {
		this.model = model;
	}

	// warehouse stock tab - consists of a menu and a table
	public Component draw() {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gc = new GridBagConstraints();
		panel.setLayout(gb);

		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.NORTH;
		gc.gridwidth = GridBagConstraints.REMAINDER;
		gc.weightx = 1.0d;
		gc.weighty = 0d;

		panel.add(drawStockMenuPane(), gc);

		gc.weighty = 1.0;
		gc.fill = GridBagConstraints.BOTH;
		panel.add(drawStockMainPane(), gc);
		return panel;
	}

	protected final void CreateAddWindow() {
		try {
			log.debug("Add new stick item:\n" + model.getWarehouseTableModel());

			Object[] options = { "Add item", "Cancel" };

			intFormat = NumberFormat.getIntegerInstance();
			numFormat = NumberFormat.getNumberInstance();

			nameField = new JTextField();
			idField = new JFormattedTextField(intFormat);
			descField = new JTextField();
			quantityField = new JFormattedTextField(intFormat);
			priceField = new JFormattedTextField(numFormat);

			final JComponent[] inputs = new JComponent[] {
					new JLabel("Product name"), nameField,
					new JLabel("Product id"), idField,
					new JLabel("Product description"), descField,
					new JLabel("Product quantity"), quantityField,
					new JLabel("Product price"), priceField,

			};

			int n = JOptionPane.showOptionDialog(null, inputs, "Add item",
					JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, // no
																				// icon
					options, options[1]);

			log.info(n);
			// 0 Accept
			if (n == 0) {

				try {

					quantity = Integer.parseInt(quantityField.getText());
					price = (double) Math.round(Double.parseDouble(priceField
							.getText()) * 100) / 100;
					id1 = Integer.parseInt(idField.getText());
					item = new StockItem(id1, nameField.getText(),
							descField.getText(), price, quantity);

					model.getWarehouseTableModel().addItem(item);
					Session session = HibernateUtil.currentSession();
					session.getTransaction().begin();

					for (StockItem x : model.getWarehouseTableModel()
							.getTableRows()) {
						if (id1 == x.getId()) {
							session.update(item);
							break;

						} else {
							session.save(item);
						}
					}
					session.getTransaction().commit();
					session.close();

				} catch (NullPointerException e) {

					e.getMessage();

				} catch (NumberFormatException e) {

					System.out.println(e.getCause());
				} catch (ConcurrentModificationException e) {
					e.printStackTrace();
				} catch (SessionException l) {
					l.printStackTrace();
				} catch (NonUniqueObjectException e) {
					e.printStackTrace();
				}

			} else {
				drawStockMenuPane();
			}
		} catch (NullPointerException e) {

			e.getMessage();

		} finally {
			JComboBox<String> combo = new JComboBox<String>();
			combo.addItem("Select one");
			// System.out.println(model.getWarehouseTableModel().getRowCount());
			for (StockItem x : model.getWarehouseTableModel().getTableRows()) {
				combo.addItem(x.getName());
			}
try{
			model.getCurrentPurchaseTableModel().setItems(combo, item);
			

		}catch(NullPointerException e){
			e.printStackTrace();}
		}

	}

	// warehouse menu
	private Component drawStockMenuPane() {
		JPanel panel = new JPanel();

		GridBagConstraints gc = new GridBagConstraints();
		GridBagLayout gb = new GridBagLayout();

		panel.setLayout(gb);
		gc.anchor = GridBagConstraints.NORTHWEST;
		gc.weightx = 0;

		addItem = new JButton("Add");
		gc.gridwidth = GridBagConstraints.RELATIVE;
		gc.weightx = 1.0;
		panel.add(addItem, gc);

		addItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				CreateAddWindow();

			}
		});

		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		return panel;
	}

	// table of the wareshouse stock
	private Component drawStockMainPane() {
		JPanel panel = new JPanel();

		JTable table = new JTable(model.getWarehouseTableModel());

		JTableHeader header = table.getTableHeader();
		header.setReorderingAllowed(false);

		JScrollPane scrollPane = new JScrollPane(table);

		GridBagConstraints gc = new GridBagConstraints();
		GridBagLayout gb = new GridBagLayout();
		gc.fill = GridBagConstraints.BOTH;
		gc.weightx = 1.0;
		gc.weighty = 1.0;

		panel.setLayout(gb);
		panel.add(scrollPane, gc);

		panel.setBorder(BorderFactory.createTitledBorder("Warehouse status"));
		return panel;
	}

}
