/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-18 ����09:13:53
 */
package nc.impl.pu.settle.action;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.vo.pu.m27.entity.InvoiceStockOptionableVO;
import nc.vo.pu.m27.entity.RBInvoiceOptionableVO;
import nc.vo.pu.m27.entity.RBStockOptionableVO;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�Զ���������ѯ������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-5-18 ����09:13:53
 */
public class AutoSettleRuleQueryAction {
  /**
   * ��������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param pks
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-19 ����08:59:55
   */
  public SuperVO[] queryAutoSettleRule(String[] pks) {
    if (ArrayUtils.isEmpty(pks)) {
      return null;
    }

    VOQuery<RBStockOptionableVO> rbstockQuery =
        new VOQuery<RBStockOptionableVO>(RBStockOptionableVO.class);
    RBStockOptionableVO[] rbstocks = rbstockQuery.query(new String[] {
      pks[0]
    });
    RBStockOptionableVO rbstock = null;
    if (!ArrayUtils.isEmpty(rbstocks)) {
      rbstock = rbstocks[0];
    }
    else {
      RBStockOptionableVO rbstockVO = new RBStockOptionableVO();
      rbstockVO.setBbatchcodesame(UFBoolean.FALSE);
      rbstockVO.setBbuyersame(UFBoolean.FALSE);
      rbstockVO.setBdeptsame(UFBoolean.TRUE);
      rbstockVO.setBfinanceorgsame(UFBoolean.TRUE);
      rbstockVO.setBfreeitemsame(UFBoolean.FALSE);
      rbstockVO.setBmaterialsame(UFBoolean.TRUE);
      rbstockVO.setBnumabssame(UFBoolean.FALSE);
      rbstockVO.setBordersame(UFBoolean.FALSE);
      rbstockVO.setBorigpricesame(UFBoolean.FALSE);
      rbstockVO.setBproductorsame(UFBoolean.FALSE);
      rbstockVO.setBprojectsame(UFBoolean.FALSE);
      rbstockVO.setBsuppliersame(UFBoolean.TRUE);
      rbstockVO.setBtrantypesame(UFBoolean.FALSE);
      rbstock = rbstockVO;
    }

    VOQuery<RBInvoiceOptionableVO> rbinvoiceQuery =
        new VOQuery<RBInvoiceOptionableVO>(RBInvoiceOptionableVO.class);
    RBInvoiceOptionableVO[] rbInvoices = rbinvoiceQuery.query(new String[] {
      pks[1]
    });
    RBInvoiceOptionableVO rbInvoice = null;
    if (!ArrayUtils.isEmpty(rbInvoices)) {
      rbInvoice = rbInvoices[0];
    }
    else {
      RBInvoiceOptionableVO rbinvoiceVO = new RBInvoiceOptionableVO();
      rbinvoiceVO.setBbatchcodesame(UFBoolean.FALSE);
      rbinvoiceVO.setBbuyersame(UFBoolean.FALSE);
      rbinvoiceVO.setBdeptsame(UFBoolean.TRUE);
      rbinvoiceVO.setBfinanceorgsame(UFBoolean.TRUE);
      rbinvoiceVO.setBfreeitemsame(UFBoolean.FALSE);
      rbinvoiceVO.setBinvoicetypesame(UFBoolean.TRUE);
      rbinvoiceVO.setBmaterialsame(UFBoolean.TRUE);
      rbinvoiceVO.setBnorigpricesame(UFBoolean.TRUE);
      rbinvoiceVO.setBnumabssame(UFBoolean.FALSE);
      rbinvoiceVO.setBordersame(UFBoolean.FALSE);
      rbinvoiceVO.setBproductorsame(UFBoolean.FALSE);
      rbinvoiceVO.setBprojectsame(UFBoolean.FALSE);
      rbinvoiceVO.setBsuppliersame(UFBoolean.TRUE);
      rbInvoice = rbinvoiceVO;
    }

    VOQuery<InvoiceStockOptionableVO> invoicestockQuery =
        new VOQuery<InvoiceStockOptionableVO>(InvoiceStockOptionableVO.class);

    InvoiceStockOptionableVO[] invoiceStocks =
        invoicestockQuery.query(new String[] {
          pks[2]
        });
    InvoiceStockOptionableVO invoiceStock = null;
    if (!ArrayUtils.isEmpty(invoiceStocks)) {
      invoiceStock = invoiceStocks[0];
    }
    else {
      InvoiceStockOptionableVO invoicestockVO = new InvoiceStockOptionableVO();
      invoicestockVO.setBbatchcodesame(UFBoolean.FALSE);
      invoicestockVO.setBbuyersame(UFBoolean.FALSE);
      invoicestockVO.setBdeptsame(UFBoolean.TRUE);
      invoicestockVO.setBfinanceorgsame(UFBoolean.TRUE);
      invoicestockVO.setBfreeitemsame(UFBoolean.FALSE);
      invoicestockVO.setBmaterialsame(UFBoolean.TRUE);
      invoicestockVO.setBnumsame(UFBoolean.FALSE);
      invoicestockVO.setBorigpricesame(UFBoolean.FALSE);
      invoicestockVO.setBproductorsame(UFBoolean.FALSE);
      invoicestockVO.setBprojectsame(UFBoolean.FALSE);
      invoicestockVO.setBsuppliersame(UFBoolean.TRUE);
      invoiceStock = invoicestockVO;
    }

    return new SuperVO[] {
      rbstock, rbInvoice, invoiceStock
    };
  }
}
