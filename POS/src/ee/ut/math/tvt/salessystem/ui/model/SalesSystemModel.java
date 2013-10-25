package ee.ut.math.tvt.salessystem.ui.model;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;

/**
 * Main model. Holds all the other models.
 */
public class SalesSystemModel {

	private static final Logger log = Logger.getLogger(SalesSystemModel.class);

	// Warehouse model
	private StockTableModel warehouseTableModel;

	// Current shopping cart model
	private PurchaseInfoTableModel currentPurchaseTableModel;

	// History model
	private HistoryTableModel historyTableModel;

	private final SalesDomainController domainController;

	/**
	 * Construct application model.
	 * 
	 * @param domainController
	 *            Sales domain controller.
	 */
	public SalesSystemModel(SalesDomainController domainController) {
		this.domainController = domainController;

		warehouseTableModel = new StockTableModel();
		currentPurchaseTableModel = new PurchaseInfoTableModel();

		historyTableModel = new HistoryTableModel();

		// populate stock model with data from the warehouse
		warehouseTableModel.populateWithData(domainController
				.loadWarehouseState());

	}

	public StockTableModel getWarehouseTableModel() {
		return warehouseTableModel;
	}

	public PurchaseInfoTableModel getCurrentPurchaseTableModel() {
		return currentPurchaseTableModel;
	}

	public HistoryTableModel getHistoryTableModel() {
		return historyTableModel;
	}

	public void CreateWindow(){
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
