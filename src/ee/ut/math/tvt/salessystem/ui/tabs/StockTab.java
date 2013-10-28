package ee.ut.math.tvt.salessystem.ui.tabs;

import ee.ut.math.tvt.salessystem.domain.data.HistoryItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

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

import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JFormattedTextField;

import javax.swing.table.JTableHeader;

import org.apache.log4j.Logger;

public class StockTab {

	private static final Logger log = Logger.getLogger(StockTab.class);

	private JButton addItem;
	// private JLabel name, quantity,price,id;

	private SalesSystemModel model;

	private String name, description;
	private int quantity;
	private JLabel teave;
	private double price;
	private long id;
	
<<<<<<< Updated upstream
	private JTextField nameField;
	private JFormattedTextField idField;
	private JTextField descField;
=======
	private JFormattedTextField nameField;
	private JFormattedTextField idField;
	private JFormattedTextField descField;
>>>>>>> Stashed changes
	private JFormattedTextField quantityField;
	private JFormattedTextField priceField;
	
    private NumberFormat intFormat;
    private NumberFormat numFormat;
<<<<<<< Updated upstream
=======
	
	
>>>>>>> Stashed changes

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
<<<<<<< Updated upstream

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

			int n = JOptionPane.showOptionDialog(
                    null,
                    inputs,
                    "Add item",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null, // no icon
                    options,
                    options[1]
            );
			
			log.info(n);
			// 0 Accept
			if (n == 0) {
				try {

					quantity = Integer.parseInt(quantityField.getText());
					price = (double) Math.round(Double.parseDouble(priceField
							.getText()) * 100) / 100;
					id = Integer.parseInt(idField.getText());
					long indeks = model.getWarehouseTableModel().getRowCount() + 1;
					// model.getWarehouseTableModel().getItemByName(name1);
					model.getWarehouseTableModel().addItem(
							new StockItem(id, nameField.getText(), descField
									.getText(), price, quantity));

				} catch (NullPointerException e) {

					e.getMessage();

				} catch (NumberFormatException e) {

					System.out.println(e.getCause());
				} catch (ConcurrentModificationException e) {
					e.printStackTrace();
				}

				// actually submit item to history
				model.getWarehouseTableModel().addItem(new StockItem());
				// model.getCurrentPurchaseTableModel().clear();
			} else {
				drawStockMenuPane();
			}
		} catch (NullPointerException e) {
			
			e.getMessage();

		}

	}
=======
	
	  protected final void CreateAddWindow() {
		    try {            
		            log.debug("Add new stick item:\n" + model.getWarehouseTableModel());

		            Object[] options = {"Add item", "Cancel"};
		            
		            intFormat = NumberFormat.getIntegerInstance();
		            numFormat = NumberFormat.getNumberInstance();
		            
		            nameField = new JFormattedTextField();
		            nameField.setValue(new String());
		            idField = new JFormattedTextField(intFormat);
		            //idField.setValue(new Integer(""));
		            descField = new JFormattedTextField();
		            descField.setValue(new String());
		            quantityField = new JFormattedTextField(intFormat);
		            //quantityField.setValue(new Integer(""));
		            priceField = new JFormattedTextField(numFormat);
		            //priceField.setValue(new Long(""));
		            
		            final JComponent[] inputs = new JComponent[] {
		                    new JLabel("Product name"),
		                    nameField,
		                    new JLabel("Product id"),
		                    idField,
		                    new JLabel("Product description"),
		                    descField,
		                    new JLabel("Product quantity"),
		                    quantityField,
		                    new JLabel("Product price"),
		                    priceField,
		                    
		            };
		            
		            int n = JOptionPane.showOptionDialog(
		                    null,
		                    inputs,
		                    "Add item",
		                    JOptionPane.YES_NO_OPTION,
		                    JOptionPane.PLAIN_MESSAGE,
		                    null, // no icon
		                    options,
		                    options[1]
		            );
		            
		      log.info(n);
		      // 0 Accept
		      if(n == 0){
		    	  try {
		  			name = nameField.getText();
		  			teave.setVisible(false);
		  			
		  			quantity = Integer.parseInt(quantityField.getText());
		  			price = (double) Math
		  					.round(Double.parseDouble(priceField.getText()) * 100) / 100;
		  			id = Integer.parseInt(idField.getText());
		  			long indeks = model.getWarehouseTableModel().getRowCount() + 1;
		  			//model.getWarehouseTableModel().getItemByName(name1);
		  			model.getWarehouseTableModel()
		  			.addItem(
		  					new StockItem(id,name,description,price,quantity));
		  			//frame.dispose();


		  		} catch (NullPointerException e) {
		  			teave.setVisible(true);
		  			e.getMessage();

		  		} catch (NumberFormatException e) {
		  			teave.setVisible(true);
		  			System.out.println(e.getCause());
		  		} catch (ConcurrentModificationException e) {
		  			e.printStackTrace();
		  		}
         
		          // actually submit item to history
		          model.getWarehouseTableModel().addItem(new StockItem());
		          //model.getCurrentPurchaseTableModel().clear();
		      } else {
		    	  drawStockMenuPane();   
		      }
		      } catch (NullPointerException e) {
		  			teave.setVisible(true);
		  			e.getMessage();

		  		}
		     
	          
	  }
>>>>>>> Stashed changes

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
