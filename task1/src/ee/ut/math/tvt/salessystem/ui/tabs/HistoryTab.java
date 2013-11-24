package ee.ut.math.tvt.salessystem.ui.tabs;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


import ee.ut.math.tvt.salessystem.domain.data.HistoryItem;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.ui.model.PurchaseInfoTableModel;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

public class HistoryTab {

	private SalesSystemModel model;

	private PurchaseInfoTableModel purchase;

	private JTable sold;
	private JTable purchases;

	public HistoryTab(SalesSystemModel model) {
		this.model = model;
	}

	public Component draw() {
		JPanel panel = new JPanel();

		panel.setLayout(new GridBagLayout());

		purchase = new PurchaseInfoTableModel();

		purchases = new JTable(model.getHistoryTableModel());
		sold = new JTable(purchase);

		JScrollPane purchasesScroll = new JScrollPane(purchases);
		purchasesScroll.setBorder(BorderFactory
				.createTitledBorder("Purchase History"));

		JScrollPane soldItemScroll = new JScrollPane(sold);
		soldItemScroll.setBorder(BorderFactory
				.createTitledBorder("Purchase Details"));

		purchases.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						SelectedPurchase();
					}
				});
		
	

	

		panel.add(purchasesScroll, getPurchasesScrollConstraints());
		panel.add(soldItemScroll, getItemScrollConstraints());

		return panel;
	}

	private void SelectedPurchase() {
		purchase.clear();

		int selected = purchases.getSelectedRow();

		if (selected == -1)
			return;

		HistoryItem sold = model.getHistoryTableModel().getTableRows()
				.get(selected);
		for (SoldItem item : sold.getGoods()) {
			purchase.addItem(item);
		}
	}

	private GridBagConstraints getPurchasesScrollConstraints() {
		GridBagConstraints gc = new GridBagConstraints();

		gc.fill = GridBagConstraints.BOTH;
		gc.weightx = 1.0;
		gc.weighty = 0.5;

		return gc;
	}

	private GridBagConstraints getItemScrollConstraints() {
		GridBagConstraints gc = new GridBagConstraints();

		gc.fill = GridBagConstraints.BOTH;
		gc.gridy = 1;
		gc.weightx = 1.0;
		gc.weighty = 0.5;

		return gc;
	}
}