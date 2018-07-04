/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-27 ����06:52:08
 */
package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɹ���Ʊ����ű�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-27 ����06:52:08
 */
public class N_25_SAVE extends AbstractCompiler2 {
  /**
   * ���෽����д
   * 
   * @see nc.bs.pub.compiler.AbstractCompiler2#runComClass(nc.vo.pub.compiler.PfParameterVO)
   */
  @Override
  public Object runComClass(PfParameterVO paraVo) throws BusinessException {

    try {
      super.m_tmpVo = paraVo;

      nc.vo.pu.m25.entity.InvoiceVO[] retValue = null;
      nc.vo.pu.m25.entity.InvoiceVO[] vos =
          (nc.vo.pu.m25.entity.InvoiceVO[]) this.getVos();
      /************* �����Ϊ����������������ʼ...���ܽ����޸� *********************/
      retValue =
          NCLocator.getInstance().lookup(nc.itf.pu.m25.IInvoiceApprove.class)
              .sendapprove(vos, this);
      /**************************************************************************/

      /************** ���ؽ�� *************************************************/
      return retValue;

    }
    catch (Exception ex) {
      if (ex instanceof BusinessException) {
        throw (BusinessException) ex;
      }
      throw new PFBusinessException(ex.getMessage(), ex);
    }
  }
}
