package nc.pubimpl.pu.m21.to;

import java.util.ArrayList;
import java.util.List;

import nc.bs.pu.m21.query.price.LatestPriceQueryBP;
import nc.pubitf.pu.m21.to.IOrderPriceQuery4TO;
import nc.pubitf.pu.m21.to.IOrderPriceQueryPara4TO;
import nc.vo.pu.m21.query.price.OrderPriceData;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 为内部交易提供的查询订单最新价服务实现
 * 
 * @since 6.0
 * @version 2011-4-20 上午11:40:12
 * @author zhaoyha
 */
public class OrderPriceQuery4TOImpl implements IOrderPriceQuery4TO {

  @Override
  public OrderPriceData[] queryLatestPrice(
      List<IOrderPriceQueryPara4TO> queryParas) throws BusinessException {
    try {
      List<String> fiorgLst = new ArrayList<String>();
      List<String> moidLst = new ArrayList<String>();
      for (IOrderPriceQueryPara4TO para : queryParas) {
        for (int i = 0; i < para.getMaterial().size(); ++i) {
          fiorgLst.add(para.getFinanceOrg());
        }
        moidLst.addAll(para.getMaterial());
      }
      return new LatestPriceQueryBP().queryByFIOrgAndMaterial(fiorgLst
          .toArray(new String[fiorgLst.size()]), moidLst
          .toArray(new String[moidLst.size()]));
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }
}
