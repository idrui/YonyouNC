/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-3 ����10:45:07
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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɹ���ƱΪ����ƽ̨�ṩ��ѯ
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-2-3 ����10:45:07
 */
public class InvoiceQueryForPfImpl implements IInvoiceQueryForPf {

  /**
   * ���෽����д
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
      // ��־�쳣
      ExceptionUtils.marsh(e);
    }
    return retVo;
  }

}
