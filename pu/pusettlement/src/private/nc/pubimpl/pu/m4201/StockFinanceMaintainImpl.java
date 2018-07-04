package nc.pubimpl.pu.m4201;

import nc.bs.pu.m4201.StockFinanceDeleteBP;
import nc.bs.pu.m4201.StockFinanceInsertBP;
import nc.pubitf.pu.m4201.IStockFinanceMaintain;
import nc.vo.ic.m45.entity.PurchaseInVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class StockFinanceMaintainImpl implements IStockFinanceMaintain {

  @Override
  public void deleteForM45Unsign(String[] cpurchaseinids)
      throws BusinessException {
    try {
      new StockFinanceDeleteBP().delete(cpurchaseinids);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  @Override
  public void insertForM45Sign(PurchaseInVO[] purchaseinVos)
      throws BusinessException {
    try {
      new StockFinanceInsertBP().insert(purchaseinVos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

}
