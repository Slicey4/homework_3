package ee.ut.math.tvt.salessystem.ui.model;

import org.apache.log4j.Logger;
import ee.ut.math.tvt.salessystem.domain.data.HistoryItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class HistoryTableModel extends SalesSystemTableModel<HistoryItem> {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(HistoryTableModel.class);

	public HistoryTableModel() {
		super(new String[] { "Date", "Time", "Price" });
	}
	
	public void addItem(HistoryItem item) {
		rows.add(item);
		fireTableDataChanged();
	}


	@Override
	public Object getColumnValue(HistoryItem item, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return item.getDate();
		case 1:
			return item.getTime();
		case 2:
			return item.getPrice();
		}
		throw new IllegalArgumentException("Column index out of range");
	}
	
	@Override
	public String toString() {
		final StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < headers.length; i++)
			buffer.append(headers[i] + "\t");
		buffer.append("\n");

		for (final HistoryItem historyItem : rows) {
			buffer.append(historyItem.getId() + "\t");
			buffer.append(historyItem.getName() + "\t");
			buffer.append(historyItem.getPrice() + "\t");
			buffer.append("\n");
		}

		return buffer.toString();
	}
}
