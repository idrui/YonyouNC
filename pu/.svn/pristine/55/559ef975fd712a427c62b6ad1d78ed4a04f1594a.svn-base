package nc.pubimpl.pu.m25.it;

import nc.bs.pu.m25.query.pu.it.InvoiceQueryForITSettleBP;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.pubitf.pu.m25.it.IInvoiceSettleQueryForIT;
import nc.pubitf.pu.m25.it.InvoiceQueryResultForIT;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

public class InvoiceSettleQueryForITImpl implements IInvoiceSettleQueryForIT {

  @Override
  public InvoiceQueryResultForIT queryByWhereSql4IT(IQueryScheme queryScheme)
      throws BusinessException {
    try {
      return (InvoiceQueryResultForIT) new InvoiceQueryForITSettleBP()
          .queryByWhereSql(queryScheme);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  /**
   * add by liangchen1
   * 根据发票bid查出表体合同号
   * item只包含bid和ctcode
   */
  @Override
  public InvoiceItemVO[] queryCtCodeByInvoiceBid(String[] bids)
      throws BusinessException {
    if (ArrayUtils.isEmpty(bids)) {
      return null;
    }
    VOQuery<InvoiceItemVO> query =
        new VOQuery<InvoiceItemVO>(InvoiceItemVO.class, new String[] {
          InvoiceItemVO.PK_INVOICE_B, InvoiceItemVO.VCTCODE
        });
    return query.query(bids);
  }

}
