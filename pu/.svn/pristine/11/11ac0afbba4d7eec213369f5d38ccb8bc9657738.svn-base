package nc.vo.pu.m4t.rule;

import nc.itf.scmpub.reference.uap.bd.currency.CurrencyRate;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

/**
 * @since 6.0
 * @version 2010-11-17 下午06:12:55
 * @author wuxla
 */

public class CurrencyAndExchangeRate {
  private IKeyValue keyValue;

  public CurrencyAndExchangeRate(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * 方法功能描述：设置本位币和汇率
   * <p>
   * <b>参数说明</b>
   * 
   * @param fromOrder
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-20 上午08:24:47
   */
  public void setCurrencyAndExchangerate(boolean fromOrder, UFDate date) {
    String corigcurrencyid =
        (String) this.keyValue.getHeadValue(InitialEstHeaderVO.CORIGCURRENCYID);
    String pk_org =
        (String) this.keyValue.getHeadValue(InitialEstHeaderVO.PK_ORG);
    if (null == corigcurrencyid || null == pk_org) {
      return;
    }

    // 设置本币
    String ccurrencyid =
        (String) this.keyValue.getHeadValue(InitialEstHeaderVO.CCURRENCYID);
    if (null == ccurrencyid) {
      ccurrencyid = OrgUnitPubService.queryOrgCurrByPk(pk_org);
      this.keyValue.setHeadValue(InitialEstHeaderVO.CCURRENCYID, ccurrencyid);
    }

    // 设置汇率
    UFDouble rate =
        (UFDouble) this.keyValue.getHeadValue(InitialEstHeaderVO.NEXCHANGERATE);
    if (fromOrder && rate != null) {
      return;
    }

    // 日期为业务日期，不是入库日期，入库日期默认为组织的启用日期
    rate =
        CurrencyRate.getCurrencySellRateByOrg(pk_org, corigcurrencyid,
            ccurrencyid, date);
    this.keyValue.setHeadValue(InitialEstHeaderVO.NEXCHANGERATE, rate);
  }
}
