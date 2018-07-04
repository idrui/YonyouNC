package nc.bs.pu.m21.alert;

import nc.bs.pu.m21.query.price.cal.OrderPriceDailyMaintainance;
import nc.bs.pu.m21.query.price.cal.OrderPriceMaintainance;
import nc.bs.pub.pa.IPreAlertPlugin;
import nc.bs.pub.pa.PreAlertContext;
import nc.bs.pub.pa.PreAlertObject;
import nc.vo.pu.pub.alert.PuAlertDataSource;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.Log;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购询价-订单无效数据重算。预警类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.1
 * @since 6.1
 * @author yangtian
 * @time 2011-11-7 下午02:57:22
 */

public class OrderPriceCalculateDailyAlert implements IPreAlertPlugin {
  static class OrderArrAlertDataSource extends PuAlertDataSource {
    private static final long serialVersionUID = 8688423592276081121L;

    @Override
    public String[] getAllDataItemExpress() {
      return null;
    }
  }

  @Override
  public PreAlertObject executeTask(PreAlertContext context)
      throws BusinessException {
    try {
      OrderPriceMaintainance orderPriceMaintainance =
          new OrderPriceDailyMaintainance();
      orderPriceMaintainance.excuteTimeTask();
    }
    catch (Exception e) {
      Log.error("采购询价计算发生错误：");/* -=notranslate=- */
      Log.error(e);
      ExceptionUtils.marsh(e);
    }
    return null;
  }

}
