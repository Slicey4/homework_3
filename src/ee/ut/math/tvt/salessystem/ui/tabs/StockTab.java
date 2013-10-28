package ee.ut.math.tvt.salessystem.ui.tabs;

import ee.ut.math.tvt.salessystem.domain.data.HistoryItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

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

		            Object[] options = {"Add item", "Cancel"};
		            
		            JTextField nameField = new JTextField();
		            JTextField idField = new JTextField();
		            JTextField descField = new JTextField();
		            JTextField quantityField = new JTextField();
		            JTextField priceField = new JTextField();
		            
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
		                    JOptionPane.QUESTION_MESSAGE,
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
