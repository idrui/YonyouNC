package nc.vo.pu.m21.rule.impl;

import nc.bs.framework.aop.Wrapper;
import nc.bs.framework.common.InvocationInfoProxy;
import nc.itf.pu.m21.IOrderCurrencyQuery;
import nc.itf.scmpub.reference.uap.bd.currency.CurrencyRate;
import nc.pubitf.uapbd.CurrencyRateUtilHelper;
import nc.vo.pu.m21.query.currency.CurrencyInfo;
import nc.vo.pu.pub.util.PubSysParamUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.para.NCPara;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>币种和汇率信息查询服务的实现类
 * <li>支持走前台实现的组件配置，可以参见UPM文件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-3-28 上午11:36:10
 */
public class OrderCurrencyQueryImpl implements IOrderCurrencyQuery, Wrapper {

  @Override
  public CurrencyInfo[] queryCurrencyInfo(String origCurrencyId, UFDate date,
      CurrencyInfo[] currencyInfos) throws BusinessException {
    if (currencyInfos == null || currencyInfos.length == 0) {
      return currencyInfos;
    }

    try {
      // 把组织单元上的本位币信息及折本汇率填写到币种汇率信息对象上
      this.fillCurrenceInfo(currencyInfos, origCurrencyId, date);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

    return currencyInfos;
  }

  @Override
  public void setWrapping(Object wrapping) {
    //
  }

  private void fillCurrenceInfo(CurrencyInfo[] currencyInfos,
      String origCurrencyId, UFDate date) {
    for (CurrencyInfo currencyInfo : currencyInfos) {
      if (currencyInfo == null || currencyInfo.getSettleFinanceId() == null) {
        continue;
      }
      // 设置本位币
      currencyInfo.setCurrencyId(CurrencyRateUtilHelper.getInstance()
          .getLocalCurrtypeByOrgID(currencyInfo.getSettleFinanceId()));
      // 设置折本汇率
      this.setExchangebrate(currencyInfo, origCurrencyId, date);
      // 设置集团本位币汇率
      this.setGroupExchangebrate(currencyInfo, origCurrencyId, date);
      // 设置全局本位币汇率
      this.setPublicExchangebrate(currencyInfo, origCurrencyId, date);
    }
  }

  private void setExchangebrate(CurrencyInfo currencyInfo,
      String origCurrencyId, UFDate date) {
    if (origCurrencyId == null || date == null) {
      return;
    }
    // 根据结算财务组织的组织单元上的汇率方案查找折本汇率
    // 采购取的卖出汇率
    String settleFinanceId = currencyInfo.getSettleFinanceId();
    String pk_exratescheme =
        CurrencyRateUtilHelper.getInstance().getExrateschemeByOrgID(
            settleFinanceId);
    UFDouble sellrate =
        CurrencyRate.getCurrencySellRate(
            pk_exratescheme,
            origCurrencyId,
            CurrencyRateUtilHelper.getInstance().getLocalCurrtypeByOrgID(
                settleFinanceId), date);
    currencyInfo.setNexchangebrate(sellrate);
  }

  private void setGroupExchangebrate(CurrencyInfo currencyInfo,
      String origCurrencyId, UFDate date) {
    String nc001 =
        PubSysParamUtil
            .getNC001(InvocationInfoProxy.getInstance().getGroupId());
    if (NCPara.NC001_CALCULATEBYORIGCURRTYPE.getName().equals(nc001)) {
      currencyInfo.setGroupExchangeRate(CurrencyRate
          .getGroupLocalCurrencySellRate(origCurrencyId, date));
    }
    else if (NCPara.NC001_CALCULATEBYCURRTYPE.getName().equals(nc001)) {
      currencyInfo.setGroupExchangeRate(CurrencyRate
          .getGroupLocalCurrencySellRate(currencyInfo.getCurrencyId(), date));
    }
  }

  private void setPublicExchangebrate(CurrencyInfo currencyInfo,
      String origCurrencyId, UFDate date) {
    String nc002 = PubSysParamUtil.getNC002();
    if (NCPara.NC001_CALCULATEBYORIGCURRTYPE.getName().equals(nc002)) {
      currencyInfo.setPublicExchangeRate(CurrencyRate
          .getGlobalLocalCurrencySellRate(origCurrencyId, date));
    }
    else if (NCPara.NC001_CALCULATEBYCURRTYPE.getName().equals(nc002)) {
      currencyInfo.setPublicExchangeRate(CurrencyRate
          .getGlobalLocalCurrencySellRate(currencyInfo.getCurrencyId(), date));
    }
  }
}
