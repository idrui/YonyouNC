package nc.pubimpl.pu.tbb;

import nc.pubimpl.pu.tbb.strategy.invoicebill.InvoiceBillLinkQueryDMO;
import nc.pubimpl.pu.tbb.strategy.orderbill.OrderBillLinkQueryDMO;
import nc.pubimpl.pu.tbb.strategy.praybill.PrayBillLinkQueryDMO;
import nc.pubitf.pu.tbb.ILinkQueryForTbb;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.tb.obj.NtbParamVO;

/**
 * @since 6.0
 * @version 2011-6-20 ÉÏÎç10:20:21
 * @author wuxla
 */

public class LinkQueryForTbbImpl implements ILinkQueryForTbb {

  @Override
  public PraybillVO[] linkQuery20ForTbb(NtbParamVO param)
      throws BusinessException {
    try {
      PrayBillLinkQueryDMO dmo = new PrayBillLinkQueryDMO();
      return dmo.linkQuery20ForTbb(new NtbParamVO[] {
        param
      });
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public OrderVO[] linkQuery21ForTbb(NtbParamVO param) throws BusinessException {
    try {
      OrderBillLinkQueryDMO dmo = new OrderBillLinkQueryDMO();
      return dmo.linkQuery21ForTbb(new NtbParamVO[] {
        param
      });
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public InvoiceVO[] linkQuery25ForTbb(NtbParamVO param)
      throws BusinessException {
    try {
      InvoiceBillLinkQueryDMO dmo = new InvoiceBillLinkQueryDMO();
      return dmo.linkQuery25ForTbb(new NtbParamVO[] {
        param
      });
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

}
