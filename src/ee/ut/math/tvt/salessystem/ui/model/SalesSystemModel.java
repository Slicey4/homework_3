package ee.ut.math.tvt.salessystem.ui.model;

import java.util.List;

import javax.swing.table.TableModel;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.util.HibernateUtil;
import ee.ut.math.tvt.salessystem.domain.data.HistoryItem;

/**
 * Main model. Holds all the other models.
 */
public class SalesSystemModel {

	// Warehouse model
	private StockTableModel warehouseTableModel;

	// Current shopping cart model
	private PurchaseInfoTableModel currentPurchaseTableModel;

	// History model
	private HistoryTableModel historyTableModel;
	
	//Client model
	private ClientTableModel clientTableModel;

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
		
		clientTableModel = new ClientTableModel();
		
		// populate stock model with data from the database
		warehouseTableModel.populateWithData(domainController
				.loadWarehouseState());
		
		List<HistoryItem> previous = HibernateUtil.currentSession()
				.createQuery("from HistoryItem").list();

		historyTableModel.populateWithData(previous);
		

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

	public TableModel getClientTableModel() {
		return clientTableModel;

	}

}
