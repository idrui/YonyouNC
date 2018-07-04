/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-31 下午03:55:31
 */
package nc.bs.pu.est.rule.pricequery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.m21.pub.IOrderPriceQuery;
import nc.pubitf.pu.m21.pub.IOrderPriceQueryParam;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.est.rule.IEstPriceQueryInfoProvide;
import nc.vo.pu.m20.price.OrderPrice;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;
import nc.vo.pubapp.pattern.pub.MapList;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购订单的最新价询价
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-31 下午03:55:31
 */
public class EstOrderNewestPriceQryStrategy extends
    AbstractEstPriceQueryStrategy {

  /**
   * 方法功能描述：生成询价结果集。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_purorg 采购组织
   * @param pk_supplier 供应商
   * @param poParam 订单最新价查询结果
   * @param priceMap 询价结果集
   *          <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-7-20 上午10:15:57
   */
  private void createPOPriceMap(String pk_purorg, String pk_supplier,
      IOrderPriceQueryParam[] poParam,
      Map<String, IOrderPriceQueryParam> priceMap) {
    for (IOrderPriceQueryParam pp : poParam) {
      String pk_srcmaterial = pp.getMaterial();
      String key = pk_purorg + pk_supplier + pk_srcmaterial;
      priceMap.put(key, pp);
    }
  }

  /**
   * 方法功能描述：生成询订单最新价的参数信息。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pqinfo 询价信息提供接口
   * @return MapList<采购组织+供应商,采购订单最新的询价参数>
   *         <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-7-20 上午10:14:16
   */
  private MapList<String, IOrderPriceQueryParam> getPoParaMap(
      IEstPriceQueryInfoProvide[] pqinfo) {
    MapList<String, IOrderPriceQueryParam> retmapList =
        new MapList<String, IOrderPriceQueryParam>();
    for (IEstPriceQueryInfoProvide info : pqinfo) {
      String purorg = info.getPk_purchaseOrg();
      String supplier = info.getBillSupplier();
      if (StringUtil.isEmptyWithTrim(purorg)
          || StringUtil.isEmptyWithTrim(supplier)) {
        continue;
      }
      String key = purorg + supplier;
      retmapList.put(key, this.getPOPriceQryPara(info));
    }
    return retmapList;
  }

  /**
   * 方法功能描述：生成查询订单最新价的参数。
   * <p>
   * <b>参数说明</b>
   * 
   * @param info 询价提供信息
   * @return 查询订单最新价的参数
   *         <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-7-20 上午10:18:51
   */
  private IOrderPriceQueryParam getPOPriceQryPara(IEstPriceQueryInfoProvide info) {
    OrderPrice op = new OrderPrice();
    op.setMaterial(info.getPk_srcmaterial());
    return op;
  }

  /**
   * 方法功能描述：调用采购订单的服务组件，询采购订单最新价。
   * <p>
   * <b>参数说明</b>
   * 
   * @param purorg 采购组织
   * @param supplier 供应商
   * @param paras 参数
   * @return 查询结果集
   *         <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-7-20 上午10:17:38
   */
  private IOrderPriceQueryParam[] queryPOPrice(String purorg, String supplier,
      IOrderPriceQueryParam[] paras) {
    try {
      IOrderPriceQuery opq =
          NCLocator.getInstance().lookup(IOrderPriceQuery.class);
      return opq.queryLatestPrice(purorg, supplier, null, paras);
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  @Override
  protected void procResltData(EstPriceQryResltData[] resltData,
      IEstPriceQueryInfoProvide[] pqinfo) {
    MapList<String, IOrderPriceQueryParam> poParaMap =
        this.getPoParaMap(pqinfo);
    Map<String, IOrderPriceQueryParam> priceMap =
        new HashMap<String, IOrderPriceQueryParam>();
    for (int i = 0; i < pqinfo.length; i++) {
      IEstPriceQueryInfoProvide info = pqinfo[i];
      String pk_purorg = info.getPk_purchaseOrg();
      String pk_supplier = info.getBillSupplier();
      if (StringUtil.isEmptyWithTrim(pk_purorg)
          || StringUtil.isEmptyWithTrim(pk_supplier)) {
        continue;
      }
      String pk_srcmaterial = info.getPk_srcmaterial();
      String key = pk_purorg + pk_supplier + pk_srcmaterial;
      if (!priceMap.containsKey(key)) {
        String ppKey = pk_purorg + pk_supplier;
        List<IOrderPriceQueryParam> poParaList = poParaMap.get(ppKey);
        ListToArrayTool<IOrderPriceQueryParam> tool =
            new ListToArrayTool<IOrderPriceQueryParam>();
        IOrderPriceQueryParam[] poPara = tool.convertToArray(poParaList);
        poPara = this.queryPOPrice(pk_purorg, pk_supplier, poPara);
        this.createPOPriceMap(pk_purorg, pk_supplier, poPara, priceMap);
      }
      IOrderPriceQueryParam qryReslt = priceMap.get(key);
      resltData[i].setPrice(qryReslt.getPrice());
      resltData[i].setTaxPrice(qryReslt.getTaxPrice());
    }
  }
}
