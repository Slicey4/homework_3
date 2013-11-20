package ee.ut.math.tvt.salessystem.ui.model;

import java.util.List;

import javax.swing.table.TableModel;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.util.HibernateUtil;
import ee.ut.math.tvt.salessystem.domain.data.HistoryItem;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;

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



		// populate stock model with data from the database
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


	
}
