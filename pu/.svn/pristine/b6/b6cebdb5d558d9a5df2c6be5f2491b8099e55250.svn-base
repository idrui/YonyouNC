/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-3 上午10:45:07
 */
package nc.pubimpl.pu.m25.pf;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.pubitf.pu.m25.pf.IInvoiceQueryForPf;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购发票为流程平台提供查询
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-2-3 上午10:45:07
 */
public class InvoiceQueryForPfImpl implements IInvoiceQueryForPf {

  /**
   * 父类方法重写
   * 
   * @see nc.pubitf.pu.m25.pf.IInvoiceQueryForPf#queryForPf(java.lang.String)
   */
  @Override
  public InvoiceVO queryForPf(String id) throws BusinessException {
    InvoiceVO retVo = null;
    try {
      InvoiceVO[] vos =
          new BillQuery<InvoiceVO>(InvoiceVO.class).query(new String[] {
            id
          });
      if (!ArrayUtils.isEmpty(vos)) {
        retVo = vos[0];
      }
    }
    catch (Exception e) {
      // 日志异常
      ExceptionUtils.marsh(e);
    }
    return retVo;
  }

}
