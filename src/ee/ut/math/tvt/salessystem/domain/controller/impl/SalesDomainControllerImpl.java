package ee.ut.math.tvt.salessystem.domain.controller.impl;

import java.util.List;

import org.hibernate.Session;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;
import ee.ut.math.tvt.salessystem.util.HibernateUtil;

/**
 * Implementation of the sales domain controller.
 */
@SuppressWarnings("unchecked")
public class SalesDomainControllerImpl implements SalesDomainController {
	private Session session = HibernateUtil.currentSession();

	public void submitCurrentPurchase(List<SoldItem> goods)
			throws VerificationFailedException {
		// XXX - Save purchase
	}
	   public void submitCurrentPurchase(List<SoldItem> goods,
               SalesSystemModel model) throws VerificationFailedException {
      
   
}
	

	public void cancelCurrentPurchase() throws VerificationFailedException {
		// XXX - Cancel current purchase
	}

	public void startNewPurchase() throws VerificationFailedException {
		// XXX - Start new purchase
	}

	public List<StockItem> loadWarehouseState() {
		// XXX mock implementation
		String query = "from StockItem";
		return session.createQuery(query).list();

	}

	public List<SoldItem>getGoods(){
	return session.createQuery("from SOLDITME").list();
	}

	public void endSession() {

		HibernateUtil.closeSession();
		System.exit(0);

	}

}
