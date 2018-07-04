package nc.vo.pu.m21.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m21.IOrderCurrencyQuery;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.query.currency.CurrencyInfo;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>职责是重新查询汇率（由于日期发生了变化，汇率需要重新设置）
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-1-15 下午12:20:08
 */
public class CurrencyAndExchangerate {
  private IKeyValue keyValue;

  public CurrencyAndExchangerate(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  public void setCurrencyAndExchangeRate() {
    int rowcount = this.keyValue.getItemCount();
    if (0 == rowcount) {
      return;
    }

    int[] rows = new int[rowcount];
    for (int i = 0; i < rowcount; ++i) {
      rows[i] = i;
    }
    this.setCurrencyAndExchangeRate(rows);
  }

  /**
   * 方法功能描述：设置本位币和折本汇率
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author duy
   * @time 2010-3-28 下午12:52:53
   */
  public void setCurrencyAndExchangeRate(int[] rows) {
    String origCurrencyId =
        (String) this.keyValue.getHeadValue(OrderHeaderVO.CORIGCURRENCYID);
    if (origCurrencyId == null) {
      return;
    }
    UFDate date = (UFDate) this.keyValue.getHeadValue(OrderHeaderVO.DBILLDATE);

    // 构造币种汇率的查询参数
    CurrencyInfo[] infos = this.getCurrencyInfo(rows);
    if (infos.length == 0) {
      return;
    }

    // 查询币种汇率信息
    infos = this.queryCurrencyInfo(origCurrencyId, date, infos);

    // 把查询到的币种汇率信息设置到keyValue中
    this.setCurrencyInfo(infos, rows);
  }

  private CurrencyInfo[] getCurrencyInfo(int[] rows) {
    List<CurrencyInfo> infos = new ArrayList<CurrencyInfo>();
    Set<String> fids = new HashSet<String>();
    for (int row : rows) {
      // 结算财务组织ID
      Object financeId =
          this.keyValue.getBodyValue(row, OrderItemVO.PK_PSFINANCEORG);
      if (financeId == null) {
        continue;
      }

      // 去掉重复的ID
      if (fids.contains(financeId)) {
        continue;
      }

      CurrencyInfo info = new CurrencyInfo();
      info.setSettleFinanceId((String) financeId);
      infos.add(info);

      fids.add((String) financeId);
    }
    return infos.toArray(new CurrencyInfo[infos.size()]);
  }

  private CurrencyInfo[] queryCurrencyInfo(String origCurrencyId, UFDate date,
      CurrencyInfo[] currencyInfos) {
    IOrderCurrencyQuery service =
        NCLocator.getInstance().lookup(IOrderCurrencyQuery.class);
    try {
      return service.queryCurrencyInfo(origCurrencyId, date, currencyInfos);
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return currencyInfos;
  }

  private void setCurrencyInfo(CurrencyInfo[] infos, int[] rows) {
    Map<String, CurrencyInfo> map = new HashMap<String, CurrencyInfo>();
    for (CurrencyInfo info : infos) {
      map.put(info.getSettleFinanceId(), info);
    }

    for (int row : rows) {
      Object financeId =
          this.keyValue.getBodyValue(row, OrderItemVO.PK_PSFINANCEORG);
      if (financeId == null) {
        continue;
      }
      CurrencyInfo info = map.get(financeId);
      if (info == null) {
        continue;
      }
      this.keyValue.setBodyValue(row, OrderItemVO.CCURRENCYID,
          info.getCurrencyId());
      this.keyValue.setBodyValue(row, OrderItemVO.NEXCHANGERATE,
          info.getNexchangebrate());
      this.keyValue.setBodyValue(row, OrderItemVO.NGROUPEXCHGRATE,
          info.getGroupExchangeRate());
      this.keyValue.setBodyValue(row, OrderItemVO.NGLOBALEXCHGRATE,
          info.getPublicExchangeRate());
    }
  }
}
