package ee.ut.math.tvt.salessystem.ui.model;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class HistoryTableModel extends SalesSystemTableModel<StockItem> {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(HistoryTableModel.class);

	public HistoryTableModel() {
		super(new String[] { "Date", "Time", "Price" });
	}

	@Override
	protected Object getColumnValue(StockItem item, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}
}
