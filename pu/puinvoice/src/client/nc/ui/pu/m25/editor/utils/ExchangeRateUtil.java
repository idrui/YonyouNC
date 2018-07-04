/**
 * $�ļ�˵��$
 * 
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-23 ����03:34:09
 */
package nc.ui.pu.m25.editor.utils;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.itf.scmpub.reference.uap.bd.currency.CurrencyRate;
import nc.pubitf.uapbd.CurrencyRateUtilHelper;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pub.bill.BillCardPanel;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.pub.util.PubSysParamUtil;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.scmpub.res.para.NCPara;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���ʵĹ�����,�����ڷ�Ʊ
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-3-23 ����03:34:09
 */
public class ExchangeRateUtil {

  public static void calculateBodyRows(CardEditorHelper util) {
    int[] rows = util.getRows(util.getItemCount());
    ExchangeRateUtil.calculateBodyRows(rows, util.getEditor());
  }

  public static void calculateBodyRows(CardEditorHelper util, String oldRate) {
    String newRate = util.getHeadStringValue(InvoiceHeaderVO.NEXCHANGERATE);
    if (newRate != null && !newRate.equals(oldRate)) {
      int[] rows = util.getRows(util.getItemCount());
      ExchangeRateUtil.calculateBodyRows(rows, util.getEditor());
    }
  }

  public static void calculateBodyRows(int[] rows, BillCardPanel billCardPanel) {
    if (rows == null) {
      return;
    }
    // ���ʱ仯�����¼�����
    new RelationCalculate().calculate(billCardPanel, rows,
        InvoiceHeaderVO.NEXCHANGERATE);
  }

  /**
   * ���������������ı���ʼ��ı���ʺ�Ĵ���
   * <p>
   * <b>����˵��</b>
   * 
   * @param util <p>
   * @since 6.0
   * @author tianft
   * @time 2010-4-5 ����01:31:38
   */
  public static void changeExchangeRate(CardEditorHelper util) {
    String oldExchangeRate =
        util.getHeadStringValue(InvoiceHeaderVO.NEXCHANGERATE);
    // 1.���ñ�ͷ����
    ExchangeRateUtil.setExchangerate(util);
    // 2.���ʱ仯����
    ExchangeRateUtil.calculateBodyRows(util, oldExchangeRate);
  }
  

  /**
   * �����������������ñ�ͷ����
   * <p>
   * <b>����˵��</b>
   * 
   * @param util <p>
   * @since 6.0
   * @author tianft
   * @time 2010-3-31 ����04:44:24
   */
  public static void setExchangerate(CardEditorHelper util) {
    ExchangeRateUtil.setExchangerate(util, null);
  }

  /**
   * �����������������ݴ��������������ñ�ͷ����
   * <p>
   * <b>����˵��</b>
   * 
   * @param util <p>
   * @since 6.0
   * @author tianft
   * @time 2010-3-31 ����04:44:24
   */
  public static void setExchangerate(CardEditorHelper util, UFDate billdate) {
    String origcurrency =
        util.getHeadStringValue(InvoiceHeaderVO.CORIGCURRENCYID);
    String currency = util.getHeadStringValue(InvoiceHeaderVO.CCURRENCYID);
    UFDate billDate = billdate;
    if(billDate == null){
      billDate = util.getHeadUFDateValue(InvoiceHeaderVO.DBILLDATE);
    }
    // ��֯����
    String pk_org = util.getHeadStringValue(InvoiceHeaderVO.PK_ORG);
    String orgCurrency =
        CurrencyRateUtilHelper.getInstance().getLocalCurrtypeByOrgID(pk_org);

    // û�б��ֻ������ڲ��ٺ�������
    if (StringUtils.isEmpty(origcurrency) || StringUtils.isEmpty(currency)
        || billDate == null) {
      return;
    }

    // ���ű�λ�һ���,����NC001������ȡ���� modify by wangljc at 2011-6-20 18:06:27
    UFDouble ngroupexchgrate = UFDouble.ZERO_DBL;
    String nc001 =
        PubSysParamUtil
            .getNC001(InvocationInfoProxy.getInstance().getGroupId());
    if (NCPara.NC001_CALCULATEBYORIGCURRTYPE.getName().equals(nc001)) {
      ngroupexchgrate =
          CurrencyRate.getGroupLocalCurrencySellRate(origcurrency, billDate);
    }
    else if (NCPara.NC001_CALCULATEBYCURRTYPE.getName().equals(nc001)) {
      ngroupexchgrate =
          CurrencyRate.getGroupLocalCurrencySellRate(orgCurrency, billDate);
    }

    // ȫ�ֱ�λ�һ���,����NC002������ȡ���� modify by wangljc at 2011-6-20 18:06:27
    UFDouble nglobalexchgrate = UFDouble.ZERO_DBL;
    String nc002 = PubSysParamUtil.getNC002();
    if (NCPara.NC001_CALCULATEBYORIGCURRTYPE.getName().equals(nc002)) {
      nglobalexchgrate =
          CurrencyRate.getGlobalLocalCurrencySellRate(origcurrency, billDate);
    }
    else if (NCPara.NC001_CALCULATEBYCURRTYPE.getName().equals(nc002)) {
      nglobalexchgrate =
          CurrencyRate.getGlobalLocalCurrencySellRate(orgCurrency, billDate);
    }

    if (ngroupexchgrate != null) {
      util.getEditor().getHeadItem(InvoiceHeaderVO.NGROUPEXCHGRATE)
          .setValue(ngroupexchgrate);
    }
    if (nglobalexchgrate != null) {
      util.getEditor().getHeadItem(InvoiceHeaderVO.NGLOBALEXCHGRATE)
          .setValue(nglobalexchgrate);
    }

    UFDouble exchangeRate = null;

    exchangeRate =
        CurrencyRate.getCurrencySellRateByOrg(pk_org, origcurrency, currency,
            billDate);
    if (exchangeRate == null) {
      return;
    }
    util.getEditor().getHeadItem(InvoiceHeaderVO.NEXCHANGERATE)
        .setValue(exchangeRate);

  }

}
