package nc.pubimpl.pu.m25.arap.f1;

import java.util.List;
import java.util.Map;

import nc.bs.pu.m25.ap.PaytermBeginDateQuery;
import nc.pubitf.pu.m25.ap.f1.IInvoiceQuery4F1;
import nc.vo.arap.termitem.PaymentDateVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 采购发票给应付单的查询服务实现
 * 
 * @since 6.0
 * @version 2011-9-1 下午1:28:17
 * @author zhaoyha
 */
public class InvoiceQuery4F1Impl implements IInvoiceQuery4F1 {

  @Override
  public Map<String, UFDate> queryPaytermDate(List<PaymentDateVO> pdvoList)
      throws BusinessException {
    try {
      return new PaytermBeginDateQuery().query(pdvoList);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

}
