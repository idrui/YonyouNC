package nc.bs.pub.action;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * ��ע���ɹ��������ջ� ���ݶ���ִ���еĶ�ִ̬����Ķ�ִ̬���ࡣ �������ڣ�(2010-10-12)
 * 
 * @author ƽ̨�ű�����
 */
public class N_21_UNSAVEBILL extends AbstractCompiler2 {

  /**
   * N_21_UNSAVEBILL ������ע�⡣
   */
  public N_21_UNSAVEBILL() {
    super();
  }

  /*
   * ��ע��ƽ̨��дԭʼ�ű�
   */
  @Override
  public String getCodeRemark() {
    return "  super.m_tmpVo = vo;\n      nc.vo.pu.m21.entity.OrderVO[] vos =\n          (nc.vo.pu.m21.entity.OrderVO[]) this.getVos();\n      Object retValue =\n          nc.bs.framework.common.NCLocator.getInstance()\n              .lookup(nc.itf.pu.m21.IOrderApprove.class)\n              .unSendapprove(vos, this);\n      return retValue;\n";
  }

  /*
   * ��ע��ƽ̨��д������ �ӿ�ִ����
   */
  @Override
  public Object runComClass(PfParameterVO vo) throws BusinessException {
    try {
      super.m_tmpVo = vo;
      nc.vo.pu.m21.entity.OrderVO[] vos =
          (nc.vo.pu.m21.entity.OrderVO[]) this.getVos();
      Object retValue =
          nc.bs.framework.common.NCLocator.getInstance()
              .lookup(nc.itf.pu.m21.IOrderApprove.class)
              .unSendapprove(vos, this);
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
