package ee.ut.math.tvt.salessystem.ui.tabs;

import ee.ut.math.tvt.salessystem.domain.data.HistoryItem;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;
import ee.ut.math.tvt.salessystem.ui.panels.PurchaseItemPanel;
import ee.ut.math.tvt.salessystem.util.HibernateUtil;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionException;

/**
 * Encapsulates everything that has to do with the purchase tab (the tab
 * labelled "Point-of-sale" in the menu).
 */
public class PurchaseTab extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(PurchaseTab.class);

	private final SalesDomainController domainController;

	private JButton newPurchase;

	private JButton submitPurchase;

	private JButton cancelPurchase;

	private PurchaseItemPanel purchasePane;

	private SalesSystemModel model;
	private ArrayList<Integer> quantities;

	private double sum;
	private double changeAmount;
	private JFormattedTextField sumField;
	private JFormattedTextField paidField;
	private JFormattedTextField changeField;

	public PurchaseTab(SalesDomainController controller, SalesSystemModel model) {
		this.domainController = controller;
		this.model = model;
	}

	/**
	 * The purchase tab. Consists of the purchase menu, current purchase dialog
	 * and shopping cart table.
	 */
	public Component draw() {
		JPanel panel = new JPanel();

		// Layout
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel.setLayout(new GridBagLayout());

		// Add the purchase menu
		panel.add(getPurchaseMenuPane(), getConstraintsForPurchaseMenu());

		// Add the main purchase-panel
		purchasePane = new PurchaseItemPanel(model);
		panel.add(purchasePane, getConstraintsForPurchasePanel());

		return panel;
	}

	// The purchase menu. Contains buttons "New purchase", "Submit", "Cancel".
	private Component getPurchaseMenuPane() {
		JPanel panel = new JPanel();

		// Initialize layout
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gc = getConstraintsForMenuButtons();

		// Initialize the buttons
		newPurchase = createNewPurchaseButton();
		submitPurchase = createConfirmButton();
		cancelPurchase = createCancelButton();

		// Add the buttons to the panel, using GridBagConstraints we defined
		// above
		panel.add(newPurchase, gc);
		panel.add(submitPurchase, gc);
		panel.add(cancelPurchase, gc);

		return panel;
	}

	// Creates the button "New purchase"
	private JButton createNewPurchaseButton() {
		JButton b = new JButton("New purchase");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getAllQuantities();
				newPurchaseButtonClicked();
				

			}
		});

		return b;
	}

	// The total sum of the order

	public double getTotalSumOfTheOrder() {
		double sum = 0;
		int row = model.getCurrentPurchaseTableModel().getRowCount();
		int column = model.getCurrentPurchaseTableModel().getColumnCount() - 1;
		for (int i = 0; i < row; i++) {
			double add = (Double) model.getCurrentPurchaseTableModel()
					.getValueAt(i, column);
			sum += add;

		}
		return sum;

	}

	// Creates the "Confirm" button
	private JButton createConfirmButton() {
		JButton b = new JButton("Confirm");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitPurchaseButtonClicked();

			}
		});
		b.setEnabled(false);

		return b;
	}

	// Creates the "Cancel" button
	private JButton createCancelButton() {
		JButton b = new JButton("Cancel");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * for(Integer x: quantities){ System.out.println(x); }
				 */

				resetQuantities();

				cancelPurchaseButtonClicked();
			}
		});
		b.setEnabled(false);

		return b;
	}

	/*
	 * === Event handlers for the menu buttons (get executed when the buttons
	 * are clicked)
	 */

	/** Event handler for the <code>new purchase</code> event. */
	protected void newPurchaseButtonClicked() {
		log.info("New sale process started");
		try {
			domainController.startNewPurchase();
			startNewSale();
		} catch (VerificationFailedException e1) {
			log.error(e1.getMessage());
		}
	}

	/** Event handler for the <code>cancel purchase</code> event. */
	public void cancelPurchaseButtonClicked() {
		log.info("Sale cancelled");

		try {
			domainController.cancelCurrentPurchase();

			endPurchaseAfterPaying();
			model.getCurrentPurchaseTableModel().clear();

		} catch (VerificationFailedException e1) {
			log.error(e1.getMessage());
		}
	}

	protected final void CreateConfirmWindow() {
		sum = getTotalSumOfTheOrder();
		try {
			log.debug("Contents of the current basket:\n"
					+ model.getCurrentPurchaseTableModel());

			Object[] options = { "Accept", "Cancel" };

			NumberFormat amountFormat = NumberFormat.getNumberInstance();

			sumField = new JFormattedTextField(amountFormat);
			sumField.setValue(new Double(sum));
			sumField.setColumns(10);
			sumField.setEditable(false);

			paidField = new JFormattedTextField(amountFormat);
			paidField.setValue(new Double(0.0));
			paidField.setColumns(10);
			paidField.addKeyListener(new KeyListener() {
				@Override
				public void keyTyped(KeyEvent e) {
				}

				@Override
				public void keyPressed(KeyEvent e) {
				}

				@Override
				public void keyReleased(KeyEvent e) {
					log.debug(e.getKeyChar());
					propertyChange();
				}
			});

			changeField = new JFormattedTextField(amountFormat);
			changeField.setValue(new Double(0.0));
			changeField.setColumns(10);
			changeField.setEditable(false);

			changeAmount = ((Number) paidField.getValue()).doubleValue() - sum;

			final JComponent[] inputs = new JComponent[] { new JLabel("Sum"),
					sumField, new JLabel("Paid"), paidField,
					new JLabel("Change"), changeField };

			int n = JOptionPane.showOptionDialog(null, inputs, "Confirm",
					JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null,
					options, options[1]);

			log.info(n);
			// 0 Accept
			if (n == 0) {
				changeAmount = ((Number) paidField.getValue()).doubleValue()
						- sum;
				if (((Number) paidField.getValue()).doubleValue() < sum) {
					JOptionPane.showMessageDialog(this, "Need more money!",
							"Try again", JOptionPane.ERROR_MESSAGE);

					changeField.setValue(new Double(0.0));
					domainController.cancelCurrentPurchase();
					dispose();

				} else {
					// actually submit item to history
					List<SoldItem> a = model.getCurrentPurchaseTableModel()
							.getTableRows();

					HistoryItem item = new HistoryItem(a);

					model.getHistoryTableModel().addItem(item);
					Session session = HibernateUtil.currentSession();

					session.getTransaction().begin();

					for (SoldItem i : a) {
						i.getStockItem().setQuantity(
								i.getStockItem().getQuantity());
						i.setHistoryItem(item);
						session.update(i.getStockItem());
					}
					session.getTransaction().commit();

					domainController.submitCurrentPurchase(a);

					model.getCurrentPurchaseTableModel().clear();

					endPurchaseAfterPaying();

				}
			} else {
				// log.info(n);
				domainController.cancelCurrentPurchase();
			}
		} catch (VerificationFailedException e1) {
			log.error(e1.getMessage());
		}catch(SessionException ee){
			ee.getMessage();
		} 
		finally {
			model.getCurrentPurchaseTableModel().clear();
			endPurchaseAfterPaying();

		}

	}

	public void propertyChange() {
		double sum = getTotalSumOfTheOrder();
		try {
			paidField.commitEdit();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		changeAmount = Double.parseDouble(paidField.getValue().toString())
				- sum;
		changeField.setValue(new Double(changeAmount));
	}

	/** Event handler for the <code>submit purchase</code> event. */
	protected void submitPurchaseButtonClicked() {
		log.info("Sale complete");
		CreateConfirmWindow();

	}

	/*
	 * === Helper methods that bring the whole purchase-tab to a certain state
	 * when called.
	 */

	// switch UI to the state that allows to proceed with the purchase
	private void startNewSale() {
		purchasePane.reset();

		purchasePane.setEnabled(true);
		submitPurchase.setEnabled(true);
		cancelPurchase.setEnabled(true);
		newPurchase.setEnabled(false);
	}

	// switch UI to the state that allows to initiate new purchase
	public void endPurchaseAfterPaying() {
		purchasePane.reset();

		cancelPurchase.setEnabled(false);
		submitPurchase.setEnabled(false);
		newPurchase.setEnabled(true);
		purchasePane.setEnabled(false);
	}

	public void getAllQuantities() {
		quantities = new ArrayList<Integer>();
		for (StockItem x : model.getWarehouseTableModel().getTableRows()) {
			//System.out.println(x.getQuantity());
			quantities.add(x.getQuantity());
		}

	}

	public void resetQuantities() {
		int i = -1;

		for (StockItem a : model.getWarehouseTableModel().getTableRows()) {
			i++;

			a.setQuantity(quantities.get(i));
			// System.out.println(a);
		}
	}
	

	/*
	 * === Next methods just create the layout constraints objects that control
	 * the the layout of different elements in the purchase tab. These
	 * definitions are brought out here to separate contents from layout, and
	 * keep the methods that actually create the components shorter and cleaner.
	 */

	private GridBagConstraints getConstraintsForPurchaseMenu() {
		GridBagConstraints gc = new GridBagConstraints();

		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.NORTH;
		gc.gridwidth = GridBagConstraints.REMAINDER;
		gc.weightx = 1.0d;
		gc.weighty = 0d;

		return gc;
	}

	private GridBagConstraints getConstraintsForPurchasePanel() {
		GridBagConstraints gc = new GridBagConstraints();

		gc.fill = GridBagConstraints.BOTH;
		gc.anchor = GridBagConstraints.NORTH;
		gc.gridwidth = GridBagConstraints.REMAINDER;
		gc.weightx = 1.0d;
		gc.weighty = 1.0;

		return gc;
	}

	// The constraints that control the layout of the buttons in the purchase
	// menu
	private GridBagConstraints getConstraintsForMenuButtons() {
		GridBagConstraints gc = new GridBagConstraints();

		gc.weightx = 0;
		gc.anchor = GridBagConstraints.CENTER;
		gc.gridwidth = GridBagConstraints.RELATIVE;

		return gc;
	}

}