package ee.ut.math.tvt.salessystem.ui.model;

import java.util.Date;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.data.DisplayableItem;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class HistoryTableModel extends SalesSystemTableModel {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(HistoryTableModel.class);

	public HistoryTableModel() {
		super(new String[] { "Date", "Time", "Price" });
	}
	
		

	
	 public void addDate(final Date d) {
	  
	        
	        rows.add(0, d);
	        log.debug("Added date ");
	        fireTableDataChanged();
	    }
	 public void addTime(final long time) {
	     
	        
	        rows.add(1, time);
	        log.debug("Added time: " + time);
	        fireTableDataChanged();
	    }
	 
	 public void addPrice(final double summa) {

		 	
	        rows.add(2, summa);
//	        rows.add(item);
	        log.debug("Added ");
	        fireTableDataChanged();
	    }


	@Override
	protected Object getColumnValue(DisplayableItem item, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return item.getId();
		case 1:
			return item.getName();
		case 2:
			
	
		}
		throw new IllegalArgumentException("Column index out of range");
	}
}
