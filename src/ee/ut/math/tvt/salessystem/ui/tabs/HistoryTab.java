package ee.ut.math.tvt.salessystem.ui.tabs;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.table.JTableHeader;

import ee.ut.math.tvt.salessystem.domain.data.HistoryItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.HistoryTableModel;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemTableModel;

/**
 * Encapsulates everything that has to do with the purchase tab (the tab
 * labelled "History" in the menu).
 */
public class HistoryTab extends JTable {
JPanel contentpane;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// TODO - implement!
	private SalesSystemModel model;
	private JTable table;

	public HistoryTab(SalesSystemModel model) {
    	this.model=model;
    	addMouseListener(new MouseAdapter() { 
            public void mousePressed(MouseEvent me) { 
            	System.out.println(me);}});
    	
    	
    	
    	
    } 
	

	public Component draw() {
		JPanel panel = new JPanel();
		
		panel.add(new HistoryTab(model));
		
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gc = new GridBagConstraints();
		panel.setLayout(gb);

		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.NORTH;
		gc.gridwidth = GridBagConstraints.REMAINDER;
		gc.weightx = 1.0d;
		gc.weighty = 0d;

		panel.add(drawHistoryMenuPane(), gc);

		gc.weighty = 1.0;
		gc.fill = GridBagConstraints.BOTH;
		panel.add(drawHistoryMainPane(), gc);
		return panel;
	}

	private Component drawHistoryMainPane() {
		JPanel panel = new JPanel();

		table = new JTable(model.getHistoryTableModel()){
			 /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public JPopupMenu getComponentPopupMenu() {
		            Point p = getMousePosition();
		            // mouse over table and valid row
		            if (p != null && rowAtPoint(p) >= 0) {
		                // condition for showing popup triggered by mouse
		                if (isRowSelected(rowAtPoint(p))) {
		                    return super.getComponentPopupMenu();
		                } else {
		                    return null;
		                }
		            }
		            return super.getComponentPopupMenu();
		        }

		    };
		

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
		createPopupMenu();

		panel.setBorder(BorderFactory.createTitledBorder("Purchase history"));
		return panel;
	}

	private Component drawHistoryMenuPane() {
		JPanel panel = new JPanel();

		GridBagConstraints gc = new GridBagConstraints();
		GridBagLayout gb = new GridBagLayout();

		panel.setLayout(gb);

		gc.anchor = GridBagConstraints.NORTHWEST;
		gc.weightx = 0;

		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		return panel;
	}
	private void createPopupMenu() {
        JPopupMenu popup = new JPopupMenu();
        JMenuItem myMenuItem1 = new JMenuItem("Info about bought items");
        popup.add(myMenuItem1);
       
        MouseListener popupListener = new PopupListener(popup);
        table.addMouseListener(popupListener);
    }
	
	 private class PopupListener extends MouseAdapter {

	        private JPopupMenu popup;

	        PopupListener(JPopupMenu popupMenu) {
	            popup = popupMenu;
	        }

	        @Override
	        public void mousePressed(MouseEvent e) {
	            maybeShowPopup(e);
	        }

	        @Override
	        public void mouseReleased(MouseEvent e) {
	            if (table.getSelectedRow() != -1) {
	                maybeShowPopup(e);
	            }
	        }

	        private void maybeShowPopup(MouseEvent e) {
	            if (e.isPopupTrigger()) {
	                popup.show(e.getComponent(), e.getX(), e.getY());
	            }
	        }
	    }


}