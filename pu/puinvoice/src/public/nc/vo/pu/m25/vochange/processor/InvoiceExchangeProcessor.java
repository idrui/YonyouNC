package nc.vo.pu.m25.vochange.processor;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.itf.scmpub.reference.uap.bd.currency.CurrencyRate;
import nc.pubitf.uapbd.CurrencyRateUtilHelper;
import nc.vo.ic.m45.entity.PurchaseInBodyVO;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.pub.VORelationCalculate;
import nc.vo.pu.pub.util.PubSysParamUtil;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.para.NCPara;

import org.apache.commons.lang.StringUtils;

/**
 * 发票转单后汇率处理器
 * 
 * @since 6.0
 * @version 2011-7-2 下午02:57:20
 * @author 田锋涛
 */

public class InvoiceExchangeProcessor {

  /**
   * 查询最新汇率并赋值
   * 
   * @param vos
   */
  public void resetExchangeByQuery(InvoiceVO vo) {
    String origcurrency = vo.getParentVO().getCorigcurrencyid();
    String currency = vo.getParentVO().getCcurrencyid();
    UFDate billDate = vo.getParentVO().getDbilldate();
    // 组织本币
    String pk_org = vo.getParentVO().getPk_org();

    // 没有币种或者日期不再后续处理
    if (StringUtils.isEmpty(origcurrency) || StringUtils.isEmpty(currency)
        || billDate == null) {
      return;
    }

    UFDouble ngroupexchgrate = this.getGroupExchangeRate(vo);
    UFDouble nglobalexchgrate = this.getGlobalExchangeRate(vo);
    UFDouble exchangeRate =
        CurrencyRate.getCurrencySellRateByOrg(pk_org, origcurrency, currency,
            billDate);

    if (!MathTool.isZero(ngroupexchgrate)
        && !ngroupexchgrate.equals(vo.getParentVO().getNgroupexchgrate())) {
      vo.getParentVO().setNgroupexchgrate(ngroupexchgrate);
      this.calculate(vo, InvoiceHeaderVO.NGROUPEXCHGRATE);
    }
    if (!MathTool.isZero(nglobalexchgrate)
        && !nglobalexchgrate.equals(vo.getParentVO().getNglobalexchgrate())) {
      vo.getParentVO().setNglobalexchgrate(nglobalexchgrate);
      this.calculate(vo, InvoiceHeaderVO.NGLOBALEXCHGRATE);
    }

    // mengjian
    boolean flag = this.checkNexchangerate(vo.getChildrenVO());
    // 来源采购入库单时 如果来源表体行折本汇率不同
    if (!flag) {
      vo.getParentVO().setNexchangerate(exchangeRate);
      this.calculate(vo, InvoiceHeaderVO.NEXCHANGERATE);
    }

    if (!MathTool.isZero(exchangeRate)
        && !exchangeRate.equals(vo.getParentVO().getNexchangerate())) {
      vo.getParentVO().setNexchangerate(exchangeRate);
      this.calculate(vo, InvoiceHeaderVO.NEXCHANGERATE);
    }
  }

  /**
   * 查询最新汇率并赋值
   * 
   * @param vos
   */
  public void resetExchangeByQuery(InvoiceVO[] vos) {
    for (InvoiceVO vo : vos) {
      this.resetExchangeByQuery(vo);
    }
  }

  /**
   * @param vo
   * @param changeKey
   */
  private void calculate(InvoiceVO vo, String changeKey) {
    if (changeKey != null) {
      VORelationCalculate cal = new VORelationCalculate();
      for (InvoiceItemVO item : vo.getChildrenVO()) {
        cal.calculate(vo.getParentVO(), item, changeKey);
      }
    }
  }

  private boolean checkNexchangerate(InvoiceItemVO[] childrenVO) {
    if (null == childrenVO) {
      return true;
    }
    String csourcetypecode = childrenVO[0].getCsourcetypecode();
    if (!ICBillType.PurchaseIn.getCode().equals(csourcetypecode)) {
      return true;
    }
    List<String> list = new ArrayList<String>();
    for (InvoiceItemVO invoiceItemVO : childrenVO) {
      String srcbid = invoiceItemVO.getCsourcebid();
      list.add(srcbid);
    }
    VOQuery<PurchaseInBodyVO> voquery =
        new VOQuery<PurchaseInBodyVO>(PurchaseInBodyVO.class);
    PurchaseInBodyVO[] itemvos =
        voquery.query(list.toArray(new String[list.size()]));
    if (null == itemvos) {
      return true;
    }
    UFDouble nchangestdrate = itemvos[0].getNchangestdrate();
    for (PurchaseInBodyVO vo : itemvos) {
      if (!nchangestdrate.equals(vo.getNchangestdrate())) {
        return false;
      }
    }
    return true;
  }

  /**
   * 清空全局数据
   * 
   * @param vo
   */
  private void clearGlobalData(InvoiceVO vo) {
    vo.getParentVO().setNglobalexchgrate(null);
    for (InvoiceItemVO item : vo.getChildrenVO()) {
      item.setNglobalmny(null);
      item.setNglobaltaxmny(null);
    }
  }

  /**
   * 清空集团数据
   * 
   * @param vo
   */
  private void clearGroupData(InvoiceVO vo) {
    vo.getParentVO().setNgroupexchgrate(null);
    for (InvoiceItemVO item : vo.getChildrenVO()) {
      item.setNgroupmny(null);
      item.setNgrouptaxmny(null);
    }
  }

  private UFDouble getGlobalExchangeRate(InvoiceVO vo) {
    String origcurrency = vo.getParentVO().getCorigcurrencyid();
    UFDate billDate = vo.getParentVO().getDbilldate();
    // 组织本币
    String pk_org = vo.getParentVO().getPk_org();
    String orgCurrency =
        CurrencyRateUtilHelper.getInstance().getLocalCurrtypeByOrgID(pk_org);

    UFDouble nglobalexchgrate = UFDouble.ZERO_DBL;
    String nc002 = PubSysParamUtil.getNC002();
    if (NCPara.NC002_CALCULATEBYORIGCURRTYPE.getName().equals(nc002)) {
      nglobalexchgrate =
          CurrencyRate.getGlobalLocalCurrencySellRate(origcurrency, billDate);
    }
    else if (NCPara.NC002_CALCULATEBYCURRTYPE.getName().equals(nc002)) {
      nglobalexchgrate =
          CurrencyRate.getGlobalLocalCurrencySellRate(orgCurrency, billDate);
    }
    else if (NCPara.NC002_NOUSEGLOBALCURRTYPE.getName().equals(nc002)) {
      this.clearGlobalData(vo);
    }
    return nglobalexchgrate;
  }

  private UFDouble getGroupExchangeRate(InvoiceVO vo) {
    String origcurrency = vo.getParentVO().getCorigcurrencyid();
    UFDate billDate = vo.getParentVO().getDbilldate();
    // 组织本币
    String pk_org = vo.getParentVO().getPk_org();
    String orgCurrency =
        CurrencyRateUtilHelper.getInstance().getLocalCurrtypeByOrgID(pk_org);
    UFDouble ngroupexchgrate = null;
    String nc001 = PubSysParamUtil.getNC001(vo.getParentVO().getPk_group());
    if (NCPara.NC001_CALCULATEBYORIGCURRTYPE.getName().equals(nc001)) {
      ngroupexchgrate =
          CurrencyRate.getGroupLocalCurrencySellRate(origcurrency, billDate);
    }
    else if (NCPara.NC001_CALCULATEBYCURRTYPE.getName().equals(nc001)) {
      ngroupexchgrate =
          CurrencyRate.getGroupLocalCurrencySellRate(orgCurrency, billDate);
    }
    else if (NCPara.NC001_NOUSEGROUPCURRTYPE.getName().equals(nc001)) {
      this.clearGroupData(vo);
    }

    return ngroupexchgrate;
  }

}
